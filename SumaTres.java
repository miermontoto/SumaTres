import static java.lang.System.out; // Se importa de esta manera para aportar durante el resto del programa.
import java.util.Random; // Se necesita para la generación de números aleatorios a la hora de generar fichas nuevas.

/** Clase principal del proyecto SumaTres.
 * @author Juan Mier, Martín Feito
 * @since 22/12/2020
 * @see Se inicializa o bien con el constructor por defecto o bien sobrecargando dos enteros que delimitan el tamaño del tablero.
 */
public class SumaTres {

	// TODO lógica de suma completa
	// TODO nueva ficha cada jugada
	// TODO imprimir matriz
	// TODO main
	// TODO comprobar final de partida (de manera exhausta)
	// TODO documentación pdf
	// TODO reestructuración y comentarios
	// TODO limpieza de código
	// TODO casos extremos

	private int turnos = 0; //no es necesario
	private int siguiente = 1;
	private int[][] tablero;
	private int puntos = 0;

	/** Constructor por defecto del tablero.
	 * Establece 5x5 (como en las demos) para el tablero.
	 * Utiliza el constructor sobrecargado con dos enteros.
	 */
	public SumaTres() {
		this(5, 5);
	}

	/** Constructor de la clase sobrecargado por dos enteros.
	 * Se presupone que el tamaño mínimo de un tablero es de 4x4.
	 * Si los parámetros introducidos son menores, se establece 4x4.
	 * @param x: Cantidad de filas del tablero.
	 * @param y: Cantidad de columnas del tablero.
	 */
	public SumaTres(int x, int y) {
		if(x>=4 && y>=4) {tablero = new int[x][y];}
		else {tablero = new int[4][4];}
	}



	// --- sets y gets --- //
	/** Devuelve los puntos.
	 * @return entero con los puntos actuales.
	 */
	public int getPuntos() {return this.puntos;}
	
	/** Establece unos puntos determinados.
	 * @param puntos que se quieren establecer.
	 */
	public void setPuntos(int puntos) {this.puntos = puntos;}
	
	/** Añade puntos al contador actual.
	 * @param puntos que se quieren sumar.
	 */
	public void addPuntos(int puntos) {this.puntos += puntos;} //se pueden añadir puntos negativos?

	/** Devuelve el valor de la ficha que se encuentre en unas coordenadas.
	 * @param x Coordenada x que se desea analizar.
	 * @param y Coordenada y que se desea analizar.
	 * @return tipo 'entero' con el valor de la casilla.
	 */
	public int getTab(int x, int y) {return this.tablero[x][y];}
	
	/** Establece el valor de una ficha para una casilla.
	 * @param x Coordenada x que se desea cambiar.
	 * @param y Coordenada y que se desea cambiar.
	 * @param nv Nuevo Valor de la casilla.
	 */
	public void setTab(int x, int y, int nv) {this.tablero[x][y] = nv;}

	/** Devuelve el valor de la próxima ficha.
	 * @return tipo 'entero' con el valor de la futura ficha.
	 */
	public int getSiguiente() {return this.siguiente;}
	
	/** Establece el valor de la próxima ficha.
	 * @param siguiente Nuevo valor de la ficha.
	 */
	public void setSiguiente(int siguiente) {this.siguiente = siguiente;}
	
	/** Añade un turno al contador total.
	 */
	public void addTurno() {this.turnos++;}
	
	/** Quita un turno al contador total.
	 */
	public void removeTurno() {this.turnos--;}
	
	/** Devuelve los turnos que se han jugado hasta el momento.
	 * @return tipo 'entero' con la cantidad de turnos.
	 */
	public int getTurnos() {return this.turnos;}
	// --- sets y gets --- //
	
	
	
	// TODO comprobar casos de varias casillas iguales juntas.
	// TODO imprimir la matriz movida antes de sumar elementos.
	// TODO comprobar el orden de suma de las casillas.

	/** Método principal de la clase.
	 * Ejecuta el código que determina qué jugada hacer, mueve y suma las piezas.
	 * Si la casilla destino está vacía, simplemente se mueve.
	 * Si la casilla destino es igual o suman 3 (1+2 || 2+1), se suman.
	 * @param c Caracter que determina el movimiento (a/b/i/d). Ideado para entrada por consola.
	 * @see Se podría utilizar un solo bucle para mover y sumar, pero se requiere imprimir por pantalla entre cada paso.
	 */
	public void Jugada(char c) {
		int movx = 0;
		int movy = 0;
		//delimitan el desplazamiento del movimiento
		//para arriba, el desplazamiento es -1 en la vertical
		//para izquierda, el desplazamiento es -1 en la horizontal
		//utilizando esto, se reduce el código en un 70% aprox. (no se tiene que incluir cada caso en cada switch)

		addTurno();
		switch(c) {
		case 'a': //ARRIBA
			movy--;
			break;
		case 'b': //ABAJO
			movy++;
			break;
		case 'i': //IZQUIERDA
			movx--;
			break;
		case 'd': //DERECHA
			movx++;
			break;
		default: //otro valor (inválido)
			out.println("Jugada inválida.");
			removeTurno(); //no se va a jugar este turno, la detección de fichas lo ignorará.
			break;
		}


		if(movx != 0 && movy != 0) { //si no se desplaza el índice significa que no es una jugada válida.
			for(int i=0; i<tablero.length; i++) for(int j=0; j<tablero[0].length; j++) { //búsqueda de casillas que contengan valores.
				// Paso 1: Se mueven las fichas en la dirección adecuada antes de sumar nada.
				if(getTab(i, j)!=0) {
					if(getTab(i+movx, j+movy)==0) { 
						setTab(i+movx, j+movy, getTab(i, j));
						setTab(i, j, 0);
					}
				}
			}
			
			//out.println(this);

			for(int i=0; i<tablero.length; i++) for(int j=0; j<tablero[0].length; j++) {
				if(getTab(i, j)!=0) { // como antes, se buscan casillas que contengan valores.
					// Paso 2: Se suman las fichas que se puedan sumar.
					if(getTab(i, j) == getTab(i+movx, j+movy) || getTab(i, j) + getTab(i+movx, j+movy) == 3) {
						setTab(i+movx, j+movy, getTab(i, j) + getTab(i+movx, j+movy));
						setTab(i, j, 0);
						addPuntos(tablero[i+movx][j+movy]); // Se suman los puntos de la suma al total.
					}
				}
			}
			
			//out.println(this);
		}
	}
	

	/** Genera un número nuevo aleatorio.
	 * @param val: un entero cualquiera.
	 * @return Un entero aleatorio entre 0 y val.
	 */
	public int newRandom(int val) {
		Random w = new Random();
		return w.nextInt(val);
	}

	/** Genera un número para la siguiente ficha a generar.
	 * Se imprime por pantalla mediante el 'toString()'.
	 * @see Genera un número entre 1 y 3 utilizando 'newRandom()'.
	 */
	public void newSiguiente() {
		setSiguiente(newRandom(3)+1);
	}
	
	/** Comprueba que si el tablero está lleno.
	 * @return Devuelve un booleano, 'true' si está lleno, 'false' si no.
	 */
	public boolean checkFull() {
		int check = 0;
		for(int i=0; i<tablero.length; i++) for(int j=0; j<tablero[0].length; j++) {
			if(tablero[i][j]!=0) check++;
		}
		return check == tablero.length * tablero[0].length;
	}

	/** Método que imprime la información del tablero por pantalla,
	 * incluyendo la siguiente ficha y los puntos.
	 * Se utiliza entre los cálculos de la Jugada()
	 */
	@Override
	public String toString() {
		String salida = String.format(""); //TODO completar salida para que coincida con la demo
		return salida; //tiene que incluir "Siguiente ficha: " y "Puntos: "
		//imprimir los turnos??? -- getTurnos()
	}
}