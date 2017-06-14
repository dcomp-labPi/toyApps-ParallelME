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
		Random random = new Random();
		for (int x = 0; x < sizeIn; x++) {
			vectorIn[x] = random.nextInt(sizeIn);
		}
        Array<Int32> array = new Array<Int32>(vectorIn, Int32.class);
		Array<Int32> output = (Array<Int32>) array.par().filter(new Filter<Int32>() {
			@Override
			public boolean function(Int32 element) {
				boolean ret = false;
				for(int i=0;i<sizeIn;i++){
					if(element.value > vectorIn[i]){
						ret = true;
					}
				}
				return ret;
			}
		});

	}
}
