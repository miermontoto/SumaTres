package handler;
import game.SumaTres;

import java.awt.event.KeyEvent;

/**
 * Clase manejadora de eventos de teclado del programa SumaTres. <p>
 * El funcionamiento de todos los objetos de esta clase se puede desactivar
 * mediante el flag {@code handlingEnabled}, a través del método
 * {@link disableHandling()}.
 *
 * @see game.SumaTres
 * @see game.LauncherRF
 * @author Juan Mier
 */
public class Keyboard {

	public static final String VALID_CLASSIC_KEYS = "wasd";
	public static final String VALID_EXPERIMENTAL_KEYS = "qweadzxc";
    private static boolean handlingEnabled = true;

    private final SumaTres game;
    private final KeyEvent event;

    public static void disableHandling() {handlingEnabled = false;}
    public static void enableHandling() {handlingEnabled = true;}

    /**
     * Constructor por defecto. Requiere el objeto de la partida que se está
     * jugando y el evento de teclado generado.
     *
     * @see SumaTres
     * @see KeyEvent
     *
     * @param gameObject Objeto de la clase SumaTres de la partida actual.
     * @param eventObject Evento de teclado que se quiera tratar.
     */
	public Keyboard(SumaTres gameObject, KeyEvent eventObject) {
        game = gameObject;
        event = eventObject;
	}

    /**
     * Método principal de la clase. Realiza jugadas en base a la tecla pulsada.
     * Las teclas válidas se seleccionan mediante {@link validKeychar()}
     * y {@link validKeycode()}. <p>
     * Traduce las jugadas por flechas mediante {@link translateKeycode()}.
     */
	public void keyboardHandler() {
        if(!handlingEnabled) return;

        if(validKeychar()) game.jugada(event.getKeyChar());
        else if(validKeycode()) game.jugada(translateKeycode());
	}

    /**
     * Método que define si el evento de una acción de teclado se corresponde
     * con el de una jugada válida. En el modo clásico, las teclas válidas son
     * "wasd". En modo experimental, son "qweadzxc". <p>
     * Para validar el caracter, busca su índice en unas constantes de la clase.
     *
     * @return Valor booleano que define la validez.
     */
    private boolean validKeychar() {
        return (game.getSettings().getStatus("diagonalMovement") && (VALID_EXPERIMENTAL_KEYS.indexOf(event.getKeyChar())) >= 0) ||
                (!game.getSettings().getStatus("diagonalMovement") && (VALID_CLASSIC_KEYS.indexOf(event.getKeyChar()) >= 0));
    }

    /**
     * Método que define si el evento de una acción de teclado se corresponde
     * con el de una tecla válida. Actualmente solo se utilizan las flechas,
     * por lo que devolverá cierto si la tecla pulsada fue una flecha.
     * @return Valor booleano que define la validez.
     */
    private boolean validKeycode() {
        return event.getKeyCode() <= 40 && event.getKeyCode() >= 37;
    }

    /**
     * Método que traduce los eventos de teclados correspondientes de las
     * flechas a "wasd". Permite traducir jugadas al sistema ya en uso.
     *
     * @return Caracter correspondiente a la dirección del movimiento.
     */
    private char translateKeycode() {
        switch(event.getKeyCode()) {
            case KeyEvent.VK_UP: return 'w';
            case KeyEvent.VK_DOWN: return 's';
            case KeyEvent.VK_LEFT: return 'a';
            case KeyEvent.VK_RIGHT: return 'd';
            default: return '\u0000';
        }
    }
}


