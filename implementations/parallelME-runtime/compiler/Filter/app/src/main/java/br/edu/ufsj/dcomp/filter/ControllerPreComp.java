package br.edu.ufsj.dcomp.filter;

import org.parallelme.userlibrary.Array;
import org.parallelme.userlibrary.datatype.Int32;
import org.parallelme.userlibrary.function.Filter;

import java.util.Random;

public class ControllerPreComp {
	private int varTeste;

	public void method(final int sizeIn) {
		final int[] vectorIn = new int[sizeIn];
		//int[] vectorOut = new int[sizeIn];

		for (int x = 0; x < sizeIn; x++) {
			vectorIn[x] = x+1;
		}
        Array<Int32> array = new Array<Int32>(vectorIn, Int32.class);
		Array<Int32> output = (Array<Int32>) array.par().filter(new Filter<Int32>() {
			@Override
			public boolean function(Int32 element) {
				boolean ret = false;
				if(element.value > (sizeIn/2)){
					ret = true;
				}
				return ret;
			}
		});

	}
}
