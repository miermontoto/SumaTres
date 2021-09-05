package tablero;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import game.SumaTres;

@DisplayName("Obtención de la posición de la ficha siguiente")
class SiguienteTest {
	
	SumaTres s;

	@BeforeEach
	void setUp() throws Exception {
		s = new SumaTres(3, 3, 1);
		for(int i=0; i<3; i++) for(int j=0; j<3; j++) s.setTab(i, j, 3);
	}

	@Test
	@DisplayName("cuando solo hay una posición válida")
	void onePositionTest() {
		int x = SumaTres.newRandom(3);
		int y = SumaTres.newRandom(3);
		s.setTab(x, y, 0);
		int[] z = s.validLocation();
		assertEquals(x, z[0]);
		assertEquals(y, z[1]);
	}
	
	@Test
	@DisplayName("cuando no hay posiciones válidas")
	void noPositionsTest() {
		int[] y = s.validLocation();
		assertEquals(null, y);
	}

}
