package psccomparer.model;

import java.util.ArrayList;

/**
 * @version 1.0
 * @author caemci
 * @see https://github.com/caemci
 */
public class EstrategiaDeSospecha {

    public double porcSospecha(Funcion f1, Funcion f2) {
        ArrayList<String> sentencias1 = f1.getSentencias();
        ArrayList<String> sentencias2 = f2.getSentencias();
        int contLineasIguales = 0;
        for (int i = 1; i < sentencias1.size() - 1; i++) {
            if (i < sentencias2.size() && Util.existeStringEnLista(sentencias1.get(i), sentencias2)) {
                contLineasIguales++;
            }
        }
        return contLineasIguales * 1.0 / (sentencias1.size() - 2) * 100;
    }
}
