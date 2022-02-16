package tablero;
import static org.junit.jupiter.api.Assertions.*;
import static java.lang.System.out;

import org.junit.jupiter.api.*;

import obj.Jugada;
import obj.Tablero;
import obj.Turno;

//@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
@DisplayName("Movimiento")
class MoverTest {

	private Tablero tabCuadrado, tabIrregular1, tabIrregular2;
	private Jugada x;
	
	@BeforeEach
	void setUp() throws Exception {
		tabCuadrado = new Tablero(5, 5);
		tabIrregular1 = new Tablero(4, 6);
		tabIrregular2 = new Tablero(6, 4);
		
		x = null;
	}
	
	void mover() {
		Turno.mover(x, tabCuadrado);
		Turno.mover(x, tabIrregular1);
		Turno.mover(x, tabIrregular2);
	}
	
	void test() {
		out.println(tabIrregular1.formattedToString());
		out.println(tabIrregular2.formattedToString());
	}
	
	@Nested
	@DisplayName("en el modo clásico")
	class ClassicTest {
		@Test
		@DisplayName("hacia arriba")
		void testMoverArribaPositivo() {
			tabCuadrado.setTab(1, 0, 1);
			tabIrregular1.setTab(1, 0, 1);
			tabIrregular2.setTab(1, 0, 1);
			
			tabCuadrado.setTab(4, 4, 3);
			tabIrregular1.setTab(3, 5, 3);
			tabIrregular2.setTab(5, 3, 3);
			
			x = new Jugada('w');
			mover();
			
			assertAll("básico", 
				() -> assertEquals(1, tabCuadrado.getTab(0, 0)),
				() -> assertEquals(1, tabIrregular1.getTab(0, 0)),
				() -> assertEquals(1, tabIrregular2.getTab(0, 0)),
				
				() -> assertAll("extendido",
						() -> assertEquals(3, tabCuadrado.getTab(0, 4)),
						() -> assertEquals(3, tabIrregular1.getTab(0, 5)),
						() -> assertEquals(3, tabIrregular2.getTab(0, 3))
					)
			);
		}
		
		@Test
		@DisplayName("hacia la izquierda")
		void testMoverIzquierdaPositivo() {
			tabCuadrado.setTab(0, 1, 1);
			tabIrregular1.setTab(0, 1, 1);
			tabIrregular2.setTab(0, 1, 1);
			
			tabCuadrado.setTab(4, 4, 3);
			tabIrregular1.setTab(3, 5, 3);
			tabIrregular2.setTab(5, 3, 3);
			
			x = new Jugada('a');
			mover();
			
			assertAll("básico",
				() -> assertEquals(1, tabCuadrado.getTab(0, 0)),
				() -> assertEquals(1, tabIrregular1.getTab(0, 0)),
				() -> assertEquals(1, tabIrregular2.getTab(0, 0)),
				
				() -> assertAll("extendido",
						() -> assertEquals(3, tabCuadrado.getTab(4, 0)),
						() -> assertEquals(3, tabIrregular1.getTab(3, 0)),
						() -> assertEquals(3, tabIrregular2.getTab(5, 0))
					)
			);
		}
		
		@Test
		@DisplayName("hacia la derecha")
		void testMoverDerechaPositivo() {
			tabCuadrado.setTab(3, 3, 1);
			tabIrregular1.setTab(2, 4, 1);
			tabIrregular2.setTab(4, 2, 1);
			
			tabCuadrado.setTab(0, 0, 3);
			tabIrregular1.setTab(0, 0, 3);
			tabIrregular2.setTab(0, 0, 3);
			
			x = new Jugada('d');
			mover();
			
			assertAll("básico", 
				() -> assertEquals(1, tabCuadrado.getTab(3, 4)),
				() -> assertEquals(1, tabIrregular1.getTab(2, 5)),
				() -> assertEquals(1, tabIrregular2.getTab(4, 3)),
				
				() -> assertAll("extendido",
						() -> assertEquals(3, tabCuadrado.getTab(0, 4)),
						() -> assertEquals(3, tabIrregular1.getTab(0, 5)),
						() -> assertEquals(3, tabIrregular2.getTab(0, 3))
					)
			);
		}
		
		@Test
		@DisplayName("hacia abajo")
		void testMoverAbajoPositivo() {
			tabCuadrado.setTab(3, 3, 1);
			tabIrregular1.setTab(2, 4, 1);
			tabIrregular2.setTab(4, 2, 1);
			
			tabCuadrado.setTab(0, 0, 3);
			tabIrregular1.setTab(0, 0, 3);
			tabIrregular2.setTab(0, 0, 3);
			
			x = new Jugada('s');
			mover();
			
			assertAll("básico", 
				() -> assertEquals(1, tabCuadrado.getTab(4, 3)),
				() -> assertEquals(1, tabIrregular1.getTab(3, 4)),
				() -> assertEquals(1, tabIrregular2.getTab(5, 2)),
				
				() -> assertAll("extendido", 
					() -> assertEquals(3, tabCuadrado.getTab(4, 0)),
					() -> assertEquals(3, tabIrregular1.getTab(3, 0)),
					() -> assertEquals(3, tabIrregular2.getTab(5, 0))
				)
			);
		}
	}
	
	@Nested
	@DisplayName("en el modo experimental")
	class ExperimentalTest {
		
		@Test
		@DisplayName("hacia el noroeste")
		void testMoverNWPositivo() {
			
			tabCuadrado.setTab(1, 2, 2);
			tabIrregular1.setTab(1, 2, 2);
			tabIrregular2.setTab(1, 2, 2);
			
			tabCuadrado.setTab(4, 4, 3);
			tabIrregular1.setTab(3, 5, 3);
			tabIrregular2.setTab(5, 3, 3);
			
			x = new Jugada('q');
			mover();
			
			assertAll("extendido", 
				() -> assertEquals(3, tabCuadrado.getTab(0, 0)),
				() -> assertEquals(3, tabIrregular1.getTab(0, 2)),
				() -> assertEquals(3, tabIrregular2.getTab(2, 0)),
				
				() -> assertAll("lateral", 
						() -> assertEquals(2, tabCuadrado.getTab(0, 1)),
						() -> assertEquals(2, tabIrregular1.getTab(0, 1)),
						() -> assertEquals(2, tabIrregular2.getTab(0, 1))
				)
			);
			
		}
		
		@Test
		@DisplayName("hacia el noreste")
		void testMoverNEPositivo() {
			
			tabCuadrado.setTab(4, 0, 3);
			tabIrregular1.setTab(3, 0, 3);
			tabIrregular2.setTab(5, 0, 3);
			
			tabCuadrado.setTab(1, 0, 2);
			tabIrregular1.setTab(1, 0, 2);
			tabIrregular2.setTab(1, 0, 2);
			
			x = new Jugada('e');
			mover();
			
			assertAll("extendido",
					() -> assertEquals(3, tabCuadrado.getTab(0, 4)),
					() -> assertEquals(3, tabIrregular1.getTab(0, 3)),
					() -> assertEquals(3, tabIrregular2.getTab(2, 3)),
					
					() -> assertAll("lateral", 
							() -> assertEquals(2, tabCuadrado.getTab(0, 1)),
							() -> assertEquals(2, tabIrregular1.getTab(0, 1)),
							() -> assertEquals(2, tabIrregular2.getTab(0, 1))
					)
			);
		}
		
		@Test
		@DisplayName("hacia el suroeste")
		void testMoverSWPositivo() {
			
			tabCuadrado.setTab(0, 4, 3);
			tabIrregular1.setTab(0, 5, 3);
			tabIrregular2.setTab(0, 3, 3);
			
			tabCuadrado.setTab(3, 4, 2);
			tabIrregular1.setTab(2, 4, 2);
			tabIrregular2.setTab(4, 3, 2);
			
			
			x = new Jugada('z');
			mover();
			
			assertAll("extendido",
					() -> assertEquals(3, tabCuadrado.getTab(4, 0)),
					() -> assertEquals(3, tabIrregular1.getTab(3, 2)),
					() -> assertEquals(3, tabIrregular2.getTab(3, 0)),
					
					() -> assertAll("lateral", 
							() -> assertEquals(2, tabCuadrado.getTab(4, 3)),
							() -> assertEquals(2, tabIrregular1.getTab(3, 3)),
							() -> assertEquals(2, tabIrregular2.getTab(5, 2))
					)
			);
		}
		
		@Test
		@DisplayName("hacia el sureste")
		void testMoverSEPositivo() {
			
			tabCuadrado.setTab(0, 0, 3);
			tabIrregular1.setTab(0, 0, 3);
			tabIrregular2.setTab(0, 0, 3);
			
			tabCuadrado.setTab(3, 0, 2);
			tabIrregular1.setTab(2, 0, 2);
			tabIrregular2.setTab(4, 0, 2);
			
			x = new Jugada('c');
			mover();
			
			assertAll("extendido",
					() -> assertEquals(3, tabCuadrado.getTab(4, 4)),
					() -> assertEquals(3, tabIrregular1.getTab(3, 3)),
					() -> assertEquals(3, tabIrregular2.getTab(3, 3)),
					
					() -> assertAll("lateral", 
							() -> assertEquals(2, tabCuadrado.getTab(4, 1))
					)
			);
		}
	}

}
