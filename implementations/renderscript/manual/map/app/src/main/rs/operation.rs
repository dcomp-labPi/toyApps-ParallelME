#pragma version(1)
#pragma rs java_package_name(br.edu.ufsj.dcomp.map)
#pragma rs_fp_relaxed

int __attribute__((kernel)) map(int element, uint32_t x, uint32_t y) {
    int resultado;
    resultado = element*element;
	return resultado;
}