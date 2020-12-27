//se utiliza %n en vez de \n por recomendación de Oracle: https://docs.oracle.com/javase/tutorial/java/data/numberformat.html
import static java.lang.System.out; //se importa de esta manera para acortar durante el resto del programa
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
		
		while (Juego.ableToMove()) {
			char jugada;
			out.print("Dirección: Up (W)/Down (S)/Left (A)/Right (D): ");
			jugada = scr.next().toLowerCase().charAt(0);
			Juego.Jugada(jugada);
		}
		
		scr.close();
		out.printf("%nSe ha terminado la partida.%nPuntuación final: %d%nTurnos: %d%n", Juego.getPuntos(), Juego.getTurnos());
		for(int i=0; i<Juego.ran.length; i++) {out.printf("%d's: %d\t", Juego.ran[i]+1, Juego.ran[i]);}

	}

}
