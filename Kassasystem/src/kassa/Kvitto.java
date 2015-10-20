package kassa;

import java.util.*;

public class Kvitto {
	private HashMap<Vara, Integer> varuMap = new HashMap<Vara, Integer>();
	
	public Kvitto(){
	}
	
	public Kvitto(Vara... varor){
		läggTillVaror(varor);
	}
	
	public HashMap<Vara, Integer> getVaruMap(){
		return varuMap;
	}
	
	public void läggTillVara(Vara v, int antal){
		if(varuMap.containsKey(v)){
			varuMap.put(v, (varuMap.get(v)+antal));
		}else{
			varuMap.put(v, antal);
		}
	}
	
	public void läggTillVara(Vara v){
		if(varuMap.containsKey(v)){
			varuMap.put(v, (varuMap.get(v)+1));
		}else{
			varuMap.put(v, 1);
		}
	}
	public void läggTillVaror(Vara ... varor){
		for(Vara v : varor){
			if(v != null && varuMap.containsKey(v)){
				varuMap.put(v, (varuMap.get(v)+1));
			}
			else if(v != null){
				varuMap.put(v, 1);
			}
		}
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
			if(v != null && varuMap.containsKey(v)){
				varuMap.put(v, (varuMap.get(v)+1));
			}
			else if(v != null){
				varuMap.put(v, 1);
			}
			
		}
	}
	
	public Set<Vara> getVaruSet(){
		return varuMap.keySet();
	}
	
	public void töm(){
		varuMap.clear();
	}
	
	public Integer taBortAllaAvEnVara(Vara v){
		return varuMap.remove(v);
	}
	
	public Pengar getPris(){
		Pengar totalPris = new Pengar(0);
		for (Vara v : varuMap.keySet()){
			for(int i = 0; i<varuMap.get(v); i++){
				Pengar prisFörVara = v.getPris();
				totalPris = totalPris.plus(prisFörVara);
			}
		}
		Pengar rabatt = Rabatt.räknaUtRabatter(this, totalPris);
		if(rabatt != null){
			totalPris = totalPris.minus(rabatt);
		}
		return totalPris;
	}
}
