package psccomparer.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * @version 1.0
 * @author caemci
 * @see https://github.com/caemci
 */
public abstract class JFrameTemplate extends JFrame {

    private Component parent;
    private JPanel jPanelContenido;
    private JPanel jPanelFooter;
    private static final int PADDING = 5;

    public JFrameTemplate(String titulo, Component parent) {
        super(titulo); // Invoca al constructor de la superclase con el título de la ventana
        this.parent = parent;
        this.jPanelContenido = new JPanel();
        this.jPanelFooter = new JPanelFooter();
        this.cargar();
        setLookAndFeel();
    }

    public JFrameTemplate(String titulo) {
        this(titulo, null);
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarCartelDeError(String mensaje) {
        JOptionPane.showMessageDialog(this.parent, mensaje, "Error", 0);
    }

    public int mostrarCartelDeConfirmacionVaciarAgenda(String mensaje) {
        return JOptionPane.showConfirmDialog(this.parent, mensaje, "Vaciar la agenda", JOptionPane.YES_NO_OPTION);
    }

    private void cargar() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // La aplicación por defecto se cierra al tocar la cruz
        this.cargarImagenes(); // Personaliza el ícono de la ventana
        this.setResizable(false); // Impide que la ventana cambie de dimensiones
        this.establecerLayout();
        this.setLocationRelativeTo(this.parent);
    }

    private void establecerLayout() {
        super.getContentPane().setLayout(new BorderLayout()); // Establece la disposición de la ventana
        super.getContentPane().add(jPanelContenido, BorderLayout.CENTER); // El contenido se inserta en el medio
        super.getContentPane().add(jPanelFooter, BorderLayout.PAGE_END); // El footer (pie) se inserta debajo
        this.jPanelContenido.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        this.pack();
    }

    private void cargarImagenes() {
        this.setIconImage(getImagen("psccomparer/img/caemci-logo-square.png")); // Establece el ícono de la ventana
    }

    private Image getImagen(String ruta) {
        Image img = null;
        try {
            img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/" + ruta));
        } catch (Exception e) {
            System.out.println("No se pudo cargar recurso " + ruta + ": " + e.getMessage());
        }
        return img;
    }

    @Override
    public final JComponent getContentPane() {
        return jPanelContenido;
    }

//    protected abstract void agregarContenido();
//
//    protected abstract void instanciarComponentes();
//
//    protected abstract void setearComponentes();
    private class JPanelFooter extends JPanel {

        private JLabel jLabelAutor;
        private JLabel jLabelLink;

        public JPanelFooter() {
            jLabelAutor = new JLabel();
            jLabelLink = new JLabel();
            prepararAutor();
            prepararLink();
            establecerLayout();
            setearComponentes();
        }

        private void establecerLayout() {
            this.setLayout(new BorderLayout());
            this.add(jLabelAutor, BorderLayout.WEST);
            this.add(jLabelLink, BorderLayout.EAST);
            this.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        }

        private void prepararAutor() {
            jLabelAutor.setIcon(new ImageIcon(getImagen("psccomparer/img/caemci-logo-wide.png"))); // Establece el ícono de la ventana);
            jLabelAutor.setText("Realizado por ");
            jLabelAutor.setHorizontalTextPosition(SwingConstants.LEFT);
            jLabelAutor.setVerticalTextPosition(SwingConstants.CENTER);
        }

        private void prepararLink() {
            jLabelLink.setText("github.com/caemci");
        }

        private void setearComponentes() {
            for (Component comp : this.getComponents()) {
                JLabel jl = (JLabel) comp;
                jl.setAlignmentX(Component.CENTER_ALIGNMENT);
                jl.setAlignmentY(Component.CENTER_ALIGNMENT);
                jl.setFont(new Font("Lato", 0, 19));
            }
        }

    }
}
