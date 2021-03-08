package handler;

import game.SumaTres;
import utils.Graphic;
import utils.Paint;

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

	public static void mouseHandler(SumaTres s, int x, int y) {
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
					else if (x > (Graphic.defineX(s.getTablero().getSizeX()) - Paint.MAIN_SPACER)) s.jugada('d');
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
