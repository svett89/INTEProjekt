package kassa;

import java.math.BigDecimal;
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
    public Pengar(BigDecimal belopp){
        this.belopp=belopp;
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

    public boolean �rNegativBelopp() {
        return belopp.compareTo(BigDecimal.ZERO)< 0;
    }

    public boolean �rPositivBelopp() {
        return belopp.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean �rNoll() {
        return belopp.compareTo(BigDecimal.ZERO) == 0;
    }
    public Pengar g�nger(int g�nger) {
        BigDecimal faktor = new BigDecimal(g�nger);
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
}
