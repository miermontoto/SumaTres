package game;

import obj.Settings;
import util.Dialog;
import util.Graphic; // Se utiliza para definir dimensiones, escala, etc.

public class LauncherRF extends javax.swing.JFrame {
    
    private PrePartida secundaria;
    private SumaTres juego;
    

    /**
     * Creates new form LauncherRF
     */
    public LauncherRF() {
        initComponents();
        secundaria = new PrePartida(this);
        secundaria.setVisible(true);
    }
    
    public void launch(Settings op) {
        juego = new SumaTres(op);
        add(juego);
        setBounds(0, 0, Graphic.defineX(juego) + 15, Graphic.defineY(juego) + 39);
        setVisible(true);
        jmiTrucos.setEnabled(op.isExperimental()); // Activa la posibilidad de trucos en modo exp.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiResultados = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmiSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiTrucos = new javax.swing.JCheckBoxMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jmiTrucosAñadir = new javax.swing.JMenuItem();
        jmiTrucosEliminar = new javax.swing.JMenuItem();
        jmiTrucosPuntos = new javax.swing.JMenuItem();
        jmiTrucosUndo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SumaTres");
        setIconImage(Graphic.ICON.getImage());
        setResizable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Juego", jPanel1);

        jToolBar1.setRollover(true);
        jTabbedPane1.addTab("Info", jToolBar1);

        jMenu1.setText("Archivo");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jmiResultados.setText("Resultados prev.");
        jMenu1.add(jmiResultados);
        jMenu1.add(jSeparator1);

        jmiSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.META_DOWN_MASK));
        jmiSalir.setText("Salir");
        jMenu1.add(jmiSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Trucos");

        jmiTrucos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmiTrucos.setText("Trucos");
        jmiTrucos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTrucosActionPerformed(evt);
            }
        });
        jMenu2.add(jmiTrucos);
        jMenu2.add(jSeparator2);

        jmiTrucosAñadir.setText("Añadir pieza");
        jmiTrucosAñadir.setEnabled(false);
        jmiTrucosAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTrucosAñadirActionPerformed(evt);
            }
        });
        jMenu2.add(jmiTrucosAñadir);

        jmiTrucosEliminar.setText("Eliminar pieza");
        jmiTrucosEliminar.setEnabled(false);
        jmiTrucosEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTrucosEliminarActionPerformed(evt);
            }
        });
        jMenu2.add(jmiTrucosEliminar);

        jmiTrucosPuntos.setText("Añadir puntos");
        jmiTrucosPuntos.setEnabled(false);
        jmiTrucosPuntos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTrucosPuntosActionPerformed(evt);
            }
        });
        jMenu2.add(jmiTrucosPuntos);

        jmiTrucosUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmiTrucosUndo.setText("Undo");
        jmiTrucosUndo.setEnabled(false);
        jmiTrucosUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTrucosUndoActionPerformed(evt);
            }
        });
        jMenu2.add(jmiTrucosUndo);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiTrucosAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTrucosAñadirActionPerformed
        juego.getTablero().colocarPieza();
    }//GEN-LAST:event_jmiTrucosAñadirActionPerformed

    private void jmiTrucosEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTrucosEliminarActionPerformed
        juego.getTablero().quitarPieza();
    }//GEN-LAST:event_jmiTrucosEliminarActionPerformed

    private void jmiTrucosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTrucosActionPerformed
        jmiTrucos.setEnabled(false);
        jmiTrucosAñadir.setEnabled(true);
        jmiTrucosEliminar.setEnabled(true);
        jmiTrucosUndo.setEnabled(true);
        jmiTrucosPuntos.setEnabled(true);
    }//GEN-LAST:event_jmiTrucosActionPerformed

    private void jmiTrucosUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTrucosUndoActionPerformed
        juego.undo();
    }//GEN-LAST:event_jmiTrucosUndoActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        juego.finalDePartida();
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jmiTrucosPuntosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTrucosPuntosActionPerformed
        juego.addPuntos(Integer.parseInt(Dialog.input("Número de puntos a añadir:")));
    }//GEN-LAST:event_jmiTrucosPuntosActionPerformed

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
            java.util.logging.Logger.getLogger(LauncherRF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LauncherRF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LauncherRF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LauncherRF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LauncherRF().setVisible(false);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem jmiResultados;
    private javax.swing.JMenuItem jmiSalir;
    private javax.swing.JCheckBoxMenuItem jmiTrucos;
    private javax.swing.JMenuItem jmiTrucosAñadir;
    private javax.swing.JMenuItem jmiTrucosEliminar;
    private javax.swing.JMenuItem jmiTrucosPuntos;
    private javax.swing.JMenuItem jmiTrucosUndo;
    // End of variables declaration//GEN-END:variables
}
