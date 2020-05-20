package psccomparer.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @version 1.0
 * @author caemci
 * @see https://github.com/caemci
 */
public class Carpeta {

    private ArrayList<ArchivoPSC> archivos;
    private String nombre;

    public Carpeta(String path) throws IOException {
        this.archivos = new ArrayList<>();
        cargarCarpeta(path);
    }

    public String getNombre() {
        return nombre;
    }

    private void cargarCarpeta(String path) throws IOException {
        File folder = new File(path);
        this.nombre = folder.getName();
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            cargarArchivos(files);
        }
    }

    private void cargarArchivos(File[] files) throws IOException {
        for (File file : files) {
            if (!file.isDirectory()) {
                LectorTexto lt = null;
                try {
                    lt = new LectorTexto(file.getAbsolutePath());
                    this.archivos.add(new ArchivoPSC(file.getName(), lt.leerLineas()));
                } catch (FileNotFoundException ex) {
                    throw new FileNotFoundException("Archivo no encontrado: " + file.getName());
                } catch (IOException ex) {
                    throw new IOException("Error al leer líneas en: " + file.getName());
                } finally {
                    try {
                        lt.cerrarArchivo();
                    } catch (Exception e) {
                        throw new IOException("Error al cerrar el archivo " + file.getName() + ": " + e.getMessage());
                    }
                }
            }
        }
    }

    public ArrayList<ArchivoPSC> getArchivos() {
        return archivos;
    }
}
