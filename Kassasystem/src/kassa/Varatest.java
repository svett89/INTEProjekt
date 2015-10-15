package kassa;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import org.junit.Test;

public class Varatest {
	
	private BigDecimal v�rde = new BigDecimal(22);
	private Currency valuta = Currency.getInstance("SEK");
	private Pengar pris = new Pengar(v�rde, valuta, RoundingMode.HALF_EVEN);
	
	@Test
	public void skapaOchTestaEnVara() {
		Vara v1 = new Vara("Salami", pris);
		assertTrue(v1.getNamn() == "Salami");
		assertTrue(v1.getPris().equals(pris));
	}
	
	/*@Test(expected = IllegalArgumentException.class)
	public void kastarExceptionN�rPrisF�rStor() {
		Vara v1 = new Vara("Br�d", 10000);
	}*/

	@Test(expected = IllegalArgumentException.class)
	public void kastarExceptionN�rPris�rMinus() {
		BigDecimal bd = new BigDecimal(-2);
		pris.setBelopp(bd);
		Vara v1 = new Vara("Br�d", pris);
	}

	@Test
	public void LikaHashCodeOchEquals() {
		BigDecimal bd = new BigDecimal(59.99);
		Vara v1 = new Vara("Ost", pris);
		Vara v2 = new Vara("Ost", pris);
		assertTrue(v1.hashCode() == v2.hashCode());
		assertTrue(v1.equals(v2));
	}
}
