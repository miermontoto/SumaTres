package tablero;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import game.SumaTres;
import obj.Jugada;
import obj.Settings;
import obj.Tablero;
import obj.Turno;

@DisplayName("Suma")
class SumarTest {
	private SumaTres s = new SumaTres(new Settings(3, 3, false));

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
			new Turno(s, new Jugada('w')).sumar();
			assertEquals(3, s.getTab(0, 1));
		}
		
		@Test
		@DisplayName("hacia abajo")
		void sumarAbajoPositivoTest() {
			new Turno(s, new Jugada('s')).sumar();
			assertEquals(3, s.getTab(2, 1));
		}
		
		@Test
		@DisplayName("hacia la izquierda")
		void sumarIzquierdaPositivoTest() {
			new Turno(s, new Jugada('a')).sumar();
			assertEquals(3, s.getTab(1, 0));
		}
		
		@Test
		@DisplayName("hacia la derecha")
		void sumarDerechaPositivoTest() {
			new Turno(s, new Jugada('d')).sumar();
			assertEquals(3, s.getTab(1, 2));
		}
	}
	
	@Nested
	@DisplayName("en el modo experimental")
	class ExperimentalTest {
		
		@Test
		@DisplayName("hacia el noroeste")
		void sumarNWPositivoTest() {
			new Turno(s, new Jugada('e')).sumar();
			assertEquals(3, s.getTab(0, 0));
		}
		
		@Test
		@DisplayName("hacia el noreste")
		void sumarNEPositivoTest() {
			new Turno(s, new Jugada('q')).sumar();
			assertEquals(3, s.getTab(0, 2));
		}
		
		@Test
		@DisplayName("hacia el suroeste")
		void sumarSWPositivoTest() {
			new Turno(s, new Jugada('z')).sumar();
			assertEquals(3, s.getTab(2, 0));
		}
		
		@Test
		@DisplayName("hacia el sureste")
		void sumarSEPositivoTest() {
			new Turno(s, new Jugada('c')).sumar();
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
		new Turno(s, new Jugada('w')).sumar();
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
		new Turno(s, new Jugada('w')).sumar();
		assertEquals(12, s.getTab(0, 1));
		assertEquals(3, s.getTab(0, 0));
	}

}
