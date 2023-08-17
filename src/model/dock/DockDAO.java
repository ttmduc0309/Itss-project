package model.dock;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import data.ConnectDatabase;

public class DockDAO {
	public static ArrayList<Dock> requestData() throws SQLException{
    	Statement stm = ConnectDatabase.connect().createStatement();
    	ResultSet result = stm.executeQuery("select * from docks");
    	ArrayList<Dock> docksList = new ArrayList<Dock>();
    	while(result.next()) {
    		Dock dock = new Dock();
    		dock.setName(result.getString("name"));
    		dock.setAddress(result.getString("address"));
    		dock.setArea(result.getFloat("area"));
    		dock.setNumOfAvailableBikes(result.getInt("numofavailablebikes"));
    		dock.setNumOfEmptyPoints(result.getInt("numOfEmptyPoints"));
    		
    		docksList.add(dock);
    	}
    	return docksList;
    }
	public static int findDockId(String dockName) throws SQLException {
		int dockId = -1;
		try (Statement stm = ConnectDatabase.connect().createStatement();
		         ResultSet result = stm.executeQuery("SELECT id FROM docks WHERE name = '" + dockName + "'")) {

		        if (result.next()) {
		            dockId = result.getInt("id");
		        }
		}
		return dockId;
	}
	public List<Dock> getUnfilledDocks() {
		List<Dock> docks = new ArrayList<Dock>();
		try {
			Statement stm = ConnectDatabase.connect().createStatement();
	    	ResultSet result = stm.executeQuery("SELECT * FROM docks WHERE numOfEmptyPoints > 0");
	    	while(result.next()) {
	    		Dock dock = new Dock();
	    		dock.setId(result.getInt("id"));
	    		dock.setName(result.getString("name"));
	    		dock.setAddress(result.getString("address"));
	    		dock.setArea(result.getFloat("area"));
	    		dock.setNumOfAvailableBikes(result.getInt("numOfAvailableBikes"));
	    		dock.setNumOfEmptyPoints(result.getInt("numOfEmptyPoints"));
	    		
	    		docks.add(dock);
	    	}

		} catch(SQLException e) {
			e.printStackTrace();
		}
		
    	return docks;
	}
}
