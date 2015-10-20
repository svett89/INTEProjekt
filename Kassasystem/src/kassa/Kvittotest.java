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
	private M�rke m�rke = new M�rke("");
	private int m�ngd = 0;
	//Hj�lpmetod som skapar en tom vara.
	private Vara skapaTomV(){
		return new Vara("", new M�rke(""), 0, pris);
	}
	private Vara skapaVMedPris(String beskrivning, M�rke m�rke, int m�ngd, int pris){
		BigDecimal bd = new BigDecimal(pris);
		Pengar varuPris = new Pengar(bd, valuta, RoundingMode.HALF_EVEN);
		return new Vara(beskrivning, m�rke, m�ngd, varuPris); 
	}
	
	@Test
	public void testaL�ggTillVarorIKonstruktor(){
		//Vara kommer kanske bli abstrakt i slut�ndan! 
		Vara v1 = skapaTomV();
		Vara v2 = skapaTomV();
		Kvitto k = new Kvitto(v1, v2);
		assertTrue(k.varaFinns(v1));
		assertTrue(k.varaFinns(v2));
		assertTrue(k.getTotalM�ngdVaror() == 2);
	}
	
	@Test
	public void testaL�ggTillVaraIMetod(){
		Kvitto k = skapaTomtK();
		Vara v = skapaTomV();
		k.l�ggTillVara(skapaTomV(), 1);
		assertTrue(k.varaFinns(v));
		
		for(int i = 0; i<7; i++){
			k.l�ggTillVara(skapaTomV());
		}
		assertTrue(k.getTotalM�ngdVaror() == 8);
	}
	
	public void testaL�ggTillVarorIMetod(){
		Kvitto k = skapaTomtK();
		Vara[] varor = new Vara[5];
		assertTrue(k.getTotalM�ngdVaror() == 0);
		k.l�ggTillVaror(varor);
		assertTrue(k.getTotalM�ngdVaror() == 5);
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
		assertTrue(k.getTotalM�ngdVaror() == 6);
		Set<Vara> varuSet = new HashSet<Vara>(varuSamling);
		assertEquals(k.getVaruSet(), varuSet);
	}
	
	@Test
	public void testaT�mKvitto(){
		Kvitto k = skapaTomtK();
		for(int i = 0; i<5; i++){
			k.l�ggTillVara(skapaTomV());
		}
		assertTrue(k.getTotalM�ngdVaror() == 5);
		
		k.t�m();
		
		assertTrue(k.getTotalM�ngdVaror() == 0);
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
		Vara v = new Vara("", m�rke, m�ngd, pris);
		l�ggTillVaror(k);
		assertTrue(k.getTotalM�ngdVaror() == 10);
		k.taBortAllaAvEnVara(v);
		assertTrue(k.getTotalM�ngdVaror() == 0);
	}
	
	@Test
	public void testaTaBortOchL�ggTill(){
		Kvitto k = new Kvitto();
		Vara v1 = new Vara("V1", m�rke, m�ngd, pris);
		Vara v2 = new Vara("V2", m�rke, m�ngd, pris);
		Vara v22 = new Vara("V2", m�rke, m�ngd, pris);
		Vara v3 = new Vara("V3", m�rke, m�ngd, pris);
		k.l�ggTillVaror(v1, v2, v22, v3);
		assertTrue(k.getTotalM�ngdVaror() == 4);
		k.taBortAllaAvEnVara(v2);
		assertTrue(k.getTotalM�ngdVaror() == 2);
		
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
			Vara v = new Vara(""+randomInt, m�rke, m�ngd, pris);
			k.l�ggTillVara(v);
		}
		assertTrue(k.getTotalM�ngdVaror() == randomInt);
	}
	
	@Test
	public void testaR�knaUtPris(){
		k.t�m();
		Vara v1 = skapaVMedPris("M�ssa", m�rke, m�ngd, 100);
		Vara v2 = skapaVMedPris("Vante", m�rke, m�ngd, 50);
		k.l�ggTillVara(v1, 2);
		k.l�ggTillVara(v2, 1);
		Pengar expectedTotalPris = new Pengar(250);
		assertTrue(k.getPris().equals(expectedTotalPris));
	}
}
