/**                                               _    __ ____
 *   _ __  ___ _____   ___   __  __   ___ __     / |  / /  __/
 *  |  _ \/ _ |  _  | / _ | / / / /  / __/ /    /  | / / /__
 *  |  __/ __ |  ___|/ __ |/ /_/ /__/ __/ /__  / / v  / /__
 *  |_| /_/ |_|_|\_\/_/ |_/____/___/___/____/ /_/  /_/____/
 *
 * Code created automatically by ParallelME compiler.
 */

package br.edu.ufsj.dcomp.filter;

import org.parallelme.ParallelMERuntime;

public class ControllerWrapperImplPM implements ControllerWrapper {
	private long PM_array8Ptr;
	private boolean PM_array8FromImage = false;
	private long PM_output11Ptr;
	private boolean PM_output11FromImage = false;
	private static boolean isValid;
	 
	private native long filter1(long runtimePtr, long dataPtr, int sizeIn);
	 
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
		PM_array8Ptr = ParallelMERuntime.getInstance().createArray(vectorIn);
	}

	public void filter1(int sizeIn) {
		PM_output11Ptr = filter1(ParallelMERuntime.getInstance().runtimePointer, PM_array8Ptr, sizeIn);
		PM_output11FromImage = false;
	}
}
