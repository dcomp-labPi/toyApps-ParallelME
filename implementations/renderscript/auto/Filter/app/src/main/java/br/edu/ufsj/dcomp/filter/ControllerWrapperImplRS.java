/**                                               _    __ ____
 *   _ __  ___ _____   ___   __  __   ___ __     / |  / /  __/
 *  |  _ \/ _ |  _  | / _ | / / / /  / __/ /    /  | / / /__
 *  |  __/ __ |  ___|/ __ |/ /_/ /__/ __/ /__  / / v  / /__
 *  |_| /_/ |_|_|\_\/_/ |_/____/___/___/____/ /_/  /_/____/
 *
 * Code created automatically by ParallelME compiler.
 */

package br.edu.ufsj.dcomp.filter;

import android.support.v8.renderscript.*;

public class ControllerWrapperImplRS implements ControllerWrapper {
	private Allocation PM_array7Out;
	private Allocation PM_result10Out;
	private RenderScript PM_mRS;
	private ScriptC_Controller PM_kernel;
	public ControllerWrapperImplRS(RenderScript PM_mRS) {
		this.PM_mRS = PM_mRS;
		this.PM_kernel = new ScriptC_Controller(PM_mRS);
	}

	public boolean isValid() {
		return true;
	}

	public void inputBind1(int[] vectorIn) {
		PM_array7Out = Allocation.createSized(PM_mRS, Element.I32(PM_mRS), vectorIn.length);
		PM_array7Out.copyFrom(vectorIn);
	}

	public void filter1() {
		Type PM_gOutputTileFilter1Type = new Type.Builder(PM_mRS, Element.I32(PM_mRS))
			.setX(PM_array7Out.getType().getX())
			.create();
		Allocation PM_gOutputTileFilter1 = Allocation.createTyped(PM_mRS, PM_gOutputTileFilter1Type);
		Type PM_gOutputXSizeFilter1_AllocationType = new Type.Builder(PM_mRS, Element.I32(PM_mRS))
			.setX(1)
			.create();
		Allocation PM_gOutputXSizeFilter1_Allocation = Allocation.createTyped(PM_mRS, PM_gOutputXSizeFilter1_AllocationType);
		PM_kernel.set_PM_gOutputTileFilter1(PM_gOutputTileFilter1);
		PM_kernel.set_PM_gOutputXSizeFilter1_Allocation(PM_gOutputXSizeFilter1_Allocation);
		PM_kernel.forEach_filter1_tile(PM_array7Out, PM_gOutputTileFilter1);
		PM_kernel.invoke_filter1_setAllocationSize();
		int PM_size[] = new int[1];
		PM_kernel.get_PM_gOutputXSizeFilter1_Allocation().copyTo(PM_size);
		if (PM_size[0] > 0) {
			Type PM_result10OutType = new Type.Builder(PM_mRS, Element.I32(PM_mRS))
				.setX(PM_size[0])
				.create();
			PM_result10Out = Allocation.createTyped(PM_mRS, PM_result10OutType);
			PM_kernel.set_PM_gOutputFilter1(PM_result10Out);
			PM_kernel.set_PM_gInputFilter1(PM_array7Out);
			PM_kernel.invoke_filter1();
		}

		PM_mRS.finish();
	}
}
