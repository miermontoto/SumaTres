package handler;
import game.SumaTres;

import java.awt.event.KeyEvent;


public class Keyboard {
	
	public static final String validClassicKeys = "wasd";
	public static final String validExperimentalKeys = "qweadzxc";
	
	
	/*
	 * Al menos UNO de los métodos que utilize el control debe de encargarse de volverlo
	 * a false una vez que termine.
	 */

	/**
	 * Constrctor generado para cumplir con SonarLint:S1118.
	 * 
	 * @see <a href="https://sonarcloud.io/organizations/default/rules?languages=java&open=java%3AS1118&q=S1118">
	 * 		Regla SonarLint:S1118 </a>
	 */
	private Keyboard() {
            throw new IllegalStateException("Event handling class");
	}
	
	public static void keyboardHandler(SumaTres s, KeyEvent e) {
            if((s.getMode() && (validExperimentalKeys.indexOf(e.getKeyChar())) >= 0) ||
                (!s.getMode() && (validClassicKeys.indexOf(e.getKeyChar()) >= 0))) s.jugada(e.getKeyChar());

	}

	/**
	 * Recive pulsaciones de teclas del usuario. Si el usuario pulsa una tecla correspondiente a un
	 * movimiento, se ejecuta dicha jugada. <p>
	 * Si se está jugando en el modo experimental, se juega con las teclas (Q/W/E/D/C/X/Z/A), formando
	 * un cuadrado en el teclado que deja la tecla 'S' en medio. Este cuadrado se corresponde con las
	 * direcciones que se pueden elegir para la jugada.
	 * 
	 * @param s Partida en la que se está jugando.
	 * @param key Caracter de la tecla pulsada (previamente filtrado).
	 */
	private static void gameplayHandler(SumaTres s, char key) {
			}
}


