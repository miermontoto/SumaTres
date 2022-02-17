package util;

import java.awt.Toolkit;
import javax.swing.ImageIcon;

import game.SumaTres;

public final class Graphic {

	public static final ImageIcon ICON = new ImageIcon("./assets/icon.png");
	public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int SCREEN_HEIGHT  = Toolkit.getDefaultToolkit().getScreenSize().height;
	public static final double SCALE = (double) SCREEN_HEIGHT / 720 < 0.5 ? 0.5 : (double) SCREEN_HEIGHT / 720;
	/*
	 * La escala ayuda a mantener un tamaño jugable tanto de tamaño de ventana como de objetos pintados
	 * gráficamente. En algunos casos extremos, como es el caso de resoluciones pequeñas y poco comunes
	 * hoy en día (640x480, 800x600), la escala y las posiciones de objetos son ligeramente incorrectos,
	 * con lo que el texto no está bien colocado del todo.
	 */

	/*
	 * Lo negativo de que los tamaños de la pantalla sean fijos y no se actualicen
	 * es que el tablero mantiene el tamaño establecido al principio de la partida,
	 * con lo que al cambiar de resolución a mitad de partida, no se ajusta nada. 
	 * No merece la pena crear métodos que obtenga constantemente la resolución de
	 * la pantalla porque realísticamente nadie cambia la resolución constantemente
	 * a menos que se quiera romper visualmente el programa.
	 */

	/**
	 * Constrctor generado para cumplir con SonarLint:S1118.
	 * 
	 * @see <a href="https://sonarcloud.io/organizations/default/rules?languages=java&open=java%3AS1118&q=S1118">
	 * 		Regla SonarLint:S1118 </a>
	 */
	private Graphic() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	* Método que calcula el ancho total final de la pantalla. Se utiliza en el main para definir la
	* aplicación y también en diversos métodos dentro de esta clase para calcular posiciones respecto
	* a los bordes de la ventana. Depende de la matriz tablero inicializada.

	* @return Valor entero con el ancho de la ventana.
	*/
	public static int defineX(int x) {
		return x * (Paint.SPOT_SPACER + Paint.SQUARE_SIZE) + 2 * Paint.BOARD_SPACER - Paint.SPOT_SPACER + 2 * Paint.MAIN_SPACER;
	}
	
	public static int defineX(SumaTres s) {
		return s.getTablero().getRows() * (Paint.SPOT_SPACER + Paint.SQUARE_SIZE) + 2 * Paint.BOARD_SPACER - Paint.SPOT_SPACER + 2 * Paint.MAIN_SPACER;
	}
	
	/**
	* Método que calcula el alto total final de la pantalla. 
	*
	* @param x Valor entero con la cantidad de filas.
	* @return Valor entero con el alto de la ventana.
	*/
	public static int defineY(int x) {
		return x * (Paint.SPOT_SPACER + Paint.SQUARE_SIZE) + 2 * Paint.BOARD_SPACER - Paint.SPOT_SPACER + 3 * Paint.MAIN_SPACER;
	}
	
	/**
	* Método que calcula el alto total final de la pantalla. 
	*
	* @param s Objeto tipo 'SumaTres' con el que se está jugando.
	* @return Valor entero con el alto de la ventana.
	*/
	public static int defineY(SumaTres s) {
		return s.getTablero().getColumns() * (Paint.SPOT_SPACER + Paint.SQUARE_SIZE) + 2 * Paint.BOARD_SPACER - Paint.SPOT_SPACER + 3 * Paint.MAIN_SPACER;
	}
	
	
	
	/**
	* Método que comprueba si el tablero entra en pantalla con la resolución
	* actual.
	* 
	* @return Valor booleano que establece si la pantalla generada entra o no en la resolución actual.
	*/
	public static boolean validSize(int x, int y) {
		return y * (Paint.SPOT_SPACER + Paint.SQUARE_SIZE) + 2 * Paint.BOARD_SPACER - Paint.SPOT_SPACER + 2 * Paint.MAIN_SPACER < SCREEN_WIDTH &&
		x * (Paint.SPOT_SPACER + Paint.SQUARE_SIZE) + 2 * Paint.BOARD_SPACER - Paint.SPOT_SPACER + 2 * Paint.MAIN_SPACER < SCREEN_HEIGHT; 
	}
}
