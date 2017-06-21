//
// Created by Renan on 05/06/17.
//
#include <jni.h>
#include <android/log.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <parallelme/ParallelME.hpp>
#include "br_edu_ufsj_dcomp_foreach_Operation.hpp"

using namespace parallelme;

struct NativeData{
    std::shared_ptr<Runtime> runtime;
    std::shared_ptr<Program> program;
    int dataSize;
    std::shared_ptr<Buffer> buffer;

};
const static char gKernels[] = "__kernel void foreach(__global uint *inputVector){ uint id = get_global_id(0); inputVector[id] = inputVector[id]*inputVector[id]; }";

unsigned short lfsr = 0xACE1u;
unsigned bit;

/*unsigned randomize()
{
bit  = ((lfsr >> 0) ^ (lfsr >> 2) ^ (lfsr >> 3) ^ (lfsr >> 5) ) & 1;
return lfsr =  (lfsr >> 1) | (bit << 15);
}
*/
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
JNIEXPORT jlong JNICALL Java_br_edu_ufsj_dcomp_foreach_Operation_nativeUpdate(JNIEnv *env, jobject self,jobject dataPointerLong,jint size){
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    if(!jvm) return (jlong) nullptr;
    auto dataPointer = (NativeData *) dataPointerLong;
    dataPointer->buffer = std::make_shared<Buffer>(sizeof(int)*size);
    int *vector = (int*) malloc(sizeof(int)*size);
    generateVector(size,vector);
    dataPointer->buffer->setSource(vector);
    dataPointer->dataSize = size;
    return (jlong) dataPointer;
}

JNIEXPORT jlong JNICALL Java_br_edu_ufsj_dcomp_foreach_Operation_nativeInit(JNIEnv *env, jobject self,jint size){
    JavaVM *jvm;
    env->GetJavaVM(&jvm);
    if(!jvm) return (jlong) nullptr;
    auto dataPointer = new NativeData();
    dataPointer->runtime = std::make_shared<Runtime>(jvm);
    dataPointer->program = std::make_shared<Program>(dataPointer->runtime,gKernels);
    dataPointer->dataSize = size;
    return (jlong) dataPointer;
}

JNIEXPORT void JNICALL Java_br_edu_ufsj_dcomp_foreach_Operation_process(JNIEnv *env,jobject self,jobject dataPointerLong){
    auto dataPointer = (NativeData *) dataPointerLong;

    dataPointer->buffer = std::make_shared<Buffer>(sizeof(int)*dataPointer->dataSize);
    int *vector = (int*) malloc(sizeof(int)*dataPointer->dataSize);
    generateVector(dataPointer->dataSize,vector);
    dataPointer->buffer->setSource(vector);

    //crio a task
    auto task = std::make_unique<Task>(dataPointer->program);
    task->addKernel("foreach");
    task->setConfigFunction([=] (parallelme::DevicePtr &device, parallelme::KernelHash &kernelHash) {
            device = device;
            kernelHash["foreach"]
            ->setArg(0, dataPointer->buffer)
            ->setWorkSize(dataPointer->dataSize);
    });
    dataPointer->runtime->submitTask(std::move(task));
    dataPointer->runtime->finish();

    std::free(vector);



}