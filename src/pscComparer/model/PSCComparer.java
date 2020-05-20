package psccomparer.model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @version 1.0
 * @author caemci
 * @see https://github.com/caemci
 */
public class PSCComparer {

    private ArrayList<String> resultados = new ArrayList<>();
    private Carpeta carpeta;
    private SospechadorDeFunciones sospechadorF;

    public void setConfig(String fExceptuadasStr, double porcMinSemejanza) {
        this.sospechadorF = new SospechadorDeFunciones(fExceptuadasStr, porcMinSemejanza);
    }

    public void cargar(String ruta) throws IOException {
        this.carpeta = new Carpeta(ruta);
    }

    public String comprobar() throws IOException {
        if (this.carpeta == null) {
            throw new IllegalStateException("No se eligió ninguna carpeta");
        }
        this.resultados.clear();
        chequearCopiaExacta();
        chequearFunciones();
        return EscritorTexto.escribir(this.carpeta.getNombre() + "-RESULTADOS", this.resultados);
    }

    public int cantArchivos() {
        return this.carpeta != null ? this.carpeta.getArchivos().size() : 0;
    }

    private void chequearCopiaExacta() {
        resultados.add("REPORTE DE COPIAS EXACTAS ENTRE ARCHIVOS\n\n");
        ArrayList<ArchivoPSC> archivos = this.carpeta.getArchivos();
        for (int i = 0; i < archivos.size() - 1; i++) {
            for (int j = i + 1; j < archivos.size(); j++) {
                if (archivos.get(i).equals(archivos.get(j))) {
                    resultados.add("¡DUPLICADOS!" + "\n");
                    resultados.add(archivos.get(i).getNombre() + "\n");
                    resultados.add(archivos.get(j).getNombre() + "\n\n");
                    archivos.remove(j);
                }
            }
        }
        if (resultados.size() <= 1) {
            resultados.add("No hubo coincidencias.\n");
        }
    }

    private void chequearFunciones() {
        this.resultados.add("\nREPORTE DE FUNCIONES SOSPECHOSAS\n\n");
        int size = resultados.size();
        ArrayList<ArchivoPSC> archivos = this.carpeta.getArchivos();
        for (int i = 0; i < archivos.size() - 1; i++) {
            for (int j = i + 1; j < archivos.size(); j++) {
                chequearFuncionesDuplicadas(archivos.get(i), archivos.get(j));
            }
        }
        if (resultados.size() == size) {
            resultados.add("No hubo sospechas.");
        }
    }

    private void chequearFuncionesDuplicadas(ArchivoPSC archivoPSC1, ArchivoPSC archivoPSC2) {
        ArrayList<Funcion> funciones1 = archivoPSC1.getFunciones();
        ArrayList<Funcion> funciones2 = archivoPSC2.getFunciones();
        for (int i = 0; i < funciones1.size(); i++) {
            for (int j = 0; j < funciones2.size(); j++) {
                double porc = this.sospechadorF.sospechar(funciones1.get(i), funciones2.get(j));
                if (porc != -1) {
                    this.resultados.add("¡SOSPECHA!\n");
                    this.resultados.add(archivoPSC1.getNombre() + "\n");
                    this.resultados.add(funciones1.get(i) + "\n");
                    this.resultados.add("... coincide un " + porc + "% con...\n");
                    this.resultados.add(archivoPSC2.getNombre() + "\n");
                    this.resultados.add(funciones2.get(j) + "\n\n");
                }
            }
        }
    }
}
