package uk.co.lnssolutions.ihbwtt.account;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.co.lnssolutions.ihbwtt.control.AuthenticationController;
import uk.co.lnssolutions.ihbwtt.control.Fortune;
import uk.co.lnssolutions.ihbwtt.login.Authentication;
import uk.co.lnssolutions.ihbwtt.rest.entities.AccountFundsResponse;
import uk.co.lnssolutions.ihbwtt.rest.entities.AccountStatementReport;
import uk.co.lnssolutions.ihbwtt.rest.entities.EventTypeResult;
import uk.co.lnssolutions.ihbwtt.rest.entities.MarketFilter;
import uk.co.lnssolutions.ihbwtt.rest.entities.TimeRange;
import uk.co.lnssolutions.ihbwtt.rest.enums.IncludeItem;
import uk.co.lnssolutions.ihbwtt.rest.enums.Wallet;
import uk.co.lnssolutions.ihbwtt.rest.exceptions.APINGException;
import uk.co.lnssolutions.ihbwtt.rest.operations.ApiNgJsonRpcOperations;
import uk.co.lnssolutions.ihbwtt.rest.operations.ApiNgOperations;

public class AccountOperations {

	 /** Simple call to get the current available balance
     * 
     * @return
     */
    public Double getBalance(){
		// This will be called by a controller that has appKey and token
		AuthenticationController ac = AuthenticationController.getInstance();
		ac.login();

		try {
			ApiNgOperations jsonOperations = ApiNgJsonRpcOperations.getInstance();
			AccountFundsResponse response = jsonOperations.GetAccountFunds("Wallet is not used",ac.getAppKey(),ac.getToken());
			Double availableBalance = response.getAvailableToBetBalance();
			//Integer pointsBalance  = response.getPointsBalance();
			//Double discountRate = response.getDiscountRate();
			return  availableBalance;
		} catch (APINGException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return null;
    }
    
    
    public Double getDiscountRate(){
		// This will be called by a controller that has appKey and token
		AuthenticationController ac = AuthenticationController.getInstance();
		ac.login();

		try {
			ApiNgOperations jsonOperations = ApiNgJsonRpcOperations.getInstance();
			AccountFundsResponse response = jsonOperations.GetAccountFunds("Wallet is not used",ac.getAppKey(),ac.getToken());
			//Double availableBalance = response.getAvailableToBetBalance();
			//Integer pointsBalance  = response.getPointsBalance();
			Double discountRate = response.getDiscountRate();
			return  discountRate;
		} catch (APINGException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return null;
    }
    
    public Integer getPointsBalance(){
		// This will be called by a controller that has appKey and token
		AuthenticationController ac = AuthenticationController.getInstance();
		ac.login();

		try {
			ApiNgOperations jsonOperations = ApiNgJsonRpcOperations.getInstance();
			AccountFundsResponse response = jsonOperations.GetAccountFunds("Wallet is not used",ac.getAppKey(),ac.getToken());
			//Double availableBalance = response.getAvailableToBetBalance();
			Integer pointsBalance  = response.getPointsBalance();
			//Double discountRate = response.getDiscountRate();
			return  pointsBalance;
		} catch (APINGException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return null;
    }
	
    /** Call to update the db with account transactions
     * 
     * Defaults to last 24 hours
     * 
     * @ret
     */
   public AccountStatementReport getAccountStatement(){
	   
	/*   int from = 0;
       int count = 5;
	   return getAccountStatement(from,count);
	*/   
   	return getAccountStatement(0,5,new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000),new Date());
       
   };
   
   
   /** Return all statement items since a point in time 
    * 
    * @param since
    * @return
    */
   public AccountStatementReport getAccountStatement(Date since){
	   return getAccountStatement(0,5,since,new Date());
   }
   
    /** This is a wrapper method that can be used to provide a complete picture of 
     * last 24 hours of ledger entries. 
     * 
     * pagination is conducted inside the method.
     * 
     * @param from
     * @param count
     * @return
     */
    public AccountStatementReport getAccountStatement(int from,int count){
		// Setup time filter
    	return getAccountStatement(from,count,new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000),new Date());
    };
    
    
    /** Get all statements between two dates
     * 
     */
    public AccountStatementReport getAccountStatement(int startCount,int rowCount,Date start,Date end){	
    	//int startCount = 0;
    	//int rowCount = 90;
    	AccountStatementReport result = getAccountStatement(startCount,rowCount,start,end,true);
    	boolean paginate = result.isMoreAvailable();
    	while (paginate)
    	{
    		System.out.println("Iterating "+startCount);
    		AccountStatementReport result2 = getAccountStatement(startCount+rowCount,rowCount,start,end,true);
    		paginate = result2.isMoreAvailable();
    		result.addStatementItems(result2.getAccountStatement());
    		startCount = startCount+rowCount;
    	}
    	if (result.isMoreAvailable()) System.out.println("We have more (than "+result.getAccountStatement().size()+")");
    	return result;
    }
    
    
    /** Base method that has to/from params for date, and record count.  
     * 
     * This is expected to be repeatedly called for pagination requests
     * @param fromRecord
     * @param recordCount
     * @param fromDate
     * @param toDate
     * @return
     */
    public AccountStatementReport getAccountStatement(int fromRecord,int recordCount,Date fromDate, Date toDate,boolean single){

		AuthenticationController ac = AuthenticationController.getInstance();
		ac.login();

		try {
			ApiNgOperations jsonOperations = ApiNgJsonRpcOperations.getInstance();
			
            //Setup timeFilter
			TimeRange timeRange = new TimeRange();
			timeRange.setFrom(fromDate);
			timeRange.setTo(toDate);
			System.out.println("From:TO "+timeRange.getFrom()+"\t"+timeRange.getTo());
			
			// Other defaults are fine
			IncludeItem ii;
			Wallet wallet;
			
			AccountStatementReport result = jsonOperations.GetAccountStatement("locale", fromRecord, recordCount, timeRange, IncludeItem.ALL, Wallet.UK, ac.getAppKey(), ac.getToken());
			return result;
					
		} catch (APINGException e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return null;
		}
    }
	
    
    /** Call to update the db with account transactions
     * This method will last X records
     * @return
     */
    public AccountStatementReport getAccountStement(Date start,Date end){
		AuthenticationController ac = AuthenticationController.getInstance();
		ac.login();

		try {
			ApiNgOperations jsonOperations = ApiNgJsonRpcOperations.getInstance();
			TimeRange tr = new TimeRange();
			
			// Setup time filter
			TimeRange timeRange = new TimeRange();
			timeRange.setFrom(start);
			timeRange.setTo(end);
			
			// Other defaults are fine
			IncludeItem ii;
			Wallet wallet;
			
			AccountStatementReport result = jsonOperations.GetAccountStatement("locale", 30, 40, tr, IncludeItem.ALL, Wallet.UK, ac.getAppKey(), ac.getToken());
			return result;
					
		} catch (APINGException e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return null;
		}
    }
    
    
}
