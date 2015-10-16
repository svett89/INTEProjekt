package kassa;

import java.math.BigDecimal;
import java.util.HashMap;

public final class Rabatt {
	
	private static HashMap<Vara, HashMap<Integer, Pengar>> rabattMap = new HashMap<Vara, HashMap<Integer, Pengar>>();
	
	private Rabatt(){
	
	}
	
	public static void addRabatt(Vara vara, HashMap<Integer, Pengar> antalOchRabattMap) {
		rabattMap.put(vara, antalOchRabattMap);
	}

	public static void räknaUtRabatter(Kvitto kvitto) {
		
	}

}
