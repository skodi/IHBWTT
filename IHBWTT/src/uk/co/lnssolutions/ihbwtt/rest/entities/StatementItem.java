package uk.co.lnssolutions.ihbwtt.rest.entities;

import java.util.Date;

import uk.co.lnssolutions.ihbwtt.rest.enums.ItemClass;


public class StatementItem {
	
	private String refId;
	private Date itemDate;
	private Double amount;
	private Double balance;
	private ItemClass itemClass;
	private ItemClassData itemClassData;
	
	//private Map<String,String> itemClassData;
	private StatementLegacyData legacyData;
	

	
	
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public Date getItemDate() {
		return itemDate;
	}
	public void setItemDate(Date itemDate) {
		this.itemDate = itemDate;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public ItemClass getItemClass() {
		return itemClass;
	}
	public void setItemclass(ItemClass itemclass) {
		this.itemClass = itemclass;
	}
/*	public Map<String, String> getItemClassData() {
		return itemClassData;
	}
	public void setItemClassData(Map<String, String> itemClassData) {
		this.itemClassData = itemClassData;
	}*/
	
	
	
	public StatementLegacyData getLegacyData() {
		return legacyData;
	}

	public ItemClassData getItemClassData() {
		return itemClassData;
	}
	public void setItemClassData(ItemClassData itemClassData) {
		this.itemClassData = itemClassData;
	}
	public void setLegacyData(StatementLegacyData legacyData) {
		this.legacyData = legacyData;
	}

	public void setItemClass(ItemClass itemClass) {
		this.itemClass = itemClass;
	}

	
	
}
