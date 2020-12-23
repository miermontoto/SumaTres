import static java.lang.System.out;
import java.util.Random;
public class SumaTres {

	// TODO lógica de suma completa
	// TODO nueva ficha cada jugada
	// TODO imprimir matriz
	// TODO main
	// TODO puntos
	// TODO comprobar final de partida
	// TODO JavaDoc
	// TODO documentación pdf
	// TODO reestructuración y comentarios
	// TODO limpieza de código
	// TODO casos extremos
	// TODO ¿más setters y getters?


	private int sizeX;
	private int sizeY; //el tamaño del tablero se establece en los constructores
	private int turnos = 0; //no es necesario
	private int siguiente = 1;
	private int[][] tablero = new int[sizeX][sizeY];
	private int puntos = 0;

	public SumaTres() { //el constructor por defecto establece 5x5 (como en las demos) para el tablero
		this(5, 5);
	}

	public SumaTres(int x, int y) {
		if(x>=3 && y>=3) {this.sizeX = x; this.sizeY = y;}
		else {this.sizeX = 5; this.sizeY = 5;}
		// se presupone que el tamaño mínimo del tablero es 3x3
		// de no ser así, se establece el tamaño por defecto.
	}



	// --- sets y gets --- //
	public int getPuntos() {return this.puntos;}
	public void setPuntos(int puntos) {this.puntos = puntos;}

	public int getTab(int x, int y) {return this.tablero[x][y];}
	public void setTab(int x, int y, int nv) {this.tablero[x][y] = nv;}

	public int getSiguiente() {return this.siguiente;}
	public void setSiguiente(int siguiente) {this.siguiente = siguiente;}
	// --- sets y gets --- //

	public void Jugada(char c) {
		int movx = 0;
		int movy = 0;
		//delimitan el desplazamiento del movimiento
		//para arriba, el desplazamiento es -1 en la vertical
		//para izquierda, el desplazamiento es -1 en la horizontal
		//utilizando esto, se reduce el código en un 70% aprox.

		switch(c) {
		case 'a': //ARRIBA
			movy--;
			turnos++;
			break;
		case 'b': //ABAJO
			movy++;
			turnos++;
			break;
		case 'i': //IZQUIERDA
			movx--;
			turnos++;
			break;
		case 'd': //DERECHA
			movx++;
			turnos++;
			break;
		default: //otro valor (inválido)
			out.println("Jugada inválida.");
			break;
		}

		// si la casilla destino está vacía, simplemente se mueve.
		// si la casilla destino es igual o suman 3 (1+2 || 2+1), se suman.
		// se podría simplificar si se utilizara un solo conjunto de bucles for,
		// sumando y moviendo a la vez, pero eso no se asemeja con la demo.
		//
		// TODO comprobar casos de varias casillas iguales juntas.
		// TODO imprimir la matriz movida antes de sumar elementos.
		// TODO comprobar el orden de suma de las casillas.

		if(movx != 0 && movy != 0) { //si no se desplaza el índice significa que no es una jugada válida.
			for(int i=0; i<sizeX; i++) for(int j=0; j<sizeY; j++) { //búsqueda de casillas que contengan valores.
				// Paso 1: Se mueven las fichas en la dirección adecuada antes de sumar nada.
				if(getTab(i, j)!=0) {
					if(getTab(i+movx, j+movy)==0) { 
						setTab(i+movx, j+movy, getTab(i, j));
						setTab(i, j, 0);
					}
				}
			}
			
			//out.println(this);

			for(int i=0; i<sizeX; i++) for(int j=0; j<sizeY; j++) {
				if(getTab(i, j)!=0) { // como antes, se buscan casillas que contengan valores.
					if(getTab(i, j) == getTab(i+movx, j+movy) || getTab(i, j) + getTab(i+movx, j+movy) == 3) {
						setTab(i+movx, j+movy, getTab(i, j) + getTab(i+movx, j+movy));
						setTab(i, j, 0);	
					}
				}
			}
			
			//out.println(this);
		}
	}

	public int newRandom(int val) {
		Random w = new Random();
		return w.nextInt(val); //Número random entre el 0 y val
		//lo devuelve directamente
	}

	public void newSiguiente() {
		setSiguiente(newRandom(3)+1); //el siguiente debe de estar entre 1 y 3
	}

	@Override
	public String toString() {
		String salida = String.format(""); //TODO completar salida para que coincida con la demo
		return salida; //tiene que incluir "Siguiente ficha: " y "Puntos: "
		//imprimir los turnos???
	}
}