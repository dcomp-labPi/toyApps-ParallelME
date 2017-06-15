/**                                               _    __ ____
 *   _ __  ___ _____   ___   __  __   ___ __     / |  / /  __/
 *  |  _ \/ _ |  _  | / _ | / / / /  / __/ /    /  | / / /__
 *  |  __/ __ |  ___|/ __ |/ /_/ /__/ __/ /__  / / v  / /__
 *  |_| /_/ |_|_|\_\/_/ |_/____/___/___/____/ /_/  /_/____/
 *
 * Code created automatically by ParallelME compiler.
 */

#ifndef USERKERNELS_HPP
#define USERKERNELS_HPP

const char userKernels[] =
	"\n"
	"\n"
	"static int map1_func(int element) {\n"
	"\n"
	"                int result = null;\n"
	"                result.value = element*element;\n"
	"                return result;\n"
	"            }\n"
	"\n"
	"__kernel void map1(__global int* PM_dataRet, __global int* PM_data) {\n"
	"int PM_gid = get_global_id(0);\n"
	"	PM_dataRet[PM_gid] = map1_func(PM_data[PM_gid]);\n"
	"}\n"
	"\n";
#endif
