/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;
import obj.Settings;

/**
 *
 * @author JuanMier
 */
public class Avanzadas extends javax.swing.JFrame {
    
    private PrePartida ventanaSecundaria;

    /**
     * Creates new form Avanzadas
     */
    public Avanzadas() {
        initComponents();
    }
    
    public Avanzadas(PrePartida p) {
        this();
        ventanaSecundaria = p;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgModos = new javax.swing.ButtonGroup();
        btnClásico = new javax.swing.JRadioButton();
        btnExperimental = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        chkMovDiagonal = new javax.swing.JCheckBox();
        chkSalidaConsola = new javax.swing.JCheckBox();
        chkHUD = new javax.swing.JCheckBox();
        chkPossibleCheats = new javax.swing.JCheckBox();
        chkMoreNextValues = new javax.swing.JCheckBox();
        chkInicioEquilibrado = new javax.swing.JCheckBox();

        setTitle("SumaTres - Opciones avanzadas");

        btgModos.add(btnClásico);
        btnClásico.setText("Modo clásico");
        btnClásico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClásicoActionPerformed(evt);
            }
        });

        btgModos.add(btnExperimental);
        btnExperimental.setText("Modo experimental");
        btnExperimental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExperimentalActionPerformed(evt);
            }
        });

        chkMovDiagonal.setText("Movimiento en diagonal");
        chkMovDiagonal.setToolTipText("Si está activado, se podrán mover las piezas en diagonal además de en los cuatro sentidos clásicos.");
        chkMovDiagonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMovDiagonalActionPerformed(evt);
            }
        });

        chkSalidaConsola.setText("Salida por consola");
        chkSalidaConsola.setToolTipText("Decide si también se muestra el resultado del turno por consola. Se puede cambiar durante la partida.");
        chkSalidaConsola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSalidaConsolaActionPerformed(evt);
            }
        });

        chkHUD.setText("HUD activado");
        chkHUD.setToolTipText("Decide si se muestra el HUD durante la partida.");
        chkHUD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkHUDActionPerformed(evt);
            }
        });

        chkPossibleCheats.setText("Posibilidad de activar trucos");
        chkPossibleCheats.setToolTipText("Si está activado, se podrán activar los trucos durante la partida.");
        chkPossibleCheats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPossibleCheatsActionPerformed(evt);
            }
        });

        chkMoreNextValues.setText("Variar siguientes fichas");
        chkMoreNextValues.setToolTipText("Si está activado, el valor de la ficha \"siguiente\" dependerá del valor de la ficha más alta.");
        chkMoreNextValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMoreNextValuesActionPerformed(evt);
            }
        });

        chkInicioEquilibrado.setText("Inicio de partida equilibrado");
        chkInicioEquilibrado.setToolTipText("Si está activado, la cantidad de fichas al inicio de la partida dependerá del tamaño del tablero.");
        chkInicioEquilibrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkInicioEquilibradoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(btnClásico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(btnExperimental)
                .addGap(48, 48, 48))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkInicioEquilibrado)
                    .addComponent(chkMoreNextValues)
                    .addComponent(chkPossibleCheats)
                    .addComponent(chkHUD)
                    .addComponent(chkMovDiagonal)
                    .addComponent(chkSalidaConsola))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClásico)
                    .addComponent(btnExperimental))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkMovDiagonal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkSalidaConsola)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkHUD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkPossibleCheats)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkMoreNextValues)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkInicioEquilibrado)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkMovDiagonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMovDiagonalActionPerformed
        ventanaSecundaria.getSettings().toggleDiagonalMovement();
    }//GEN-LAST:event_chkMovDiagonalActionPerformed

    private void btnClásicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClásicoActionPerformed
        setModoClásico();
        ventanaSecundaria.setClassic();
        ventanaSecundaria.setSettings(new Settings(ventanaSecundaria.getSettings().getX(), ventanaSecundaria.getSettings().getY(), false));
    }//GEN-LAST:event_btnClásicoActionPerformed

    private void btnExperimentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExperimentalActionPerformed
        setModoExperimental();
        ventanaSecundaria.setExperimental();
        ventanaSecundaria.setSettings(new Settings(ventanaSecundaria.getSettings().getX(), ventanaSecundaria.getSettings().getY(), true));
    }//GEN-LAST:event_btnExperimentalActionPerformed

    private void chkSalidaConsolaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSalidaConsolaActionPerformed
        ventanaSecundaria.getSettings().toggleConsole();
    }//GEN-LAST:event_chkSalidaConsolaActionPerformed

    private void chkHUDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkHUDActionPerformed
        ventanaSecundaria.getSettings().toggleHud();
    }//GEN-LAST:event_chkHUDActionPerformed

    private void chkPossibleCheatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPossibleCheatsActionPerformed
        ventanaSecundaria.getSettings().togglePossibleCheats();
    }//GEN-LAST:event_chkPossibleCheatsActionPerformed

    private void chkMoreNextValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMoreNextValuesActionPerformed
        ventanaSecundaria.getSettings().toggleMoreNextValues();
    }//GEN-LAST:event_chkMoreNextValuesActionPerformed

    private void chkInicioEquilibradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkInicioEquilibradoActionPerformed
        ventanaSecundaria.getSettings().toggleBalancedStart();
    }//GEN-LAST:event_chkInicioEquilibradoActionPerformed

    public void setModoClásico() {
        btnClásico.setSelected(true);
        chkMovDiagonal.setEnabled(false);
        chkHUD.setEnabled(false);
        chkPossibleCheats.setEnabled(false);
        chkMovDiagonal.setSelected(false);
        chkHUD.setSelected(true);
        chkPossibleCheats.setSelected(false);
        chkSalidaConsola.setSelected(true);
        chkInicioEquilibrado.setSelected(false);
        chkInicioEquilibrado.setEnabled(false);
        chkMoreNextValues.setEnabled(false);
        chkMoreNextValues.setSelected(false);
    }
    
    public void setModoExperimental() {
        btnExperimental.setSelected(true);
        chkMovDiagonal.setEnabled(true);
        chkHUD.setEnabled(true);
        chkPossibleCheats.setEnabled(true);
        chkHUD.setSelected(true);
        chkPossibleCheats.setSelected(true);
        chkSalidaConsola.setSelected(false);
        chkMovDiagonal.setSelected(true);
        chkInicioEquilibrado.setSelected(true);
        chkInicioEquilibrado.setEnabled(true);
        chkMoreNextValues.setEnabled(true);
        chkMoreNextValues.setSelected(true);
    }
    
    
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
            java.util.logging.Logger.getLogger(Avanzadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Avanzadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Avanzadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Avanzadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Avanzadas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgModos;
    private javax.swing.JRadioButton btnClásico;
    private javax.swing.JRadioButton btnExperimental;
    private javax.swing.JCheckBox chkHUD;
    private javax.swing.JCheckBox chkInicioEquilibrado;
    private javax.swing.JCheckBox chkMoreNextValues;
    private javax.swing.JCheckBox chkMovDiagonal;
    private javax.swing.JCheckBox chkPossibleCheats;
    private javax.swing.JCheckBox chkSalidaConsola;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}