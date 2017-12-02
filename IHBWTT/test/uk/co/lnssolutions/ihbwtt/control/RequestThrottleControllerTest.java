package uk.co.lnssolutions.ihbwtt.control;

import junit.framework.TestCase;

public class RequestThrottleControllerTest extends TestCase {

	public void testOkToRequest() {
		int iteration = 0;
        RequestThrottleController throttler = RequestThrottleController.getInstance();
        while(!throttler.okToRequest() && iteration < 6)
        {
        	try {
        		if (iteration == 0) assertFalse(throttler.okToRequest());
        		if (iteration == 1) assertFalse(throttler.okToRequest());
        		if (iteration == 2) assertTrue(throttler.okToRequest());
        		if (iteration == 3) assertFalse(throttler.okToRequest());
        		if (iteration == 4) assertFalse(throttler.okToRequest());
        		if (iteration == 5) assertTrue(throttler.okToRequest());
        		
        	    Thread.sleep(300);
        	} catch(InterruptedException ex) {
        	    Thread.currentThread().interrupt();
        	}
        }
	}

}
