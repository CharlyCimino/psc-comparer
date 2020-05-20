package psccomparer.model;

/**
 * @version 1.0
 * @author caemci
 * @see https://github.com/caemci
 */
public class SospechadorDeFunciones {

    private double porcMin;
    private EstrategiaDeSospecha estrategia;

    public SospechadorDeFunciones(String funcionesExceptuadasStr, double porcMin) {
        this.porcMin = porcMin;
        this.estrategia = generarEstrategia(funcionesExceptuadasStr);
    }

    private EstrategiaDeSospecha generarEstrategia(String funcionesExceptuadasStr) {
        if (funcionesExceptuadasStr.isEmpty()) {
            return new EstrategiaDeSospecha();
        } else {
            String[] fExcepts = Util.cadComasToArray(funcionesExceptuadasStr);
            return new EstrategiaDeSospechaConExcep(fExcepts);
        }
    }

    public double sospechar(Funcion f1, Funcion f2) {
        double porc = estrategia.porcSospecha(f1, f2);
        return (porc > this.porcMin) ? porc : -1;
    }

}
