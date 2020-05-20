package psccomparer.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @version 1.0
 * @author caemci
 * @see https://github.com/caemci
 */
public class JFramePrincipal extends JFrameTemplate {

    public JFramePrincipal(String titulo) {
        super(titulo);
        initComponents();
        setEventos();
    }

    public String getRuta() throws IllegalStateException {
        JFileChooser explorador = new JFileChooser();
        explorador.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int resultado = explorador.showOpenDialog(null);
        if (resultado == JFileChooser.CANCEL_OPTION) {
            throw new IllegalStateException("No se eligió ninguna carpeta");
        }
        String ruta = explorador.getSelectedFile().getAbsolutePath();
        this.JTextFieldRuta.setText(ruta);
        return ruta;
    }

    public void mostrarMensajeError(String msj) {
        JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarMensajeInfo(String msj) {
        JOptionPane.showMessageDialog(this, msj, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public String getFuncionesExceptuadas() {
        String cad = "";
        if (this.jCheckBoxFuncionesExceptuadas.isSelected()) {
            cad = this.jTextFieldFuncionesExceptuadas.getText();
        }
        return cad;
    }

    public void addCargarHandler(ActionListener al) {
        this.jButtonCargarCarpeta.addActionListener(al);
    }

    public void addComprobarHandler(ActionListener al) {
        this.jButtonComprobar.addActionListener(al);
    }

    private void setEventos() {
        this.jCheckBoxFuncionesExceptuadas.addItemListener(new CheckBoxHandler());
        this.jSliderPorcentaje.addChangeListener(new SliderHandler());
    }

    public double getPorcentajeMinSemejanza() {
        return this.jSliderPorcentaje.getValue();
    }

    public void activarTextFieldFuncionesExceptuadas(boolean flag) {
        this.jTextFieldFuncionesExceptuadas.setEditable(flag);
        this.jTextFieldFuncionesExceptuadas.setEnabled(flag);
    }

    private class SliderHandler implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            jLabelPorcSlider.setText(jSliderPorcentaje.getValue() + "%");
        }

    }

    private class CheckBoxHandler implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            activarTextFieldFuncionesExceptuadas(e.getStateChange() == ItemEvent.SELECTED);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonCargarCarpeta = new javax.swing.JButton();
        jSliderPorcentaje = new javax.swing.JSlider();
        JTextFieldRuta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldFuncionesExceptuadas = new javax.swing.JTextField();
        jCheckBoxFuncionesExceptuadas = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButtonComprobar = new javax.swing.JButton();
        jLabelPorcSlider = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonCargarCarpeta.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jButtonCargarCarpeta.setText("Elegir carpeta...");
        jButtonCargarCarpeta.setActionCommand("Seleccionar carpeta...");

        jSliderPorcentaje.setPaintLabels(true);
        jSliderPorcentaje.setPaintTicks(true);
        jSliderPorcentaje.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        JTextFieldRuta.setEditable(false);
        JTextFieldRuta.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel2.setText("100%");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel3.setText("0%");

        jTextFieldFuncionesExceptuadas.setEditable(false);
        jTextFieldFuncionesExceptuadas.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextFieldFuncionesExceptuadas.setEnabled(false);
        jTextFieldFuncionesExceptuadas.setMinimumSize(new java.awt.Dimension(6, 23));
        jTextFieldFuncionesExceptuadas.setPreferredSize(new java.awt.Dimension(6, 23));

        jCheckBoxFuncionesExceptuadas.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jCheckBoxFuncionesExceptuadas.setText("Exceptuar funciones");

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        jLabel4.setText("Si deseás exceptuar de la búsqueda alguna/s función/es,");

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Seleccioná el porcentaje mínimo de semejanza entre funciones para lanzar una sospecha");

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        jLabel1.setText("colocá su/s nombre/s aquí, separados por comas, sin espacios.");

        jButtonComprobar.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jButtonComprobar.setText("Comprobar");

        jLabelPorcSlider.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabelPorcSlider.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPorcSlider.setText("50%");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPorcSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JTextFieldRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCargarCarpeta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSliderPorcentaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldFuncionesExceptuadas, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBoxFuncionesExceptuadas))
                            .addComponent(jLabel4)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonComprobar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTextFieldRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCargarCarpeta))
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addGap(3, 3, 3)
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldFuncionesExceptuadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxFuncionesExceptuadas))
                .addGap(15, 15, 15)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPorcSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSliderPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jButtonComprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JTextFieldRuta;
    private javax.swing.JButton jButtonCargarCarpeta;
    private javax.swing.JButton jButtonComprobar;
    private javax.swing.JCheckBox jCheckBoxFuncionesExceptuadas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelPorcSlider;
    private javax.swing.JSlider jSliderPorcentaje;
    private javax.swing.JTextField jTextFieldFuncionesExceptuadas;
    // End of variables declaration//GEN-END:variables
}
