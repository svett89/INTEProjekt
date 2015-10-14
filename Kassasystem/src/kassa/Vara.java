package kassa;

public class Vara {
	private String testDescription;
	public Vara(String testDescription){
		this.testDescription = testDescription;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((testDescription == null) ? 0 : testDescription.hashCode());
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
		if (testDescription == null) {
			if (other.testDescription != null)
				return false;
		} else if (!testDescription.equals(other.testDescription))
			return false;
		return true;
	}
	
	public String toString(){
		return testDescription;
	}
}
