package kassa;

import java.util.HashMap;

public class MängdRabatt extends Rabatt {
	
	private int antal;
	
	public MängdRabatt(String rabattNamn, Pengar pengar, int antal){
		super(rabattNamn, pengar);
		this.antal = antal;
	}
	
	public int getAntal(){
		return antal;
	}

	@Override
	public HashMap<Vara, Pengar> räknaUtRabatt(Vara v, int antalPåKvitto, HashMap<Vara, Pengar> rabattMap) {
		if(v == null || rabattMap == null){
			throw new IllegalArgumentException("Vara eller HashMap är null");
		}else if(antalPåKvitto <= 0){
			throw new IllegalArgumentException("Antal på kvitto angett som 0 eller lägre");
		}
		if(antalPåKvitto >= this.antal){
			rabattMap.put(v, this.rabattAvdrag);
		}
		return rabattMap;
	}
	
	
	
}
