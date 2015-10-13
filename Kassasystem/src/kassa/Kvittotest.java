package kassa;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
public class Kvittotest {
	
	//Hj�lpmetod som skapar tomt kvitto (meningsl�s, fixa till).
	private Kvitto skapaTomtK(){
		return new Kvitto();
	}
	
	//Hj�lpmetod som skapar tom vara (meningsl�s, fixa till).
	private Vara skapaTomV(){
		return new Vara();
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
	public void testaL�ggTillVarorIMetod(){
		Kvitto k = skapaTomtK();
		Vara v = skapaTomV();
		k.l�ggTillVara(skapaTomV(), 1);
		assert(k.varaFinns(v));
		
		for(int i = 0; i<7; i++){
			k.l�ggTillVara(skapaTomV());
		}
		assert(k.getTotalM�ngdVaror() == 8);
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
	
	//De h�r testerna �r �nnu inte implementerade i Kvitto och kompilerar d�rf�r inte
	@Test
	public void testaT�mKvitto(){
		Kvitto k = skapaTomtK();
		for(int i = 0; i<5; i++){
			k.l�ggTillVara(skapaTomV());
		}
		assertEquals(k.getAntalVaror() == 5);
		
		k.t�m();
		
		assertEquals(k.getAntalVaror() == 0);
	}
	
	//Hj�lpmetod som l�gger till 10 varor (anv�nds inte i tester som testar varutill�ggning)
	private void l�ggTillVaror(Kvitto k){
		for(int i = 0; i<10; i++){
			k.l�ggTillVara(skapaTomV());
		}
	}
	
	@Test
	public void testaTaBortSenasteVara(){
		Kvitto k = new Kvitto();
		l�ggTillVaror(k);
		assertEquals(k.getAntalVaror == 10);
		k.taBortSenasteVara();
		assertEquals(k.getAntalVaror == 9);
	}
	
}
