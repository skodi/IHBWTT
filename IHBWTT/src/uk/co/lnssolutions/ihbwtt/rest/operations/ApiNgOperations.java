package uk.co.lnssolutions.ihbwtt.rest.operations;



import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import uk.co.lnssolutions.ihbwtt.rest.entities.AccountFundsResponse;
import uk.co.lnssolutions.ihbwtt.rest.entities.AccountStatementReport;
import uk.co.lnssolutions.ihbwtt.rest.entities.EventTypeResult;
import uk.co.lnssolutions.ihbwtt.rest.entities.MarketBook;
import uk.co.lnssolutions.ihbwtt.rest.entities.MarketCatalogue;
import uk.co.lnssolutions.ihbwtt.rest.entities.MarketFilter;
import uk.co.lnssolutions.ihbwtt.rest.entities.PlaceExecutionReport;
import uk.co.lnssolutions.ihbwtt.rest.entities.PlaceInstruction;
import uk.co.lnssolutions.ihbwtt.rest.entities.PriceProjection;
import uk.co.lnssolutions.ihbwtt.rest.entities.TimeRange;
import uk.co.lnssolutions.ihbwtt.rest.enums.IncludeItem;
import uk.co.lnssolutions.ihbwtt.rest.enums.MarketProjection;
import uk.co.lnssolutions.ihbwtt.rest.enums.MarketSort;
import uk.co.lnssolutions.ihbwtt.rest.enums.MatchProjection;
import uk.co.lnssolutions.ihbwtt.rest.enums.OrderProjection;
import uk.co.lnssolutions.ihbwtt.rest.enums.Wallet;
import uk.co.lnssolutions.ihbwtt.rest.exceptions.APINGException;




/*
import uk.co.lnssolutions.fortune.entities.EventTypeResult;
import uk.co.lnssolutions.fortune.entities.MarketBook;
import uk.co.lnssolutions.fortune.entities.MarketCatalogue;
import uk.co.lnssolutions.fortune.entities.MarketFilter;
import uk.co.lnssolutions.fortune.entities.PlaceExecutionReport;
import uk.co.lnssolutions.fortune.entities.PlaceInstruction;
import uk.co.lnssolutions.fortune.entities.PriceProjection;
import uk.co.lnssolutions.fortune.entities.TimeRange;
import uk.co.lnssolutions.fortune.entities.account.AccountFundsResponse;
import uk.co.lnssolutions.fortune.enums.IncludeItem;
import uk.co.lnssolutions.fortune.enums.MarketProjection;
import uk.co.lnssolutions.fortune.enums.MarketSort;
import uk.co.lnssolutions.fortune.enums.MatchProjection;
import uk.co.lnssolutions.fortune.enums.OrderProjection;
import uk.co.lnssolutions.fortune.enums.Wallet;
import uk.co.lnssolutions.fortune.exceptions.APINGException;
import uk.co.lnssolutions.fortune.entities.account.AccountStatementReport;
import uk.co.lnssolutions.fortune.entities.account.StatementItem;
*/

public abstract class ApiNgOperations {
	protected final String FILTER = "filter";
    protected final String LOCALE = "locale";
    protected final String SORT = "sort";
    protected final String MAX_RESULT = "maxResults";
    protected final String MARKET_IDS = "marketIds";
    protected final String MARKET_ID = "marketId";
    protected final String INSTRUCTIONS = "instructions";
    protected final String CUSTOMER_REF = "customerRef";
    protected final String MARKET_PROJECTION = "marketProjection";
    protected final String PRICE_PROJECTION = "priceProjection";
    protected final String MATCH_PROJECTION = "matchProjection";
    protected final String ORDER_PROJECTION = "orderProjection";
    protected final String locale = Locale.getDefault().toString();
    
    // Account params
    protected final String WALLET = "wallet";

	public abstract List<EventTypeResult> listEventTypes(MarketFilter filter, String appKey, String ssoId) throws APINGException;

	public abstract List<MarketBook> listMarketBook(List<String> marketIds, PriceProjection priceProjection, OrderProjection orderProjection,
						MatchProjection matchProjection, String currencyCode, String appKey, String ssoId) throws APINGException;

    public abstract List<MarketCatalogue> listMarketCatalogue(MarketFilter filter, Set<MarketProjection> marketProjection,
        MarketSort sort, String maxResult, String appKey, String ssoId) throws APINGException;

	public abstract PlaceExecutionReport placeOrders(String marketId, List<PlaceInstruction> instructions, String customerRef , String appKey, String ssoId) throws APINGException;

	//  So let's see what can be built on
	public abstract AccountFundsResponse GetAccountFunds(String wallet,String appKey,String ssoID) throws APINGException;
	public abstract AccountStatementReport GetAccountStatement(String locale,int fromRecord, int recordCount,TimeRange dateRange,IncludeItem includeItem,Wallet wallet,String appKey,String ssoID) throws APINGException;
	
	
    protected abstract String makeExchangeRequest(String operation, Map<String, Object> params, String appKey, String ssoToken) throws  APINGException;

}

