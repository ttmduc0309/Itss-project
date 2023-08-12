package model.Bike;
import javax.sql.DataSource;


public class Bike {
	private String BikeID;
	private String BarCode;
	private int BikeType;
	private String LicensePlate;
	private int price;
	private String BikeImg;
	
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
	public int getBikeType() {
		return BikeType;
	}
	public void setBikeType(int bikeType) {
		BikeType = bikeType;
	}
	public String getLicensePlate() {
		return LicensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		LicensePlate = licensePlate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getBikeImg() {
		return BikeImg;
	}
	public void setBikeImg(String bikeImg) {
		BikeImg = bikeImg;
	}
	
	
}