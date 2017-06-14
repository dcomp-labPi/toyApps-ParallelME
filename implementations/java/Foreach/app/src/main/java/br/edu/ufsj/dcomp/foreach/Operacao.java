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

    public void method() {
        for (Integer element : this.tmp) {
            element = element * element;
        }
    }
}
