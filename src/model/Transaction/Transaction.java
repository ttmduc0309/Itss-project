package model.Transaction;

public class Transaction {
	private int transactionID;
	private int transAmount;
	private String transContent;
	private String transDate;
	
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public int getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(int transAmount) {
		this.transAmount = transAmount;
	}
	public String getTransContent() {
		return transContent;
	}
	public void setTransContent(String transContent) {
		this.transContent = transContent;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	
}
