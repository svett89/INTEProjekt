package kassa;

import static org.junit.Assert.*;
import org.junit.Test;

public class Varatest {

	@Test
	public void skapaOchTestaEnVara() {
		Vara v1 = new Vara("Salami", 22);
		assertTrue(v1.getNamn() == "Salami");
		assertTrue(v1.getPris() == 22);
	}

	@Test(expected = IllegalArgumentException.class)
	public void kastarExceptionN�rPrisF�rStor() {
		Vara v1 = new Vara("Br�d", 10000);
	}

	@Test(expected = IllegalArgumentException.class)
	public void kastarExceptionN�rPris�rMinus() {
		Vara v1 = new Vara("Br�d", -2);
	}

	@Test
	public void LikaHashCodeOchEquals() {
		Vara v1 = new Vara("Ost", 59.99);
		Vara v2 = new Vara("Ost", 59.99);
		assertTrue(v1.hashCode() == v2.hashCode());
		assertTrue(v1.equals(v2));
	}
}
