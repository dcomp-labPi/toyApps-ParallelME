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
	"static bool filter1_func(int element, int sizeIn) {\n"
	"\n"
	"				if(element > (sizeIn/2)){\n"
	"					return true;\n"
	"				}\n"
	"				return false;\n"
	"			}\n"
	"\n"
	"__kernel void filter1_tile(__global int* PM_data, __global int* PM_dataTile, int sizeIn) {\n"
	"int PM_gid = get_global_id(0);\n"
	"	if (filter1_func(PM_data[PM_gid], sizeIn)) {\n"
	"		PM_dataTile[PM_gid] = PM_gid;\n"
	"	} else {\n"
	"		PM_dataTile[PM_gid] = -1;\n"
	"	}\n"
	"}\n"
	"\n"
	"__kernel void filter1(__global int* PM_dataRet, __global int* PM_data, __global int* PM_dataTile, int PM_length) {\n"
	"	int PM_count = 0;\n"
	"	for (int PM_x=0; PM_x<PM_length; ++PM_x) {\n"
	"		int PM_value = PM_dataTile[PM_x];\n"
	"		if (PM_value >= 0) {\n"
	"			PM_dataRet[PM_count++] = PM_data[PM_value];\n"
	"		}\n"
	"	}\n"
	"}\n"
	"\n";
#endif
