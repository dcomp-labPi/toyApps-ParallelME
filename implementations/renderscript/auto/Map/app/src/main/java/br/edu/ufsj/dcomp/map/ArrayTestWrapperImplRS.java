/**                                               _    __ ____
 *   _ __  ___ _____   ___   __  __   ___ __     / |  / /  __/
 *  |  _ \/ _ |  _  | / _ | / / / /  / __/ /    /  | / / /__
 *  |  __/ __ |  ___|/ __ |/ /_/ /__/ __/ /__  / / v  / /__
 *  |_| /_/ |_|_|\_\/_/ |_/____/___/___/____/ /_/  /_/____/
 *
 * Code created automatically by ParallelME compiler.
 */

package br.edu.ufsj.dcomp.map;

import android.support.v8.renderscript.*;

public class ArrayTestWrapperImplRS implements ArrayTestWrapper {
	private Allocation PM_array9Out;
	private boolean PM_array9FromImage = false;
	private Allocation PM_array212Out;
	private boolean PM_array212FromImage = false;
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
		PM_array9Out = Allocation.createSized(PM_mRS, Element.I32(PM_mRS), tmp.length);
		PM_array9Out.copyFrom(tmp);
	}

	public void map1() {
		Type PM_array212OutType = new Type.Builder(PM_mRS, Element.I32(PM_mRS))
			.setX(PM_array9Out.getType().getX())
			.create();
		PM_array212Out = Allocation.createTyped(PM_mRS, PM_array212OutType);
		PM_kernel.forEach_map1(PM_array9Out, PM_array212Out);
		PM_array212FromImage = false;
	}
}
