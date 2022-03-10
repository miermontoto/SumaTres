package obj;

import java.util.Arrays;
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
    private int amountOfPiezas;

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
            amountOfPiezas = 0;
        }
    }
    
    public boolean setFromString(String s) {    
        String[] tab = s.split(",");
        if(tab.length != columns*rows) return false;
        amountOfPiezas = 0;
        
        for(int i = 0, k = 0; i < columns; i++) for(int j = 0; j < rows; j++, k++) {
            int val = Integer.parseInt(tab[k]);
            if(val != 0) {
                addAmount();
                tablero[i][j].setValor(val);
            } 
        }
        return true;
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
    
    public void addAmount() {this.amountOfPiezas++;}
    public void subAmount() {this.amountOfPiezas--;}
    
    /**
     * Devuelve la cantidad de piezas en tablero.
     * Se utiliza para evitar recorrer el tablero cuando ya se han encontrado
     * todas las piezas.
     * @return 
     */
    public int amount() {return this.amountOfPiezas;}

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
    * Comprueba que si el tablero está lleno. Si la cantidad de piezas es igual
    * a la cantidad de espacios en tablero, se devuelve 'true'.
    * 
    * @return Devuelve un booleano, 'true' si está lleno, 'false' si no.
    */
    public boolean isFull() {
        return amount() == getColumns() * getRows();
    }

    /**
     * Método <code>toString()</code> que devuelve una cadena con el valor de todas las piezas del tablero.
     */
    @Override
    public String toString() {
        String s = "";
        for(int i=0; i < this.getColumns(); i++) 
            for(int j=0; j < this.getRows(); j++) 
                s += String.format("%d,", this.getTab(i, j));
        return s;
    }

    public String formattedToString() {
        String s = "";
        for(int i=0; i < this.getColumns(); i++) {
            for(int j=0; j < this.getRows(); j++) s += String.valueOf(getTab(i, j));
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
        return x.toString().equals(this.toString());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Arrays.deepHashCode(this.tablero);
        return hash;
    }
}