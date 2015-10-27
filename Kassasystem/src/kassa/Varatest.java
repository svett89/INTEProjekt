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
		Jacka j1 = new Jacka("Ski-jacket", new M�rke("babidas"), pris);
		assertTrue(j1.getNamn() == "Ski-jacket");
		assertTrue(j1.getPris().equals(pris));
	}
	
	/*@Test(expected = IllegalArgumentException.class)
	public void kastarExceptionN�rPrisF�rStor() {
		Vara v1 = new Vara("Br�d", 10000);
	}*/

	@Test(expected = IllegalArgumentException.class)
	public void kastarExceptionN�rPris�rMinus() {
		BigDecimal bd = new BigDecimal(-2);
		pris.setBelopp(bd);
		Vara v1 = new Vara("Strumpor", new M�rke("inter-gaming"), pris);
	}

	@Test
	public void likaHashCodeOchEquals() {
		Vara v1 = new Vara("Jeans", new M�rke("levaj"), pris);
		Vara v2 = new Vara("Jeans", new M�rke("levaj"), pris);
		assertTrue(v1.hashCode() == v2.hashCode());
		assertTrue(v1.equals(v2));
	}
}
