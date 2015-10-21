package kassa;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;
public class Rabattest {
	
	Kvitto k1 = new Kvitto();
	Pengar p1 = new Pengar(100);
	Pengar p2 = new Pengar(150);
	Pengar p3 = new Pengar(288);
	Vara v1 = new Vara("cykel", p1);
	Vara v2 = new Vara("bil", p2);
	Vara v3 = new Vara("stol", p3);
	
	@Test
	public void testaOmVarorKanLäggasTill(){
		HashMap<Integer, Pengar> h1 = new HashMap<Integer, Pengar>();
		//new BigDecimal(100) innebär att om man köper 3 cyklar får man en av dem gratis
		Pengar p4 = new Pengar(100);
		h1.put(3, p4);
		Rabatt.addRabatt(v1, h1);
		assertEquals(Rabatt.getRabatter(v1, 3), p4);
	}
	//OBS! Kommer inte fungera nu!
	@Test
	public void testaTaInKvitto(){
		k1.läggTillVara(v2, 5);
		HashMap<Integer, Pengar> h2 = new HashMap<Integer, Pengar>();
		h2.put(3, new Pengar(200));
		Rabatt.addRabatt(v2, h2);
		assertEquals(new Pengar(550), k1.getPrisUtanRabatt());
	}
	

}
