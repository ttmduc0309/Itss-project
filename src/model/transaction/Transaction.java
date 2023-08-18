package model.transaction;

import java.sql.Timestamp;

public class Transaction {
	private int id;
	private Long amount;
	private String content;
	private Timestamp timeCreated;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(Timestamp timeCreated) {
		this.timeCreated = timeCreated;
	}
}