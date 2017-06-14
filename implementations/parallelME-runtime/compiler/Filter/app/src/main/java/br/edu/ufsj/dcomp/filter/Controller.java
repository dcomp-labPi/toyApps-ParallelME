package br.edu.ufsj.dcomp.filter;

import java.util.Random;

public class Controller {
	private ControllerWrapper PM_parallelME;

	public Controller() {
		this.PM_parallelME = new ControllerWrapperImplPM();

	}

	private int varTeste;

	public void method(final int sizeIn) {
		final int[] vectorIn = new int[sizeIn];
		
		Random random = new Random();
		for (int x = 0; x < sizeIn; x++) {
			vectorIn[x] = random.nextInt(sizeIn);
		}
		PM_parallelME.inputBind1(vectorIn);
		PM_parallelME.filter1(sizeIn, vectorIn);

	}
}
