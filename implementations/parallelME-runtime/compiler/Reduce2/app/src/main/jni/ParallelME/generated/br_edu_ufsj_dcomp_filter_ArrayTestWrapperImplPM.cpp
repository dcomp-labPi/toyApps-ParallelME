/**                                               _    __ ____
 *   _ __  ___ _____   ___   __  __   ___ __     / |  / /  __/
 *  |  _ \/ _ |  _  | / _ | / / / /  / __/ /    /  | / / /__
 *  |  __/ __ |  ___|/ __ |/ /_/ /__/ __/ /__  / / v  / /__
 *  |_| /_/ |_|_|\_\/_/ |_/____/___/___/____/ /_/  /_/____/
 *
 * Code created automatically by ParallelME compiler.
 */

#include "br_edu_ufsj_dcomp_filter_ArrayTestWrapperImplPM.h"

#include <memory>
#include <stdexcept>
#include <android/log.h>
#include <parallelme/ParallelME.hpp>
#include <parallelme/SchedulerHEFT.hpp>
#include "ParallelMEData.hpp"
#include "org_parallelme_ParallelMERuntime.h"

using namespace parallelme;

JNIEXPORT void JNICALL Java_br_edu_ufsj_dcomp_filter_ArrayTestWrapperImplPM_foreach1
		(JNIEnv *env, jobject self, jlong PM_runtime, jlong PM_data) {
	auto PM_runtimePtr = (ParallelMERuntimeData *) PM_runtime;
	auto PM_dataPtr = (ArrayData *) PM_data;
	auto PM_task = std::make_unique<Task>(PM_runtimePtr->program);
	PM_task->addKernel("foreach1");
	PM_task->setConfigFunction([=](DevicePtr &device, KernelHash &kernelHash) {
		kernelHash["foreach1"]
			->setArg(0, PM_dataPtr->buffer)
			->setWorkSize(PM_dataPtr->length);
	});
	PM_runtimePtr->runtime->submitTask(std::move(PM_task));
	PM_runtimePtr->runtime->finish();
}