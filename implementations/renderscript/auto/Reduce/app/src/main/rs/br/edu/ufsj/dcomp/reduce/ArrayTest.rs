/**                                               _    __ ____
 *   _ __  ___ _____   ___   __  __   ___ __     / |  / /  __/
 *  |  _ \/ _ |  _  | / _ | / / / /  / __/ /    /  | / / /__
 *  |  __/ __ |  ___|/ __ |/ /_/ /__/ __/ /__  / / v  / /__
 *  |_| /_/ |_|_|\_\/_/ |_/____/___/___/____/ /_/  /_/____/
 *
 * Code created automatically by ParallelME compiler.
 */

#pragma version(1)
#pragma rs java_package_name(br.edu.ufsj.dcomp.reduce)
#pragma rs_fp_relaxed

rs_allocation PM_gInputReduce1;
rs_allocation PM_gTileReduce1;
rs_allocation PM_gOutputResultadoReduce1;

static int reduce1_func(int element1, int element2) {

if(element1 < element2){
element1 = element2;
}
return element1;
}

int __attribute__((kernel)) reduce1_tile(uint32_t x) {
	int PM_base = x * rsAllocationGetDimX(PM_gTileReduce1);
	int element1 = rsGetElementAt_int(PM_gInputReduce1, PM_base);
	int element2;
	for (int PM_x=1; PM_x<rsAllocationGetDimX(PM_gTileReduce1); ++PM_x) {
		element2 = rsGetElementAt_int(PM_gInputReduce1, PM_base + PM_x);
		element1 = reduce1_func(element1, element2);
	}
	return element1;
}

void reduce1() {
	int element1 = rsGetElementAt_int(PM_gTileReduce1, 0);
	int element2;
	for (int PM_x=1; PM_x < rsAllocationGetDimX(PM_gTileReduce1); ++PM_x) {
		element2 = rsGetElementAt_int(PM_gTileReduce1, PM_x);
		element1 = reduce1_func(element1, element2);
	}
	for (int PM_x=(int) pow(floor(sqrt((float)rsAllocationGetDimX(PM_gInputReduce1))), 2); PM_x < rsAllocationGetDimX(PM_gInputReduce1); ++PM_x) {
		element2 = rsGetElementAt_int(PM_gInputReduce1, PM_x);
		element1 = reduce1_func(element1, element2);
	}
	rsSetElementAt_int(PM_gOutputResultadoReduce1, element1, 0);
}

