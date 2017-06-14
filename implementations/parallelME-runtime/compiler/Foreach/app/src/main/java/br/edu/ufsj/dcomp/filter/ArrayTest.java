package br.edu.ufsj.dcomp.filter;

import android.util.Log;
import android.widget.TextView;

import org.parallelme.userlibrary.Array;
import org.parallelme.userlibrary.datatype.Int32;
import org.parallelme.userlibrary.function.Filter;
import org.parallelme.userlibrary.function.Foreach;

import java.util.Locale;

public class ArrayTest {
	private ArrayTestWrapper PM_parallelME;

	public ArrayTest() {
		this.PM_parallelME = new ArrayTestWrapperImplPM();
	}

	private int varTeste;

	public void method(int size) {
		int i = 0;
		int[] tmp = new int[size];
		for (int x = 0; x < tmp.length; x++) {
			tmp[x] = ++i;
		}
		PM_parallelME.inputBind1(tmp);
		varTeste = 0;
		PM_parallelME.foreach1();
		PM_parallelME.outputBind1(tmp);

	}
}
