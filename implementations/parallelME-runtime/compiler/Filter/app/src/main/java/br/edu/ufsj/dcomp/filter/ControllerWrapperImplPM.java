/**                                               _    __ ____
 *   _ __  ___ _____   ___   __  __   ___ __     / |  / /  __/
 *  |  _ \/ _ |  _  | / _ | / / / /  / __/ /    /  | / / /__
 *  |  __/ __ |  ___|/ __ |/ /_/ /__/ __/ /__  / / v  / /__
 *  |_| /_/ |_|_|\_\/_/ |_/____/___/___/____/ /_/  /_/____/
 *
 * Code created automatically by ParallelME compiler.
 */

package br.edu.ufsj.dcomp.filter;

import android.util.Log;

import org.parallelme.ParallelMERuntime;

public class ControllerWrapperImplPM implements ControllerWrapper {
	private long PM_array10Ptr;
	private boolean PM_array10FromImage = false;
	private long PM_output13Ptr;
	private boolean PM_output13FromImage = false;
	private static boolean isValid;
	 
	private native long filter1(long runtimePtr, long dataPtr, int sizeIn, int[] vectorIn);
	 
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}
	 
	static {
		try {
			System.loadLibrary("ParallelMEGenerated");
			isValid = true;
		} catch (UnsatisfiedLinkError e) {
			isValid = false;
		}
	}

	public boolean isValid() {
		return isValid && ParallelMERuntime.getInstance().runtimePointer != 0;
	}

	public void inputBind1(int[] vectorIn) {
		PM_array10Ptr = ParallelMERuntime.getInstance().createArray(vectorIn);
	}

	public void filter1(int sizeIn, int[] vectorIn) {

		PM_output13Ptr = filter1(ParallelMERuntime.getInstance().runtimePointer, PM_array10Ptr, sizeIn, vectorIn);
		PM_output13FromImage = false;
	}
}
