import static java.lang.System.out;
import java.security.SecureRandom;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.HashMap;
import java.awt.Toolkit;

/**
 * <h1> Clase principal del proyecto SumaTres </h1>
 * 
 * Se inicializa o bien con el constructor por defecto o bien sobrecargando dos
 * enteros que delimitan el tamaño del tablero. Se utiliza %n en vez de \n por
 * recomendación de Oracle. Además, se utiliza <code>java.security.SecureRandom</code>
 * en vez de <code>java.util.Random</code>. Cada método está documentado.
 * 
 * <p>
 * 
 * Para determinar y almacenar el color de las piezas según su valor, se utiliza
 * un HashMap. El HashMap es necesario si se quiere almacenar todos los valores
 * predeterminados en {@link #inicializarColores()} y, además, se generan
 * colores aleatoriamente utilizando {@link #newRandom(int)} para los valores
 * que no están ya determinados. Esta opción es mucho más práctica que un
 * ArrayList, mucho más elegante y funcional que simplemente implementar un
 * <code>switch(int val)</code> y algo innovador que no se ha visto en las
 * prácticas.
 * 
 * <p>
 * 
 * Para determinar la escala de la aplicación gráfica, se obtiene el alto de la
 * pantalla mediante <code>java.awt.Toolkit</code> y se divide por 720.
 * Correspondientemente, la aplicación se pintará con todos los atributos
 * multiplicados por la escala.
 * 
 * <p>
 * 
 * Para la entrada, se utiliza MouseEvent y KeyEvent para detectar la entrada.
 * 
 * @author Juan Mier
 * @author Martín Feito
 * @version v11b
 * @see <a href="https://docs.oracle.com/javase/tutorial/java/data/numberformat.html">
 * 		Documentación de Oracle: Number Format</a>
 *      <blockquote>A new line character appropriate to the platform running the
 *      application. You should always use %n, rather than \n. </blockquote>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/security/SecureRandom.html">
 * 		Documentación de Oracle: SecureRandom</a>
 * @see <a href="https://rules.sonarsource.com/java/RSPEC-2119">
 * 		Regla SonarLint:2119</a>
 *  	<blockquote>SecureRandom is preferred to Random</blockquote>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html">
 * 		Documentación de Oracle: HashMap</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/awt/Toolkit.html">
 * 		Documentación de Oracle: Toolkit</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html">
 * 		Documentación de Oracle: Tutorial de KeyListener</a>
 * @see <a href="https://www.github.com/GijonDev/SumaTres">Repositorio de GitHub</a>
 * 
 */
public class SumaTres extends JPanel {

	// TODO arreglar que el programa no detecte KeyEvents hasta que se haga un MouseEvent (focus?)
	// TODO arreglar lógica de suma en dos direcciones
	// TODO mover a la izquierda fichas muy grandes
	// TODO end: documentación pdf
	// TODO end: casos extremos
	// TODO end: eliminar TODOs

	/**
	 * Se incluye un serial generado aleatoriamente en vez de dejar que el
	 * compilador genere uno por defecto porque así se indica en la documentación de
	 * Oracle. Si no se declarara uno, podría generar excepciones como
	 * 'InvalidClassExceptions'.
	 * <p>
	 * El serial ha sido generado mediante la herramienta de Eclipse 'Quick Fix'. Si
	 * SumaTres no fuera una subclase de JPanel, no sería necesario declarar un
	 * serial. El serial debe de ser obligatoriamente final y de tipo 'double'. El
	 * programa no utiliza serialización explícitamente.
	 * 
	 * @see <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Serializable.html">
	 * 		Documentación de Oracle: Serializable</a>
	 */
	private static final long serialVersionUID = -1110032705510692144L;
	private int turnos = 1;
	private int siguiente;
	private int[][] tablero;
	private int puntos = 0;
	private int highestValue = 0;
	private SecureRandom rand = new SecureRandom();

	/**
	 * Constructor de la clase sobrecargado por dos enteros. Se presupone que el
	 * main introduce tamaños correctos.
	 * <p>
	 * Incializa el tablero con tres fichas (1, 2, 3) utilizando
	 * {@link #newFicha(int)}.
	 * <p>
	 * Rellena la lista de colores por valor según los valores por defecto
	 * utilizando {@link #inicializarColores()}.
	 * <p>
	 * Se establece la primera ficha que se introducirá en el tablero después de la
	 * primera jugada mediante {@link #newSiguiente()}.
	 * <p>
	 * Se comienza a esperar por clicks del usuario mediante
	 * {@link #addMouseListener(java.awt.event.MouseListener)}.
	 * 
	 * @param x: Cantidad de filas del tablero.
	 * @param y: Cantidad de columnas del tablero.
	 */
	public SumaTres(int x, int y) {
		tablero = new int[x][y];
		
		newFicha(3);
		newFicha(2); // Se inicializa el tablero.
		newFicha(1);

		newSiguiente(); // Se establece la ficha 'siguiente' por primera vez.

		addMouseListener(new MouseHandler()); // El programa comienza a escuchar por clicks del usuario.
		addKeyListener(new KeyHandler()); // El programa comienza a escuchar por pulsaciones de tecla.
		inicializarColores(); // Se populiza el HashMap de colores con los valores por defecto.
		
		requestFocus();
		requestFocusInWindow(); // Pide el focus de la ventana para que se registre el tecleo.
	}

	// --- sets y gets --- //
	/**
	 * Devuelve la ficha de mayor valor actual.
	 * 
	 * @return entero con el valor actuales.
	 */
	public int getHighest() {return this.highestValue;}

	/**
	 * Establece el mayor valor de cualquier ficha en el tablero. Para que el valor
	 * sea realmente el mayor, se realiza un condicional sencillo antes de
	 * establecerlo.
	 * 
	 * @param valor que se quiera establecer.
	 */
	public void setHighest(int puntos) {
		if (puntos > this.getHighest()) this.highestValue = puntos;
	}

	/**
	 * Devuelve los puntos.
	 * 
	 * @return entero con los puntos actuales.
	 */
	public int getPuntos() {return this.puntos;}

	/**
	 * Establece unos puntos determinados.
	 * 
	 * @param puntos que se quieren establecer.
	 */
	public void setPuntos(int puntos) {this.puntos = puntos;}

	/**
	 * Añade puntos al contador actual.
	 * 
	 * @param puntos que se quieren sumar.
	 */
	public void addPuntos(int puntos) {this.puntos += puntos;}

	/**
	 * Devuelve el valor de la ficha que se encuentre en unas coordenadas.
	 * 
	 * @param x Coordenada x que se desea analizar.
	 * @param y Coordenada y que se desea analizar.
	 * @return tipo 'entero' con el valor de la casilla.
	 */
	public int getTab(int x, int y) {return this.tablero[x][y];}

	/**
	 * Establece el valor de una ficha para una casilla.
	 * 
	 * @param x  Coordenada x que se desea cambiar.
	 * @param y  Coordenada y que se desea cambiar.
	 * @param nv Nuevo Valor de la casilla.
	 */
	public void setTab(int x, int y, int nv) {this.tablero[x][y] = nv;}

	/**
	 * Devuelve el valor de la próxima ficha.
	 * 
	 * @return tipo 'entero' con el valor de la futura ficha.
	 */
	public int getSiguiente() {return this.siguiente;}

	/**
	 * Establece el valor de la próxima ficha.
	 * 
	 * @param siguiente Nuevo valor de la ficha.
	 */
	public void setSiguiente(int siguiente) {this.siguiente = siguiente;}

	/**
	 * Añade un turno al contador total.
	 */
	public void addTurno() {this.turnos++;}

	/**
	 * Quita un turno al contador total.
	 */
	public void removeTurno() {this.turnos--;}

	/**
	 * Devuelve los turnos que se han jugado hasta el momento.
	 * 
	 * @return tipo 'entero' con la cantidad de turnos.
	 */
	public int getTurnos() {return this.turnos;}
	// --- sets y gets --- //

	/**
	 * Método principal de la clase. Ejecuta el código que determina qué jugada
	 * hacer, mueve y suma las piezas.
	 * <p>
	 * Se mueven todas las piezas en la dirección seleccionada hasta encontrar otra
	 * pieza o hasta llegar al borde del tablero. Si la casilla destino es igual o
	 * suman 3 (1+2 || 2+1), se suman. Se utiliza
	 * {@link #sumaCond(int, int, int, int, int, int)} para verificar si la suma es
	 * posible o no.
	 * <p>
	 * Al final de la jugada, se establece el valor de la siguiente ficha con
	 * {@link #newSiguiente()}. Al final de la jugada, se pone la nueva ficha en el
	 * tablero, comprobando que se encuentra en una casilla vacía mediante
	 * {@link #newFicha(int)} y {@link #getSiguiente()}.
	 * <p>
	 * Se imprime la situación de la partida mediante {@link #toString()} después de
	 * cada acción.
	 * <p>
	 * Se modifican los turnos mediante {@link #addTurno()}.
	 * <p>
	 * Después de cada suma satisfactoria, se añade el valor de la suma al total de
	 * puntuación mediante {@link #addPuntos(int)}.
	 * <p>
	 * Se utilizan de manera extensiva los atributos <code>up</code>,
	 * <code>down</code>, <code>left</code> y <code>right</code>:
	 * <ul>
	 * <li>Indican la dirección señalada en el main.</li>
	 * <li>Se utilizan valores enteros y no booleanos porque es necesario para los
	 * movimientos y las comprobaciones.</li>
	 * <li>Se selecciona cada dirección mediante un switch dependiendo del caracter
	 * sobrecargado.</li>
	 * </ul>
	 * <p>
	 * Para optimizar el uso del código, los métodos para sumar y mover las piezas
	 * están separados: Para sumar, se utiliza {@link #sumar(int, int, int, int)},
	 * para mover, se utiliza {@link #mover(int, int, int, int)}.
	 * <p>
	 * Al terminar la jugada, se comprueba si se ha llegado al final de la partida.
	 * De ser así, se termina la partida imprimiendo información por pantalla y se
	 * hace <code>System.exit()</code>.
	 * 
	 * @param c Caracter que determina el movimiento (w/s/a/d).
	 */
	public void Jugada(char c) {

		int[][] reserva = new int[tablero.length][tablero[0].length];
		for (int i = 0; i < tablero.length; i++)
			for (int j = 0; j < tablero.length; j++) {
				reserva[i][j] = getTab(i, j);
		}

		char d = String.format("%s", c).toLowerCase().charAt(0);
		// Se convierte la letra a minúsculas para evitar que, por cualquier motivo, se introduzca
		// un caracter en mayúsculas y el switch no lo detecte pese a que es un movimiento válido.

		int up = 0;
		int down = 0;
		int left = 0;
		int right = 0;

		switch (d) {
			case 'w': // ARRIBA
				up = 1;
				break;
			case 's': // ABAJO
				down = 1;
				break;
			case 'a': // IZQUIERDA
				left = 1;
				break;
			case 'd': // DERECHA
				right = 1;
				break;
			default: // otro valor (inválido)
				out.println("Jugada inválida.");
				break;
		}

		if (!(up == 0 && down == 0 && left == 0 && right == 0)) { // Se comprueba que se ha seleccionado
																  // alguna dirección.

			mover(up, down, left, right);
			out.println(this);

			sumar(up, down, left, right);

			// Para evitar que si el tablero está lleno el programa intente calcular
			// infinitamente un sitio vacío de manera aleatoria, se comprueba antes
			// que el tablero no esté lleno mediante #tableroLleno. Además, así la
			// siguiente ficha a colocar no varía.
			if (!tableroLleno()) {
				newFicha(getSiguiente()); // Se coloca la ficha 'siguiente'.
				newSiguiente(); // Se calcula el valor de ficha 'siguiente'.
			}

			boolean didChange = false;
			for (int i = 0; i < tablero.length; i++)
				for (int j = 0; j < tablero[0].length; j++) {
					if (reserva[i][j] != getTab(i, j))
						didChange = true;
				}
			if (didChange) addTurno();

			out.println(this);
			repaint();
			if (!ableToMove()) finalDePartida();
			// Si no se puede mover, se termina la partida.
		}
	}

	/**
	 * Método utilizado {@link #Jugada(char)} para mover las piezas. Se mueven las
	 * piezas hasta que hay otra pieza en la dirección o se encuentra con el borde
	 * del tablero. Después de mover las piezas, se comprueba si se pueden mover más
	 * las piezas mediante un recorrido de todo el tablero. Si se encuentra alguna
	 * pieza que se pueda mover, el atributo 'check' se convierte en true, con lo
	 * que se mantiene el bucle while y se vuelven a mover todas las piezas.
	 * Obviamente, aquellas piezas que no se puedan mover más en la dirección
	 * escogida, se quedarán en la misma casilla. Se introduce la dirección mediante
	 * los enteros 'up', 'down', 'left' y 'right'.
	 * 
	 * @param up    '1' o '0' para el movimiento hacia arriba.
	 * @param down  '1' o '0' para el movimiento hacia abajo.
	 * @param left  '1' o '0' para el movimiento hacia la izquierda.
	 * @param right '1' o '0' para el movimiento hacia la derecha.
	 */
	public void mover(int up, int down, int left, int right) {
		boolean check = true;
		while (check) {
			for (int i = up; i + down < tablero.length; i++)
				for (int j = left; j + right < tablero[0].length; j++) {
					if (getTab(i - up + down, j - left + right) == 0) {
						setTab(i - up + down, j - left + right, getTab(i, j));
						setTab(i, j, 0);
					}
				}

			check = false;
			// Con un solo if, si se encuentra una pieza que contenga un valor y en la
			// siguiente pieza
			// en la dirección seleccionada está vacía, significa que se puede seguir
			// moviendo el tablero.
			// De lo contrario, check se mantiene false por lo que se sale del bucle y se
			// termina el
			// movimiento de las piezas.
			for (int i = up; i + down < tablero.length; i++)
				for (int j = left; j + right < tablero[0].length; j++) {
					if (getTab(i, j) != 0 && (getTab(i - up + down, j - left + right) == 0))
						check = true;
				}
		}
	}

	/**
	 * Método utilizado por {@link #Jugada(char)} para sumar las piezas contiguas en
	 * la dirección seleccionada. Para detectar dichas sumas, se recorre todo el
	 * tablero examinando las piezas en la dirección seleccionada. Obviamente, si no
	 * se encuentra ninguna suma, no se suma nada.
	 * <p>
	 * Al terminar, el módulo vuelve a ejecutar {@link #mover(int, int, int, int)}.
	 * Aunque esto ya se haya hecho supuestamente en Jugada(), dependiendo del
	 * posicionamiento de las fichas puede quedar sitio para que la ficha se siga
	 * moviendo en la dirección seleccionada después de moverse. Se mueve
	 * directamente porque así se evitan bucles innecesarios y evitar que se añadan
	 * las siguientes fichas y que se imprima más veces de las necesarias
	 * información por pantalla. Además, se comprueba si la ficha que resulta de la
	 * suma es la mayor en el tablero a través de {@link #checkHighestValue(int)}.
	 * 
	 * @param up    '1' o '0' para el movimiento hacia arriba.
	 * @param down  '1' o '0' para el movimiento hacia abajo.
	 * @param left  '1' o '0' para el movimiento hacia la izquierda.
	 * @param right '1' o '0' para el movimiento hacia la derecha.
	 */
	public void sumar(int up, int down, int left, int right) {
		for (int i = up; i + down < tablero.length; i++)
			for (int j = left; j + right < tablero[0].length; j++) {
				if (sumaCond(i, j, up, down, left, right)) {
					setTab(i - up + down, j - left + right, getTab(i, j) + getTab(i - up + down, j - left + right));
					setTab(i, j, 0);
					addPuntos(getTab(i - up + down, j - left + right));
					setHighest(getTab(i - up + down, j - left + right));
				}
			}
		mover(up, down, left, right); // se mueve al terminar de suma
	}

	/**
	 * Genera un número nuevo aleatorio. Como especificado en el JavaDoc de la
	 * clase, <code>rand</code> es un SecureRandom, no un Random normal.
	 * 
	 * @param val un entero cualquiera.
	 * @return Un entero aleatorio entre 0 y val.
	 */
	public int newRandom(int val) {
		return this.rand.nextInt(val);
	}

	/**
	 * Genera un número para la siguiente ficha a generar. Se imprime por pantalla
	 * mediante {@link #toString()}. Genera un número entre 1 y 3 utilizando
	 * {@link #newRandom(int)}.
	 */
	public void newSiguiente() {
		setSiguiente(newRandom(3) + 1);
	}

	/**
	 * Comprueba que si el tablero está lleno. Si una casilla tiene un valor, se
	 * suma 1 al contador. Si al terminar la operación, el contador es igual a la
	 * cantidad de casillas, entonces el tablero está lleno.
	 * 
	 * @return Devuelve un booleano, 'true' si está lleno, 'false' si no.
	 */
	public boolean tableroLleno() {
		int check = 0;
		for (int i = 0; i < tablero.length; i++)
			for (int j = 0; j < tablero[0].length; j++) {
				if (getTab(i, j) != 0)
					check++;
			}
		return check == tablero.length * tablero[0].length;
	}

	/**
	 * Comprueba si se ha terminado la partida. Para esto, escanea el tablero
	 * repetidas veces con todas los posibles movimientos. Si en algún momento se
	 * detecta que hay una suma que se pueda hacer, el check devuelve 'true'. Si el
	 * tablero no está lleno, devuelve 'true'.
	 * <p>
	 * Se utiliza {@link #checkEndLoop(int, int, int, int)} para independizar el
	 * bucle y optimizar el código. También se utiliza
	 * {@link #sumaCond(int, int, int, int, int, int)} para verificar si la suma es
	 * posible o no.
	 * <p>
	 * Se utiliza {@link #tableroLleno()} como condición necesaria para seguir
	 * comprobando si la partida está acabada. Si el tablero no está lleno, es
	 * imposible que la partida esté terminada.
	 * 
	 * @return Valor 'booleano' definiendo si es posible algún movimiento.
	 */
	public boolean ableToMove() {
		boolean ableToMove = false;
		if (tableroLleno()) {
			if (checkEndLoop(1, 0, 0, 0)) ableToMove = true;
			if (checkEndLoop(0, 1, 0, 0)) ableToMove = true;
			if (checkEndLoop(0, 0, 1, 0)) ableToMove = true;
			if (checkEndLoop(0, 0, 0, 1)) ableToMove = true;
		} else ableToMove = true;
		return ableToMove;
	}

	/**
	 * Es el bucle perteneciente a checkEnd(). Comprueba si en alguna parte del
	 * tablero es posible. Al método se le sobrecarga con el movimiento del tablero
	 * que se desea comprobar.
	 * 
	 * @param up    Valor '1' o '0' para el movimiento hacia arriba.
	 * @param down  Valor '1' o '0' para el movimiento hacia atrás.
	 * @param left  Valor '1' o '0' para el movimiento hacia la izquierda.
	 * @param right Valor '1' o '0' para el movimiento hacia la derecha.
	 * @return Devuelve 'true' si se puede sumar alguna casilla y 'false' si no se
	 *         puede sumar ninguna.
	 */
	public boolean checkEndLoop(int up, int down, int left, int right) {
		boolean check = false;
		for (int i = up; i + down < tablero.length; i++)
			for (int j = left; j + right < tablero[0].length; j++) {
				if (sumaCond(i, j, up, down, left, right))
					check = true;
			}
		return check;
	}

	/**
	 * Condición que detecta si se puede sumar o no.
	 * 
	 * @param i    Posición x del tablero.
	 * @param j    Posición y del tablero.
	 * @param movx Dirección de la suma horizontal (si no hay, es igual a 0).
	 * @param movy Dirección de la suma vertical (si no hay, es igual a 0).
	 * @return Un booleano, 'true' si se puede sumar, 'false' si no.
	 */
	public boolean sumaCond(int i, int j, int up, int down, int left, int right) {
		return getTab(i - up + down, j - left + right) == getTab(i, j) && getTab(i, j) != 2 && getTab(i, j) != 1
				|| getTab(i, j) + getTab(i - up + down, j - left + right) == 3;
	}

	/**
	 * Coloca una nueva ficha en tablero. Para encontrar una poisición nueva,
	 * utiliza 'newRandom()'. Se comprueba que en la casilla no haya ya una ficha.
	 * 
	 * @param nV El valor que tendrá la nueva ficha.
	 */
	public void newFicha(int nV) {
		int nX = newRandom(tablero.length);
		int nY = newRandom(tablero[0].length);
		while (getTab(nX, nY) != 0) {
			nX = newRandom(tablero.length);
			nY = newRandom(tablero[0].length);
		}
		setTab(nX, nY, nV);
	}

	/**
	 * Método que imprime la información del tablero por pantalla, incluyendo la
	 * siguiente ficha y los puntos. Se utiliza entre los cálculos de
	 * {@link #Jugada(char)}.
	 * <p>
	 * Dependiendo del tamaño de la ficha máxima, es decir, de la cantidad de cifras
	 * que tenga, el tamaño por celda cambia. Mientras la ficha máxima no supere las
	 * tres cifras, el tamaño en celda estará ajustado para dos caracteres:
	 * <code>"  "</code> o <code>"%2d"</code>. De la misma manera, si la cantidad
	 * tiene tres cifras, estará ajustado para tres caracteres. Como la manera
	 * principal de input/output es la app gráfica, en el remoto caso de que el
	 * usuario supere las tres cifras, el tablero sigue siendo funcional pero con
	 * menores errores visuales.
	 */
	@Override
	public String toString() {

		String salida = String.format("%n%n╔");
		int times = String.valueOf(getHighest()).length();
		
		
		for (int i = 0; i + 1 < tablero[0].length; i++) {
			for (int x = 0; x < times; x++) {salida += "═";}
			salida += "╦";
		}
		for (int x = 0; x < times; x++) {salida += "═";}
		salida += String.format("╗%n");
		
		for (int i = 0; i < tablero.length; i++) {
			if (getTab(i, 0) == 0) {
				salida += "║";
				for(int x=0; x<times; x++) {salida += " ";}
				salida += "║";
			} else {
				salida += "║";
				for (int x = 0; x < times - String.valueOf(getTab(i, 0)).length(); x++) {salida += " ";}
				salida += String.format("%d", getTab(i, 0));
				salida += "║";
			}
			for (int j = 1; j < tablero[0].length; j++) {
				if (getTab(i, j) == 0) {
					for (int x = 0; x < times; x++) {salida += " ";}
					salida += "║";
				} 
				else {
					for (int x = 0; x < times - String.valueOf(getTab(i, j)).length(); x++) {
						salida += " ";
					}
					salida += String.format("%d", getTab(i, j));
					salida += "║";
				}
			}
			salida += String.format("%n");
		}
		salida += "╚";
		for (int i = 0; i + 1 < tablero[0].length; i++) {
			for (int x = 0; x < times; x++) {salida += "═";}
			salida += "╩";
		}
		for (int x = 0; x < times; x++) {salida += "═";}
		salida += String.format("╝%n");
		
		// Se imprime por pantalla información general sobre el estado acutal de la
		// partida.
		salida += String.format("Siguiente: %d%nPuntos: %d\tMáx: %d%nTurno [%d]", getSiguiente(), getPuntos(),
				getHighest(), getTurnos());
		return salida;
	}

	/**
	 * Método que se ejecuta al terminar la partida Para evitar que el entorno
	 * gráfico continúe ejecutándose, se utiliza <code>System.exit()</code> para
	 * cerrar el entorno con un código normal de finalización <code>0</code>. Para
	 * obtener la información, se utiliza {@link #getPuntos()},
	 * {@link #getHighest()}, {@link #getTurnos()}.
	 */
	public void finalDePartida() {
		String salida = String.format("Se ha terminado la partida.%nPuntuación final: %d%nFicha máxima: %d%nTurnos: %d%n", getPuntos(),
				getHighest(), getTurnos());
		JOptionPane.showMessageDialog(null, salida);
		out.printf("%n%s",salida);
		System.exit(0);
	}

	// ----------------------------------------------------------------------------------------------------
	private static final int anchoPantalla = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int altoPantalla = Toolkit.getDefaultToolkit().getScreenSize().height;
	private static final double scale = (double) altoPantalla / 720 < 0.5 ? 0.5 : (double) altoPantalla / 720;
	// Para evitar que en pantallas muy pequeñas no haya espacio entre piezas por el redondeo, la escala mínima es 0.5,
	// independientemente del tamaño de la pantalla. Se redondea después de multiplicar por la escala.
	
	private static final int MAIN_SPACER = (int) (50 * scale); // Espacio entre el tablero y el borde de la pantalla.
	private static final int SPOT_SPACER = (int) (2 * scale); // Espacio entre piezas.
	private static final int BOARD_SPACER = (int) (5 * scale); // Espacio entre el borde del tablero y las piezas.
	private static final int SQUARE_SIZE = (int) (40 * scale); // Tamaño de las piezas.
	private static final int ROUND_DIAMETER = (int) (10 * scale); // Radio del diámetro del arco.

	/**
	 * HashMap que contiene un valor con un color para cada clave asignada a los
	 * posibles valores de las fichas. Más información en la documentación de la
	 * clase.
	 * <p>
	 * Se inicializa en el constructor mediante {@link #inicializarColores()}
	 */
	private HashMap<Integer, Color> colores = new HashMap<>();

	/**
	 * Se inicializan los colores para las fichas por defecto. Es similar a los
	 * colores del enunciado.
	 */
	public void inicializarColores() {
		colores.put(1, Color.red);
		colores.put(2, Color.orange);
		colores.put(3, Color.cyan);
		colores.put(6, Color.blue);
		colores.put(12, Color.green);
		colores.put(24, Color.magenta);
		colores.put(48, Color.pink);
	}

	/**
	 * Método que se accede desde el main para comprobar si el tablero entra en pantalla con la resolución
	 * actual. Simplemente devuelve un valor booleano, la lógica se encuentra en el main.
	 * @return Valor booleano que establece si la pantalla generada entra o no en la resolución actual.
	 */
	public boolean checkValidSize() {
		return tablero[0].length * (SPOT_SPACER + SQUARE_SIZE) + 2 * BOARD_SPACER - SPOT_SPACER + 2 * MAIN_SPACER < anchoPantalla &&
				tablero.length * (SPOT_SPACER + SQUARE_SIZE) + 2 * BOARD_SPACER - SPOT_SPACER + 2 * MAIN_SPACER < altoPantalla; 
	}
	
	/**
	 * Método que calcula el ancho total final de la pantalla. Se utiliza en el main para definir la
	 * aplicación y también en diversos métodos dentro de esta clase para calcular posiciones respecto
	 * a los bordes de la ventana. Depende de la matriz tablero inicializada.
	 * @return Valor entero con el ancho de la ventana.
	 */
	public int defineX() {
		return tablero[0].length * (SPOT_SPACER + SQUARE_SIZE) + 2 * BOARD_SPACER - SPOT_SPACER + 2 * MAIN_SPACER;
	}

	/**
	 * Método que calcula el alto total final de la pantalla. Se utiliza en el main para definir la
	 * aplicación y también en diversos métodos dentro de esta clase para calcular posiciones respecto
	 * a los bordes de la ventana. Depende de la matriz tablero inicializada.
	 * @return Valor entero con el alto de la ventana.
	 */
	public int defineY() {
		return tablero.length * (SPOT_SPACER + SQUARE_SIZE) + 2 * BOARD_SPACER - SPOT_SPACER + 3 * MAIN_SPACER;
	}

	/**
	 * Método que optimiza el cambio del tamaño de la fuente. Puesto que siempre se utiliza ARIAL como
	 * fuente, no es necesario cambiar nada más y se acorta más de esta manera.
	 * @param g Entorno gráfico
	 * @param size Tamaño de la fuente a establecer
	 */
	public void setFontSize(Graphics g, int size) {
		g.setFont(new Font("Arial", Font.PLAIN, size));
	}
	


	/**
	 * Método que pinta la partida en la aplicación gráfica.
	 * <ul>
	 * <li>Para pintar las flechas, se utiliza {@link #pintarFlechas(Graphics)}. </li>
	 * <li>Para pintar el tablero, se utiliza {@link #pintarTablero(Graphics)}. </li>
	 * <li>Para pintar las fichas, se utiliza {@link #pintarFichas(Graphics)}. </li>
	 * <li>Para pintar la información, se utiliza {@link #pintarInfo(Graphics)}. </li>
	 * </ul>
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		pintarFlechas(g);
		pintarTablero(g);
		pintarFichas(g);
		pintarInfo(g);
	}
	
	/**
	 * Método que imprime las flechas que indican dónde hacer click para realizar un movimiento
	 * y en qué dirección, aunque sea bastante intuitivo de por sí. Los cálculos de la posición
	 * de las flechas son aproximados a ojo, pero se mantienen en cualquier con cualquier
	 * combinación de columnas, filas y resolución de pantalla.
	 * @param g Entorno gráfico
	 */
	public void pintarFlechas(Graphics g) {
		g.setColor(Color.blue);
		setFontSize(g, 16);
		g.drawString("↑", defineX() / 2, MAIN_SPACER * 14 / 24);
		g.drawString("←", MAIN_SPACER * 9 / 24, (defineY() - MAIN_SPACER) / 2);
		g.drawString("→", defineX() - MAIN_SPACER * 16 / 24, (defineY() - MAIN_SPACER) / 2);
		g.drawString("↓", defineX() / 2, defineY() - MAIN_SPACER * 35 / 24);
	}
	
	/**
	 * Método sencillo que imprime el tablero, es decir, un rectángulo blanco, en la aplicación
	 * gráfica. Para obtener las dimensiones del tablero, se tienen en cuenta los espaciados
	 * entre el tablero y el borde de la ventana, el tamaño de las piezas, la separación entre
	 * piezas y la separación entre las piezas y el tablero.
	 * @param g Entorno gráfico
	 */
	public void pintarTablero(Graphics g) {
		g.setColor(Color.white);
		g.fillRoundRect(MAIN_SPACER, MAIN_SPACER,
				tablero[0].length * (SPOT_SPACER + SQUARE_SIZE) + 2 * BOARD_SPACER - SPOT_SPACER,
				tablero.length * (SPOT_SPACER + SQUARE_SIZE) + 2 * BOARD_SPACER - SPOT_SPACER, ROUND_DIAMETER,
				ROUND_DIAMETER);
	}
	
	/**
	 * Método que imprime la siguiente ficha y los puntos actuales en la aplicación gráfica.
	 * Se obtiene el color de la siguiente ficha con el HashMap. Para obtener el valor de la
	 * próxima ficha, se utiliza {@link #getSiguiente()}.
	 * @param g Entorno gráfico
	 */
	public void pintarInfo(Graphics g) {
		g.setColor(Color.black);
		setFontSize(g, 15);
		g.drawString("Siguiente:", MAIN_SPACER, defineY() - MAIN_SPACER / 2);
		g.drawString(String.format("Puntos: %d", getPuntos()), defineX() - 2 * MAIN_SPACER,
				defineY() - MAIN_SPACER / 2);

		g.setColor(colores.get(getSiguiente()));
		g.fillRoundRect(MAIN_SPACER * 9 / 4, defineY() - MAIN_SPACER, SQUARE_SIZE, SQUARE_SIZE,
				ROUND_DIAMETER, ROUND_DIAMETER);
		g.setColor(Color.white);
		setFontSize(g, 18);
		g.drawString(String.format("%d", getSiguiente()), MAIN_SPACER * 123 / 48,
				defineY() - MAIN_SPACER / 2);
	}
	
	/**
	 * Método que pinta las fichas en pantalla.
	 * <p>
	 * Supuestamente después de pintar el tablero mediante {@link #pintarTablero(Graphics)} se
	 * pintan las fichas. Para esto, se examina el tablero entero. Si la posición en el tablero
	 * tiene valor <code>0</code>, no se pinta nada.
	 * <p>
	 * Para obtener los colores con los que se va a pintar las piezas, se utiliza un HashMap con
	 * los valores de las piezas como claves y los colores como valores. Si no existe una clave,
	 * se genera un nuevo color y se guarda, de modo que todas las futuras piezas con ese valor
	 * tengan el mismo color. Para esto, se utiliza {@link #newRandom(int)}.
	 * @param g Entorno gráfico
	 */
	public void pintarFichas(Graphics g) {
		for (int i = 0; i < tablero.length; i++)
			for (int j = 0; j < tablero[0].length; j++) {
				if (getTab(i, j) != 0) { // Al detectarse una pieza, se obtiene su color referente.
					if (colores.containsKey(getTab(i, j))) {
						g.setColor(colores.get(getTab(i, j)));
					} else { // Si no tiene un color predeterminado, se genera uno aleatorio.
						colores.put(getTab(i, j), new Color(newRandom(255), newRandom(255), newRandom(255)));
						g.setColor(colores.get(getTab(i, j)));
					}
					
					// Se pinta la pieza en sí.
					g.fillRoundRect(MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * j,
							MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * i, SQUARE_SIZE, SQUARE_SIZE,
							ROUND_DIAMETER, ROUND_DIAMETER);
					
					// Por último, se pinta el valor de la ficha.
					g.setColor(Color.white);
					setFontSize(g, 18 - 2 * (String.valueOf(getTab(i, j)).length() - 1));
					// Se establece un tamaño de fuente en función de los dígitos de la ficha.
					g.drawString(String.format("%d", getTab(i, j)),
							MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * j + SQUARE_SIZE * 13 / 32,
							SQUARE_SIZE * 5 / 8 + MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * i);
				}
			}
	}
	
	// -------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Recive pulsaciones de teclas del usuario. Si el usuario pulsa una tecla correspondiente a un
	 * movimiento (W/A/S/D o ARRIBA/IZQUIERDA/ABAJO/DERECHA), se ejecuta dicha jugada. Si el usuario
	 * pulsa escape, se termina la partida mediante {@link #SumaTres.finalDePartida()}. Con el
	 * objetivo de probar el rendimiento en circunstancias extremas del programa, al pulsar
	 * AVPAG/PAGE_DOWN se mandan jugadas constantes repetidas hasta que se termina el programa.
	 * 
	 * @see <a href="https://rules.sonarsource.com/java/RSPEC-131"> SonarLint: RSPEC-131</a>
	 * 		<blockquote>The requirement for a final default clause is defensive programming. The
	 * 		clause should either take appropriate action, or contain a suitable comment as to why
	 * 		no action is taken.</blockquote>
	 */
	private class KeyHandler extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent event) {
			requestFocus();
			requestFocusInWindow();
			switch (event.getKeyCode()) {
				case KeyEvent.VK_PAGE_DOWN:
					while (true) {
						Jugada('w');
						Jugada('a');
						Jugada('s');
						Jugada('d');
					}
				case KeyEvent.VK_ESCAPE:
					finalDePartida();
					break;
				case KeyEvent.VK_W:
				case KeyEvent.VK_UP:
					Jugada('w');
					break;
				case KeyEvent.VK_A:
				case KeyEvent.VK_LEFT:
					Jugada('a');
					break;
				case KeyEvent.VK_S:
				case KeyEvent.VK_DOWN:
					Jugada('s');
					break;
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT:
					Jugada('d');
					break;
				default:
					// En cualquier otro caso, no se hace nada.
					break;
			}
		}
	}

	/**
	 * Recive los clicks del ratón en la aplicación. Si se hace click en alguna de
	 * las cuatro direcciones, se llama a {@link #SumaTres.Jugada(char c)} con el
	 * caracter correspondiente a dicha jugada. Se utiliza MouseHandler en una clase
	 * separada como indicado en el enunciado del trabajo.
	 */
	private class MouseHandler extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent event) {
			requestFocusInWindow();
			if (event.getX() < MAIN_SPACER) {
				if (event.getY() > MAIN_SPACER) Jugada('a');
			} else {
				if (event.getY() < MAIN_SPACER) Jugada('w');
				else {
					if (event.getY() > (defineY() - 2*MAIN_SPACER) && event.getY() < (defineY() - MAIN_SPACER)) Jugada('s');
					else if (event.getX() > (defineX() - MAIN_SPACER)) Jugada('d');
				}
			}
		}
	}
}
