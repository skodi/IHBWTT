package uk.co.lnssolutions.ihbwtt.control;

import java.util.Date;

public class RequestThrottleController {

	
	
	private static RequestThrottleController instance;
	private static Date lastRequest;
	private static long delay;
	
	
    public static synchronized RequestThrottleController getInstance(){
        if(instance == null){
            instance = new RequestThrottleController();
        }
        return instance;
    }
	
	private RequestThrottleController(){
		lastRequest = new Date();
		delay = 1000;
	}
	
	public boolean okToRequest(){
		if (lastRequest.getTime() + delay < new Date().getTime())
		{
			lastRequest = new Date();
			return true;
		}
		return false;
	}
}
