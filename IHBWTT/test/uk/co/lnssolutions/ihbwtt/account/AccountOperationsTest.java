package uk.co.lnssolutions.ihbwtt.account;

import junit.framework.TestCase;
import uk.co.lnssolutions.ihbwtt.rest.entities.AccountStatementReport;

public class AccountOperationsTest extends TestCase {

	public void testGetBalance() {
		AccountOperations ao = new AccountOperations();
		Double balance = ao.getBalance();
		assertTrue(balance != null);
		assertFalse(balance.isNaN());
	}

	public void testGetDistcountRate() {
		AccountOperations ao = new AccountOperations();
		Double balance = ao.getDiscountRate();
		assertTrue(balance != null);
		assertFalse(balance.isNaN());
	}

	public void testGetPointsBalance() {
		AccountOperations ao = new AccountOperations();
		Integer balance = ao.getPointsBalance();
		assertTrue(balance != null);
		assertTrue(balance.intValue() > 0);
	}
	
	public void testGetAccountStatement(){
		AccountOperations ao = new AccountOperations();
		
		// Assume the data loading and update concludes.
		// More detailed tests required.
		AccountStatementReport report = ao.getAccountStatement();
		
		assertTrue(report.getAccountStatement() != null);
	}
	

}
