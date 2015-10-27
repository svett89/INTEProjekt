package vara;

import kassa.Pengar;

public class Skor extends Vara {
	
	private String namn;
	private M�rke m�rke;
	private Pengar pris;
	
	public Skor(String namn, M�rke m�rke, Pengar pris) {
		super(namn, m�rke, pris);
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
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (getClass() != obj.getClass())
				return false;
			Skor other = (Skor) obj;
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
		
		@Override
		public int hashCode(){
			return super.hashCode();
		}
}
