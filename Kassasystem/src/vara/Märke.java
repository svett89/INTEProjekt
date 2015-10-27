package vara;

public class Märke {
	private String namn;
	
	public Märke(String namn){
		this.namn = namn;
	}
	
	public String getNamn(){
		return namn;
	}
	
	public String toString(){
		return namn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((namn == null) ? 0 : namn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Märke))
			return false;
		Märke other = (Märke) obj;
		if (namn == null) {
			if (other.namn != null)
				return false;
		} else if (!namn.equals(other.namn))
			return false;
		return true;
	}
}
