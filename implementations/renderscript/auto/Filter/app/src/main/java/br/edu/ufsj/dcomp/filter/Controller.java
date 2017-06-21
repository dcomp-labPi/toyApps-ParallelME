package br.edu.ufsj.dcomp.filter;

import org.parallelme.userlibrary.Array;
import org.parallelme.userlibrary.datatype.Int32;
import org.parallelme.userlibrary.function.Filter;

import android.support.v8.renderscript.*;

public class Controller {
	private ControllerWrapper PM_parallelME;

	public Controller(RenderScript PM_mRS) {
		//this.PM_parallelME = new ControllerWrapperImplPM();
		//if (!this.PM_parallelME.isValid())
		this.PM_parallelME = new ControllerWrapperImplRS(PM_mRS);
	}


	public void method(final int sizeIn) {
		final int[] vectorIn = new int[sizeIn];

		for (int x = 0; x < sizeIn; x++) {
			vectorIn[x] = x+1;
		}
		PM_parallelME.inputBind1(vectorIn);
		PM_parallelME.filter1(sizeIn);

	}
}
