package model.bike;
import java.sql.*;
import java.util.ArrayList;

import data.ConnectDatabase;
import model.dock.Dock;


public class BikeDAO {
	public static ArrayList<Bike> viewDockBike(int dockId) throws SQLException{
		ConnectDatabase.connect();
		Statement stm = ConnectDatabase.connect().createStatement();
		ResultSet result = stm.executeQuery("select * from bikes where ");
		ArrayList<Bike> bikesList = new ArrayList<Bike>();
    	while(result.next()) {
    		Bike bike = new Bike();
    		
    		bikesList.add(bike);
    	}
    	return bikesList;
	}
}
