package handler;

import java.awt.event.MouseEvent;

import game.SumaTres;
import util.visual.Graphic;
import util.visual.Paint;

/**
 * Clase manejadora de eventos de ratón del programa SumaTres. <p>
 * El funcionamiento de todos los objetos de esta clase se puede desactivar
 * mediante el flag {@code handlingEnabled}, a través del método
 * {@link disableHandling()}.
 * 
 * @see game.SumaTres
 * @see game.LauncherRF
 * @author Juan Mier
 */
public class Mouse {
    
    private static boolean handlingEnabled = true;
    
    private final SumaTres s;
    private final MouseEvent e;
    
    public static void disableHandling() {handlingEnabled = false;}
    public static void enableHandling() {handlingEnabled = true;}

    /**
     * Constructor por defecto. Requiere el objeto de la partida que se está
     * jugando y el evento de ratón generado.
     * 
     * @see SumaTres
     * @see MouseEvent
     * 
     * @param si Objeto de la clase SumaTres de la partida actual.
     * @param ei Evento de ratón que se quiera tratar.
     */
    public Mouse(SumaTres si, MouseEvent ei) {
        s = si;
        e = ei;
    }
    
    /**
     * Método principal de la clase.
     * Filtra las jugadas válidas, es decir, las que estén dentro del frame de
     * la partida (mediante {@link inFrameClick()}) pero que no estén dentro del
     * tablero (mediante {@link inBoardClick()}). Solo se tratan clicks izquierdos.
     * <p> Las jugadas las realiza {@link jugadaHandler()}.
     */
    public void mouseHandler() {
        //System.out.printf("%d %d (%d) %n", e.getX(), e.getY(), Paint.MAIN_SPACER);
        if(e.getButton() == MouseEvent.BUTTON1 && handlingEnabled 
                && inFrameClick() && !inBoardClick()) jugadaHandler();
    }
    
    /**
     * Devuelve si el click se ha hecho dentro del frame de la partida o no.
     * Se le llama "frame" al todo el área del programa visible excepto la zona
     * de información.
     * @return Valor booleano.
     */
    private boolean inFrameClick() {
        return e.getY() <= (Graphic.lateralSize(s.getSettings().getX()) + Paint.MAIN_SPACER);
    }
    
    /**
     * Devuelve si el click se ha hecho dentro del tablero de la partida o no.
     * @return Valor booleano.
     */
    private boolean inBoardClick() {
        return e.getX() > Paint.MAIN_SPACER && e.getY() > Paint.MAIN_SPACER &&
                e.getX() <= Graphic.lateralSize(s.getSettings().getY()) && 
                e.getY() <= Graphic.lateralSize(s.getSettings().getX());
    }
    
    /**
     * Método que traduce los clics de ratón a jugadas.
     * Dependiendo de dónde ha hecho click el usuario, realiza una jugada en la
     * dirección que indique la flecha en tablero. <p>
     * Se puede ver visualmente las zonas de las jugadas durante la partida
     * habilitando la opción <i>Dibujar zonas</i>.
     */
    private void jugadaHandler() {
        int x = e.getX();
        int y = e.getY();

        int limitX = Graphic.lateralSize(s.getSettings().getX());
        int limitY = Graphic.lateralSize(s.getSettings().getY());
        boolean diagonal = s.getSettings().getStatus("diagonalMovement");

        if(x <= Paint.MAIN_SPACER) { 
            if(y <= Paint.MAIN_SPACER && diagonal) s.jugada('q');
            else {
                if(y <= limitY) s.jugada('a');
                else if(diagonal && y <= limitY + Paint.MAIN_SPACER) s.jugada('z');
            }
        } else {
            if(x >= limitX) {
                if(y <= Paint.MAIN_SPACER && diagonal) s.jugada('e');
                else {
                    if(y <= limitY) s.jugada('d');
                    else if(diagonal && y <= limitY + Paint.MAIN_SPACER) s.jugada('c');
                }
            } else {
                if(y <= Paint.MAIN_SPACER) s.jugada('w');
                else if(y <= limitY + Paint.MAIN_SPACER && limitY <= y) s.jugada('s');
            }
        }
    }
}
