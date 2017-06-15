package br.edu.ufsj.dcomp.reduce;

import android.widget.TextView;

import org.parallelme.userlibrary.Array;
import org.parallelme.userlibrary.datatype.Int32;
import org.parallelme.userlibrary.datatype.UserData;
import org.parallelme.userlibrary.function.Foreach;
import org.parallelme.userlibrary.function.Reduce;

import java.util.Locale;

public class ArrayUserTest {
	public void method(int size) {
		int i = 0;
		int[] tmp = new int[200];
		for (int x = 0; x < tmp.length; x++) {
			tmp[x] = ++i;
		}
        Array<Int32> array = new Array<Int32>(tmp, Int32.class);
		Int32 resultado = (Int32) array.par().reduce(new Reduce<Int32>() {
			@Override
			public Int32 function(Int32 element1, Int32 element2) {
				if(element1.value < element2.value){
					element1.value = element2.value;
				}
				return element1;
			}
		});
		array.toJavaArray(tmp);
	}
}
