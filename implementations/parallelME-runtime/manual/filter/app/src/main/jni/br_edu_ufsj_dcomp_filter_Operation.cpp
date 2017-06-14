//
// Created by Renan on 05/06/17.
//
#include <jni.h>
#include <android/log.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <parallelme/ParallelME.hpp>
#include "br_edu_ufsj_dcomp_filter_Operation.hpp"

using namespace parallelme;

struct NativeData{
    std::shared_ptr<Runtime> runtime;
    std::shared_ptr<Program> program;
    int testSize = 0;
};
const static char gKernels[] = "__kernel void firstFilter(int *inputVector,uint *inputSize){ uint id = get_global_id(0); uint save = 0; for(uint i=0;i<inputSize[0];i++){ if(inputVector[id] > inputVector[i]){ save = 1; } } if(save != 1){ inputVector[id] = -1; } } __kernel void filter(uint *inputVector,uint *inputSize, uint *outputVector,uint *outputSize){ outputSize[0] = 0; for(uint i=0;i<inputSize[0];i++){ if(inputVector[i] == 1){ outputVector[outputSize[0]] = inputVector[i]; outputSize[0] += 1; } } }";


void generateVector(int size, int *vector){
    for (int i = 0;i<size;i++){
        vector[i] = i+1;
    }
}

/*void printVector(int size,int *vector){
    for(int i=0; i<size;i++){
        __android_log_print(ANDROID_LOG_ERROR, "Print Debug", "vector[%d] = %d",i,vector[i]);
    }
}*/

JNIEXPORT jlong JNICALL Java_br_edu_ufsj_dcomp_filter_Operation_nativeInit(JNIEnv *env, jobject self,jint size){
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    if(!jvm) return (jlong) nullptr;

    auto dataPointer = new NativeData();
    dataPointer->runtime = std::make_shared<Runtime>(jvm);
    dataPointer->program = std::make_shared<Program>(dataPointer->runtime,gKernels);
    dataPointer->testSize = (int) size;
    return (jlong) dataPointer;
}

JNIEXPORT void JNICALL Java_br_edu_ufsj_dcomp_filter_Operation_process(JNIEnv *env,jobject self,jobject dataPointerLong){
    auto dataPointer = (NativeData *) dataPointerLong;
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    int *inputVector = (int*) malloc(sizeof(int)*dataPointer->testSize);
    generateVector(dataPointer->testSize,inputVector);
    int *outputVector= (int*) malloc(sizeof(int)*dataPointer->testSize);
    //printVector(dataPointer->testSize,vector);

    //inicializo os buffers
    auto vectorBuffer = std::make_shared<Buffer>(sizeof(int)*dataPointer->testSize);
    vectorBuffer->setSource(inputVector);
    auto vectorSizeBuffer = std::make_shared<Buffer>(sizeof(int));
    vectorSizeBuffer->setSource(&dataPointer->testSize);
    auto outputBuffer = std::make_shared<Buffer>(sizeof(int)*dataPointer->testSize);
    outputBuffer->setSource(outputVector);
    int outputSize = 0;
    auto outputSizeBuffer = std::make_shared<Buffer>(sizeof(int));
    outputSizeBuffer->setSource(&outputSize);
    //crio a task
    auto task = std::make_unique<Task>(dataPointer->program);
    task->addKernel("firstFilter");
    task->addKernel("filter");
    task->setConfigFunction([=] (parallelme::DevicePtr &device, parallelme::KernelHash &kernelHash) {
            device = device;
            kernelHash["firstFilter"]
            ->setArg(0, vectorBuffer)
            ->setArg(1, vectorSizeBuffer)
            ->setWorkSize(dataPointer->testSize);
            kernelHash["filter"]
            ->setArg(0, vectorBuffer)
            ->setArg(1, vectorSizeBuffer)
            ->setArg(2, outputBuffer)
            ->setArg(3, outputSizeBuffer)
            ->setWorkSize(1);

    });
    dataPointer->runtime->submitTask(std::move(task));
    dataPointer->runtime->finish();

    outputSizeBuffer->copyTo(&outputSize);
    /*int *outputNewVector = (int*) malloc(sizeof(int)*outputSize);
    outputBuffer->copyTo(outputNewVector);*/

    //printVector(dataPointer->testSizeResult,result);


}