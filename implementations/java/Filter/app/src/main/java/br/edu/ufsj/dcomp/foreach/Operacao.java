package br.edu.ufsj.dcomp.foreach;

import java.util.Arrays;

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
        Integer smaller = this.tmp[0];
        Boolean[] save = new Boolean[tmp.length];
        Arrays.fill(save,false);
        int cont = 0;
        for (int i=0;i<tmp.length;i++) {
            if(tmp[i] > (tmp.length/2)){
                save[i] = true;
            }
        }

        Integer[] returnVector = new Integer[tmp.length-cont];
        cont = 0;
        for (int i=0;i<save.length;i++) {
            if (save[i]) {
                returnVector[cont] = tmp[i];
                cont++;
            }
        }
        return returnVector;
    }
}
