package br.edu.ufsj.dcomp.filter;

import android.util.Log;
import android.widget.TextView;

import org.parallelme.userlibrary.Array;
import org.parallelme.userlibrary.datatype.Int32;
import org.parallelme.userlibrary.function.Filter;
import org.parallelme.userlibrary.function.Foreach;

import java.util.Locale;

public class ArrayUserTest {
	private int varTeste;

	public void method(int size) {
		int i = 0;
		int[] tmp = new int[size];
		for (int x = 0; x < tmp.length; x++) {
			tmp[x] = ++i;
		}
        Array<Int32> array = new Array<Int32>(tmp, Int32.class);
        varTeste = 0;
		array.par().foreach(new Foreach<Int32>() {
			@Override
			public void function(Int32 element) {
				element.value = element.value*element.value;
			}
		});
		array.toJavaArray(tmp);
		//Int32 result = array.reduce(new Reduce<Int32>() {
		//	@Override
		//	public Int32 function(Int32 element1, Int32 element2) {
		//		element1.value += 10;
		//		return element1;
		//	}
		//});
	}
}
