package kassatester;

import static org.junit.Assert.*;

import org.junit.Test;

import rabatt.MängdRabatt;
import rabatt.MärkesRabatt;
import rabatt.RabattLista;
import vara.*;

import java.util.*;
import java.math.*;

import kassa.*;

public class Kvittotest {
	
	//Tomt kvitto som tester kan jobba på
	private Kvitto k = new Kvitto();
	
	//Hjälpmetod för att skapa vara
	private Vara skapaVara(String beskrivning, BigDecimal pris){
		Märke m = new Märke("ABC");
		Currency valuta = Currency.getInstance("SEK");
		Pengar varuPris = new Pengar(pris, valuta, RoundingMode.HALF_UP);
		return new Vara(beskrivning, m, varuPris); 
	}
	
	//Hjälpmetod för att skapa BigDecimal
	private BigDecimal bigDec(String värde){
		return new BigDecimal(värde);
	}
	
	@Test
	public void testaLäggTillVarorIKonstruktor(){
		Vara v1 = skapaVara("V1", bigDec("100"));
		Vara v2 = skapaVara("V2", bigDec("200"));
		Vara v3 = skapaVara("V3", bigDec("300"));
		Kvitto k = new Kvitto(v1, v2);
		assertTrue(k.varaFinns(v1));
		assertTrue(k.varaFinns(v2));
		assertFalse(k.varaFinns(v3));
		assertEquals(2, k.getTotalMängdVaror());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaLäggTillNullIKonstruktor(){
		Vara v1 = null;
		Vara v2 = null;
		Kvitto k = new Kvitto(v1, v2);
	}
	
	@Test
	public void testaLäggTillVaraIMetod(){
		Vara v = skapaVara("V", bigDec("100"));
		k.läggTillVara(v, 3);
		assertTrue(k.varaFinns(v));
		for(int i = 0; i<7; i++){
			k.läggTillVara(skapaVara("V"+i, bigDec(""+i)));
		}
		assertEquals(10, k.getTotalMängdVaror());
	}
	
	@Test
	public void testaLäggTillVaraSomFinns(){
		Vara v = skapaVara("Vara V", bigDec("100"));
		k.läggTillVara(v, 2);
		assertEquals(2, k.getTotalMängdVaror());
		k.läggTillVara(v, 1);
		assertEquals(3, k.getTotalMängdVaror());
	}
	
	@Test
	public void testaLäggTillMaxAntalAvEnVara(){
		Vara v = skapaVara("Vara", bigDec("150"));
		k.läggTillVara(v, Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, k.getTotalMängdVaror());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaLäggTillNullVara(){
		Vara v = null;
		k.läggTillVara(v, 3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaLäggTillNullIMetod(){
		Vara v = null;
		k.läggTillVara(v);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaLäggTillNollVaror(){
		Vara v = skapaVara("V1", bigDec("323"));
		k.läggTillVara(v, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaLäggTillNegativtAntalVaror(){
		Vara v = skapaVara("V1", bigDec("6.5"));
		k.läggTillVara(v, -10);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaLäggTillNollAntalVaror(){
		Vara v = skapaVara("Vara1", bigDec("75"));
		k.läggTillVara(v, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaLäggTillIntMinAntalVaror(){
		Vara v = skapaVara("Vara1", bigDec("375"));
		k.läggTillVara(v, Integer.MIN_VALUE);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaLäggTillStörreAntalÄnIntMax(){
		Vara v = skapaVara("V1", bigDec("5942"));
		k.läggTillVara(v, (Integer.MAX_VALUE+1));
	}
	
	@Test 
	public void testaLäggTillVarorIMetodMedArray(){
		Vara v1 = skapaVara("V1", bigDec("100"));
		Vara v2 = skapaVara("V2", bigDec("200"));
		Vara v3 = skapaVara("V3", bigDec("300"));
		Vara[] varor = {v1, v2, v3, v1};
		assertEquals(0, k.getTotalMängdVaror());
		k.läggTillVaror(varor);
		assertEquals(4, k.getTotalMängdVaror());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaLäggTillTomArray(){
		Vara[] varor = new Vara[0];
		k.läggTillVaror(varor);
	}
	
	@Test
	public void testaLäggTillFlerVarorFrånLista(){
		ArrayList<Vara> varuSamling = new ArrayList<Vara>();
		//Lägg till 6 varor
		for(int i = 0; i<6; i++){
			varuSamling.add(skapaVara("V"+i, bigDec(""+i)));
		}
		//Lägg till samlingen (ArrayList i detta fall) och jämför
		k.läggTillVarorFrånSamling(varuSamling);
		assertEquals(6, k.getTotalMängdVaror());
		Set<Vara> varuSet = new HashSet<Vara>(varuSamling);
		assertEquals(k.getVaruSet(), varuSet);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaLäggTillFrånListaNärListaÄrNull(){
		ArrayList<Vara> varuSamling = null;
		k.läggTillVarorFrånSamling(varuSamling);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testaLäggTillFrånListaMedNullVärde(){
		ArrayList<Vara> varuSamling = new ArrayList<Vara>();
		Vara v = skapaVara("V", bigDec("2765"));
		varuSamling.add(v);
		varuSamling.add(null);
		k.läggTillVarorFrånSamling(varuSamling);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaLäggTillTomLista(){
		ArrayList<Vara> varuSamling = new ArrayList<Vara>();
		k.läggTillVarorFrånSamling(varuSamling);
	}
	
	@Test
	public void testaTömKvitto(){
		for(int i = 0; i<5; i++){
			k.läggTillVara(skapaVara("V"+i, bigDec(""+i)));
		}
		assertEquals(5, k.getTotalMängdVaror());
		
		k.tömVaruMap();
		
		assertEquals(0, k.getTotalMängdVaror());
	}
	
	//Hjälpmetod som lägger till 10 varor (används inte i tester som testar varutilläggning)
	private void läggTillVaror(Kvitto k){
		for(int i = 0; i<10; i++){
			k.läggTillVara(skapaVara("V"+i, bigDec("1"+i)));
		}
	}
	
	@Test
	public void testaTaBortAllaAvEnVara(){
		Vara v0 = skapaVara("V0", bigDec("10"));
		läggTillVaror(k);
		assertEquals(10, k.getTotalMängdVaror());
		assertTrue(k.taBortAllaAvEnVara(v0));
		assertEquals(9, k.getTotalMängdVaror());
	}
	
	@Test
	public void testaTaBortVaraSomFinnsFleraAv(){
		Vara v1 = skapaVara("V1", bigDec("30"));
		k.läggTillVara(v1, 3);
		assertTrue(k.taBortAllaAvEnVara(v1));
		assertEquals(0, k.getTotalMängdVaror());
	}
	
	@Test
	public void testaMinskaAntaletAvVara(){
		Vara v = skapaVara("V1", bigDec("30"));
		k.läggTillVara(v, 15);
		k.taBortVaror(v, 7);
		assertEquals(8, k.getTotalMängdVaror());
	}
	
	@Test
	public void testaTaBortSomInteFinns(){
		läggTillVaror(k);
		Vara v = skapaVara("Finns inte!", bigDec("10"));
		assertFalse(k.taBortAllaAvEnVara(v));
	}
	
	@Test
	public void testaTaMinskaAntalAvVaraSomInteFinns(){
		läggTillVaror(k);
		Vara v = skapaVara("Finns inte nu heller!", bigDec("255"));
		assertEquals(10, k.getTotalMängdVaror());
		assertFalse(k.taBortVaror(v, 10));
		assertEquals(10, k.getTotalMängdVaror());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaTaBortNullVara(){
		k.taBortAllaAvEnVara(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaMinskaNullVara(){
		k.taBortVaror(null, 345);
	}
	//Lägger till och tar bort slumpmässigt varor 
	//av upp till 1000 olika typer upp till 10 gånger var
	@Test
	public void slumpTilläggOchBorttag(){
		int expectedTotalAntal = 0;
		Random slump = new Random();
		int min = 1;
		int randomInt = min + slump.nextInt(999);
		for(int i = 0; i < randomInt; i++){
			Vara v = skapaVara("V"+i, bigDec(""+slump.nextInt(1000)));
			int antalSomLäggsTill = min + slump.nextInt(10);
			k.läggTillVara(v, antalSomLäggsTill);
			expectedTotalAntal += antalSomLäggsTill;
			if(slump.nextBoolean()){
				if(slump.nextBoolean()){
					k.taBortVaror(v, antalSomLäggsTill % 3);
					expectedTotalAntal -= antalSomLäggsTill % 3;
				}else{
					k.taBortAllaAvEnVara(v);
					expectedTotalAntal -= antalSomLäggsTill;
				}
			}
		}
		assertEquals(expectedTotalAntal, k.getTotalMängdVaror());
	}
	
	@Test
	public void testaRäknaUtPrisUtanRabatt(){
		k.tömVaruMap();
		Vara v1 = skapaVara("V1", bigDec("100"));
		Vara v2 = skapaVara("V2", bigDec("50"));
		k.läggTillVara(v1, 2);
		k.läggTillVara(v2, 1);
		BigDecimal expectedPrisBigDec = new BigDecimal("250");
		Pengar expectedTotalPris = new Pengar(expectedPrisBigDec);
		assertEquals(expectedTotalPris, k.getPrisUtanRabatt());
	}
	@Test
	public void testaRäknaUtPrisMedRabatt(){
		k.tömVaruMap();
		Vara v1 = skapaVara("V1", bigDec("10"));
		k.läggTillVara(v1, 15);
		MängdRabatt r = new MängdRabatt("10 V1 -25 SEK", new Pengar(bigDec("25")), 10);
		RabattLista.sparaRabatt(v1, r);
		Pengar expectedTotalPris = new Pengar(bigDec("125"));
		assertEquals(expectedTotalPris, k.getPrisMedRabatt());
		RabattLista.tömLista();
	}
	
	@Test
	public void testaUtskrift(){
		k.tömVaruMap();
		läggTillVaror(k);
		Vara v1 = skapaVara("V1", bigDec("100"));
		MängdRabatt mr = new MängdRabatt("2 V1", new Pengar(bigDec("10")), 2);
		RabattLista.sparaRabatt(v1, mr);
		k.läggTillVara(v1, 3);
		Vara v2 = skapaVara("V2", bigDec("200"));
		MärkesRabatt mr2 = new MärkesRabatt("ABC V2", new Pengar(bigDec("25")), new Märke("ABC"));
		RabattLista.sparaRabatt(v2, mr2);
		k.läggTillVara(v2, 3);
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
		RabattLista.tömLista();
	}
	
	@Test
	public void realistiskAnvändning1(){
		Kvitto k1 = new Kvitto();
		Vara v1 = null;
		try{
			k1.läggTillVara(v1);
		}catch(IllegalArgumentException e){
			
		}
		Vara v2 = skapaVara("V2", bigDec("20"));
		k.läggTillVara(v2);
		k.taBortVaror(v2, 1);
		k.läggTillVara(v2);
		Vara v3 = skapaVara("V3", bigDec("30"));
		k.läggTillVara(v3, 3);
		try{
			k1.läggTillVara(v1);
		}catch(IllegalArgumentException e){
			
		}
		k.taBortVaror(v3, 1);
		Pengar expectedTotalPris = new Pengar(bigDec("80"));
		assertEquals(expectedTotalPris, k.getPrisUtanRabatt());
		assertEquals(expectedTotalPris, k.getPrisUtanRabatt());
		MängdRabatt mr = new MängdRabatt("2 V3 -10", new Pengar(bigDec("10")), 2);
		RabattLista.sparaRabatt(v3, mr);
		Pengar expectedTotalPrisMedRabatt = new Pengar(bigDec("70"));
		assertEquals(expectedTotalPrisMedRabatt, k.getPrisMedRabatt());
		k.skapaUtskrift();
	}
	
	@Test
	public void realistiskAnvändning2(){
		Kvitto k = new Kvitto();
		Vara v = skapaVara("V1", bigDec("100"));
		k.läggTillVara(v);
		assertEquals(new Pengar(bigDec("100")), k.getPrisUtanRabatt());
		assertEquals(new Pengar(bigDec("100")), k.getPrisMedRabatt());
		k.skapaUtskrift();
	}
}
