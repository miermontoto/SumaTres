import static java.lang.System.out;
import java.util.Random;
public class SumaTres {
	//el tamaño del tablero se establece en los constructores
	private int sizeX;
	private int sizeY;
	private int turnos = 0; //no es necesario
	private int puntuación = 0; //la partida se comienza con 0 puntos
	private int siguiente = 1; //la primera "siguiente" es 1.


	public SumaTres() { //el constructor por defecto establece 5x5 (como en las demos) para el tablero
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

		if(movx != 0 && movy != 0) { //si no se desplaza el índice significa que no es una jugada válida.
			for(int i=0; i<sizeX; i++) for(int j=0; j<sizeY; j++) { //búsqueda de casillas que contengan valores.
				/*if(getTab(i, j)!=0) {
					if(getTab(i+movx, j+movy)==0) { 
						setTab(i+movx, j+movy, getTab(i, j));
						setTab(i, j, 0);
						
					} else {
						if(getTab(i, j) == getTab(i+movx, j+movy) || getTab(i, j) + getTab(i+movx, j+movy) == 3) {
							setTab(i+movx, j+movy, getTab(i, j) + getTab(i+movx, j+movy));
							setTab(i, j, 0);
							
						}
					}*/
				
				//TODO probar que esto funciona
				//Documentación: elimina todos los if y else en favor de reducción de código.
				//Incluye las comprobaciones del movimiento originales:
				// si la casilla destino está vacía, simplemente se mueve.
				// si la casilla destino es igual o suman 3 (1+2 || 2+1), se suman.
				// *TODO comprobar casos de varias casillas iguales juntas
				//*puede que favorecer la reduccción frente a la estética y simplicidad del código no merezca la pena.
				
				
				setTab(i+movx, j+movy, 
					(getTab(i+movx, j+movy)==0)? getTab(i, j) :
						(getTab(i, j) == getTab(i+movx, j+movy) || getTab(i, j) + getTab(i+movx, j+movy) == 3)?
								getTab(i, j) + getTab(i+movx, j+movy) : getTab(i+movx, j+movy));
				setTab(i, j,
						(getTab(i+movx, j+movy)==0) || (getTab(i, j) == getTab(i+movx, j+movy) || getTab(i, j) + getTab(i+movx, j+movy) == 3) ?
								0 : getTab(i, j));

			}
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
	}
}