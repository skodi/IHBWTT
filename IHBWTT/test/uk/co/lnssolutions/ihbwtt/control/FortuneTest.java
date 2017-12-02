package uk.co.lnssolutions.ihbwtt.control;

import junit.framework.TestCase;

public class FortuneTest extends TestCase {

	public void testGetProp() {
		Fortune fortune = new Fortune();
		assertEquals(fortune.getProp().getProperty("USERNAME"), "epicken");
				
		// Check the API endpoints in place		
		assertEquals(fortune.getProp().getProperty("APING_URL"), "https://api.betfair.com/exchange/betting/");	
	
		// Check the other one
		assertEquals(fortune.getProp().getProperty("ACCOUNT_URL"), "https://api.betfair.com/exchange/account/");	
		
		
	}

}
