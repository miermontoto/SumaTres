package tablero;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import game.SumaTres;
import obj.Jugada;
import obj.Tablero;
import obj.Turno;

@DisplayName("Suma")
class SumarTest {
	private SumaTres s = new SumaTres(3, 3, 0);

	@BeforeEach
	void setUp() throws Exception {
		s.setTablero(new Tablero(3, 3));
		for(int i=0; i<3; i++) for(int j=0; j<3; j++) s.setTab(i, j, 2);
		s.setTab(1, 1, 1);
	}

	@Nested
	@DisplayName("en el modo clásico")
	class ClassicTest {
		
		@Test
		@DisplayName("hacia arriba")
		void sumarArribaPositivoTest() {
			Turno.sumar(new Jugada('w'), s);
			assertEquals(3, s.getTab(0, 1));
		}
		
		@Test
		@DisplayName("hacia abajo")
		void sumarAbajoPositivoTest() {
			Turno.sumar(new Jugada('s'), s);
			assertEquals(3, s.getTab(2, 1));
		}
		
		@Test
		@DisplayName("hacia la izquierda")
		void sumarIzquierdaPositivoTest() {
			Turno.sumar(new Jugada('a'), s);
			assertEquals(3, s.getTab(1, 0));
		}
		
		@Test
		@DisplayName("hacia la derecha")
		void sumarDerechaPositivoTest() {
			Turno.sumar(new Jugada('d'), s);
			assertEquals(3, s.getTab(1, 2));
		}
	}
	
	@Nested
	@DisplayName("en el modo experimental")
	class ExperimentalTest {
		
		@Test
		@DisplayName("hacia el noroeste")
		void sumarNWPositivoTest() {
			Turno.sumar(new Jugada('q'), s);
			assertEquals(3, s.getTab(0, 0));
		}
		
		@Test
		@DisplayName("hacia el noreste")
		void sumarNEPositivoTest() {
			Turno.sumar(new Jugada('e'), s);
			assertEquals(3, s.getTab(0, 2));
		}
		
		@Test
		@DisplayName("hacia el suroeste")
		void sumarSWPositivoTest() {
			Turno.sumar(new Jugada('z'), s);
			assertEquals(3, s.getTab(2, 0));
		}
		
		@Test
		@DisplayName("hacia el sureste")
		void sumarSEPositivoTest() {
			Turno.sumar(new Jugada('c'), s);
			assertEquals(3, s.getTab(2, 2));
		}
		
	}
	
	@Test
	@DisplayName("se suman piezas iguales")
	void PiezasIgualesTest() {
		s.setTab(1, 1, 48);
		s.setTab(0, 1, 48);
		s.setTab(0, 0, 3);
		s.setTab(1, 0, 3);
		Turno.sumar(new Jugada('w'), s);
		assertEquals(96, s.getTab(0, 1));
		assertEquals(6, s.getTab(0, 0));
	}
	
	@Test
	@DisplayName("no se suman piezas desiguales")
	void PiezasDesigualesTest() {
		s.setTab(1, 1, 6);
		s.setTab(0, 1, 12);
		s.setTab(0, 0, 3);
		s.setTab(1, 0, 2);
		Turno.sumar(new Jugada('w'), s);
		assertEquals(12, s.getTab(0, 1));
		assertEquals(3, s.getTab(0, 0));
	}

}
