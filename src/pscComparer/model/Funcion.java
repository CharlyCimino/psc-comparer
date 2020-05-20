package psccomparer.model;

import java.util.ArrayList;

/**
 * @version 1.0
 * @author caemci
 * @see https://github.com/caemci
 */
public class Funcion {

    private ArrayList<String> sentencias;

    public Funcion() {
        this.sentencias = new ArrayList<>();
    }

    public String getHeader() {
        return this.sentencias.get(0);
    }

    public String getEnd() {
        return this.sentencias.get(this.sentencias.size() - 1);
    }

    public void addSentencia(String st) {
        this.sentencias.add(st);
    }

    @Override
    public String toString() {
        return getHeader() + " <" + (this.sentencias.size() - 2) + " sentencias> " + getEnd();
    }

    public ArrayList<String> getSentencias() {
        return sentencias;
    }

}
