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
    public void g�nger(Pengar p2) {
        // TODO Auto-generated method stub
        
    }
    public void minus(Pengar p2) {
        // TODO Auto-generated method stub
        
    }
    public void plus(Pengar p2) {
        // TODO Auto-generated method stub
        
    }
   
    
    
}
