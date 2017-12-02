package uk.co.lnssolutions.ihbwtt.control;

import junit.framework.TestCase;

public class AccountStatementControllerTest extends TestCase {

	public void testUpdateLedger() {
		AccountStatementController asc = AccountStatementController.getInstance();
		assertTrue(asc.updateLedger());
	}

}
