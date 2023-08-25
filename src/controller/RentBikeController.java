package controller;

import java.util.Map;

import common.exception.InvalidPaymentDetailsException;
import model.bike.Bike;
import model.bike.BikeDAO;
import model.card.Card;
import model.card.CardValidator;
import model.rentalInfo.RentalInfo;
import model.rentalInfo.RentalInfoDAO;
import model.transaction.Transaction;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;

import java.sql.Timestamp;
import java.time.Instant;

public class RentBikeController {
	
	private void removeRentedBikeFromDock(int bikeId, int dockId) {
		BikeDAO dao = new BikeDAO();
		dao.removeBikeFromDock(bikeId, dockId);
	}
	
	
	public RentalInfo createNewRentalInfo(int bikeId, int dockId){
		removeRentedBikeFromDock(bikeId, dockId);
		BikeDAO bikeDao = new BikeDAO();
		RentalInfoDAO riDao = new RentalInfoDAO();
		Bike bike = bikeDao.getBikeById(bikeId);
		RentalInfo rentalInfo = new RentalInfo();
		
		rentalInfo.setRentStartTime(Timestamp.from(Instant.now()));
		rentalInfo.setRentDockId(dockId);
		rentalInfo.setBikeId(bikeId);
		rentalInfo.setDepositAmount(calculateDepositAmount(bike.getPrice()));
		
		int id = riDao.createRentalInfo(rentalInfo);
		rentalInfo.setId(id);
		
		return rentalInfo;
	}
	
	private long calculateDepositAmount(long price) {
		return (long) (price * 0.4);
	}
	
}
