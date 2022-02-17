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
	 * Método principal que permite el uso del ratón con la interfaz básica.
	 * 
	 * @param s Partida en la que se está jugando.
	 * @param e Evento del ratón.
	 */
	private static void mouseClicked(SumaTres s, MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
                
                int limitX = Graphic.defineX(s);
                int limitY = Graphic.defineY(s);
                
                if(x < Paint.MAIN_SPACER && y < Paint.MAIN_SPACER)
                    if(s.getSettings().isDiagonalMovementEnabled()) s.jugada('q');
                else if(x < Paint.MAIN_SPACER && y > limitY - Paint.MAIN_SPACER)
                    if(s.getSettings().isDiagonalMovementEnabled()) s.jugada('z');
                else if(x > limitX - Paint.MAIN_SPACER && y < Paint.MAIN_SPACER)
                    if(s.getSettings().isDiagonalMovementEnabled()) s.jugada('e');
                else if(x > limitX - Paint.MAIN_SPACER && y > limitY - Paint.MAIN_SPACER)
                    if(s.getSettings().isDiagonalMovementEnabled()) s.jugada('c');
                else {
                        if(x < Paint.MAIN_SPACER) s.jugada('a');
                        else if(y < Paint.MAIN_SPACER) s.jugada('w');
                        else if(y > limitY - Paint.MAIN_SPACER)
                            if(s.getSettings().isDiagonalMovementEnabled()) s.jugada('c');
                            else s.jugada('s');
                        else if(x > limitX - Paint.MAIN_SPACER) s.jugada('d');
                } 
        }
}
