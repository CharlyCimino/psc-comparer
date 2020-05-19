package pscComparer;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author caemci
 */
public class PSCComparer {

    private ArrayList<String> resultados = new ArrayList<>();
    private Vista v;
    private Carpeta carpeta;

    public PSCComparer() {
        this.v = new Vista();
    }

    public void iniciar() {
        try {
            //String ruta = v.getRuta();
            this.carpeta = new Carpeta("D:\\Users\\caemci\\Desktop\\Prueba-PSC-Comparer");
            v.mostrarMensajeInfo(this.carpeta.getArchivos().size() + " archivos serán analizados...");
            String fExceptuadas = v.obtenerCadena("¿Deseás exceptuar de la búsqueda alguna/s función/es?\n"
                            + "Colocá sus nombres aquí, separados por comas (sin espacios).\n"
                            + "Si no deseás exceptuar nada, dejá el campo en blanco");
            chequearCopiaExacta();
            String[] funcionesExceptuadas = Util.cadComasToArray(fExceptuadas);
            chequearFunciones(funcionesExceptuadas);
            String path = EscritorTexto.escribir(this.carpeta.getNombre() + "-RESULTADOS", this.resultados);
            v.mostrarMensajeInfo("Resultados generados con éxito en " + path);
            Util.mostrarLista(resultados);
        } catch (IOException ex) {
            v.mostrarMensajeError("Error de E/S: " + ex.getMessage());
        } catch (Exception ex) {
            v.mostrarMensajeError("Error: " + ex.getMessage());
        }
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
                }
            }
        }
        if (resultados.size() <= 1) {
            resultados.add("No hubo coincidencias.\n");
        }
    }

    private void chequearFunciones(String[] fExceptuadas) {
        resultados.add("\nREPORTE DE FUNCIONES SOSPECHOSAS\n\n");
        int size = resultados.size();
        ArrayList<ArchivoPSC> archivos = this.carpeta.getArchivos();
        for (int i = 0; i < archivos.size() - 1; i++) {
            for (int j = i + 1; j < archivos.size(); j++) {
                chequearFuncionesDuplicadas(fExceptuadas, archivos.get(i), archivos.get(j));
            }
        }
        if (resultados.size() == size) {
            resultados.add("No hubo sospechas.");
        }
    }

    private void chequearFuncionesDuplicadas(String[] fExceptuadas, ArchivoPSC archivoPSC1, ArchivoPSC archivoPSC2) {
        double porcSemejanza = 0;
        ArrayList<Funcion> funciones1 = archivoPSC1.getFunciones();
        ArrayList<Funcion> funciones2 = archivoPSC2.getFunciones();
        for (int i = 0; i < funciones1.size(); i++) {
            for (int j = 0; j < funciones2.size(); j++) {
                if (fExceptuadas != null) {
                    porcSemejanza = porcSospecha(fExceptuadas, funciones1.get(i), funciones2.get(j));
                } else {
                    porcSemejanza = porcSospecha(funciones1.get(i), funciones2.get(j));
                }
                if (porcSemejanza > 0.3) {
                    resultados.add("SOSPECHA!\n");
                    resultados.add(archivoPSC1.getNombre() + "\n");
                    resultados.add(funciones1.get(i) + "\n");
                    resultados.add("... coincide un " + porcSemejanza*100 + "% con...\n");
                    resultados.add(archivoPSC2.getNombre() + "\n");
                    resultados.add(funciones2.get(j) + "\n\n");
                }
            }
        }
    }

    private double porcSospecha(String[] fExceptuadas, Funcion f1, Funcion f2) {
        double porcSemejanza = 0;
        boolean esFuncExceptuada = Util.hayItemDeListaEnCadena(f1.getHeader(), fExceptuadas);
        if (!esFuncExceptuada) {
            porcSemejanza = porcSospecha(f1, f2);
        }
        return porcSemejanza;
    }

    private double porcSospecha(Funcion f1, Funcion f2) {
        ArrayList<String> sentencias1 = f1.getSentencias();
        ArrayList<String> sentencias2 = f2.getSentencias();
        int contLineasIguales = 0;
        for (int i = 1; i < sentencias1.size() - 1; i++) {
            if (i < sentencias2.size() && Util.existeStringEnLista(sentencias1.get(i), sentencias2)) {
                contLineasIguales++;
            }
        }
        return contLineasIguales * 1.0 / (sentencias1.size()-2);
    }
}
