package kassa;

public class MärkesRabatt extends Rabatt{
	
	private Märke märke;
	
	public MärkesRabatt(String rabattNamn, Pengar pengar, Märke märke){
		super(rabattNamn, pengar);
		this.märke = märke;
	}
	
	public Märke getMärke(){
		return märke;
	}
	
}
