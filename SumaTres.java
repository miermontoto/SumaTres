import static java.lang.System.out;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * <h2> Clase principal del proyecto SumaTres </h2>
 * 
 * Se inicializa sobrecargando tres enteros que delimitan el tamaño del tablero
 * y el modo de juego.
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
 * @version v16
 * @see <a href="https://docs.oracle.com/javase/tutorial/java/data/numberformat.html">
 *      Documentación de Oracle: Number Format </a> <blockquote> A new line
 *      character appropriate to the platform running the application. You
 *      should always use %n, rather than \n. </blockquote>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/security/SecureRandom.html">
 *      Documentación de Oracle: SecureRandom </a>
 * @see <a href="https://rules.sonarsource.com/java/RSPEC-2119"> Regla
 *      SonarLint:2119 </a> <blockquote>SecureRandom is preferred to
 *      Random</blockquote>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html">
 *      Documentación de Oracle: HashMap </a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/awt/Toolkit.html">
 *      Documentación de Oracle: Toolkit </a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html">
 *      Documentación de Oracle: Tutorial de KeyListener </a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html">
 *      Documentación de Oracle: FileWriter </a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html">
 * 		Documentación de Oracle: LocalDateTime </a>
 * @see <a href="https://stackoverflow.com/questions/25536500/how-to-remove-milliseconds-from-localtime-in-java-8">
 * 		StackOverflow: Cómo acortar nanosegundos en la fecha </a>
 * @see <a href="https://www.tutorialspoint.com/java/io/writer_flush.htm">
 * 		Tutorial y documentación sobre el método flush() </a>
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/io/File.html">
 * 		Documentación de Oracle: File </a>
 * @see <a href="https://en.wikipedia.org/wiki/Color_difference">
 * 		Wikipedia: Fórmula e información sobre distancia entre colores </a>
 * @see <a href="https://stackoverflow.com/questions/22463062/how-to-parse-format-dates-with-localdatetime-java-8">
 * 		StackOverflow: Información sobre formateo de fechas con LocalDateTime </a>
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
	private Tablero tablero; // El tablero se inicializa en el constructor.
	private ArrayList<Tablero> tableros = new ArrayList<>(); // Se genera un ArrayList que guarda las tableros.
	private long puntos = 0; // Se comienza la partida con cero puntos.
	private int highestValue = 0; // Se podría inicializar con el valor '3', pero es preferible que se ajuste
								  // a lo que se declara en el constructor por si el usuario lo modifica.
	private HashMap<Integer, Integer> obtainedFromRandom = new HashMap<>(); // Vector que almacena las fichas obtenidas en el modo clásico.
	private SecureRandom rand = new SecureRandom();
	private int[] warning = new int[2]; // Vector que define la posición de la nueva ficha.
	private boolean cheatsUsed = false; // Estado de los trucos. Por defecto, están desactivados.
	private boolean experimental; // Determina el modo de juego. Por defecto, el modo de juego es clásico.
	private int numPlayers = 1;
	private boolean consoleStatus = false; // Por defecto, la consola está desactivada.
	private double difficultyMultiplier = 1.0; // Multiplicador
	private final File archivo = new File("resultados.txt");
	private static final String version = "v16"; // Declara la versión del programa.
	
	// TODO: implementar probabilidades en la selección de la siguiente ficha para mejorar jugabilidad en el modo experimental
	// TODO: multijugador
	// TODO: bonus por desactivar ayudas visuales 
	// TODO: modo final de partida
	// TODO: mayor puntuación csv?
	
	
	/**
	* Constructor de la clase sobrecargado por tres enteros.
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
	* Se utiliza un switch para activar los modos dependiendo del entero 'type' sobrecargado
	* desde el main. En el main se encuentra un showInputMessage con botones que devuelve
	* directamente el valor que se debe pasar a este constructor.
	* <p>
	* El main solo puede sobrecargar valores válidos. Aún así, se implementa un try/catch para
	* evitar exepciones molestas en caso de que alguien manipule el código de manera incorrecta.
	* 
	* @param x: Cantidad de filas del tablero.
	* @param y: Cantidad de columnas del tablero.
	* @param type: Valor entero que define el modo de juego.
	*/
	public SumaTres(int x, int y, int type) throws IOException { // debería haber una manera mejor de manejar la excepción
		try {tablero = new Tablero(x, y);} catch (Exception ex) {
			out.printf("ERROR: Se ha detectado la excepción %s. Establecido tablero por defecto 5x5.", ex);
			tablero = new Tablero(5, 5);
		}
		switch(type) {
			case 0: setClassicMode(); break;      // Clásico
			case 1: setExperimentalMode(); break; // Experimental
			//case 2: setMultiplayer(); break;    // Multijugador - Desactivado hasta que se implemente finalmente
			default: System.exit(0);              // Cancelar
			// Se cancela si el tipo introducido es 2 o -1.
		}
		
		inicializarColores(); // Se popula el HashMap de colores con los valores por defecto.
		// Debido a cómo funciona la clase 'Pieza', se debe de inicializar los colores ANTES de
		// generar el set de fichas inicial. De lo contrario, la primera jugada contaría con piezas
		// de colores aleatorios durante ese turno solamente.
		
		if(getMode()) {
			for(int i = 0; i < (int) (0.15 * tablero.getSizeX() * tablero.getSizeY()) / 3; i++) {generarSetFichas();}
		} else {
			generarSetFichas();
		}
		
		newSiguiente(); // Se establece la ficha 'siguiente' por primera vez.
		
		addKeyListener(new KeyHandler()); // El programa comienza a escuchar por pulsaciones de tecla.
		addMouseListener(new MouseHandler()); // El programa comienza a escuchar por clicks del usuario.
		
		
	}
	
	/**
	* Método que genera un set estándar de tres fichas: 1, 2 y 3.
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
	public int getTab(int x, int y) {return this.tablero.getPieza(x, y).getValor();}
	
	/**
	* Establece el valor de una ficha para una casilla.
	* 
	* @param x  Coordenada x que se desea cambiar.
	* @param y  Coordenada y que se desea cambiar.
	* @param nv Nuevo Valor de la casilla.
	*/
	public void setTab(int x, int y, int nv) {this.tablero.getPieza(x, y).setValor(nv);}
	
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
	 * Método que quita un turno al contador total.
	 * Se utiliza en {@link #undo()}.
	 */
	public void removeTurno() {this.turnos--;}

	/**
	* Devuelve los turnos que se han jugado hasta el momento.
	* 
	* @return tipo 'entero' con la cantidad de turnos.
	*/
	public int getTurnos() {return this.turnos;}
	
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
	* Activa el modo multijugador. No activable ahora mismo.
	*/
	public void setMultiplayer() {this.numPlayers = 2;}
	
	/**
	* Devuelve la cantidad de jugadores.
	* @return Valor entero con la cantidad de jugadores actualmente.
	*/
	public int getPlayers() {return this.numPlayers;}
	
	/**
	* Activa o desactiva el toString() por consola.
	* Solo accessible en el modo experimental.
	*/
	public void toggleConsole() {
		String s;
		if(consoleStatus) s = "Desactivada salida por consola.";
		else s = "Activada salida por consola.";
		this.consoleStatus = !consoleStatus;
		if(consoleStatus) {out.print(this); out.println(printExtraInfo());}
		JOptionPane.showMessageDialog(null, s, "SumaTres", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	* Activa los trucos en la partida actual.
	* Solo accessible en el modo experimental.
	*/
	public void enableCheats() {
		if (JOptionPane.showConfirmDialog(null, "¿Desea activar los trucos?", "SumaTres", JOptionPane.YES_NO_OPTION)
		== JOptionPane.YES_OPTION) {
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
	 * {@link #undo()}.
	 */
	public void deactivateWarning() {this.warning[0] = -1;}
	
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
	* {@link #printExtraInfo()} para evitar que el programa utilice métodos que no
	* son necesarios, ya que a media jugada no es necesario saber el turno ni la ficha
	* siguiente ni la puntuación. Para ajustarse a este cambio, en el método main se
	* imprime la información extra para mostrar correctamente el tablero por primera
	* vez.
	* 
	* @param c Caracter que determina el movimiento (w/s/a/d) y, en modo experimental, (q/w/e/d/c/x/z/a).
	*/
	public void Jugada(char c) {
		
		// Se genera una copia del tablero para comparar después de realizar la jugada.
		// Si al compararse el tablero nuevo con su posición anterior resulta que ambos
		// son iguales, significa que el nuevo tablero no se ha visto modificado, por lo
		// que no debería añadirse un turno al contador.
		tableros.add(new Tablero(tablero.getSizeX(), tablero.getSizeY()));
		for(int i=0; i<tablero.getSizeX(); i++) for(int j=0; j<tablero.getSizeY(); j++) {
			tableros.get(getTurnos() - 1).getPieza(i, j).setValor(getTab(i, j));
		}
		
		Jugada x = new Jugada(c);	
		
		mover(x);
		if(consoleStatus()) out.println(this);
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
		for (int i = 0; i < tablero.getSizeX(); i++) for (int j = 0; j < tablero.getSizeY(); j++) {
			if (tableros.get(getTurnos() - 1).getPieza(i, j).getValor() != getTab(i, j))
				didChange = true;
		}
		if (didChange) addTurno(); // Si el tablero ha cambiado, se añade un turno.
		
		update(); // Se actualiza las salidas para mostrar los cambios en el tablero.
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
			for (int i = x.getUp() + x.getDown()*(tablero.getSizeX() - 2); i < tablero.getSizeX() && i >= 0; i += 1 -2*x.getDown())
				for (int j = x.getLeft() + x.getRight()*(tablero.getSizeY() - 2); j < tablero.getSizeY() && j >= 0; j += 1 - 2*x.getRight()) {
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
			for (int i = x.getUp() + x.getDown() * (tablero.getSizeX() - 2); i < tablero.getSizeX() && i >= 0; i += 1 - 2 * x.getDown())
				for (int j = x.getLeft() + x.getRight() * (tablero.getSizeY() - 2); j < tablero.getSizeY() && j >= 0; j += 1 - 2 * x.getRight()) {
					if (getTab(i, j) != 0 && (getTab(i + x.moveVert(), j + x.moveHorz()) == 0)) check = true;
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
		for (int i = x.getUp() + x.getDown() * (tablero.getSizeX() - 2); i < tablero.getSizeX() && i >= 0; i += 1 - 2 * x.getDown())
			for (int j = x.getLeft() + x.getRight() * (tablero.getSizeY() - 2); j < tablero.getSizeY() && j >= 0; j += 1 - 2 * x.getRight()) {
				if (sumaCond(i, j, x)) { // Si se puede sumar, se convierte la nueva casilla en la suma y la antigua en 0.
					setTab(i + x.moveVert(), j + x.moveHorz(), getTab(i, j) + getTab(i + x.moveVert(), j + x.moveHorz()));
					setTab(i, j, 0);
					addPuntos(getTab(i + x.moveVert(), j + x.moveHorz()));
					setHighest(getTab(i + x.moveVert(), j + x.moveHorz()));
					// Se comprueba si la mayor pieza es la recién sumada.
			}
		}
		mover(x);
		// Se mueve al terminar de suma para evitar que queden huecos vacíos.
	}
	
	/**
	* Devuelve el tablero a la situación anterior.
	*/
	public void undo() {
		if (getTurnos() > 1) { // Se ejecuta a menos que se esté en el primer turno.
			for (int i = 0; i < tablero.getSizeX(); i++)
				for (int j = 0; j < tablero.getSizeY(); j++) {
					setTab(i, j, tableros.get(getTurnos() - 2).getPieza(i, j).getValor());
				}
			update();
			removeTurno();
			deactivateWarning();
		}
	}

	/**
	 * Método que determina la validez de un valor dado. <p>
	 * Para ello, se define si el número es 1, 2, 3 o un número perteneciente a la serie
	 * 6*2n.
	 * 
	 * @param x Valor a examinar
	 * @return Valor booleano que determina la validez
	 */
	public boolean validValue(int x) {
		boolean check = false;
		if (x<=3 && x>=1)  check = true;
		else {
			int i = 0;
			while (x > 6 * Math.pow(2, i)) i++;
			if (6 * Math.pow(2, i) == x) check = true;
		}
		return check;
	}
	
	/**
	 * Método que coloca una pieza en el tablero de manera artificial, obteniendo las coordenadas y el
	 * valor de la nueva pieza y comprobando que todo es correcto. <p>
	 * Para obtener las coordenadas, se utiliza el método {@link #inputCoord(String, int)}. <p>
	 * Para obtener el nuevo valor, se utiliza una versión ligeramente modificada de este último método,
	 * comprobando el valor con {@link #validValue(int)}. <p>
	 * El método debería ser accesible solamente cuando los trucos estén activados.
	 */
	public void colocarPieza() {
		int nX = inputCoord("Introduzca la coordenada x de la pieza que desea colocar", tablero.getSizeX());
		if(nX != -1) {
			int nY = inputCoord("Introduzca la coordenada y de la pieza que desea colocar", tablero.getSizeY());
			if (nY != 1) {
				int nV;
				try {
					String respuesta = JOptionPane.showInputDialog(null, "Introduzca un valor para la pieza", "SumaTres", JOptionPane.QUESTION_MESSAGE);
					if (respuesta == null || respuesta.length() == 0) nV = -1;
					else nV = Integer.parseInt(respuesta);

					while (nV < -1 && !validValue(nV)) {
						JOptionPane.showMessageDialog(null, "Valor inválido", "SumaTres", JOptionPane.ERROR_MESSAGE);
						respuesta = JOptionPane.showInputDialog(null, "Introduzca un valor para la pieza", "SumaTres", JOptionPane.QUESTION_MESSAGE);
						if (respuesta == null || respuesta.length() == 0) nV = -1;
						else nV = Integer.parseInt(respuesta);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Valor catastrófico.", "SumaTres", JOptionPane.ERROR_MESSAGE);
					nV = -1;
				}
				if(nV != -1) {
					setTab(nX, nY, nV);
					update();
				}
			}
		}
	}

	/**
	 * Método que quita una pieza de manera artificial, obteniendo las coordenadas y comprobando que
	 * son correctas mediante {@link #inputCoord(String, int)}. <p>
	 * El método debería ser accesible solamente cuando los trucos estén activados.
	 */
	public void quitarPieza() {
		int nX = inputCoord("Introduzca la coordenada x de la pieza que desea colocar", tablero.getSizeX());
		if(nX != -1) {
			int nY = inputCoord("Introduzca la coordenada y de la pieza que desea colocar", tablero.getSizeY());
			if (nY != -1 && getTab(nX, nY) != 0) setTab(nX, nY, 0);
		}
	}	

	/**
	 * Método que actualiza la ventana gráfica y, en caso de que esté activada, la salida por consola.
	 * <p> Debe de utilizarse DESPUÉS de actualizar la situación del tablero.
	 */
	public void update() {
		repaint();
		if(consoleStatus()) {
			out.print(this);
			out.print(printExtraInfo());
		}
	}
	
	/**
	* Método que le pide al usuario coordenadas del tablero.
	* <p>
	* Es utilizado por los métodos {@link #colocarPieza()} y
	* {@link #quitarPieza()}.
	* <p>
	* En caso de que se pulse 'cancelar' o se introduzcan caracteres que no puedan
	* ser convertidos a números enteros, el programa devuelve un valor
	* <code>-1</code>. Los métodos antes mencionados
	* deberían estar preparados para que, en caso de recibir un input <code>-1</code>,
	* no continuar con la ejecucción y volver a esperar por una jugada. Lo malo es
	* que el usuario puede introducir manualmente <code>-1</code>, aunque no es muy
	* intuitivo y no debería afectar la experiencia del usuario sin antes mirar
	* este código. Si el usuario consigue causar una excepción, el programa lo resuelve
	* con un <code>catch</code> que devuelve el valor <code>-1</code>.
	* 
	* @param s     Una cadena que define lo que se le va a preguntar
	*              al usuario.
	* @param limit Un número entero que define el número máximo que se puede
	*              introducir.
	 */
	private int inputCoord(String s, int limit) {
		int value;
		try {
			String respuesta = JOptionPane.showInputDialog(null, s, "SumaTres", JOptionPane.QUESTION_MESSAGE);
			if(respuesta == null || respuesta.length() == 0) value = -1;
			else value = Integer.parseInt(respuesta);
			
			while (value < -1 || value >= limit) {
				JOptionPane.showMessageDialog(null, "Valor inválido", "SumaTres", JOptionPane.ERROR_MESSAGE);
				respuesta = JOptionPane.showInputDialog(null, s, "SumaTres", JOptionPane.QUESTION_MESSAGE);
				if(respuesta == null || respuesta.length() == 0) value = -1;
				else value = Integer.parseInt(respuesta);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Valor catastrófico.", "SumaTres", JOptionPane.ERROR_MESSAGE);
			value = -1;
		}
		return value;
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

	public int[] possibleValuesNewSiguiente() {
		int i = 0;
		while(getHighest()>6*Math.pow(2, i)) {i++;}
		int[] vlsSig = new int[getHighest()<13 ? 3 : i+1];
		// Si la ficha no supera 12, el método de obtener el valor de
		// la siguiente ficha es el clásico.
		
		for(int h=0; h<3; h++) {vlsSig[h] = h+1;} // Se introducen 1, 2 y 3 en el vector de posibles valores.
		
		for(int j=0; j < vlsSig.length - 3; j++) {
			vlsSig[3+j] = (int) (6 * Math.pow(2, j)); 
		}

		return vlsSig;
	}
	
	/**
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
	*/
	public void newSiguiente() {
		if(getMode()) setSiguiente(possibleValuesNewSiguiente()[newRandom(possibleValuesNewSiguiente().length)]);
		else setSiguiente(newRandom(3) + 1);

		obtainedFromRandom.put(getSiguiente(),
			obtainedFromRandom.containsKey(getSiguiente()) ? obtainedFromRandom.get(getSiguiente()) + 1 : 1);
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
		for (int i = 0; i < tablero.getSizeX(); i++) for (int j = 0; j < tablero.getSizeY(); j++) {
			if (getTab(i, j) != 0) check++;
		}
		return check == tablero.getSizeX() * tablero.getSizeY();
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
			if(getMode() && (checkEndLoop(new Jugada('q')) || checkEndLoop(new Jugada('z'))
			|| checkEndLoop(new Jugada('c')) || checkEndLoop(new Jugada('e')))) ableToMove = true;
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
		for (int i = x.getUp(); i + x.getDown() < tablero.getSizeX(); i++)
			for (int j = x.getLeft(); j + x.getRight() < tablero.getSizeY(); j++) {
				if (sumaCond(i, j, x)) check = true;
		}
		return check;
	}
	
	/**
	* Condición que detecta si se puede sumar o no.
	* <p>
	* Para determinar que sea una suma válida, se comprueba: o bien que sean piezas
	* iguales (excepto 1 y 2), o bien que una de las piezas sea 1 y la otra 2.
	* 
	* @param i  Posición x del tablero.
	* @param j  Posición y del tablero.
	* @param x 	Objeto de la clase 'Jugada' que define el movimiento.
	* @return   Un booleano, 'true' si se puede sumar, 'false' si no.
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
		int nX = newRandom(tablero.getSizeX());
		int nY = newRandom(tablero.getSizeY());
		while (getTab(nX, nY) != 0) {
			nX = newRandom(tablero.getSizeX());
			nY = newRandom(tablero.getSizeY());
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
		for (int i = 0; i + 1 < tablero.getSizeY(); i++) {
			for (int x = 0; x < times; x++) {salida += "═";}
			salida += "╦";
		}
		for (int x = 0; x < times; x++) {salida += "═";}
		salida += String.format("╗%n"); // Se imprime la esquina superior y se salta de línea.
		
		
		
		for (int i = 0; i < tablero.getSizeX(); i++) {
			salida += "║"; // Para la primera columna, se imprime el borde izquierdo de la matriz.
			for (int j = 0; j < tablero.getSizeY(); j++) { // Se imprime la matriz en sí.
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
		for (int i = 0; i + 1 < tablero.getSizeY(); i++) {
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
	* Además, se imprimen la cantidad de nuevas fichas sacadas por consola, para 
	* comprobar que dicha cantidad es más o menos la misma y que no hay ningún
	* tipo de manipulación, pese a lo que pueda parecer según se juega.
	*/
	public void finalDePartida() {
		long finalPuntos = getMode() ? (long) (getPuntos() * getMultiplier()) : getPuntos();

		String salida = String.format("Se ha terminado la partida.%nPuntuación final: %d%nFicha máxima: %d%nTurnos: %d%n",
			finalPuntos, getHighest(), getTurnos());
		if (cheatsUsed()) salida += "Se han utilizado trucos.";
		JOptionPane.showMessageDialog(null, salida, "SumaTres", JOptionPane.INFORMATION_MESSAGE);
		out.printf("%n%n%n%s",salida);
		
		if(getMode()) {
			out.print("Fichas obtenidas: ");
			for(int i=0; i<possibleValuesNewSiguiente().length; i++) {
				out.printf("[%d]: %d ",
					possibleValuesNewSiguiente()[i], obtainedFromRandom.get(possibleValuesNewSiguiente()[i]));
			}
			out.println();
			try {
				if(archivo.createNewFile()) {out.println("Creado archivo de puntuaciones.");}
				final FileWriter writer = new FileWriter(archivo, true);

				String output = String.format("[%s] %s\t%dx%d\tPTS %d\tMAX %d\tTURN %d%n",
					LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
					SumaTres.version, tablero.getSizeX(), tablero.getSizeY(), finalPuntos, getHighest(),
					getTurnos());

				// El formateo es bastante pobre, pero no estoy dispuesto a generar un csv porque quiero
				// que sea legible a simple vista. En el futuro, sí que podría ser útil generar un csv
				// para detectar puntuaciones altas. De momento, el mero hecho de que se guarde información
				// sobre la partida es más que suficiente.

				writer.write(output);
				writer.close();
				out.printf("Resultados guardados en '%s'.%n", archivo.getName());
			} catch (Exception e) {
				out.println("Error al abrir el archivo de puntuaciones.");
				//e.printStackTrace();
			}
		} else {
			out.printf("Fichas obtenidas: [1]: %d  [2]: %d  [3]: %d%n",
				obtainedFromRandom.get(1), obtainedFromRandom.get(2), obtainedFromRandom.get(3));
		}
		System.exit(0); // Se termina con estado '0' para indicar que se termina correctamente.
	}
	
	// ----------------------------------------------------------------------------------------------------
	public static final ImageIcon icono = new ImageIcon("icon.png");
	private static final int anchoPantalla = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int altoPantalla  = Toolkit.getDefaultToolkit().getScreenSize().height;
	private static final double scale = (double) altoPantalla / 720 < 0.5 ? 0.5 : (double) altoPantalla / 720;
	// La escala ayuda a mantener un tamaño jugable tanto de tamaño de ventana como de objetos pintados
	// gráficamente. En algunos casos extremos, como es el caso de resoluciones enanas y poco comunes
	// hoy en día (640x480, 800x600), la escala y las posiciones de objetos son ligeramente incorrectos,
	// con lo que el texto no está bien colocado del todo.

	// Lo negativo de que los tamaños de la pantalla sean fijos y no se actualicen es que el tablero
	// mantiene el tamaño establecido al principio de la partida, con lo que al cambiar de resolución
	// a mitad de partida, no se ajustan todos los datos correctamente. No merece la pena crear métodos
	// que actualicen los datos sobre la pantalla y la escala porque este error se encuuentra solo en
	// casos muy remotos, a menos que el usuario cambie de resolución intencionalmente para romper
	// visualmente el programa.
	
	private static final int SPOT_SPACER    = (int) (3  * scale); // Espacio entre piezas.
	private static final int BOARD_SPACER   = (int) (6  * scale); // Espacio entre el borde del tablero y las piezas.
	private static final int ROUND_DIAMETER = (int) (10 * scale); // Radio del diámetro del arco.
	private static final int BUTTON_SIZE    = (int) (20 * scale); // Tamaño de los botones.
	private static final int SQUARE_SIZE    = (int) (40 * scale); // Tamaño de las piezas.
	private static final int MAIN_SPACER    = (int) (50 * scale); // Espacio entre el tablero y el borde de la pantalla.
	
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
		colores.put(0 , Color.white  );
		colores.put(1 , Color.red    );
		colores.put(2 , Color.orange );
		colores.put(3 , Color.cyan   );
		colores.put(6 , Color.blue   );
		colores.put(12, Color.green  );
		colores.put(24, Color.magenta);
		colores.put(48, Color.pink   );
	}
	
	/**
	* Método que se accede desde el main para comprobar si el tablero entra en pantalla con la resolución
	* actual. Simplemente devuelve un valor booleano, la lógica se encuentra en el main.
	* @return Valor booleano que establece si la pantalla generada entra o no en la resolución actual.
	*/
	public boolean checkValidSize() {
		return getPlayers() * (tablero.getSizeY() * (SPOT_SPACER + SQUARE_SIZE) + 2 * BOARD_SPACER - SPOT_SPACER + 2 * MAIN_SPACER) < anchoPantalla &&
		tablero.getSizeX() * (SPOT_SPACER + SQUARE_SIZE) + 2 * BOARD_SPACER - SPOT_SPACER + 2 * MAIN_SPACER < altoPantalla; 
	}
	
	/**
	* Método que calcula el ancho total final de la pantalla. Se utiliza en el main para definir la
	* aplicación y también en diversos métodos dentro de esta clase para calcular posiciones respecto
	* a los bordes de la ventana. Depende de la matriz tablero inicializada.
	* @return Valor entero con el ancho de la ventana.
	*/
	public int defineX() {
		return getPlayers() * (tablero.getSizeY() * (SPOT_SPACER + SQUARE_SIZE) + 2 * BOARD_SPACER - SPOT_SPACER + 2 * MAIN_SPACER);
	}
	
	/**
	* Método que calcula el alto total final de la pantalla. Se utiliza en el main para definir la
	* aplicación y también en diversos métodos dentro de esta clase para calcular posiciones respecto
	* a los bordes de la ventana. Depende de la matriz tablero inicializada.
	* @return Valor entero con el alto de la ventana.
	*/
	public int defineY() {
		return tablero.getSizeX() * (SPOT_SPACER + SQUARE_SIZE) + 2 * BOARD_SPACER - SPOT_SPACER + 3 * MAIN_SPACER;
	}
	
	/**
	* Método que optimiza el cambio del tamaño de la fuente. Puesto que siempre se utiliza ARIAL como
	* fuente, no es necesario cambiar nada más y se acorta más de esta manera.
	* @param g Entorno gráfico
	* @param size Tamaño de la fuente a establecer
	*/
	public void setFontSize(Graphics2D g2d, int size) {
		g2d.setFont(new Font("Helvetica", Font.PLAIN, size));
	}
	
	/**
	* Método que pinta la partida en la aplicación gráfica. El programa NO se basa en
	* {@link #toString()} para pintar el tablero, no siempre existe la misma
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

		Graphics2D g2d = (Graphics2D) g;

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		rh.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		// No es ni siquiera apreciable la mejora gráfica de la calidad del color, pero me quedo más agusto.
		// La mejora clara aquí es gracias al antialiasing. La fuente y, en especial los bordes de las fichas,
		// son mucho más suaves y aptos para una pantalla de gran dpi y resolución, como casi cualquiera hoy
		// en día. Esto no afecta mucho el rendimiento del programa.

        g2d.setRenderingHints(rh);
		
		pintarFlechas(g2d);
		pintarTablero(g2d);
		pintarFichas(g2d);
		pintarInfo(g2d);
		pintarBotones(g2d);
	}
	
	/**
	* Método que imprime las flechas que indican dónde hacer click para realizar un movimiento
	* y en qué dirección, aunque sea bastante intuitivo de por sí. Los cálculos de la posición
	* de las flechas son aproximados a ojo, pero se mantienen en cualquier con cualquier
	* combinación de columnas, filas y resolución de pantalla. <p> Si está activado el modo
	* experimental, se pintan las flechas que delimitan el movimiento en diagonal. Se han
	* escogido estos caracteres en concreto porque representan bastante bien el carácter del
	* movimiento en diagonal: se mueve hasta encontrarse con otra pieza o el borde del tablero,
	* no hasta estar en la posición más extrema en la dirección indicada.

	* @param g Entorno gráfico
	*/
	public void pintarFlechas(Graphics2D g) {
		g.setColor(Color.blue);
		setFontSize(g, 18);
		g.drawString("↑", defineX() / 2, MAIN_SPACER * 14 / 24);
		g.drawString("←", MAIN_SPACER * 9 / 24, (defineY() - MAIN_SPACER) / 2);
		g.drawString("→", defineX() - MAIN_SPACER * 16 / 24, (defineY() - MAIN_SPACER) / 2);
		g.drawString("↓", defineX() / 2, defineY() - MAIN_SPACER * 35 / 24);
		if(getMode()) {
			g.drawString("⭶", MAIN_SPACER * 9 / 24, MAIN_SPACER * 14 / 24);
			g.drawString("⭷", defineX() - MAIN_SPACER * 16 / 24, MAIN_SPACER * 14 / 24);
			g.drawString("⭹", MAIN_SPACER * 9 / 24, defineY() - MAIN_SPACER * 35 / 24);
			g.drawString("⭸", defineX() - MAIN_SPACER * 16 / 24, defineY() - MAIN_SPACER * 35 / 24);
		}
	}
	
	/**
	* Método sencillo que imprime el tablero, es decir, un rectángulo blanco, en la aplicación
	* gráfica. Para obtener las dimensiones del tablero, se tienen en cuenta los espaciados
	* entre el tablero y el borde de la ventana, el tamaño de las piezas, la separación entre
	* piezas y la separación entre las piezas y el tablero. También se encarga de pintar los botones.
	* @param g Entorno gráfico
	*/
	public void pintarTablero(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRoundRect(MAIN_SPACER, MAIN_SPACER,
			tablero.getSizeY() * (SPOT_SPACER + SQUARE_SIZE) + 2 * BOARD_SPACER - SPOT_SPACER,
			tablero.getSizeX() * (SPOT_SPACER + SQUARE_SIZE) + 2 * BOARD_SPACER - SPOT_SPACER,
			ROUND_DIAMETER, ROUND_DIAMETER);
	}
	
	public void pintarBotones(Graphics2D g) {
		// Primero se dibujan los botones. Solo se deben poder utilizar en modo experimental y cuando
		// solo está jugando un jugador.
		if(getMode() && getPlayers() == 1) {
			g.setColor(Color.white);
			g.fillRect(defineX() - BUTTON_SIZE, defineY() - BUTTON_SIZE,
				BUTTON_SIZE, BUTTON_SIZE); // Botón de toggleConsole()
			g.setColor(Color.darkGray);
			g.drawRect(defineX() - BUTTON_SIZE,  defineY() - BUTTON_SIZE,
				BUTTON_SIZE, BUTTON_SIZE);
			g.setColor(Color.black);
			setFontSize(g, (int) (7 * scale * 2));
			g.drawString("C", defineX() - BUTTON_SIZE * 3 / 4 , defineY() - BUTTON_SIZE / 4);

			g.setColor(Color.white);
			g.fillRect(0, defineY() - BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
			g.setColor(Color.darkGray);
			g.drawRect(0, defineY() - BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
			
			if(!cheatsUsed()) { // Botón de enableCheats()
				setFontSize(g, (int) (7 * scale * 5));
				g.drawString("*", BUTTON_SIZE / 8, defineY() + BUTTON_SIZE / 2);
			} else {
				setFontSize(g, (int) (7 * scale * 4));
				g.drawString("+", BUTTON_SIZE / 7, defineY()); // ponerPieza())
				
				g.setColor(Color.white); // Botón de quitarPieza()
				g.fillRect(BUTTON_SIZE, defineY() - BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
				g.setColor(Color.darkGray);
				g.drawRect(BUTTON_SIZE, defineY() - BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
				setFontSize(g, (int) (7 * scale * 5));
				g.drawString("-", BUTTON_SIZE + 2*BUTTON_SIZE / 8, defineY() - BUTTON_SIZE / 18);
			}
			
			if(getTurnos() > 1 && cheatsUsed()) { // Botón de undo()
				g.setColor(Color.white);
				g.fillRect(defineX() - 2 * BUTTON_SIZE + SPOT_SPACER, defineY() - BUTTON_SIZE,
					BUTTON_SIZE	, BUTTON_SIZE); 
				g.setColor(Color.darkGray);
				g.drawRect(defineX() - 2 * BUTTON_SIZE + SPOT_SPACER, defineY() - BUTTON_SIZE,
					BUTTON_SIZE	, BUTTON_SIZE);

				setFontSize(g, (int) (7 * scale * 3));
				g.drawString("⤺", defineX() - 8 * BUTTON_SIZE / 4 + SPOT_SPACER,
					defineY() - BUTTON_SIZE / 6); // ponerPieza())
			}
		}
		
		
		
	}
	
	/**
	* Método que imprime la siguiente ficha y los puntos actuales en la aplicación gráfica.
	* Se obtiene el color de la siguiente ficha con el HashMap. Para obtener el valor de la
	* próxima ficha, se utiliza {@link #getSiguiente()}.
	* @param g Entorno gráfico
	*/
	public void pintarInfo(Graphics2D g) {
		g.setColor(Color.black);
		setFontSize(g, 15);
		g.drawString("Siguiente:", MAIN_SPACER, defineY() - MAIN_SPACER / 2);
		g.drawString(String.format("[%d]", getTurnos()), defineX() / 2, defineY() - MAIN_SPACER / 2);
		setFontSize(g, getPuntos() >= 10000000 ? 12 : 15);
		g.drawString(String.format("Puntos: %d", getPuntos()), defineX() - 2 * MAIN_SPACER,
				defineY() - MAIN_SPACER / 2);
		
		g.setColor(colores.get(getSiguiente()));
		g.fillRoundRect(MAIN_SPACER * 8 / 4, defineY() - MAIN_SPACER, SQUARE_SIZE, SQUARE_SIZE,
			ROUND_DIAMETER, ROUND_DIAMETER);
		g.setColor(Color.white);
		int desiredFontSize = 19 - (String.valueOf(getSiguiente()).length() - 1);
		setFontSize(g, getSiguiente() >= 350000 ? 10 : desiredFontSize);
		g.drawString(String.format("%d", getSiguiente()), MAIN_SPACER * 112 / 48,
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
	public void pintarFichas(Graphics2D g) {
		for (int i = 0; i < tablero.getSizeX(); i++)
			for (int j = 0; j < tablero.getSizeY(); j++) {
				if (getTab(i, j) != 0) { // Al detectarse una pieza, se obtiene su color referente.
					g.setColor(tablero.getPieza(i, j).getColor());
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
					if(getTurnos() > 1 && warning[0] != -1) {
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
					int desiredFontSize = 19 - (String.valueOf(getTab(i, j)).length() - 1);
					setFontSize(g, getTab(i, j) >= 350000 ? 10 : desiredFontSize);
					int sizer = getTab(i, j) >= 100000 ? (13 - (String.valueOf(getTab(i, j)).length()-1)) : (13 - (2*(String.valueOf(getTab(i, j)).length()-1)));
					g.drawString(String.format("%d", getTab(i, j)),
						MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * j + SQUARE_SIZE * sizer / 32,
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
		
		private int up    = 0;
		private int down  = 0;
		private int left  = 0;
		private int right = 0;
		
		public Jugada(char c) {
			switch (Character.toString(c).toLowerCase().charAt(0)) {
				case 'w': this.Up();                 break; // ARRIBA
				case 'a': this.Left();               break; // IZQUIERDA
				case 's': this.Down();               break; // ABAJO
				case 'd': this.Right();              break; // DERECHA
				case 'q': this.Up();   this.Left();  break; // EXPERIMENTAL: IZQ-ARR
				case 'z': this.Down(); this.Left();  break; // EXPERIMENTAL: IZQ-ABA
				case 'c': this.Down(); this.Right(); break; // EXPERIMENTAL: DER-ABA
				case 'e': this.Up();   this.Right(); break; // EXPERIMENTAL: DER-ARR
				default:
					out.print("ERROR: Jugada inválida.");
					System.exit(0);
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
		* Devuelve el movimiento vertical de la jugada.
		* @return Valor entero
		*/
		public int moveVert() {return this.getDown() - this.getUp();}
		
		/**
		* Devuelve el movimiento horizontal de la jugada.
		* @return Valor entero
		*/
		public int moveHorz() {return this.getRight() - this.getLeft();}
	}
	
	/**
	* Clase que genera objetos tipo 'Pieza' para que sean utilizados en un tablero.
	* Cada objeto tiene dos atributos: un tipo entero que contiene el valor y un
	* objeto de clase 'Color' que contiene el color de la pieza correspondiente a 
	* su valor. <p> Contiene un constructor que inicializa la pieza con el valor
	* sobrecargado y establece su color. <p> Cada vez que se cambia el valor de una
	* pieza, se actualiza su color mediante el método 'updateColor()'.
	*/
	private class Pieza {
		private int valor;
		private Color color;
		
		/**
		* Constructor que inicializa la pieza con el valor sobrecargado.
		* @param nv Valor entero con el valor que se desea establecer a la pieza.
		*/
		public Pieza() {this.setValor(0);}		
		
		/**
		* Método que devuelve el valor de la pieza.
		* @return Tipo entero con el valor.
		*/
		public int getValor() {return valor;}
		
		/**
		* Establece el valor de la pieza y llama a {@link #updateColor()} para que
		* actualice el color de la pieza en base a este nuevo color. El valor
		* sobrecargado debe de ser válido para que la pieza se establezca correctamente.
		* <p> Para comprobar que el valor introducido sea válido, se comprueba que sea 1,
		* 2, 3, algún valor que se corresponda a la serie 6*2n o 0(se utiliza para inicializar
		* el tablero con fichas vacías).
		* @param valor que desea asignarle a la pieza.
		*/
		public void setValor(int valor) {
			boolean check = false;
			if(validValue(valor) || valor == 0) check = true;
			if(check) {
				this.valor = valor;
				updateColor();
			}
		}
		
		/**
		* Método que devuelve el color de la pieza actual.
		* @return Objeto tipo 'Color'
		*/
		public Color getColor() {return color;}
		
		/**
		* Método que actualiza el color de la pieza en base a su valor.
		* Si la ficha no tiene un valor contenido en el HashMap de colores por defecto,
		* se genera un color nuevo y se guarda para que todas las futuras piezas con el
		* mismo valor tengan el mismo color. <p> Se utiliza automáticamente en
		* {@link #setValor(int)} para que cada vez que se cambie el valor de la pieza,
		* también se cambie su color.
		* De esta forma, no es necesario actualizar manualmente el color de la pieza. <p>
		* Si no existe un color predeterminado para el valor de la pieza, se genera y se
		* guarda un color en el HashMap para que todas las futuras piezas con el mismo
		* valor tengan el mismo color. <p>
		* Para más información sobre la distancia entre colores, leer la documentación de la
		* clase principal.
		*/
		public void updateColor() {
			if (!colores.containsKey(getValor())) {
				Color newColor = Color.white; 
				boolean check = true;
				while(check) {
					check = false;
					newColor = new Color(newRandom(256), newRandom(256), newRandom(256));
					for(int i=0; i<colores.keySet().size(); i++) {
   						ArrayList<Integer> targetList = new ArrayList<>(colores.keySet());
						Color compare = colores.get(targetList.get(i)); //ARREGLAR!
						double mediaR = (newColor.getRed() + compare.getRed()) / 2.0;
						double distancia = Math.sqrt( (2 + mediaR / 256) *
							Math.pow((double) newColor.getRed() - compare.getRed(), 2) +
							4 * Math.pow((double) newColor.getGreen() - compare.getGreen(), 2) +
							(2 + (255 - mediaR) / 256.0));
						out.println(distancia);	
						if (distancia < 200) check = true;
					}
				}
				colores.put(getValor(), newColor);
			}
			this.color = colores.get(getValor());
		}
	}
	
	/**
	* Clase que genera objetos tipo 'Tablero'. Contiene tres atributos: una matriz de objetos
	* 'Pieza' y dos enteros que definen el tamaño del atributo. En el constructor, se sobrecarga
	* con dos valores enteros, las dimensiones del tablero. Para facilitar la interacción con los
	* setters y getters principales, existen los métodos 'getPieza()' y 'setPieza()', así como
	* setters y getters para 'sizeX'y 'sizeY'. 
	*/
	public class Tablero {
		private Pieza[][] tablero;
		private int sizeX;
		private int sizeY;
		
		/**
		* Constructor que inicializa el tablero. Para hacerlo, se toman las dimensiones
		* sobrecargadas. A continuación, se llena el tablero de piezas con valor '0', de
		* modo que no aparecen ni en la ventana gráfica ni en la consola, en caso de que
		* esté activada.
		* @param x Cantidad de filas de la matriz.
		* @param y Cantidad de columnas de la matriz.
		*/
		public Tablero(int x, int y) {
			this.setSize(x, y);
			tablero = new Pieza[getSizeX()][getSizeY()];
			for(int i=0; i<getSizeX(); i++) for(int j=0; j<getSizeY(); j++) tablero[i][j] = new Pieza();
			
		}
		
		/**
		* Método que devuelve la dimensión horizontal del tablero.
		* @return Valor entero
		*/
		public int getSizeX() {return sizeX;} 
		
		/**
		* Método que establece las dimensiones del tablero.
		* Para que se pueda establecer, los enteros sobrecargados deben de 
		* ser mayores o iguales a cuatro. En caso contrario, se establece un valor
		* por defecto '5'.
		* @param nv
		*/
		public void setSize(int nx, int ny) {
			if(nx >= 4) this.sizeX = nx;
			else        this.sizeX = 5;
			if(ny >= 4) this.sizeY = ny;
			else        this.sizeY = 5;
			
		}
		
		/**
		* Método que devuelve la dimensión vertical del tablero.
		* @return Valor entero
		*/
		public int getSizeY() {return sizeY;}
		
		/**
		* Método que devuelve la pieza localizada en la posición especificada.
		* @param x Coordenada x de la pieza que se desea obtener.
		* @param y Coordenada y de la pieza que se desea obtener.
		* @return Objeto tipo 'pieza'
		*/
		public Pieza getPieza(int x, int y) {return this.tablero[x][y];}
		
		/**
		* Haciendo uso del método 'setValor(int nv)', establece un valor
		* para la pieza determinada.
		* @param x Coordenada x de la pieza.
		* @param y Coordenada y de la pieza.
		* @param nv Valor que se desea establecer.
		*/
		public void setPieza(int x, int y, int nv) {this.tablero[x][y].setValor(nv);}
	}
	
	
	// -------------------------------------------------------------------------------------------------------------------
	
	/**
	* Recive pulsaciones de teclas del usuario. Si el usuario pulsa una tecla correspondiente a un
	* movimiento (W/A/S/D o ARRIBA/IZQUIERDA/ABAJO/DERECHA), se ejecuta dicha jugada. <p>
	* Con el objetivo de probar el rendimiento en circunstancias extremas del programa, al pulsar
	* AVPAG/PAGE_DOWN junto a la tecla 'control', se mandan jugadas constantes repetidas hasta que se
	* termina el programa. Para que esto funcione, es necesario que los trucos hayan sido activados
	* con anterioridad. <p>
	* Si se pulsa la tecla escape, salta un prompt que le pregunta al usuario si desea terminar la
	* partda. En caso afirmativo, se ejecuta {@link #SumaTres.finalDePartida()} para finalizar la
	* partida en el estado actual del tablero. <p>
	* Si se está jugando en el modo experimental, se juega con las teclas (Q/W/E/D/C/X/Z/A), formando
	* un cuadrado en el teclado que deja la tecla 'S' en medio. Este cuadrado se corresponde con las
	* direcciones que se pueden elegir para la jugada.
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
					while (!getMode()) {
						Jugada('w');
						Jugada('a');
						Jugada('s');
						Jugada('d');
					}
					while(getMode() && cheatsUsed()) {
						Jugada('w');
						Jugada('q');
						Jugada('a');
						Jugada('z');
						Jugada('s');
						Jugada('c');
						Jugada('d');
						Jugada('e');
					}
				}
				break;
				case KeyEvent.VK_ESCAPE: // Si se confirma, se termina la partida.
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
					if(!getMode()) Jugada('s'); break;
				case KeyEvent.VK_X:
					if(!getMode()) break;
				case KeyEvent.VK_DOWN:
					Jugada('s');
					break;
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT:
					Jugada('d');
					break;
				case KeyEvent.VK_C:
					if(getMode()) Jugada('c');
					break;
				case KeyEvent.VK_Z:
					if(getMode()) Jugada('z');
					break;
				case KeyEvent.VK_Q:
					if(getMode()) Jugada('q');
					break;
				case KeyEvent.VK_E:
					if(getMode()) Jugada('e');
					break;
				default:
					break;	// En cualquier otro caso, no se hace nada.
			}
		}
	}
	
	/**
	* Recive los clicks del ratón en la aplicación. Si se hace click en alguna de
	* las cuatro direcciones, se llama a {@link #SumaTres.Jugada(char c)} con el
	* caracter correspondiente a dicha jugada. <p>
	* Si se está jugando en el modo experimental, el método envía jugadas en diagonal
	* cuando el ratón hace click en alguna de las cuatro esquinas clickeables.
	*/
	private class MouseHandler extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent event) {
			if(event.getY() < defineY() - MAIN_SPACER) {
				if (event.getX() < MAIN_SPACER) {
					if (event.getY() > MAIN_SPACER) {
						if (event.getY() > (defineY() - 2 * MAIN_SPACER)) {
							if(getMode()) Jugada('z');
							else          Jugada('a');
						}
						else Jugada('a');
					}
					else {
						if(getMode()) Jugada('q');
						else          Jugada('a');
					}
				} else {
					if (event.getY() < MAIN_SPACER) {
						if (event.getX() > defineX() - MAIN_SPACER) {
							if(getMode()) Jugada('e');
							else          Jugada('w');
						}
						else Jugada('w');
					} else {
						if (event.getY() > (defineY() - 2 * MAIN_SPACER)) {
							if(event.getX() > defineX() - MAIN_SPACER) {
								if(getMode()) Jugada('c');
								else          Jugada('s');
							}
							else Jugada('s');
						}
						else if (event.getX() > (defineX() - MAIN_SPACER)) Jugada('d');
					}
				}
			} else { // Botonera
				if(event.getY() > defineY() - BUTTON_SIZE && getMode() && getPlayers() == 1) {
					if(event.getX() > defineX() - BUTTON_SIZE ) toggleConsole(); // Botón de alternar consola
					else {
						if(event.getX() < BUTTON_SIZE) {
							if(!cheatsUsed()) enableCheats(); // Botón de activar trucos
							else              colocarPieza(); // Botón de colocar nueva pieza
						} else {
							if(event.getX() > defineX() - 2 * BUTTON_SIZE + SPOT_SPACER && getTurnos() > 1 && cheatsUsed()) undo();
							else if(event.getX() < BUTTON_SIZE * 2 && cheatsUsed()) quitarPieza(); // Botón de quitar pieza
						}
					}
				}
			}
		}
	}
}
