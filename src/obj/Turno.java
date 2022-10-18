package obj;

import game.SumaTres;
import handler.Keyboard;


/**
 * Método que maneja el movimiento del turno; mover o sumar las piezas
 * del tablero. <p>
 * Llevo mucho tiempo sin modificar esta parte del código, tanto que no
 * recuerdo muy bien cómo funciona, pero funciona. Está propiamente explicado
 * (o debería).
 *
 * @author Juan Mier
 */
public final class Turno {

    private final SumaTres game;
    private final Tablero board;
    private final Jugada move;

    public Turno(SumaTres gameObject, Jugada moveObject) {
        game = gameObject;
        move = moveObject;

        board = gameObject.getTablero();
    }

    /**
     * Método utilizado por {@link #game.SumaTres.jugada(char)} para mover las piezas. Se mueven las
     * piezas hasta que hay otra pieza en la dirección o se encuentra con el borde
     * del tablero. Después de mover las piezas, se comprueba si se pueden mover más
     * mediante un recorrido de todo el tablero. Si se encuentra alguna
     * pieza que se pueda mover, el atributo 'check' se convierte en true, con lo
     * que se mantiene el bucle while y se vuelven a mover todas las piezas.
     * Obviamente, aquellas piezas que no se puedan mover más en la dirección
     * escogida, se quedarán en la misma casilla.
     */
    public void mover() {
        boolean check = true;
        while (check) {

            // Si se detecta un espacio vacío en la dirección de movimiento, se mueve la pieza.
            for (int i = move.getUp() + move.getDown()*(board.getColumns() - 2); i < board.getColumns() && i >= 0; i += 1 - 2*  move.getDown())
                for (int j = move.getLeft() + move.getRight()*(board.getRows() - 2); j < board.getRows() && j >= 0; j += 1 - 2 * move.getRight()) {
                    if (board.getTab(i + move.moveVert(), j + move.moveHorz()) == 0) {
                        board.setTab(i + move.moveVert(), j + move.moveHorz(), board.getTab(i, j));
                        board.setTab(i, j, 0);
                }
            }

            /*
             * Si se encuentra una pieza que contenga un valor y la siguiente pieza en la
             * dirección seleccionada está vacía, significa que se puede seguir moviendo el tablero.
             * De lo contrario, check se mantiene false por lo que se sale del bucle y se
             * termina el movimiento de las piezas.
             */
            check = false;
            for (int i = move.getUp() + move.getDown() * (board.getColumns() - 2); i < board.getColumns() && i >= 0 && !check; i += 1 - 2 * move.getDown())
                for (int j = move.getLeft() + move.getRight() * (board.getRows() - 2); j < board.getRows() && j >= 0 && !check; j += 1 - 2 * move.getRight()) {
                    if (board.getTab(i, j) != 0 && (board.getTab(i + move.moveVert(), j + move.moveHorz()) == 0)) check = true;
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
     */
    public void sumar() {
        for (int i = move.getUp() + move.getDown() * (board.getColumns() - 2); i < board.getColumns() && i >= 0; i += 1 - 2 * move.getDown())
            for (int j = move.getLeft() + move.getRight() * (board.getRows() - 2); j < board.getRows() && j >= 0; j += 1 - 2 * move.getRight()) {
                if (sumaCond(i, j, move, board)) { // Si se puede sumar, se convierte la nueva casilla en la suma y la antigua en 0.
                    int addResult = board.getTab(i, j) + board.getTab(i + move.moveVert(), j + move.moveHorz());
                    board.setTab(i + move.moveVert(), j + move.moveHorz(), addResult);
                    board.setTab(i, j, 0);
                    game.addPuntos(addResult);
                    game.setHighest(addResult); // Se comprueba si la mayor pieza es la recién sumada.
                    board.subAmount(); // resta 1 al número de fichas en el tablero.
            }
        }
        mover(); // Se mueve al terminar de suma para evitar que queden huecos vacíos.
    }

    /**
     * Comprueba si se ha terminado la partida. Para esto, escanea el tablero
     * repetidas veces con todas los posibles movimientos. Si en algún momento se
     * detecta que hay una suma que se pueda hacer, el check devuelve 'true'. Si el
     * tablero no está lleno, devuelve 'true'.
     * <p>
     * Se utiliza {@link #sumaCond(int, int, Jugada)} para verificar si la suma es
     * posible o no.
     * <p>
     * Se utiliza {@link #obj.Tablero.isFull()} como condición necesaria para seguir
     * comprobando si la partida está acabada. Si el tablero no está lleno, es
     * imposible que la partida esté terminada.
     * <p>
     * Si se está jugando con movimientos diagonales, los comprueba también.
     * @param game Objeto de tipo 'SumaTres' que se desea analizar.
     * @return Valor 'booleano' definiendo si es posible algún movimiento.
     */
    public static boolean ableToMove(SumaTres game) {
        Tablero board = game.getTablero();
        if (!board.isFull()) return true;

        String movesToCheck = game.getSettings().getStatus("diagonalMovement") ?
            Keyboard.VALID_EXPERIMENTAL_KEYS : Keyboard.VALID_CLASSIC_KEYS;

        for(int o = 0; o < movesToCheck.length(); o++) {
            Jugada move = new Jugada(movesToCheck.charAt(o));
            for (int i = move.getUp(); i + move.getDown() < board.getColumns(); i++)
                for (int j = move.getLeft(); j + move.getRight() < board.getRows(); j++) {
                    if (sumaCond(i, j, move, board)) return true;
            }
        }
        return false;
    }


    /**
     * Condición que detecta si se puede sumar o no.
     * <p>
     * Para determinar que sea una suma válida, se comprueba: o bien que sean piezas
     * iguales (excepto 1 y 2), o bien que una de las piezas sea 1 y la otra 2. <p>
     * Esto es FUNDAMENTAL!!! para el funcionamiento del juego.
     *
     * @param x Posición x del tablero.
     * @param y Posición y del tablero.
     * @param move Objeto de la clase 'Jugada' que define el movimiento.
     * @param board Objeto de la clase 'Tablero' en el que se está jugando.
     * @return  Un booleano, 'true' si se puede sumar, 'false' si no.
     */
    private static boolean sumaCond(int x, int y, Jugada move, Tablero board) {
        return (board.getTab(x + move.moveVert(), y + move.moveHorz()) == board.getTab(x, y) && board.getTab(x, y) >= 3)  ||
                        (board.getTab(x, y) + board.getTab(x + move.moveVert(), y + move.moveHorz()) == 3 &&
                            (board.getTab(x, y) == 2 || board.getTab(x, y) == 1));
    }
}
