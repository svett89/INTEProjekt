//package kassa;
//import static org.junit.Assert.*;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.Currency;
//
//import org.junit.Test;
//
//
//public class PengarTest {
//
//    BigDecimal belopp = new BigDecimal("3266.52");
//    Money testPengar = new Money(belopp,Currency.getInstance("EUR"),RoundingMode.UP);   
//  
//    
//    // Anv�nder h�r Currency klassens to.string f�r att se om 
//    @Test
//    public void testValuta() {
//        assertEquals("EUR",testPengar.getValuta().toString());
//    }
//
//    // Testar beloppet, Bigdecimal kan inte s�ttas in i expected parametern s� man f�r skapa den innan.
//    @Test
//    public void testBelopp() {
//        BigDecimal expected = new BigDecimal("3266.52");
//        assertEquals(testPengar.getBelopp(),expected);
//    }
//    
//    @Test
//    public void testAvrundingStil(){
//        assertEquals(testPengar.getAvrundningsMode(),RoundingMode.UP);
//    }
//    
//    @Test
//    public void testBelopp�verNoll(){
//        assertEquals(testPengar.isPositivBelopp(),true);
//    }
//    
//    @Test
//    public void testBeloppUnderNoll(){
//        assertEquals(testPengar.isNegativBelopp(),false);
//    }
//    
//    @Test
//    public void testNollPunkt(){
//        BigDecimal zero = new BigDecimal("0");
//        Money testAmountZero = new Money(zero,Currency.getInstance("SEK"),RoundingMode.UP);
//        assertEquals(testAmountZero.isNoll(),true);
//    }
//    
//    //Ej klar
//    @Test
//    public void testAdderaPengar(){
//        
//    }
//    
//    @Test
//    public void testSubtraheraPengar(){
//        
//    }
//    @Test
//    public void testMultipliceraPengar(){
//        
//    }
//    
//    @Test
//    public void testSummaAvPengar(){
//        
//    }
//    
//}
//
//
