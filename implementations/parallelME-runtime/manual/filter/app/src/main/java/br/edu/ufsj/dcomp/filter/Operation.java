package br.edu.ufsj.dcomp.filter;

/**
 * Created by labpi on 05/06/17.
 */

public class Operation {

    private long dataPointer;
    private native long nativeInit(int size);
    private native void process(long dataPointerLong);

    Operation(int testSize){
        dataPointer = nativeInit(testSize);
    }

    public void callProcess(){
        process(dataPointer);
    }



    static{
        System.loadLibrary("Operation");
    }
}
