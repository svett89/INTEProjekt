package kassa;

public class MängdRabatt extends Rabatt {
	
	private int antal;
	
	public MängdRabatt(String rabattNamn, Pengar pengar, int antal){
		super(rabattNamn, pengar);
		this.antal = antal;
	}
	
	public int getAntal(){
		return antal;
	}
	
}
