package tablero;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import game.SumaTres;

@DisplayName("Detección de tablero lleno")
class FullTest {
	
	SumaTres s;

	@BeforeEach
	void setUp() {
		s = new SumaTres(3, 3, 0);
	}

	@Test
	@DisplayName("positiva")
	void llenoTest() {
		for(int i=0; i<3; i++) for(int j=0; j<3; j++) s.setTab(i, j, 3);
		assertEquals(true, s.getTablero().isFull());
	}
	
	@Test
	@DisplayName("negativa")
	void noLlenoTest() {
		assertEquals(false, s.getTablero().isFull());
		setUp();
		for(int i=0; i<3; i++) for(int j=0; j<3; j++) s.setTab(i, j, 3);
		int x = SumaTres.newRandom(3);
		int y = SumaTres.newRandom(3);
		s.setTab(x, y, 0);
		assertEquals(false, s.getTablero().isFull());
	}

}
