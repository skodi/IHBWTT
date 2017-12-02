package uk.co.lnssolutions.ihbwtt.rest.containers;

import java.util.List;

import uk.co.lnssolutions.ihbwtt.rest.entities.MarketCatalogue;


public class ListMarketCatalogueContainer extends Container {

	private List<MarketCatalogue> result;

	public List<MarketCatalogue> getResult() {
		return result;
	}

	public void setResult(List<MarketCatalogue> result) {
		this.result = result;
	}

}
