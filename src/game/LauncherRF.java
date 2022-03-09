package game;

import com.formdev.flatlaf.FlatDarkLaf; // Modo claro que reemplaza a Nimbus.
import com.formdev.flatlaf.FlatLightLaf; // Modo oscuro.
import gui.PrePartida; // Ventana de opciones prepartida.
import gui.EditarColores; // Ventana de edición de colores de piezas.
import handler.Keyboard;
import handler.Mouse;
import java.awt.Desktop;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Map; // Map.EntrySet
import javax.swing.SwingUtilities; // Se utiliza para actualizar intfz. entre modo claro y oscuro.
import obj.Settings; // Se utiliza para guardar y editar opciones.
import obj.Turno;
import thread.LoopComms;
import util.Dialog; // Se utiliza para hacer que el usuario confirme algunas acciones.
import util.Graphic; // Se utiliza para definir las dimensiones de la ventana.
import util.Paint; // Se utiliza para obtener el color del fondo.


/**
 * Ventana principal y sustituto del antiguo <code>Launcher</code> o <code>
 * SumaTresTexto</code>.
 * @author JuanMier
 */
public class LauncherRF extends javax.swing.JFrame {
    
    private final PrePartida secundaria;
    private final EditarColores ventanaColores;
    private SumaTres juego;
    private Thread loopThread;
    private static LoopComms loopComms;
    
    
    /**
     * Constructor principal y único. No necesita parámetros.
     */
    public LauncherRF() {
        FlatLightLaf.setup(); // Se establece el modo claro por defecto.
        initComponents();
        ventanaColores = new EditarColores(this);
        ventanaColores.setVisible(false);
        secundaria = new PrePartida(this);
        secundaria.setVisible(true); // Se lanza la ventana de opciones prepartida.
    }
    
    /**
     * Método que lanza la partida como tal. Solo debería accederse una vez.
     * Establece el tamaño de ventana dependiendo del tamaño del tablero,
     * hace visible esta ventana y crea un objeto de tipo SumaTres según las
     * opciones introducidas. <p>
     * 
     * Se encarga de crear un objeto de clase <code>SumaTres</code> que es
     * el que se utilzará para jugar la partida, hasta el final de la misma. <p>
     * 
     * Se determinan qué opciones y menús están habilitados y seleccionados.
     * @param op Opciones con las que se pretende inicializar la partida.
     */
    public void launch(Settings op) {
        juego = new SumaTres(op);

        
        /*
         * Handlers de teclado y ratón.
         * Son más flexibles al estar en la ventana principal en vez del
         * objeto de la partida y simplifican código.
         */
        juego.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                new Keyboard(juego, event).keyboardHandler();
                ventanaColores.updateValues();
            }
        });
        
        juego.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                new Mouse(juego, event).mouseHandler();
            }
        });
                
                
        
        // Propiedades de la ventana.
        setBounds(0, 0, Graphic.defineX(juego), Graphic.defineY(juego));
        setVisible(true);
        loopComms = new LoopComms(this);
         
        // Propiedades del JPanel de la partida.
        juego.setSize(Graphic.defineX(juego) + 15, Graphic.defineY(juego) + 39);
        juego.setLocation(0,0);
        juego.setBackground(op.isDarkModeEnabled() ? Paint.DARK_BACKGROUND : Paint.LIGHT_BACKGROUND);
        
        // añade el panel de la partida y establece el orden correcto.
        jTabbedPane1.addTab("Juego", juego);
        jTabbedPane1.setComponentAt(0, juego);
        jTabbedPane1.addTab("Info", pneInfo);
        
        SwingUtilities.updateComponentTreeUI(this); // arregla pintura extra en la barra de pestañas.
        
        // Se determinan qué opciones del menú pueden ser seleccionadas y cuales ya lo están.
        jmiTrucos.setEnabled(op.isPossibleCheats());
        jmiExtrasConsole.setSelected(op.isConsoleEnabled());
        jmiExitOnEnd.setSelected(op.isExitOnEndEnabled());
        jmiInterfazHud.setSelected(op.isHudEnabled());
        jmiInterfazFlechas.setSelected(op.isPaintArrowsEnabled());
        jmiInterfazHud.setEnabled(op.isExperimental());
        jmiInterfazFlechas.setEnabled(op.isExperimental());
        jmiDarkMode.setSelected(op.isDarkModeEnabled());
        jmiInterfaz.setEnabled(op.isExperimental());
        jmiIntefazGrid.setSelected(op.isDrawGridEnabled());
        jmiInterfazZonas.setSelected(op.isDrawZonesEnabled());
        jmiInterfazCoords.setSelected(op.isDrawCoordsEnabled());
        
        // Se selecciona qué modo y si se puede cambiar entre modos o no.
        jmiModoClassic.setEnabled(op.isExperimental());
        jmiModoClassic.setSelected(op.isExperimental());
        jmiModoExperimental.setEnabled(op.isExperimental());
        jmiModoExperimental.setSelected(op.isExperimental());
              
        // TODO: habilitar estas funciones
        jmiLoad.setEnabled(false);
        jmiSave.setEnabled(false);
        
        requestFocusInWindow();
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
        jmiSave = new javax.swing.JMenuItem();
        jmiLoad = new javax.swing.JMenuItem();
        jmiResults = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmiSalir = new javax.swing.JMenuItem();
        mnuOpciones = new javax.swing.JMenu();
        jmiExtrasConsole = new javax.swing.JCheckBoxMenuItem();
        jmiExitOnEnd = new javax.swing.JCheckBoxMenuItem();
        jmiDarkMode = new javax.swing.JCheckBoxMenuItem();
        jmiSaveOnExit = new javax.swing.JCheckBoxMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jmiInterfaz = new javax.swing.JMenu();
        jmiInterfazFlechas = new javax.swing.JCheckBoxMenuItem();
        jmiInterfazHud = new javax.swing.JCheckBoxMenuItem();
        jmiInterfazZonas = new javax.swing.JCheckBoxMenuItem();
        jmiIntefazGrid = new javax.swing.JCheckBoxMenuItem();
        jmiInterfazCoords = new javax.swing.JCheckBoxMenuItem();
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
        jmiTrucosModSiguiente = new javax.swing.JMenuItem();
        jmiTrucosForzarSiguiente = new javax.swing.JMenuItem();
        jmiTrucosPuntos = new javax.swing.JMenuItem();
        jmiTrucosUndo = new javax.swing.JMenuItem();
        jmiTrucosLoop = new javax.swing.JMenu();
        jmiTrucosLoopStart = new javax.swing.JMenuItem();
        jmiTrucosLoopEnd = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SumaTres");
        setIconImage(Graphic.ICON.getImage());
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        pneInfo.setEditable(false);
        pneInfo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pneInfoComponentShown(evt);
            }
        });
        jTabbedPane1.addTab("Info", pneInfo);

        getContentPane().add(jTabbedPane1, "card2");

        mnuArchivo.setText("Archivo");

        jmiSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmiSave.setText("Guardar");
        mnuArchivo.add(jmiSave);

        jmiLoad.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmiLoad.setText("Cargar");
        mnuArchivo.add(jmiLoad);

        jmiResults.setText("Resultados prev.");
        jmiResults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiResultsActionPerformed(evt);
            }
        });
        mnuArchivo.add(jmiResults);
        mnuArchivo.add(jSeparator1);

        jmiSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmiSalir.setText("Terminar partida");
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

        jmiExitOnEnd.setSelected(true);
        jmiExitOnEnd.setText("Salir al terminar");
        jmiExitOnEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExitOnEndActionPerformed(evt);
            }
        });
        mnuOpciones.add(jmiExitOnEnd);

        jmiDarkMode.setSelected(true);
        jmiDarkMode.setText("Modo oscuro");
        jmiDarkMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiDarkModeActionPerformed(evt);
            }
        });
        mnuOpciones.add(jmiDarkMode);

        jmiSaveOnExit.setSelected(true);
        jmiSaveOnExit.setText("Guardar res. al terminar");
        jmiSaveOnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSaveOnExitActionPerformed(evt);
            }
        });
        mnuOpciones.add(jmiSaveOnExit);
        mnuOpciones.add(jSeparator5);

        jmiInterfaz.setText("Interfaz");
        jmiInterfaz.setEnabled(false);

        jmiInterfazFlechas.setSelected(true);
        jmiInterfazFlechas.setText("Dibujar flechas");
        jmiInterfazFlechas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiInterfazFlechasActionPerformed(evt);
            }
        });
        jmiInterfaz.add(jmiInterfazFlechas);

        jmiInterfazHud.setSelected(true);
        jmiInterfazHud.setText("Dibujar HUD");
        jmiInterfazHud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiInterfazHudActionPerformed(evt);
            }
        });
        jmiInterfaz.add(jmiInterfazHud);

        jmiInterfazZonas.setSelected(true);
        jmiInterfazZonas.setText("Dibujar zonas");
        jmiInterfazZonas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiInterfazZonasActionPerformed(evt);
            }
        });
        jmiInterfaz.add(jmiInterfazZonas);

        jmiIntefazGrid.setSelected(true);
        jmiIntefazGrid.setText("Dibujar grid");
        jmiIntefazGrid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiIntefazGridActionPerformed(evt);
            }
        });
        jmiInterfaz.add(jmiIntefazGrid);

        jmiInterfazCoords.setSelected(true);
        jmiInterfazCoords.setText("Dibujar coordenadas");
        jmiInterfazCoords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiInterfazCoordsActionPerformed(evt);
            }
        });
        jmiInterfaz.add(jmiInterfazCoords);

        mnuOpciones.add(jmiInterfaz);
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

        jmiTrucosAñadir.setText("Modificar tablero");
        jmiTrucosAñadir.setEnabled(false);
        jmiTrucosAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTrucosAñadirActionPerformed(evt);
            }
        });
        mnuTrucos.add(jmiTrucosAñadir);

        jmiTrucosModSiguiente.setText("Modificar siguiente");
        jmiTrucosModSiguiente.setEnabled(false);
        jmiTrucosModSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTrucosModSiguienteActionPerformed(evt);
            }
        });
        mnuTrucos.add(jmiTrucosModSiguiente);

        jmiTrucosForzarSiguiente.setText("Forzar siguiente");
        jmiTrucosForzarSiguiente.setEnabled(false);
        jmiTrucosForzarSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTrucosForzarSiguienteActionPerformed(evt);
            }
        });
        mnuTrucos.add(jmiTrucosForzarSiguiente);

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

        jmiTrucosLoop.setText("Loop");
        jmiTrucosLoop.setEnabled(false);

        jmiTrucosLoopStart.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_PAGE_DOWN, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmiTrucosLoopStart.setText("Iniciar");
        jmiTrucosLoopStart.setEnabled(false);
        jmiTrucosLoopStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTrucosLoopStartActionPerformed(evt);
            }
        });
        jmiTrucosLoop.add(jmiTrucosLoopStart);

        jmiTrucosLoopEnd.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_PAGE_UP, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmiTrucosLoopEnd.setText("Parar");
        jmiTrucosLoopEnd.setEnabled(false);
        jmiTrucosLoopEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTrucosLoopEndActionPerformed(evt);
            }
        });
        jmiTrucosLoop.add(jmiTrucosLoopEnd);

        mnuTrucos.add(jmiTrucosLoop);

        jMenuBar1.add(mnuTrucos);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiTrucosAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTrucosAñadirActionPerformed
        juego.modificarTablero();
    }//GEN-LAST:event_jmiTrucosAñadirActionPerformed

    private void jmiTrucosUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTrucosUndoActionPerformed
        juego.undo();
    }//GEN-LAST:event_jmiTrucosUndoActionPerformed

    private void jmiTrucosPuntosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTrucosPuntosActionPerformed
        juego.addPuntos(Integer.parseInt(Dialog.input("Número de puntos a añadir:")));
    }//GEN-LAST:event_jmiTrucosPuntosActionPerformed

    private void jmiModoClassicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiModoClassicActionPerformed
        boolean check = true;
        if(juego.getMultiplier() != 0.0) check = Dialog.confirm("Esta acción cambia el mutiplicador de puntos a cero.\n¿Desea continuar?");
        if(check) {
            juego.setMultiplier(0.0);
            juego.getSettings().setExperimentalMode(false);
            jmiTrucos.setEnabled(false);            
            setCheatsEnabled(false);
            juego.repaint();
            actualizarPneInfo();
        } else {jmiModoExperimental.setSelected(true);}
    }//GEN-LAST:event_jmiModoClassicActionPerformed

    private void setCheatsEnabled(final boolean status) {
        boolean opdef = juego.areCheatsEnabled() && juego.getSettings().isPossibleCheats() && status;
        jmiTrucosAñadir.setEnabled(opdef);
        jmiTrucosPuntos.setEnabled(opdef);
        jmiTrucosUndo.setEnabled(opdef);
        jmiTrucosLoopStart.setEnabled(opdef);
        jmiTrucosModSiguiente.setEnabled(opdef);
        jmiTrucosForzarSiguiente.setEnabled(opdef);
        jmiTrucosLoop.setEnabled(opdef);
    }
    
    public void loopStarting() {
        jmiTrucosLoopStart.setEnabled(false);
        jmiTrucosLoopEnd.setEnabled(true);
        jmiTrucosLoopStart.setSelected(true);
    }
    
    public void loopEnding() {
        jmiTrucosLoopStart.setEnabled(true);
        jmiTrucosLoopEnd.setEnabled(false);
        jmiTrucosLoopStart.setSelected(false);
    }
    
    private void jmiModoExperimentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiModoExperimentalActionPerformed
        // Si se llega aquí, significa que el usuario partió del modo experimental,
        // pasó al modo clásico y con esta acción vuelve al modo experimental, por
        // lo que no es necesario volver a activar los trucos ni mostrar alertas.
        juego.getSettings().setExperimentalMode(true);
        setCheatsEnabled(true); // Se activan los trucos.
        jmiTrucos.setEnabled(true);
        juego.repaint();
        actualizarPneInfo();
    }//GEN-LAST:event_jmiModoExperimentalActionPerformed

    private void jmiExtrasConsoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExtrasConsoleActionPerformed
        actualizarPneInfo();
        juego.toggleConsole();
    }//GEN-LAST:event_jmiExtrasConsoleActionPerformed

    public void toggleDarkMode(boolean b) {
        if(b) FlatDarkLaf.setup();
        else FlatLightLaf.setup();
        SwingUtilities.updateComponentTreeUI(this);
        SwingUtilities.updateComponentTreeUI(ventanaColores);
    }
    
    private void actualizarPneInfo() {
        if(juego != null) {
            Settings op = juego.getSettings();
            String s = String.format("Puntos obtenidos: %d (multiplicador: %.1f)%n"
                + "Turnos jugados: %d%n"
                + "Siguiente ficha: %d%n"
                + "Pieza más alta: %d%n",
                (int) (juego.getPuntos()*juego.getMultiplier()), 
                juego.getMultiplier(), juego.getTurnos(), juego.getSiguiente(),
                juego.getHighest());

            s += String.format("Posibles piezas siguientes actualmente: ");
            for(int i : juego.possibleValuesNewSiguiente()) 
                s += String.format("%d ", i);

            s += String.format("%nPiezas \"siguientes\" obtenidas:%n");
            for(Map.Entry<Integer, Integer> par : juego.getObtainedFromRandom().entrySet())
                s += String.format("\t- [%d]: %d obtenido(s)%n", par.getKey(), par.getValue());

            s += String.format("%nModo: %s%n"
                + "Trucos: %s%n"
                + "Tamaño del tablero: %d x %d%n"
                + "HUD: %s%n"
                + "Flechas: %s%n",
                op.isExperimental() ? "experimental" : "clásico",
                juego.areCheatsEnabled() ? "activados" : "desactivados",
                juego.getTablero().getRows(), juego.getTablero().getColumns(),
                activado(op.isHudEnabled()), activado(op.isPaintArrowsEnabled()));

            s += String.format("%nMovimiento diagonal: %s%n"
                + "Salida por consola: %s%n"
                + "Posibilidad de activar trucos: %s%n"
                + "Inicio de partida equilibrado: %s%n"
                + "Más fichas siguientes: %s%n"
                + "Multiplicador de dificultad mejorado %s%n"
                + "Guardar resultados al final de la partida: %s%n",
                activado(op.isDiagonalMovementEnabled()),
                activado(op.isConsoleEnabled()),
                activado(op.isPossibleCheats()),
                activado(op.isBalancedStartEnabled()),
                activado(op.isMoreNextValuesEnabled()),
                activado(op.isEnhancedDiffMultEnabled()),
                activado(op.isSaveResultsToFileEnabled())
                );
            
            s += String.format("%n%n%s", juego.isFinished() ? "Partida terminada." : "");
            pneInfo.setText(s);
        }
    }
    
    private String activado(boolean b) {
        return b ? "activado" : "desactivado";
    }
    
    private void jmiSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSalirActionPerformed
        juego.finalDePartida();
    }//GEN-LAST:event_jmiSalirActionPerformed

    private void jmiColoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiColoresActionPerformed
        ventanaColores.setVisible(true);
        ventanaColores.updateValues();
    }//GEN-LAST:event_jmiColoresActionPerformed

    private void jmiTrucosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmiTrucosMouseEntered
        jmiTrucos.setToolTipText(String.format("%s",  juego.getSettings().isPossibleCheats() ? "Los trucos no se pueden desactivar una vez habilitados." : "Los trucos no se pueden activar en modo clásico."));
    }//GEN-LAST:event_jmiTrucosMouseEntered

    private void mnuTrucosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuTrucosMouseEntered
        jmiTrucos.setSelected(juego.areCheatsEnabled());
    }//GEN-LAST:event_mnuTrucosMouseEntered

    private void jmiExitOnEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExitOnEndActionPerformed
        juego.getSettings().toggleExitOnEnd();
    }//GEN-LAST:event_jmiExitOnEndActionPerformed

    private void jmiInterfazFlechasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiInterfazFlechasActionPerformed
        juego.getSettings().togglePaintArrows();
        juego.repaint();
    }//GEN-LAST:event_jmiInterfazFlechasActionPerformed

    private void jmiInterfazHudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiInterfazHudActionPerformed
        juego.getSettings().toggleHud();
        juego.repaint();
    }//GEN-LAST:event_jmiInterfazHudActionPerformed

    private void jmiTrucosLoopStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTrucosLoopStartActionPerformed
        loopThread = new Thread() {@Override
            public void run(){LauncherRF.loopComms.Run();};
        };
        loopThread.start();
    }//GEN-LAST:event_jmiTrucosLoopStartActionPerformed

    private void jmiDarkModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDarkModeActionPerformed
        juego.getSettings().toggleDarkMode();
        juego.setBackground(juego.getSettings().isDarkModeEnabled() ? Paint.DARK_BACKGROUND : Paint.LIGHT_BACKGROUND);
        toggleDarkMode(juego.getSettings().isDarkModeEnabled());
        juego.repaint();
    }//GEN-LAST:event_jmiDarkModeActionPerformed

    private void jmiTrucosModSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTrucosModSiguienteActionPerformed
        juego.modificarSiguiente();
    }//GEN-LAST:event_jmiTrucosModSiguienteActionPerformed

    private void jmiTrucosForzarSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTrucosForzarSiguienteActionPerformed
        try {
            juego.colocarSiguiente();
            juego.repaint();
            if(!Turno.ableToMove(juego)) juego.finalDePartida();
        } catch (NullPointerException ex) {Dialog.showError("No hay hueco para otra pieza.");}
    }//GEN-LAST:event_jmiTrucosForzarSiguienteActionPerformed

    private void jmiSaveOnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSaveOnExitActionPerformed
        juego.getSettings().toggleSaveResultsToFile();
    }//GEN-LAST:event_jmiSaveOnExitActionPerformed

    private void jmiResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiResultsActionPerformed
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) 
                Desktop.getDesktop().open(SumaTres.ARCHIVO);
            else throw new IOException();
        } catch (IOException ex) {jmiResults.setEnabled(false); Dialog.showError("No se pudo abrir el archivo de resultados.");}
    }//GEN-LAST:event_jmiResultsActionPerformed

    private void jmiTrucosLoopEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTrucosLoopEndActionPerformed
        jmiTrucosLoopEnd.setSelected(true);
        loopComms.setStop();
    }//GEN-LAST:event_jmiTrucosLoopEndActionPerformed

    private void jmiTrucosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTrucosActionPerformed
        if(Dialog.confirm("Activar los trucos cambiará el multiplicador de puntos a 0.\n¿Desea continuar?")) {
            juego.enableCheats(); // Activa los trucos dentro del objeto de la partida.
            jmiTrucos.setEnabled(false); // Deshabilita la propia casilla.
            setCheatsEnabled(true); // Habilita los menús de trucos.
            jmiSaveOnExit.setEnabled(false); // Deshabilita la posibilidad de activar el guardado de resultados.
            jmiSaveOnExit.setSelected(false);
            if(juego.getSettings().isSaveResultsToFileEnabled()) juego.getSettings().toggleSaveResultsToFile();
            actualizarPneInfo(); // Actualiza el estado de los trucos en el panel de info.
        } else {jmiTrucos.setSelected(false);}
    }//GEN-LAST:event_jmiTrucosActionPerformed

    private void pneInfoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pneInfoComponentShown
        actualizarPneInfo();
    }//GEN-LAST:event_pneInfoComponentShown

    private void jmiIntefazGridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiIntefazGridActionPerformed
        juego.getSettings().toggleDrawGrid();
        juego.repaint();
    }//GEN-LAST:event_jmiIntefazGridActionPerformed

    private void jmiInterfazZonasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiInterfazZonasActionPerformed
        juego.getSettings().toggleDrawZones();
        juego.repaint();
    }//GEN-LAST:event_jmiInterfazZonasActionPerformed

    private void jmiInterfazCoordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiInterfazCoordsActionPerformed
        juego.getSettings().toggleDrawCoords();
        juego.repaint();
    }//GEN-LAST:event_jmiInterfazCoordsActionPerformed

    
    public SumaTres getPartida() {
        return this.juego;
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
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem jmiColores;
    private javax.swing.JCheckBoxMenuItem jmiDarkMode;
    private javax.swing.JCheckBoxMenuItem jmiExitOnEnd;
    private javax.swing.JCheckBoxMenuItem jmiExtrasConsole;
    private javax.swing.JCheckBoxMenuItem jmiIntefazGrid;
    private javax.swing.JMenu jmiInterfaz;
    private javax.swing.JCheckBoxMenuItem jmiInterfazCoords;
    private javax.swing.JCheckBoxMenuItem jmiInterfazFlechas;
    private javax.swing.JCheckBoxMenuItem jmiInterfazHud;
    private javax.swing.JCheckBoxMenuItem jmiInterfazZonas;
    private javax.swing.JMenuItem jmiLoad;
    private javax.swing.JMenu jmiModo;
    private javax.swing.JRadioButtonMenuItem jmiModoClassic;
    private javax.swing.JRadioButtonMenuItem jmiModoExperimental;
    private javax.swing.ButtonGroup jmiModoGroup;
    private javax.swing.JMenuItem jmiResults;
    private javax.swing.JMenuItem jmiSalir;
    private javax.swing.JMenuItem jmiSave;
    private javax.swing.JCheckBoxMenuItem jmiSaveOnExit;
    private javax.swing.JCheckBoxMenuItem jmiTrucos;
    private javax.swing.JMenuItem jmiTrucosAñadir;
    private javax.swing.JMenuItem jmiTrucosForzarSiguiente;
    private javax.swing.JMenu jmiTrucosLoop;
    private javax.swing.JMenuItem jmiTrucosLoopEnd;
    private javax.swing.JMenuItem jmiTrucosLoopStart;
    private javax.swing.JMenuItem jmiTrucosModSiguiente;
    private javax.swing.JMenuItem jmiTrucosPuntos;
    private javax.swing.JMenuItem jmiTrucosUndo;
    private javax.swing.JMenu mnuArchivo;
    private javax.swing.JMenu mnuOpciones;
    private javax.swing.JMenu mnuTrucos;
    private javax.swing.JTextPane pneInfo;
    // End of variables declaration//GEN-END:variables
}
