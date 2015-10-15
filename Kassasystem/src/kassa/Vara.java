package kassa;
import java.math.*;

public class Vara {
	private String namn;
	//�ndra till pengar
	private Pengar pris;

	public Vara(String namn, Pengar pris) {
		//Kollar inte maxv�rde! Fixa!
		if (pris.�rNegativBelopp()) {
			throw new IllegalArgumentException();
		}
		this.namn = namn;
		this.pris = pris;
	}

	public String getNamn() {
		return namn;
	}
	//�ndra till pengar
	public Pengar getPris() {
		return pris;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((namn == null) ? 0 : namn.hashCode());
		result = prime * result + ((pris == null) ? 0 : pris.hashCode());
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
		if (namn == null) {
			if (other.namn != null)
				return false;
		} else if (!namn.equals(other.namn))
			return false;
		if (pris == null) {
			if (other.pris != null)
				return false;
		} else if (!pris.equals(other.pris))
			return false;
		return true;
	}

}
