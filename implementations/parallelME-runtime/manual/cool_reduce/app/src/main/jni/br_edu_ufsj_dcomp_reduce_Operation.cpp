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
    std::shared_ptr<Buffer> vectorBuffer;
    std::shared_ptr<Buffer> sizeBuffer;
    std::shared_ptr<Buffer> lnBuffer;
    std::shared_ptr<Buffer> resultBuffer;
    std::shared_ptr<Buffer> outSizeBuffer;
    std::shared_ptr<Buffer> outputBuffer;
    int outputSize = 0;
    int testSize = 0;
};
const static char gKernels[] = "__kernel void compare(__global uint *inputVector,__global uint *inputSize,__global uint *lnOfSize,__global uint *output){ uint id = get_global_id(0); uint end = (((id*lnOfSize[0])+lnOfSize[0]) > inputSize[0])?inputSize[0]:((id*lnOfSize[0])+lnOfSize[0]); uint n1 = inputVector[id*lnOfSize[0]]; for(uint i=(id*lnOfSize[0]);i<end;i++){ if(inputVector[i] > n1){ n1 = inputVector[i]; } } output[id] = n1; } __kernel void reduce(__global uint *inputVector,__global uint *inputSize,__global uint *result){ result[0] = 0; for(uint i=0;i<inputSize[0];i++){ if(inputVector[i] > result[0]){ result[0] = inputVector[i]; } } }";


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

JNIEXPORT jlong JNICALL Java_br_edu_ufsj_dcomp_reduce_Operation_nativeInit(JNIEnv *env, jobject self){
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    if(!jvm) return (jlong) nullptr;
    auto dataPointer = new NativeData();
    dataPointer->runtime = std::make_shared<Runtime>(jvm);
    dataPointer->program = std::make_shared<Program>(dataPointer->runtime,gKernels);
    return (jlong) dataPointer;
}

JNIEXPORT void JNICALL Java_br_edu_ufsj_dcomp_reduce_Operation_process(JNIEnv *env,jobject self,jobject dataPointerLong,jint size,jobject dataPointerLong2){
    dataPointerLong2 = dataPointerLong;
    auto dataPointer = (NativeData *) dataPointerLong;
    __android_log_print(ANDROID_LOG_ERROR,"Print Debug ","P1 %d",size);
    int testSize = (int) 10000000;

    int *vector = (int*) malloc(sizeof(int)*testSize);
    //int result = 0;
    generateVector(testSize,vector);
    __android_log_print(ANDROID_LOG_ERROR,"Print Debug","P2");

    int lnOfSize = (int) log2f(testSize);
    //__android_log_print(ANDROID_LOG_ERROR, "Print Debug", "lnOfSize = %d",lnOfSize);
    auto vectorBuffer = std::make_shared<Buffer>(sizeof(int)*testSize);
    vectorBuffer->setSource(vector);
    auto sizeBuffer = std::make_shared<Buffer>(sizeof(int));
    sizeBuffer->setSource(&dataPointer->testSize);
    auto lnBuffer = std::make_shared<Buffer>(sizeof(int));
    lnBuffer->setSource(&lnOfSize);
    auto resultBuffer = std::make_shared<Buffer>(sizeof(int));
    unsigned int result = 0;
    resultBuffer->setSource(&result);
    auto outSizeBuffer = std::make_shared<Buffer>(sizeof(int));
    int outSize = (int)(testSize/lnOfSize);
    //__android_log_print(ANDROID_LOG_ERROR, "Print Debug", "lnOfSize = %d outSize = %d",lnOfSize,outSize);
    while((outSize*lnOfSize) < testSize){
        outSize++;
    }
    int outputSize = outSize;
    outSizeBuffer->setSource(&outSize);

    auto outputBuffer = std::make_shared<Buffer>(sizeof(int)*outSize);
    int *outputVector = (int*) malloc(sizeof(int)*outSize);
    outputBuffer->setSource(outputVector);
    __android_log_print(ANDROID_LOG_ERROR,"Print Debug", "P3");
    auto task = std::make_unique<Task>(dataPointer->program);
    __android_log_print(ANDROID_LOG_ERROR,"Print Debug", "P41");
    task->addKernel("compare");
    task->addKernel("reduce");
    task->setConfigFunction([=] (parallelme::DevicePtr &device, parallelme::KernelHash &kernelHash) {
            device = device;
            kernelHash["compare"]
            ->setArg(0, vectorBuffer)
            ->setArg(1, sizeBuffer)
            ->setArg(2, lnBuffer)
            ->setArg(3, outputBuffer)
            ->setWorkSize(outputSize);
            kernelHash["reduce"]
            ->setArg(0, outputBuffer)
            ->setArg(1, outSizeBuffer)
            ->setArg(2, resultBuffer)
            ->setWorkSize(1);


    });
    dataPointer->runtime->submitTask(std::move(task));
    __android_log_print(ANDROID_LOG_ERROR,"Print Debug","P4");
    dataPointer->runtime->finish();
    __android_log_print(ANDROID_LOG_ERROR,"Print Debug","P5");

}