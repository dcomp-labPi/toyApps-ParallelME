/**                                               _    __ ____
 *   _ __  ___ _____   ___   __  __   ___ __     / |  / /  __/
 *  |  _ \/ _ |  _  | / _ | / / / /  / __/ /    /  | / / /__
 *  |  __/ __ |  ___|/ __ |/ /_/ /__/ __/ /__  / / v  / /__
 *  |_| /_/ |_|_|\_\/_/ |_/____/___/___/____/ /_/  /_/____/
 *
 * Code created automatically by ParallelME compiler.
 */

package br.edu.ufsj.dcomp.map;

import org.parallelme.ParallelMERuntime;

public class ArrayTestWrapperImplPM implements ArrayTestWrapper {
	private long PM_array9Ptr;
	private boolean PM_array9FromImage = false;
	private long PM_array212Ptr;
	private boolean PM_array212FromImage = false;
	private static boolean isValid;
	 
	private native void map1(long runtimePtr, long dataPtr, long dataRetPtr);
	 
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

	public void map1() {
		PM_array212Ptr = ParallelMERuntime.getInstance().createArray(int.class, 
			ParallelMERuntime.getInstance().getLength(PM_array9Ptr));
		map1(ParallelMERuntime.getInstance().runtimePointer, PM_array9Ptr, PM_array212Ptr);
		PM_array212FromImage = false;
	}

	public void outputBind1(int[] tmp) {
		ParallelMERuntime.getInstance().toArray(PM_array9Ptr, tmp);
	}
}
