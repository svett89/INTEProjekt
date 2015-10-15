package kassa;

public class Vara {
	private String namn;
	private double pris;

	public Vara(String namn, double pris) {

		if (pris < 0 || pris > 9999) {
			throw new IllegalArgumentException();
		}
		this.namn = namn;
		this.pris = pris;
	}

	public String getNamn() {
		return namn;
	}

	public double getPris() {
		return pris;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((namn == null) ? 0 : namn.hashCode());
		long temp;
		temp = Double.doubleToLongBits(pris);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vara other = (Vara) obj;
		if (namn == null) {
			if (other.namn != null)
				return false;
		} else if (!namn.equals(other.namn))
			return false;
		if (Double.doubleToLongBits(pris) != Double.doubleToLongBits(other.pris))
			return false;
		return true;
	}
}
