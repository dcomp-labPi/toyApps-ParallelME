#pragma version(1)
#pragma rs java_package_name(br.edu.ufsj.dcomp.reduce)
#pragma rs_fp_relaxed

int __attribute__((kernel)) reduce(int element, uint32_t x, uint32_t y) {
	return element*element;
}