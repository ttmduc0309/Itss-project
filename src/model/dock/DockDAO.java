package model.dock;

import java.sql.*;
import java.util.ArrayList;

import data.ConnectDatabase;

public class DockDAO {
	public static ArrayList<Dock> requestData() throws SQLException{
    	Statement stm = ConnectDatabase.connect().createStatement();
    	ResultSet result = stm.executeQuery("select * from docks");
    	ArrayList<Dock> docksList = new ArrayList<Dock>();
    	while(result.next()) {
    		Dock dock = new Dock();
    		dock.setDockName(result.getString("name"));
    		dock.setDockAddress(result.getString("address"));
    		dock.setDockArea(result.getFloat("area"));
    		dock.setDockNumBike(result.getInt("numOfAvailableBikes"));
    		dock.setDockEmptyPoints(result.getInt("numOfEmptyPoints"));
    		
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
}
