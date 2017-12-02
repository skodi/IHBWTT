package uk.co.lnssolutions.ihbwtt.rest.entities;

public class ItemClassData {

	//private UnknownStatementItem unknownStatementItem;
	private String unknownStatementItem;   // Is there an error in the JSON returned ?
	private StatementLegacyData legacyData;	

	public ItemClassData(String unknownStatementItem, StatementLegacyData legacyData) {
		super();
		this.unknownStatementItem = unknownStatementItem;
		this.legacyData = legacyData;
	}
	
	public String getUnknownStatementItem() {
		return unknownStatementItem;
	}
	public void setUnknownStatementItem(String unknownStatementItem) {
		this.unknownStatementItem = unknownStatementItem;
	}
	public StatementLegacyData getLegacyData() {
		return legacyData;
	}
	public void setLegacyData(StatementLegacyData legacyData) {
		this.legacyData = legacyData;
	}
	
	
	
}
