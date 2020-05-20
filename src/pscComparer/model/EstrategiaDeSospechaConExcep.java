package psccomparer.model;

/**
 * @version 1.0
 * @author caemci
 * @see https://github.com/caemci
 */
public class EstrategiaDeSospechaConExcep extends EstrategiaDeSospecha {

    private String[] funcionesExceptuadas;

    public EstrategiaDeSospechaConExcep(String[] funcionesExceptuadas) {
        this.funcionesExceptuadas = funcionesExceptuadas;
    }

    @Override
    public double porcSospecha(Funcion f1, Funcion f2) {
        double porcSemejanza = 0;
        boolean esFuncExceptuada = Util.hayItemDeListaEnCadena(f1.getHeader(), this.funcionesExceptuadas);
        if (!esFuncExceptuada) {
            porcSemejanza = super.porcSospecha(f1, f2);
        }
        return porcSemejanza;
    }
}
