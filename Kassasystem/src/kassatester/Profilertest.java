package kassatester;

import org.junit.Test;

import rabatt.MängdRabatt;
import rabatt.MärkesRabatt;
import rabatt.RabattLista;
import vara.*;

import java.util.*;
import java.math.*;

import kassa.*;

public class Profilertest {
	
	private Kvitto k = new Kvitto();
	Currency valuta = Currency.getInstance("SEK");
	
	private Vara skapaVara(String beskrivning, BigDecimal pris){
		Märke m = new Märke("ABC");
		Pengar varuPris = new Pengar(pris, valuta, RoundingMode.HALF_UP);
		return new Vara(beskrivning, m, varuPris); 
	}
	
	@Test
	public void simuleradAnvändning(){
		Vara v1 = skapaVara("V1", new BigDecimal("100"));
		k.läggTillVara(v1, 2);
		Vara v2 = skapaVara("V2", new BigDecimal("200"));
		k.läggTillVara(v2, 2);
		k.taBortVaror(v2, 1);
		Vara v3 = skapaVara("V3", new BigDecimal("300"));
		k.läggTillVaror(v3, v3, v1);
		MängdRabatt mr1 = new MängdRabatt("3 -40", new Pengar(new BigDecimal("40"), valuta, RoundingMode.HALF_UP), 3);
		RabattLista.sparaRabatt(v1, mr1);
		MärkesRabatt mr2 = new MärkesRabatt("ABC -50", new Pengar(new BigDecimal("50")), new Märke("ABC"));
		RabattLista.sparaRabatt(v3, mr2);
		System.out.println(k.skapaUtskrift());
		RabattLista.tömLista();
	}
	
	
}
