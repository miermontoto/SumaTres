package handler;
import game.SumaTres;

import java.awt.event.KeyEvent;


public class Keyboard {
	
	public static final String VALID_CLASSIC_KEYS = "wasd";
	public static final String VALID_EXPERIMENTAL_KEYS = "qweadzxc";
        private static boolean handlingEnabled = true;
        
        private final SumaTres s;
        private final KeyEvent e;
        
        public static void disableHandling() {handlingEnabled = false;}

	public Keyboard(SumaTres si, KeyEvent ei) {
            s = si;
            e = ei;
	}
	
	public void keyboardHandler() {
            if(validKeystroke() && handlingEnabled) s.jugada(e.getKeyChar());
	}
        
        private boolean validKeystroke() {
            return (s.getSettings().isDiagonalMovementEnabled() && (VALID_EXPERIMENTAL_KEYS.indexOf(e.getKeyChar())) >= 0) ||
                    (!s.getSettings().isDiagonalMovementEnabled() && (VALID_CLASSIC_KEYS.indexOf(e.getKeyChar()) >= 0));
        }
}


