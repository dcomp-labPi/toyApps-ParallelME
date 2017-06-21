package br.edu.ufsj.dcomp.reduce;

/**
 * Created by labpi on 05/06/17.
 */

public class Operation {

    private long dataPointer;
    private native long nativeInit();
    private native void process(long dataPointerLong,int size,long dataPointerLong2);

    Operation(){
        dataPointer = nativeInit();
    }

    public void callProcess(int testSize){
        process(dataPointer,testSize,dataPointer);
    }



    static{
        System.loadLibrary("Operation");
    }
}
