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
	"int reduce1_func(int element1, int element2) {\n"
	"\n"
	"				if(element1 < element2){\n"
	"					element1 = element2;\n"
	"				}\n"
	"				return element1;\n"
	"			}\n"
	"\n"
	"__kernel void reduce1_tile(__global int* PM_data, __global int* PM_tile, int PM_tileSize) {\n"
	"	int PM_gid = get_global_id(0);\n"
	"	int PM_base = PM_gid * PM_tileSize;\n"
	"	int element1 = PM_data[PM_base];\n"
	"	int element2;\n"
	"	for (int PM_x=1; PM_x<PM_tileSize; ++PM_x) {\n"
	"		element2 = PM_data[PM_base + PM_x];\n"
	"		element1 = reduce1_func(element1, element2);\n"
	"	}\n"
	"	PM_tile[PM_gid] = element1;\n"
	"}\n"
	"\n"
	"__kernel void reduce1(__global int* PM_dataRet, __global int* PM_data, __global int* PM_tile, int PM_length, int PM_tileSize) {\n"
	"	int element1= PM_tile[0];\n"
	"	int element2;\n"
	"	for (int PM_x=1; PM_x < PM_tileSize; ++PM_x) {\n"
	"	element2 = PM_tile[PM_x];\n"
	"	element1 = reduce1_func(element1, element2);\n"
	"}\n"
	"for (int PM_x=(int) pow(floor(sqrt((float)PM_length)), 2); PM_x < PM_length; ++PM_x) {\n"
	"	element2 = PM_data[PM_x];\n"
	"	element1 = reduce1_func(element1, element2);\n"
	"}\n"
	"	*PM_dataRet = element1;\n"
	"}\n"
	"\n";
#endif
