//
// Created by Renan on 05/06/17.
//
#include <jni.h>
#include <android/log.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <parallelme/ParallelME.hpp>
#include "br_edu_ufsj_dcomp_reduce_Operation.hpp"

using namespace parallelme;

struct NativeData{
    std::shared_ptr<Runtime> runtime;
    std::shared_ptr<Program> program;
    int testSize = 0;
};
const static char gKernels[] = "__kernel void reduce(uint *inputVector,uint *size,uint *result){ uint id = get_global_id(0); result[0] = 0; for(uint i=0; i<size[0];i++){ if(inputVector[i] > result[0]){ result[0] = inputVector[i]; } } }";


void generateVector(int size, int *vector){
    for (int i = 0;i<size;i++){
        vector[i] = i+1;
    }
}

void printVector(int size,int *vector){
    for(int i=0; i<size;i++){
        __android_log_print(ANDROID_LOG_ERROR, "Print Debug", "vector[%d] = %d",i,vector[i]);
    }
}

JNIEXPORT jlong JNICALL Java_br_edu_ufsj_dcomp_reduce_Operation_nativeInit(JNIEnv *env, jobject self,jint size){
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    if(!jvm) return (jlong) nullptr;
    auto dataPointer = new NativeData();
    dataPointer->runtime = std::make_shared<Runtime>(jvm);
    dataPointer->program = std::make_shared<Program>(dataPointer->runtime,gKernels);
    dataPointer->testSize = (int) size;
    return (jlong) dataPointer;
}

JNIEXPORT void JNICALL Java_br_edu_ufsj_dcomp_reduce_Operation_process(JNIEnv *env,jobject self,jobject dataPointerLong){
    auto dataPointer = (NativeData *) dataPointerLong;
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    int *vector = (int*) malloc(sizeof(int)*dataPointer->testSize);
    int result = 0;
    generateVector(dataPointer->testSize,vector);

    //printVector(dataPointer->testSize,vector);

    //inicializo os buffers
    auto vectorBuffer = std::make_shared<Buffer>(sizeof(int)*dataPointer->testSize);
    vectorBuffer->setSource(vector);
    auto sizeBuffer = std::make_shared<Buffer>(sizeof(int));
    sizeBuffer->setSource(&dataPointer->testSize);
    auto resultBuffer = std::make_shared<Buffer>(sizeof(int));
    resultBuffer->setSource(&result);
    //crio a task
    auto task = std::make_unique<Task>(dataPointer->program);
    task->addKernel("reduce");
    task->setConfigFunction([=] (parallelme::DevicePtr &device, parallelme::KernelHash &kernelHash) {
            device = device;
            kernelHash["reduce"]
            ->setArg(0, vectorBuffer)
            ->setArg(1, sizeBuffer)
            ->setArg(2, resultBuffer)
            ->setWorkSize(1);
    });
    dataPointer->runtime->submitTask(std::move(task));
    dataPointer->runtime->finish();

    resultBuffer->copyTo(&result);
    //__android_log_print(ANDROID_LOG_ERROR, "Print Debug", "result = %d",result);
    //printVector(dataPointer->testSize,vector);


}