package handler;
import game.SumaTres;
import util.Dialog;

import java.awt.event.KeyEvent;


public class Keyboard {
	
	public static final String validClassicKeys = "wasd";
	public static final String validExperimentalKeys = "qweadzxc";
	public static final String validKeys = validClassicKeys + validExperimentalKeys;
	public static final int[] validKeyCommands = {KeyEvent.VK_PAGE_DOWN, KeyEvent.VK_ESCAPE,
			KeyEvent.VK_T, KeyEvent.VK_PERIOD};
	
	public static boolean isControlDown = false;
	
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
		if(e.isControlDown()) isControlDown = true; // Independiente del resto de la lógica.
		
		if(validKeys.indexOf(e.getKeyChar()) >= 0) gameplayHandler(s, e.getKeyChar());
		else for(int i : validKeyCommands) {if(i == e.getKeyCode()) extraHandler(s, e);}
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
		if((s.getMode() && validExperimentalKeys.indexOf(key) >= 0) ||
			   (!s.getMode() && validClassicKeys.indexOf(key) >= 0)) s.jugada(key);
	}
	
	/**
	 * Método que recive pulsaciones extra y las ejecuta en caso de que coincida con algún
	 * atajo de teclado ya establecido. <p>
	 * 
	 * Algunas notas: <ul>
	 * <li> Al pulsar escape, se debe confirmar la intención del usuario de salir de la
	 * partida. </li>
	 * <li> Al pulsar PAGE_DOWN, se debe de haber activado los trucos o estar jugando en
	 * modo clásico, para conservar las intenciones originales del código. </li>
	 * <li> Al pulsar T, solo debería poder activar o desactivar la consola en modo
	 * experimental. </li> </ul>
	 * 
	 * @param s Partida en la que se está jugando.
	 * @param e Evento del teclado.
	 */
	private static void extraHandler(SumaTres s, KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_PAGE_DOWN &&
				(s.cheatsUsed() || !s.getMode())) s.loop();
		else if(e.getKeyCode() == KeyEvent.VK_T && s.getMode()) s.toggleConsole(); 
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE &&
				Dialog.confirm("¿Desea salir de la partida?")) s.finalDePartida();
		else if(e.getKeyCode() == KeyEvent.VK_T && s.getMode() && !s.cheatsUsed()) s.enableCheats();
	}
}


