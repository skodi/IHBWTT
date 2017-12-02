package uk.co.lnssolutions.ihbwtt.markets;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;



import uk.co.lnssolutions.ihbwtt.control.AuthenticationController;
import uk.co.lnssolutions.ihbwtt.control.Fortune;
import uk.co.lnssolutions.ihbwtt.login.Authentication;
import uk.co.lnssolutions.ihbwtt.rest.entities.EventType;
import uk.co.lnssolutions.ihbwtt.rest.entities.EventTypeResult;

public class MarketOperationsMetaDataController {

	private List eventTypes;
	
/*	
	private Fortune fortune;
	private String token;
	private String appKey;
	private Authentication a;
	private boolean connected;
	*/
	
	
	private static MarketOperationsMetaDataController instance;
	
	
    public static synchronized MarketOperationsMetaDataController  getInstance(){
        if(instance == null){
            instance = new MarketOperationsMetaDataController ();
        }
        return instance;
    }
	
	public List getEventTypes(){
		return eventTypes;
	}
	
	public void setEventTypes(List in){
		eventTypes = in;
	}
	
	public String getEventTypeNameById(String id){
		if (eventTypes != null)
		{
			Iterator i = eventTypes.iterator();
			while(i.hasNext())
			{
				EventTypeResult etr = (EventTypeResult)i.next();
				if (etr.getEventType().getId().equals(id))
				{
					return etr.getEventType().getName();
				}
			}
		}
		return "Not found";
	}
	
	public String getEventIdByName(String name){
		if (eventTypes != null)
		{
			Iterator i = eventTypes.iterator();
			while(i.hasNext())
			{
				EventTypeResult etr = (EventTypeResult)i.next();
				if (etr.getEventType().getName().equals(name))
				{
					return etr.getEventType().getId();
				}
			}
		}
		return "Not found";
	}
	
	public void report(){
	    ListIterator i = eventTypes.listIterator();	
	    while(i.hasNext()){
	    	EventTypeResult etr = (EventTypeResult)i.next();
	    	{
	    		//System.out.println(et.getId()+"\t"+et.getName());
	    		EventType et = etr.getEventType();
	    		System.out.println(et.getId()+"\t"+et.getName());
	    		
	    	}	
	    }
	}
		
}
