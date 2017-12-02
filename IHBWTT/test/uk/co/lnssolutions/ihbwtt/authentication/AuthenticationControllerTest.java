package uk.co.lnssolutions.ihbwtt.control;

import junit.framework.TestCase;

public class AuthenticationControllerTest extends TestCase {

	public void testGetInstance() {
		AuthenticationController ac = AuthenticationController.getInstance();
		AuthenticationController ac2 = AuthenticationController.getInstance();
		
		assertFalse(ac.isLoggedIn());
		assertFalse(ac2.isLoggedIn());
		assertTrue(ac.login());
		assertTrue(ac2.isLoggedIn());
		
		
	}

	public void testGetToken() {
		AuthenticationController ac = AuthenticationController.getInstance();
		assertTrue(ac.login());
		String token = ac.getToken();
		assertTrue(((token != null) && (token != "PROBLEM")));
		
	}

}
