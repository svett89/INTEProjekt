package kassa;

import java.util.HashMap;
import java.util.Set;
import java.util.Map.*;
import java.util.Map;

public class RabattLista {

	private static Map<Vara, Rabatt> rabatter = new HashMap<Vara,Rabatt>();
	
	public RabattLista(){
		
	}
	
	public static Map<Vara, Rabatt> getRabatter(){
		return rabatter;
	}
	
	public static boolean sparaRabatt(Vara vara, Rabatt rabatt){
		if(!rabatter.containsKey(vara)){
			rabatter.put(vara, rabatt);
			return true;
		} else {
			return false;
		}
	}
	
	public static void t�mLista(){
		rabatter.clear();
	}
	
	public static int storlek(){
		return rabatter.size();
	}
	
	public static boolean taBortRabatt(Vara v){
		if(rabatter.containsKey(v)){
			rabatter.remove(v);
			return true;
		}else{
			return false;
		}
	}
	
	public static HashMap<Vara, Pengar> r�knaUtRabatt(Kvitto k){
		HashMap<Vara, Pengar> rabattMap = new HashMap<Vara, Pengar>();
		HashMap<Vara, Integer> varuMap = k.getVaruMap();
		Set<Entry<Vara, Integer>> varuSet = varuMap.entrySet();
		for(Entry<Vara, Integer> e : varuSet){
			if(rabatter.containsKey(e.getKey())){
				Vara v = e.getKey();
				Integer antal = e.getValue();
				Rabatt r = rabatter.get(v);
				if(r instanceof M�ngdRabatt){
					int antalF�rRabatt = ((M�ngdRabatt) r).getAntal();
					int antalP�Kvitto = antal;
					if(antalP�Kvitto >= antalF�rRabatt){
						rabattMap.put(v, rabatter.get(v).rabattAvdrag());
					}
					
				}else if(r instanceof M�rkesRabatt){
					M�rke m�rkeF�rRabatt = ((M�rkesRabatt) r).getM�rke();
					M�rke m�rkeP�Kvitto = v.getM�rke();
					if(m�rkeP�Kvitto.equals(m�rkeF�rRabatt)){
						rabattMap.put(v, rabatter.get(v).rabattAvdrag());
					}
				}
			}
		}
		return rabattMap;
	}
	
}
