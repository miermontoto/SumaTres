package game;

import obj.Jugada;
import obj.Tablero;
import obj.Turno;
import obj.Settings;
import util.Dialog;
import util.Paint;
import obj.Pieza;
import handler.Mouse;
import handler.Keyboard;

import static java.lang.System.out;
import static java.lang.System.err;
import java.awt.event.*;
import java.awt.Graphics;
import java.io.File;
import java.security.SecureRandom;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JPanel;


/**
 * <h2> Clase principal del proyecto SumaTres </h2>
 * 
 * Se inicializa sobrecargando tres enteros que delimitan el tamaño del tablero
 * y el modo de juego, respectivamente.
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
 * Para la entrada, se utiliza MouseEvent y KeyEvent, que perimte movimientos
 * mediante teclado y mediante ratón.
 * 
 * <p>
 * 
 * Cada vez que se genera una nueva pieza, aparece un rectángulo anaranjado
 * alrededor de la ficha para señalarla. Se guarda la posición de la nueva ficha
 * en un vector y luego se genera un rectángulo basado en ese vector.
 * 
 * <p>
 * 
 * En determinadas ocasiones, los valores de las fichas pueden hacer overflow y
 * convertirse en negativos. No merece la pena cambiar todo el programa para
 * corregir esto, ya que tan solo en jugadas automáticas se puede alcanzar esos
 * valores.
 * 
 * <p>
 * 
 * Si no se han activado los trucos y está activado el modo experimental, el
 * programa guarda información de la partida al terminar en 
 * "resultados.txt" en el directorio actual.
 * 
 * <p>
 * 
 * Personalmente, recomiendo jugar en modo 'experimental' con un tablero pequeño,
 * como 5x5. Esto genera partidas más fluidas y cortas. El modo clásico es ligeramente
 * más frustrante, ya que no hay tantas opciones de movimiento ni de ayudas. Cuanto
 * más grande sea el tablero, más difícil es llegar al final de la partida. En pantallas
 * de 1920x1080, el tamaño máximo del tablero es de 14x29. Estas partidas suelen tardar
 * entre 20000 y 100000 turnos en terminar, por lo que el objetivo no es llegar al final,
 * sino alcanzar la máxima puntuación y la ficha máxima más grande posible.
 * 
 * <p>
 * 
 * De una manera relativamente rápida, se calcula la distancia entre los colores
 * ya instalados y el nuevo color, en caso de que se necesite. Esto se utiliza para
 * evitar que se generen colores muy similares a los ya presentes. En la realidad,
 * los cálculos necesarios para la diferencia entre colores son mucho más complejos
 * que los que hay programados. Para obtener más información sobre este tema, recomiendo
 * leer la página de Wikipedia en inglés sobre la diferencia entre colores. Debido a
 * que se calcula la diferencia en el espacio sRGB para evitar más conversiones,
 * la diferencia entre colores numéricamente no es la misma visualmente, por lo que
 * sigue siendo posible que se generen colores similares, aunque esto debería ayudar a
 * reducir la cantidad de estos casos.
 * 
 * <p>
 * 
 * Desde la versión 14, el proyecto ya no forma parte del trabajo en grupo para
 * la EPI. La versión entregada fue 'v13'. <p> 
 * Code coverage: ~95% (v13) - Programas utilizados: Eclipse, VSCode.
 * 
 * @author Juan Mier
 * @version v19
 * @see <a href="https://docs.oracle.com/javase/tutorial/java/data/numberformat.html">
 *      Documentación de Oracle: Number Format </a> <blockquote> A new line
 *      character appropriate to the platform running the application. You
 *      should always use %n, rather than \n. </blockquote>
 *      
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/security/SecureRandom.html">
 *      Documentación de Oracle: SecureRandom </a>
 *      
 * @see <a href="https://rules.sonarsource.com/java/RSPEC-2119"> Regla SonarLint:2119 </a> 
 *      <blockquote>SecureRandom is preferred to Random</blockquote>
 *      
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html">
 *      Documentación de Oracle: HashMap </a>
 *      
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/awt/Toolkit.html">
 *      Documentación de Oracle: Toolkit </a>
 *      
 * @see <a href="https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html">
 *      Documentación de Oracle: Tutorial de KeyListener </a>
 *      
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html">
 *      Documentación de Oracle: FileWriter </a>
 *      
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html">
 * 		Documentación de Oracle: LocalDateTime </a>
 * 
 * @see <a href="https://stackoverflow.com/questions/25536500/how-to-remove-milliseconds-from-localtime-in-java-8">
 * 		StackOverflow: Cómo acortar nanosegundos en la fecha </a>
 * 
 * @see <a href="https://www.tutorialspoint.com/java/io/writer_flush.htm">
 * 		Tutorial y documentación sobre el método flush() </a>
 * 
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/io/File.html">
 * 		Documentación de Oracle: File </a>
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Color_difference">
 * 		Wikipedia: Fórmula e información sobre distancia entre colores </a>
 * 		<blockquote> [...] One of the better low-cost approximations, sometimes called
 * 		"redmean", combines the two cases smoothly </blockquote>
 * 
 * @see <a href="https://stackoverflow.com/questions/22463062/how-to-parse-format-dates-with-localdatetime-java-8">
 * 		StackOverflow: Información sobre formateo de fechas con LocalDateTime </a>
 * 
 * @see <a href="https://www.github.com/miermontoto/SumaTres"> Repositorio de GitHub </a>
 * 
 */
public class SumaTres extends JPanel {
	
	/**
	 * Se incluye un serial generado aleatoriamente en vez de dejar que el
	 * compilador genere uno por defecto porque así se indica en la documentación de
	 * Oracle. Si no se declarara uno, podría generar excepciones como
	 * 'InvalidClassExceptions'.
	 * <p>
	 * El serial ha sido generado automáticamente por Eclipse. El serial debe
	 * de ser obligatoriamente final y de tipo 'long'.
	 * 
	 * @see <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Serializable.html">
	 * 		Documentación de Oracle: Serializable </a>
	 */
	public static final long serialVersionUID = -1110032705510692144L;
	public static final SecureRandom RAND = new SecureRandom();
	public static final File ARCHIVO = new File("./assets/resultados.txt");
	public static final String VERSION = "v19";
	
	private HashMap<Integer, Integer> obtainedFromRandom; // Vector que almacena las fichas obtenidas en el modo clásico.
	private ArrayList<Tablero> tableros; // ArrayList que guarda todos los tableros de la partida.
	private int[] warning; // Vector que define la posición de la nueva ficha.
	private int turno; // Contador de turnos.
	private int siguiente; // Valor de la siguiente ficha a colocar.
	private int highestValue; // Valor de la ficha más alta.
	private long puntos; // Contador de puntos.
	private double difficultyMultiplier; // Multiplicador de puntuación final.
	private boolean cheatsUsed; // Estado de activación de los trucos.
	private boolean experimental; // Modo de juego.
	private boolean consoleStatus; //  Estado de activación de la salida por consola.
	private Tablero t; // Tablero sobre el que se juega la partida.
		
	/**
	* Constructor de la clase sobrecargado por tres enteros.
	* <p>
	* Rellena la lista de colores por valor según los valores por defecto
	* utilizando {@link #obj.Pieza.inicializarColores()}.
	* <p>
	* Se establece la primera ficha que se introducirá en el tablero después de la
	* primera jugada mediante {@link #newSiguiente()}.
	* <p>
	* Se comienza a esperar por clicks del usuario mediante
	* {@link #addMouseListener(java.awt.event.MouseListener)}.
	* <p>
	* Se utiliza un switch para activar los modos dependiendo del entero 'type'
	* sobrecargado desde el main. En el main se encuentra un showInputMessage con
	* botones que devuelve directamente el valor que se debe pasar a este constructor.
	* <p>
	* El main solo puede sobrecargar valores válidos. Aún así, se implementa un
	* try/catch para evitar exepciones molestas en caso de que alguien manipule
	* el código de manera incorrecta.
	* 
	* @param x: Cantidad de filas del tablero.
	* @param y: Cantidad de columnas del tablero.
	* @param type: Valor entero que define el modo de juego.
	*/
	public SumaTres(Settings op) {
		
		// Se inicializan variables.
		difficultyMultiplier = 1.0;
		cheatsUsed = false;
		obtainedFromRandom = new HashMap<>();
		tableros = new ArrayList<>();
		warning = new int[2];
		puntos = 0;
		highestValue = 3;
		turno = 1;
		
		try {t = new Tablero(op.getX(), op.getY());} catch (Exception ex) {
			err.printf("ERROR: %s."
					+ " Estableciendo tablero por defecto 5x5.", ex);
			t = new Tablero(5, 5);
		}
		
		if(op.isExperimental()) setExperimentalMode();
                else setClassicMode();
		
		/*
		 * Debido a cómo funciona la clase 'Pieza', se debe de inicializar los colores ANTES de
		 * generar el set de fichas inicial. De lo contrario, la primera jugada contaría con piezas
		 * de colores aleatorios durante ese turno solamente.
		 */
		Pieza.inicializarColores();
		if (getMode()) 
			for(int i = 0; i < Math.max((int) (0.15 * t.getColumns() * t.getRows()) / 3, 1); i++) 
				generarSetFichas();
		else {generarSetFichas(); out.print(fullToString());}
		 /*
		  * Se hace Math.max porque en el tablero 3x3, el resultado del cálculo es 0.45,
		  * que convertido a entero es 0, lo que resulta en un tablero vacío al empezar
		  * la partida en modo experimental.
		  */
		
		
		newSiguiente(); // Se establece la ficha 'siguiente' por primera vez.
		
		addKeyListener(new KeyHandler()); // El programa comienza a escuchar por pulsaciones de tecla.
		addMouseListener(new MouseHandler()); // El programa comienza a escuchar por clicks del usuario.
	}
	
	/**
	 * Método que genera un set estándar de tres fichas: 1, 2 y 3.
	 * Solo se accede al comienzo de la partida en el modo clásico, pero podría
	 * ser útil en caso de implementar otros modos de juego o hacer debug.
	 */
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
	 * Establece directamente un vector como el vector que contiene la posición
	 * de la pieza siguiente. NO se comprueba que la posición sea válida ni
	 * correcta, ya que se presupone que la posición viene de <code>validLocation()</code>,
	 * que solo puede generar posiciones válidas por definición.
	 * @param x Vector a establecer.
	 */
	public void setWarning(int[] x) {if(x.length == 2) this.warning = x;}
	
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
	 * @param Puntos que se quieren sumar.
	 */
	public void addPuntos(int puntos) {if(puntos>=3) this.puntos += puntos;}
	
	/**
	 * Establece la cantidad de puntos obtenida manualmente. <p>
	 * No debería poder usarse en condiciones normales, tan solo para pruebas.
	 * 
	 * @param nv Número de puntos a establecer.
	 */
	//public void setPuntos(int nv) {this.puntos = nv;}
	
	/**
	 * Devuelve el valor de la ficha que se encuentre en unas coordenadas.
	 * 
	 * @param x Coordenada x que se desea analizar.
	 * @param y Coordenada y que se desea analizar.
	 * @return  Entero con el valor de la casilla.
	 */
	public int getTab(int x, int y) {return this.t.getPieza(x, y).getValor();}
	
	/**
	 * Establece el valor de una ficha para una casilla.
	 * 
	 * @param x  Coordenada x que se desea cambiar.
	 * @param y  Coordenada y que se desea cambiar.
	 * @param nv Nuevo Valor de la casilla.
	 */
	public void setTab(int x, int y, int nv) {this.t.getPieza(x, y).setValor(nv);}
	
	/**
	 * Devuelve el valor de la próxima ficha.
	 * 
	 * @return Entero con el valor de la futura ficha.
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
	public void addTurno() {this.turno++;}
	
	/**
	 * Método que quita un turno al contador total.
	 * Se utiliza en {@link #undo()}.
	 */
	public void removeTurno() {this.turno--;}

	/**
	 * Devuelve los turnos que se han jugado hasta el momento.
	 * 
	 * @return Entero con la cantidad de turnos.
	 */
	public int getTurnos() {return this.turno;}
	
	/**
	 * Activa el modo clásico.
	 * <p> Al establecer el modo clásico, se activa la consola.
	 */
	public void setClassicMode() {this.experimental = false; this.consoleStatus = true;}
	
	/**
	 * Activa el modo de juego experimental.
	 * <p> Al establecer el modo experimental, se desactiva la consola.
	 */
	public void setExperimentalMode() {this.experimental = true; this.consoleStatus = false;}
	
	/**
	 * Devuelve el estado del modo. False para modo clásico.
	 * @return Valor booleano con el estado del modo experimental.
	 */
	public boolean getMode() {return this.experimental;}
	
	/**
	 * Activa o desactiva el toString() por consola.
	 * Solo accessible en el modo experimental.
	 */
	public void toggleConsole() {
		String s;
		if(consoleStatus()) s = "Desactivada salida por consola.";
		else              s = "Activada salida por consola.";
		this.consoleStatus = !consoleStatus;
		update();
		Dialog.show(s);
	}
	
	/**
	 * Activa los trucos en la partida actual.
	 * Solo accessible en el modo experimental.
	 */
	public void enableCheats() {
		if (Dialog.confirm("¿Desea activar los trucos?")) {
			cheatsUsed = true;
			repaint();
			setMultiplier(0.0);
		}
	}
	
	/**
	 * Devuelve el estado de los trucos.
	 */
	public boolean cheatsUsed() {return this.cheatsUsed;}
	
	/**
	 * Devuelve el estado de la consola. 'true' en caso de que esté activada.
	 * @return Valor booleano.
	 */
	public boolean consoleStatus() {return this.consoleStatus;}

	/**
	 * Método que establece el multiplicador que depende de la dificultad.
	 * Solo afecta a la partida en el modo experimental.
	 * 
	 * @param nv Valor a establecer
	 */
	public void setMultiplier(double nv) {this.difficultyMultiplier = nv;}

	/**
	 * Método que devuelve el multiplicador actual.
	 * Solo accessible en el modo experimental.
	 * 
	 * @return Valor 'double'
	 */
	public double getMultiplier() {return this.difficultyMultiplier;}
	
	/**
	 * Establece el valor del vector 'warning' a -1. Esto debería tomarse
	 * como una señal de que el vector no debería accederse hasta que se
	 * vuelva a establecer otro valor. Principalmente, sirve para no remarcar
	 * a la pieza siguiente cuando se vuelve al tablero anterior mediante
	 * {@link #undo()}. <p> Para simplificar, solo se establece la primera
	 * posición del vector a -1.
	 */
	public void deactivateWarning() {this.warning[0] = -1;}
	
	/**
	 * Método que devuelve el tablero de la partida actual.
	 * 
	 * @return Objeto de tipo 'Tablero'.
	 */
	public Tablero getTablero() {return this.t;}
	
	/**
	 * Método que intercambia el tablero actual por otro en caso de que ambos sean
	 * del mismo tamaño.
	 * 
	 * @param nt Tablero a establecer como actual.
	 */
	public void setTablero(Tablero nt) {
		if(nt.getColumns() == t.getColumns() && nt.getRows() == t.getRows()) t = nt;
	}
	
	/*
	 *  No existe setTurnos() porque no es necesario, los turnos son un simple contador sin
	 *  ninguna implicación en la partida. Además, solo se puede mover hacia atrás o hacia
	 *  adelante con UN turno de diferencia, por lo que con sumar o restar uno al contador
	 *  sirve.
	 */
	
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
	 * comprobar que los valores de la jugada sean válidos (el constructor de la clase
	 * solo puede establecer valores válidos).
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
	 * {@link #extraInfo()} para evitar que el programa utilice métodos que no
	 * son necesarios, ya que a media jugada no es necesario saber el turno ni la ficha
	 * siguiente ni la puntuación. Para ajustarse a este cambio, en el método main se
	 * imprime la información extra para mostrar correctamente el tablero por primera
	 * vez.
	 * 
	 * @see Nombre Desde v17, el método es <code>jugada(char)</code>, no
	 * 		<code>jugada(char)</code>, para seguir con el convenio de nombre de métodos.
	 * @param c Caracter que determina el movimiento (w/s/a/d) y, en modo experimental, (q/w/e/d/c/x/z/a).
	 */
	public void jugada(char c) {
		
		tableros.add(t); // Se guarda una copia del tablero para poder hacer undo()
		
		/*
		 * Se genera una copia del tablero para comparar después de realizar la jugada.
		 * Si al compararse el tablero nuevo con su posición anterior resulta que ambos
		 * son iguales, significa que el nuevo tablero no se ha visto modificado, por lo
		 * que no debería añadirse un turno al contador.
		 */
		var temp = new Tablero(t);
		var x = new Jugada(c); // Se crea un objeto jugada que almacena los valores del movimiento.
		
		Turno.mover(x, this);
		if(consoleStatus()) out.println(this); //TODO: hacer que los saltos de línea coincidan
		Turno.sumar(x, this);
		
		

		/*
		 * Para evitar que el programa intente calcular infinitamente un sitio
		 * vacío de manera aleatoria cuando el tablero está lleno, se comprueba antes
		 * que el tablero no esté lleno mediante isFull(). Así, la siguiente ficha
		 * a colocar no varía.
		 */
		if (!t.isFull()) {
			setWarning(validLocation()); // IMPORTANTE! se debe calcular una posición válida DESPUÉS de mover y sumar
			newFicha();     // Se coloca la ficha 'siguiente'.
			newSiguiente(); // Se calcula el valor de ficha 'siguiente'.
		}
		
		if (!t.equals(temp)) addTurno(); // Si el tablero ha cambiado, se añade un turno.
		else deactivateWarning();
		
		update(); // Se actualiza las salidas para mostrar los cambios en el tablero.
		if (!Turno.ableToMove(this)) finalDePartida(); // Si no se puede mover, se termina la partida.
	}
	
	
	
	
	/**
	* Devuelve el tablero a la situación anterior.
	*/
	public void undo() {
		if (getTurnos() > 1) { // Se comprueba que no se esté en el primer turno.
			for (int i = 0; i < t.getColumns(); i++)
				for (int j = 0; j < t.getRows(); j++) {
					setTab(i, j, tableros.get(getTurnos() - 2).getPieza(i, j).getValor());
				}
			update();
			removeTurno();
			deactivateWarning();
		}
	}	

	/**
	 * Método que actualiza la ventana gráfica y, en caso de que esté activada, la salida por consola.
	 * <p> Debe de utilizarse DESPUÉS de actualizar la situación del tablero.
	 */
	public void update() {
		repaint();
		if(consoleStatus()) out.print(fullToString());
	}
	
	/**
	* Genera un número nuevo aleatorio. Como especificado en el JavaDoc de la
	* clase, <code>rand</code> es un SecureRandom, no un Random normal.
	* 
	* @param val un entero cualquiera.
	* @return Un entero aleatorio [0, val)
	*/
	public static int newRandom(int val) {
		return RAND.nextInt(val);
	}

	/**
	 * Método que devuelve todos los posibles valores que pueden establecerse
	 * como "siguiente" en el modo experimiental.
	 * 
	 * @return
	 */
	private int[] possibleValuesNewSiguiente() {
		if(getHighest() <= 12) {
			// Si la ficha no supera 12, el método de obtener el valor de
			// la siguiente ficha es el clásico.
			return new int[] {1, 2, 3};
		} else {
			var i = 0;
			while(getHighest() != 6*Math.pow(2, i)) {i++;}
			
			var vlsSig = new int[i+1];
			for(int h=0; h<3; h++) vlsSig[h] = h+1; // Se introducen 1, 2 y 3 en el vector de posibles valores.+

			for(int j=0; j < vlsSig.length - 3; j++) {
				vlsSig[3+j] = (int) (6 * Math.pow(2, j)); 
			}

			return vlsSig;
		}
	}

	/**
	 * Método experimental para generar nuevos siguientes. Tiene en cuenta el tamaño de las piezas
	 * y aumenta las probabilidades de piezas menores, especialmente 1, 2 y 3.
	 *
	 * @see <a href="https://stackoverflow.com/questions/880581/how-to-convert-int-to-integer-in-java">
	 *		Pasar de int[] a List </a>
	 */
	// public void newSiguienteExperimental() {
	// 	if(getMode()) {
	// 		int[] values = possibleValuesNewSiguiente();
	// 		if(values.length == 3) setSiguiente(newRandom(3) + 1);
	// 		else {
	// 			int finalValue;
	// 			List<Integer> probabilities = Arrays.stream(values).boxed().collect(Collectors.toList());
	// 			int otherValues = values.length - 3;
	// 			double max = values.length * 5 + 20 / 100.0;
	// 			if(max < 0.55) max = 0.55;
	// 		}
	// 	}
	// 	else setSiguiente(newRandom(3) + 1);
	// 	saveSiguiente();
	// }
	
	/**
	 * 
	 * Genera un número para la siguiente ficha a colocar. Dependiendo del modo de juego, funciona de
	 * una manera diferente. <p>
	 * En el caso de estar jugando en el modo 'experimental',  se generan valores
	 * progresivamente mayores a lo largo del transcurso del programa acordes con el valor de la máxima
	 * ficha en pantalla.
	 * Para generar el siguiente valor, se define un vector y se rellena con los posibles valores.
	 * Obviamente, el 1, 2 y 3 siempre se introducen por defecto. Además, hasta que no se llega al valor
	 * 24, solo se puede sacar uno de esos tres valores. A partir de 24, la siguiente ficha posible
	 * se añade a la pool, es decir, 6. Al llegar a la 98, se añade 12, etc. <p>
	 * En el modo clásico, genera un número entre 1 y 3 utilizando
	 * {@link #newRandom(int)}. Es el método clásico que sigue con las reglas originales
	 * del enunciado.
	 * 
	 */
	//@Deprecated (since="v18", forRemoval=false)
	public void newSiguiente() {
		if(getMode()) setSiguiente(possibleValuesNewSiguiente()[newRandom(possibleValuesNewSiguiente().length)]);
		else setSiguiente(newRandom(3) + 1);
		
		obtainedFromRandom.put(getSiguiente(),
				obtainedFromRandom.containsKey(getSiguiente()) ? obtainedFromRandom.get(getSiguiente()) + 1 : 1);
	}
	
	/**
	 * Coloca la ficha 'siguiente'. Para encontrar una poisición nueva, utiliza
	 * {@link #validLocation()}.
	 */
	public void newFicha() {
		setTab(getWarning()[0], getWarning()[1], getSiguiente());
	}
	
	/**
	 * Coloca una ficha en el tablero. Para encontrar una poisición nueva, utiliza
	 * {@link #validLocation()}.
	 * 
	 * @param nv El valor que tendrá la nueva ficha.
	 */
	public void newFicha(int nv) {
		int[] x = validLocation();
		try {
			setTab(x[0], x[1], nv);
		} catch (NullPointerException npe) {
			/*
			 *  Este trozo de código NUNCA debería ser visible por el usuario.
			 *  De ser ese el caso, hay algo mal planteado. Si se encuentra otra
			 *  excepción entonces hay un problema muy gordo.
			 */
			err.println("Aplicación incorrecta: No hay posiciones válidas en las que colocar una ficha.");
		}
	}
	
	/**
	 * Método que genera una posición válida en la que colocar una ficha.
	 * @return Vector de enteros -> x[0] es la coordenada x, x[1] la y.
	 */
	public int[] validLocation() {
		if(t.isFull()) return null;
		else {
			int[] x = new int[2];
			int nX = newRandom(t.getColumns());
			int nY = newRandom(t.getRows());
			while (getTab(nX, nY) != 0) { // Si ya hay una pieza en la casilla indicada, se vuelve a obtener otra.
				nX = newRandom(t.getColumns());
				nY = newRandom(t.getRows());
			}
			x[0] = nX;
			x[1] = nY;
			return x;
		}
	}
	
	
	
	/**
	 * <h2> Método que devuelve una cadena con la situación actual del tablero. </h2>
	 * 
	 * Dependiendo de la longitud de la ficha máxima, es decir, de la cantidad
	 * de cifras que tenga, el tamaño por celda cambia, con lo que las fichas
	 * que tengan una longitud inferior se justifican a la derecha. Como el
	 * output por consola está pensado para el modo clásico, si se llega a
	 * alcanzar un número muy grande o si el tablero introducido es mayor de
	 * lo normal, el output por consola puede resultar difícil de leer.
	 * <p>
	 * Por cómo está hecho el programa, es preferible imprimir los saltos de
	 * línea al principio del toString() en vez de al final para hacer más visible
	 * el tablero en consola cuando no se está haciendo ninguna jugada. El
	 * resto del programa está ajustado para que los saltos de línea concatenen con
	 * este y tenga sentido el output por consola.
	 * <p>
	 * La información extra sobre la situación actual de la partida se devuelve
	 * mediante el método {@link #extraInfo()}.
	 */
	@Override
	public String toString() {
		
		int times = String.valueOf(getHighest()).length(); // Longitud de la mayor ficha.
		
		// Se comienza a construir la matriz, empezando por la esquina superior izquierda.
		String salida = String.format("%n%n\u2554");
	
		// Se imprime el borde superior de la matriz.
		for (int i = 0; i < t.getRows(); i++) {
			for (int x = 0; x < times; x++) {salida += "\u2550";}
			salida += (i + 1 == t.getRows()) ? String.format("\u2557%n") : "\u2566";
		}
		
		for (int i = 0; i < t.getColumns(); i++) {
			salida += "\u2551"; // Para la primera columna, se imprime el borde izquierdo de la matriz.
			for (int j = 0; j < t.getRows(); j++) {
				for (int x = 0; x < times - String.valueOf(getTab(i, j)).length(); x++) {salida += " ";}
				salida += getTab(i, j) == 0 ? " " : String.format("%d", getTab(i, j));
				salida += "\u2551"; // Borde derecho de cada casilla.
			}
			salida += String.format("%n"); // Salto de línea para seguir construyendo la matriz.
		}
		
		salida += "\u255A"; // Borde inferior izquierdo de la matriz.
		
		// Se imprime el borde inferior de la matriz.
		for (int i = 0; i < t.getRows(); i++) {
			for (int x = 0; x < times; x++) {salida += "\u2550";}
			salida +=  (i + 1 == t.getRows()) ? String.format("\u255D%n") : "\u2569";
		}
		
		return salida;
	}
	
	/**
	 * Método que devuelve una cadena con información extra sobre la partida.
	 * Devuelve la ficha que se va a colocar aleatoriamente a continuación,
	 * la cantidad de puntos acumulada hasta ahora, la ficha máxima en pantalla
	 * y el número del turno.
	 *
	 * @return Cadena con la información.
	 */
	public String extraInfo() {
		return String.format("Siguiente [%d]%nPuntos [%d]\tMáx [%d]"
		+ "%nTurno [%d]",
		getSiguiente(), getPuntos(), getHighest(), getTurnos());
	}
	
	public String fullToString() {
		return this + extraInfo();
	}
	
	/**
	 * Método que maneja el final de la partida. <p> Para evitar que el entorno
	 * gráfico continúe ejecutándose, se utiliza <code>System.exit(int)</code> para
	 * cerrar el entorno con un código normal de finalización <code>0</code>. Para
	 * obtener la información, se utiliza {@link #getPuntos()},
	 * {@link #getHighest()}, {@link #getTurnos()} y {@link #getSiguiente()}. <p>
	 * Además, se imprimen la cantidad de nuevas fichas sacadas por consola en caso
	 * de que se esté jugando en modo experimental. <p>
	 * En el modo experimental se exportan los datos a un archivo de texto,
	 * 'resultados.txt', que se encuentra en la raíz desde la que se esté ejecutando
	 * el programa.
	 */
	public void finalDePartida() {
		long finalPuntos = (long) (getPuntos() * getMultiplier());

		String salida = String.format("Se ha terminado la partida.%nPuntuación final: %d%nFicha máxima: %d%nTurnos: %d%n",
			finalPuntos, getHighest(), getTurnos());
		if (cheatsUsed()) salida += "Se han utilizado trucos.";
		Dialog.show(salida);
		out.printf("%n%n%s",salida);
		
		if(getMode()) {
			out.print("Fichas obtenidas: ");
			for(int i=0; i < possibleValuesNewSiguiente().length; i++) {
				out.printf("[%d]: %d ",
					possibleValuesNewSiguiente()[i], obtainedFromRandom.get(possibleValuesNewSiguiente()[i]));
			}
			out.println();
			
			String output = String.format("[%s] %s\t%dx%d\tPTS %d\tMAX %d\tTURN %d%n",
					LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
					VERSION, getTablero().getColumns(), getTablero().getRows(), puntos, getHighest(),
					getTurnos());
			handler.File.write(output, ARCHIVO);
			out.println("Puntuaciones guardadas.");
			
		}
		System.exit(0); // Se termina con estado '0' para indicar que se termina correctamente.
	}
	
	public void loop() {
		while(!Keyboard.isControlDown) {
		              jugada('w');
		if(getMode()) jugada('q');
		              jugada('a');
		if(getMode()) jugada('z');
		if(getMode()) jugada('x');
		else 		  jugada('s');
		if(getMode()) jugada('c');
		              jugada('d');
		if(getMode()) jugada('e');
		}
	}
	
	// ----------------------------------------------------------------------------------------------------

	/**
	 * Método que redirige los gráficos a {@link #paint(Graphics)}. <p>
	 * También pide el focus de la ventana al comenzar a pintar, para evitar que se pierdan
	 * pulsaciones de teclado al comenzar la partida.
	 */
	@Override
	public void paintComponent(Graphics g) {
		requestFocusInWindow();
		super.paintComponent(g);
		Paint.paint(g, this);
	}

	// -------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Recive los clicks del ratón en la aplicación y redirige el evento a 
	 * {@link game.SumaTres.rerouteMouse(event)}. <p>
	 * Para mayor libertad a la hora de programar, se pasa el evento independientemente
	 * de qué botón de ratón se haya pulsado.
	 */
	private class MouseHandler extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent event) {
			rerouteMouse(event);
		}
	}
	
	/**
	 * Redirige todos los eventos del ratón a
	 * {@link handler.Mouse.mouseHandler(SumaTres, MouseEvent)}.
	 * @param e Evento de ratón.
	 */
	public void rerouteMouse(MouseEvent e) {Mouse.mouseHandler(this, e);}
	
	
	
	/**
	 * Recive pulsaciones de teclado y los introduce en
	 * {@link #game.SumaTres.rerouteKeyboard(event)}, con información sobre la tecla pulsada
	 * y si el control estaba pulsado o no durante la pulsación.
	 */
	private class KeyHandler extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent event) {
			rerouteKeyboard(event);
		}
	}	
	
	/**
	 * Toda la lógica del teclado debería ocurrir en la propia clase del teclado. <p>
	 * Necesita estar fuera de la clase KeyHandler para poder pasar la partida 'SumaTres'
	 * como this. Esa es la única función de este método.
	 * @param e
	 */
	public void rerouteKeyboard(KeyEvent e) {Keyboard.keyboardHandler(this, e);}
}
