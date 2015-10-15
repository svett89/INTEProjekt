package kassa;

import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigDecimal;

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
		Rabatt.addVara(v1);
		assertEquals(Rabatt);
	}
	

}
