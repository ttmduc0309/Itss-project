package model.bike;

import java.sql.*;

import db.DBConnection;

public class BikeDAO {
	public Bike getBikeById(int bikeId) {
		Bike bike = null;
		try {
			Statement stm = DBConnection.connect().createStatement();
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
	
	public String getBikeTypeName(int typeId) {
		String typeName = "";
		try {
			Statement stm = DBConnection.connect().createStatement();
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
			Statement stm = DBConnection.connect().createStatement();
			stm.executeUpdate("UPDATE bikes SET dockId = " + dockId);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
