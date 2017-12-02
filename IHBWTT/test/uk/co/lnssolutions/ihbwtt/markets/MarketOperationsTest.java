package uk.co.lnssolutions.ihbwtt.markets;

import java.util.HashMap;
import java.util.List;

import junit.framework.TestCase;
import uk.co.lnssolutions.ihbwtt.rest.entities.EventTypeResult;
import uk.co.lnssolutions.ihbwtt.rest.entities.MarketBook;
import uk.co.lnssolutions.ihbwtt.rest.entities.MarketCatalogue;
import uk.co.lnssolutions.ihbwtt.rest.entities.MarketDescription;
import uk.co.lnssolutions.ihbwtt.rest.entities.Runner;
import uk.co.lnssolutions.ihbwtt.rest.entities.RunnerCatalog;

public class MarketOperationsTest extends TestCase {

	public void testGetEventTypes() {
		MarketOperations me = new MarketOperations();
		List<EventTypeResult> results = me.getEventTypes();
		assertTrue(results != null);

		// Let's see what we have
		System.out.println(results.size());
		MarketOperationsMetaDataController momd = MarketOperationsMetaDataController.getInstance();
		momd.setEventTypes(results);

		momd.report();
		// Test lookup
		assertTrue(momd.getEventTypeNameById("1").equals("Soccer"));
		assertTrue(momd.getEventTypeNameById("7").equals("Horse Racing"));
		assertTrue(momd.getEventTypeNameById("3").equals("Golf"));

		// And test the reverse
		assertTrue(momd.getEventIdByName("Tennis").equals("2"));
		assertTrue(momd.getEventIdByName("Cricket").equals("4"));
		assertTrue(momd.getEventIdByName("Rugby Union").equals("5"));

	}

	public void testGetMarkets() {
		MarketOperations mo = new MarketOperations();
		List<EventTypeResult> results = mo.getEventTypes();
		assertTrue(results != null);

		// Let's see what we have
		System.out.println(results.size());
		MarketOperationsMetaDataController momd = MarketOperationsMetaDataController.getInstance();
		momd.setEventTypes(results);

		List markets = mo.getMarkets("Tennis");
		assertTrue(markets != null);
		assertTrue(markets.size() > 0);

	}

	/**
	 * This test will pickup the getMarketsTest to get the books of some markets
	 * 
	 */
	public void testGetDetailsForMarket() {
		
		System.out.println("------------------------------");
		System.out.println("--                          --");
		System.out.println("-- testGetDetailsForMarket  --");
		System.out.println("                              ");
		MarketOperations mo = new MarketOperations();
		List<EventTypeResult> results = mo.getEventTypes();

		// Let's see what we have
		//MarketOperationsMetaDataController momd = MarketOperationsMetaDataController.getInstance();
	    //momd.setEventTypes(results);

		List<MarketCatalogue> markets = mo.getMarkets("Tennis");

		/**
		 * So now we have market ids (in markets somewhere) let's get market
		 * books for them
		 */
		for (MarketCatalogue marketCatalogue : markets) {
			String marketID = marketCatalogue.getMarketId();
			if (marketCatalogue.getMarketName().contains("Match Odds")) {
				List<MarketBook> marketBooks = mo.getDetailsForMarket(marketID);
				for (MarketBook marketBook : marketBooks) {
					int limit = 20;
					Boolean inplay = marketBook.getInplay();
					assertTrue(inplay); // We're assuming market filter still
										// has inplay running. Tennis usually
										// does

					String marketName = marketCatalogue.getMarketName();
					MarketDescription marketDescription = marketCatalogue.getDescription();
					
			        
			        
					Integer numberofWinners = marketBook.getNumberOfWinners();
					Double totalMatched = marketBook.getTotalMatched();
					Double totalAvailable = marketBook.getTotalAvailable();
					System.out.println("--------------------------------");
					System.out.println(marketCatalogue.getCompetition().getName()+"\t"+marketCatalogue.getEvent().getName()+ "\t"+totalMatched + " with available "+totalAvailable+"\t INPLAY:"+inplay);
					
					HashMap<Long, String> runnerNames = new HashMap();
					List<RunnerCatalog> runnersData =  marketCatalogue.getRunners();
					for (RunnerCatalog runnerD : runnersData) {
						String runnerName = runnerD.getRunnerName();
						Long runnerID = runnerD.getSelectionId();
						runnerNames.put(runnerID,runnerName);
						//System.out.println(runnerID + "\t"+ runnerName);
					}
					
					List<Runner> runners = marketBook.getRunners();
					for (Runner runner : runners){
						Long selectionID = runner.getSelectionId();
						Double lastPrice = runner.getLastPriceTraded();
						String runnerName = runnerNames.get(selectionID);
					    System.out.println("Last Price \t" + selectionID + "\t"+lastPrice + "\t"+runnerName);
						
					}
					limit++;
					if (limit> 40) break;
				}
			}

		}
		assertTrue(markets.size() > 0);

	}

}
