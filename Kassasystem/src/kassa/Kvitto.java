package kassa;

import java.util.*;

public class Kvitto {
	private HashMap<Vara, Integer> varuMap = new HashMap<Vara, Integer>();
	
	public Kvitto(){
		//Tom konstruktor, b�r beh�llas?
	}
	public Kvitto(Vara... varor){
		for(Vara v : varor){
			varuMap.put(v, (varuMap.get(v)+1));
		}
	}
	
	public void l�ggTillVara(Vara v, int antal){
		varuMap.put(v, (varuMap.get(v)+antal));
	}
	
	public void l�ggTillVara(Vara v){
		varuMap.put(v, (varuMap.get(v)+1));
	}
	
	public boolean varaFinns(Vara v){
		return varuMap.containsKey(v) && varuMap.get(v) > 0;
	}
	
	public int getTotalM�ngdVaror(){
		Collection<Integer> totalM�ngd = varuMap.values();
		int total = 0;
		for(Integer i : totalM�ngd){
			total += i;
		}
		return total;
	}
	
	public void l�ggTillVarorFr�nSamling(Collection<Vara> varuSamling){
		for(Vara v : varuSamling){
			varuMap.put(v, (varuMap.get(v)+1));
		}
	}
	
	public Set<Vara> getVaruSet(){
		return varuMap.keySet();
	}
}
