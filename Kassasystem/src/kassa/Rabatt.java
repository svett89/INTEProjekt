package kassa;

import java.util.HashMap;

public abstract class Rabatt {
	
	protected String rabattNamn;
	protected Pengar rabattAvdrag;
	
	public Rabatt(String rabattNamn, Pengar pengar){
		this.rabattNamn = rabattNamn;
		this.rabattAvdrag = pengar;
	}
	
	public String getRabattNamn(){
		return rabattNamn;
	}
	
	public Pengar rabattAvdrag(){
		return rabattAvdrag;
	}
	
	public String toString(){
		return rabattNamn;
	}
	
	public abstract HashMap<Vara, Pengar> räknaUtRabatt(Vara v, int antalPåKvitto, HashMap<Vara, Pengar> rabattMap);
	
}
