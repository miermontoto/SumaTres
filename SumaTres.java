import static java.lang.System.out; // Se importa de esta manera para aportar durante el resto del programa.
import java.util.Random; // Se necesita para la generación de números aleatorios a la hora de generar fichas nuevas.

/** Clase principal del proyecto SumaTres.
 * @author Juan Mier, Martín Feito
 * @since 22/12/2020
 * @version v9b
 * @see Se inicializa o bien con el constructor por defecto o bien sobrecargando dos enteros que delimitan el tamaño del tablero.
 */
public class SumaTres {

	// TODO interfaz gráfica
	// TODO end: documentación pdf
	// TODO end: limpieza de código
	// TODO end: casos extremos

	private int turnos = 1; //no es necesario
	private int siguiente;
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
	 * Incializa el tablero con tres fichas (1, 2, 3) utilizando 'newFicha()'.
	 * @param x: Cantidad de filas del tablero.
	 * @param y: Cantidad de columnas del tablero.
	 */
	public SumaTres(int x, int y) {
		if(x>=4 && y>=4) {tablero = new int[x][y];}
		else {tablero = new int[4][4];}
		newFicha(3); 
		newFicha(2); // se inicializa el tablero.
		newFicha(1);
		newSiguiente();
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
	public void addPuntos(int puntos) {this.puntos += puntos;}

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
	// TODO comprobar el orden de suma de las casillas.

	/** Método principal de la clase.
	 * Ejecuta el código que determina qué jugada hacer, mueve y suma las piezas. 
	 * Se mueven todas las piezas en la dirección seleccionada hasta encontrar otra pieza o
	 * hasta llegar al borde del tablero.
	 * Si la casilla destino es igual o suman 3 (1+2 || 2+1), se suman.
	 * Se utiliza {@link #sumaCond(int, int, int, int, int, int)} para verificar si la suma es posible o no.
	 * Al final de la jugada, se establece el valor de la siguiente ficha con {@link #newSiguiente()}.
	 * Al final de la jugada, se pone la nueva ficha en el tablero, comprobando que se encuentra
	 * en una casilla vacía mediante {@link #newFicha(int)} y {@link #getSiguiente()}.
	 * Se imprime la situación de la partida mediante {@link #toString()} después de cada acción.
	 * Se modifican los turnos mediante {@link #addTurno()}.
	 * Después de cada suma satisfactoria, se añade el valor de la suma al total de puntuación
	 * mediante {@link #addPuntos(int)}.
	 * Se utilizan de manera extensiva los atributos 'up', 'down', 'left' y 'right':
	 *  · Indican la dirección señalada en el main.
	 *  · Se utilizan valores enteros y no booleanos porque es necesario para los movimientos y las comprobaciones.
	 *  · Se selecciona cada dirección mediante un switch.
	 * Para optimizar el uso del código, los métodos para sumar y mover las piezas están separados:
	 * Para sumar, se utiliza {@link #sumar(int, int, int, int)},
	 * para mover, se utiliza {@link #mover(int, int, int, int)}.
	 * @param c Caracter que determina el movimiento (a/b/i/d).
	 */
	public void Jugada(char c) {

		int up = 0;
		int down = 0;
		int left = 0;
		int right = 0;

		switch(c) {
		case 'w': //up
			up = 1;
			break;
		case 's': //down
			down = 1;
			break;
		case 'a': //left
			left = 1;
			break;
		case 'd': //right
			right = 1;
			break;
		default: //otro valor (inválido)
			out.println("Jugada inválida.");
			break;
		}

		if (!(up == 0 && down == 0 && left == 0 && right == 0)) { // Se comprueba que se ha seleccionado alguna dirección.
			addTurno();
			
			mover(up, down, left, right);
			out.println(this);


			sumar(up, down, left, right);

			newFicha(getSiguiente());
			newSiguiente();
			out.println(this);
		}
	}

	
	/** Método utilizado {@link #Jugada(char)} para mover las piezas.
	 * Se mueven las piezas hasta que hay otra pieza en la dirección o se encuentra con el borde del tablero.
	 * Después de mover las piezas, se comprueba si se pueden mover más las piezas mediante un
	 * recorrido de todo el tablero. Si se encuentra alguna pieza que se pueda mover, el atributo
	 * 'check' se convierte en true, con lo que se mantiene el bucle while y se vuelven a mover todas
	 * las piezas. Obviamente, aquellas piezas que no se puedan mover más en la dirección escogida,
	 * se quedarán en la misma casilla. Se introduce la dirección mediante los enteros 'up', 'down',
	 * 'left' y 'right'.
	 * @param up '1' o '0' para el movimiento hacia arriba.
	 * @param down '1' o '0' para el movimiento hacia abajo.
	 * @param left '1' o '0' para el movimiento hacia la izquierda.
	 * @param right '1' o '0' para el movimiento hacia la derecha.
	 */
	public void mover(int up, int down, int left, int right) {
		boolean check = true;
		while(check) {
			for(int i=up; i+down<tablero.length; i++) for(int j=left; j+right<tablero[0].length; j++) {
				if(getTab(i-up+down, j-left+right) == 0) {
					setTab(i-up+down, j-left+right, getTab(i, j));
					setTab(i, j, 0);
				}
			}

			check = false;
			for(int i=up; i+down<tablero.length; i++) for(int j=left; j+right<tablero[0].length; j++) {
				if(getTab(i, j) != 0) {
					if(getTab(i-up+down, j-left+right) == 0) check = true;
				}
			}
		}
	}

	
	/** Método utilizado por {@link #Jugada(char)} para sumar las piezas contiguas en la dirección
	 * seleccionada. Para detectar dichas sumas, se recorre todo el tablero examinando las piezas
	 * en la dirección seleccionada. Obviamente, si no se encuentra ninguna suma, no se suma nada.
	 * Al terminar, el módulo vuelve a ejecutar {@link #mover(int, int, int, int)}. Aunque esto ya
	 * se haya hecho supuestamente en Jugada(), dependiendo del posicionamiento de las fichas puede
	 * quedar sitio para que la ficha se siga moviendo en la dirección seleccionada después de moverse.
	 * Se mueve directamente porque así se evitan bucles innecesarios y evitar que se añadan las 
	 * siguientes fichas y que se imprima más veces de las necesarias información por pantalla.
	 * @param up '1' o '0' para el movimiento hacia arriba.
	 * @param down '1' o '0' para el movimiento hacia abajo.
	 * @param left '1' o '0' para el movimiento hacia la izquierda.
	 * @param right '1' o '0' para el movimiento hacia la derecha.
	 */
	public void sumar(int up, int down, int left, int right) {
		for(int i=up; i+down<tablero.length; i++) for(int j=left; j+right<tablero[0].length; j++) {
			if(sumaCond(i, j, up, down, left, right)) {
				setTab(i-up+down, j-left+right, getTab(i, j) + getTab(i-up+down, j-left+right));
				setTab(i, j, 0);
				addPuntos(getTab(i-up+down, j-left+right));
			}
		}
		mover(up, down, left, right); //se mueve al terminar de suma
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
	 * Si una casilla tiene un valor, se suma 1 al contador.
	 * Si al terminar la operación, el contador es igual a la cantidad
	 * de casillas, entonces el tablero está lleno.
	 * @return Devuelve un booleano, 'true' si está lleno, 'false' si no.
	 */
	public boolean checkFull() {
		int check = 0;
		for(int i=0; i<tablero.length; i++) for(int j=0; j<tablero[0].length; j++) {
			if(getTab(i, j) != 0) check++;
		}
		return check == tablero.length * tablero[0].length;
	}

	/** Comprueba si se ha terminado la partida.
	 * Para esto, escanea el tablero repetidas veces con todas los posibles movimientos.
	 * Si en algún momento se detecta que hay una suma que se pueda hacer, el check devuelve 'true'.
	 * Si el tablero no está lleno, devuelve 'true'.
	 * Se utiliza {@link #checkEndLoop(int, int, int, int)} para independizar el bulce y optimizar el código.
	 * Se utiliza {@link #sumaCond(int, int, int, int, int, int)} para verificar si la suma es posible o no.
	 * Se utiliza {@link #checkFull()} como condición necesaria para seguir comprobando si la partida está acabada.
	 * Si el tablero no está lleno, es imposible que la partida esté terminada.
	 * @return Valor 'booleano' definiendo si es posible algún movimiento.
	 */
	public boolean ableToMove() {
		boolean ableToMove = false;
		if (checkFull()) {
			if (checkEndLoop(1,0,0,0)) ableToMove = true;
			if (checkEndLoop(0,1,0,0)) ableToMove = true;
			if (checkEndLoop(0,0,1,0)) ableToMove = true;
			if (checkEndLoop(0,0,0,1)) ableToMove = true;
		} else {ableToMove = true;}
		return ableToMove;
	}
	
	/** Es el bucle perteneciente a checkEnd().
	 * Comprueba si en alguna parte del tablero es posible.
	 * Al método se le sobrecarga con el movimiento del tablero que se desea comprobar.
	 * @param up Valor '1' o '0' para el movimiento hacia arriba.
	 * @param down Valor '1' o '0' para el movimiento hacia atrás.
	 * @param left Valor '1' o '0' para el movimiento hacia la izquierda.
	 * @param right Valor '1' o '0' para el movimiento hacia la derecha.
	 * @return Devuelve 'true' si se puede sumar alguna casilla y 'false' si no se puede sumar ninguna.
	 */
	public boolean checkEndLoop(int up, int down, int left, int right) {
		boolean check = false;
		for(int i=up; i+down<tablero.length; i++) for(int j=left; j+right<tablero[0].length; j++) {
			 if(sumaCond(i, j, up, down, left, right)) check = true;
		}
		return check;
	}

	/** Condición que detecta si se puede sumar o no.
	 * @param i Posición x del tablero.
	 * @param j Posición y del tablero.
	 * @param movx Dirección de la suma horizontal (si no hay, es igual a 0).
	 * @param movy Dirección de la suma vertical (si no hay, es igual a 0).
	 * @return Un booleano, 'true' si se puede sumar, 'false' si no.
	 */
	public boolean sumaCond(int i, int j, int up, int down, int left, int right) {
		return getTab(i-up+down, j-left+right) == getTab(i, j) && getTab(i, j) != 2 && getTab(i, j) != 1 || getTab(i, j) + getTab(i-up+down, j-left+right) == 3;
	}

	/** Coloca una nueva ficha en tablero.
	 * Para encontrar una poisición nueva, utiliza 'newRandom()'.
	 * Se comprueba que en la casilla no haya ya una ficha.
	 * @param nV El valor que tendrá la nueva ficha.
	 */
	public void newFicha(int nV) {
		int nX;
		int nY;
		nX = newRandom(tablero.length);
		nY = newRandom(tablero[0].length);
		while(getTab(nX, nY) != 0) {
			nX = newRandom(tablero.length);
			nY = newRandom(tablero[0].length);
		}
		setTab(nX, nY, nV);
	}

	/** Método que imprime la información del tablero por pantalla,
	 * incluyendo la siguiente ficha y los puntos.
	 * Se utiliza entre los cálculos de la Jugada()
	 */
	@Override
	public String toString() {
		String salida = String.format("╔");
		for(int i=0; i+1<tablero.length; i++) salida += "══╦";
		salida += "══╗";
		salida += String.format("%n");
		
		for(int i=0; i<tablero.length; i++) {
			if(getTab(i, 0) == 0) salida += "║  ║";
			else salida += String.format("║%2d║", getTab(i, 0));
			for(int j=1; j<tablero[0].length; j++) {
				if(getTab(i, j) == 0) salida += "  ║";
				else salida += String.format("%2d║", getTab(i, j));
			}
			salida += String.format("%n");
		}
		
		salida += "╚";
		for(int i=0; i+1<tablero.length; i++) salida += "══╩";
		salida += "══╝";
		salida += String.format("%n");
		salida += String.format("Siguiente %d%nPuntos %d%nTurno %d%n", getSiguiente(), getPuntos(), getTurnos());
		return salida;
	}
}