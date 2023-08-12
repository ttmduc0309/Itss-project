package model.bike;

import java.sql.*;

import db.DBConnection;

public class BikeDAO {
	public String getBikeTypeName(int typeId) throws SQLException {
		Statement stm = DBConnection.connect().createStatement();
		ResultSet result = stm.executeQuery("SELECT name FROM bike_types WHERE id = " + typeId);
		result.next();
		return result.getString("name");
	}
}
