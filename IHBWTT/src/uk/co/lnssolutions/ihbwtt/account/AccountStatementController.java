package uk.co.lnssolutions.ihbwtt.control;

import java.util.Date;

import uk.co.lnssolutions.ihbwtt.account.AccountOperations;
import uk.co.lnssolutions.ihbwtt.login.Authentication;
import uk.co.lnssolutions.ihbwtt.rest.entities.AccountStatementReport;
import uk.co.lnssolutions.ihbwtt.rest.entities.ItemClassData;
import uk.co.lnssolutions.ihbwtt.rest.entities.StatementItem;

/* Singleton to control access to account information 
 * 
 * This class primarily keeps the DB ledger table up to data.
 * 
 */
public class AccountStatementController {

	private Fortune fortune;
	private static AccountStatementController instance;
	private static Date lastRequest;
	private static Double balance;
	
	
    public static synchronized AccountStatementController getInstance(){
        if(instance == null){
            instance = new AccountStatementController();
        }
        return instance;
    }
	
    /** Method to trigger a ledger Update for daily totals
     * 
     * @return
     */
    public boolean updateLedger(){
    	
    	// Get latest timestamp from table
		AccountDBController dao = AccountDBController.getInstance();
		Date lastUpdateDate  = dao.getLastStatementItemDate();

		// Get all transactions since then
    	// Insert them into DB
    	
    	AccountOperations ao = new AccountOperations();
    	AccountStatementReport accountUpdate = ao.getAccountStatement(lastUpdateDate);
    	if (accountUpdate != null){
    		for(StatementItem ledgerEntry : accountUpdate.getAccountStatement())
    		{
    			Double amount = ledgerEntry.getAmount();
    			Double balance = ledgerEntry.getBalance();
    			String refID = ledgerEntry.getRefId();
    			Date entryDate = ledgerEntry.getItemDate();
    			
    			String itemClass = ledgerEntry.getItemClass().toString();  // seems broken
    			//ItemClassData icd = ledgerEntry.getItemClassData();
    			
    			
    			// Legacy data
    			String transactionType = ledgerEntry.getLegacyData().getTransactionType();
    			Long transactionId = ledgerEntry.getLegacyData().getTransactionId();
    			Long eventID = ledgerEntry.getLegacyData().getEventId();
    			Long eventTypeID =ledgerEntry.getLegacyData().getEventTypeId();
    			Double betSize = ledgerEntry.getLegacyData().getBetSize();
    			String betType = ledgerEntry.getLegacyData().getBetType();
    			Date settlementDate = ledgerEntry.getLegacyData().getStartDate();
    			String marketType = ledgerEntry.getLegacyData().getMarketType().toString();
    			String selectionName = ledgerEntry.getLegacyData().getSelectionName();
    			Long selectionId = ledgerEntry.getLegacyData().getSelectionId();
    			String marketName = ledgerEntry.getLegacyData().getMarketName();
    			String fullMarketName = ledgerEntry.getLegacyData().getFullMarketName();
    			Double avgPrice  = ledgerEntry.getLegacyData().getAvgPrice();
    			String betCategoryType = ledgerEntry.getLegacyData().getBetCategoryType();
    			String commissionRate = ledgerEntry.getLegacyData().getCommissionRate();
    		    Double grossBetAmount = ledgerEntry.getLegacyData().getGrossBetAmount();
    		    Date placedDate = ledgerEntry.getLegacyData().getPlacedDate();
    		    Date startDate = ledgerEntry.getLegacyData().getStartDate();
    		    String winLose = ledgerEntry.getLegacyData().getWinLose().toString();
    		    
    			/*System.out.println(  
    					refID +"\t"+
    					eventTypeID  +"\t"+
    					eventID  +"\t"+
    					transactionType+"\t"+
    			        --transactionId + "\t"+
    			        marketType + "\t" +
    			        selectionId + "\t" +
    			        avgPrice + "\t" +
    			        betSize+"\t"+
    			        grossBetAmount  +"\t"+
    			        amount+"\t"+
                        balance+"\t"+
    			        betType +"\t"+
    			        betCategoryType +"\t"+ 
    			        commissionRate  +"\t"+
    			        placedDate + "\t" +
    			        startDate + "\t" +
    			        marketName  +"\t"+
    			        fullMarketName + "\t" +	         
    					selectionName + "\t"+                   
    					winLose + "\t" +
    					entryDate+"\t"+
    					 
    			                           
    					                   
    					                    itemClass);
    	*/
    			
    			//--------------------------------------
    			// Persist the record
    			dao.insertAccountTransaction(ledgerEntry);
    			
    		}
    		if(accountUpdate.isMoreAvailable())
    		{
    			
    		}
    		
    	}
    	return true;
    }
   
}
