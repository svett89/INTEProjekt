package kassa;

import org.junit.Test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Rabattest {
	
	Kvitto k1 = new Kvitto();
	Pengar p1 = new Pengar(new BigDecimal(100));
	Pengar p2 = new Pengar(new BigDecimal(155));
	Pengar p3 = new Pengar(new BigDecimal(288));
	Vara v1 = new Vara("cykel", p1);
	Vara v2 = new Vara("bil", p2);
	Vara v3 = new Vara("stol", p3);
	
	@Test
	public void testaOmVarorKanLäggasTill(){
		Map<Integer, BigDecimal> h1 = new HashMap<Integer, BigDecimal>();
		//new BigDecimal(100) innebär att om man köper 3 cyklar får man en av dem gratis
		BigDecimal p4 = new BigDecimal(100);
		h1.put(3, p4);
		Rabatt.addVara(v1, h1);
		assertEquals(Rabatt.getRabatter().get(v1).get(3), p4);
	}
	

}
