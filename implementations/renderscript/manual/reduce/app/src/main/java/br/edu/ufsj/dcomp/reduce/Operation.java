package br.edu.ufsj.dcomp.reduce;


import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.Type;




/**
 * Created by labpi on 05/06/17.
 */

public class Operation {
    private RenderScript rs;
    private ScriptC_operation operation;
    private Allocation input;
    private Allocation output;

    Operation(RenderScript rs){
        this.rs = rs;
        this.operation = new ScriptC_operation(rs);
    }

    public void call(int size){
        int i = 0;
        int[] tmp = new int[size];
        //Random random = new Random();
        for (int x = 0; x < tmp.length; x++) {
            tmp[x] = ++i;
        }
        input = Allocation.createSized(rs, Element.I32(rs), tmp.length);
        input.copyFrom(tmp);
        int menorSize = (int)Math.floor(Math.sqrt(input.getType().getX()));
        Type outputType = new Type.Builder(rs, Element.I32(rs))
                .setX(1)
                .create();
        Allocation resultAlloc = Allocation.createTyped(rs, outputType);
        Type PM_gTileReduce1Type = new Type.Builder(rs, Element.I32(rs))
                .setX(menorSize)
                .create();
        Allocation menorAlloc = Allocation.createTyped(rs, PM_gTileReduce1Type);
        operation.set_output(resultAlloc);
        operation.set_input(input);
        operation.set_menor(menorAlloc);
        operation.forEach_reduce1_tile(menorAlloc);
        operation.invoke_reduce1();
        int[] resultado = new int[1];
        resultAlloc.copyTo(resultado);
        rs.finish();

    }




}
