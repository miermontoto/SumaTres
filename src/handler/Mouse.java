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

    private final SumaTres game;
    private final MouseEvent event;

    public static void disableHandling() {handlingEnabled = false;}
    public static void enableHandling() {handlingEnabled = true;}

    /**
     * Constructor por defecto. Requiere el objeto de la partida que se está
     * jugando y el evento de ratón generado.
     *
     * @see SumaTres
     * @see MouseEvent
     *
     * @param gameObject Objeto de la clase SumaTres de la partida actual.
     * @param eventObject Evento de ratón que se quiera tratar.
     */
    public Mouse(SumaTres gameObject, MouseEvent eventObject) {
        game = gameObject;
        event = eventObject;
    }

    /**
     * Método principal de la clase.
     * Filtra las jugadas válidas, es decir, las que estén dentro del frame de
     * la partida (mediante {@link inFrameClick()}) pero que no estén dentro del
     * tablero (mediante {@link inBoardClick()}). Solo se tratan clicks izquierdos.
     * <p> Las jugadas las realiza {@link jugadaHandler()}.
     */
    public void mouseHandler() {
        if(event.getButton() == MouseEvent.BUTTON1 && handlingEnabled
                && inFrameClick() && !inBoardClick()) jugadaHandler();
    }

    /**
     * Devuelve verdadero si el click se ha hecho dentro del frame de la partida o no.
     * Se le llama "frame" al todo el área del programa visible excepto la zona
     * de información.
     * @return Valor booleano.
     */
    private boolean inFrameClick() {
        return event.getY() <= (Graphic.lateralSize(game.getSettings().getX()) + Paint.MAIN_SPACER);
    }

    /**
     * Devuelve verdadero si el click se ha hecho dentro del tablero de la partida o no.
     * @return Valor booleano.
     */
    private boolean inBoardClick() {
        return event.getX() > Paint.MAIN_SPACER && event.getY() > Paint.MAIN_SPACER &&
                event.getX() <= Graphic.lateralSize(game.getSettings().getY()) &&
                event.getY() <= Graphic.lateralSize(game.getSettings().getX());
    }

    /**
     * Método que traduce los clics de ratón a jugadas.
     * Dependiendo de dónde ha hecho click el usuario, realiza una jugada en la
     * dirección que indique la flecha en tablero. <p>
     * Se puede ver visualmente las zonas de las jugadas durante la partida
     * habilitando la opción <i>Dibujar zonas</i>.
     */
    private void jugadaHandler() {
        int x = event.getX();
        int y = event.getY();

        int limitX = Graphic.lateralSize(game.getSettings().getX());
        int limitY = Graphic.lateralSize(game.getSettings().getY());
        boolean diagonal = game.getSettings().getStatus("diagonalMovement");

        if(x <= Paint.MAIN_SPACER) { // Clicks en la parte superior del frame.
            if(y <= Paint.MAIN_SPACER && diagonal) game.jugada('q');
            else {
                if(y <= limitY) game.jugada('a');
                else if(diagonal && y <= limitY + Paint.MAIN_SPACER) game.jugada('z');
            }
        } else { // Clicks en el resto del frame que no es la parte superior.
            if(x >= limitX) {
                if(y <= Paint.MAIN_SPACER && diagonal) game.jugada('e');
                else {
                    if(y <= limitY) game.jugada('d');
                    else if(diagonal && y <= limitY + Paint.MAIN_SPACER) game.jugada('c');
                }
            } else {
                if(y <= Paint.MAIN_SPACER) game.jugada('w');
                else if(y <= limitY + Paint.MAIN_SPACER && limitY <= y) game.jugada('s');
            }
        }
    }
}
