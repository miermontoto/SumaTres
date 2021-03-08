package handler;
import game.SumaTres;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class Keyboard {

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
	public static void keyboardHandler(SumaTres s, int code, boolean ctrl) {
		switch (code) {
		case KeyEvent.VK_PAGE_DOWN: // Esto rompe varios paradigmas de la programación.
			if(ctrl) {
				while (!s.getMode()) {
					s.jugada('w');
					s.jugada('a');
					s.jugada('s');
					s.jugada('d');
				}
				while(s.getMode() && s.cheatsUsed()) {
					s.jugada('w');
					s.jugada('q');
					s.jugada('a');
					s.jugada('z');
					s.jugada('s');
					s.jugada('c');
					s.jugada('d');
					s.jugada('e');
				}
			}
			break; // break no accesible???
		case KeyEvent.VK_ESCAPE: // Si se confirma, se termina la partida.
			if (JOptionPane.showConfirmDialog(null, "¿Desea salir de la partida?", "SumaTres", JOptionPane.YES_NO_OPTION)
					== JOptionPane.YES_OPTION) s.finalDePartida();
			break;
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			s.jugada('w');
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			s.jugada('a');
			break;
		case KeyEvent.VK_S:
			if(!s.getMode()) s.jugada('s'); break;
		case KeyEvent.VK_X:
			if(!s.getMode()) break;
		case KeyEvent.VK_DOWN:
			s.jugada('s');
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			s.jugada('d');
			break;
		case KeyEvent.VK_C:
			if(s.getMode()) s.jugada('c');
			break;
		case KeyEvent.VK_Z:
			if(s.getMode()) s.jugada('z');
			break;
		case KeyEvent.VK_Q:
			if(s.getMode()) s.jugada('q');
			break;
		case KeyEvent.VK_E:
			if(s.getMode()) s.jugada('e');
			break;
		default:
			break;	// En cualquier otro caso, no se hace nada.
		}
	}
}
