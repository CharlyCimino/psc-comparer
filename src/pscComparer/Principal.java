package psccomparer;

import psccomparer.model.PSCComparer;
import psccomparer.view.JFramePrincipal;

/**
 * @version 1.0
 * @author caemci
 * @see https://github.com/caemci
 */
public class Principal {

    public static void main(String[] args) {
        PSCComparer pscComparer = new PSCComparer();
        JFramePrincipal vista = new JFramePrincipal("PSC Comparer v1.0");
        Controlador c = new Controlador(vista, pscComparer);
        c.iniciar();
    }

}
