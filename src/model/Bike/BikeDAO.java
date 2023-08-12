package model.bike;
import java.sql.*;
import java.util.ArrayList;

import data.ConnectDatabase;


public class BikeDAO {
	public static ArrayList<Bike> viewDockBike(int dockId) throws SQLException{
		ConnectDatabase.connect();
		Statement stm = ConnectDatabase.connect().createStatement();
//		ResultSet result = stm.executeQuery("select * from bikes where ")
	}
}
