package kassa;

public class Jacka extends Vara {
	
	private String namn;
	private Märke märke;
	private int mängd;
	private Pengar pris;
	
		public Jacka(String namn, Märke märke, int mängd, Pengar pris) {
			super(namn, märke, mängd, pris);
			this.namn = namn;
			this.märke = märke;
			this.mängd = mängd;
			this.pris = pris;
		}

		public String getNamn() {
			return namn;
		}
		
		public Märke getMärke() {
			return märke;
		}
		
		public int getMängd() {
			return mängd;
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
