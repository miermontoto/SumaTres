package handler;
import game.SumaTres;

import java.awt.event.KeyEvent;


public class Keyboard {
	
	public static final String VALID_CLASSIC_KEYS = "wasd";
	public static final String VALID_EXPERIMENTAL_KEYS = "qweadzxc";

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
            if((s.getSettings().isDiagonalMovementEnabled() && (VALID_EXPERIMENTAL_KEYS.indexOf(e.getKeyChar())) >= 0) ||
                (!s.getSettings().isDiagonalMovementEnabled() && (VALID_CLASSIC_KEYS.indexOf(e.getKeyChar()) >= 0))) s.jugada(e.getKeyChar());

	}
}


