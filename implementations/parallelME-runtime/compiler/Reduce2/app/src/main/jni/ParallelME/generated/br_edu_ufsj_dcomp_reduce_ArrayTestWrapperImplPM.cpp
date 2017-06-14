/**                                               _    __ ____
 *   _ __  ___ _____   ___   __  __   ___ __     / |  / /  __/
 *  |  _ \/ _ |  _  | / _ | / / / /  / __/ /    /  | / / /__
 *  |  __/ __ |  ___|/ __ |/ /_/ /__/ __/ /__  / / v  / /__
 *  |_| /_/ |_|_|\_\/_/ |_/____/___/___/____/ /_/  /_/____/
 *
 * Code created automatically by ParallelME compiler.
 */

#include "br_edu_ufsj_dcomp_reduce_ArrayTestWrapperImplPM.h"

#include <memory>
#include <stdexcept>
#include <android/log.h>
#include <parallelme/ParallelME.hpp>
#include <parallelme/SchedulerHEFT.hpp>
#include "ParallelMEData.hpp"
#include "org_parallelme_ParallelMERuntime.h"

using namespace parallelme;

JNIEXPORT void JNICALL Java_br_edu_ufsj_dcomp_reduce_ArrayTestWrapperImplPM_reduce1
		(JNIEnv *env, jobject self, jlong PM_runtime, jlong PM_data, jintArray resultado) {
	auto PM_runtimePtr = (ParallelMERuntimeData *) PM_runtime;
	auto PM_dataPtr = (ArrayData *) PM_data;
	auto PM_task = std::make_unique<Task>(PM_runtimePtr->program);
	int PM_tileSize = floor(sqrt((float)PM_dataPtr->length));
	auto PM_tileBuffer = std::make_shared<Buffer>(sizeof(int) * PM_tileSize);
	auto PM_resultadoBuffer = std::make_shared<Buffer>(sizeof(int));
	PM_task->addKernel("reduce1_tile");
	PM_task->addKernel("reduce1");
	PM_task->setConfigFunction([=](DevicePtr &device, KernelHash &kernelHash) {
		kernelHash["reduce1_tile"]
			->setArg(0, PM_dataPtr->buffer)
			->setArg(1, PM_tileBuffer)
			->setArg(2, PM_tileSize)
			->setWorkSize(PM_tileSize);
		kernelHash["reduce1"]
			->setArg(0, PM_resultadoBuffer)
			->setArg(1, PM_dataPtr->buffer)
			->setArg(2, PM_tileBuffer)
			->setArg(3, PM_dataPtr->length)
			->setArg(4, PM_tileSize)
			->setWorkSize(1);
	});
	PM_runtimePtr->runtime->submitTask(std::move(PM_task));
	PM_runtimePtr->runtime->finish();
	PM_resultadoBuffer->copyToJArray(env, resultado);
}