package kassa;

import java.util.HashMap;
import java.util.Map.Entry;

public final class Rabatt {
	
	private static HashMap<Vara, HashMap<Integer, Pengar>> rabattMap = new HashMap<Vara, HashMap<Integer, Pengar>>();
	
	private Rabatt(){
	
	}
	
	public static void addRabatt(Vara vara, HashMap<Integer, Pengar> antalOchRabattMap) {
		rabattMap.put(vara, antalOchRabattMap);
	}

	public static Pengar räknaUtRabatter(Kvitto kvitto, Pengar prisInnanRabatter) {
		Pengar rabattIPengar = null;
		HashMap<Vara, Integer> varuMap = kvitto.getVaruMap();
		for(Entry<Vara, Integer> e1 : varuMap.entrySet()){
			Vara v = e1.getKey();
			if(rabattMap.containsKey(v)){
				for(Entry<Integer, Pengar> e2 : rabattMap.get(v).entrySet()){
					Integer störstAntalRabatt = 0;
					if(e1.getValue() >= e2.getKey() && e1.getValue() > störstAntalRabatt){
						störstAntalRabatt = e2.getKey();
						rabattIPengar = e2.getValue();
					}
				}
			}
		}
		return rabattIPengar;
	}
	
	public static Pengar getRabatter(Vara v, Integer antal){
		if(rabattMap.containsKey(v)){
			return rabattMap.get(v).get(antal);
		}else{
			return new Pengar(0);
		}
		
	}

}
