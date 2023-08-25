package subsystem;

import common.exception.interbank.PaymentException;
import common.exception.interbank.UnrecognizedException;
import model.card.Card;
import model.transaction.Transaction;

public interface InterbankInterface {
	public Transaction pay(Card card, long amount, String content) throws PaymentException, UnrecognizedException;
	public Transaction refund(Card card, long amount, String content) throws PaymentException, UnrecognizedException;
}