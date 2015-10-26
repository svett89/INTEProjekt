package kassa;

public class M�ngdRabatt extends Rabatt {
	
	private int antal;
	
	public M�ngdRabatt(String rabattNamn, Pengar pengar, int antal){
		super(rabattNamn, pengar);
		this.antal = antal;
	}
	
	public int getAntal(){
		return antal;
	}
	
}
