
package pscComparer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EscritorTexto {

    private static BufferedWriter writer;

    public static String escribir(String nombreArchivo, ArrayList<String> datos) throws IOException {
        String ruta = "";
        try {
            File logFile = new File(nombreArchivo + ".txt");
            logFile.createNewFile();
            writer = new BufferedWriter(new FileWriter(logFile));
            for (String dato : datos) {
                writer.write(dato);
            }
            ruta = logFile.getAbsolutePath();
        } catch (IOException ex) {
            throw new IOException("Error al escribir archivo: " + nombreArchivo);
        }
        finally {
            writer.close();
        }
        return ruta;
    }
}
