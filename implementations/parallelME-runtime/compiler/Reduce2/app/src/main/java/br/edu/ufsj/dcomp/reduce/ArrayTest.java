package br.edu.ufsj.dcomp.reduce;

import android.provider.Settings;
import android.widget.TextView;

import org.parallelme.userlibrary.Array;
import org.parallelme.userlibrary.datatype.Int32;
import org.parallelme.userlibrary.function.Reduce;

public class ArrayTest {
	private ArrayTestWrapper PM_parallelME;

	public ArrayTest() {
		this.PM_parallelME = new ArrayTestWrapperImplPM();
	}

	private int varTeste;

	public void method(TextView t) {
		long start = System.currentTimeMillis();
		int i = 0;
		int[] tmp = new int[50000];
		for (int x = 0; x < tmp.length; x++) {
			tmp[x] = ++i;
		}
		PM_parallelME.inputBind1(tmp);
		varTeste = 0;
		Int32 resultado = PM_parallelME.reduce1();
		long last = System.currentTimeMillis();
		System.out.println("\n\n#> "+resultado.value+" TIME: "+(last-start)+"\n");
	}
}
