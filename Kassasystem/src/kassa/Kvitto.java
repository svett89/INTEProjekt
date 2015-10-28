package kassa;

import java.math.BigDecimal;
import java.util.*;

import rabatt.RabattLista;
import vara.Vara;

public class Kvitto {
	//Map med varor som nyckel och antal som v�rde 
	private HashMap<Vara, Integer> varuMap = new HashMap<Vara, Integer>();
	
	//Map med varor samt den rabatt som g�ller f�r varan
	private HashMap<Vara, Pengar> varuRabattMap = new HashMap<Vara, Pengar>();
	
	public Kvitto(){
	}
	
	public Kvitto(Vara... varor){
		l�ggTillVaror(varor);
	}
	
	public HashMap<Vara, Integer> getVaruMap(){
		return varuMap;
	}
	
	public Set<Vara> getVaruSet(){
		return varuMap.keySet();
	}
	
	public int getTotalM�ngdVaror(){
		Collection<Integer> totalM�ngd = varuMap.values();
		int total = 0;
		for(Integer i : totalM�ngd){
			total += i;
		}
		return total;
	}
	
	public void l�ggTillVara(Vara v, int antal){
		if(v != null){
			if(antal <= 0){
				throw new IllegalArgumentException("Antal m�ste vara st�rre �n 0");
			}
			if(varuMap.containsKey(v)){
				varuMap.put(v, (varuMap.get(v)+antal));
			}else{
				varuMap.put(v, antal);
			}
		}else{
			throw new IllegalArgumentException("Vara �r null");
		}
	}
	
	public void l�ggTillVara(Vara v){
		l�ggTillVara(v, 1);
	}
	
	public void l�ggTillVaror(Vara ... varor){
		if(varor.length <= 0){
			throw new IllegalArgumentException("Arrayen med varor �r tom");
		}
		for(Vara v : varor){
			if(v != null && varuMap.containsKey(v)){
				varuMap.put(v, (varuMap.get(v)+1));
			}
			else if(v != null){
				varuMap.put(v, 1);
			}else{
				throw new IllegalArgumentException("Vara �r null");
			}
		}
	}
	
	public void l�ggTillVarorFr�nSamling(Collection<Vara> varuSamling){
		if(varuSamling == null){
			throw new IllegalArgumentException("Varusamling �r null");
		}else if(varuSamling.isEmpty()){
			throw new IllegalArgumentException("Varusamling �r tom");
		}else{
			for(Vara v : varuSamling){
				if(v == null){
					throw new IllegalArgumentException("Samlingen inneh�ller null-v�rden");
				}
			}
		}
		
		for(Vara v : varuSamling){
			l�ggTillVara(v);
		}
	}
	
	public boolean varaFinns(Vara v){
		return varuMap.containsKey(v) && varuMap.get(v) > 0;
	}
	
	public void t�mVaruMap(){
		varuMap.clear();
	}
	
	public boolean taBortAllaAvEnVara(Vara v){
		if(v == null){
			throw new IllegalArgumentException("Vara �r null");
		}else if(!varuMap.containsKey(v)){
			return false; 
		}else{
			varuMap.remove(v);
			return true;
		}
	}
	
	public boolean taBortVaror(Vara v, int antal){
		if(v == null){
			throw new IllegalArgumentException("Vara �r null");
		}else if(!varuMap.containsKey(v)){
			return false;
		}else{
			Integer nyttAntal = varuMap.get(v) - antal;
			if(nyttAntal <= 0){
				varuMap.remove(v);
			}else{
				varuMap.put(v, nyttAntal);
			}
			return true;
		}
		
	}
	
	public Pengar getPrisUtanRabatt(){
		Pengar totalPris = new Pengar(new BigDecimal("0"));
		for (Vara v : varuMap.keySet()){
			Pengar prisF�rVara = v.getPris();
			int antal = varuMap.get(v);
			Pengar totalPrisF�rVara = prisF�rVara.g�nger(antal);
			totalPris = totalPris.plus(totalPrisF�rVara);
		}
		System.out.println("");
		return totalPris;
	}
	
	public Pengar getPrisMedRabatt(){
		Pengar totalPris = getPrisUtanRabatt();
		r�knaUtRabatt();
		for (Vara v : varuRabattMap.keySet()){
			totalPris = totalPris.minus(varuRabattMap.get(v));
		}
		return totalPris;
	}
	
	public void r�knaUtRabatt(){
		varuRabattMap.clear();
		varuRabattMap = RabattLista.r�knaUtRabatt(this);
	}
	
	public String skapaUtskrift(){
		r�knaUtRabatt();
		String utskrift = "";
		Set<Vara> varuSet = varuMap.keySet();
		List<Vara> varuList = new ArrayList<Vara>();
		varuList.addAll(varuSet);
		Collections.sort(varuList);
		for(Vara v : varuList){
			String namn = v.getNamn();
			Integer antal = varuMap.get(v);
			if(varuRabattMap.containsKey(v)){
				Pengar rabatt = varuRabattMap.get(v);
				Pengar totalPris = v.getPris().g�nger(antal).minus(rabatt);
				utskrift += namn + "   " + antal + "   " + 
						totalPris + " (" + "-" + rabatt + ")" + "\n";
			}else{
				utskrift += namn + "   " + antal + "   " + v.getPris() + "\n";
						
			}
		}
		return utskrift;
	}
}
