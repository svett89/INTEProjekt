package kassa;
import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import org.junit.Test;


public class Pengartest {

    // Skapa pengar med bara bigDecimal B
    private Pengar skapaPengar(BigDecimal b){
        return new Pengar(b);
    }
    
    
    BigDecimal belopp = new BigDecimal("326.125");
    
    Pengar testPengar = new Pengar(belopp,Currency.getInstance("EUR"),RoundingMode.HALF_UP);   
  
    
    // Använder här Currency klassens to.string för att se om valutan man skickar in stämmer, PASS
    @Test
    public void testaValuta() {      
    	assertEquals("EUR",testPengar.getValuta().toString());
    }

    // Testar beloppet, Bigdecimal kan inte sättas in i expected parametern så man får skapa den innan.
    @Test
    public void testaBelopp() {
        BigDecimal expected = new BigDecimal("326.13");
        assertEquals(expected,testPengar.getBelopp());
    }
    
    @Test
    public void testaAvrundingStil(){
        assertEquals(RoundingMode.HALF_UP, testPengar.getAvrundningsMode());
    }
    
    @Test
    public void testaBeloppÖverNoll(){
        assertEquals(true, testPengar.ärPositivBelopp());
    }
    
    @Test
    public void testaBeloppUnderNoll(){
        assertEquals(false, testPengar.ärNegativBelopp());
    }
    
    @Test
    public void testaNollPunkt(){
        BigDecimal noll = new BigDecimal("0");
        Pengar testaPengarNoll = new Pengar(noll,Currency.getInstance("SEK"),RoundingMode.UP);
        assertEquals(true, testaPengarNoll.ärNoll());
    }
    
    //Ej klar
    @Test
    public void testAdderaPengar(){
        Pengar p1 = skapaPengar(BigDecimal.valueOf(2.0));
        Pengar p2 = skapaPengar(BigDecimal.valueOf(3.0));
        
        assertEquals(new BigDecimal("5.0"), p1.plus(p2).getBelopp());
        
    }
    
    @Test
    public void testSubtraheraPengar(){
        Pengar p1 = skapaPengar(BigDecimal.valueOf(5.0));
        Pengar p2 = skapaPengar(BigDecimal.valueOf(3.0));
       
        //p1.minus(p2).getBelopp();

        assertEquals(new BigDecimal("2.0"), p1.minus(p2).getBelopp() );
    }
    @Test
    public void testMultipliceraPengar(){
        Pengar p1 = skapaPengar(BigDecimal.valueOf(5.0));
        int faktor = 3;
        assertEquals(new BigDecimal("15.0"), p1.gånger(faktor).getBelopp());
        
    }
  
    
}


