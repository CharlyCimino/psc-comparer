
package pscComparer;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Vista {
    public String getRuta() throws IllegalStateException {
        String ruta = ""; // Ruta inicial vacía
        JFileChooser explorador = new JFileChooser();
        explorador.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int resultado = explorador.showOpenDialog(null);
        if (resultado == JFileChooser.CANCEL_OPTION) {
            throw new IllegalStateException("No se seleccionó ningún archivo");
        }
        return explorador.getSelectedFile().getAbsolutePath();
    }
    
    public void mostrarMensajeError(String msj) {
        JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void mostrarMensajeInfo(String msj) {
        JOptionPane.showMessageDialog(null, msj, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public String obtenerCadena (String msj) {
        String cad = JOptionPane.showInputDialog(msj);
        return (cad == null ? "" : cad);
    }
}
