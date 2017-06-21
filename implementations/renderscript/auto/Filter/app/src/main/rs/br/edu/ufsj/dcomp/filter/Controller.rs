/**                                               _    __ ____
 *   _ __  ___ _____   ___   __  __   ___ __     / |  / /  __/
 *  |  _ \/ _ |  _  | / _ | / / / /  / __/ /    /  | / / /__
 *  |  __/ __ |  ___|/ __ |/ /_/ /__/ __/ /__  / / v  / /__
 *  |_| /_/ |_|_|\_\/_/ |_/____/___/___/____/ /_/  /_/____/
 *
 * Code created automatically by ParallelME compiler.
 */

#pragma version(1)
#pragma rs java_package_name(br.edu.ufsj.dcomp.filter)
#pragma rs_fp_relaxed

rs_allocation PM_gInputFilter1;
rs_allocation PM_gOutputFilter1;
rs_allocation PM_gOutputXSizeFilter1_Allocation;
rs_allocation PM_gOutputTileFilter1;
int PM_gOutputXSizeFilter1;

static bool filter1_func(int element) {
    bool valor = false;

    for (int PM_x=0; PM_x<rsAllocationGetDimX(PM_gOutputTileFilter1); ++PM_x) {
        int PM_value = rsGetElementA_int(PM_gOutputTileFilter1, PM_x);

        if (PM_value < element) {
            valor = true;
        }
    }

    return valor;
}

int __attribute__((kernel)) filter1_tile(int element, uint32_t x, uint32_t y) {
	if (filter1_func(element)) {
		rsAtomicInc(&PM_gOutputXSizeFilter1);
		return x;
	} else {
		return -1;
	}
}

void filter1_setAllocationSize() {
	rsSetElementAt_int(PM_gOutputXSizeFilter1_Allocation, PM_gOutputXSizeFilter1, 0);
}

void filter1() {
	int PM_count = 0;
	for (int PM_x=0; PM_x<rsAllocationGetDimX(PM_gOutputTileFilter1); ++PM_x) {
		int PM_value = rsGetElementAt_int(PM_gOutputTileFilter1, PM_x);
		if (PM_value >= 0) {
			rsSetElementAt_int(PM_gOutputFilter1, rsGetElementAt_int(PM_gInputFilter1, PM_value), PM_count++);
		}
	}
}