package obj;

import gui.GetMatrixCoordsDialog;
import util.Dialog;

/**
 * Clase que genera objetos tipo 'Tablero'. Contiene tres atributos: una matriz
 * de objetos 'Pieza' y dos enteros que definen el tamaño del atributo. En el
 * constructor, se sobrecarga con dos valores enteros, las dimensiones del
 * tablero. Para facilitar la interacción con los setters y getters principales,
 * existen los métodos 'getPieza()' y 'setPieza()', así como setters y getters
 * para 'sizeX'y 'sizeY'.
 */
public class Tablero {
    private Pieza[][] tablero;
    private final int columns;
    private final int rows;

    /**
     * Constructor que inicializa el tablero. Para hacerlo, se toman las dimensiones
     * sobrecargadas. A continuación, se llena el tablero de piezas con valor '0',
     * de modo que no aparecen ni en la ventana gráfica ni en la consola, en caso de
     * que esté activada.
     * 
     * @param x Cantidad de columnas de la matriz.
     * @param y Cantidad de filas de la matriz.
     */
    public Tablero(int x, int y) {
        try {tablero = new Pieza[x][y];} catch (Exception ex) {
            Dialog.showError(ex);
            x = 5;
            y = 5;
            tablero = new Pieza[x][y];
        } finally {
            columns = x;
            rows = y;
            for (int i = 0; i < x; i++) for (int j = 0; j < y; j++) tablero[i][j] = new Pieza();
        }
    }

    /**
     * Constructor que copia los parámetros y valores de las piezas de otro tablero.
     * 
     * @param x Objeto tablero a copiar.
     */
    public Tablero(Tablero x) {
        columns = x.getColumns();
        rows = x.getRows();
        tablero = x.tablero;
    }

    /**
     * Método que devuelve la dimensión horizontal del tablero.
     * 
     * @return Valor entero
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Método que devuelve la dimensión vertical del tablero.
     * 
     * @return Valor entero
     */
    public int getRows() {
            return rows;
    }

    public void setTab(int x, int y, int nv) {this.getPieza(x, y).setValor(nv);}
    public int getTab(int x, int y) {return this.getPieza(x, y).getValor();}

    /**
     * Método que devuelve la pieza localizada en la posición especificada. Se puede
     * utilizar para acceder a piezas cuando no sea posible con
     * <code>setTab(int, int)</code> o <code>getTab(int, int)</code>. <p>
     * Si no puede devolver la ficha especificada, devuelve la ficha 0, 0.
     * 
     * @param x Coordenada x de la pieza que se desea obtener.
     * @param y Coordenada y de la pieza que se desea obtener.
     * @return Objeto tipo 'pieza'
     */
    public Pieza getPieza(int x, int y) {
        try {
            return this.tablero[x][y];
        } catch (Exception ex) {
            Dialog.showError(ex);
            return this.tablero[0][0];
        }
    }

    /**
    * Comprueba que si el tablero está lleno. Se recorre todo el tablero,
    * si se detecta una casilla vacía (sin valor), se para de comprobar y
    * se devuelve <code>false</code>. Si se llega al final del tablero y
    * ninguna pieza está vacía, se devuelve <code>true</code>.
    * 
    * @return Devuelve un booleano, 'true' si está lleno, 'false' si no.
    */
    public boolean isFull() {
        int i = 0;
        boolean full = true;
        while (full && i < this.getColumns()) {
            int j = 0;
            while(j < this.getRows()) {
                if(this.getPieza(i, j).getValor() == 0) full = false;
                j++;
            }
            i++;
        }
        return full;
    }



    /**
     * Método <code>toString()</code> que devuelve una cadena con el valor de todas las piezas del tablero.
     */
    @Override
    public String toString() {
        String s = "";
        for(int i=0; i < this.getColumns(); i++) for(int j=0; j < this.getRows(); j++) s += String.format("%d", this.getTab(i, j));
        return s;
    }

    public String formattedToString() {
        String s = "";
        for(int i=0; i < this.getColumns(); i++) {
            for(int j=0; j < this.getRows(); j++) s += String.format("%d", this.getTab(i, j));
            s += String.format("%n");
        }
        return s;
    }

    /**
     * Método que compara los <code>toString()</code> de dos objetos y devuelve verdadero si son iguales.
     * Se da por supuesto que ningún otro objeto devolvería una cadena ni siquiera similar al <code>toString()</code>
     * de un objeto de tipo Tablero. Por eso, no es necesario siquiera examinar la clase del objeto, simplemente
     * igualar las cadenas resultantes. <p> Este método evita tener que comparar una a una el valor de cada pieza.
     * @param x: Objeto con el que compararse.
     */
    @Override
    public boolean equals(Object x) {
        return (x.getClass().equals(this.getClass()) ? (x.toString() == null ? 
            this.toString() == null : x.toString().equals(this.toString())) :
            false);
    }
}