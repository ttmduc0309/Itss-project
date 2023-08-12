package model.rentalInfo;

import java.time.Instant;

public class RentalInfo {
	private int id;
	private int bikeId;
	private int rentDockId;
	private int returnDockId;
	private long depositAmount;
	private long rentalFee;
	private Instant rentStartTime;
	private Instant rentEndTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBikeId() {
		return bikeId;
	}
	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}
	public int getRentDockId() {
		return rentDockId;
	}
	public void setRentDockId(int rentDockId) {
		this.rentDockId = rentDockId;
	}
	public int getReturnDockId() {
		return returnDockId;
	}
	public void setReturnDockId(int returnDockId) {
		this.returnDockId = returnDockId;
	}
	public long getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(long depositAmount) {
		this.depositAmount = depositAmount;
	}
	public long getRentalFee() {
		return rentalFee;
	}
	public void setRentalFee(long rentalFee) {
		this.rentalFee = rentalFee;
	}
	public Instant getRentStartTime() {
		return rentStartTime;
	}
	public void setRentStartTime(Instant rentStartTime) {
		this.rentStartTime = rentStartTime;
	}
	public Instant getRentEndTime() {
		return rentEndTime;
	}
	public void setRentEndTime(Instant rentEndTime) {
		this.rentEndTime = rentEndTime;
	}
}
