package vara;

import kassa.Pengar;

public class Vara implements Comparable<Vara> {
	private String namn;
	private M�rke m�rke;
	private Pengar pris;
	

	public Vara(String namn, M�rke m�rke, Pengar pris) {
		if (pris.�rNegativBelopp()) {
			//Meddelande till exception
			throw new IllegalArgumentException();
		}
		this.namn = namn;
		this.m�rke = m�rke;
		this.pris = pris;
	}

	public String getNamn() {
		return namn;
	}
	
	public M�rke getM�rke() {
		return m�rke;
	}

	public Pengar getPris() {
		return pris;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m�rke == null) ? 0 : m�rke.hashCode());
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
		if (m�rke == null) {
			if (other.m�rke != null)
				return false;
		} else if (!m�rke.equals(other.m�rke))
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
