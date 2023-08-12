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
}
