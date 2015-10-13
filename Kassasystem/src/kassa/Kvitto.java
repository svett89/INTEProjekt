package kassa;

import java.util.*;

public class Kvitto {
	private HashMap<Vara, Integer> varuMap = new HashMap<Vara, Integer>();
	
	public Kvitto(){
		//Tom konstruktor, bör behållas?
	}
	public Kvitto(Vara... varor){
		for(Vara v : varor){
			varuMap.put(v, (varuMap.get(v)+1));
		}
	}
	
	public void läggTillVara(Vara v, int antal){
		varuMap.put(v, (varuMap.get(v)+antal));
	}
	
	public void läggTillVara(Vara v){
		varuMap.put(v, (varuMap.get(v)+1));
	}
	
	public boolean varaFinns(Vara v){
		return varuMap.containsKey(v) && varuMap.get(v) > 0;
	}
	
	public int getTotalMängdVaror(){
		Collection<Integer> totalMängd = varuMap.values();
		int total = 0;
		for(Integer i : totalMängd){
			total += i;
		}
		return total;
	}
	
	public void läggTillVarorFrånSamling(Collection<Vara> varuSamling){
		for(Vara v : varuSamling){
			varuMap.put(v, (varuMap.get(v)+1));
		}
	}
	
	public Set<Vara> getVaruSet(){
		return varuMap.keySet();
	}
}
