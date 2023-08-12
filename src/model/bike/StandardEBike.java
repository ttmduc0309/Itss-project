package model.bike;

public class StandardEBike extends Bike {
	private int remainingBattery;
	
	public StandardEBike() {}
	
	public StandardEBike(int remainingBattery) {
		this.remainingBattery = remainingBattery;
	}
	
	public int getRemainingBattery() {
		return remainingBattery;
	}
	
	public void setRemainingBattery(int remainingBattery) {
		this.remainingBattery = remainingBattery;
	}
	
	@Override
	public String typeString() {
		return "StandardEBike";
	}
}
