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
    
    
    BigDecimal belopp = new BigDecimal("3266.52");
    Pengar testPengar = new Pengar(belopp,Currency.getInstance("EUR"),RoundingMode.UP);   
  
    
    // Anv�nder h�r Currency klassens to.string f�r att se om valutan man skickar in st�mmer, PASS
    @Test
    public void testaValuta() {
        assertEquals("EUR",testPengar.getValuta().toString());
    }

    // Testar beloppet, Bigdecimal kan inte s�ttas in i expected parametern s� man f�r skapa den innan.
    @Test
    public void testaBelopp() {
        BigDecimal expected = new BigDecimal("3266.52");
        assertEquals(testPengar.getBelopp(),expected);
    }
    
    @Test
    public void testaAvrundingStil(){
        assertEquals(testPengar.getAvrundningsMode(),RoundingMode.UP);
    }
    
    @Test
    public void testaBelopp�verNoll(){
        assertEquals(testPengar.�rPositivBelopp(),true);
    }
    
    @Test
    public void testaBeloppUnderNoll(){
        assertEquals(testPengar.�rNegativBelopp(),false);
    }
    
    @Test
    public void testaNollPunkt(){
        BigDecimal noll = new BigDecimal("0");
        Pengar testaPengarNoll = new Pengar(noll,Currency.getInstance("SEK"),RoundingMode.UP);
        assertEquals(testaPengarNoll.�rNoll(),true);
    }
    
    //Ej klar
    @Test
    public void testAdderaPengar(){
        Pengar p1 = skapaPengar(BigDecimal.valueOf(2.0));
        Pengar p2 = skapaPengar(BigDecimal.valueOf(3.0));
        
        p1.plus(p2);
        BigDecimal expected = new BigDecimal("5.0");
        assertEquals(p1.getBelopp(), expected);
        
    }
    
    @Test
    public void testSubtraheraPengar(){
        Pengar p1 = skapaPengar(BigDecimal.valueOf(5.0));
        Pengar p2 = skapaPengar(BigDecimal.valueOf(3.0));
       
        p1.minus(p2);
        BigDecimal expected = new BigDecimal("2.0");
        assertEquals(p1.getBelopp(), expected);
    }
    @Test
    public void testMultipliceraPengar(){
        Pengar p1 = skapaPengar(BigDecimal.valueOf(5.0));
        Pengar p2 = skapaPengar(BigDecimal.valueOf(3.0));
        
        p1.g�nger(p2);
       
        BigDecimal expected = new BigDecimal("15.0");
        assertEquals(p1.getBelopp(), expected);
        
    }
  
}


