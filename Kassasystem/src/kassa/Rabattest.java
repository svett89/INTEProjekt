package kassa;

import org.junit.Test;
import static org.junit.Assert.*;


public class Rabattest {
	
	Kvitto kvitto = new Kvitto();
	
	@Test
	public void testaRabattEjNull(){
		
		assertTrue(Rabatt.getRabatt(kvitto) != null);
		
	}
	
	@Test
	public void testaRabattÄrRabatt(){
		
		assertTrue(Rabatt.getRabatt(kvitto) instanceof Rabatt);
	}
	
	
}
