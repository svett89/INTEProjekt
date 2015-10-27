package kassatester;
import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import kassa.*;
import org.junit.Test;


public class Pengartest {

    // Skapa pengar med bara bigDecimal B
    private Pengar skapaPengar(BigDecimal b){
        return new Pengar(b);
    }
    
    
    BigDecimal belopp = new BigDecimal("326.125");
    
    Pengar testPengar = new Pengar(belopp,Currency.getInstance("USD"),RoundingMode.HALF_UP);   
  
    
    
    @Test
    public void testaSkapaSek(){
    	Pengar p1 = skapaPengar(BigDecimal.valueOf(2.0));
    	assertEquals(new BigDecimal("2.00"), p1.getBelopp());
    	assertEquals("SEK",p1.getValuta().toString());
    	assertEquals(RoundingMode.HALF_UP, p1.getAvrundningsMode());
    }
    
    
    @Test
    public void testaValuta() {      
    	assertEquals("USD",testPengar.getValuta().toString());
    }

    @Test
    public void testaBelopp() {
        System.out.println(testPengar.toString());
        assertEquals(new BigDecimal("326.13"),testPengar.getBelopp());
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
    
    @Test
    public void testAdderaPengar(){
        Pengar p1 = skapaPengar(BigDecimal.valueOf(2.0));
        Pengar p2 = skapaPengar(BigDecimal.valueOf(3.0));
        
        assertEquals(new BigDecimal("5.00"), p1.plus(p2).getBelopp());
        
    }
    
    @Test
    public void testaSubtraheraPengar(){
        Pengar p1 = skapaPengar(BigDecimal.valueOf(5.0));
        Pengar p2 = skapaPengar(BigDecimal.valueOf(3.0));
       
        
        assertEquals(new BigDecimal("2.00"), p1.minus(p2).getBelopp() );
    }
    @Test
    public void testaMultipliceraPengar(){
        Pengar p1 = skapaPengar(BigDecimal.valueOf(5.0));
        int faktor = 3;
        assertEquals(new BigDecimal("15.00"), p1.gånger(faktor).getBelopp());
        
    }
    
    @Test
    public void testaDivideraPengar(){
        Pengar p1 = skapaPengar(BigDecimal.valueOf(1.0));
        int faktor = 3;
        assertEquals(new BigDecimal("0.33"), p1.delaMedInt(faktor).getBelopp());
    }
  
    
}


