package model.dock;

import java.util.List;

import model.bike.Bike;

public class Dock {
	private int dockId;
	private String dockName;
	private String dockAddress;
	private float dockArea;
	private int dockNumBike;
	private int dockEmptyPoints;
	private String dockImgSrc;
	private List<Bike> bikeList;
	
	
	public String getDockName() {
		return dockName;
	}
	public void setDockName(String dockName) {
		this.dockName = dockName;
	}
	public String getDockAddress() {
		return dockAddress;
	}
	public void setDockAddress(String dockAddress) {
		this.dockAddress = dockAddress;
	}
	public float getDockArea() {
		return dockArea;
	}
	public void setDockArea(float dockArea) {
		this.dockArea = dockArea;
	}
	public int getDockNumBike() {
		return dockNumBike;
	}
	public void setDockNumBike(int dockNumBike) {
		this.dockNumBike = dockNumBike;
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
	public int getDockEmptyPoints() {
		return dockEmptyPoints;
	}
	public void setDockEmptyPoints(int dockEmptyPoints) {
		this.dockEmptyPoints = dockEmptyPoints;
	}
	public int getNumOfAvailableBikes() {
		// TODO Auto-generated method stub
		return this.dockEmptyPoints;
	}
	
}