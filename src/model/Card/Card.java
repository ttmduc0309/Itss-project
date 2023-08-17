package model.card;

public class Card {
	private String cardHolderName;
	private String cardNumber;
	private String securityCode;
	private String expirationDate;
	private int balance = 1000000;
	
	public Card(String cardHolderName, String cardNumber, String securityCode, String expirationDate, int balance) {
		this.cardHolderName = cardHolderName;
		this.cardNumber = cardNumber;
		this.securityCode = securityCode;
		this.expirationDate = expirationDate;
		this.balance = balance;
	}
	
	public String getCardHolderName() {
		return cardHolderName;
	}
	
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	public Card() {}
}