package subsystem;

import model.card.Card;
import model.transaction.Transaction;
import subsystem.interbank.InterbankSubsystemController;

public class InterbankSubsystem implements InterbankInterface {
	private InterbankSubsystemController controller = new InterbankSubsystemController();
	
	@Override
	public Transaction pay(Card card, long amount, String content) {
		return controller.pay(card, amount, content);
	}

	@Override
	public Transaction refund(Card card, long amount, String content) {
		return controller.refund(card, amount, content);
	}
}
