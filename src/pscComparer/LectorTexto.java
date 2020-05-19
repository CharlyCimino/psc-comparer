package pscComparer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LectorTexto {

    private FileReader fr;
    private BufferedReader br;

    /**
     * Construye un lector de archivos de texto.
     *
     * @param rutaAbsoluta La ruta absoluta hacia el archivo de texto.
     * @throws FileNotFoundException Si no se encuentra el archivo.
     */
    public LectorTexto(String rutaAbsoluta) throws FileNotFoundException {
        this.fr = new FileReader(new File(rutaAbsoluta));
        this.br = new BufferedReader(this.fr);
    }

    /**
     * Retorna Un ArrayList de cadenas, obtenidas del archivo de texto, a razón
     * de una cadena por cada salto de línea.
     *
     * @return Un ArrayList de cadenas, obtenidas del archivo de texto.
     * @throws IOException Si ocurre un error en el proceso de lectura.
     */
    public ArrayList<String> leerLineas() throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        String cadena = leerLinea();
        while (cadena != null) {
            lines.add(cadena);
            cadena = leerLinea();
        }
        return lines;
    }

    private String leerLinea() throws IOException {
        return this.br.readLine();
    }

    public void cerrarArchivo() throws IOException {
        this.fr.close();
    }
}
