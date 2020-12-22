import static java.lang.System.out;
import java.util.Random;
public class SumaTres {

	private int sizeX;
	private int sizeY;
	private int turnos = 0;
	private int puntuación = 0;
	private int siguiente;

	public SumaTres() {
		this(5, 5);
	}

	public SumaTres(int x, int y) {
		this.sizeX = x;
		this.sizeY = y;
	}

	private int[][] tablero = new int[sizeX][sizeY];
	private int puntos = 0;

	// --- sets y gets --- //
	public int getPuntos() {return this.puntos;}
	public void setPuntos(int puntos) {this.puntos = puntos;}

	public int getTab(int x, int y) {return this.tablero[x][y];}
	public void setTab(int x, int y, int nv) {this.tablero[x][y] = nv;}
	
	public int getPuntuación() {return this.puntuación;}
	public void setPuntuación(int puntuación) {this.puntuación = puntuación;}

	public int getSiguiente() {return this.siguiente;}
	public void setSiguiente(int siguiente) {this.siguiente = siguiente;}
	// --- sets y gets --- //

	public void Jugada(char c) {
		switch(c) {
		case 'a': //ARRIBA
			for(int i=0; i<sizeX; i++) for(int j=1; j<sizeY; j++) { //búsqueda de casillas que contengan valores.
				if(getTab(i, j)!=0) {
					if(getTab(i, j-1)==0) { 
						setTab(i, j-1, getTab(i, j));
						setTab(i, j, 0);
						// si la casilla de encima está vacía, simplemente se mueve.
					} else {
						if(getTab(i, j) == getTab(i, j-1) || getTab(i, j) + getTab(i, j-1) == 3) {
							setTab(i, j-1, getTab(i, j) + getTab(i, j-1));
							setTab(i, j, 0);
							//si la casilla de encima es igual o suman 3, se suman
							//TODO comprobar casos de varias casillas iguales juntas
						}
					}
				}
			}
			turnos++;
			break;

		case 'b': //ABAJO

			turnos++;
			break;

		case 'i': //IZQUIERDA

			turnos++;
			break;

		case 'd': //DERECHA

			turnos++;
			break;
		default: //otro valor (inválido)
			out.println("Jugada inválida.");
			break;
		}
	}

	public int newRandom(int val) {
		Random w = new Random();
		int num = w.nextInt(val); //Número random entre el 0 y val
		return num;
	}
	
	public void newSiguiente() {
		setSiguiente(newRandom(3)+1);
	}

	@Override
	public String toString() {
		String salida = String.format(""); //TODO completar salida para que coincida con la demo
		return salida; //tiene que incluir "Siguiente ficha: " y "Puntos: "



	}
}