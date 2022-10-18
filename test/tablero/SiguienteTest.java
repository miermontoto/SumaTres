package tablero;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import game.SumaTres;
import obj.Settings;
import util.Crypto;

@DisplayName("Obtención de la posición de la ficha siguiente")
class SiguienteTest {

	SumaTres s;

	@BeforeEach
	void setUp() throws Exception {
		s = new SumaTres(new Settings(3, 3, true));
		for(int i=0; i<3; i++) for(int j=0; j<3; j++) s.setTab(i, j, 3);
	}

	@Test
	@DisplayName("cuando solo hay una posición válida")
	void onePositionTest() {
		int x = Crypto.newRandom(3);
		int y = Crypto.newRandom(3);
		s.setTab(x, y, 0);
		int[] z = s.getValidLocations();
		assertEquals(x, z[0]);
		assertEquals(y, z[1]);
	}

	@Test
	@DisplayName("cuando no hay posiciones válidas")
	void noPositionsTest() {
		int[] y = s.getValidLocations();
		assertEquals(null, y);
	}

}
