package tablero;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.*;

import game.SumaTres;
import obj.Turno;

/**
 * Test que comprueba la correcta detección del final de partida
 * en los dos modos disponibles utilizando diversos casos límite
 * que ponen a prueba la lógica y el correcto funcionamiento de
 * {@link #Turno.ableToMove(SumaTres)}.
 * @author under
 *
 */
@DisplayName("Detección de final de partida")
class FinalTest {

	/**
	 * Test que comprueba la correcta detección del final de partida en
	 * modo clásico.
	 */
	@Test
	@DisplayName("en partida clásica")
	void ClassicTest() {
		SumaTres s1 = new SumaTres(3, 3, 0);
		SumaTres s2 = new SumaTres(3, 3, 0);
		SumaTres s3 = new SumaTres(3, 3, 0);
		SumaTres s4 = new SumaTres(3, 3, 0);
		SumaTres s5 = new SumaTres(3, 3, 0);
		
		for (int i=0; i<3; i++) for(int j=0; j<3; j++) s1.setTab(i, j, 3);
		s1.setTab(0, 0, 2);
		s1.setTab(2, 0, 2);
		s1.setTab(0, 2, 2);
		s1.setTab(2, 2, 2);
		s1.setTab(1, 1, 2);
		
		for (int i=0; i<3; i++) for(int j=0; j<3; j++) s2.setTab(i, j, 3);
		
		/*
		 * Por algún motivo, el primer set de fichas genera 6 fichas.
		 * No tengo ni idea de por qué. No tengo tiempo de adivinar por qué.
		 * TODO: arreglar esto.
		 */
		s4.generarSetFichas();
		s4.generarSetFichas();

		
		for (int i=0; i<3; i++) for(int j=0; j<3; j++) s5.setTab(i, j, 2);
		
		assertEquals(false, Turno.ableToMove(s1));
		assertEquals(true, Turno.ableToMove(s2));
		assertEquals(true, Turno.ableToMove(s3));
		assertEquals(true, Turno.ableToMove(s4));
		assertEquals(false, Turno.ableToMove(s5));
	}
	
	/**
	 * Test que comprueba la correcta detección del final de partida,
	 * esta vez en el modo experimental. Esto significa que hay que
	 * tener en cuenta los movimientos en diagonal.
	 */
	@Test
	@DisplayName("en partida experimental")
	void ExperimentalTest() {
		// Se establecen seis escenarios diferentes para evaluar.
		SumaTres s1 = new SumaTres(3, 3, 1);
		SumaTres s2 = new SumaTres(3, 3, 1);
		SumaTres s3 = new SumaTres(3, 3, 1);
		SumaTres s4 = new SumaTres(3, 3, 1);
		SumaTres s5 = new SumaTres(3, 3, 1);
		SumaTres s6 = new SumaTres(3, 3, 1);
		
		/*
		 * En el primer tablero, se rellena el tablero con un patrón repetitivo
		 * de piezas con valores 3, 2, 3, 2...
		 */
		for (int i=0; i<3; i++) for(int j=0; j<3; j++) s1.setTab(i, j, 3);
		s1.setTab(0, 1, 2);
		s1.setTab(1, 0, 2);
		s1.setTab(2, 1, 2);
		s1.setTab(1, 2, 2);
		
		/*
		 * El segundo tablero se rellena simplemente de piezas de valor 3.
		 */
		for (int i=0; i<3; i++) for(int j=0; j<3; j++) s2.setTab(i, j, 3);
		
		// El tercer tablero está completamente vacío.
		
		/*
		 * El cuarto tablero tiene una posición que debería suponer el final de la partida.
		 */
		s4.setTab(0, 1, 6);
		s4.setTab(1, 0, 3);
		s4.setTab(1, 2, 3);
		s4.setTab(2, 1, 6);
		s4.setTab(0, 0, 2);
		s4.setTab(2, 0, 2);
		s4.setTab(0, 2, 2);
		s4.setTab(2, 2, 2);
		s4.setTab(1, 1, 48);
		
		/*
		 * El quinto tablero tiene un patrón similar al de una partida recién empezada.
		 */
		s5.generarSetFichas();
		
		/*
		 * El sexto tablero se rellena de pieas de valor 2.
		 */
		for (int i=0; i<3; i++) for(int j=0; j<3; j++) s6.setTab(i, j, 2);
		
		// Se comprueba la veracidad del método.
		assertEquals(true, Turno.ableToMove(s1));
		assertEquals(true, Turno.ableToMove(s2));
		assertEquals(true, Turno.ableToMove(s3));
		assertEquals(false, Turno.ableToMove(s4));
		assertEquals(true, Turno.ableToMove(s5));
		assertEquals(false, Turno.ableToMove(s6));
	}
	

}
