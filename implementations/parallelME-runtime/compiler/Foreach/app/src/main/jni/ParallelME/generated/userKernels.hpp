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
	"int foreach1_func(int element) {\n"
	"\n"
    "   element = element*element;\n"
	"	return element;\n"
	"}\n"
	"\n"
	"__kernel void foreach1(__global int* PM_data) {\n"
	"   int PM_gid = get_global_id(0);\n"
	"	PM_data[PM_gid] = foreach1_func(PM_data[PM_gid]);\n"
	"}\n"
	"\n";
#endif
