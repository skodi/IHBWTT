package uk.co.lnssolutions.ihbwtt.rest.containers;

import uk.co.lnssolutions.ihbwtt.rest.entities.PlaceExecutionReport;

public class PlaceOrdersContainer extends Container {

	private PlaceExecutionReport result;
	
	public PlaceExecutionReport getResult() {
		return result;
	}
	
	public void setResult(PlaceExecutionReport result) {
		this.result = result;
	}

}
