package gui;

import game.SumaTres;
import obj.Pieza;

/**
 *
 * @author JuanMier
 */
public class ModifyBoardDialog extends javax.swing.JDialog {
    
    private boolean pressedOk;
    private SumaTres s;
    private PiezaDisplayer displayer;
    private int mode;
    
    /**
     * Creates new form CoordenadasMatriz
     */
    private ModifyBoardDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        displayer = (PiezaDisplayer) pnlPieza;
    }
    
    public ModifyBoardDialog(SumaTres si) {
        this(null, true);
        this.s = si;
        sldHorizontal.setMaximum(si.getSettings().getY() - 1);
        sldVertical.setMaximum(si.getSettings().getX() - 1);
        updateValues();
        
    }
    
    public void updateValues() {
        for(Integer i : Pieza.COLORES.keySet()) {
            cmbValores.addItem(String.valueOf(i));
        }
        
        // se eliminan los valores -2, -1 y 0 del combobox.
        cmbValores.removeItem("-2");
        cmbValores.removeItem("-1");
        cmbValores.removeItem("0");
    }
    
    
    
    public boolean showDialog() {
        pressedOk = false;
        this.setVisible(true);
        update();
        return pressedOk;
    }
    
    public int getMode() {
        return mode;
    }
    
    public int getValue() {
        return Integer.parseInt((String) cmbValores.getSelectedItem());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpAcción = new javax.swing.ButtonGroup();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        sldVertical = new javax.swing.JSlider();
        sldHorizontal = new javax.swing.JSlider();
        txtX = new javax.swing.JLabel();
        txtY = new javax.swing.JLabel();
        lblInfo = new javax.swing.JLabel();
        pnlPieza = new PiezaDisplayer();
        btnAñadir = new javax.swing.JToggleButton();
        btnModificar = new javax.swing.JToggleButton();
        btnEliminar = new javax.swing.JToggleButton();
        cmbValores = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SumaTres - Input coordenadas");
        setAlwaysOnTop(true);
        setResizable(false);

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        sldVertical.setOrientation(javax.swing.JSlider.VERTICAL);
        sldVertical.setValue(0);
        sldVertical.setInverted(true);
        sldVertical.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldVerticalStateChanged(evt);
            }
        });

        sldHorizontal.setValue(0);
        sldHorizontal.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldHorizontalStateChanged(evt);
            }
        });

        txtX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtX.setText("0");

        txtY.setText("0");

        lblInfo.setText("Pieza:");

        javax.swing.GroupLayout pnlPiezaLayout = new javax.swing.GroupLayout(pnlPieza);
        pnlPieza.setLayout(pnlPiezaLayout);
        pnlPiezaLayout.setHorizontalGroup(
            pnlPiezaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pnlPiezaLayout.setVerticalGroup(
            pnlPiezaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        grpAcción.add(btnAñadir);
        btnAñadir.setMnemonic('a');
        btnAñadir.setText("Añadir");
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });

        grpAcción.add(btnModificar);
        btnModificar.setMnemonic('m');
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        grpAcción.add(btnEliminar);
        btnEliminar.setMnemonic('e');
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        cmbValores.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAceptar)
                            .addComponent(txtX, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sldVertical, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnModificar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnEliminar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(lblInfo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(pnlPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbValores, 0, 1, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCancelar)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sldHorizontal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtY)))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(txtX)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sldVertical, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(lblInfo)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAñadir)
                            .addComponent(cmbValores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnModificar)
                            .addComponent(btnEliminar))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sldHorizontal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        pressedOk = true; setVisible(false); s.deactivateSelected();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        setVisible(false); s.deactivateSelected();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void sldVerticalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldVerticalStateChanged
        txtX.setText(String.format("%d", sldVertical.getValue()));
        update();
    }//GEN-LAST:event_sldVerticalStateChanged

    private void sldHorizontalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldHorizontalStateChanged
        txtY.setText(String.format("%d", sldHorizontal.getValue()));
        update();
    }//GEN-LAST:event_sldHorizontalStateChanged

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        mode = 0;
        cmbValores.setEnabled(true);
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        mode = 1;
        cmbValores.setEnabled(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        mode = 2;
        cmbValores.setEnabled(false);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void update() {
        int valor = s.getTablero().getTab(getCoordsX(), getCoordsY());
        if(valor != 0) {
            displayer.setPieza(valor);
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnAñadir.setEnabled(false);
            btnAñadir.setSelected(false);
        } else {
            displayer.setPieza(0);
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnAñadir.setEnabled(true);
            btnModificar.setSelected(false);
            btnEliminar.setSelected(false);
            
        }
        s.setSelected(new int[] {getCoordsX(), getCoordsY()});
        displayer.repaint();
        s.repaint();
    }
    
    public int getCoordsX() {return sldVertical.getValue();}
    public int getCoordsY() {return sldHorizontal.getValue();}
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModifyBoardDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifyBoardDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifyBoardDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifyBoardDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            ModifyBoardDialog dialog = new ModifyBoardDialog(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JToggleButton btnAñadir;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JToggleButton btnEliminar;
    private javax.swing.JToggleButton btnModificar;
    private javax.swing.JComboBox<String> cmbValores;
    private javax.swing.ButtonGroup grpAcción;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JPanel pnlPieza;
    private javax.swing.JSlider sldHorizontal;
    private javax.swing.JSlider sldVertical;
    private javax.swing.JLabel txtX;
    private javax.swing.JLabel txtY;
    // End of variables declaration//GEN-END:variables
}
