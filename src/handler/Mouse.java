package handler;

import java.awt.event.MouseEvent;

import game.SumaTres;
import util.Graphic;
import util.Paint;

public class Mouse {

	/**
	 * Constrctor generado para cumplir con SonarLint:S1118.
	 * 
	 * @see <a href="https://sonarcloud.io/organizations/default/rules?languages=java&open=java%3AS1118&q=S1118">
	 * 		Regla SonarLint:S1118 </a>
	 */
	private Mouse() {
		throw new IllegalStateException("Event handling class");
	}

	/**
	 * Método que diferencia entre eventos y los redirige.
	 * 
	 * @param s Partida en la que se está jugando.
	 * @param e Evento del ratón.
	 */
	public static void mouseHandler(SumaTres s, MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) mouseClicked(s, e);
	}
	
	/**
	 * Método principal que permite el uso del ratón con la interfaz básica. <p>
	 * Debido a que este es actualmente el método más antiguo, es bastante ofensivo
	 * a la vista e ilegible. Se ha ido parcheado con el tiempo para que siga funcionando
	 * con toda las nuevas características del programa, pero necesita un rewrite.
	 * 
	 * @param s Partida en la que se está jugando.
	 * @param e Evento del ratón.
	 */
	private static void mouseClicked(SumaTres s, MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(y < Graphic.defineY(s) - Paint.MAIN_SPACER) {
			if (x < Paint.MAIN_SPACER) {
				if (y > Paint.MAIN_SPACER) {
					if (y > (Graphic.defineY(s) - 2 * Paint.MAIN_SPACER)) {
						if(s.getMode()) s.jugada('z');
						else            s.jugada('a');
					}
					else s.jugada('a');
				}
				else {
					if(s.getMode()) s.jugada('q');
					else            s.jugada('a');
				}
			} else {
				if (y < Paint.MAIN_SPACER) {
					if (x > Graphic.defineX(s) - Paint.MAIN_SPACER) {
						if(s.getMode()) s.jugada('e');
						else          s.jugada('w');
					}
					else s.jugada('w');
				} else {
					if (y > (Graphic.defineY(s) - 2 * Paint.MAIN_SPACER)) {
						if(x > Graphic.defineX(s) - Paint.MAIN_SPACER) {
							if(s.getMode()) s.jugada('c');
							else            s.jugada('s');
						}
						else s.jugada('s');
					}
					else if (x > (Graphic.defineX(s.getTablero().getColumns()) - Paint.MAIN_SPACER)) s.jugada('d');
				}
			}
		} else { // Botonera
			if(s.getMode() && y > Graphic.defineY(s) - Paint.BUTTON_SIZE) {
				if(x > Graphic.defineX(s) - Paint.BUTTON_SIZE ) s.toggleConsole(); // Botón de alternar consola
				else {
					if(x < Paint.BUTTON_SIZE) {
						if(!s.cheatsUsed()) s.enableCheats(); // Botón de activar trucos
						else if(s.getTablero().colocarPieza()) s.update(); // Botón de colocar nueva pieza
					} else {
						if(x > Graphic.defineX(s) - 2 * Paint.BUTTON_SIZE + Paint.SPOT_SPACER && s.getTurnos() > 1 && s.cheatsUsed()) s.undo();
						else if(x < Paint.BUTTON_SIZE * 2 && s.cheatsUsed()
							&& s.getTablero().quitarPieza()) s.update(); // Botón de quitar pieza
						
					}
				}
			}
		}
	}
}
