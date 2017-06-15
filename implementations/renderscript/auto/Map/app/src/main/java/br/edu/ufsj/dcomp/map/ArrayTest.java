package br.edu.ufsj.dcomp.map;

import android.support.v8.renderscript.*;

public class ArrayTest {
	private ArrayTestWrapper PM_parallelME;

	public ArrayTest(RenderScript PM_mRS) {
        this.PM_parallelME = new ArrayTestWrapperImplRS(PM_mRS);
	}

    private int varTeste;

    public void method(Integer size) {
        int i = 0;
        int[] tmp = new int[size];
        for (int x = 0; x < tmp.length; x++) {
            tmp[x] = ++i;
        }
        PM_parallelME.inputBind1(tmp);
        varTeste = 0;
        PM_parallelME.map1();
    }
}
