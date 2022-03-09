package obj;

import util.Dialog;

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
 * Si se introduce un caracter inválido, el programa se cierra. Esto solo puede
 * pasar en caso de que el usuario manipule el código manualmente.
 * <p>
 * Contiene dos métodos (<code>moveVert()</code> y <code>moveHorz()</code>).
 * Estos dos métodos existen para que las detecciones de movimiento sean más
 * cortas y más sencillas, ya que todas usan el desplazamiento de la misma
 * manera.
 * 
 * @see <a href="https://rules.sonarsource.com/java/RSPEC-131"> SonarLint: RSPEC-131 </a>
 *		<blockquote> The requirement for a final default clause is defensive programming. The
 * 		clause should either take appropriate action, or contain a suitable comment as to why
 * 		no action is taken. </blockquote>
 */
public class Jugada {

    private int up    = 0;
    private int down  = 0;
    private int left  = 0;
    private int right = 0;
    private String nombre;

    public Jugada(char c) {
        switch (Character.toString(c).toLowerCase().charAt(0)) {
            case 'w': this.up    = 1;                 nombre = "up";         break; // ARRIBA
            case 'a': this.left  = 1;                 nombre = "left";       break; // IZQUIERDA
            case 'x':                                                               // EXPEIRMENTAL: ABAJO
            case 's': this.down  = 1;                 nombre = "down";       break; // ABAJO
            case 'd': this.right = 1;                 nombre = "right";      break; // DERECHA
            case 'q': this.up    = 1; this.left  = 1; nombre = "up-left";    break; // EXPERIMENTAL: IZQ-ARR
            case 'z': this.down  = 1; this.left  = 1; nombre = "down-left";  break; // EXPERIMENTAL: IZQ-ABA
            case 'c': this.down  = 1; this.right = 1; nombre = "down-right"; break; // EXPERIMENTAL: DER-ABA
            case 'e': this.up    = 1; this.right = 1; nombre = "up-right";   break; // EXPERIMENTAL: DER-ARR
            default:
                Dialog.showError("ERROR: Caracter de jugada inválido.");
                System.exit(1);
        }
    }
    
    /**
     * Devuelve el nombre de la dirección de la jugada.
     * @return String con el nombre.
     */
    public String getNombre() {return this.nombre;}

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