package uk.co.lnssolutions.ihbwtt.rest.entities;

import java.util.List;
import java.util.ListIterator;

import uk.co.lnssolutions.ihbwtt.rest.containers.Container;



public class AccountStatementReport extends Container {

	public boolean moreAvailable;
	public List<StatementItem> accountStatement;
	
	
	public boolean isMoreAvailable() {
		return moreAvailable;
	}
	public void setMoreAvailable(boolean moreAvailable) {
		this.moreAvailable = moreAvailable;
	}
	public List<StatementItem> getAccountStatement() {
		return accountStatement;
	}
	public void setAccountStatement(List<StatementItem> accountStatement) {
		this.accountStatement = accountStatement;
	}
	public AccountStatementReport(boolean moreAvailable, List<StatementItem> accountStatement) {
		this.moreAvailable = moreAvailable;
		this.accountStatement = accountStatement;
	}
	
	public void addStatementItems(List<StatementItem> in){
		accountStatement.addAll(in);
	}
	
	
	public String report(){
		StringBuffer result = new StringBuffer();
		if (accountStatement != null)
		{
		   ListIterator<StatementItem>	items = accountStatement.listIterator();
		   while(items.hasNext())
		   {
			   StatementItem item = (StatementItem)items.next();
			   // May need to format the report on this a bit better
			   System.out.println("------------------------------------");
			   System.out.println("| New Item                         |");
			   System.out.println("------------------------------------");
			   System.out.println(" Ref ID             :"+item.getRefId());
			   System.out.println(" Item Date          :"+item.getItemDate());
			   System.out.println(" Amount             :"+item.getAmount());
			   System.out.println(" Balance            :"+item.getBalance());
			   System.out.println(" ItemClass          :"+item.getClass());
			   System.out.println("");
			   System.out.println(" Item Class Data ");
			   System.out.println("");
/*			   Set itemClassKeys = item.getItemClassData().keySet();
			   Iterator i = itemClassKeys.iterator();
			   while(i.hasNext())
			   {
				   String key = (String)i.next();
				   String data = item.getItemClassData().get(key);
				   System.out.println("\n--   "+key+"\t\t\t\t"+data);
			   }*/
			   System.out.println("1:"+item.getItemClassData().getUnknownStatementItem().toString());
			
			   
			   System.out.println("");
			   System.out.println("  Legacy Data ");
			   System.out.println("");
			   System.out.println(item.getLegacyData().toString());
			  // result.append("\n"+item.getRefId()+"\t"+item.getItemDate()+"\t"+item.getAmount()+"\t"+item.getBalance()+"\t"+item.getItemClass()+"\t"+item.getItemClassData()+"\t"+item.getLegacyData().toString());
		   }
		}
		return result.toString();
	}
	
}
