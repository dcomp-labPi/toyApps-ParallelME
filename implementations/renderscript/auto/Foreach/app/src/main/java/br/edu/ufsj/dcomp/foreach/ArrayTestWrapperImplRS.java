/**                                               _    __ ____
 *   _ __  ___ _____   ___   __  __   ___ __     / |  / /  __/
 *  |  _ \/ _ |  _  | / _ | / / / /  / __/ /    /  | / / /__
 *  |  __/ __ |  ___|/ __ |/ /_/ /__/ __/ /__  / / v  / /__
 *  |_| /_/ |_|_|\_\/_/ |_/____/___/___/____/ /_/  /_/____/
 *
 * Code created automatically by ParallelME compiler.
 */

package br.edu.ufsj.dcomp.foreach;

import android.support.v8.renderscript.*;

import foreach.ScriptC_ArrayTest;

public class ArrayTestWrapperImplRS implements ArrayTestWrapper {
	private Allocation PM_array8Out;
	private boolean PM_array8FromImage = false;
	private RenderScript PM_mRS;
	private ScriptC_ArrayTest PM_kernel;
	public ArrayTestWrapperImplRS(RenderScript PM_mRS) {
		this.PM_mRS = PM_mRS;
		this.PM_kernel = new ScriptC_ArrayTest(PM_mRS);
	}

	public boolean isValid() {
		return true;
	}

	public void inputBind1(int[] tmp) {
		PM_array8Out = Allocation.createSized(PM_mRS, Element.I32(PM_mRS), tmp.length);
		PM_array8Out.copyFrom(tmp);
	}

	public void foreach1() {
		PM_kernel.forEach_foreach1(PM_array8Out, PM_array8Out);

		PM_mRS.finish();
	}
}
