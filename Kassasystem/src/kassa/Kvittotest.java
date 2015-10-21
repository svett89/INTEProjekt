package kassa;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
import java.math.*;

public class Kvittotest {
	
	//Tomt kvitto som tester kan jobba p�
	private Kvitto k = new Kvitto();
	
	//Hj�lpmetod f�r att skapa vara
	private Vara skapaVara(String beskrivning, BigDecimal pris){
		M�rke m = new M�rke("ABC");
		Currency valuta = Currency.getInstance("SEK");
		Pengar varuPris = new Pengar(pris, valuta, RoundingMode.HALF_UP);
		return new Vara(beskrivning, m, varuPris); 
	}
	
	//Hj�lpmetod f�r att skapa BigDecimal
	private BigDecimal bigDec(String v�rde){
		return new BigDecimal(v�rde);
	}
	
	@Test
	public void testaL�ggTillVarorIKonstruktor(){
		Vara v1 = skapaVara("V1", bigDec("100"));
		Vara v2 = skapaVara("V2", bigDec("200"));
		Kvitto k = new Kvitto(v1, v2);
		assertTrue(k.varaFinns(v1));
		assertTrue(k.varaFinns(v2));
		assertEquals(2, k.getTotalM�ngdVaror());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaL�ggTillNullIKonstruktor(){
		Vara v1 = null;
		Vara v2 = null;
		Kvitto k = new Kvitto(v1, v2);
	}
	@Test
	public void testaL�ggTillVaraIMetod(){
		Vara v = skapaVara("V", bigDec("100"));
		k.l�ggTillVara(v, 1);
		assertTrue(k.varaFinns(v));
		for(int i = 0; i<7; i++){
			k.l�ggTillVara(skapaVara("V"+i, bigDec(""+i)));
		}
		assertEquals(8, k.getTotalM�ngdVaror());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaL�ggTillNullIMetod(){
		Vara v = null;
		k.l�ggTillVara(v);
	}
	
	@Test 
	public void testaL�ggTillVarorIMetodMedArray(){
		Vara v1 = skapaVara("V1", bigDec("100"));
		Vara v2 = skapaVara("V2", bigDec("200"));
		Vara v3 = skapaVara("V3", bigDec("300"));
		Vara[] varor = {v1, v2, v3};
		assertEquals(0, k.getTotalM�ngdVaror());
		k.l�ggTillVaror(varor);
		assertEquals(3, k.getTotalM�ngdVaror());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaL�ggTillTomArray(){
		Vara[] varor = new Vara[5];
		k.l�ggTillVaror(varor);
	}
	
	@Test
	public void testaL�ggTillFlerVarorFr�nLista(){
		ArrayList<Vara> varuSamling = new ArrayList<Vara>();
		//L�gg till 6 varor
		for(int i = 0; i<6; i++){
			varuSamling.add(skapaVara("V"+i, bigDec(""+i)));
		}
		//L�gg till samlingen (ArrayList i detta fall) och j�mf�r
		k.l�ggTillVarorFr�nSamling(varuSamling);
		assertEquals(6, k.getTotalM�ngdVaror());
		Set<Vara> varuSet = new HashSet<Vara>(varuSamling);
		assertEquals(k.getVaruSet(), varuSet);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaL�ggTillFr�nListaN�rLista�rNull(){
		ArrayList<Vara> varuSamling = null;
		k.l�ggTillVarorFr�nSamling(varuSamling);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaL�ggTillFr�nListaMedNullV�rde(){
		ArrayList<Vara> varuSamling = new ArrayList<Vara>();
		Vara v = skapaVara("V", bigDec("2765"));
		varuSamling.add(v);
		varuSamling.add(null);
		k.l�ggTillVarorFr�nSamling(varuSamling);
	}
	
	@Test
	public void testaT�mKvitto(){
		for(int i = 0; i<5; i++){
			k.l�ggTillVara(skapaVara("V"+i, bigDec(""+i)));
		}
		assertEquals(5, k.getTotalM�ngdVaror());
		
		k.t�m();
		
		assertEquals(0, k.getTotalM�ngdVaror());
	}
	
	//Hj�lpmetod som l�gger till 10 varor (anv�nds inte i tester som testar varutill�ggning)
	private void l�ggTillVaror(Kvitto k){
		for(int i = 0; i<10; i++){
			k.l�ggTillVara(skapaVara("V"+i, bigDec("1"+i)));
		}
	}
	
	@Test
	public void testaTaBortAllaAvEnVara(){
		Vara v0 = skapaVara("V0", bigDec("10"));
		l�ggTillVaror(k);
		assertEquals(10, k.getTotalM�ngdVaror());
		assertTrue(k.taBortAllaAvEnVara(v0));
		assertEquals(9, k.getTotalM�ngdVaror());
	}
	
	@Test
	public void testaTaBortVaraSomFinnsFleraAv(){
		Vara v1 = skapaVara("V1", bigDec("30"));
		k.l�ggTillVara(v1, 3);
		assertTrue(k.taBortAllaAvEnVara(v1));
		assertEquals(0, k.getTotalM�ngdVaror());
	}
	
	@Test
	public void testaMinskaAntaletAvVara(){
		Vara v = skapaVara("V1", bigDec("30"));
		k.l�ggTillVara(v, 15);
		k.taBortVaror(v, 7);
		assertEquals(8, k.getTotalM�ngdVaror());
	}
	
	@Test
	public void testaTaBortSomInteFinns(){
		l�ggTillVaror(k);
		Vara v = skapaVara("Finns inte!", bigDec("10"));
		assertFalse(k.taBortAllaAvEnVara(v));
	}
	
	@Test
	public void testaTaMinskaAntalAvVaraSomInteFinns(){
		l�ggTillVaror(k);
		Vara v = skapaVara("Finns inte nu heller!", bigDec("255"));
		assertEquals(10, k.getTotalM�ngdVaror());
		assertFalse(k.taBortVaror(v, 10));
		assertEquals(10, k.getTotalM�ngdVaror());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaTaBortNullVara(){
		k.taBortAllaAvEnVara(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaMinskaNullVara(){
		k.taBortVaror(null, 345);
	}
	//L�gger till och tar bort slumpm�ssigt varor 
	//av upp till 1000 olika typer upp till 10 g�nger var
	@Test
	public void slumpTill�ggOchBorttag(){
		int expectedTotalAntal = 0;
		Random slump = new Random();
		int min = 1;
		int randomInt = min + slump.nextInt(999);
		for(int i = 0; i < randomInt; i++){
			Vara v = skapaVara("V"+i, bigDec(""+slump.nextInt(1000)));
			int antalSomL�ggsTill = min + slump.nextInt(10);
			k.l�ggTillVara(v, antalSomL�ggsTill);
			expectedTotalAntal += antalSomL�ggsTill;
			if(slump.nextBoolean()){
				if(slump.nextBoolean()){
					k.taBortVaror(v, antalSomL�ggsTill % 3);
					expectedTotalAntal -= antalSomL�ggsTill % 3;
				}else{
					k.taBortAllaAvEnVara(v);
					expectedTotalAntal -= antalSomL�ggsTill;
				}
			}
		}
		assertEquals(expectedTotalAntal, k.getTotalM�ngdVaror());
	}
	
	@Test
	public void testaR�knaUtPrisUtanRabatt(){
		k.t�m();
		Vara v1 = skapaVara("V1", bigDec("100"));
		Vara v2 = skapaVara("V2", bigDec("50"));
		k.l�ggTillVara(v1, 2);
		k.l�ggTillVara(v2, 1);
		BigDecimal expectedPrisBigDec = new BigDecimal("250");
		Pengar expectedTotalPris = new Pengar(expectedPrisBigDec);
		assertEquals(expectedTotalPris, k.getPrisUtanRabatt());
	}
	@Test
	public void testaR�knaUtPrisMedRabatt(){
		k.t�m();
		Vara v1 = skapaVara("V1", bigDec("10"));
		k.l�ggTillVara(v1, 15);
		Rabatt r = new M�ngdRabatt("10 V1 -25 SEK", new Pengar(bigDec("25")), 10);
		RabattLista.sparaRabatt(v1, r);
		Pengar expectedTotalPris = new Pengar(bigDec("125"));
		assertEquals(expectedTotalPris, k.getPrisMedRabatt());
		RabattLista.t�mLista();
	}
	
	@Test
	public void testaUtskrift(){
		k.t�m();
		l�ggTillVaror(k);
		Vara v1 = skapaVara("V1", bigDec("100"));
		M�ngdRabatt mr = new M�ngdRabatt("2 V1", new Pengar(bigDec("10")), 2);
		RabattLista.sparaRabatt(v1, mr);
		k.l�ggTillVara(v1, 3);
		Vara v2 = skapaVara("V2", bigDec("200"));
		M�rkesRabatt mr2 = new M�rkesRabatt("ABC V2", new Pengar(bigDec("25")), new M�rke("ABC"));
		RabattLista.sparaRabatt(v2, mr2);
		k.l�ggTillVara(v2, 3);
		assertEquals(k.skapaUtskrift(), "V0   1   10.00 SEK\n"
				+ "V1   4   34.00 SEK (-10.00 SEK)\n"
				+ "V2   4   23.00 SEK (-25.00 SEK)\n"
				+ "V3   1   13.00 SEK\n"
				+ "V4   1   14.00 SEK\n"
				+ "V5   1   15.00 SEK\n"
				+ "V6   1   16.00 SEK\n"
				+ "V7   1   17.00 SEK\n"
				+ "V8   1   18.00 SEK\n"
				+ "V9   1   19.00 SEK\n");
		RabattLista.t�mLista();
	}
}
