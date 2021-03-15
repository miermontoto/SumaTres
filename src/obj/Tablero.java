package obj;

import util.Dialog;
import util.Input;

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
	private int sizeX;
	private int sizeY;

	/**
	 * Constructor que inicializa el tablero. Para hacerlo, se toman las dimensiones
	 * sobrecargadas. A continuación, se llena el tablero de piezas con valor '0',
	 * de modo que no aparecen ni en la ventana gráfica ni en la consola, en caso de
	 * que esté activada.
	 * 
	 * @param x Cantidad de filas de la matriz.
	 * @param y Cantidad de columnas de la matriz.
	 */
	public Tablero(int x, int y) {
		this.setSize(x, y);
		tablero = new Pieza[x][y];
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++)
				tablero[i][j] = new Pieza();

	}

	/**
	 * Método que devuelve la dimensión horizontal del tablero.
	 * 
	 * @return Valor entero
	 */
	public int getSizeX() {
		return sizeX;
	}

	/**
	 * Método que establece las dimensiones del tablero. Para que se pueda
	 * establecer, los enteros sobrecargados deben de ser mayores o iguales a
	 * cuatro. En caso contrario, se establece un valor por defecto '5'.
	 * 
	 * @param nv
	 */
	public void setSize(int nx, int ny) {
		this.sizeX = nx;
		this.sizeY = ny;
	}

	/**
	 * Método que devuelve la dimensión vertical del tablero.
	 * 
	 * @return Valor entero
	 */
	public int getSizeY() {
		return sizeY;
	}
	
	public void setTab(int x, int y, int nv) {this.getPieza(x, y).setValor(nv);}
	public int getTab(int x, int y) {return this.getPieza(x, y).getValor();}

	/**
	 * Método que devuelve la pieza localizada en la posición especificada. Se puede
	 * utilizar para acceder a piezas cuando no sea posible con
	 * <code>setTab(int, int)</code> o <code>getTab(int, int)</code>.
	 * 
	 * @param x Coordenada x de la pieza que se desea obtener.
	 * @param y Coordenada y de la pieza que se desea obtener.
	 * @return Objeto tipo 'pieza'
	 */
	public Pieza getPieza(int x, int y) {
		return this.tablero[x][y];
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
		while (full && i < this.getSizeX()) {
			int j = 0;
			while(j < this.getSizeY()) {
				if(this.getPieza(i, j).getValor() == 0) full = false;
				j++;
			}
			i++;
		}
		return full;
	}
	
	/**
	 * Método que coloca una pieza en el tablero de manera artificial, obteniendo las coordenadas y el
	 * valor de la nueva pieza y comprobando que todo es correcto. <p>
	 * Para obtener las coordenadas, se utiliza el método {@link #inputCoord(String, int)}. <p>
	 * Para obtener el nuevo valor, se utiliza una versión ligeramente modificada de este último método,
	 * comprobando el valor con {@link #validValue(int)}. <p>
	 * El método debería ser accesible solamente cuando los trucos estén activados.
	 */
	public boolean colocarPieza() {
		boolean completed = false;
		int nX = Input.input("Introduzca la coordenada x de la pieza que desea colocar", 0, this.getSizeX());
		if(nX != -1) {
			int nY = Input.input("Introduzca la coordenada y de la pieza que desea colocar", 0, this.getSizeY());
			if (nY != 1) {
				int nV;
				try {
					String respuesta = Dialog.input("Introduzca un valor para la pieza");
					if (respuesta == null || respuesta.length() == 0) nV = -1;
					else nV = Integer.parseInt(respuesta);

					while (nV < 1 && !Pieza.validValue(nV) || nV == -1) {
						Dialog.showError();
						respuesta = Dialog.input("Introduzca un valor para la pieza");
						if (respuesta == null || respuesta.length() == 0) nV = -1;
						else nV = Integer.parseInt(respuesta);
					}
				} catch (Exception ex) {
					Dialog.showExceptionError(ex);
					nV = -1;
				}
				if(nV != -1) {
					this.setTab(nX, nY, nV);
					completed = true;
				}
			}
		}
		return completed;
	}

	/**
	 * Método que quita una pieza de manera artificial, obteniendo las coordenadas y comprobando que
	 * son correctas mediante {@link #util.Input.input(String, int, int)}. <p>
	 * El método debería ser accesible solamente cuando los trucos estén activados.
	 * @return Valor booleano que determina si se ha eliminado o no una pieza.
	 */
	public boolean quitarPieza() {
		boolean completed = false;
		int nX = Input.input("Introduzca la coordenada x de la pieza que desea eliminar", 0, this.getSizeX());
		if(nX != -1) {
			int nY = Input.input("Introduzca la coordenada y de la pieza que desea eliminar", 0, this.getSizeY());
			if (nY != -1 && this.getPieza(nX, nY).getValor() != 0) {
				this.setTab(nX, nY, 0);
				completed = true;
			}
		}
		return completed;
	}
}