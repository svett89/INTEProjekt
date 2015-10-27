package rabatt;

import java.util.HashMap;

import kassa.Pengar;
import vara.M�rke;
import vara.Vara;

public class M�rkesRabatt extends Rabatt{
	
	private M�rke m�rke;
	
	public M�rkesRabatt(String rabattNamn, Pengar pengar, M�rke m�rke){
		super(rabattNamn, pengar);
		this.m�rke = m�rke;
	}
	
	public M�rke getM�rke(){
		return m�rke;
	}

	@Override
	public HashMap<Vara, Pengar> r�knaUtRabatt(Vara v, int antalP�Kvitto,
			HashMap<Vara, Pengar> rabattMap) {
		if(v == null || rabattMap == null){
			throw new IllegalArgumentException("Vara eller HashMap �r null");
		}
		if(v.getM�rke().equals(this.m�rke)){
			rabattMap.put(v, this.rabattAvdrag);
		}
		return rabattMap;
	}
	
}
