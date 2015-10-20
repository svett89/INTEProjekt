package kassa;

public class Jacka extends Vara {
	
	private String namn;
	private M�rke m�rke;
	private int m�ngd;
	private Pengar pris;
	
		public Jacka(String namn, M�rke m�rke, int m�ngd, Pengar pris) {
			super(namn, m�rke, m�ngd, pris);
			this.namn = namn;
			this.m�rke = m�rke;
			this.m�ngd = m�ngd;
			this.pris = pris;
		}

		public String getNamn() {
			return namn;
		}
		
		public M�rke getM�rke() {
			return m�rke;
		}
		
		public int getM�ngd() {
			return m�ngd;
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
			Jacka other = (Jacka) obj;
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
