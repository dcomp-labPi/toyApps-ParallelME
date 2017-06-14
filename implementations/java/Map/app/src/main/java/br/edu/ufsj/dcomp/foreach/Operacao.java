package br.edu.ufsj.dcomp.foreach;

/**
 * Created by millas on 14/06/17.
 */

public class Operacao {
    Integer[] tmp;
    public Operacao(int size){
        this.tmp = new Integer[size];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = i + 1;
        }
    }

    public Integer[] method() {
        int i = 0;
        Integer[] returnVector = new Integer[this.tmp.length];
        for (Integer element : this.tmp) {
            returnVector[i] = element * element;
            i++;
        }
        return returnVector;
    }
}
