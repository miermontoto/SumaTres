package util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import game.SumaTres;
import obj.Pieza;
import obj.Settings;


public class Paint {

    public static final int SPOT_SPACER    = (int) (3  * Graphic.SCALE); // Espacio entre piezas.
    public static final int BOARD_SPACER   = (int) (6  * Graphic.SCALE); // Espacio entre el borde del tablero y las piezas.
    public static final int ROUND_DIAMETER = (int) (10 * Graphic.SCALE); // Radio del diámetro del arco.
    public static final int SQUARE_SIZE    = (int) (40 * Graphic.SCALE); // Tamaño de las piezas.
    public static final int MAIN_SPACER    = (int) (50 * Graphic.SCALE); // Espacio entre el tablero y el borde de la pantalla.

    public static final int STROKE_SIZE    = 3; // Tamaño de la "brocha". (i)
    
    public static final Color LIGHT_BACKGROUND = new Color(214, 217, 223);
    public static final Color DARK_BACKGROUND = new Color(60, 63, 65);
    public static final Color BOARD_COLOR = new Color(242, 242, 242);
    public static final Color GRID_COLOR = new Color(60, 63, 65);

    private final Graphics2D g;
    private final SumaTres s;
    
    private final int lateralSizeX;
    private final int lateralSizeY;
    private final int defineX;
    private final int defineY;
    private final Settings op;


    public Paint(SumaTres si, Graphics gi) {
        g = (Graphics2D) gi;
        s = si;
        
        op = si.getSettings();
        
        lateralSizeX = Graphic.lateralSize(op.getY());
        lateralSizeY = Graphic.lateralSize(op.getX());
        defineX = Graphic.defineX(si);
        defineY = Graphic.defineY(si);
    }

    /**
     * Método que optimiza el cambio del tamaño de la fuente. Puesto que siempre se utiliza 'Helvetica' como
     * fuente, no es necesario cambiar nada más y se acorta más de esta manera. <p>
     * Se utiliza Helvetica como fuente porque se encuentra en casi cualquier sistema operativo hoy en día y
     * además está mejor diseñada y adaptada que Arial.
     * 
     * @param size Tamaño de fuente a establecer
     */
    private void setFontSize(int size) {
        g.setFont(new Font("Helvetica", Font.PLAIN, size));
    }

    /**
     * Método que pinta la partida en la aplicación gráfica. El programa NO se basa en
     * {@link #game.SumaTres.toString()} para pintar el tablero, no siempre existe la misma
     * información en ambas salidas.
     * <ul>
     * <li>Para pintar las flechas, se utiliza {@link #pintarFlechas(Graphics)}. </li>
     * <li>Para pintar el tablero, se utiliza {@link #pintarTablero(Graphics)}. </li>
     * <li>Para pintar las fichas, se utiliza {@link #pintarFichas(Graphics)}. </li>
     * <li>Para pintar la información, se utiliza {@link #pintarInfo(Graphics)}. </li>
     * </ul>
     */
    public void paint() {

        var rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        rh.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        rh.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        rh.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        /*
         *  Gracias al antialiasing,  los bordes de las fichas, son mucho más suaves y
         *  aptos para una pantalla de gran dpi y resolución. No afecta al rendimiento del programa.
         *  Algunas de estas mejoras no suponen una clara diferencia en la calidad visual del juego.
         */

        g.setRenderingHints(rh);
        g.setStroke(new BasicStroke(STROKE_SIZE));

        pintarTablero();
        pintarFichas();
        if(op.isPaintArrowsEnabled()) pintarFlechas();
        if(op.isHudEnabled()) pintarInfo();
        if(op.isDrawZonesEnabled()) pintarZonas();
        if(op.isDrawGridEnabled()) pintarGrid();
        if(op.isDrawCoordsEnabled()) pintarCoords();
    }

    /**
     * Método que imprime las flechas que indican dónde hacer click para realizar un movimiento
     * y en qué dirección, aunque sea bastante intuitivo de por sí. Los cálculos de la posición
     * de las flechas son aproximados a ojo, pero se mantienen en cualquier con cualquier
     * combinación de columnas, filas y resolución de pantalla. <p> Si está activado el modo
     * experimental, se pintan las flechas que delimitan el movimiento en diagonal. Se han
     * escogido estos caracteres en concreto porque representan bastante bien el carácter del
     * movimiento en diagonal: se mueve hasta encontrarse con otra pieza o el borde del tablero,
     * no hasta estar en la posición más extrema en la dirección indicada. 
     */
    private void pintarFlechas() {
        
        int topRow = MAIN_SPACER * 7 / 12;
        int middleRow = (lateralSizeY - MAIN_SPACER) / 2 + MAIN_SPACER;
        int bottomRow = lateralSizeY + topRow;
        
        int leftColumn = MAIN_SPACER * 9 / 24;
        int middleColumn = defineX / 2;
        int rightColumn = defineX - MAIN_SPACER * 2 / 3;
        
        g.setColor(op.isDarkModeEnabled() ? BOARD_COLOR : Color.blue);
        setFontSize(20);
        
        g.drawString("\u2190", leftColumn, middleRow);   // flecha izquierda
        g.drawString("\u2191", middleColumn, topRow);    // flecha arriba
        g.drawString("\u2192", rightColumn, middleRow);  // flecha derecha
        g.drawString("\u2193", middleColumn, bottomRow); // flecha abajo
        
        if(op.isDiagonalMovementEnabled()) {
            g.drawString("\u2B76", leftColumn, topRow);     // arriba izquierda
            g.drawString("\u2B77", rightColumn, topRow);    // arriba derecha
            g.drawString("\u2B78", rightColumn, bottomRow); // abajo derecha
            g.drawString("\u2B79", leftColumn, bottomRow);  // abajo izquierda
        }
    }

    /**
     * Método sencillo que imprime el tablero. El tablero no es más que un rectángulo
     * redondeado blanco.
     */
    private void pintarTablero() {
        g.setColor(BOARD_COLOR);
        g.fillRoundRect(MAIN_SPACER, MAIN_SPACER,
            lateralSizeX - MAIN_SPACER,
            lateralSizeY - MAIN_SPACER,
            ROUND_DIAMETER, ROUND_DIAMETER);
    }

    /**
     * Método que imprime información sobre la partida como los turnos, los puntos o la
     * siguiente pieza en pantalla.
     */
    private void pintarInfo() {
        g.setColor(op.isDarkModeEnabled() ? BOARD_COLOR : new Color(48, 50, 52));
        setFontSize(15);

        // Siguiente:
        g.drawString("Siguiente:", MAIN_SPACER * 2 - 75, lateralSizeY + MAIN_SPACER * 3 / 2);
        // Turnos:
        g.drawString(String.format("[%d]", s.getTurnos()), defineX / 2, lateralSizeY + MAIN_SPACER * 3 / 2);
        // Puntos:
        /*
         *  Primero se calcula si es necesario decrecer el tamaño de la fuente dependiendo
         *  de la longitud de los puntos de la ficha. Es ligeramente inncesario ya que en
         *  rara ocasión el usuario va a llegar a cantidades tan altas, pero eh.
         */
        setFontSize(s.getPuntos() >= 10000000 ? 12 : 15);
        g.drawString(String.format("Puntos: %d", s.getPuntos()), defineX - 2 * MAIN_SPACER,
            lateralSizeY + MAIN_SPACER * 3 / 2);

        // Pieza siguiente:
        g.setColor(Pieza.getColores().get(s.getSiguiente()));
        g.fillRoundRect(MAIN_SPACER * 2, lateralSizeY + MAIN_SPACER,
            SQUARE_SIZE, SQUARE_SIZE,
            ROUND_DIAMETER, ROUND_DIAMETER);
        g.setColor(BOARD_COLOR);
        int desiredFontSize = s.getSiguiente() >= 350000 ? 10 : 19 - (String.valueOf(s.getSiguiente()).length() - 1);
        setFontSize(desiredFontSize); // Se desplaza a la derecha cuando la pieza pasa a tener más de un dígito.
        int displacer = 2*(String.valueOf(s.getSiguiente()).length() - 1);
        g.drawString(String.valueOf(s.getSiguiente()), 
                MAIN_SPACER * (112 - displacer) / 48,
                lateralSizeY + MAIN_SPACER * 3 / 2);
        
        // Final de partida:
        if(s.isFinished()) {
            g.setColor(new Color(0f,0f,0f,.3f));
            int size = (lateralSizeY - MAIN_SPACER) / 3;
            g.fillRect(0, (size + MAIN_SPACER), defineX, size * 2);
            
            int wx = defineX * 13 / 48;
            int wy = lateralSizeY / 2 + MAIN_SPACER*5/6;
            String endMsg = "FINAL";
            g.setFont(new Font("Helvetica", Font.BOLD, 70));
            g.setColor(Color.black);
            g.drawString(endMsg, wx + 1, wy);
            g.drawString(endMsg, wx - 1, wy);
            g.drawString(endMsg, wx, wy + 1);
            g.drawString(endMsg, wx, wy - 1);
            g.setColor(Color.red);
            g.drawString(endMsg, wx, wy);
        }
    }
    
    private void pintarZonas() {
        g.setColor(op.isDarkModeEnabled() ? BOARD_COLOR : Color.DARK_GRAY);
        g.drawLine(MAIN_SPACER, 0, MAIN_SPACER, lateralSizeY + MAIN_SPACER);
        g.drawLine(lateralSizeX, 0, lateralSizeX, lateralSizeY + MAIN_SPACER);
        g.drawLine(0, MAIN_SPACER, defineX, MAIN_SPACER);
        g.drawLine(0, lateralSizeY, defineX, lateralSizeY);
        g.drawLine(0, lateralSizeY + MAIN_SPACER, defineX, lateralSizeY + MAIN_SPACER);
    }
    
    private void pintarGrid() {
        g.setStroke(new BasicStroke(1));
        g.setColor(GRID_COLOR);
        for(int i = 0; i <= op.getX(); i++) g.drawLine(MAIN_SPACER, MAIN_SPACER + BOARD_SPACER + i * (SQUARE_SIZE + SPOT_SPACER) - SPOT_SPACER / 2, lateralSizeX, MAIN_SPACER + BOARD_SPACER + i * (SQUARE_SIZE + SPOT_SPACER) - SPOT_SPACER / 2);
        for(int i = 0; i <= op.getY(); i++) g.drawLine(MAIN_SPACER + BOARD_SPACER + i * (SQUARE_SIZE + SPOT_SPACER) - SPOT_SPACER / 2, MAIN_SPACER, MAIN_SPACER + BOARD_SPACER + i * (SQUARE_SIZE + SPOT_SPACER) - SPOT_SPACER / 2, lateralSizeY);
        g.setStroke(new BasicStroke(STROKE_SIZE));
    }
    
    private void pintarCoords() {
        g.setColor(Color.BLACK);
        setFontSize(11);
        for(int i = 0; i < op.getX(); i++) {
            for(int j = 0; j < op.getY(); j++) {
                g.drawString(String.format("[%d, %d]", i, j), 
                        MAIN_SPACER + BOARD_SPACER + j * (SPOT_SPACER + SQUARE_SIZE), 
                        MAIN_SPACER + 2*BOARD_SPACER + i * (SPOT_SPACER + SQUARE_SIZE));
            }
        }
    }

    /**
     * Método que pinta las fichas en pantalla.
     * <p>
     * Supuestamente, después de pintar el tablero mediante {@link #pintarTablero(Graphics)} se
     * pintan las fichas. Para esto, se examina el tablero entero. Si la posición en el tablero
     * tiene valor <code>0</code>, no se pinta nada.
     * <p>
     * Para obtener los colores con los que se va a pintar las piezas, se utiliza un Map con
     * los valores de las piezas como claves y los colores como valores.
     * <p>
     * Dependiendo de la cantidad de dígitos que tiene una ficha, su valor se imprime desplazado
     * hacia la izquierda para centrar el valor de las fichas más grandes.
     */
    private void pintarFichas() {
        int limit = 0;
        for (int i = 0; i < s.getTablero().getColumns() && limit < s.getTablero().amount(); i++)
            for (int j = 0; j < s.getTablero().getRows() && limit < s.getTablero().amount(); j++) { // Se recorre el tablero.
                Pieza next = s.getTablero().getPieza(i, j);
                if (!next.isEmpty()) { // Al detectarse una pieza, se obtiene su color referente.  
                    limit++;
                    
                    // Se pinta la pieza en sí.
                    g.setColor(next.getColor());
                    g.fillRoundRect(MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * j,
                        MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * i, SQUARE_SIZE, SQUARE_SIZE,
                        ROUND_DIAMETER, ROUND_DIAMETER);

                    // Si la pieza es demasiado clara, se le pinta un reborde para que se aprecie.
                    if(next.isBrillante()) {
                        g.setStroke(new BasicStroke(1));
                        g.setColor(Color.gray);
                        g.drawRoundRect(MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * j,
                                MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * i, SQUARE_SIZE, SQUARE_SIZE,
                                ROUND_DIAMETER, ROUND_DIAMETER);
                        g.setStroke(new BasicStroke(STROKE_SIZE));
                    }

                    // Por último, se pinta el valor de la ficha.
                    // Si la luminosidad pasa de un cierto valor, el color de la fuente del valor
                    // de la ficha debería ser negro, de lo contrario es blanco.
                    g.setColor(next.isBrillante() ? Color.black : Color.white);

                    // Se establece un tamaño de fuente en función de los dígitos de la ficha.
                    int desiredFontSize = 19 - (String.valueOf(next.getValor()).length() - 1);
                    setFontSize(s.getTab(i, j) >= 350000 ? 10 : desiredFontSize);
                    int sizer = s.getTab(i, j) >= 100000 ? (13 - (String.valueOf(next.getValor()).length()-1)) :
                            (13 - (2*(String.valueOf(next.getValor()).length()-1)));
                    g.drawString(String.valueOf(next.getValor()),
                        MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * j + SQUARE_SIZE * sizer / 32,
                        SQUARE_SIZE * 5 / 8 + MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * i);
                    // Dependiendo del tamaño de la pieza, se desplaza ligeramente a la izquierda para que siga centrada
                    // en concordancia con el resto de piezas.
            }
        }
        
        // Si no se está en el primer turno, se imprime un rectángulo alrededor de la nueva ficha
        // para diferenciarla. (debería haber alguna manera mejor de señalarla?)
        if(s.getTurnos() > 1 && s.getWarning()[0] != -1) {
            g.setColor(Color.yellow);
            g.drawRoundRect(MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * s.getWarning()[1],
                MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * s.getWarning()[0], SQUARE_SIZE, SQUARE_SIZE,
                ROUND_DIAMETER, ROUND_DIAMETER);
        }

        if(s.getSelected()[0] != -1) {
            g.setColor(Color.BLACK);
            g.drawRoundRect(MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * s.getSelected()[1],
                MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * s.getSelected()[0], SQUARE_SIZE, SQUARE_SIZE,
                ROUND_DIAMETER, ROUND_DIAMETER);
            g.drawString("?" ,
                        MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * s.getSelected()[1] + SQUARE_SIZE * 13 / 32,
                        SQUARE_SIZE * 5 / 8 + MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * s.getSelected()[0]);
        }
    }
}
