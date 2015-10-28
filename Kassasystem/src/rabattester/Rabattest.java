package rabattester;

import java.math.BigDecimal;
import java.util.HashMap;

import rabatt.*;
import kassa.Kvitto;
import kassa.Pengar;

import org.junit.Test;

import vara.Märke;
import vara.Vara;
import static org.junit.Assert.*;


public class Rabattest {
	
	private final Märke m = new Märke("ABC");
	private HashMap<Vara, Rabatt> toCompare = new HashMap<Vara, Rabatt>();
	
	private Pengar skapaPengar(String värde){
		BigDecimal bd = new BigDecimal(värde);
		Pengar p = new Pengar(bd);
		return p;
	}
	
	private Vara skapaVara(String namn, Pengar pris){
		return new Vara(namn, m, pris);
	}
	
	private void töm(){
		RabattLista.tömLista();
	}
	@Test
	public void sparaOchHämtaMärkesRabatt(){
		töm();
		MärkesRabatt mr = new MärkesRabatt("KantoKlothing", skapaPengar("200"), new Märke("KantoKlothing"));
		Vara vara1 = skapaVara("VaraMärke", skapaPengar("400"));
		RabattLista.sparaRabatt(vara1, mr);
		toCompare.put(vara1, mr);
		assertEquals(toCompare, RabattLista.getRabatter());
	}
	
	@Test
	public void sparaOchHämtaMängdRabatt(){
		töm();
		MängdRabatt mr = new MängdRabatt("Mängdrabatt", skapaPengar("30"), 3);
		Vara vara1 = skapaVara("VaraMängd", skapaPengar("200"));
		RabattLista.sparaRabatt(vara1, mr);
		toCompare.put(vara1, mr);
		assertEquals(3, mr.getAntal());
		assertEquals(toCompare, RabattLista.getRabatter());
	}
	
	@Test
	public void sparaRabattFörVaraSomRedanFinns(){
		töm();
		MängdRabatt mr = new MängdRabatt("Mr", skapaPengar("100"), 3);
		Vara vara1 = skapaVara("VaraMängd", skapaPengar("400"));
		RabattLista.sparaRabatt(vara1, mr);
		MärkesRabatt mr2 = new MärkesRabatt("Mr2", skapaPengar("200"), new Märke("HoennHoses"));
		assertFalse(RabattLista.sparaRabatt(vara1, mr2));
	}
	
	@Test
	public void taBortRabatt(){
		töm();
		MängdRabatt mr = new MängdRabatt("Mängdrabatt", skapaPengar("55"), 4);
		Vara vara1 = skapaVara("tröja", skapaPengar("350"));
		RabattLista.sparaRabatt(vara1, mr);
		assertEquals(1, RabattLista.storlek());
		RabattLista.taBortRabatt(vara1);
		assertEquals(0, RabattLista.storlek());
	}
	
	@Test
	public void taBortRabattSomInteFinns(){
		töm();
		Vara v1 = skapaVara("Lamatröja", skapaPengar("5654"));
		assertFalse(RabattLista.taBortRabatt(v1));
	}
	
	private Kvitto k = new Kvitto();
	
	private void läggTillVaror(){
		for(int i = 1; i<11; i++){
			k.läggTillVara(skapaVara("V"+i, skapaPengar(""+(10*i))));
		}
	}
	@Test
	public void räknaUtRabattSomInteFinns(){
		töm();
		läggTillVaror();
		Pengar expectedPris = skapaPengar("550");
		assertEquals(expectedPris, k.getPrisUtanRabatt());
		HashMap<Vara, Pengar> toCompareTotalRabatt = new HashMap<Vara, Pengar>();
		assertEquals(toCompareTotalRabatt, RabattLista.räknaUtRabatt(k));
	}
	@Test
	public void räknaUtMärkesRabattSomFinns(){
		töm();
		läggTillVaror();
		Pengar expectedPrisUtanRabatt = skapaPengar("550");
		assertEquals(expectedPrisUtanRabatt, k.getPrisUtanRabatt());
		Vara v = new Vara("V5", new Märke("ABC"), skapaPengar("50"));
		Rabatt r = new MärkesRabatt("20 sek för V5", skapaPengar("20"), new Märke("ABC"));
		RabattLista.sparaRabatt(v, r);
		Pengar expectedPrisMedRabatt = skapaPengar("530");
		k.räknaUtRabatt();
		assertEquals(expectedPrisMedRabatt, k.getPrisMedRabatt());
	}
	
	@Test
	public void räknaUtMängdRabattSomFinns(){
		töm();
		läggTillVaror();
		Vara v = new Vara("V6", new Märke("ABC"), skapaPengar("60"));
		k.läggTillVara(v);
		Rabatt r = new MängdRabatt("30 sek för 2 V6", skapaPengar("30"), 2);
		RabattLista.sparaRabatt(v, r);
		k.räknaUtRabatt();
		Pengar expectedPrisMedRabatt = skapaPengar("580");
		assertEquals(expectedPrisMedRabatt, k.getPrisMedRabatt());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaNullVärdenFörRäknaUtMängdRabatt(){
		töm();
		HashMap<Vara, Pengar> hm = null;
		MängdRabatt mr = new MängdRabatt("mr", skapaPengar("10"), 3);
		mr.räknaUtRabatt(skapaVara("v1", skapaPengar("40")), 3, hm);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaAntalMindreÄnEllerLikaMedNollMängdRabatt(){
		töm();
		HashMap<Vara, Pengar> hm = new HashMap<Vara, Pengar>();
		MängdRabatt mr = new MängdRabatt("mr", skapaPengar("30"), 6);
		mr.räknaUtRabatt(skapaVara("vara1", skapaPengar("55")), 0, hm);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaNullVärdenFörRäknaUtMärkesRabatt(){
		töm();
		HashMap<Vara, Pengar> hm = null;
		MärkesRabatt mr = new MärkesRabatt("mr", skapaPengar("10"), new Märke("Krante"));
		mr.räknaUtRabatt(skapaVara("v1", skapaPengar("40")), 3, hm);
	}
	
	@Test
	public void testaGettersOchToString(){
		töm();
		MärkesRabatt mr = new MärkesRabatt("mr", skapaPengar("74"), new Märke("MärkeA"));
		assertEquals(new Märke("MärkeA"), mr.getMärke());
		assertEquals("mr", mr.getRabattNamn());
		assertEquals(skapaPengar("74"), mr.rabattAvdrag());
		assertEquals("mr", mr.toString());
	}
	

}
