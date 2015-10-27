package kassa;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Test;

import static org.junit.Assert.*;


public class Rabattest {
	
	private final M�rke m = new M�rke("ABC");
	private HashMap<Vara, Rabatt> toCompare = new HashMap<Vara, Rabatt>();
	
	private Pengar skapaPengar(String v�rde){
		BigDecimal bd = new BigDecimal(v�rde);
		Pengar p = new Pengar(bd);
		return p;
	}
	
	private Vara skapaVara(String namn, Pengar pris){
		return new Vara(namn, m, pris);
	}
	
	private void t�m(){
		RabattLista.t�mLista();
	}
	@Test
	public void sparaOchH�mtaM�rkesRabatt(){
		t�m();
		M�rkesRabatt mr = new M�rkesRabatt("KantoKlothing", skapaPengar("200"), new M�rke("KantoKlothing"));
		Vara vara1 = skapaVara("VaraM�rke", skapaPengar("400"));
		RabattLista.sparaRabatt(vara1, mr);
		toCompare.put(vara1, mr);
		assertEquals(toCompare, RabattLista.getRabatter());
	}
	
	@Test
	public void sparaOchH�mtaM�ngdRabatt(){
		t�m();
		M�ngdRabatt mr = new M�ngdRabatt("M�ngdrabatt", skapaPengar("30"), 3);
		Vara vara1 = skapaVara("VaraM�ngd", skapaPengar("200"));
		RabattLista.sparaRabatt(vara1, mr);
		toCompare.put(vara1, mr);
		assertEquals(toCompare, RabattLista.getRabatter());
	}
	
	@Test
	public void taBortRabatt(){
		t�m();
		M�ngdRabatt mr = new M�ngdRabatt("M�ngdrabatt", skapaPengar("55"), 4);
		Vara vara1 = skapaVara("tr�ja", skapaPengar("350"));
		RabattLista.sparaRabatt(vara1, mr);
		assertEquals(1, RabattLista.storlek());
		RabattLista.taBortRabatt(vara1);
		assertEquals(0, RabattLista.storlek());
	}
	
	private Kvitto k = new Kvitto();
	
	private void l�ggTillVaror(){
		for(int i = 1; i<11; i++){
			k.l�ggTillVara(skapaVara("V"+i, skapaPengar(""+(10*i))));
		}
	}
	@Test
	public void r�knaUtRabattSomInteFinns(){
		t�m();
		l�ggTillVaror();
		Pengar expectedPris = skapaPengar("550");
		assertEquals(expectedPris, k.getPrisUtanRabatt());
		HashMap<Vara, Pengar> toCompareTotalRabatt = new HashMap<Vara, Pengar>();
		assertEquals(toCompareTotalRabatt, RabattLista.r�knaUtRabatt(k));
	}
	@Test
	public void r�knaUtM�rkesRabattSomFinns(){
		t�m();
		l�ggTillVaror();
		Pengar expectedPrisUtanRabatt = skapaPengar("550");
		assertEquals(expectedPrisUtanRabatt, k.getPrisUtanRabatt());
		Vara v = new Vara("V5", new M�rke("ABC"), skapaPengar("50"));
		Rabatt r = new M�rkesRabatt("20 sek f�r V5", skapaPengar("20"), new M�rke("ABC"));
		RabattLista.sparaRabatt(v, r);
		Pengar expectedPrisMedRabatt = skapaPengar("530");
		k.r�knaUtRabatt();
		assertEquals(expectedPrisMedRabatt, k.getPrisMedRabatt());
	}
	
	@Test
	public void r�knaUtM�ngdRabattSomFinns(){
		t�m();
		l�ggTillVaror();
		Vara v = new Vara("V6", new M�rke("ABC"), skapaPengar("60"));
		k.l�ggTillVara(v);
		Rabatt r = new M�ngdRabatt("30 sek f�r 2 V6", skapaPengar("30"), 2);
		RabattLista.sparaRabatt(v, r);
		k.r�knaUtRabatt();
		Pengar expectedPrisMedRabatt = skapaPengar("580");
		assertEquals(expectedPrisMedRabatt, k.getPrisMedRabatt());
	}
}
