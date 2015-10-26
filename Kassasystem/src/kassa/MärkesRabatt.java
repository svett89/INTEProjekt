package kassa;

import java.util.HashMap;

public class MärkesRabatt extends Rabatt{
	
	private Märke märke;
	
	public MärkesRabatt(String rabattNamn, Pengar pengar, Märke märke){
		super(rabattNamn, pengar);
		this.märke = märke;
	}
	
	public Märke getMärke(){
		return märke;
	}

	@Override
	public HashMap<Vara, Pengar> räknaUtRabatt(Vara v, int antalPåKvitto,
			HashMap<Vara, Pengar> rabattMap) {
		if(v == null || rabattMap == null){
			throw new IllegalArgumentException("Vara eller HashMap är null");
		}
		if(v.getMärke().equals(this.märke)){
			rabattMap.put(v, this.rabattAvdrag);
		}
		return rabattMap;
	}
	
}
