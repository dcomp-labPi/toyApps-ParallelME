package br.edu.ufsj.dcomp.foreach;

import org.parallelme.userlibrary.Array;
import org.parallelme.userlibrary.datatype.Int32;
import org.parallelme.userlibrary.function.Foreach;

public class ArrayUserTest {
	public void method(int size) {
		int i = 0;
		int[] tmp = new int[size];
		for (int x = 0; x < tmp.length; x++) {
			tmp[x] = ++i;
		}
        Array<Int32> array = new Array<Int32>(tmp, Int32.class);
		array.par().foreach(new Foreach<Int32>() {
			@Override
			public void function(Int32 element) {
				element.value = element.value*element.value;
			}
		});
	}
}
