package kassa;

public class M�rkesRabatt extends Rabatt{
	
	private M�rke m�rke;
	
	public M�rkesRabatt(String rabattNamn, Pengar pengar, M�rke m�rke){
		super(rabattNamn, pengar);
		this.m�rke = m�rke;
	}
	
	public M�rke getM�rke(){
		return m�rke;
	}
	
}
