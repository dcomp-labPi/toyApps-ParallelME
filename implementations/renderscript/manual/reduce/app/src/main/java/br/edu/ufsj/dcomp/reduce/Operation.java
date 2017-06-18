package br.edu.ufsj.dcomp.reduce;


import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.util.Log;

import java.util.Random;

/**
 * Created by labpi on 05/06/17.
 */

public class Operation {
    private RenderScript rs;
    private ScriptC_operation operation;
    private Allocation input;

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

        operation.forEach_foreach(input,input);

        input.copyTo(tmp);
        rs.finish();

    }




}
