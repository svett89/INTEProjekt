package kassa;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
public class Kvittotest {
	
	//Hjälpmetod som skapar tomt kvitto (meningslös, fixa till).
	private Kvitto skapaTomtK(){
		return new Kvitto();
	}
	
	//Hjälpmetod som skapar tom vara (meningslös, fixa till).
	private Vara skapaTomV(){
		return new Vara();
	}
	
	@Test
	public void testaLäggTillVarorIKonstruktor(){
		//Vara kommer kanske bli abstrakt i slutändan! 
		Vara v1 = skapaTomV();
		Vara v2 = skapaTomV();
		Kvitto k = new Kvitto(v1, v2);
		assertTrue(k.varaFinns(v1));
		assertTrue(k.varaFinns(v2));
		assert(k.getTotalMängdVaror() == 2);
	}
	
	@Test
	public void testaLäggTillVarorIMetod(){
		Kvitto k = skapaTomtK();
		Vara v = skapaTomV();
		k.läggTillVara(skapaTomV(), 1);
		assert(k.varaFinns(v));
		
		for(int i = 0; i<7; i++){
			k.läggTillVara(skapaTomV());
		}
		assert(k.getTotalMängdVaror() == 8);
	}
	
	@Test
	public void testaLäggTillFlerVarorFrånLista(){
		Kvitto k = skapaTomtK();
		ArrayList<Vara> varuSamling = new ArrayList<Vara>();
		//Lägg till 6 varor
		for(int i = 0; i<6; i++){
			varuSamling.add(skapaTomV());
		}
		//Lägg till samlingen (ArrayList i detta fall) och jämför
		k.läggTillVarorFrånSamling(varuSamling);
		assert(k.getTotalMängdVaror() == 6);
		Set<Vara> varuSet = new HashSet<Vara>(varuSamling);
		assertEquals(k.getVaruSet(), varuSet);
	}
	
	//De här testerna är ännu inte implementerade i Kvitto och kompilerar därför inte
	@Test
	public void testaTömKvitto(){
		Kvitto k = skapaTomtK();
		for(int i = 0; i<5; i++){
			k.läggTillVara(skapaTomV());
		}
		assertEquals(k.getAntalVaror() == 5);
		
		k.töm();
		
		assertEquals(k.getAntalVaror() == 0);
	}
	
	//Hjälpmetod som lägger till 10 varor (används inte i tester som testar varutilläggning)
	private void läggTillVaror(Kvitto k){
		for(int i = 0; i<10; i++){
			k.läggTillVara(skapaTomV());
		}
	}
	
	@Test
	public void testaTaBortSenasteVara(){
		Kvitto k = new Kvitto();
		läggTillVaror(k);
		assertEquals(k.getAntalVaror == 10);
		k.taBortSenasteVara();
		assertEquals(k.getAntalVaror == 9);
	}
	
}
