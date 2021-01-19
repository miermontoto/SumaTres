import static java.lang.System.out;
import java.security.SecureRandom;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
import java.awt.Toolkit;

/**
 * <h1> Clase principal del proyecto SumaTres </h1>
 * 
 * Se inicializa o bien con el constructor por defecto o bien sobrecargando dos
 * enteros que delimitan el tamaño del tablero.
 * 
 * <p>
 * 
 * Para determinar y almacenar el color de las piezas según su valor, se utiliza
 * un HashMap. El HashMap es necesario si se quiere almacenar todos los valores
 * predeterminados en {@link #inicializarColores()} y, además, se generan
 * colores aleatoriamente utilizando {@link #newRandom(int)} para los valores
 * que no están ya determinados.
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
 * Para generar la siguiente ficha que colocar en el tablero, la piscina de valores
 * que se pueden generar depende de la ficha máxima en el tablero en cada momento. 
 * 
 * <p>
 * 
 * Para la entrada, se utiliza MouseEvent y KeyEvent.

 * <p>
 * Cada vez que se genera una nueva pieza, aparece un rectángulo anaranjado alrededor
 * de la ficha para señalarla. Se guarda la posición de la nueva ficha en un vector y
 * luego se genera un rectángulo basado en ese vector.
 * 
 * <p>
 * 
 * En determinadas ocasiones, los valores de las fichas pueden hacer overflow y
 * convertirse en negativos. No merece la pena cambiar todo el programa para
 * corregir esto, ya que tan solo en jugadas automáticas se puede alcanzar esos valores.
 * 
 * <p> Code coverage: ~95% (v13) - Programas utilizados: Eclipse, VSCode.
 * <p> Desde la versión 14, el proyecto ya no forma parte del trabajo en grupo para 
 * 	   la EPI. La versión entregada fue 'v13'.
 * 
 * @author Juan Mier
 * @version v14
 * @see <a href="https://docs.oracle.com/javase/tutorial/java/data/numberformat.html">
 * 		Documentación de Oracle: Number Format </a>
 *      <blockquote>A new line character appropriate to the platform running the
 *      application. You should always use %n, rather than \n. </blockquote>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/security/SecureRandom.html">
 * 		Documentación de Oracle: SecureRandom </a>
 * @see <a href="https://rules.sonarsource.com/java/RSPEC-2119">
 * 		Regla SonarLint:2119 </a>
 *  	<blockquote>SecureRandom is preferred to Random</blockquote>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html">
 * 		Documentación de Oracle: HashMap </a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/awt/Toolkit.html">
 * 		Documentación de Oracle: Toolkit </a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html">
 * 		Documentación de Oracle: Tutorial de KeyListener </a>
 * @see <a href="https://www.github.com/GijonDev/SumaTres"> Repositorio de GitHub </a>
 * 
 */
public class SumaTres extends JPanel {

	/**
	 * Se incluye un serial generado aleatoriamente en vez de dejar que el
	 * compilador genere uno por defecto porque así se indica en la documentación de
	 * Oracle. Si no se declarara uno, podría generar excepciones como
	 * 'InvalidClassExceptions'.
	 * <p>
	 * El serial ha sido generado automáticamente por Eclipse. Si SumaTres no fuera
	 * una subclase de JPanel, no sería necesario declarar un serial. El serial debe
	 * de ser obligatoriamente final y de tipo 'double'. El programa no utiliza
	 * serialización explícitamente.
	 * 
	 * @see <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Serializable.html">
	 * 		Documentación de Oracle: Serializable</a>
	 */
	private static final long serialVersionUID = -1110032705510692144L;
	private int turnos = 1; // Se comienza en el primer turno, lógicamente.
	private int siguiente; // El valor se inicializa en el constructor.
	private int[][] tablero; // El tablero se inicializa en el constructor.
	private long puntos = 0; // Se comienza la partida con cero puntos.
	private int highestValue = 0; // Se podría inicializar con el valor '3', pero es preferible que se ajuste
								  // a lo que se declara en el constructor por si el usuario lo modifica.
	private SecureRandom rand = new SecureRandom();
	private int[] warning = new int[2]; // Vector que define la posición de la nueva ficha.
	
	// TODO: almacenar una cola de fichas siguientes
	// TODO: volver a la jugada anterior(con la nueva ficha en el mismo sitio)
	// TODO: cheat mode en el que se puede eliminar y colocar fichas al gusto
	// TODO: JButton?
	// TODO: mejora visual de la interfaz
	// TODO: debug escondido en el input del tamaño del programa
	// TODO: implementar probabilidades en la selección de la siguiente ficha para mejorar jugabilidad
	// TODO: cambiar tamaño puntuación en ventana gráfica
	// TODO: arreglar desplazamiento de la siguiente pieza
	// TODO: toggle información por consola
	

	/**
	 * Constructor de la clase sobrecargado por dos enteros. Se presupone que el
	 * main introduce tamaños correctos, aunque en caso de lanzar una excepción,
	 * se establecen los valores por defectos 5x5.
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
	 * <p>
	 * Dependiendo del tamaño del tablero establecido, se generan varios sets de piezas
	 * en el tablero para que siempre queden el mismo porcentaje de fichas en el tablero.
	 * 
	 * @param x: Cantidad de filas del tablero.
	 * @param y: Cantidad de columnas del tablero.
	 */
	public SumaTres(int x, int y) {
		tablero = new int[x][y];

		for(int i = 0; i < (int) (0.12 * tablero.length * tablero[0].length) / 3; i++) {
			generarSetFichas();
		}

		newSiguiente(); // Se establece la ficha 'siguiente' por primera vez.
		
		addKeyListener(new KeyHandler()); // El programa comienza a escuchar por pulsaciones de tecla.
		addMouseListener(new MouseHandler()); // El programa comienza a escuchar por clicks del usuario.
	
		inicializarColores(); // Se popula el HashMap de colores con los valores por defecto.
	}
	
	public void generarSetFichas() {
		newFicha(3);
		newFicha(2);
		newFicha(1);
	}

	// --- sets y gets --- //
	/**
	 * Devuelve el vector que define la posición de la nueva ficha.
	 * @return vector con las coordenadas.
	 */
	public int[] getWarning() {return this.warning;}
	
	/**
	 * Establece las coordenadas de la nueva ficha.
	 * @param x Coordenada x de la ficha
	 * @param y Coordenada y de la ficha
	 */
	public void setWarning(int x, int y) {this.warning[0] = x; this.warning[1] = y;}
	
	/**
	 * Devuelve la ficha de mayor valor actual.
	 * 
	 * @return entero con el valor actuales.
	 */
	public int getHighest() {return this.highestValue;}

	/**
	 * Comprueba y establece el mayor valor de cualquier ficha en el tablero.
	 * 
	 * @param valor que se quiera establecer.
	 */
	public void setHighest(int puntos) {if (puntos > this.getHighest()) this.highestValue = puntos;}

	/**
	 * Devuelve los puntos.
	 * 
	 * @return entero con los puntos actuales.
	 */
	public long getPuntos() {return this.puntos;}

	/**
	 * Añade puntos al contador actual.
	 * El valor tiene que ser <code>3</code> o superior. Es imposible
	 * que la suma de puntos sea inferior a esto.
	 * 
	 * @param puntos que se quieren sumar.
	 */
	public void addPuntos(int puntos) {if(puntos>=3) this.puntos += puntos;}

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
	 * Devuelve los turnos que se han jugado hasta el momento.
	 * 
	 * @return tipo 'entero' con la cantidad de turnos.
	 */
	public int getTurnos() {return this.turnos;}
	
	// No existe setTurnos() porque no es necesario, los turnos son un simple contador sin ninguna
	// implicación directa en la partida.
	
	// Tampoco existe setPuntos(), ya que no debería poder modificarse libremente. Se pueden añadir
	// puntos mediante addPuntos()
	// --- sets y gets --- //

	/**
	 * Método principal de la clase. Ejecuta el código que determina qué jugada
	 * hacer, mueve y suma las piezas.
	 * <p>
	 * Se mueven todas las piezas en la dirección seleccionada hasta encontrar otra
	 * pieza o hasta llegar al borde del tablero. Si la casilla destino es igual o
	 * suman 3 (1+2 || 2+1), se suman. Se utiliza {@link #sumaCond(int, int, Jugada)}
	 * para verificar si la suma es posible o no.
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
	 * Para indicar la dirección de la jugada, se utiliza un objeto de la clase
	 * 'Jugada', que está preparado para acortar condiciones y ser más limpio que
	 * valores separados para cada dirección del movimiento. Además, no es necesario
	 * comprobar que los valores de la jugada sean válidos.
	 * <p>
	 * Para optimizar el uso del código, los métodos para sumar y mover las piezas
	 * están separados: Para sumar, se utiliza {@link #sumar(Jugada)},
	 * para mover, se utiliza {@link #mover(Jugada)}.
	 * <p>
	 * Al terminar la jugada, se comprueba si se ha llegado al final de la partida.
	 * De ser así, se termina la partida imprimiendo información por pantalla y se
	 * hace <code>System.exit()</code> con valor <code>0</code>.
	 * <p>
	 * Solo se imprime información extra al terminar la jugada mediante
	 * {@link #printExtraInfo()} para evitar que el programa utilice métodos que no
	 * son necesarios, ya que a media jugada no es necesario saber el turno ni la ficha
	 * siguiente ni la puntuación. Para ajustarse a este cambio, en el método main se
	 * imprime la información extra para mostrar correctamente el tablero por primera
	 * vez.
	 * 
	 * @param c Caracter que determina el movimiento (w/s/a/d).
	 */
	public void Jugada(char c) {

		// Se genera una copia del tablero para comparar después de realizar la jugada.
		// Si al compararse el tablero nuevo con su posición anterior resulta que ambos
		// son iguales, significa que el nuevo tablero no se ha visto modificado, por lo
		// que no debería añadirse un turno al contador.
		int[][] reserva = new int[tablero.length][tablero[0].length];
		for (int i = 0; i < tablero.length; i++)
			for (int j = 0; j < tablero.length; j++) {
				reserva[i][j] = getTab(i, j);
			}
	
		Jugada x = new Jugada(c);	
			
		mover(x);
		//out.println(this);
		sumar(x);
			
		// Para evitar que si el tablero está lleno el programa intente calcular
		// infinitamente un sitio vacío de manera aleatoria, se comprueba antes
		// que el tablero no esté lleno mediante tableroLleno(). Además, así la
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
		if (didChange) addTurno(); // Si el tablero ha cambiado, se añade un turno.
			
		//out.print(this);
		//out.println(printExtraInfo()); // Se imprime información extra.
		repaint(); // Se pinta la situación actual de la partida.
		if (!ableToMove()) finalDePartida(); // Si no se puede mover, se termina la partida.
	}
	

	/**
	 * Método utilizado por {@link #Jugada(char)} para mover las piezas. Se mueven las
	 * piezas hasta que hay otra pieza en la dirección o se encuentra con el borde
	 * del tablero. Después de mover las piezas, se comprueba si se pueden mover más
	 * las piezas mediante un recorrido de todo el tablero. Si se encuentra alguna
	 * pieza que se pueda mover, el atributo 'check' se convierte en true, con lo
	 * que se mantiene el bucle while y se vuelven a mover todas las piezas.
	 * Obviamente, aquellas piezas que no se puedan mover más en la dirección
	 * escogida, se quedarán en la misma casilla.
	 * 
	 * @param x Objeto de la clase 'Jugada' que define el movimiento.
	 */
	public void mover(Jugada x) {
		boolean check = true;
		while (check) {
			for (int i = x.getUp() + x.getDown()*(tablero.length - 2); i < tablero.length && i >= 0; i += 1 -2*x.getDown())
				for (int j = x.getLeft() + x.getRight()*(tablero[0].length - 2); j < tablero[0].length && j >= 0; j += 1 - 2*x.getRight()) {
					if (getTab(i + x.moveVert(), j + x.moveHorz()) == 0) {
						setTab(i + x.moveVert(), j + x.moveHorz(), getTab(i, j));
						setTab(i, j, 0);
					}
				}

			check = false;
			// Con un solo if, si se encuentra una pieza que contenga un valor y en la
			// siguiente pieza en la dirección seleccionada está vacía, significa que
			// se puede seguir moviendo el tablero.
			// De lo contrario, check se mantiene false por lo que se sale del bucle y se
			// termina el movimiento de las piezas.
			for (int i = x.getUp() + x.getDown() * (tablero.length - 2); i < tablero.length && i >= 0; i += 1 - 2 * x.getDown())
				for (int j = x.getLeft() + x.getRight() * (tablero[0].length - 2); j < tablero[0].length && j >= 0; j += 1 - 2 * x.getRight()) {
					if (getTab(i, j) != 0 && (getTab(i + x.moveVert(), j + x.moveHorz()) == 0))
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
	 * Al terminar, el módulo vuelve a ejecutar {@link #mover(Jugada)}.
	 * Aunque esto ya se haya hecho supuestamente en Jugada(), dependiendo del
	 * posicionamiento de las fichas puede quedar sitio para que la ficha se siga
	 * moviendo en la dirección seleccionada después de moverse. Se mueve
	 * directamente porque así se evitan bucles innecesarios y evitar que se añadan
	 * las siguientes fichas y que se imprima más veces de las necesarias
	 * información por pantalla.
	 * <p>
	 * Se comprueba si la ficha que resulta de la suma es la mayor en el tablero a
	 * través de {@link #setHighest(int)}.
	 * 
	 * @param x Objeto de la clase 'Jugada' que define el movimiento.
	 */
	public void sumar(Jugada x) {
		for (int i = x.getUp() + x.getDown() * (tablero.length - 2); i < tablero.length && i >= 0; i += 1 - 2 * x.getDown())
			for (int j = x.getLeft() + x.getRight() * (tablero[0].length - 2); j < tablero[0].length && j >= 0; j += 1 - 2 * x.getRight()) {
				if (sumaCond(i, j, x)) { // Si se puede sumar, se convierte la nueva casilla en la suma y la antigua en 0.
					setTab(i + x.moveVert(), j + x.moveHorz(), getTab(i, j) + getTab(i + x.moveVert(), j + x.moveHorz()));
					setTab(i, j, 0);
					addPuntos(getTab(i + x.moveVert(), j + x.moveHorz()));
					setHighest(getTab(i + x.moveVert(), j + x.moveHorz()));
					// NO se establece directamente la mayor pieza, se comprueba dentro de setHighest().
				}
			}
		mover(x);
		// Se mueve al terminar de suma para evitar que queden huecos vacíos. NO se suma dos veces.
		
		// Definitivamente hay algo incorrecto en la lógica de la suma. En casos muy aislados y extremos,
		// hay veces que no se suma todo lo que debería. La verdad es que no tengo ni idea de por qué
		// pasa esto, y ya son varias las revisiones que he hecho a los bucles que recorren la matriz.
		// Tal y como está ahora mismo, todos los bucles se invierten para los movimientos hacia abajo y
		// hacia la derecha, para evitar que se sumen duplicadamente las piezas en algunas situaciones.
		// Esto tiene todo el sentido del mundo en la teoría, pero en la práctica causa que no se sumen
		// algunas piezas. Se puede comprobar fácilmente el funcionamiento del programa colocando piezas
		// manualmente mediante 'debugColocarPieza()'.
	}
	
	/**
	 * Método debug que coloca piezas manualmente. NO es accesible por el usuario. <p>
	 * Antes de colocar la ficha, comprueba  que se trata de una ficha válida y que
	 * no se trata de una posición OutOfBounds para evitar exepciones. Además, no se
	 * puede colocar una pieza donde ya hay otra. <p>Se ha utilizado durante el
	 * desarrollo del programa.
	 * 
	 * @param x Coordenada horizontal de la ficha que se desea colocar
	 * @param y Coordenada vertical de la ficha que se desea colocar
	 * @param nv Valor de la pieza que se desea colocar.
	 */
	public void debugColocarPiezas(int x, int y, int nv) {
		if (x >= 0 && y >= 0 && x < tablero.length && y < tablero.length && getTab(x, y) == 0) {
			if (nv == 1 || nv == 2 || nv == 3) {setTab(x, y, nv);}
			else {
				int i = 0;
				while(nv>6*Math.pow(2, i)) {i++;}
				if(6*Math.pow(2, i) == nv) {setTab(x, y, nv);}
			}
		}
	}
	
	/**
	 * Método debug que quita piezas manualmente. NO es accesible por el usuario. <p>
	 * Obviamente, solo se puede quitar el valor de una ficha que existe. Comprueba
	 * que se trata de una ficha válida y que no se trata de una posición OutOfBounds
	 * para evitar exepciones.
	 * 
	 * @param x Coordenada horizontal de la ficha que se quiere quitar.
	 * @param y Coordenada vertical de la ficha que se quiere quitar.
	 */
	public void debugQuitarPiezas(int x, int y) {
		if (x >= 0 && y >= 0 && x < tablero.length && y < tablero.length && getTab(x, y) != 0) {
			setTab(x, y, 0);
		}
	}

	/**
	 * Genera un número nuevo aleatorio. Como especificado en el JavaDoc de la
	 * clase, <code>rand</code> es un SecureRandom, no un Random normal.
	 * 
	 * @param val un entero cualquiera.
	 * @return Un entero aleatorio entre 0 y val-1.
	 */
	public int newRandom(int val) {
		return this.rand.nextInt(val);
	}

	/**
	 * Genera un número para la siguiente ficha a colocar. Se imprime por pantalla
	 * mediante {@link #toString()}. Genera un número entre 1 y 3 utilizando
	 * {@link #newRandom(int)}. Es el método clásico que sigue con las reglas originales
	 * del enunciado.
	 */
	public void newSiguienteOld() {
		setSiguiente(newRandom(3) + 1);
		//obtainedFromRandom[getSiguiente()-1]++;
	}
	
	/**
	 * Genera un número para la siguiente ficha a colocar. La diferencia con {@link #newSiguiente()}
	 * es que se generan valores progresivamente mayores a lo largo del transcurso del programa acordes
	 * con el valor de la máxima ficha en pantalla. <p>
	 * Para generar el siguiente valor, se define un vector y se rellena con los posibles valores.
	 * Obviamente, el 1, 2 y 3 siempre se introducen por defecto. Además, hasta que no se llega al valor
	 * 24, solo se puede sacar uno de esos tres valores. <p> A partir de 24, la siguiente ficha posible
	 * se añade a la pool, es decir, 6. Al llegar a la 98, se añade 12, etc.
	 */
	public void newSiguiente() {
		int i = 0;
		while(getHighest()>6*Math.pow(2, i)) {i++;}
		int[] vlsSig = new int[getHighest()<13 ? 3 : i+1]; // Si la ficha no supera 12, el método de
														   // obtener el valor de la siguiente ficha
														   // es el clásico.
		
		for(int h=0; h<3; h++) {vlsSig[h] = h+1;} // Se introducen 1, 2 y 3 en el vector de posibles
												  // valores.
		
		for(int j=0; j < vlsSig.length - 3; j++) {
			vlsSig[3+j] = (int) (6 * Math.pow(2, j)); 
		}
		

		setSiguiente(vlsSig[newRandom(vlsSig.length)]);
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
			if (checkEndLoop(new Jugada('w')) || checkEndLoop(new Jugada('s'))
				|| checkEndLoop(new Jugada('a')) || checkEndLoop(new Jugada('d'))) ableToMove = true;
		} else ableToMove = true;
		return ableToMove;
	}

	/**
	 * Es el bucle perteneciente a {@link #ableToMove()}. Comprueba si en alguna parte
	 * del tablero es posible mover o sumar. Al método se le sobrecarga con el movimiento
	 * del tablero que se desea comprobar.
	 * 
	 * @param x Objeto de la clase 'Jugada' que define el movimiento.
	 * @return Devuelve 'true' si se puede sumar alguna casilla y 'false' si no se
	 *         puede sumar ninguna.
	 */
	public boolean checkEndLoop(Jugada x) {
		boolean check = false;
		for (int i = x.getUp(); i + x.getDown() < tablero.length; i++)
			for (int j = x.getLeft(); j + x.getRight() < tablero[0].length; j++) {
				if (sumaCond(i, j, x))
					check = true;
			}
		return check;
	}

	/**
	 * Condición que detecta si se puede sumar o no.
	 * <p>
	 * Para evitar que se introduzcan movimientos inválidos,
	 * {@link #validMovement(int, int, int, int)} está incluído en el condicional.
	 * 
	 * @param i     Posición x del tablero.
	 * @param j     Posición y del tablero.
	 * @param x Objeto de la clase 'Jugada' que define el movimiento.
	 * @return Un booleano, 'true' si se puede sumar, 'false' si no.
	 */
	public boolean sumaCond(int i, int j, Jugada x) {
		return getTab(i + x.moveVert(), j + x.moveHorz()) == getTab(i, j) && getTab(i, j) != 2 && getTab(i, j) != 1
				|| getTab(i, j) + getTab(i + x.moveVert(), j + x.moveHorz()) == 3;
	}

	/**
	 * Coloca una nueva ficha en tablero. Para encontrar una poisición nueva, utiliza
	 * {@link #newRandom(int)}. Se comprueba que en la casilla no haya ya una ficha.
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
		setWarning(nX, nY);
	}

	/**
	 * Método que devuelve una cadena con la situación actual del tablero.
	 * <h3> NO se utiliza en versiones fuera del trabajo de grupo. </h3>
	 * <p>
	 * Dependiendo del tamaño de la ficha máxima, es decir, de la cantidad de cifras
	 * que tenga, el tamaño por celda cambia. Mientras la ficha máxima no supere las
	 * tres cifras, el tamaño en celda estará ajustado para dos caracteres:
	 * <code>"  "</code> o <code>"%2d"</code>. De la misma manera, si la cantidad
	 * tiene tres cifras, estará ajustado para tres caracteres. Como la manera
	 * principal de input/output es la app gráfica, en el remoto caso de que el
	 * usuario supere las tres cifras, el tablero sigue siendo funcional pero con
	 * menores errores visuales.
	 * <p>
	 * La información extra sobre la situación actual de la partida se devuelve
	 * mediante el método {@link #printExtraInfo()}.
	 */
	@Override
	public String toString() {
		
		int times = String.valueOf(getHighest()).length();
		// Se examina la cantidad de cifras que tiene la cifra más grande.
		// En función de esto, posteriormente se imprime la matriz de manera
		// que todas las casillas tienen el mismo tamaño, ajustado para la
		// ficha con mayor valor.

		// Por cómo está hecho el programa, es preferible imprimir los saltos de
		// línea al principio del toString() en vez de al final para hacer más visible
		// el tablero en consola cuando no se está haciendo ninguna jugada. El
		// resto del programa está ajustado para que los saltos de línea concatenen con
		// este y tenga sentido el output por consola.
		String salida = String.format("%n%n╔");
		// Se comienza a construir la matriz, empezando por la esquina superior izquierda.
		
		
		// Se imprime el borde superior de la matriz.
		for (int i = 0; i + 1 < tablero[0].length; i++) {
			for (int x = 0; x < times; x++) {salida += "═";}
			salida += "╦";
		}
		for (int x = 0; x < times; x++) {salida += "═";}
		salida += String.format("╗%n"); // Se imprime la esquina superior y se salta de línea.
		
		
		
		for (int i = 0; i < tablero.length; i++) {
			salida += "║"; // Para la primera columna, se imprime el borde izquierdo de la matriz.
			for (int j = 0; j < tablero[0].length; j++) { // Se imprime la matriz en sí.
				if (getTab(i, j) == 0) {
					// Si la casilla está vacía, se imprime un hueco en blanco.
					for (int x = 0; x < times; x++) {salida += " ";}
					salida += "║";
				} else {
					// De lo contrario, se imprime el valor de la casilla ajustado al tamaño de la celda.
					for (int x = 0; x < times - String.valueOf(getTab(i, j)).length(); x++) {salida += " ";}
					salida += String.format("%d", getTab(i, j));
					salida += "║";
				}
			}
			salida += String.format("%n"); // Salto de línea para seguir construyendo la matriz.
		}
		salida += "╚"; // Borde inferior izquierdo de la matriz.
		// Se imprime el borde inferior de la matriz.
		for (int i = 0; i + 1 < tablero[0].length; i++) {
			for (int x = 0; x < times; x++) {salida += "═";}
			salida += "╩";
		}
		for (int x = 0; x < times; x++) {salida += "═";}
		salida += String.format("╝%n"); // Por último, se imprime el borde inferior derecho y se salta
										// de línea para terminar la construcción de la matriz.
		return salida;
		// Para optimizar el código en tableros grandes, no se imprimen separaciones entre filas de la
		// matriz, aunque bastaría con un bucle similar al de los bordes superiores e inferiores.
	}
	
	/**
	 * Método que devuelve una cadena con información extra sobre la partida.
	 * Devuelve la ficha que se va a colocar aleatoriamente a continuación,
	 * la cantidad de puntos acumulada hasta ahora, la ficha máxima en pantalla
	 * y el número del turno.
	 * @return Cadena con la información.
	 */
	public String printExtraInfo() {
		return String.format("Siguiente [%d]%nPuntos [%d]\tMáx [%d]"
				+ "%nTurno [%d]",
				getSiguiente(), getPuntos(), getHighest(), getTurnos());
	}

	/**
	 * Método que se ejecuta al terminar la partida Para evitar que el entorno
	 * gráfico continúe ejecutándose, se utiliza <code>System.exit()</code> para
	 * cerrar el entorno con un código normal de finalización <code>0</code>. Para
	 * obtener la información, se utiliza {@link #getPuntos()},
	 * {@link #getHighest()}, {@link #getTurnos()}. <p>
	 * Además, se imprimen la cantidad de unos, doses o treses por consola, para 
	 * comprobar que dicha cantidad es más o menos la misma y que no hay ningún
	 * tipo de manipulación, pese a lo que pueda parecer según se juega.
	 */
	public void finalDePartida() {
		String salida = String.format("Se ha terminado la partida.%nPuntuación final: %d%nFicha máxima: %d%nTurnos: %d%n", getPuntos(),
				getHighest(), getTurnos());
		JOptionPane.showMessageDialog(null, salida);
		out.printf("%n%s",salida);
		//out.printf("Fichas obtenidas: [1]: %d  [2]: %d  [3]: %d",
		//		obtainedFromRandom[0], obtainedFromRandom[1], obtainedFromRandom[2]);
		System.exit(0); // Se termina con estado '0' para indicar que se termina correctamente.
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
	 * Se popula en el constructor mediante {@link #inicializarColores()}
	 */
	private HashMap<Integer, Color> colores = new HashMap<>();

	/**
	 * Método que inicializa los colores para las fichas por defecto.
	 * Idealmente, solo debería utilizarse en el único constructor.
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
	 * Método que pinta la partida en la aplicación gráfica. El programa NO se basa en
	 * {@link #toString()} para pintar el tablero, con lo que no siempre existe la misma
	 * información en ambas salidas.
	 * <ul>
	 * <li>Para pintar las flechas, se utiliza {@link #pintarFlechas(Graphics)}. </li>
	 * <li>Para pintar el tablero, se utiliza {@link #pintarTablero(Graphics)}. </li>
	 * <li>Para pintar las fichas, se utiliza {@link #pintarFichas(Graphics)}. </li>
	 * <li>Para pintar la información, se utiliza {@link #pintarInfo(Graphics)}. </li>
	 * </ul>
	 */
	@Override
	public void paintComponent(Graphics g) {
		requestFocusInWindow(); // Se pide el focus de la ventana al pintar para evitar pérdidas
								// de pulsaciones de teclado, especialmente al comenzar la partida.
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
	 * Supuestamente, después de pintar el tablero mediante {@link #pintarTablero(Graphics)} se
	 * pintan las fichas. Para esto, se examina el tablero entero. Si la posición en el tablero
	 * tiene valor <code>0</code>, no se pinta nada.
	 * <p>
	 * Para obtener los colores con los que se va a pintar las piezas, se utiliza un HashMap con
	 * los valores de las piezas como claves y los colores como valores. Si no existe una clave,
	 * se genera un nuevo color y se guarda, de modo que todas las futuras piezas con ese valor
	 * tengan el mismo color. Para esto, se utiliza {@link #newRandom(int)}. Esto puede causar
	 * que el color de algunas piezas sea muy similar al de algunos valores predeterminados, o
	 * que la fuente blanca no se vea encima al imprimir el valor de la ficha.
	 * <p>
	 * Dependiendo de la cantidad de dígitos que tiene una ficha, su valor se imprime desplazado
	 * hacia la izquierda para centrar el valor de las fichas más grandes.
	 * 
	 * @param g Entorno gráfico
	 */
	public void pintarFichas(Graphics g) {
		for (int i = 0; i < tablero.length; i++)
			for (int j = 0; j < tablero[0].length; j++) {
				if (getTab(i, j) != 0) { // Al detectarse una pieza, se obtiene su color referente.
					if (colores.containsKey(getTab(i, j))) {
						g.setColor(colores.get(getTab(i, j)));
					} else { // Si no tiene un color predeterminado, se genera uno aleatorio.
						colores.put(getTab(i, j), new Color(newRandom(256), newRandom(256), newRandom(256)));
						g.setColor(colores.get(getTab(i, j)));
					}
					
					// Se pinta la pieza en sí.
					g.fillRoundRect(MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * j,
							MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * i, SQUARE_SIZE, SQUARE_SIZE,
							ROUND_DIAMETER, ROUND_DIAMETER);
					
					// 'Y' es la luminosidad del color de la ficha.
					double Y = (0.2126*g.getColor().getRed() + 0.7152*g.getColor().getGreen() + 0.0722*g.getColor().getBlue());
					
					// Si la pieza es demasiado clara, se le pinta un reborde para que se aprecie.
					if(Y>=210) {
						g.setColor(Color.gray);
						g.drawRoundRect(MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * j,
								MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * i, SQUARE_SIZE, SQUARE_SIZE,
								ROUND_DIAMETER, ROUND_DIAMETER);
					}
					
					// Si no se está en el primer turno, se imprime un rectángulo alrededor de la nueva ficha
					// para diferenciarla. (debería haber alguna manera mejor de señalarla?)
					if(getTurnos() > 1) {
						g.setColor(Color.yellow);
						g.drawRoundRect(MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * getWarning()[1],
								MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * getWarning()[0], SQUARE_SIZE, SQUARE_SIZE,
								ROUND_DIAMETER, ROUND_DIAMETER);
					}
					
					// Por último, se pinta el valor de la ficha.
				    // Si la luminosidad pasa de un cierto valor, el color de la fuente del valor
					// de la ficha debería ser negro, de lo contrario es blanco. Si la ficha es la
					g.setColor(Y>=210 ? Color.black : Color.white);
					
					// Se establece un tamaño de fuente en función de los dígitos de la ficha.
					int desiredFontSize = 18 - 1 * (String.valueOf(getTab(i, j)).length() - 1);
					setFontSize(g, getTab(i, j) >= 350000 ? 10 : desiredFontSize);
					int sizer = getTab(i, j) >= 100000 ? (13 - (String.valueOf(getTab(i, j)).length()-1)) : (13 - (2*(String.valueOf(getTab(i, j)).length()-1)));
					g.drawString(String.format("%d", getTab(i, j)),
							MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * j + SQUARE_SIZE *
							 sizer / 32,
							SQUARE_SIZE * 5 / 8 + MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * i);
					// Dependiendo del tamaño de la pieza, se desplaza ligeramente a la izquierda para que siga centrada
					// en concordancia con el resto de piezas.
				}
			}
	}
	
	/**
	 * <h2>Clase que define las jugadas del juego en el tablero.</h2>
	 * 
	 * Contiene valores para cada sentido del movimiento bidimensional. Por defecto,
	 * todos los movimientos tienen valor '0'. Cuando se define un movimiento, dicho
	 * movimiento pasa a tener valor '1'. Esto permite que el programa funcione de
	 * la misma manera que con valores separados como se hacía antes sin necesidad
	 * de comprobar que la jugada sea válida, puesto que la clase Jugada solo puede
	 * definir jugadas válidas.
	 * <p>
	 * El constructor por defecto utiliza un caracter que coincide con las teclas
	 * con las que se quiere jugar al programa. Podría implementarse fácilmente
	 * otros constructores que definieran la jugada a partir cadenas o de enteros,
	 * pero solo se utiliza el método más corto.
	 * <p>
	 * Si se introduce una jugada inválida, el programa se cierra, puesto que no
	 * endría sentido que se introdujera una jugada con varios movimientos (?)
	 * <p>
	 * Contiene dos métodos (<code>moveVert()</code> y <code>moveHorz()</code>).
	 * Estos dos métodos existen para que las detecciones de movimiento sean más
	 * cortas y más sencillas, ya que todas usan el desplazamiento de la misma
	 * manera.
	 */
	private class Jugada {

		private int up = 0;
		private int down = 0;
		private int left = 0;
		private int right = 0;

		public Jugada(char c) {
			switch (Character.toString(c).toLowerCase().charAt(0)) {
				case 'w': this.Up(); break;
				case 'a': this.Left(); break;
				case 's': this.Down(); break;
				case 'd': this.Right(); break;
				default:
					out.print("ERROR: Jugada inválida.");
					System.exit(0);
					break;
			}
		}

		/**
		 * Establece el movimiento de la jugada hacia arriba.
		 */
		public void Up() {this.up = 1;}
		
		/**
		 * Establece el movimiento de la jugada hacia abajo.
		 */
		public void Down() {this.down = 1;}
		
		/**
		 * Establece el movimiento de la jugada hacia la izquierda.
		 */
		public void Left() {this.left = 1;}
		
		/**
		 * Establece el movimiento de la jugada hacia la derecha.
		 */
		public void Right() {this.right = 1;}
		
		/**
		 * Devuelve el valor del movimiento hacia arriba de la jugada.
		 * @return Valor entero
		 */
		public int getUp() {return this.up;}
		
		/**
		 * Devuelve el valor del movimiento hacia abajo de la jugada.
		 * @return Valor entero
		 */
		public int getDown() {return this.down;}
		
		/**
		 * Devuelve el valor del movimiento hacia la izquierda de la jugada.
		 * @return Valor entero
		 */
		public int getLeft() {return this.left;}
		
		/**
		 * Devuelve el valor del movimiento hacia la derecha de la jugada.
		 * @return Valor entero
		 */
		public int getRight() {return this.right;}
		
		/**
		 * Devuelve el movimiento horizontal de la jugada.
		 * @return Valor entero
		 */
		public int moveVert() {return this.getDown() - this.getUp();}
		
		/**
		 * Devuelve el movimiento horizontal de la jugada.
		 * @return Valor entero
		 */
		public int moveHorz() {return this.getRight() - this.getLeft();}
	}

	
	// -------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Recive pulsaciones de teclas del usuario. Si el usuario pulsa una tecla correspondiente a un
	 * movimiento (W/A/S/D o ARRIBA/IZQUIERDA/ABAJO/DERECHA), se ejecuta dicha jugada. Si el usuario
	 * pulsa escape, se termina la partida mediante {@link #SumaTres.finalDePartida()}. <p> Con el
	 * objetivo de probar el rendimiento en circunstancias extremas del programa, al pulsar
	 * AVPAG/PAGE_DOWN junto a la tecla 'control', se mandan jugadas constantes repetidas hasta que se
	 * termina el programa.
	 * 
	 * @see <a href="https://rules.sonarsource.com/java/RSPEC-131"> SonarLint: RSPEC-131 </a>
	 * 		<blockquote>The requirement for a final default clause is defensive programming. The
	 * 		clause should either take appropriate action, or contain a suitable comment as to why
	 * 		no action is taken.</blockquote>
	 */
	private class KeyHandler extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent event) {
			switch (event.getKeyCode()) {
				case KeyEvent.VK_PAGE_DOWN: // Esto rompe varios paradigmas de la programación.
					if (event.isControlDown()) {
						while (!event.isAltDown()) {
							// Esto arregla que el bucle no tenga una condición de salida,
							// aunque la condición sea imposible de cumplir una vez dentro
							// del bucle.
							Jugada('w');
							Jugada('a');
							Jugada('s');
							Jugada('d');
						}
					}
					break;
				case KeyEvent.VK_ESCAPE:
					if (JOptionPane.showConfirmDialog(null, "¿Desea salir de la partida?", "SumaTres", JOptionPane.YES_NO_OPTION)
							== JOptionPane.YES_OPTION) finalDePartida();
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
			if (event.getX() < MAIN_SPACER) {
				if (event.getY() > MAIN_SPACER) Jugada('a');
			} else {
				if (event.getY() < MAIN_SPACER) Jugada('w');
				else {
					if (event.getY() > (defineY() - 2 * MAIN_SPACER)
							&& event.getY() < (defineY() - MAIN_SPACER)) Jugada('s');
					else if (event.getX() > (defineX() - MAIN_SPACER)) Jugada('d');
				}
			}
		}
	}
	
	
}
