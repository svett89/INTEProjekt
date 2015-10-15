package kassa;

import static org.junit.Assert.*;
import org.junit.Test;



public class Varatest {
	
	@Test
	public void skapaEnVara() {
		Vara v1 = new Vara("Salami", 22);
		assertTrue(v1.getName() == "Salami");
		assertTrue(v1.getPris() == 22);
	}
	
	@Test
	public void LikaHashCodeOchEquals() {
		Vara v1 = new Vara("Ost", 50);
		Vara v2 = new Vara("Ost", 50);
		assertTrue(v1.hashCode() == v2.hashCode());
		assertTrue(v1.equals(v2));
	}
	
	
}
