package controller;

import java.util.Map;

import common.exception.InvalidPaymentDetailsException;
import model.bike.BikeDAO;
import model.card.Card;
import model.card.CardValidator;
import model.rentalInfo.RentalInfo;
import model.rentalInfo.RentalInfoDAO;
import model.transaction.Transaction;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;

public class PaymentController {

	public void validatePaymentDetails(String holderName, String cardNumber, String cvv, String expirationDate) throws InvalidPaymentDetailsException {
		CardValidator cardValidator = new CardValidator();
		cardValidator.validateRequiredFields(holderName, cardNumber, cvv, expirationDate);
		cardValidator.validateCardNumber(cardNumber);
		cardValidator.validateCardHolderName(holderName);
		cardValidator.validateSecurityCode(cvv);
		cardValidator.validateExpirationDate(expirationDate);
		return;
	}
	

	public Transaction makeDepositPayment(Map<String, String> transactionPayLoad, int bikeId ){
		String holderName = transactionPayLoad.get("holderName");
		String cardNumber = transactionPayLoad.get("cardNumber");
		String cvv = transactionPayLoad.get("cvv");
		String expirationDate = transactionPayLoad.get("expirationDate");
		String content = transactionPayLoad.get("content");
		
		BikeDAO dao = new BikeDAO();
		long amount = (long) (dao.getBikeById(bikeId).getPrice() * 0.4);
		
		Card card = new Card();
		card.setCardHolderName(holderName);
		card.setCardNumber(cardNumber);
		card.setSecurityCode(cvv);
		card.setExpirationDate(expirationDate);
		
		InterbankInterface interbank = new InterbankSubsystem();
		return interbank.pay(card, amount, content);
	}
	
	public Transaction makeRentalPayment(Map<String, String> transactionPayload, int rentalId) throws InvalidPaymentDetailsException {
		String holderName = transactionPayload.get("holderName");
		String cardNumber = transactionPayload.get("cardNumber");
		String cvv = transactionPayload.get("cvv");
		String expirationDate = transactionPayload.get("expirationDate");
		String content = transactionPayload.get("content");
		
		validatePaymentDetails(holderName, cardNumber, cvv, expirationDate);
		
		Card card = new Card();
		card.setCardHolderName(holderName);
		card.setCardNumber(cardNumber);
		card.setSecurityCode(cvv);
		card.setExpirationDate(expirationDate);
		
		InterbankInterface interbank = new InterbankSubsystem();
		
		long amount = calculateAmount(rentalId);
		if (amount >= 0) {
			return interbank.pay(card, amount, content);
		} else {
			return interbank.refund(card, amount, content);
		}
	}
	
	
	private long calculateAmount(int rentalId) {
		RentalInfoDAO riDao = new RentalInfoDAO();
		
		RentalInfo rentalInfo = riDao.getRentalInfoById(rentalId);
		
		return rentalInfo.getRentalFee() - rentalInfo.getDepositAmount();
	}
	
	
}
