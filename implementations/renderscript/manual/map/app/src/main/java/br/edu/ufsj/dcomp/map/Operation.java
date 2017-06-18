package br.edu.ufsj.dcomp.map;


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

        int[] tmp2 = new int[size];

        input = Allocation.createSized(rs, Element.I32(rs), tmp.length);
        input.copyFrom(tmp);
        /*Type type = new Type.Builder(rs, Element.I32(rs))
                .setX(output.getType().getX())
                .create();*/
        output = Allocation.createSized(rs,Element.I32(rs), tmp2.length);

        operation.forEach_map(input,output);

        output.copyTo(tmp2);
        rs.finish();

    }




}
