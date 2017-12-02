package uk.co.lnssolutions.ihbwtt.rest.entities;

import java.util.Date;

import uk.co.lnssolutions.ihbwtt.rest.enums.MarketType;
import uk.co.lnssolutions.ihbwtt.rest.enums.WinLose;



public class UnknownStatementItem {

	private Double 	avgPrice;
	private Double 	betSize;
	private String 	betType;
	private String 	betCategoryType;
	private String 	commissionRate;
	private Long   	eventId;
	private Long	eventTypeId;
	private String	fullMarketName;
	private Double  grossBetAmount;
	private String	marketName;
	private MarketType marketType;
	private Date	placedDate;
	private Long	selectionId;
	private String	selectionName;
	private Date	startDate;
	private String	transactionType;
	private Long	transactionId;
	private WinLose winLose;
	
	
	
	
	@Override
	public String toString() {
		return "UnknownStatementItem [avgPrice=" + avgPrice + ", betSize=" + betSize + ", betType=" + betType
				+ ", betCategoryType=" + betCategoryType + ", commissionRate=" + commissionRate + ", eventId=" + eventId
				+ ", eventTypeId=" + eventTypeId + ", fullMarketName=" + fullMarketName + ", grossBetAmount="
				+ grossBetAmount + ", marketName=" + marketName + ", marketType=" + marketType + ", placedDate="
				+ placedDate + ", selectionId=" + selectionId + ", selectionName=" + selectionName + ", startDate="
				+ startDate + ", transactionType=" + transactionType + ", transactionId=" + transactionId + ", winLose="
				+ winLose + "]";
	}
	public UnknownStatementItem(Double avgPrice, Double betSize, String betType, String betCategoryType,
			String commissionRate, Long eventId, Long eventTypeId, String fullMarketName, Double grossBetAmount,
			String marketName, MarketType marketType, Date placedDate, Long selectionId, String selectionName,
			Date startDate, String transactionType, Long transactionId, WinLose winLose) {
		this.avgPrice = avgPrice;
		this.betSize = betSize;
		this.betType = betType;
		this.betCategoryType = betCategoryType;
		this.commissionRate = commissionRate;
		this.eventId = eventId;
		this.eventTypeId = eventTypeId;
		this.fullMarketName = fullMarketName;
		this.grossBetAmount = grossBetAmount;
		this.marketName = marketName;
		this.marketType = marketType;
		this.placedDate = placedDate;
		this.selectionId = selectionId;
		this.selectionName = selectionName;
		this.startDate = startDate;
		this.transactionType = transactionType;
		this.transactionId = transactionId;
		this.winLose = winLose;
	}
	public Double getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
	}
	public Double getBetSize() {
		return betSize;
	}
	public void setBetSize(Double betSize) {
		this.betSize = betSize;
	}
	public String getBetType() {
		return betType;
	}
	public void setBetType(String betType) {
		this.betType = betType;
	}
	public String getBetCategoryType() {
		return betCategoryType;
	}
	public void setBetCategoryType(String betCategoryType) {
		this.betCategoryType = betCategoryType;
	}
	public String getCommissionRate() {
		return commissionRate;
	}
	public void setCommissionRate(String commissionRate) {
		this.commissionRate = commissionRate;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public Long getEventTypeId() {
		return eventTypeId;
	}
	public void setEventTypeId(Long eventTypeId) {
		this.eventTypeId = eventTypeId;
	}
	public String getFullMarketName() {
		return fullMarketName;
	}
	public void setFullMarketName(String fullMarketName) {
		this.fullMarketName = fullMarketName;
	}
	public Double getGrossBetAmount() {
		return grossBetAmount;
	}
	public void setGrossBetAmount(Double grossBetAmount) {
		this.grossBetAmount = grossBetAmount;
	}
	public String getMarketName() {
		return marketName;
	}
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	public MarketType getMarketType() {
		return marketType;
	}
	public void setMarketType(MarketType marketType) {
		this.marketType = marketType;
	}
	public Date getPlacedDate() {
		return placedDate;
	}
	public void setPlacedDate(Date placedDate) {
		this.placedDate = placedDate;
	}
	public Long getSelectionId() {
		return selectionId;
	}
	public void setSelectionId(Long selectionId) {
		this.selectionId = selectionId;
	}
	public String getSelectionName() {
		return selectionName;
	}
	public void setSelectionName(String selectionName) {
		this.selectionName = selectionName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public WinLose getWinLose() {
		return winLose;
	}
	public void setWinLose(WinLose winLose) {
		this.winLose = winLose;
	}
	
	
	
}
