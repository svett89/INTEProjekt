package varatester;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import kassa.Pengar;
import vara.*;
import org.junit.Test;

public class Varatest {
	
	private BigDecimal värde = new BigDecimal(22);
	private Currency valuta = Currency.getInstance("SEK");
	private Pengar pris = new Pengar(värde, valuta, RoundingMode.HALF_EVEN);
	
	@Test
	public void skapaOchTestaJacka() {
		Jacka j1 = new Jacka("Ski-jacket", new Märke("babidas"), pris);
		assertTrue(j1.getNamn() == "Ski-jacket");
		assertTrue(j1.getMärke().toString() == "babidas");
		assertTrue(j1.getPris().equals(pris));
	}
	
	@Test
	public void skapaOchTestaMössa() {
		Mössa m1 = new Mössa("vintermössa", new Märke("babidas"), pris);
		assertTrue(m1.getNamn() == "vintermössa");
		assertTrue(m1.getMärke().toString() == "babidas");
		assertTrue(m1.getPris().equals(pris));
	}
	
	@Test
	public void skapaOchTestaSkor() {
		Skor s1 = new Skor("Boots", new Märke("Najk"), pris);
		assertTrue(s1.getNamn() == "Boots");
		assertTrue(s1.getMärke().toString() == "Najk");
		assertTrue(s1.getPris().equals(pris));
	}
	
	@Test
	public void skapaOchTestaStrumpor() {
		Strumpor str1 = new Strumpor("Tjockstrumpor", new Märke("Faddium"), pris);
		assertTrue(str1.getNamn().equals("Tjockstrumpor"));
		assertTrue(str1.getMärke().toString() == "Faddium");
		assertTrue(str1.getPris().equals(pris));
	}
	
	/*@Test(expected = IllegalArgumentException.class)
	public void kastarExceptionNärPrisFörStor() {
		Vara v1 = new Vara("Bröd", 10000);
	}*/

	@Test(expected = IllegalArgumentException.class)
	public void kastarExceptionNärPrisÄrMinus() {
		BigDecimal bd = new BigDecimal(-2);
		pris.setBelopp(bd);
		Vara v1 = new Vara("Strumpor", new Märke("inter-gaming"), pris);
	}

	@Test
	public void likaHashCodeOchEquals() {
		Vara v1 = new Vara("Jeans", new Märke("levaj"), pris);
		Vara v2 = new Vara("Jeans", new Märke("levaj"), pris);
		assertTrue(v1.hashCode() == v2.hashCode());
		assertTrue(v1.equals(v2));
	}
	

}
