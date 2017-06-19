#pragma version(1)
#pragma rs java_package_name(br.edu.ufsj.dcomp.reduce)
#pragma rs_fp_relaxed

rs_allocation input;
rs_allocation menor;
rs_allocation output;

static int func(int elementA, int elementB) {
    if(elementA < elementB){
        elementA = elementB;
    }
    return elementA;
}

int __attribute__((kernel)) reduce1_tile(uint32_t x) {
	int base = x * rsAllocationGetDimX(menor);
	int elementA = rsGetElementAt_int(input, base);
	int elementB;
	for (int xx=1; xx<rsAllocationGetDimX(menor); ++xx) {
		elementB = rsGetElementAt_int(input, base+xx);
		elementA = func(elementA, elementB);
	}
	return elementA;
}

void reduce1() {
	int elementA = rsGetElementAt_int(menor, 0);
	int elementB;
	for (int xx=1; xx < rsAllocationGetDimX(menor); ++xx) {
		elementB = rsGetElementAt_int(menor, xx);
		elementA = func(elementA, elementB);
	}
	for (int xx=(int) pow(floor(sqrt((float)rsAllocationGetDimX(input))), 2); xx < rsAllocationGetDimX(input); ++xx) {
		elementB = rsGetElementAt_int(input, xx);
		elementA = func(elementA, elementB);
	}
	rsSetElementAt_int(output, elementA, 0);
}

