package game;

import gui.PrePartida;
import gui.EditarColores;
import obj.Settings;
import util.Dialog; // Se utiliza para hacer que el usuario confirme algunas acciones.
import util.Graphic; // Se utiliza para definir las dimensiones de la ventana.

public class LauncherRF extends javax.swing.JFrame {
    
    private final PrePartida secundaria;
    private final EditarColores ventanaColores;
    private SumaTres juego;
    
    /**
     * Creates new form LauncherRF
     */
    public LauncherRF() {
        initComponents();
        secundaria = new PrePartida(this);
        secundaria.setVisible(true);
        ventanaColores = new EditarColores(this);
        ventanaColores.setVisible(false);
    }
    
    /**
     * Método que lanza la partida como tal. Solo debería accederse una vez.
     * Establece el tamaño de ventana dependiendo del tamaño del tablero,
     * hace visible esta ventana y crea un objeto de tipo SumaTres según las
     * opciones introducidas.
     * @param op Opciones con las que se pretende inicializar la partida.
     */
    public void launch(Settings op) {
        juego = new SumaTres(op);
        setBounds(0, 0, Graphic.defineX(juego) + 15, (int) (Graphic.defineY(juego) + 39 + 30 * Graphic.SCALE));
        setVisible(true);
        
        /*
         * Se activan los trucos, cambios de modo, etc, en base a si el modo
         * actual es experimental o no. Siguiendo las reglas del enunciado
         * original, el modo clásico es imperturbable por estos cambios.
         */
        
        juego.setSize(Graphic.defineX(juego) + 15, Graphic.defineY(juego) + 39);
        juego.setLocation(0,0);
        juego.setName("Juego");
        jTabbedPane1.add(juego);
        //jTabbedPane1.setSelectedComponent(juego);
        jTabbedPane1.setComponentAt(0, juego);
        pneInfo.setName("Info");
        jTabbedPane1.add(pneInfo);
        
        jmiTrucos.setEnabled(op.isPossibleCheats());
        jmiModoExperimental.setEnabled(op.isExperimental());
        jmiModoClassic.setEnabled(op.isExperimental());
        jmiModoClassic.setSelected(op.isExperimental());
        jmiModoExperimental.setSelected(op.isExperimental());
        jmiExtrasConsole.setSelected(!op.isExperimental());
        
        // TODO: habilitar estas funciones
        jmiCargar.setEnabled(false);
        jmiGuardar.setEnabled(false);
        jmiResultados.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jmiModoGroup = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pneInfo = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuArchivo = new javax.swing.JMenu();
        jmiGuardar = new javax.swing.JMenuItem();
        jmiCargar = new javax.swing.JMenuItem();
        jmiResultados = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmiSalir = new javax.swing.JMenuItem();
        mnuOpciones = new javax.swing.JMenu();
        jmiExtrasConsole = new javax.swing.JCheckBoxMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jmiColores = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jmiModo = new javax.swing.JMenu();
        jmiModoClassic = new javax.swing.JRadioButtonMenuItem();
        jmiModoExperimental = new javax.swing.JRadioButtonMenuItem();
        mnuTrucos = new javax.swing.JMenu();
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

        pneInfo.setEditable(false);
        pneInfo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pneInfoComponentShown(evt);
            }
        });
        jTabbedPane1.addTab("Info", pneInfo);

        mnuArchivo.setText("Archivo");

        jmiGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmiGuardar.setText("Guardar");
        mnuArchivo.add(jmiGuardar);

        jmiCargar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmiCargar.setText("Cargar");
        mnuArchivo.add(jmiCargar);

        jmiResultados.setText("Resultados prev.");
        mnuArchivo.add(jmiResultados);
        mnuArchivo.add(jSeparator1);

        jmiSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.META_DOWN_MASK));
        jmiSalir.setText("Salir");
        jmiSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSalirActionPerformed(evt);
            }
        });
        mnuArchivo.add(jmiSalir);

        jMenuBar1.add(mnuArchivo);

        mnuOpciones.setText("Opciones");

        jmiExtrasConsole.setSelected(true);
        jmiExtrasConsole.setText("Salida por consola");
        jmiExtrasConsole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExtrasConsoleActionPerformed(evt);
            }
        });
        mnuOpciones.add(jmiExtrasConsole);
        mnuOpciones.add(jSeparator3);

        jmiColores.setText("Editar colores");
        jmiColores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiColoresActionPerformed(evt);
            }
        });
        mnuOpciones.add(jmiColores);
        mnuOpciones.add(jSeparator4);

        jmiModo.setText("Modo");

        jmiModoGroup.add(jmiModoClassic);
        jmiModoClassic.setSelected(true);
        jmiModoClassic.setText("Clásico");
        jmiModoClassic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiModoClassicActionPerformed(evt);
            }
        });
        jmiModo.add(jmiModoClassic);

        jmiModoGroup.add(jmiModoExperimental);
        jmiModoExperimental.setText("Experimental");
        jmiModoExperimental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiModoExperimentalActionPerformed(evt);
            }
        });
        jmiModo.add(jmiModoExperimental);

        mnuOpciones.add(jmiModo);

        jMenuBar1.add(mnuOpciones);

        mnuTrucos.setText("Trucos");
        mnuTrucos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mnuTrucosMouseEntered(evt);
            }
        });

        jmiTrucos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmiTrucos.setText("Trucos");
        jmiTrucos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jmiTrucosMouseEntered(evt);
            }
        });
        jmiTrucos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTrucosActionPerformed(evt);
            }
        });
        mnuTrucos.add(jmiTrucos);
        mnuTrucos.add(jSeparator2);

        jmiTrucosAñadir.setText("Añadir pieza");
        jmiTrucosAñadir.setEnabled(false);
        jmiTrucosAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTrucosAñadirActionPerformed(evt);
            }
        });
        mnuTrucos.add(jmiTrucosAñadir);

        jmiTrucosEliminar.setText("Eliminar pieza");
        jmiTrucosEliminar.setEnabled(false);
        jmiTrucosEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTrucosEliminarActionPerformed(evt);
            }
        });
        mnuTrucos.add(jmiTrucosEliminar);

        jmiTrucosPuntos.setText("Añadir puntos");
        jmiTrucosPuntos.setEnabled(false);
        jmiTrucosPuntos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTrucosPuntosActionPerformed(evt);
            }
        });
        mnuTrucos.add(jmiTrucosPuntos);

        jmiTrucosUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmiTrucosUndo.setText("Undo");
        jmiTrucosUndo.setEnabled(false);
        jmiTrucosUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTrucosUndoActionPerformed(evt);
            }
        });
        mnuTrucos.add(jmiTrucosUndo);

        jMenuBar1.add(mnuTrucos);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiTrucosAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTrucosAñadirActionPerformed
        juego.getTablero().colocarPieza();
    }//GEN-LAST:event_jmiTrucosAñadirActionPerformed

    private void jmiTrucosEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTrucosEliminarActionPerformed
        juego.getTablero().quitarPieza();
    }//GEN-LAST:event_jmiTrucosEliminarActionPerformed

    /**
     * Evento que activa el resto de opciones del menú "Trucos". Se supone que
     * los trucos están desactivados antes de este evento y estarán activados
     * después, sin ninguna excepción.
     * 
     * Antes de activar los trucos, muestra una alerta al usuario para que
     * confirme la acción.
     * @param evt 
     */
    private void jmiTrucosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTrucosActionPerformed
        if(Dialog.confirm("Activar los trucos marcará el resultado de la partida como inválido. ¿Desea continuar?")) {
            juego.enableCheats();
            jmiTrucos.setEnabled(false);
            setCheatsEnabled(true);
        } else {jmiTrucos.setSelected(false);}
    }//GEN-LAST:event_jmiTrucosActionPerformed

    private void jmiTrucosUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTrucosUndoActionPerformed
        juego.undo();
    }//GEN-LAST:event_jmiTrucosUndoActionPerformed

    private void jmiTrucosPuntosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTrucosPuntosActionPerformed
        juego.addPuntos(Integer.parseInt(Dialog.input("Número de puntos a añadir:")));
    }//GEN-LAST:event_jmiTrucosPuntosActionPerformed

    private void jmiModoClassicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiModoClassicActionPerformed
        boolean check = true;
        if(!juego.cheatsUsed()) check = Dialog.confirm("Esta acción activa los trucos. ¿Desea continuar?");
        if(check) {
            juego.enableCheats();
            juego.getSettings().setExperimentalMode(false);
            jmiTrucos.setEnabled(false);
            setCheatsEnabled(false);
        }
    }//GEN-LAST:event_jmiModoClassicActionPerformed

    private void setCheatsEnabled(final boolean status) {
        jmiTrucosAñadir.setEnabled(status);
        jmiTrucosEliminar.setEnabled(status);
        jmiTrucosPuntos.setEnabled(status);
        jmiTrucosUndo.setEnabled(status);
    }
    
    private void jmiModoExperimentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiModoExperimentalActionPerformed
        // Si se llega aquí, significa que el usuario partió del modo experimental,
        // pasó al modo clásico y con esta acción vuelve al modo experimental, por
        // lo que no es necesario volver a activar los trucos ni mostrar alertas.
        juego.getSettings().setExperimentalMode(true);
        setCheatsEnabled(true); // se activan los trucos.
    }//GEN-LAST:event_jmiModoExperimentalActionPerformed

    private void jmiExtrasConsoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExtrasConsoleActionPerformed
        juego.toggleConsole();
    }//GEN-LAST:event_jmiExtrasConsoleActionPerformed

    /**
     * Actualiza el panel de información cada vez que se muestra. Este listener
     * evita tener que estar actualizando constantemente o tener un botón con el
     * que tenga que interactuar el usuario.
     * @param evt 
     */
    private void pneInfoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pneInfoComponentShown
        if(juego != null) {
            pneInfo.setText(String.format("Puntos obtenidos: %d (multiplicador: %.1f)%n"
                    + "Turnos jugados: %d%n"
                    + "Siguiente ficha: %d%n"
                    + "Posibles siguientes: ",
                    (int) (juego.getPuntos()*juego.getMultiplier()), juego.getMultiplier(), juego.getTurnos(), juego.getSiguiente()));
            for(int i : juego.possibleValuesNewSiguiente()) {
                pneInfo.setText(String.format("%s%d ", pneInfo.getText(), i));
            }
            pneInfo.setText(String.format("%s%n%n"
                    + "Modo: %s%n"
                    + "Trucos: %s%n"
                    + "Tamaño del tablero: %d x %d%n",
                    pneInfo.getText(), juego.getSettings().isExperimental() ? "experimental" : "clásico",
                    juego.cheatsUsed() ? "activados" : "desactivados",
                    juego.getTablero().getRows(), juego.getTablero().getColumns()));
        }
    }//GEN-LAST:event_pneInfoComponentShown

    private void jmiSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSalirActionPerformed
        juego.finalDePartida();
    }//GEN-LAST:event_jmiSalirActionPerformed

    private void jmiColoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiColoresActionPerformed
        ventanaColores.setVisible(true);
    }//GEN-LAST:event_jmiColoresActionPerformed

    private void jmiTrucosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmiTrucosMouseEntered
        jmiTrucos.setToolTipText(String.format("%s",  juego.getSettings().isPossibleCheats() ? "Los trucos no se pueden desactivar una vez habilitados." : "Los trucos no se pueden activar en modo clásico."));
    }//GEN-LAST:event_jmiTrucosMouseEntered

    private void mnuTrucosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuTrucosMouseEntered
        jmiTrucos.setSelected(juego.cheatsUsed());
    }//GEN-LAST:event_mnuTrucosMouseEntered

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
            java.util.logging.Logger.getLogger(LauncherRF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LauncherRF().setVisible(false);
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem jmiCargar;
    private javax.swing.JMenuItem jmiColores;
    private javax.swing.JCheckBoxMenuItem jmiExtrasConsole;
    private javax.swing.JMenuItem jmiGuardar;
    private javax.swing.JMenu jmiModo;
    private javax.swing.JRadioButtonMenuItem jmiModoClassic;
    private javax.swing.JRadioButtonMenuItem jmiModoExperimental;
    private javax.swing.ButtonGroup jmiModoGroup;
    private javax.swing.JMenuItem jmiResultados;
    private javax.swing.JMenuItem jmiSalir;
    private javax.swing.JCheckBoxMenuItem jmiTrucos;
    private javax.swing.JMenuItem jmiTrucosAñadir;
    private javax.swing.JMenuItem jmiTrucosEliminar;
    private javax.swing.JMenuItem jmiTrucosPuntos;
    private javax.swing.JMenuItem jmiTrucosUndo;
    private javax.swing.JMenu mnuArchivo;
    private javax.swing.JMenu mnuOpciones;
    private javax.swing.JMenu mnuTrucos;
    private javax.swing.JTextPane pneInfo;
    // End of variables declaration//GEN-END:variables
}
