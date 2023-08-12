package model.dock;

import java.sql.*;
import java.util.ArrayList;

import db.DBConnection;

public class DockDAO {
	public static ArrayList<Dock> requestData() throws SQLException{
    	Statement stm = DBConnection.connect().createStatement();
    	ResultSet result = stm.executeQuery("select * from docks");
    	ArrayList<Dock> docksList = new ArrayList<Dock>();
    	while(result.next()) {
    		Dock dock = new Dock();
    		dock.setId(result.getInt("id"));
    		dock.setName(result.getString("name"));
    		dock.setAddress(result.getString("address"));
    		dock.setArea(result.getFloat("area"));
    		dock.setNumOfAvailableBikes(result.getInt("numOfAvailableBikes"));
    		dock.setNumOfEmptyPoints(result.getInt("numOfEmptyPoints"));
    		
    		docksList.add(dock);
    	}
    	return docksList;
    }
}
