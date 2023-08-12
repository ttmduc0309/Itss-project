package model.rentalInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;

import db.DBConnection;

public class RentalInfoDAO {
	public RentalInfo getRentalInfoById(int rentalId) {
		RentalInfo rentalInfo = new RentalInfo();
		try {
			Statement stm = DBConnection.connect().createStatement();
			ResultSet result = stm.executeQuery("SELECT * FROM rentalInfo WHERE id = " + rentalId);
			result.next();
			
			rentalInfo.setId(result.getInt("id"));
			rentalInfo.setBikeId(result.getInt("bikeId"));
			rentalInfo.setRentDockId(result.getInt("rentDockId"));
			rentalInfo.setRentStartTime(result.getObject("rentStartTime", Instant.class));
			rentalInfo.setDepositAmount(result.getLong("depositAmount"));
			
			if (result.getInt("returnDockId") != 0) {
				rentalInfo.setReturnDockId(result.getInt("returnDockId"));
				rentalInfo.setRentEndTime(result.getObject("rentEndTime", Instant.class));
				rentalInfo.setRentalFee(result.getLong("rentalFee"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rentalInfo;
	}
	
	public void updateRentalInfo(RentalInfo rentalInfo) {
		String sql = "UPDATE rentalInfo SET returnDockId = ?, rentEndTime = ?, rentalFee = ? WHERE id = " + rentalInfo.getId();
		try (PreparedStatement preparedStm = DBConnection.connect().prepareStatement(sql)){
			preparedStm.setInt(1, rentalInfo.getReturnDockId());
			preparedStm.setObject(2, rentalInfo.getRentEndTime());
			preparedStm.setLong(3, rentalInfo.getRentalFee());
			
			preparedStm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
