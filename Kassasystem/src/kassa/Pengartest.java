package kassa;
import static org.junit.Assert.*;
<<<<<<< HEAD

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import org.junit.Test;


public class PengarTest {

    BigDecimal belopp = new BigDecimal("3266.52");
    Money testPengar = new Money(belopp,Currency.getInstance("EUR"),RoundingMode.UP);   
  
    
    // Anv�nder h�r Currency klassens to.string f�r att se om 
    @Test
    public void testValuta() {
=======
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
>>>>>>> refs/remotes/origin/Kvitto+Kvittotest
        assertEquals("EUR",testPengar.getValuta().toString());
    }

    // Testar beloppet, Bigdecimal kan inte s�ttas in i expected parametern s� man f�r skapa den innan.
    @Test
<<<<<<< HEAD
    public void testBelopp() {
        BigDecimal expected = new BigDecimal("3266.52");
        assertEquals(testPengar.getBelopp(),expected);
    }
    
    @Test
    public void testAvrundingStil(){
        assertEquals(testPengar.getAvrundningsMode(),RoundingMode.UP);
    }
    
    @Test
    public void testBelopp�verNoll(){
        assertEquals(testPengar.isPositivBelopp(),true);
    }
    
    @Test
    public void testBeloppUnderNoll(){
        assertEquals(testPengar.isNegativBelopp(),false);
    }
    
    @Test
    public void testNollPunkt(){
        BigDecimal zero = new BigDecimal("0");
        Money testAmountZero = new Money(zero,Currency.getInstance("SEK"),RoundingMode.UP);
        assertEquals(testAmountZero.isNoll(),true);
=======
    public void testaBelopp() {
        BigDecimal expected = new BigDecimal("3266.52");
        assertEquals(expected,testPengar.getBelopp());
    }
    
    @Test
    public void testaAvrundingStil(){
        assertEquals(RoundingMode.UP, testPengar.getAvrundningsMode());
    }
    
    @Test
    public void testaBelopp�verNoll(){
        assertEquals(true, testPengar.�rPositivBelopp());
    }
    
    @Test
    public void testaBeloppUnderNoll(){
        assertEquals(false, testPengar.�rNegativBelopp());
    }
    
    @Test
    public void testaNollPunkt(){
        BigDecimal noll = new BigDecimal("0");
        Pengar testaPengarNoll = new Pengar(noll,Currency.getInstance("SEK"),RoundingMode.UP);
        assertEquals(true, testaPengarNoll.�rNoll());
>>>>>>> refs/remotes/origin/Kvitto+Kvittotest
    }
    
    //Ej klar
    @Test
    public void testAdderaPengar(){
<<<<<<< HEAD
=======
        Pengar p1 = skapaPengar(BigDecimal.valueOf(2.0));
        Pengar p2 = skapaPengar(BigDecimal.valueOf(3.0));
        
        assertEquals(new BigDecimal("5.0"), p1.plus(p2).getBelopp());
>>>>>>> refs/remotes/origin/Kvitto+Kvittotest
        
    }
    
    @Test
    public void testSubtraheraPengar(){
<<<<<<< HEAD
        
    }
    @Test
    public void testMultipliceraPengar(){
        
    }
    
    @Test
    public void testSummaAvPengar(){
        
    }
=======
        Pengar p1 = skapaPengar(BigDecimal.valueOf(5.0));
        Pengar p2 = skapaPengar(BigDecimal.valueOf(3.0));
       
        //p1.minus(p2).getBelopp();

        assertEquals(new BigDecimal("2.0"), p1.minus(p2).getBelopp() );
    }
    @Test
    public void testMultipliceraPengar(){
        Pengar p1 = skapaPengar(BigDecimal.valueOf(5.0));
        int faktor = 3;
        assertEquals(new BigDecimal("15.0"), p1.g�nger(faktor).getBelopp());
        
    }
  
>>>>>>> refs/remotes/origin/Kvitto+Kvittotest
    
}


