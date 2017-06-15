/**                                               _    __ ____
 *   _ __  ___ _____   ___   __  __   ___ __     / |  / /  __/
 *  |  _ \/ _ |  _  | / _ | / / / /  / __/ /    /  | / / /__
 *  |  __/ __ |  ___|/ __ |/ /_/ /__/ __/ /__  / / v  / /__
 *  |_| /_/ |_|_|\_\/_/ |_/____/___/___/____/ /_/  /_/____/
 *
 * Code created automatically by ParallelME compiler.
 */

#pragma version(1)
#pragma rs java_package_name(br.edu.ufsj.dcomp.map)
#pragma rs_fp_relaxed

static int map1_func(int element) {

                int result;
                result = element*element;
                return result;
            }

int __attribute__((kernel)) map1(int element, uint32_t x, uint32_t y) {
	return map1_func(element);
}