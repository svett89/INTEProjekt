package kassa;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Test;

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
		assertEquals(toCompare, RabattLista.getRabatter());
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
}
