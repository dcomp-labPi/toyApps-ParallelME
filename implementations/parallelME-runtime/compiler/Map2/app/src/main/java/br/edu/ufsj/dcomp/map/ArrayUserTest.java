package br.edu.ufsj.dcomp.map;

import android.widget.TextView;

import org.parallelme.userlibrary.Array;
import org.parallelme.userlibrary.datatype.Int32;
import org.parallelme.userlibrary.function.Map;

public class ArrayUserTest {
	private int varTeste;

	public void method(TextView t) {
		int i = 0;
		int[] tmp = new int[200];
		for (int x = 0; x < tmp.length; x++) {
			tmp[x] = ++i;
		}
        Array<Int32> array = new Array<Int32>(tmp, Int32.class);
        varTeste = 0;
		Array<Int32> array2 = (Array<Int32>) array.par().map(Int32.class, new Map<Int32,Int32>() {
			@Override
			public Int32 function(Int32 element) {
				Int32 result = null;
				result.value = element.value*element.value;
				return result;
			}
		});
		array.toJavaArray(tmp);
	}
}
