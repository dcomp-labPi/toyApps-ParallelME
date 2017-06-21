package br.edu.ufsj.dcomp.filter;



public class Controller{
	private ControllerWrapper PM_parallelME;

	public Controller() {
		this.PM_parallelME = new ControllerWrapperImplPM();
	}

	private int varTeste;

	public void method(final int sizeIn) {
		final int[] vectorIn = new int[sizeIn];
		

		for (int x = 0; x < sizeIn; x++) {
			vectorIn[x] = x+1;
		}
		PM_parallelME.inputBind1(vectorIn);
		PM_parallelME.filter1(sizeIn);

	}
}
