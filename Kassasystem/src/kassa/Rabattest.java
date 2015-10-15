package kassa;

import org.junit.Test;
import static org.junit.Assert.*;


public class Rabattest {
	
	
	@Test
	public void testaHämtaRabatter(){
		Rabatt.getRabatter();
	}
	
	@Test
	public void testaRabbaterEjNull(){
		assertNotNull(Rabatt.getRabatter());
	}
	
//	@Test
//	public void testaSparaRabatt(){
//	Rabatt.sparaRabatt(Vara vara, int antal, int rabatt) 
//	}
//	
	
}
