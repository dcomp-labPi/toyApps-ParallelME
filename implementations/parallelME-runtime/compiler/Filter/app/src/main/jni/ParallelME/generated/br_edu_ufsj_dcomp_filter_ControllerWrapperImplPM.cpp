/**                                               _    __ ____
 *   _ __  ___ _____   ___   __  __   ___ __     / |  / /  __/
 *  |  _ \/ _ |  _  | / _ | / / / /  / __/ /    /  | / / /__
 *  |  __/ __ |  ___|/ __ |/ /_/ /__/ __/ /__  / / v  / /__
 *  |_| /_/ |_|_|\_\/_/ |_/____/___/___/____/ /_/  /_/____/
 *
 * Code created automatically by ParallelME compiler.
 */

#include "br_edu_ufsj_dcomp_filter_ControllerWrapperImplPM.h"

#include <memory>
#include <stdexcept>
#include <android/log.h>
#include <parallelme/ParallelME.hpp>
#include <parallelme/SchedulerHEFT.hpp>
#include "ParallelMEData.hpp"
#include "org_parallelme_ParallelMERuntime.h"

using namespace parallelme;

JNIEXPORT jlong JNICALL Java_br_edu_ufsj_dcomp_filter_ControllerWrapperImplPM_filter1
		(JNIEnv *env, jobject self, jlong PM_runtime, jlong PM_data, int sizeIn) {
	auto PM_runtimePtr = (ParallelMERuntimeData *) PM_runtime;
	auto PM_dataPtr = (ArrayData *) PM_data;
	auto PM_task = std::make_unique<Task>(PM_runtimePtr->program);
	auto PM_tileBuffer = std::make_shared<Buffer>(sizeof(int) * PM_dataPtr->length);
	PM_task->addKernel("filter1_tile");
	PM_task->setConfigFunction([=](DevicePtr &device, KernelHash &kernelHash) {
		kernelHash["filter1_tile"]
			->setArg(0, PM_dataPtr->buffer)
			->setArg(1, PM_tileBuffer)
			->setArg(2, sizeIn)
			->setWorkSize(PM_dataPtr->length);
	});
	PM_runtimePtr->runtime->submitTask(std::move(PM_task));
	PM_runtimePtr->runtime->finish();
	jintArray PM_tileArray = env->NewIntArray(PM_dataPtr->length);
	PM_tileBuffer->copyToJArray(env, PM_tileArray);
	auto PM_task2 = std::make_unique<Task>(PM_runtimePtr->program);
	int PM_length = getFilterArrayLength(env, PM_tileArray);
	auto PM_dataRetPtr = (ArrayData *)Java_org_parallelme_ParallelMERuntime_nativeCreateArray__II(env, self, PM_length, 2);
	PM_task2->addKernel("filter1");
	PM_task2->setConfigFunction([=](DevicePtr &device, KernelHash &kernelHash) {
		kernelHash["filter1"]
			->setArg(0, PM_dataRetPtr->buffer)
			->setArg(1, PM_dataPtr->buffer)
			->setArg(2, PM_tileBuffer)
			->setArg(3, PM_dataPtr->length)
			->setWorkSize(1);
	});
	PM_runtimePtr->runtime->submitTask(std::move(PM_task2));
	PM_runtimePtr->runtime->finish();
	int *array = (int*) malloc(sizeof(int)*PM_length);
	PM_dataRetPtr->buffer->copyTo(array);
	return (jlong)PM_dataRetPtr;
}