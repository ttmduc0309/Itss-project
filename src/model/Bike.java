package model;

public class Bike {
	private String BikeID;
	private String BarCode;
	private String BikeType;
	private String CurrentDock;
	
	public String getBikeID() {
		return BikeID;
	}
	public void setBikeID(String bikeID) {
		BikeID = bikeID;
	}
	public String getBarCode() {
		return BarCode;
	}
	public void setBarCode(String barCode) {
		BarCode = barCode;
	}
	public String getBikeType() {
		return BikeType;
	}
	public void setBikeType(String bikeType) {
		BikeType = bikeType;
	}
	public String getCurrentDock() {
		return CurrentDock;
	}
	public void setCurrentDock(String currentDock) {
		CurrentDock = currentDock;
	}
	
}
