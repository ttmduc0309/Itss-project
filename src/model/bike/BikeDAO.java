package model.bike;
import java.sql.*;
import java.util.ArrayList;

import data.ConnectDatabase;


public class BikeDAO {
	public static ArrayList<Bike> getListBikeInDock(int dockId) throws SQLException{
		ConnectDatabase.connect();
		Statement stm = ConnectDatabase.connect().createStatement();
		ResultSet result = stm.executeQuery("select * from bikes where bikes.dockid = " + dockId);
		ArrayList<Bike> bikeList = new ArrayList<Bike>();
		while(result.next()) {
			Bike bike = null;
			int type = result.getInt("typeId");
			if (type == 1) {
				bike = new StandardBike();
			} else if (type == 2) {
				bike = new StandardEBike(result.getInt("remainingBattery"));
			} else {
				bike = new TwinBike();
			}
			bike.setId(result.getInt("id"));
			bike.setTypeId(result.getInt("typeid"));
			bike.setLicensePlate(result.getString("licenseplate"));
			bike.setBarCode(result.getString("barcode"));
			bike.setPrice(result.getLong("price"));
			bikeList.add(bike);
		}
		
		return bikeList;
	}
	
	public Bike getBikeById(int bikeId) {
		Bike bike = null;
		try {
			Statement stm = ConnectDatabase.connect().createStatement();
			ResultSet result = stm.executeQuery("SELECT * FROM bikes WHERE id = " + bikeId);
			result.next();
			int type = result.getInt("typeId");
			if (type == 1) {
				bike = new StandardBike();
			} else if (type == 2) {
				bike = new StandardEBike(result.getInt("remainingBattery"));
			} else {
				bike = new TwinBike();
			}
			
			bike.setId(result.getInt("id"));
			bike.setLicensePlate(result.getString("licensePlate"));
			bike.setBarCode(result.getString("barCode"));
			bike.setBeingRented(result.getBoolean("isBeingRented"));
			bike.setTypeId(result.getInt("typeId"));
			bike.setPrice(result.getLong("price"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bike;
	}
	
	public Bike getBikeByBarcode(String barcode) throws SQLException{
		Bike bike = null;
		Statement stm = ConnectDatabase.connect().createStatement();
		ResultSet result = stm.executeQuery("SELECT * FROM bikes WHERE barcode = '" + barcode + "'");
		result.next();
		int type = result.getInt("typeId");
		if (type == 1) {
			bike = new StandardBike();
		} else if (type == 2) {
			bike = new StandardEBike(result.getInt("remainingBattery"));
		} else {
			bike = new TwinBike();
		}
		
		bike.setId(result.getInt("id"));
		bike.setLicensePlate(result.getString("licensePlate"));
		bike.setBarCode(result.getString("barCode"));
		bike.setBeingRented(result.getBoolean("isBeingRented"));
		bike.setTypeId(result.getInt("typeId"));
		bike.setPrice(result.getLong("price"));
		
		return bike;
	}
	
	public String getBikeTypeName(int typeId) {
		String typeName = "";
		try {
			Statement stm = ConnectDatabase.connect().createStatement();
			ResultSet result = stm.executeQuery("SELECT name FROM bike_types WHERE id = " + typeId);
			result.next();
			typeName = result.getString("name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return typeName;
	}
	
	public void addBikeToDock(int bikeId, int dockId) {
		try {
			Statement stm = ConnectDatabase.connect().createStatement();
			stm.executeUpdate("UPDATE bikes SET dockId = " + dockId + ", isBeingRented = FALSE where id = " + bikeId);
			stm.executeUpdate("UPDATE docks SET numOfAvailableBikes = numOfAvailableBikes + 1,"
					+ " numOfEmptyPoints = numOfEmptyPoints - 1 WHERE id = " + dockId);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeBikeFromDock(int bikeId, int dockId) {
		try {
			Statement stm = ConnectDatabase.connect().createStatement();
			stm.executeUpdate("UPDATE bikes SET dockId = null, isBeingRented = TRUE where id = " + bikeId);
			stm.executeUpdate("UPDATE docks SET numOfAvailableBikes = numOfAvailableBikes - 1,"
					+ " numOfEmptyPoints = numOfEmptyPoints + 1 WHERE id = " + dockId);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
