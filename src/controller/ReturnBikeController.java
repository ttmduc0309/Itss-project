package controller;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import common.exception.InvalidPaymentDetailsException;
import model.bike.*;
import model.dock.*;
import model.rentalInfo.*;
import model.card.Card;
import model.transaction.Transaction;
import subsystem.*;

public class ReturnBikeController {
	public List<Dock> getUnfilledDocks(){
		DockDAO dockDao = new DockDAO();
		List<Dock> docks = dockDao.getUnfilledDocks();
		return docks;
	}
	
	public RentalInfo finalizeRentalDetails(int rentalId, int bikeId, int dockId) {
		addRentedBikeToNewDock(bikeId, dockId);
		
		RentalInfoDAO riDao = new RentalInfoDAO();
		
		// get rental info
		RentalInfo rentalInfo = riDao.getRentalInfoById(rentalId);
		rentalInfo.setReturnDockId(dockId);
		rentalInfo.setRentEndTime(Instant.now());
		
		// calculate rental fee
		long rentalFee = calculateRentalFee(rentalInfo.getRentStartTime(), rentalInfo.getRentEndTime(), bikeId);
		rentalInfo.setRentalFee(rentalFee);
		
		riDao.updateRentalInfo(rentalInfo);
		
		return rentalInfo;
	}
	
	public Transaction makePayment(Map<String, String> transactionPayload, int rentalId) throws InvalidPaymentDetailsException {
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
	
	private void validatePaymentDetails(String holderName, String cardNumber, String cvv, String expirationDate) throws InvalidPaymentDetailsException {
		return;
	}
	
	private void addRentedBikeToNewDock(int bikeId, int dockId) {
		BikeDAO bikeDao = new BikeDAO();
		
		bikeDao.addBikeToDock(bikeId, dockId);
	}
	
	private long calculateRentalFee(Instant startTime, Instant endTime, int bikeId) {
		Duration duration = Duration.between(startTime, endTime);
		long minutes = duration.toMinutes();
		long amount;
		
		if (minutes < 10) {
			amount = 0;
		} else if (minutes <= 30) {
			amount = 10000;
		} else {
			amount = 10000 + Math.ceilDiv(minutes, 15) * 3000;
		}
		
		BikeDAO bikeDao = new BikeDAO();
		Bike bike = bikeDao.getBikeById(bikeId);
		if (bike instanceof StandardBike) {
			return amount;
		}
		return (long) (amount * 1.5);
	}
	
	private long calculateAmount(int rentalId) {
		RentalInfoDAO riDao = new RentalInfoDAO();
		
		RentalInfo rentalInfo = riDao.getRentalInfoById(rentalId);
		
		return rentalInfo.getRentalFee() - rentalInfo.getDepositAmount();
	}
}
