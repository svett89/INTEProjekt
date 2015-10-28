package kassatester;

import org.junit.Test;

import rabatt.M�ngdRabatt;
import rabatt.M�rkesRabatt;
import rabatt.RabattLista;
import vara.*;

import java.util.*;
import java.math.*;

import kassa.*;

public class Profilertest {
	
	private Kvitto k = new Kvitto();
	Currency valuta = Currency.getInstance("SEK");
	
	private Vara skapaVara(String beskrivning, BigDecimal pris){
		M�rke m = new M�rke("ABC");
		Pengar varuPris = new Pengar(pris, valuta, RoundingMode.HALF_UP);
		return new Vara(beskrivning, m, varuPris); 
	}
	
	@Test
	public void simuleradAnv�ndning(){
		Vara v1 = skapaVara("V1", new BigDecimal("100"));
		k.l�ggTillVara(v1, 2);
		Vara v2 = skapaVara("V2", new BigDecimal("200"));
		k.l�ggTillVara(v2, 2);
		k.taBortVaror(v2, 1);
		Vara v3 = skapaVara("V3", new BigDecimal("300"));
		k.l�ggTillVaror(v3, v3, v1);
		M�ngdRabatt mr1 = new M�ngdRabatt("3 -40", new Pengar(new BigDecimal("40"), valuta, RoundingMode.HALF_UP), 3);
		RabattLista.sparaRabatt(v1, mr1);
		M�rkesRabatt mr2 = new M�rkesRabatt("ABC -50", new Pengar(new BigDecimal("50")), new M�rke("ABC"));
		RabattLista.sparaRabatt(v3, mr2);
		System.out.println(k.skapaUtskrift());
		RabattLista.t�mLista();
	}
	
	
}
