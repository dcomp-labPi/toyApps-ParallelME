package br.edu.ufsj.dcomp.filter;

import org.parallelme.userlibrary.Array;
import org.parallelme.userlibrary.datatype.Int32;
import org.parallelme.userlibrary.function.Filter;

import android.support.v8.renderscript.*;

public class Controller {
	private RenderScript rs;
    private ScriptC_Controller kernel;
    private Allocation input;
	int[] vectorIn;
    Allocation outputTile;
    Allocation outputSizeAlloc;
    Allocation resultado;
    public Controller(RenderScript PM_mRS,int sizeIn) {
		this.rs = PM_mRS;
        kernel = new ScriptC_Controller(rs);
		int[] vectorIn = new int[sizeIn];

		for (int x = 0; x < sizeIn; x++) {
			vectorIn[x] = x+1;
		}

		input = Allocation.createSized(PM_mRS, Element.I32(PM_mRS), vectorIn.length);
        input.copyFrom(vectorIn);
        Type PM_gOutputTileFilter1Type = new Type.Builder(PM_mRS, Element.I32(PM_mRS))
                .setX(input.getType().getX())
                .create();
        outputTile = Allocation.createTyped(PM_mRS, PM_gOutputTileFilter1Type);
        Type type = new Type.Builder(PM_mRS, Element.I32(PM_mRS))
                .setX(1)
                .create();
        outputSizeAlloc = Allocation.createTyped(PM_mRS, type);
        kernel.set_PM_gOutputTileFilter1(outputTile);
        kernel.set_PM_gOutputXSizeFilter1_Allocation(outputSizeAlloc);
        kernel.set_PM_gSizeInFilter1(sizeIn);
	}


	public void method() {
        kernel.forEach_filter1_tile(input, outputTile);
        kernel.invoke_filter1_setAllocationSize();
        int PM_size[] = new int[1];
        kernel.get_PM_gOutputXSizeFilter1_Allocation().copyTo(PM_size);
        if (PM_size[0] > 0) {
            Type PM_result10OutType = new Type.Builder(rs, Element.I32(rs))
                    .setX(PM_size[0])
                    .create();
            resultado = Allocation.createTyped(rs, PM_result10OutType);

            kernel.set_PM_gOutputFilter1(resultado);
            kernel.set_PM_gInputFilter1(input);
            kernel.invoke_filter1();

            rs.finish();
        }


	}
}
