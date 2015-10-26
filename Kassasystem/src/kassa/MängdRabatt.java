package kassa;

import java.util.HashMap;

public class M�ngdRabatt extends Rabatt {
	
	private int antal;
	
	public M�ngdRabatt(String rabattNamn, Pengar pengar, int antal){
		super(rabattNamn, pengar);
		this.antal = antal;
	}
	
	public int getAntal(){
		return antal;
	}

	@Override
	public HashMap<Vara, Pengar> r�knaUtRabatt(Vara v, int antalP�Kvitto, HashMap<Vara, Pengar> rabattMap) {
		if(v == null || rabattMap == null){
			throw new IllegalArgumentException("Vara eller HashMap �r null");
		}else if(antalP�Kvitto <= 0){
			throw new IllegalArgumentException("Antal p� kvitto angett som 0 eller l�gre");
		}
		if(antalP�Kvitto >= this.antal){
			rabattMap.put(v, this.rabattAvdrag);
		}
		return rabattMap;
	}
	
	
	
}
