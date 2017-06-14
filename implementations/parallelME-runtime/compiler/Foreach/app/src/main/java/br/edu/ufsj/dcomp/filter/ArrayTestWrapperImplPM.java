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

public class ArrayTestWrapperImplPM implements ArrayTestWrapper {
	private long PM_array9Ptr;
	private boolean PM_array9FromImage = false;
	private static boolean isValid;
	 
	private native void foreach1(long runtimePtr, long dataPtr);
	 
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

	public void inputBind1(int[] tmp) {
		PM_array9Ptr = ParallelMERuntime.getInstance().createArray(tmp);
	}

	public void foreach1() {
		foreach1(ParallelMERuntime.getInstance().runtimePointer, PM_array9Ptr);
		PM_array9FromImage = false;
	}

	public void outputBind1(int[] tmp) {
		ParallelMERuntime.getInstance().toArray(PM_array9Ptr, tmp);
	}
}
