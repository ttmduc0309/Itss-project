package subsystem.interbank;

import java.time.Instant;

import model.card.Card;
import model.transaction.Transaction;
import model.transaction.TransactionDAO;

public class InterbankSubsystemController {
	private InterbankBoundry interbankBoundry = new InterbankBoundry();
	
	public Transaction pay(Card card, long amount, String content) {
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setContent(content);
		transaction.setTimeCreated(Instant.now());
		
		TransactionDAO transactionDao = new TransactionDAO();
		int transactionId = transactionDao.saveTransaction(transaction);
		
		transaction.setId(transactionId);
		
		return transaction;
	}
	
	public Transaction refund(Card card, long amount, String content) {
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setContent(content);
		transaction.setTimeCreated(Instant.now());
		
		TransactionDAO transactionDao = new TransactionDAO();
		int transactionId = transactionDao.saveTransaction(transaction);
		
		transaction.setId(transactionId);
		return transaction;
	}
}
