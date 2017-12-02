package uk.co.lnssolutions.ihbwtt.login;

import junit.framework.TestCase;
import uk.co.lnssolutions.ihbwtt.control.Fortune;

public class AuthenticationTest extends TestCase {

	private Fortune fortune;
	
	public void setup(){
		fortune = new Fortune();
	}
	
	
	public void testLogin() {
		Authentication a = new Authentication();
		String token = a.login(fortune.getProp().getProperty("USERNAME"), fortune.getProp().getProperty("PASSWORD"));
		assertTrue(token != null);
	}

	public void testKeepAlive() {
		Authentication a = new Authentication();
		String token = a.keepAlive();
		assertTrue(token != "PROBLEM");
	}

	
	public void testLogout() {
		Authentication a = new Authentication();
		String token = a.logout();
		assertTrue(token != "PROBLEM");
		
	}

}
