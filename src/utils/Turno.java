package utils;

import game.SumaTres;
import obj.Jugada;
import obj.Tablero;

public class Turno {

	/**
	 * Constrctor generado para cumplir con SonarLint:S1118.
	 * 
	 * @see <a href="https://sonarcloud.io/organizations/default/rules?languages=java&open=java%3AS1118&q=S1118">
	 * 		Regla SonarLint:S1118 </a>
	 */
	private Turno() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * Método utilizado por {@link #game.SumaTres.jugada(char)} para mover las piezas. Se mueven las
	 * piezas hasta que hay otra pieza en la dirección o se encuentra con el borde
	 * del tablero. Después de mover las piezas, se comprueba si se pueden mover más
	 * las piezas mediante un recorrido de todo el tablero. Si se encuentra alguna
	 * pieza que se pueda mover, el atributo 'check' se convierte en true, con lo
	 * que se mantiene el bucle while y se vuelven a mover todas las piezas.
	 * Obviamente, aquellas piezas que no se puedan mover más en la dirección
	 * escogida, se quedarán en la misma casilla.
	 * 
	 * @param x Objeto de la clase 'Jugada' que define el movimiento.
	 * @param s Objeto de la clase 'SumaTres' del que obtener información.
	 */
	public static void mover(Jugada x, SumaTres s) {
		Tablero t = s.getTablero();
		boolean check = true;
		while (check) {
			for (int i = x.getUp() + x.getDown()*(t.getSizeX() - 2); i < t.getSizeX() && i >= 0; i += 1 -2*x.getDown())
				for (int j = x.getLeft() + x.getRight()*(t.getSizeY() - 2); j < t.getSizeY() && j >= 0; j += 1 - 2*x.getRight()) {
					if (t.getTab(i + x.moveVert(), j + x.moveHorz()) == 0) {
						t.setTab(i + x.moveVert(), j + x.moveHorz(), t.getTab(i, j));
						t.setTab(i, j, 0);
				}
			}
			
			check = false;
			// Con un solo if, si se encuentra una pieza que contenga un valor y en la
			// siguiente pieza en la dirección seleccionada está vacía, significa que
			// se puede seguir moviendo el tablero.
			// De lo contrario, check se mantiene false por lo que se sale del bucle y se
			// termina el movimiento de las piezas.
			for (int i = x.getUp() + x.getDown() * (t.getSizeX() - 2); i < t.getSizeX() && i >= 0; i += 1 - 2 * x.getDown())
				for (int j = x.getLeft() + x.getRight() * (t.getSizeY() - 2); j < t.getSizeY() && j >= 0; j += 1 - 2 * x.getRight()) {
					if (t.getTab(i, j) != 0 && (t.getTab(i + x.moveVert(), j + x.moveHorz()) == 0)) check = true;
			}
		}
	}
	
	/**
	 * Método utilizado por {@link #jugada(char)} para sumar las piezas contiguas en
	 * la dirección seleccionada. Para detectar dichas sumas, se recorre todo el
	 * tablero examinando las piezas en la dirección seleccionada. Obviamente, si no
	 * se encuentra ninguna suma, no se suma nada.
	 * <p>
	 * Al terminar, el módulo vuelve a ejecutar {@link #mover(Jugada)}.
	 * Aunque esto ya se haya hecho supuestamente en Jugada(), dependiendo del
	 * posicionamiento de las fichas puede quedar sitio para que la ficha se siga
	 * moviendo en la dirección seleccionada después de moverse. Se mueve
	 * directamente porque así se evitan bucles innecesarios y evitar que se añadan
	 * las siguientes fichas y que se imprima más veces de las necesarias
	 * información por pantalla.
	 * <p>
	 * Se comprueba si la ficha que resulta de la suma es la mayor en el tablero a
	 * través de {@link #game.SumaTres.setHighest(int)}.
	 * 
	 * @param x Objeto de la clase 'Jugada' que define el movimiento.
	 * @param s Objeto de la clase 'SumaTres' del que obtener información.
	 */
	public static void sumar(Jugada x, SumaTres s) {
		Tablero t = s.getTablero();
		for (int i = x.getUp() + x.getDown() * (t.getSizeX() - 2); i < t.getSizeX() && i >= 0; i += 1 - 2 * x.getDown())
			for (int j = x.getLeft() + x.getRight() * (t.getSizeY() - 2); j < t.getSizeY() && j >= 0; j += 1 - 2 * x.getRight()) {
				if (sumaCond(i, j, x, t)) { // Si se puede sumar, se convierte la nueva casilla en la suma y la antigua en 0.
					t.setTab(i + x.moveVert(), j + x.moveHorz(), t.getTab(i, j) + t.getTab(i + x.moveVert(), j + x.moveHorz()));
					t.setTab(i, j, 0);
					s.addPuntos(t.getTab(i + x.moveVert(), j + x.moveHorz()));
					s.setHighest(t.getTab(i + x.moveVert(), j + x.moveHorz()));
					// Se comprueba si la mayor pieza es la recién sumada.
			}
		}
		mover(x, s);
		// Se mueve al terminar de suma para evitar que queden huecos vacíos.
	}

	/**
	 * Comprueba si se ha terminado la partida. Para esto, escanea el tablero
	 * repetidas veces con todas los posibles movimientos. Si en algún momento se
	 * detecta que hay una suma que se pueda hacer, el check devuelve 'true'. Si el
	 * tablero no está lleno, devuelve 'true'.
	 * <p>
	 * Se utiliza {@link #checkEndLoop(Jugada)} para independizar el
	 * bucle y optimizar el código. También se utiliza
	 * {@link #sumaCond(int, int, Jugada)} para verificar si la suma es
	 * posible o no.
	 * <p>
	 * Se utiliza {@link #obj.Tablero.isFull()} como condición necesaria para seguir
	 * comprobando si la partida está acabada. Si el tablero no está lleno, es
	 * imposible que la partida esté terminada.
	 * <p>
	 * Si se está jugando en el modo experimental, se comprueba que no se pueda
	 * mover en diagonal para completar los posibles movimientos.
	 * 
	 * 
	 * @return Valor 'booleano' definiendo si es posible algún movimiento.
	 */
	public static boolean ableToMove(SumaTres s) {
		Tablero t = s.getTablero();
		if (t.isFull()) {
			if (checkEndLoop(new Jugada('w'), t) || checkEndLoop(new Jugada('s'), t)
			|| checkEndLoop(new Jugada('a'), t) || checkEndLoop(new Jugada('d'), t)) return true;
			if(s.getMode() && (checkEndLoop(new Jugada('q'), t) || checkEndLoop(new Jugada('z'), t)
			|| checkEndLoop(new Jugada('c'), t) || checkEndLoop(new Jugada('e'), t))) return true;
		} else return true;
		return false;
	}
	
	/**
	 * Es el bucle perteneciente a {@link #ableToMove()}. Comprueba si en alguna parte
	 * del tablero es posible mover o sumar. Al método se le sobrecarga con el movimiento
	 * del tablero que se desea comprobar. <p> Se utiliza {@link #sumaCond(int, int, Jugada)}
	 * para comprobar la suma.
	 * 
	 * @param x Objeto de la clase 'Jugada' que define el movimiento.
	 * @param t Objeto de la clase 'Tablero' del que comprobar si se puede mover o no.
	 * @return Devuelve 'true' si se puede sumar alguna casilla y 'false' si no se
	 *         puede sumar ninguna.
	 */
	private static boolean checkEndLoop(Jugada x, Tablero t) {
		boolean check = false;
		for (int i = x.getUp(); i + x.getDown() < t.getSizeX(); i++)
			for (int j = x.getLeft(); j + x.getRight() < t.getSizeY(); j++) {
				if (sumaCond(i, j, x, t)) check = true;
		}
		return check;
	}
	
	/**
	 * Condición que detecta si se puede sumar o no.
	 * <p>
	 * Para determinar que sea una suma válida, se comprueba: o bien que sean piezas
	 * iguales (excepto 1 y 2), o bien que una de las piezas sea 1 y la otra 2.
	 * 
	 * @param i Posición x del tablero.
	 * @param j Posición y del tablero.
	 * @param x Objeto de la clase 'Jugada' que define el movimiento.
	 * @param t Objeto de la clase 'Tablero' en el que se está jugando.
	 * @return  Un booleano, 'true' si se puede sumar, 'false' si no.
	 */
	public static boolean sumaCond(int i, int j, Jugada x, Tablero t) {
		return t.getPieza(i + x.moveVert(), j + x.moveHorz()).getValor() == t.getPieza(i, j).getValor() &&
				t.getPieza(i, j).getValor() >= 3  ||
				t.getPieza(i, j).getValor() + t.getPieza(i + x.moveVert(), j + x.moveHorz()).getValor() == 3;
	}
}
