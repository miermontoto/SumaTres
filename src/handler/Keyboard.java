package handler;
import game.SumaTres;
import util.Dialog;

import java.awt.event.KeyEvent;


public class Keyboard {
	
	public static final String validClassicKeys = "wasd";
	public static final String validExperimentalKeys = "qweadzxc";
	public static final String validKeys = validClassicKeys + validExperimentalKeys;

	/**
	 * Constrctor generado para cumplir con SonarLint:S1118.
	 * 
	 * @see <a href="https://sonarcloud.io/organizations/default/rules?languages=java&open=java%3AS1118&q=S1118">
	 * 		Regla SonarLint:S1118 </a>
	 */
	private Keyboard() {
		throw new IllegalStateException("Event handling class");
	}

	/**
	 * Recive pulsaciones de teclas del usuario. Si el usuario pulsa una tecla correspondiente a un
	 * movimiento (W/A/S/D o ARRIBA/IZQUIERDA/ABAJO/DERECHA), se ejecuta dicha jugada. <p>
	 * Con el objetivo de probar el rendimiento en circunstancias extremas del programa, al pulsar
	 * AVPAG/PAGE_DOWN junto a la tecla 'control', se mandan jugadas constantes repetidas hasta que se
	 * termina el programa. Para que esto funcione, es necesario que los trucos hayan sido activados
	 * con anterioridad. <p>
	 * Si se pulsa la tecla escape, salta un prompt que le pregunta al usuario si desea terminar la
	 * partda. En caso afirmativo, se ejecuta {@link #game.SumaTres.finalDePartida()} para finalizar la
	 * partida en el estado actual del tablero. <p>
	 * Si se está jugando en el modo experimental, se juega con las teclas (Q/W/E/D/C/X/Z/A), formando
	 * un cuadrado en el teclado que deja la tecla 'S' en medio. Este cuadrado se corresponde con las
	 * direcciones que se pueden elegir para la jugada.
	 * 
	 * @see <a href="https://rules.sonarsource.com/java/RSPEC-131"> SonarLint: RSPEC-131 </a>
	 * 		<blockquote> The requirement for a final default clause is defensive programming. The
	 * 		clause should either take appropriate action, or contain a suitable comment as to why
	 * 		no action is taken. </blockquote>
	 */
	public static void keyboardHandler(SumaTres s, char key) {
		if((s.getMode() && validExperimentalKeys.indexOf(key) >= 0) ||
			   (!s.getMode() && validClassicKeys.indexOf(key) >= 0)) s.jugada(key);
	}
	
	public static void keyboardHandler(SumaTres s, KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
			while (e.isControlDown()) {
				s.jugada('w');
				if(s.getMode()) s.jugada('q');
				s.jugada('a');
				if(s.getMode()) s.jugada('z');
				if(s.getMode()) s.jugada('x');
				else 			s.jugada('s');
				if(s.getMode()) s.jugada('c');
				s.jugada('d');
				if(s.getMode()) s.jugada('e');
			}

		} else if(e.getKeyCode() == KeyEvent.VK_ESCAPE &&
				Dialog.confirm("¿Desea salir de la partida?")) s.finalDePartida();
	}
}


