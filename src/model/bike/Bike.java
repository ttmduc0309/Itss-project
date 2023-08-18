package model.bike;

public abstract class Bike {
	private int id;
	private String licensePlate;
	private String barCode;
	private boolean isBeingRented;
	private int typeId;
	private long price;
	private int dockId;
	private String BikeImg;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public boolean isBeingRented() {
		return isBeingRented;
	}
	public void setBeingRented(boolean isBeingRented) {
		this.isBeingRented = isBeingRented;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getBikeImg() {
		return BikeImg;
	}
	public void setBikeImg(String bikeImg) {
		BikeImg = bikeImg;
	}
	
	public int getDockId() {
		return dockId;
	}
	public void setDockId(int dockId) {
		this.dockId = dockId;
	}

	public Bike() {
		
	}

	public abstract String typeString();
	
}