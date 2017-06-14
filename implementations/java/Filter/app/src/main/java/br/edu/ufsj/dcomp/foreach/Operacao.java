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
        Integer smaller = this.tmp[0];
        Integer count = 0;
        for (Integer element : this.tmp) {
            if(smaller > element){
                smaller = element;
                count = 1;
            }else if(smaller == element){
                count++;
            }
        }

        Integer[] returnVector = new Integer[this.tmp.length-count];
        count = 0;
        for (Integer element : this.tmp) {
            if (element != smaller) {
                returnVector[count] = element;
                count++;
            }
        }
        return returnVector;
    }
}
