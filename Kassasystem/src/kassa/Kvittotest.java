package kassa;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
import java.math.*;

public class Kvittotest {
	
	//Hjälpmetod som skapar tomt kvitto.
	private Kvitto skapaTomtK(){
		return new Kvitto();
	}
	private BigDecimal värde = new BigDecimal(10);
	private Currency valuta = Currency.getInstance("SEK");
	private Pengar pris = new Pengar(värde, valuta, RoundingMode.HALF_EVEN);
	private Märke märke = new Märke("");
	private int mängd = 0;
	//Hjälpmetod som skapar en tom vara.
	private Vara skapaTomV(){
		return new Vara("", new Märke(""), 0, pris);
	}
	private Vara skapaVMedPris(String beskrivning, Märke märke, int mängd, int pris){
		BigDecimal bd = new BigDecimal(pris);
		Pengar varuPris = new Pengar(bd, valuta, RoundingMode.HALF_EVEN);
		return new Vara(beskrivning, märke, mängd, varuPris); 
	}
	
	@Test
	public void testaLäggTillVarorIKonstruktor(){
		//Vara kommer kanske bli abstrakt i slutändan! 
		Vara v1 = skapaTomV();
		Vara v2 = skapaTomV();
		Kvitto k = new Kvitto(v1, v2);
		assertTrue(k.varaFinns(v1));
		assertTrue(k.varaFinns(v2));
		assertTrue(k.getTotalMängdVaror() == 2);
	}
	
	@Test
	public void testaLäggTillVaraIMetod(){
		Kvitto k = skapaTomtK();
		Vara v = skapaTomV();
		k.läggTillVara(skapaTomV(), 1);
		assertTrue(k.varaFinns(v));
		
		for(int i = 0; i<7; i++){
			k.läggTillVara(skapaTomV());
		}
		assertTrue(k.getTotalMängdVaror() == 8);
	}
	
	public void testaLäggTillVarorIMetod(){
		Kvitto k = skapaTomtK();
		Vara[] varor = new Vara[5];
		assertTrue(k.getTotalMängdVaror() == 0);
		k.läggTillVaror(varor);
		assertTrue(k.getTotalMängdVaror() == 5);
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
		assertTrue(k.getTotalMängdVaror() == 6);
		Set<Vara> varuSet = new HashSet<Vara>(varuSamling);
		assertEquals(k.getVaruSet(), varuSet);
	}
	
	@Test
	public void testaTömKvitto(){
		Kvitto k = skapaTomtK();
		for(int i = 0; i<5; i++){
			k.läggTillVara(skapaTomV());
		}
		assertTrue(k.getTotalMängdVaror() == 5);
		
		k.töm();
		
		assertTrue(k.getTotalMängdVaror() == 0);
	}
	
	//Hjälpmetod som lägger till 10 varor (används inte i tester som testar varutilläggning)
	private void läggTillVaror(Kvitto k){
		for(int i = 0; i<10; i++){
			k.läggTillVara(skapaTomV());
		}
	}
	
	@Test
	public void testaTaBortVara(){
		BigDecimal bd = new BigDecimal(30);
		pris.setBelopp(bd);
		Kvitto k = new Kvitto();
		Vara v = new Vara("", märke, mängd, pris);
		läggTillVaror(k);
		assertTrue(k.getTotalMängdVaror() == 10);
		k.taBortAllaAvEnVara(v);
		assertTrue(k.getTotalMängdVaror() == 0);
	}
	
	@Test
	public void testaTaBortOchLäggTill(){
		Kvitto k = new Kvitto();
		Vara v1 = new Vara("V1", märke, mängd, pris);
		Vara v2 = new Vara("V2", märke, mängd, pris);
		Vara v22 = new Vara("V2", märke, mängd, pris);
		Vara v3 = new Vara("V3", märke, mängd, pris);
		k.läggTillVaror(v1, v2, v22, v3);
		assertTrue(k.getTotalMängdVaror() == 4);
		k.taBortAllaAvEnVara(v2);
		assertTrue(k.getTotalMängdVaror() == 2);
		
	}
	
	private Kvitto k = new Kvitto();
	
	//Lägger till slumpmässigt många varor av olika typer mellan 1-1000
	//OBS! Bedrövligt dålig just nu. Ska utöka och förbättra.
	@Test
	public void slumpTillägg(){
		Random slump = new Random();
		int min = 1;
		int randomInt = min + slump.nextInt(999);
		for(int i = 0; i < randomInt; i++){
			Vara v = new Vara(""+randomInt, märke, mängd, pris);
			k.läggTillVara(v);
		}
		assertTrue(k.getTotalMängdVaror() == randomInt);
	}
	
	@Test
	public void testaRäknaUtPris(){
		k.töm();
		Vara v1 = skapaVMedPris("Mössa", märke, mängd, 100);
		Vara v2 = skapaVMedPris("Vante", märke, mängd, 50);
		k.läggTillVara(v1, 2);
		k.läggTillVara(v2, 1);
		Pengar expectedTotalPris = new Pengar(250);
		assertTrue(k.getPris().equals(expectedTotalPris));
	}
}
