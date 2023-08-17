package controller;

import java.util.Map;

import common.exception.InvalidPaymentDetailsException;
import model.bike.BikeDAO;
import model.card.Card;
import model.card.CardValidator;
import model.rentalInfo.RentalInfo;
import model.transaction.Transaction;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;
import java.time.Instant;

public class RentBikeController {
	
	private void removeRentedBikeFromDock(int bikeId) {
		BikeDAO dao = new BikeDAO();
		dao.removeBikeFromDock(bikeId);
	}
	
	public Transaction makePayment(Map<String, String> transactionPayLoad, int rentalId, int bikeId ) {
		String holderName = transactionPayLoad.get("holderName");
		String cardNumber = transactionPayLoad.get("cardNumber");
		String cvv = transactionPayLoad.get("cvv");
		String expirationDate = transactionPayLoad.get("expirationDate");
		String content = transactionPayLoad.get("content");
		
//		validatePaymentDetails(holderName, cardNumber, cvv, expirationDate);
		
		BikeDAO dao = new BikeDAO();
		long amount = dao.getBikeById(bikeId).getDepositPrice();
		
		Card card = new Card();
		card.setCardHolderName(holderName);
		card.setCardNumber(cardNumber);
		card.setSecurityCode(cvv);
		card.setExpirationDate(expirationDate);
		
		InterbankInterface interbank = new InterbankSubsystem();
		return interbank.pay(card, amount, content);
	}
	
	public RentalInfo createNewRentalInfo(int rentalId, int bikeId, int dockId) {
		removeRentedBikeFromDock(bikeId);
		
		RentalInfo rentalInfo = new RentalInfo();
		
		rentalInfo.setRentStartTime(Instant.now());
		rentalInfo.setRentDockId(dockId);
		rentalInfo.setBikeId(bikeId);
		
		return rentalInfo;
	}
	
	private void validatePaymentDetails(String holderName, String cardNumber, String cvv, String expirationDate) throws InvalidPaymentDetailsException {
		CardValidator cardValidator = new CardValidator();
		cardValidator.validateCardHolderName(holderName);
		cardValidator.validateCardNumber(cardNumber);
		cardValidator.validateSecurityCode(cvv);
		cardValidator.validateExpirationDate(expirationDate);
		return;
	}
}
