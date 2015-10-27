package vara;

import kassa.Pengar;

public class Vara implements Comparable<Vara> {
	private String namn;
	private Märke märke;
	private Pengar pris;
	

	public Vara(String namn, Märke märke, Pengar pris) {
		if (pris.ärNegativBelopp()) {
			//Meddelande till exception
			throw new IllegalArgumentException();
		}
		this.namn = namn;
		this.märke = märke;
		this.pris = pris;
	}

	public String getNamn() {
		return namn;
	}
	
	public Märke getMärke() {
		return märke;
	}

	public Pengar getPris() {
		return pris;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((märke == null) ? 0 : märke.hashCode());
		result = prime * result + ((namn == null) ? 0 : namn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vara))
			return false;
		Vara other = (Vara) obj;
		if (märke == null) {
			if (other.märke != null)
				return false;
		} else if (!märke.equals(other.märke))
			return false;
		if (namn == null) {
			if (other.namn != null)
				return false;
		} else if (!namn.equals(other.namn))
			return false;
		return true;
	}

	@Override
	public int compareTo(Vara other) {
		return namn.compareTo(other.namn);
	}
	
	public String toString(){
		return namn;
	}

}
