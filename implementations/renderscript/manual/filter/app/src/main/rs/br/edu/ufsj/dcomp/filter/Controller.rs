#pragma version(1)
#pragma rs java_package_name(br.edu.ufsj.dcomp.filter)
#pragma rs_fp_relaxed

rs_allocation gInputFilter1;
rs_allocation gOutputFilter1;
rs_allocation gOutputXSizeFilter1_Allocation;
rs_allocation gOutputTileFilter1;
int gOutputXSizeFilter1;
int gSizeInFilter1;

static bool filter1_func(int element) {
    if(element > (gSizeInFilter1/2)){
        return true;
    }
    return false;
}

int __attribute__((kernel)) filter1_tile(int element, uint32_t x, uint32_t y) {
	if (filter1_func(element)) {
		rsAtomicInc(&gOutputXSizeFilter1);
		return x;
	} else {
		return -1;
	}
}

void filter1_setAllocationSize() {
	rsSetElementAt_int(gOutputXSizeFilter1_Allocation, gOutputXSizeFilter1, 0);
}

void filter1() {
	int count = 0;
	for (int x=0; x<rsAllocationGetDimX(gOutputTileFilter1); ++x) {
		int value = rsGetElementAt_int(gOutputTileFilter1, x);
		if (value >= 0) {
			rsSetElementAt_int(gOutputFilter1, rsGetElementAt_int(gInputFilter1, value), count++);
		}
	}
}