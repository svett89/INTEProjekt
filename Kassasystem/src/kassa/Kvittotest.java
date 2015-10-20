package kassa;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
import java.math.*;

public class Kvittotest {
	
	//Hj�lpmetod som skapar tomt kvitto.
	private Kvitto skapaTomtK(){
		return new Kvitto();
	}
	private BigDecimal v�rde = new BigDecimal(10);
	private Currency valuta = Currency.getInstance("SEK");
	private Pengar pris = new Pengar(v�rde, valuta, RoundingMode.HALF_EVEN);
	//Hj�lpmetod som skapar en tom vara.
	private Vara skapaTomV(){
		return new Vara("", pris);
	}
	
	@Test
	public void testaL�ggTillVarorIKonstruktor(){
		//Vara kommer kanske bli abstrakt i slut�ndan! 
		Vara v1 = skapaTomV();
		Vara v2 = skapaTomV();
		Kvitto k = new Kvitto(v1, v2);
		assertTrue(k.varaFinns(v1));
		assertTrue(k.varaFinns(v2));
		assert(k.getTotalM�ngdVaror() == 2);
	}
	
	@Test
	public void testaL�ggTillVaraIMetod(){
		Kvitto k = skapaTomtK();
		Vara v = skapaTomV();
		k.l�ggTillVara(skapaTomV(), 1);
		assert(k.varaFinns(v));
		
		for(int i = 0; i<7; i++){
			k.l�ggTillVara(skapaTomV());
		}
		assert(k.getTotalM�ngdVaror() == 8);
	}
	
	public void testaL�ggTillVarorIMetod(){
		Kvitto k = skapaTomtK();
		Vara[] varor = new Vara[5];
		assert(k.getTotalM�ngdVaror() == 0);
		k.l�ggTillVaror(varor);
		assert(k.getTotalM�ngdVaror() == 5);
	}
	
	@Test
	public void testaL�ggTillFlerVarorFr�nLista(){
		Kvitto k = skapaTomtK();
		ArrayList<Vara> varuSamling = new ArrayList<Vara>();
		//L�gg till 6 varor
		for(int i = 0; i<6; i++){
			varuSamling.add(skapaTomV());
		}
		//L�gg till samlingen (ArrayList i detta fall) och j�mf�r
		k.l�ggTillVarorFr�nSamling(varuSamling);
		assert(k.getTotalM�ngdVaror() == 6);
		Set<Vara> varuSet = new HashSet<Vara>(varuSamling);
		assertEquals(k.getVaruSet(), varuSet);
	}
	
	@Test
	public void testaT�mKvitto(){
		Kvitto k = skapaTomtK();
		for(int i = 0; i<5; i++){
			k.l�ggTillVara(skapaTomV());
		}
		assert(k.getTotalM�ngdVaror() == 5);
		
		k.t�m();
		
		assert(k.getTotalM�ngdVaror() == 0);
	}
	
	//Hj�lpmetod som l�gger till 10 varor (anv�nds inte i tester som testar varutill�ggning)
	private void l�ggTillVaror(Kvitto k){
		for(int i = 0; i<10; i++){
			k.l�ggTillVara(skapaTomV());
		}
	}
	
	@Test
	public void testaTaBortVara(){
		BigDecimal bd = new BigDecimal(30);
		pris.setBelopp(bd);
		Kvitto k = new Kvitto();
		Vara v = new Vara("", pris);
		l�ggTillVaror(k);
		assert(k.getTotalM�ngdVaror() == 10);
		k.taBortAllaAvEnVara(v);
		assert(k.getTotalM�ngdVaror() == 9);
	}
	
	@Test
	public void testaTaBortOchL�ggTill(){
		Kvitto k = new Kvitto();
		Vara v1 = new Vara("V1", pris);
		Vara v2 = new Vara("V2", pris);
		Vara v22 = new Vara("V2", pris);
		Vara v3 = new Vara("V3", pris);
		k.l�ggTillVaror(v1, v2, v22, v3);
		assert(k.getTotalM�ngdVaror() == 4);
		k.taBortAllaAvEnVara(v2);
		assert(k.getTotalM�ngdVaror() == 2);
		
	}
	
	private Kvitto k = new Kvitto();
	
	//L�gger till slumpm�ssigt m�nga varor av olika typer mellan 1-1000
	//OBS! Bedr�vligt d�lig just nu. Ska ut�ka och f�rb�ttra.
	@Test
	public void slumpTill�gg(){
		Random slump = new Random();
		int min = 1;
		int randomInt = min + slump.nextInt(999);
		for(int i = 0; i < randomInt; i++){
			Vara v = new Vara(""+randomInt, pris);
			k.l�ggTillVara(v);
			assert(k.getTotalM�ngdVaror() == 1);
		}
		assert(k.getTotalM�ngdVaror() == randomInt);
	}
}
