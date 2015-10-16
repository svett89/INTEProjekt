package kassa;

public class Strumpor extends Vara {
	
	private String namn;
	private Pengar pris;
	
		public Strumpor(String namn, Pengar pris) {
			super(namn, pris);
			this.namn = namn;
			this.pris = pris;
		}

		public String getNamn() {
			return namn;
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
			Strumpor other = (Strumpor) obj;
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
