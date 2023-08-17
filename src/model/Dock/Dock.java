package model.dock;

import java.util.List;

import model.bike.Bike;

public class Dock {
	private int id;
	private String name;
	private String address;
	private float area;
	private int numOfAvailableBikes;
	private int numOfEmptyPoints;
	private String dockImgSrc;
	private List<Bike> bikeList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public float getArea() {
		return area;
	}
	public void setArea(float area) {
		this.area = area;
	}
	public int getNumOfAvailableBikes() {
		return numOfAvailableBikes;
	}
	public void setNumOfAvailableBikes(int numOfAvailableBikes) {
		this.numOfAvailableBikes = numOfAvailableBikes;
	}
	public int getNumOfEmptyPoints() {
		return numOfEmptyPoints;
	}
	public void setNumOfEmptyPoints(int numOfEmptyPoints) {
		this.numOfEmptyPoints = numOfEmptyPoints;
	}
	
	public String getDockImgSrc() {
		return dockImgSrc;
	}
	public void setDockImgSrc(String dockImgSrc) {
		this.dockImgSrc = dockImgSrc;
	}
	
	public List<Bike> getBikeList() {
		return bikeList;
	}
	public void setBikeList(List<Bike> bikeList) {
		this.bikeList = bikeList;
	}
	
}