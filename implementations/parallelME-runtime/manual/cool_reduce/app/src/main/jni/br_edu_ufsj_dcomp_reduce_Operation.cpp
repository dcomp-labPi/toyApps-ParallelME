//
// Created by Renan on 05/06/17.
//
#include <jni.h>
#include <android/log.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <parallelme/ParallelME.hpp>
#include "br_edu_ufsj_dcomp_reduce_Operation.hpp"

using namespace parallelme;

struct NativeData{
    std::shared_ptr<Runtime> runtime;
    std::shared_ptr<Program> program;
    int testSize = 0;
};
const static char gKernels[] = "__kernel void compare(uint *inputVector,uint *inputSize,uint *lnOfSize,uint *output){ uint id = get_global_id(0); uint end = (((id*lnOfSize[0])+lnOfSize[0]) > inputSize[0])?inputSize[0]:((id*lnOfSize[0])+lnOfSize[0]); uint n1 = inputVector[id*lnOfSize[0]]; for(uint i=(id*lnOfSize[0]);i<end;i++){ if(inputVector[i] > n1){ n1 = inputVector[i]; } } output[id] = n1; } __kernel void reduce(uint *inputVector,uint *inputSize,uint *result){ result[0] = 0; for(uint i=0;i<inputSize[0];i++){ if(inputVector[i] > result[0]){ result[0] = inputVector[i]; } } }";


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
    //int result = 0;
    generateVector(dataPointer->testSize,vector);

    int lnOfSize = (int) log2f(dataPointer->testSize);
    //__android_log_print(ANDROID_LOG_ERROR, "Print Debug", "lnOfSize = %d",lnOfSize);
    auto vectorBuffer = std::make_shared<Buffer>(sizeof(int)*dataPointer->testSize);
    vectorBuffer->setSource(vector);
    auto sizeBuffer = std::make_shared<Buffer>(sizeof(int));
    sizeBuffer->setSource(&dataPointer->testSize);
    auto lnBuffer = std::make_shared<Buffer>(sizeof(int));
    lnBuffer->setSource(&lnOfSize);
    auto resultBuffer = std::make_shared<Buffer>(sizeof(int));
    unsigned int result = 0;
    resultBuffer->setSource(&result);
    auto outSizeBuffer = std::make_shared<Buffer>(sizeof(int));
    int outSize = (int)(dataPointer->testSize/lnOfSize);
    __android_log_print(ANDROID_LOG_ERROR, "Print Debug", "lnOfSize = %d outSize = %d",lnOfSize,outSize);
    while((outSize*lnOfSize) < dataPointer->testSize){
        outSize++;
    }
    outSizeBuffer->setSource(&outSize);

    auto outputBuffer = std::make_shared<Buffer>(sizeof(int)*outSize);
    int *outputVector = (int*) malloc(sizeof(int)*outSize);
    outputBuffer->setSource(outputVector);


    auto task = std::make_unique<Task>(dataPointer->program);
    task->addKernel("compare");
    task->addKernel("reduce");
    task->setConfigFunction([=] (parallelme::DevicePtr &device, parallelme::KernelHash &kernelHash) {
            device = device;
            kernelHash["compare"]
            ->setArg(0, vectorBuffer)
            ->setArg(1, sizeBuffer)
            ->setArg(2, lnBuffer)
            ->setArg(3, outputBuffer)
            ->setWorkSize(outSize);
            kernelHash["reduce"]
            ->setArg(0, outputBuffer)
            ->setArg(1, outSizeBuffer)
            ->setArg(2, resultBuffer)
            ->setWorkSize(1);


    });
    dataPointer->runtime->submitTask(std::move(task));
    dataPointer->runtime->finish();
    resultBuffer->copyTo(&result);
    //printVector(outSize,outputVector);
    //resultBuffer->copyTo(&result);
    __android_log_print(ANDROID_LOG_ERROR, "Print Debug", "result = %d",result);
    //printVector(dataPointer->testSize,vector);
/*
    //inicializo os buffers

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
            ->setWorkSize((int)dataPointer->testSize/lnOfSize);
    });
    dataPointer->runtime->submitTask(std::move(task));
    dataPointer->runtime->finish();

    resultBuffer->copyTo(&result);
    //__android_log_print(ANDROID_LOG_ERROR, "Print Debug", "result = %d",result);
    //printVector(dataPointer->testSize,vector);

*/
}