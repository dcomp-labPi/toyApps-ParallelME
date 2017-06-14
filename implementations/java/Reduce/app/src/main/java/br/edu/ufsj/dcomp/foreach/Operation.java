package br.edu.ufsj.dcomp.foreach;

/**
 * Created by millas on 14/06/17.
 */

public class Operation {
    Integer[] tmp;
    public Operation(int size){
        this.tmp = new Integer[size];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = i + 1;
        }
    }

    public Integer method() {
        Integer bigger = this.tmp[0];
        for (Integer element : this.tmp) {
            if(element > bigger){
                bigger = element;
            }
        }
        return bigger;
    }
}
