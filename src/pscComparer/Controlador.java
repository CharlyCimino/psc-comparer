package psccomparer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import psccomparer.model.PSCComparer;
import psccomparer.view.JFramePrincipal;

/**
 * @version 1.0
 * @author caemci
 * @see https://github.com/caemci
 */
public class Controlador {

    private JFramePrincipal vista;
    private PSCComparer comparador;

    public Controlador(JFramePrincipal vista, PSCComparer comparador) {
        this.vista = vista;
        this.comparador = comparador;
    }

    public void iniciar() {
        this.vista.addCargarHandler(new CargarHandler());
        this.vista.addComprobarHandler(new ComprobarHandler());
        this.vista.setVisible(true);
    }

    private void cargar() {
        try {
            this.comparador.cargar(this.vista.getRuta());
            this.vista.mostrarMensajeInfo(this.comparador.cantArchivos() + " archivos serán analizados...");
        } catch (IOException ex) {
            this.vista.mostrarMensajeError("Error de E/S\n" + ex.getMessage());
        } catch (Exception ex) {
            this.vista.mostrarMensajeError("Error\n" + ex.getMessage());
        }
    }

    private void comprobar() {
        try {
            setConfigDeComparador();
            String ruta = this.comparador.comprobar();
            this.vista.mostrarMensajeInfo("Resultados generados con éxito en " + ruta);
        } catch (IOException ex) {
            this.vista.mostrarMensajeError("Error de E/S\n" + ex.getMessage());
        } catch (Exception ex) {
            this.vista.mostrarMensajeError("Error\n" + ex.getMessage());
        }
    }

    private void setConfigDeComparador() {
        String fExceptuadasStr = this.vista.getFuncionesExceptuadas();
        double porcSemejanza = this.vista.getPorcentajeMinSemejanza();
        this.comparador.setConfig(fExceptuadasStr, porcSemejanza);
    }

    private class CargarHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            cargar();
        }
    }

    private class ComprobarHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            comprobar();
        }
    }
}
