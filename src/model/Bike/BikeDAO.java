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
			Bike bike = new Bike();
			bike.setId(result.getString("id"));
			bike.setTypeId(result.getInt("typeid"));
			bike.setLicensePlate(result.getString("licenseplate"));
			bike.setBarCode(result.getString("barcode"));
			bike.setPrice(result.getLong("price"));
			
			bikeList.add(bike);
		}
		
		return bikeList;
	}
}
