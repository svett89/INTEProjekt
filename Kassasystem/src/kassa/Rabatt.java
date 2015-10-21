package kassa;

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
	
}
