import static java.lang.System.out;
import java.util.Scanner;

public class SumaTresTexto {

	public static void main(String[] args) {
		Scanner scr = new Scanner(System.in);
		SumaTres Juego;
		int sizex;
		int sizey;
		
		out.print("Introduzca el tamaño del tablero con el que se desea jugar: ");
		sizex = scr.nextInt();
		sizey = scr.nextInt();
		Juego = new SumaTres(sizex, sizey);
		
		out.print(Juego);
		
		while (!(Juego.checkEnd())) {
			char jugada;
			out.print("Dirección Arriba (W)/Abajo (S)/Izquierda (A)/Derecha (D): ");
			jugada = scr.next().toLowerCase().charAt(0);
			Juego.Jugada(jugada);
		}
		
		out.printf("%nSe ha terminado la partida.%nPuntuación final: %d%nTurnos: %d", Juego.getPuntos(), Juego.getTurnos());
		scr.close();

	}

}
