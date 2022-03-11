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
        
        private final SumaTres s;
        private final KeyEvent e;
        
        public static void disableHandling() {handlingEnabled = false;}

        /**
         * Constructor por defecto. Requiere el objeto de la partida que se está
         * jugando y el evento de teclado generado.
         * 
         * @see SumaTres
         * @see KeyEvent
         * 
         * @param si Objeto de la clase SumaTres de la partida actual.
         * @param ei Evento de teclado que se quiera tratar.
         */
	public Keyboard(SumaTres si, KeyEvent ei) {
            s = si;
            e = ei;
	}
	
        /**
         * Método principal de la clase. Realiza jugadas en base a la tecla pulsada.
         * Las teclas válidas se seleccionan mediante {@link validKeychar()}
         * y {@link validKeycode()}. <p>
         * Traduce las jugadas por flechas mediante {@link translateKeycode()}.
         */
	public void keyboardHandler() {
            if(validKeychar() && handlingEnabled) s.jugada(e.getKeyChar());
            else if(validKeycode() && handlingEnabled) s.jugada(translateKeycode());
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
            return (s.getSettings().isDiagonalMovementEnabled() && (VALID_EXPERIMENTAL_KEYS.indexOf(e.getKeyChar())) >= 0) ||
                    (!s.getSettings().isDiagonalMovementEnabled() && (VALID_CLASSIC_KEYS.indexOf(e.getKeyChar()) >= 0));
        }
        
        /**
         * Método que define si el evento de una acción de teclado se corresponde
         * con el de una tecla válida. Actualmente solo se utilizan las flechas,
         * por lo que devolverá cierto si la tecla pulsada fue una flecha.
         * @return Valor booleano que define la validez.
         */
        private boolean validKeycode() {
            return e.getKeyCode() <= 40 && e.getKeyCode() >= 37;
        }
        
        /**
         * Método que traduce los eventos de teclados correspondientes de las
         * flechas a "wasd". Permite traducir jugadas al sistema ya en uso.
         * 
         * @return Caracter correspondiente a la dirección del movimiento.
         */
        private char translateKeycode() {
            int code = e.getKeyCode();
            if(code == KeyEvent.VK_UP) return 'w';
            if(code == KeyEvent.VK_DOWN) return 's';
            if(code == KeyEvent.VK_LEFT) return 'a';
            if(code == KeyEvent.VK_RIGHT) return 'd';
            return '\u0000'; // carácter nulo.
        }
}


