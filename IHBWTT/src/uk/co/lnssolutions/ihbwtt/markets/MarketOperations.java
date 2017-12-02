package uk.co.lnssolutions.ihbwtt.markets;

import java.rmi.activation.ActivationGroupID;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.co.lnssolutions.ihbwtt.control.AuthenticationController;
import uk.co.lnssolutions.ihbwtt.rest.entities.EventTypeResult;
import uk.co.lnssolutions.ihbwtt.rest.entities.MarketBook;
import uk.co.lnssolutions.ihbwtt.rest.entities.MarketCatalogue;
import uk.co.lnssolutions.ihbwtt.rest.entities.MarketFilter;
import uk.co.lnssolutions.ihbwtt.rest.entities.PriceProjection;
import uk.co.lnssolutions.ihbwtt.rest.entities.RunnerCatalog;
import uk.co.lnssolutions.ihbwtt.rest.entities.TimeRange;
import uk.co.lnssolutions.ihbwtt.rest.enums.MarketProjection;
import uk.co.lnssolutions.ihbwtt.rest.enums.MarketSort;
import uk.co.lnssolutions.ihbwtt.rest.enums.MatchProjection;
import uk.co.lnssolutions.ihbwtt.rest.enums.OrderProjection;
import uk.co.lnssolutions.ihbwtt.rest.enums.OrderStatus;
import uk.co.lnssolutions.ihbwtt.rest.enums.PriceData;
import uk.co.lnssolutions.ihbwtt.rest.exceptions.APINGException;
import uk.co.lnssolutions.ihbwtt.rest.operations.ApiNgJsonRpcOperations;
import uk.co.lnssolutions.ihbwtt.rest.operations.ApiNgOperations;

/**
 * Controller for Market Operation queries
 * 
 * @author eliotpicken
 *
 */
public class MarketOperations {

	/**
	 * So, in here we need a variety of GetMarketOPerations, designed to find
	 * markets of interest.
	 * 
	 * If we find an interesting one, we'll create a tracker for it.
	 * 
	 * If the tracker things it's worthwhile, time for a punt
	 * 
	 * Authentication is controlled by AuthenticationController.
	 */

	/**
	 * Simple start then : let's get some event types
	 * 
	 */
	public List<EventTypeResult> getEventTypes() {

		// This will be called by a controller that has appKey and token
		AuthenticationController ac = AuthenticationController.getInstance();
		ac.login();

		try {
			ApiNgOperations jsonOperations = ApiNgJsonRpcOperations.getInstance();
			MarketFilter marketFilter;
			marketFilter = new MarketFilter();
			Set<String> eventTypeIds = new HashSet<String>();

			List<EventTypeResult> r = jsonOperations.listEventTypes(marketFilter, ac.getAppKey(), ac.getToken());
			for (EventTypeResult eventTypeResult : r) {
				eventTypeIds.add(eventTypeResult.getEventType().getId().toString());
			}
			return r;

		} catch (APINGException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Given our event type list, let's get a list of markets.
	 * 
	 * We're going to use get MarketCatalgoue for this
	 * 
	 * 
	 * @param eventTypeName
	 */
	public List<MarketCatalogue> getMarkets(String eventTypeName) {
		// This will be called by a controller that has appKey and token
		AuthenticationController ac = AuthenticationController.getInstance();
		ac.login();

		// Needs

		// filter
		// marketprojection
		// sort
		// maxresult
		//

		// Get EventTypeIds
		MarketOperationsMetaDataController momdc = MarketOperationsMetaDataController.getInstance();
		String tennisID = momdc.getEventIdByName(eventTypeName);
		HashSet eventTypeIDs = new HashSet();
		eventTypeIDs.add(tennisID);

		try {
			ApiNgOperations jsonOperations = ApiNgJsonRpcOperations.getInstance();
			((ApiNgJsonRpcOperations)jsonOperations).setDebug(true);

			// Setup time filter
			TimeRange timeRange = new TimeRange();
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.HOUR_OF_DAY, -3);
			cal.setTime(cal.getTime());
			timeRange.setFrom(cal.getTime());
			cal.add(Calendar.HOUR_OF_DAY, 27);
			timeRange.setTo(cal.getTime());

			// Setup Projection
			Set<MarketProjection> projection = new HashSet<MarketProjection>();
			projection.add(MarketProjection.COMPETITION);
		//	projection.add(MarketProjection.MARKET_DESCRIPTION);
			projection.add(MarketProjection.EVENT);
			projection.add(MarketProjection.EVENT_TYPE);
			projection.add(MarketProjection.MARKET_START_TIME);
			projection.add(MarketProjection.RUNNER_DESCRIPTION);
			projection.add(MarketProjection.RUNNER_METADATA);

			// Set Order Status 
			Set<OrderStatus> orders = new HashSet<OrderStatus>();
			orders.add(OrderStatus.EXECUTABLE);
			
			// Setup market Filter
			MarketFilter marketFilter;
			marketFilter = new MarketFilter();
			marketFilter.setMarketStartTime(timeRange);
			marketFilter.setEventTypeIds(eventTypeIDs);
			//marketFilter.setWithOrders(orders);
			marketFilter.setInPlayOnly(true);
		    //marketFilter.setTextQuery("Match Odds");

			List<MarketCatalogue> m = jsonOperations.listMarketCatalogue(marketFilter, projection,
					MarketSort.FIRST_TO_START, "200", ac.getAppKey(), ac.getToken());
			/*for (MarketCatalogue marketCatalogue : m) {
				String comp = marketCatalogue.getCompetition().getName();
				String marketName = marketCatalogue.getMarketName();
				if (marketName.contains("Match Odds")) {
					//Date marketTime = marketCatalogue.getDescription().getMarketTime();
					//Date suspendTime = marketCatalogue.getDescription().getSuspendTime();
					List<RunnerCatalog> runners = marketCatalogue.getRunners();
				/*	System.out.println(comp + "\t[" + marketName + "]\t");   //+ marketTime + "\t" + suspendTime);
					for (RunnerCatalog r : runners) {
						System.out.println("\t" + r.getRunnerName() + "\t" + r.getSelectionId());
					}
				}*/
	         // Do the display in the calling method	
		
			//}
			return m;

		} catch (APINGException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return null;

	}


	public List<MarketBook> getDetailsForMarket(String marketID) {
		// This will be called by a controller that has appKey and token
		AuthenticationController ac = AuthenticationController.getInstance();
		ac.login();

		// Straight lift from demo code
		   PriceProjection priceProjection = new PriceProjection();
           Set<PriceData> priceData = new HashSet<PriceData>();
           priceData.add(PriceData.EX_BEST_OFFERS);

           //In this case we don't need these objects so they are declared null
           OrderProjection orderProjection = null;
           MatchProjection matchProjection = null;
           String currencyCode = null;

           List<String> marketIds = new ArrayList<String>();
           marketIds.add(marketID);

   		try {
			ApiNgOperations jsonOperations = ApiNgJsonRpcOperations.getInstance();
			((ApiNgJsonRpcOperations)jsonOperations).setDebug(false);
			
             List<MarketBook> marketBookReturn = jsonOperations.listMarketBook(marketIds, priceProjection,
                   orderProjection, matchProjection, currencyCode, ac.getAppKey(), ac.getToken());
             
             return marketBookReturn;
		} catch (APINGException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}

		
		return null;
	};

}
