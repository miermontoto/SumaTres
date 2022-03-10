package game;

import gui.ModifyBoardDialog;
import obj.Jugada;
import obj.Tablero;
import obj.Turno;
import obj.Settings;
import util.Dialog;
import util.Paint;
import util.Random;
import obj.Pieza;
import handler.Mouse;
import handler.Keyboard;
import handler.FileWS;

import static java.lang.System.out;
import static java.lang.System.err;
import java.awt.Graphics;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
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
 * "./assets/resultados.txt"
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
 * Code coverage: ~95% (v13) - Programas utilizados: Eclipse, VSCode, NetBeans.
 * 
 * @author Juan Mier
 * @version v21
 * @see <a href="https://docs.oracle.com/javase/tutorial/java/data/numberformat.html">
 *      Documentación de Oracle: Number Format </a> <blockquote> A new line
 *      character appropriate to the platform running the application. You
 *      should always use %n, rather than \n. </blockquote>
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
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/io/FileWS.html">
 		Documentación de Oracle: FileWS </a>
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
public final class SumaTres extends JPanel {

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
    public static final File ARCHIVO = new File("./assets/resultados.txt");
    public static final String VERSION = "v21";

    private Map<Integer, Integer> obtainedFromRandom; // Diccionario que almacena las fichas obtenidas en el modo clásico.
    private LinkedList<Tablero> tableros; // Cola que guarda todos los tableros de la partida.
    private int[] warning; // Vector que define la posición de la nueva ficha.
    private int[] selected; // Vector que define la posición de la ficha señalada.
    private int turno; // Contador de turnos.
    private int siguiente; // Valor de la siguiente ficha a colocar.
    private int highestValue; // Valor de la ficha más alta.
    private long puntos; // Contador de puntos.
    private double difficultyMultiplier; // Multiplicador de puntuación final.
    private boolean cheatsUsed; // Estado de activación de los trucos.
    private Tablero t; // Tablero sobre el que se juega la partida.
    private Settings op; // Opciones de la partida.
    private boolean finished; // Booleano que determina si la partida se ha terminado ya o no.
    private long startTime; // Computer time para el comienzo de la partida.

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
    * @param op: Objeto de tipo obj.Settings que defina la partida.
    */
    public SumaTres(Settings op) {
        
        startTime = System.currentTimeMillis();
        this.op = op;
        vrbMsg(2, "Partida iniciada.");
        vrbMsg(1, String.format("INFO: Verbosity level = %d", op.verbosity()));

        // Se inicializan variables.
        cheatsUsed = false;
        obtainedFromRandom = new TreeMap<>();
        tableros = new LinkedList<>();
        warning = new int[] {-1, 0};
        selected = new int[] {-1, 0};
        puntos = 0;
        highestValue = 3;
        turno = 1;
        finished = false;
        difficultyMultiplier = op.isEnhancedDiffMultEnabled() ? calculateMultiplier() : 1.0;

        try {t = new Tablero(op.getX(), op.getY());} catch (Exception ex) {
            err.printf("ERROR: %s."
                    + " Estableciendo tablero por defecto 5x5.", ex);
            t = new Tablero(5, 5);
        }

        /*
         * Debido a cómo funciona la clase 'Pieza', se debe de inicializar los colores ANTES de
         * generar el set de fichas inicial. De lo contrario, la primera jugada contaría con piezas
         * de colores aleatorios durante ese turno solamente.
         */
        Pieza.inicializarColores(); vrbMsg(2, "Colores inicializados.");
        if (op.isBalancedStartEnabled()) {
            int tmpLimit = Math.max((int) (0.15 * t.getColumns() * t.getRows()) / 3, 1);
            for(int i = 0; i < tmpLimit; i++) 
                generarSetFichas();
            vrbMsg(2, String.format("Generado(s) %d set(s) de ficha(s).", tmpLimit));
        }
        else generarSetFichas(); vrbMsg(2, "Generado set de fichas.");
         /*
          * Se hace Math.max porque en el tablero 3x3, el resultado del cálculo es 0.45,
          * que convertido a entero es 0, lo que resulta en un tablero vacío al empezar
          * la partida en modo experimental.
          */
         
         if(op.isConsoleEnabled()) out.println(fullToString());


        newSiguiente(); // Se establece la ficha 'siguiente' por primera vez.
    }

    /**
     * Método que genera un set estándar de tres fichas: 1, 2 y 3.
     * Solo se accede al comienzo de la partida en el modo clásico, pero podría
     * ser útil en caso de implementar otros modos de juego o hacer debug.
     */
    public void generarSetFichas() {
        for(int i = 1; i <= 3; i++) {
            int[] loc = validLocation();
            setTab(loc[0], loc[1], i);
            t.addAmount();
        }
    }
    
    /**
     * Método que calcula la dificultad en caso de estar activada la opción "enhancedDiffMult".
     * Calcula una dificultad proporcional al tamaño del tablero. Para el tablero
     * por defecto, 5x5, la dificultad será 1. Para tableros inferiores, el multiplicador
     * podrá llegar hasta 2 en el mínimo tablero posible, es decir, 2x2. Para tableros
     * mayores, devolverá el cálculo de una función logarítmica invertida o 0,
     * dependiendo de cuál sea mayor.
     * @return 
     */
    private double calculateMultiplier() {
        int res = op.getX() * op.getY();
        if(res < 25) return -0.08*res+3;
        return Math.max(Math.log(15.6 - 0.05 * res) - 1.66, 0);
    }

    // --- sets y gets --- //
    /**
     * Devuelve el vector que define la posición de la nueva ficha.
     * @return vector con las coordenadas.
     */
    public int[] getWarning() {return this.warning;}

    /**
     * Establece directamente un vector como el vector que contiene la posición
     * de la pieza siguiente. NO se comprueba que la posición sea válida ni
     * correcta, ya que se presupone que la posición viene de <code>validLocation()</code>,
     * que solo puede generar posiciones válidas por definición.
     * @param x Vector a establecer.
     */
    private void setWarning(int[] x) {if(x.length == 2 && x[0]>=0 && x[1]>=0 &&
            x[0] < op.getX() && x[1] < op.getY()) this.warning = x;}
    
    
    public int[] getSelected() {return this.selected;}
    
    public void setSelected(int[] x) {if(x.length == 2 && x[0]>=0 && x[1]>=0 &&
            x[0] < op.getX() && x[1] < op.getY()) this.selected = x;}

    /**
     * Devuelve la ficha de mayor valor actual.
     * 
     * @return entero con el valor actuales.
     */
    public int getHighest() {return this.highestValue;}

    /**
     * Comprueba y establece el mayor valor de cualquier ficha en el tablero.
     * 
     * @param puntos que se quiera establecer como máximo.
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
    public void addPuntos(long puntos) {if(puntos>=3) this.puntos += puntos;}

    /**
     * Devuelve el valor de la ficha que se encuentre en unas coordenadas.
     * 
     * @param x Coordenada x que se desea analizar.
     * @param y Coordenada y que se desea analizar.
     * @return  Entero con el valor de la casilla.
     */
    public int getTab(int x, int y) {return this.t.getTab(x, y);}

    /**
     * Establece el valor de una ficha para una casilla.
     * 
     * @param x  Coordenada x que se desea cambiar.
     * @param y  Coordenada y que se desea cambiar.
     * @param nv Nuevo Valor de la casilla.
     */
    public void setTab(int x, int y, int nv) {this.t.setTab(x, y, nv);}

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
     * Activa o desactiva el toString() por consola.
     * Solo accessible en el modo experimental.
     */
    public void toggleConsole() {
            String s;
            if(op.isConsoleEnabled()) s = "Desactivada salida por consola.";
            else                      s = "Activada salida por consola.";
            op.toggleConsole();
            update();
            Dialog.show(s);
    }

    public boolean isFinished() {
        return this.finished;
    }

    /**
     * Activa los trucos en la partida actual.
     * Solo accessible en el modo experimental.
     */
    public void enableCheats() {
        cheatsUsed = true;
        setMultiplier(0.0);
        op.toggleSaveResultsToFile(); // se desactiva guardar los resultados de una partida con trucos.
    }

    /**
     * Devuelve el estado de los trucos.
     * @return Valor booleano.
     */
    public boolean areCheatsEnabled() {return this.cheatsUsed;}

    /**
     * Método que devuelve las opciones con las que se está jugando.
     * @return Objeto de tipo 'obj.Settings'.
     */
    public Settings getSettings() {return this.op;}

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
    
    public void deactivateSelected() {this.selected[0] = -1;}

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

    public Map<Integer, Integer> getObtainedFromRandom() {
        return obtainedFromRandom;
    }

    public void setPuntos(long nv) {
        if(nv >= 0) this.puntos = nv;
    }
    
    public void setTurno(int nv) {
        if(nv >= 1) this.turno = nv;
    }
    
    public void setSettings(Settings nv) {
        this.op = nv;
    }

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
        long jstTime = 0;
        long tmpTime = 0;
        if(op.verbosity() == 2) jstTime = System.currentTimeMillis();
        
        String oldStatus = t.toString();
        Tablero temp = new Tablero(op.getX(), op.getY());
        temp.setFromString(t.toString());
        Jugada x = new Jugada(c); // Se crea un objeto jugada que almacena los valores del movimiento.
        Turno turn = new Turno(this, x); // Se crea un objeto tipo "Turno" que ejecute la jugada.
        
        vrbMsg(1, String.format("Jugada: %s", x.getNombre()));

        if(op.verbosity() == 2) tmpTime = System.currentTimeMillis();
        long tmpPuntos = getPuntos();
        turn.mover();
        if(op.isConsoleEnabled()) out.println(this); //TODO: hacer que los saltos de línea coincidan
        turn.sumar();
        vrbMsg(2, String.format("La jugada ha tardado %s en calcularse.", getTime(tmpTime)));
        vrbMsg(1, String.format("Se han conseguido %d puntos en la última jugada.", getPuntos() - tmpPuntos));


        /*
         * Para evitar que el programa intente calcular infinitamente un sitio
         * vacío de manera aleatoria cuando el tablero está lleno, se comprueba antes
         * que el tablero no esté lleno mediante isFull(). Así, la siguiente ficha
         * a colocar no varía.
         */
        if (!t.isFull()) {
            vrbMsg(1, String.format("Colocando ficha siguiente, \"%d\"", getSiguiente()));
            colocarSiguiente();
        }
        else vrbMsg(1, "El tablero está lleno, no se ha colocado ficha siguiente.");

        
        String newStatus = t.toString();
        vrbMsg(2, String.format("ID tablero previo: %s", oldStatus));
        vrbMsg(2, String.format("ID tablero nuevo : %s", newStatus));
        
        if (!newStatus.equals(oldStatus)) {
            vrbMsg(2, "La jugada ha modificado el tablero, sumando un turno.");
            addTurno(); // Si el tablero ha cambiado, se añade un turno.
            tableros.addLast(temp);
        } else {
            vrbMsg(2, "La jugada no ha modificado el tablero.");
            deactivateWarning();
        }
        

        update(); // Se actualizan las salidas para mostrar los cambios en el tablero.
        if (!Turno.ableToMove(this)) finalDePartida(); // Si no se puede mover, se termina la partida.
        else vrbMsg(2, "Quedan jugadas posibles.");
        
        vrbMsg(2, String.format("La jugada ha tardado %s en ejecutarse.", getTime(jstTime)));
    }
    

    /**
     * Devuelve el tablero a la situación anterior.
     */
    public void undo() {
        if (!tableros.isEmpty()) { // Se comprueba que haya más de un tablero.
            setTablero(tableros.removeLast());
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
        if(op.isConsoleEnabled()) out.print(fullToString());
    }
    
    private String getTime() {
        return getTime(startTime);
    }
    
    private String getTime(long start) {
        return String.valueOf((System.currentTimeMillis() - start) / 1000.0);
    }
    
    public void colocarSiguiente() throws NullPointerException {
        int[] loc = validLocation();
        if(loc == null) throw new NullPointerException("No se ha encontrado lugar donde insertar pieza siguiente (NULL).");
        setWarning(loc);
        setTab(loc[0], loc[1], getSiguiente());
        t.addAmount();
        newSiguiente();
    }

    /**
     * Método que devuelve todos los posibles valores que pueden establecerse
     * como "siguiente" en el modo experimiental.
     * 
     * @return
     */
    public int[] possibleNextValues() {
        if(getHighest() <= 24) {
            // Si la ficha no supera 24, el método de obtener el valor de
            // la siguiente ficha es el clásico.
                return new int[] {1, 2, 3};
        } else {
            var i = 0;
            while(getHighest() != 12*Math.pow(2, i)) {i++;}

            var vlsSig = new int[i+1];
            for(int h=0; h<3; h++) vlsSig[h] = h+1; // Se introducen 1, 2 y 3 en el vector de posibles valores.+

            for(int j=0; j < vlsSig.length - 3; j++) {
                vlsSig[3+j] = (int) (6 * Math.pow(2, j)); 
            }

            return vlsSig;
        }
    }
    
    public void vrbMsg(int i, String s) {
        if(i <= op.verbosity()) {out.printf("[%s] %s%n", getTime(), s);}
    }

    /**
     * Método experimental para generar nuevos siguientes. Tiene en cuenta el tamaño de las piezas
     * y aumenta las probabilidades de piezas menores, especialmente 1, 2 y 3.
     *
     * @see <a href="https://stackoverflow.com/questions/880581/how-to-convert-int-to-integer-in-java">
     *		Pasar de int[] a List </a>
     */
    private void newSiguienteExperimental() {
   	if(op.isMoreNextValuesEnabled()) {
            int[] values = possibleNextValues();
            if(values.length == 3) setSiguiente(Random.newRandom(3) + 1);
            else {
                int finalValue;
                //List<Integer> probabilities = Arrays.stream(values).boxed().collect(Collectors.toList());
                int otherValues = values.length - 3;
                double max = values.length * 5 + 20 / 100.0;
                if(max < 0.55) max = 0.55;
            }
     	}
    	else setSiguiente(Random.newRandom(3) + 1);
    	obtainedFromRandom.put(getSiguiente(),
            obtainedFromRandom.containsKey(getSiguiente()) ? obtainedFromRandom.get(getSiguiente()) + 1 : 1);
    }

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
    @Deprecated (since="v20", forRemoval=false)
    private void newSiguiente() {
        if(op.isMoreNextValuesEnabled()) 
            setSiguiente(possibleNextValues()[Random.newRandom(possibleNextValues().length)]);
        else 
            setSiguiente(Random.newRandom(3) + 1);
        
        obtainedFromRandom.put(getSiguiente(),
            obtainedFromRandom.containsKey(getSiguiente()) ? obtainedFromRandom.get(getSiguiente()) + 1 : 1);
    }

    /**
     * Método que genera una posición válida en la que colocar una ficha.
     * @return Vector de enteros -> x[0] es la coordenada x, x[1] la y.
     */
    public int[] validLocation() {
        if(t.isFull()) return null;
        else {
            int[] x = new int[2];
            int nX = Random.newRandom(t.getColumns());
            int nY = Random.newRandom(t.getRows());
            while (getTab(nX, nY) != 0) { // Si ya hay una pieza en la casilla indicada, se vuelve a obtener otra.
                nX = Random.newRandom(t.getColumns());
                nY = Random.newRandom(t.getRows());
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
     * @return Cadena con el tablero actual.
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
        if (areCheatsEnabled()) salida += "Se han utilizado trucos.";
        Dialog.show(salida);
        

        if(op.isConsoleEnabled()) {
            out.printf("%n%n%s%n",salida);
            out.print("Fichas obtenidas: ");
            for(int i=0; i < possibleNextValues().length; i++) {
                out.printf("[%d]: %d ",
                    possibleNextValues()[i], obtainedFromRandom.get(possibleNextValues()[i]));
            }
            out.println();
        }
        
        if(op.isSaveResultsToFileEnabled()) {
            String output = String.format("[%s] %s\t%dx%d\tPTS %d\tMAX %d\tTURN %d%n",
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                VERSION, getTablero().getColumns(), getTablero().getRows(), puntos, getHighest(),
                getTurnos());
            FileWS.write(output, ARCHIVO);
            if(op.isConsoleEnabled()) out.println("Puntuaciones guardadas.");
        }

        if(op.isExitOnEndEnabled()) System.exit(0); // Se termina con estado '0' para indicar que se termina correctamente.
        Keyboard.disableHandling(); // desactiva la entrada por teclado.
        Mouse.disableHandling(); // desctiva la entrada por ratón.
        finished = true;
        repaint();
    }

    
    public void modificarSiguiente() {
        int nV = Dialog.valueDialog("Introduzca el nuevo valor de la pieza siguiente:");
        if (nV != -1) {
            setSiguiente(nV);
            update();
        }
    }
    
    public boolean modificarTablero() {
        boolean check = false;
        ModifyBoardDialog dialog = new ModifyBoardDialog(this);
        if (dialog.showDialog()) {
            if(dialog.getMode() == 0 || dialog.getMode() == 1) {
                if(getTab(dialog.getCoordsX(), dialog.getCoordsY()) != 0) t.addAmount();
                setTab(dialog.getCoordsX(), dialog.getCoordsY(), dialog.getValue());
            }
            else {
                t.subAmount();
                setTab(dialog.getCoordsX(), dialog.getCoordsY(), 0);
            }
            update();
            check = true;
        }
        deactivateSelected();
        return check;
    }

    // ----------------------------------------------------------------------------------------------------

    /**
     * Método que redirige los gráficos a {@link #paint(Graphics)}. <p>
     * También pide el focus de la ventana al comenzar a pintar, para evitar que se pierdan
     * pulsaciones de teclado al comenzar la partida.
     */
    @Override
    public void paintComponent(Graphics g) {
        requestFocusInWindow(); // <- IMPORTANTE! necesita el focus para poder redirigir el teclado
        super.paintComponent(g);
        new Paint(this, g).paint();
    }


}