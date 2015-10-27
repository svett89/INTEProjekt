package kassa;

public class Mössa extends Vara {
		
	private String namn;
	private Märke märke;
	private Pengar pris;
	
	public Mössa(String namn, Märke märke, Pengar pris) {
		super(namn, märke, pris);
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
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (getClass() != obj.getClass())
				return false;
			Mössa other = (Mössa) obj;
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
