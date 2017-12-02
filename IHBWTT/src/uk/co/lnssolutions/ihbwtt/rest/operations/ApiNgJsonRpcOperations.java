package uk.co.lnssolutions.ihbwtt.rest.operations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.co.lnssolutions.ihbwtt.control.Fortune;
import uk.co.lnssolutions.ihbwtt.control.RequestThrottleController;
import uk.co.lnssolutions.ihbwtt.rest.containers.AccountFundsResponseContainer;
import uk.co.lnssolutions.ihbwtt.rest.containers.AccountStatementContainer;
import uk.co.lnssolutions.ihbwtt.rest.containers.EventTypeResultContainer;
import uk.co.lnssolutions.ihbwtt.rest.containers.ListMarketBooksContainer;
import uk.co.lnssolutions.ihbwtt.rest.containers.ListMarketCatalogueContainer;
import uk.co.lnssolutions.ihbwtt.rest.containers.PlaceOrdersContainer;
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
import uk.co.lnssolutions.ihbwtt.rest.enums.ApiNgOperation;
import uk.co.lnssolutions.ihbwtt.rest.enums.IncludeItem;
import uk.co.lnssolutions.ihbwtt.rest.enums.MarketProjection;
import uk.co.lnssolutions.ihbwtt.rest.enums.MarketSort;
import uk.co.lnssolutions.ihbwtt.rest.enums.MatchProjection;
import uk.co.lnssolutions.ihbwtt.rest.enums.OrderProjection;
import uk.co.lnssolutions.ihbwtt.rest.enums.Wallet;
import uk.co.lnssolutions.ihbwtt.rest.exceptions.APINGException;
import uk.co.lnssolutions.ihbwtt.rest.json.util.JsonConverter;
import uk.co.lnssolutions.ihbwtt.rest.json.util.JsonrpcRequest;
import uk.co.lnssolutions.ihbwtt.rest.entities.Error;




public class ApiNgJsonRpcOperations extends ApiNgOperations{

	private boolean debug;
	
    private static ApiNgJsonRpcOperations instance = null;

    private ApiNgJsonRpcOperations(){}

    public static ApiNgJsonRpcOperations getDebugInstance(){
        if (instance == null){
            instance = new ApiNgJsonRpcOperations();
            instance.setDebug(true);
        }
        return instance;
    }
    
    
    public static ApiNgJsonRpcOperations getInstance(){
        if (instance == null){
            instance = new ApiNgJsonRpcOperations();
            instance.setDebug(false);
        }
        return instance;
    }
    
    public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public List<EventTypeResult> listEventTypes(MarketFilter filter, String appKey, String ssoId) throws APINGException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(FILTER, filter);
        params.put(LOCALE, locale);
        String result = getInstance().makeExchangeRequest(ApiNgOperation.LISTEVENTTYPES.getOperationName(), params, appKey, ssoId);
        if(debug)
            System.out.println("\nResponse: "+result);

        EventTypeResultContainer container = JsonConverter.convertFromJson(result, EventTypeResultContainer.class);
        if(container.getError() != null)
            throw container.getError().getData().getAPINGException();

        return container.getResult();

    }

    public List<MarketBook> listMarketBook(List<String> marketIds, PriceProjection priceProjection, OrderProjection orderProjection,
                                           MatchProjection matchProjection, String currencyCode, String appKey, String ssoId) throws APINGException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(LOCALE, locale);
        params.put(MARKET_IDS, marketIds);
        params.put(PRICE_PROJECTION, priceProjection);
        params.put(ORDER_PROJECTION, orderProjection);
        params.put(MATCH_PROJECTION, matchProjection);
        params.put("currencyCode", currencyCode);
        String result = getInstance().makeExchangeRequest(ApiNgOperation.LISTMARKETBOOK.getOperationName(), params, appKey, ssoId);
        if(debug)
            System.out.println("\nResponse: "+result);

        ListMarketBooksContainer container = JsonConverter.convertFromJson(result, ListMarketBooksContainer.class);

        if(container.getError() != null)
            throw container.getError().getData().getAPINGException();

        return container.getResult();


    }

    public List<MarketCatalogue> listMarketCatalogue(MarketFilter filter, Set<MarketProjection> marketProjection,
                                                     MarketSort sort, String maxResult, String appKey, String ssoId) throws APINGException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(LOCALE, locale);
        params.put(FILTER, filter);
        params.put(SORT, sort);
        params.put(MAX_RESULT, maxResult);
        params.put(MARKET_PROJECTION, marketProjection);
        String result = getInstance().makeExchangeRequest(ApiNgOperation.LISTMARKETCATALOGUE.getOperationName(), params, appKey, ssoId);
        if(debug)
            System.out.println("\nResponse: "+result);

        ListMarketCatalogueContainer container = JsonConverter.convertFromJson(result, ListMarketCatalogueContainer.class);

        if(container.getError() != null)
            throw container.getError().getData().getAPINGException();

        return container.getResult();

    }

    public PlaceExecutionReport placeOrders(String marketId, List<PlaceInstruction> instructions, String customerRef , String appKey, String ssoId) throws APINGException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(LOCALE, locale);
        params.put(MARKET_ID, marketId);
        params.put(INSTRUCTIONS, instructions);
        params.put(CUSTOMER_REF, customerRef);
        String result = getInstance().makeExchangeRequest(ApiNgOperation.PLACORDERS.getOperationName(), params, appKey, ssoId);
        if(debug)
            System.out.println("\nResponse: "+result);

        PlaceOrdersContainer container = JsonConverter.convertFromJson(result, PlaceOrdersContainer.class);

        if(container.getError() != null)
            throw container.getError().getData().getAPINGException();

        return container.getResult();

    }

	//  So let's see what can be built on
	public  AccountFundsResponse GetAccountFunds(String wallet,String appKey,String ssoId) throws APINGException{
        Map<String, Object> params = new HashMap<String, Object>();
         params.put(LOCALE, locale);
      //  params.put(MARKET_ID, marketId);
      //  params.put(INSTRUCTIONS, instructions);
        params.put(WALLET,Wallet.UK );
        
        String result = getInstance().makeAccountRequest(ApiNgOperation.GETACCOUNTFUNDS.getOperationName(), params, appKey, ssoId);
       // if(debug)
            System.out.println("\nResponse: "+result);

//        PlaceOrdersContainer container = JsonConverter.convertFromJson(result, PlaceOrdersContainer.class);
  
         AccountFundsResponseContainer container = JsonConverter.convertFromJson(result, AccountFundsResponseContainer.class);
         if(container.getError() != null)
            throw container.getError().getData().getAPINGException();

         
         
        return container.getResult();
	 // return container;
	}
    
	public  AccountStatementReport GetAccountStatement(String locale,int fromRecord, int recordCount,TimeRange dateRange,IncludeItem includeItem,Wallet wallet,String appKey,String ssoId) throws APINGException
	{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(LOCALE, locale);
        params.put("fromRecord", fromRecord);
        params.put("recordCount", recordCount);
        params.put("itemDateRange", dateRange);
        
        String resultStr = getInstance().makeAccountRequest(ApiNgOperation.GETACCOUNTSTATEMENT.getOperationName(), params, appKey, ssoId);
       // if(debug)
            System.out.println("\nResponse :"+resultStr);

       AccountStatementContainer container = JsonConverter.convertFromJson(resultStr, AccountStatementContainer.class);
         if(container.getError() != null)
            throw container.getError().getData().getAPINGException();
         
        // So have we parse the data then ?
         AccountStatementReport test = container.getResult();
         /*if (test != null) System.out.println("Statement Report is not null");
         else System.out.println("Statement Report is null");
         
         if (test != null) System.out.println(test.report());
         
         List statementItems = test.getAccountStatement();
         if (statementItems == null) System.out.println("We have items of some description");
         */
        return container.getResult();	
    
	}
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////
	//
	//  Two protected methods for calling Betfair - note API endpoint change
	
	
    protected String makeExchangeRequest(String operation, Map<String, Object> params, String appKey, String ssoToken) {
        String requestString;
        //Handling the JSON-RPC request
        JsonrpcRequest request = new JsonrpcRequest();
        request.setId("1"); //TODO Do we want this ? UK / AUD wallets ?
        request.setMethod(Fortune.getProp().getProperty("SPORTS_APING_V1_0") + operation);
        request.setParams(params);

        //TODO Throttling in here.
        RequestThrottleController throttler = RequestThrottleController.getInstance();
        while(!throttler.okToRequest())
        {
        	try {
        	    Thread.sleep(300);
        	} catch(InterruptedException ex) {
        	    Thread.currentThread().interrupt();
        	}
        }
        
        requestString =  JsonConverter.convertToJson(request);
        if(debug)  System.out.println("\nRequest: "+requestString);

        //We need to pass the "sendPostRequest" method a string in util format:  requestString
        HttpUtil requester = new HttpUtil();
        return requester.sendPostRequestJsonRpc(requestString, operation, appKey, ssoToken);

       }
    
    protected String makeAccountRequest(String operation, Map<String, Object> params, String appKey, String ssoToken) {
        String requestString;
        //Handling the JSON-RPC request
        JsonrpcRequest request = new JsonrpcRequest();
        request.setId("1");  //TODO Do we want this ? UK / AUD wallets ?
        request.setMethod(Fortune.getProp().getProperty("ACCOUNT_APING_V1_0") + operation);
        request.setParams(params);

        requestString =  JsonConverter.convertToJson(request);
      //  if(debug)
            System.out.println("\nRequest: "+requestString);

        //We need to pass the "sendPostRequest" method a string in util format:  requestString
        HttpUtil requester = new HttpUtil();
        return requester.sendPostAccountRequestJsonRpc(requestString, operation, appKey, ssoToken);

       }

}

