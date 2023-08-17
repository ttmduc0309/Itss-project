package model.transaction;

import java.time.Instant;

public class Transaction {
	private int id;
	private Long amount;
	private String content;
	private Instant timeCreated;
	
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
	public Instant getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(Instant timeCreated) {
		this.timeCreated = timeCreated;
	}
}