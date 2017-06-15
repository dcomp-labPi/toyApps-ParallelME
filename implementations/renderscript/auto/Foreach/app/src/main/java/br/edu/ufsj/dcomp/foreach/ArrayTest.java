package br.edu.ufsj.dcomp.foreach;

import android.support.v8.renderscript.*;

public class ArrayTest {
	private ArrayTestWrapper PM_parallelME;

	public ArrayTest(RenderScript PM_mRS) {
		this.PM_parallelME = new ArrayTestWrapperImplRS(PM_mRS);
	}

	public void method(int size) {
		int i = 0;
		int[] tmp = new int[size];
		for (int x = 0; x < tmp.length; x++) {
			tmp[x] = ++i;
		}
		PM_parallelME.inputBind1(tmp);
		PM_parallelME.foreach1();
	}
}
