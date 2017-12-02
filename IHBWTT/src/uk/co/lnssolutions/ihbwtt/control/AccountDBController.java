package uk.co.lnssolutions.ihbwtt.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import uk.co.lnssolutions.ihbwtt.rest.entities.StatementItem;

/**
 * Controller for access to Account DB. This one records outcomes of bets,
 * acting as the ledger keeper.
 * 
 * @author eliotpicken
 *
 */
public class AccountDBController {

	private Fortune fortune;
	private static AccountDBController instance;
	private static Date lastRequest;
	private static Double balance;
	private boolean isConnected;
	private Connection conn;

	public static synchronized AccountDBController getInstance() {
		if (instance == null) {
			instance = new AccountDBController();
		}
		return instance;
	}

	/**
	 * Let's see if we can connect to account data db at least
	 * 
	 * @return
	 */
	public boolean connect() {

		// Get hold of properties that hold our passwords and connection details
		fortune = new Fortune();
		try {
			// Establish connection to MySQL
			Class.forName("com.mysql.jdbc.Driver");
			String url = fortune.getProp().getProperty("ACCOUNTDB_URL");
			String username = fortune.getProp().getProperty("ACCOUNTDB_USERNAME");
			String password = fortune.getProp().getProperty("ACCOUNTDB_PASSWORD");
			conn = DriverManager.getConnection(url,username,password);
			if (conn.getMetaData() != null)
			{
				isConnected = true;
				return true;
			}
		} catch (Exception e) {
			System.out.println("Direct JDBC Driver error " + e.toString());
		}

		return false;
	}

	public boolean disconnect() {
		if (conn == null) {
			isConnected = false;
			return true;
		}
		if (conn != null) {
			try {
				if (conn.getMetaData() != null) {
					conn.close();
					conn = null;
					isConnected = false;
					return true;
				}
			} catch (SQLException sqle) {
				System.out.println(sqle.toString());
			}
		}

		return false;
	}

	/* Method to insert a transaction statement.
	 * Will silently update it if it's an update.
	 * 
	 */
	public boolean insertAccountTransaction(StatementItem ledgerEntry) {
		
		//  Connect to DB
		if (!isConnected) connect();
		
		//  Insert Statement
		try {
		Statement statement = conn.createStatement();
		
		// Lets grab our data	
		String refID = ledgerEntry.getRefId();
		Long eventTypeId =ledgerEntry.getLegacyData().getEventTypeId();
		Long eventId = ledgerEntry.getLegacyData().getEventId();
		String transactionType = ledgerEntry.getLegacyData().getTransactionType();
		Long transactionId = ledgerEntry.getLegacyData().getTransactionId();
		String marketType = ledgerEntry.getLegacyData().getMarketType().toString();
		Long selectionId = ledgerEntry.getLegacyData().getSelectionId();
		Double avgPrice  = ledgerEntry.getLegacyData().getAvgPrice();
		Double betSize = ledgerEntry.getLegacyData().getBetSize();
	    Double grossBetAmount = ledgerEntry.getLegacyData().getGrossBetAmount();
		Double amount = ledgerEntry.getAmount();
		Double balance = ledgerEntry.getBalance();
		String betType = ledgerEntry.getLegacyData().getBetType();
		String betCategoryType = ledgerEntry.getLegacyData().getBetCategoryType();
		String commissionRate = ledgerEntry.getLegacyData().getCommissionRate();
	    Date placedDate = ledgerEntry.getLegacyData().getPlacedDate();
	    Date startDate = ledgerEntry.getLegacyData().getStartDate();
		String marketName = ledgerEntry.getLegacyData().getMarketName();
		String fullMarketName = ledgerEntry.getLegacyData().getFullMarketName();
		String selectionName = ledgerEntry.getLegacyData().getSelectionName();
	    String winLose = ledgerEntry.getLegacyData().getWinLose().toString();
		Date entryDate = ledgerEntry.getItemDate();	
		Date settlementDate = ledgerEntry.getLegacyData().getStartDate();
		Date itemDate = ledgerEntry.getItemDate();
	

        //Anything needing cleaned ?
		
		//Everything is nullable.  Table has an id column though.  Autonumber ?
		
		String query = "insert into statementItem (    refID ,	eventTypeID,eventID,	transactionType,	transactionId ,	marketType ,	selectionId ,	avgPrice  ,betSize ,  grossBetAmount ,amount ,	balance ,	betType ,betCategoryType,	commissionRate ,  placedDate ,  startDate ,marketName ,	fullMarketName ,	selectionName ,  winLose ,entryDate ,settlementDate,itemDate) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString(1, refID);
	      preparedStmt.setLong(2,   eventTypeId);
	      preparedStmt.setLong(3,   eventId);
	      preparedStmt.setString(4, transactionType);
	      preparedStmt.setString(5, transactionId.toString());
	      preparedStmt.setString(6, marketType);
	      preparedStmt.setLong(7,   selectionId);
	      preparedStmt.setDouble(8, avgPrice);
	      preparedStmt.setDouble(9, betSize);
	      preparedStmt.setDouble(10, grossBetAmount);
	      preparedStmt.setDouble(11, amount);
	      preparedStmt.setDouble(12, balance);
	      preparedStmt.setString(13, betType);
	      preparedStmt.setString(14, betCategoryType);
	      preparedStmt.setString(15, commissionRate);
	      preparedStmt.setTimestamp(16, new java.sql.Timestamp(placedDate.getTime()));
	      preparedStmt.setTimestamp(17,   new java.sql.Timestamp(startDate.getTime()));
	      preparedStmt.setString(18, marketName);
	      preparedStmt.setString(19, fullMarketName);
	      preparedStmt.setString(20, selectionName);
	      preparedStmt.setString(21, winLose);
	      preparedStmt.setTimestamp(22,  new java.sql.Timestamp(entryDate.getTime()));	
	      preparedStmt.setTimestamp(23,  new java.sql.Timestamp(settlementDate.getTime()));
	      preparedStmt.setTimestamp(24,  new java.sql.Timestamp(itemDate.getTime()));
	      
	      preparedStmt.execute();

	
		} catch(SQLException sqle)
		{
			System.out.println(sqle.toString());
		}
		disconnect();
		return true;
	}

	public int getTranscationCount() {
		if (!isConnected) {
			connect();
		}
		if (isConnected) {
			try {
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery("Select count(*) from statementItem;");
				rs.next();
				int rowCount = rs.getInt(1);
				rs.close();
				statement.close();
				return rowCount;

			} catch (SQLException sqle) {
				System.out.println(sqle.toString());
			}
		}
		return -1;
	}
	
	public Date getLastStatementItemDate() {
		if (!isConnected) {
			connect();
		}
		java.util.Date result;
		if (isConnected) {
			try {
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery("Select max(itemDate) from statementItem;");
				rs.next();
				java.sql.Timestamp ts = rs.getTimestamp(1);
				result = new Date(ts.getTime());
				rs.close();
				statement.close();
				return result;

			} catch (SQLException sqle) {
				System.out.println(sqle.toString());
			}
		}
		return null;
	}
}