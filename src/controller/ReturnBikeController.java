package controller;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import common.exception.InvalidPaymentDetailsException;
import model.bike.*;
import model.dock.*;
import model.rentalInfo.*;
import model.card.Card;
import model.card.CardValidator;
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
		rentalInfo.setRentEndTime(Timestamp.from(Instant.now()));
		
		// calculate rental fee
		long rentalFee = calculateRentalFee(rentalInfo.getRentStartTime(), rentalInfo.getRentEndTime(), bikeId);
		rentalInfo.setRentalFee(rentalFee);
		
		riDao.updateRentalInfo(rentalInfo);
		
		return rentalInfo;
	}
	
	
	
	private void addRentedBikeToNewDock(int bikeId, int dockId) {
		BikeDAO bikeDao = new BikeDAO();
		
		bikeDao.addBikeToDock(bikeId, dockId);
	}
	
	private long calculateRentalFee(Timestamp startTime, Timestamp endTime, int bikeId) {
		Duration duration = Duration.between(startTime.toInstant(), endTime.toInstant());
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
	
}