/**                                               _    __ ____
 *   _ __  ___ _____   ___   __  __   ___ __     / |  / /  __/
 *  |  _ \/ _ |  _  | / _ | / / / /  / __/ /    /  | / / /__
 *  |  __/ __ |  ___|/ __ |/ /_/ /__/ __/ /__  / / v  / /__
 *  |_| /_/ |_|_|\_\/_/ |_/____/___/___/____/ /_/  /_/____/
 *
 * Code created automatically by ParallelME compiler.
 */

package br.edu.ufsj.dcomp.reduce;

import org.parallelme.userlibrary.datatype.Int32;
import android.support.v8.renderscript.*;

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

	public Int32 reduce1() {
		int PM_gTileSizeReduce1 = (int)Math.floor(Math.sqrt(PM_array8Out.getType().getX()));
		Type PM_gOutputResultadoReduce1Type = new Type.Builder(PM_mRS, Element.I32(PM_mRS))
			.setX(1)
			.create();
		Allocation PM_gOutputResultadoReduce1 = Allocation.createTyped(PM_mRS, PM_gOutputResultadoReduce1Type);
		Type PM_gTileReduce1Type = new Type.Builder(PM_mRS, Element.I32(PM_mRS))
			.setX(PM_gTileSizeReduce1)
			.create();
		Allocation PM_gTileReduce1 = Allocation.createTyped(PM_mRS, PM_gTileReduce1Type);
		PM_kernel.set_PM_gOutputResultadoReduce1(PM_gOutputResultadoReduce1);
		PM_kernel.set_PM_gInputReduce1(PM_array8Out);
		PM_kernel.set_PM_gTileReduce1(PM_gTileReduce1);
		PM_kernel.forEach_reduce1_tile(PM_gTileReduce1);
		PM_kernel.invoke_reduce1();
		int[] PM_gOutputResultadoReduce1Tmp = new int[1];
		PM_gOutputResultadoReduce1.copyTo(PM_gOutputResultadoReduce1Tmp);
		return new Int32(PM_gOutputResultadoReduce1Tmp[0]);
	}

	public void outputBind1(int[] tmp) {
		PM_array8Out.copyTo(tmp);
	}
}
