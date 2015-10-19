package kassa;

import java.util.Currency;
import java.math.*;

public class Pengar {

    private BigDecimal belopp;
    private Currency valuta;
    private RoundingMode avrundningsMode;
    
    
    public Pengar(BigDecimal belopp, Currency valuta, RoundingMode avrundningsMode){
        
        this.belopp = belopp;
        this.valuta = valuta;
        this.avrundningsMode = avrundningsMode;
  
    }
    
    //Skapar SEK automatiskt
    public Pengar(int belopp){
        BigDecimal bd = new BigDecimal(belopp);
    	this.belopp = bd;
        this.valuta = Currency.getInstance("SEK");
        this.avrundningsMode = RoundingMode.HALF_EVEN;
    }
    
    public Pengar(BigDecimal belopp){
    	this.belopp = belopp;
    	this.valuta = Currency.getInstance("SEK");
        this.avrundningsMode = RoundingMode.HALF_EVEN;
    }
    
    public void setBelopp(BigDecimal belopp){
    	this.belopp = belopp;
    }
    
	public BigDecimal getBelopp(){
        return belopp;
    }
    
    public Currency getValuta(){
        return valuta;
    }
    
    public RoundingMode getAvrundningsMode(){
        return avrundningsMode;
    }

    public boolean ärNegativBelopp() {
        return belopp.compareTo(BigDecimal.ZERO)< 0;
    }

    public boolean ärPositivBelopp() {
        return belopp.compareTo(BigDecimal.ZERO) > 0;
    }

	public boolean ärNoll() {
        return belopp.compareTo(BigDecimal.ZERO) == 0;
    }
    public Pengar gånger(int gånger) {
        BigDecimal faktor = new BigDecimal(gånger);
        return new Pengar(belopp.multiply(faktor));
    }
    public Pengar minus(Pengar p2) {
        return new Pengar(belopp.subtract(p2.belopp));
        
    }
    public Pengar plus(Pengar p2) {
        return new Pengar(belopp.add(p2.belopp));
    }
  
    public String toString(){
        return belopp.toPlainString();
      }
    
    @Override
   	public int hashCode() {
   		final int prime = 31;
   		int result = 1;
   		result = prime * result + ((belopp == null) ? 0 : belopp.hashCode());
   		result = prime * result + ((valuta == null) ? 0 : valuta.hashCode());
   		return result;
   	}
   	@Override
   	public boolean equals(Object obj) {
   		if (this == obj)
   			return true;
   		if (obj == null)
   			return false;
   		if (!(obj instanceof Pengar))
   			return false;
   		Pengar other = (Pengar) obj;
   		if (belopp == null) {
   			if (other.belopp != null)
   				return false;
   		} else if (belopp.compareTo(other.belopp) != 0)
   			return false;
   		if (valuta == null) {
   			if (other.valuta != null)
   				return false;
   		} else if (!valuta.equals(other.valuta))
   			return false;
   		return true;
   	}
}
