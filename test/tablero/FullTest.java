package tablero;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import game.SumaTres;
import obj.Settings;
import util.Random;

@DisplayName("Detección de tablero lleno")
public class FullTest {
	
	SumaTres s;

	@BeforeEach
	public void setUp() {
		s = new SumaTres(new Settings(3, 3, false));
	}

	@Test
	@DisplayName("positiva")
	public void llenoTest() {
		for(int i=0; i<3; i++) for(int j=0; j<3; j++) s.setTab(i, j, 3);
		assertEquals(true, s.getTablero().isFull());
	}
	
	@Test
	@DisplayName("negativa")
	public void noLlenoTest() {
		assertEquals(false, s.getTablero().isFull());
		setUp();
		for(int i=0; i<3; i++) for(int j=0; j<3; j++) s.setTab(i, j, 3);
		int x = Random.newRandom(3);
		int y = Random.newRandom(3);
		s.setTab(x, y, 0);
		assertEquals(false, s.getTablero().isFull());
	}

}
