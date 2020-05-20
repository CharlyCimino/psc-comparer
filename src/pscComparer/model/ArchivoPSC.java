package psccomparer.model;

import java.util.ArrayList;

/**
 * @version 1.0
 * @author caemci
 * @see https://github.com/caemci
 */
public class ArchivoPSC {

    private String nombre;
    private ArrayList<String> lineas;
    private ArrayList<Funcion> funciones;

    public ArchivoPSC(String nombre, ArrayList<String> lineas) {
        this.nombre = nombre;
        this.lineas = lineas;
        normalizar(this.lineas);
        this.funciones = generarFunciones();
    }

    private void normalizar(ArrayList<String> lineas) {
        ArrayList<String> copia = new ArrayList<>();
        for (String linea : lineas) {
            linea = linea.replaceAll("\t", "");
            linea = linea.trim();
            if (!linea.isEmpty()) {
                copia.add(linea);
            }
        }
        this.lineas = copia;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Funcion> getFunciones() {
        return funciones;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ArchivoPSC other = (ArchivoPSC) obj;
        return sonIguales(this.lineas, other.lineas);
    }

    public boolean sonIguales(ArrayList<String> al1, ArrayList<String> al2) {
        for (int i = 0; i < al1.size(); i++) {
            if (i < al2.size() && !al1.get(i).equalsIgnoreCase(al2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private ArrayList<Funcion> generarFunciones() {
        ArrayList<Funcion> funs = new ArrayList<>();
        for (int i = 0; i < this.lineas.size(); i++) {
            if (empezoFuncion(this.lineas.get(i))) {
                Funcion f = new Funcion();
                while (i < this.lineas.size() && !terminoFuncion(this.lineas.get(i))) {
                    f.addSentencia(this.lineas.get(i));
                    i++;
                }
                if (i < this.lineas.size()) {
                    f.addSentencia(this.lineas.get(i));
                }
                funs.add(f);
            }
        }
        return funs;
    }

    private boolean empezoFuncion(String cadena) {
        cadena = cadena.toUpperCase();
        return cadena.contains("ALGORITMO") || cadena.contains("FUNCION") || cadena.contains("PROCESO");
    }

    private boolean terminoFuncion(String cadena) {
        cadena = cadena.toUpperCase();
        return cadena.contains("FINALGORITMO") || cadena.contains("FINFUNCION") || cadena.contains("FINPROCESO")
                || cadena.contains("FIN ALGORITMO") || cadena.contains("FIN FUNCION") || cadena.contains("FIN PROCESO");
    }
}
