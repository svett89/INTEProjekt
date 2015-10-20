package kassa;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;
public class Rabattest {
	
	Kvitto k1 = new Kvitto();
	Pengar p1 = new Pengar(100);
	Pengar p2 = new Pengar(150);
	Pengar p3 = new Pengar(288);
	Skor s1 = new Skor("Herrskor", new Märke("minsko"), 1, p1);
	Mössa m1 = new Mössa("Facemask", new Märke("Raichu"), 1, p2);
	Strumpor st2 = new Strumpor("Ski-sock", new Märke("snorlax"), 2,  p3);
	
	@Test
	public void testaOmVarorKanLäggasTill(){
		HashMap<Integer, Pengar> h1 = new HashMap<Integer, Pengar>();
		//new BigDecimal(100) innebär att om man köper 3 cyklar får man en av dem gratis
		Pengar p4 = new Pengar(100);
		h1.put(3, p4);
		Rabatt.addRabatt(s1, h1);
		assertEquals(Rabatt.getRabatter(s1, 3), p4);
	}
	@Test
	public void testaTaInKvitto(){
		k1.läggTillVara(m1, 5);
		HashMap<Integer, Pengar> h2 = new HashMap<Integer, Pengar>();
		h2.put(3, new Pengar(200));
		Rabatt.addRabatt(m1, h2);
		assertEquals(new Pengar(550), k1.getPris());
	}
	

}
