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
    private Avanzadas() {
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
        chkDiagonalMovement = new javax.swing.JCheckBox();
        chkConsoleOutput = new javax.swing.JCheckBox();
        chkHUD = new javax.swing.JCheckBox();
        chkCheatsAvailable = new javax.swing.JCheckBox();
        chkMoreNextValues = new javax.swing.JCheckBox();
        chkBalancedStart = new javax.swing.JCheckBox();
        chkExitOnEnd = new javax.swing.JCheckBox();
        chkPaintArrows = new javax.swing.JCheckBox();
        chkEnhancedDiffMult = new javax.swing.JCheckBox();
        chkSaveResults = new javax.swing.JCheckBox();
        chkDrawZones = new javax.swing.JCheckBox();
        chkDrawGrid = new javax.swing.JCheckBox();

        setTitle("SumaTres - Opciones avanzadas");
        setResizable(false);

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

        chkDiagonalMovement.setText("Movimiento en diagonal");
        chkDiagonalMovement.setToolTipText("Si está activado, se podrán mover las piezas en diagonal además de en los cuatro sentidos clásicos.");
        chkDiagonalMovement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDiagonalMovementActionPerformed(evt);
            }
        });

        chkConsoleOutput.setText("Salida por consola");
        chkConsoleOutput.setToolTipText("Decide si también se muestra el resultado del turno por consola. Se puede cambiar durante la partida.");
        chkConsoleOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkConsoleOutputActionPerformed(evt);
            }
        });

        chkHUD.setText("HUD activado");
        chkHUD.setToolTipText("Decide si se muestra el HUD durante la partida.");
        chkHUD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkHUDActionPerformed(evt);
            }
        });

        chkCheatsAvailable.setText("Posibilidad de activar trucos");
        chkCheatsAvailable.setToolTipText("Si está activado, se podrán activar los trucos durante la partida.");
        chkCheatsAvailable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCheatsAvailableActionPerformed(evt);
            }
        });

        chkMoreNextValues.setText("Variar siguientes fichas");
        chkMoreNextValues.setToolTipText("Si está activado, el valor de la ficha \"siguiente\" dependerá del valor de la ficha más alta.");
        chkMoreNextValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMoreNextValuesActionPerformed(evt);
            }
        });

        chkBalancedStart.setText("Inicio de partida equilibrado");
        chkBalancedStart.setToolTipText("Si está activado, la cantidad de fichas al inicio de la partida dependerá del tamaño del tablero.");
        chkBalancedStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBalancedStartActionPerformed(evt);
            }
        });

        chkExitOnEnd.setText("Salir al terminar");
        chkExitOnEnd.setToolTipText("Si está activado, el programa se cierra cuando la partida se termina.");
        chkExitOnEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkExitOnEndActionPerformed(evt);
            }
        });

        chkPaintArrows.setText("Pintar flechas");
        chkPaintArrows.setToolTipText("Si está activado, se pintan las flechas que indican el movimiento de las fichas.");
        chkPaintArrows.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPaintArrowsActionPerformed(evt);
            }
        });

        chkEnhancedDiffMult.setText("Mult. de dificultad mejorado");
        chkEnhancedDiffMult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEnhancedDiffMultActionPerformed(evt);
            }
        });

        chkSaveResults.setText("Guardar resultados");
        chkSaveResults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaveResultsActionPerformed(evt);
            }
        });

        chkDrawZones.setText("Pintar zonas");
        chkDrawZones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDrawZonesActionPerformed(evt);
            }
        });

        chkDrawGrid.setText("Pintar grid");
        chkDrawGrid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDrawGridActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(btnClásico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExperimental)
                .addGap(88, 88, 88))
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkExitOnEnd)
                            .addComponent(chkPaintArrows)
                            .addComponent(chkDrawZones)
                            .addComponent(chkDrawGrid))
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkBalancedStart)
                            .addComponent(chkEnhancedDiffMult)
                            .addComponent(chkMoreNextValues)
                            .addComponent(chkSaveResults)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkHUD)
                            .addComponent(chkConsoleOutput))
                        .addGap(88, 88, 88)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkCheatsAvailable)
                            .addComponent(chkDiagonalMovement))))
                .addGap(0, 28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClásico)
                    .addComponent(btnExperimental))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkDiagonalMovement)
                    .addComponent(chkConsoleOutput))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkHUD)
                    .addComponent(chkCheatsAvailable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkExitOnEnd)
                    .addComponent(chkMoreNextValues))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkPaintArrows)
                    .addComponent(chkBalancedStart))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkEnhancedDiffMult)
                    .addComponent(chkDrawZones))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkSaveResults)
                    .addComponent(chkDrawGrid))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkDiagonalMovementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDiagonalMovementActionPerformed
        ventanaSecundaria.getSettings().toggleDiagonalMovement();
    }//GEN-LAST:event_chkDiagonalMovementActionPerformed

    private void btnClásicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClásicoActionPerformed
        ventanaSecundaria.setClassic();
        readValues();
    }//GEN-LAST:event_btnClásicoActionPerformed

    private void btnExperimentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExperimentalActionPerformed
        ventanaSecundaria.setExperimental();
        readValues();
    }//GEN-LAST:event_btnExperimentalActionPerformed

    private void chkConsoleOutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkConsoleOutputActionPerformed
        ventanaSecundaria.getSettings().toggleConsole();
    }//GEN-LAST:event_chkConsoleOutputActionPerformed

    private void chkHUDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkHUDActionPerformed
        ventanaSecundaria.getSettings().toggleHud();
    }//GEN-LAST:event_chkHUDActionPerformed

    private void chkCheatsAvailableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCheatsAvailableActionPerformed
        ventanaSecundaria.getSettings().togglePossibleCheats();
    }//GEN-LAST:event_chkCheatsAvailableActionPerformed

    private void chkMoreNextValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMoreNextValuesActionPerformed
        ventanaSecundaria.getSettings().toggleMoreNextValues();
    }//GEN-LAST:event_chkMoreNextValuesActionPerformed

    private void chkBalancedStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBalancedStartActionPerformed
        ventanaSecundaria.getSettings().toggleBalancedStart();
    }//GEN-LAST:event_chkBalancedStartActionPerformed

    private void chkExitOnEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkExitOnEndActionPerformed
        ventanaSecundaria.getSettings().toggleExitOnEnd();
    }//GEN-LAST:event_chkExitOnEndActionPerformed

    private void chkPaintArrowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPaintArrowsActionPerformed
        ventanaSecundaria.getSettings().togglePaintArrows();
    }//GEN-LAST:event_chkPaintArrowsActionPerformed

    private void chkEnhancedDiffMultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEnhancedDiffMultActionPerformed
        ventanaSecundaria.getSettings().toggleEnhancedDiffMult();
    }//GEN-LAST:event_chkEnhancedDiffMultActionPerformed

    private void chkSaveResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaveResultsActionPerformed
        ventanaSecundaria.getSettings().toggleSaveResultsToFile();
    }//GEN-LAST:event_chkSaveResultsActionPerformed

    private void chkDrawZonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDrawZonesActionPerformed
        ventanaSecundaria.getSettings().toggleDrawZones();
    }//GEN-LAST:event_chkDrawZonesActionPerformed

    private void chkDrawGridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDrawGridActionPerformed
        ventanaSecundaria.getSettings().toggleDrawGrid();
    }//GEN-LAST:event_chkDrawGridActionPerformed
 
    public void readValues() {
        Settings op = ventanaSecundaria.getSettings();
        
        btnExperimental.setSelected(op.isExperimental());
        btnClásico.setSelected(!op.isExperimental());
        
        chkDiagonalMovement.setEnabled(op.isExperimental());
        chkHUD.setEnabled(op.isExperimental());
        chkCheatsAvailable.setEnabled(op.isExperimental());
        chkMoreNextValues.setEnabled(op.isExperimental());
        chkBalancedStart.setEnabled(op.isExperimental());
        chkPaintArrows.setEnabled(op.isExperimental());
        chkEnhancedDiffMult.setEnabled(op.isExperimental());
        chkDrawGrid.setEnabled(op.isExperimental());
        chkDrawZones.setEnabled(op.isExperimental());
        
        chkDiagonalMovement.setSelected(op.isDiagonalMovementEnabled());
        chkHUD.setSelected(op.isHudEnabled());
        chkCheatsAvailable.setSelected(op.isPossibleCheats());
        chkBalancedStart.setSelected(op.isBalancedStartEnabled());
        chkMoreNextValues.setSelected(op.isMoreNextValuesEnabled());
        chkConsoleOutput.setSelected(op.isConsoleEnabled());
        chkExitOnEnd.setSelected(op.isExitOnEndEnabled());
        chkPaintArrows.setSelected(op.isPaintArrowsEnabled());
        chkEnhancedDiffMult.setSelected(op.isEnhancedDiffMultEnabled());
        chkSaveResults.setSelected(op.isSaveResultsToFileEnabled());
        chkDrawGrid.setSelected(op.isDrawGridEnabled());
        chkDrawZones.setSelected(op.isDrawZonesEnabled());
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Avanzadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Avanzadas().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgModos;
    private javax.swing.JRadioButton btnClásico;
    private javax.swing.JRadioButton btnExperimental;
    private javax.swing.JCheckBox chkBalancedStart;
    private javax.swing.JCheckBox chkCheatsAvailable;
    private javax.swing.JCheckBox chkConsoleOutput;
    private javax.swing.JCheckBox chkDiagonalMovement;
    private javax.swing.JCheckBox chkDrawGrid;
    private javax.swing.JCheckBox chkDrawZones;
    private javax.swing.JCheckBox chkEnhancedDiffMult;
    private javax.swing.JCheckBox chkExitOnEnd;
    private javax.swing.JCheckBox chkHUD;
    private javax.swing.JCheckBox chkMoreNextValues;
    private javax.swing.JCheckBox chkPaintArrows;
    private javax.swing.JCheckBox chkSaveResults;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
