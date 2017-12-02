package uk.co.lnssolutions.ihbwtt.control;

import java.util.Date;

import junit.framework.TestCase;

public class AccountDBControllerTest extends TestCase {

	public void testConnect() {
		AccountDBController adc = AccountDBController.getInstance();
		assertTrue(adc.connect());
	}

	public void testDisconnect() {
		AccountDBController adc = AccountDBController.getInstance();
		assertTrue(adc.disconnect());
		
	}

	public void testGetTranscationCount() {
		AccountDBController adc = AccountDBController.getInstance();
		assertTrue(adc.connect());
		int rowCount = adc.getTranscationCount();
		assertTrue(rowCount == 47);
	}

	public void testGetLastStatementItemDate(){
		AccountDBController adc = AccountDBController.getInstance();
		assertTrue(adc.connect());
		Date d = adc.getLastStatementItemDate();
		assertTrue(d != null);
		assertTrue(d.after(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000)));
	}
	
}
