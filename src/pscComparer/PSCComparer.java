
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
            String ruta = v.getRuta();
            this.carpeta = new Carpeta(ruta);
            v.mostrarMensajeInfo(this.carpeta.getArchivos().size() + " archivos serán analizados...");
            String fExceptuada = v.obtenerCadena("¿Deseás exceptuar de la búsqueda alguna función?\n"
                    + "Colocá el nombre aquí. Si no deseás exceptuar nada, dejalo en blanco");
            chequearCopiaExacta();
            chequearFunciones(fExceptuada);
            String path = EscritorTexto.escribir(this.carpeta.getNombre() + "-RESULTADOS", this.resultados);
            v.mostrarMensajeInfo("Resultados generados con éxito en " + path);
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
                    resultados.add("COINCIDENCIA!" + "\n");
                    resultados.add(archivos.get(i).getNombre() + "\n");
                    resultados.add(archivos.get(j).getNombre() + "\n\n");
                }
            }
        }
        if (resultados.size() <= 1) {
            resultados.add("No hubo coincidencias.\n");
        }
    }
    
    private void chequearFunciones(String fExceptuada) {
        resultados.add("\nREPORTE DE FUNCIONES DUPLICADAS\n\n");
        int size = resultados.size();
        ArrayList<ArchivoPSC> archivos = this.carpeta.getArchivos();
        for (int i = 0; i < archivos.size() - 1; i++) {
            for (int j = i + 1; j < archivos.size(); j++) {
                chequearFuncionesDuplicadas(fExceptuada, archivos.get(i), archivos.get(j));
            }
        }
        if (resultados.size() == size) {
            resultados.add("No hubo coincidencias.");
        }
    }
    
    private void chequearFuncionesDuplicadas(String fExceptuada, ArchivoPSC archivoPSC1, ArchivoPSC archivoPSC2) {
        ArrayList<Funcion> funciones1 = archivoPSC1.getFunciones();
        ArrayList<Funcion> funciones2 = archivoPSC2.getFunciones();
        for (int i = 0; i < funciones1.size() - 1; i++) {
            for (int j = 0; j < funciones2.size(); j++) {
                if (estaDuplicada(fExceptuada, funciones1.get(i), funciones2.get(j))) {
                    resultados.add("SOSPECHA!\n");
                    resultados.add(archivoPSC1.getNombre() + "\n");
                    resultados.add(funciones1.get(i).getHeader() + "\n");
                    resultados.add(archivoPSC2.getNombre() + "\n");
                    resultados.add(funciones2.get(j).getHeader() + "\n\n");
                }
            }
        }
    }

    private boolean estaDuplicada(String fExceptuada, Funcion f1, Funcion f2) {
        boolean sonIguales = analizarCopia(f1,f2);
        if (fExceptuada.isEmpty()) {
            return sonIguales;
        } else {
            return !f1.getHeader().contains(fExceptuada) && sonIguales;
        }
    }
    
    private boolean analizarCopia(Funcion f1, Funcion f2) {
        ArrayList<String> sentencias1 = f1.getSentencias();
        ArrayList<String> sentencias2 = f2.getSentencias();
        int contLineasIguales = 0;
        for (int i = 1; i < sentencias1.size() - 1; i++) {
            if (i < sentencias2.size() && sentencias1.get(i).equalsIgnoreCase(sentencias2.get(i))) {
                contLineasIguales++;
            }
        }
        double porcSemejanza = contLineasIguales * 1.0 / sentencias1.size();
        return porcSemejanza > 0.3;
    }

}