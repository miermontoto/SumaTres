package handler;
import game.SumaTres;
import java.awt.List;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;


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
            else if(validKeycode() && handlingEnabled) s.jugada(translateKeycode());
	}
        
        private boolean validKeystroke() {
            return (s.getSettings().isDiagonalMovementEnabled() && (VALID_EXPERIMENTAL_KEYS.indexOf(e.getKeyChar())) >= 0) ||
                    (!s.getSettings().isDiagonalMovementEnabled() && (VALID_CLASSIC_KEYS.indexOf(e.getKeyChar()) >= 0));
        }
        
        private boolean validKeycode() {
            return e.getKeyCode() <= 40 && e.getKeyCode() >= 37;
        }
        
        private char translateKeycode() {
            int code = e.getKeyCode();
            if(code == KeyEvent.VK_UP) return 'w';
            if(code == KeyEvent.VK_DOWN) return 's';
            if(code == KeyEvent.VK_LEFT) return 'a';
            if(code == KeyEvent.VK_RIGHT) return 'd';
            return '\u0000'; // carácter nulo.
        }
}


