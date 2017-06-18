#pragma version(1)
#pragma rs java_package_name(br.edu.ufsj.dcomp.foreach)
#pragma rs_fp_relaxed

int __attribute__((kernel)) foreach(int element, uint32_t x, uint32_t y) {
	return element*element;
}