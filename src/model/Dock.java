package model;

import java.util.List;

public class Dock {
	private String dockName;
	private String dockDesc;
	private String dockImgSrc;
	private List<Bike> bikeList;
	
	public String getDockName() {
		return dockName;
	}
	public void setDockName(String dockName) {
		this.dockName = dockName;
	}
	public String getDockDesc() {
		return dockDesc;
	}
	public void setDockDesc(String dockDesc) {
		this.dockDesc = dockDesc;
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
