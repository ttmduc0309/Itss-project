package model.rentalInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import data.ConnectDatabase;

public class RentalInfoDAO {
	public RentalInfo getRentalInfoById(int rentalId) {
		RentalInfo rentalInfo = new RentalInfo();
		try {
			Statement stm = ConnectDatabase.connect().createStatement();
			ResultSet result = stm.executeQuery("SELECT * FROM rentalInfo WHERE id = " + rentalId);
			result.next();
			
			rentalInfo.setId(result.getInt("id"));
			rentalInfo.setBikeId(result.getInt("bikeId"));
			rentalInfo.setRentDockId(result.getInt("rentDockId"));
			rentalInfo.setRentStartTime(result.getObject("rentStartTime", Timestamp.class));
			rentalInfo.setDepositAmount(result.getLong("depositAmount"));
			
			if (result.getInt("returnDockId") != 0) {
				rentalInfo.setReturnDockId(result.getInt("returnDockId"));
				rentalInfo.setRentEndTime(result.getObject("rentEndTime", Timestamp.class));
				rentalInfo.setRentalFee(result.getLong("rentalFee"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rentalInfo;
	}
	
	public int createRentalInfo(RentalInfo rentalInfo) {
		int id = -1;
		String generatedColumns[] = { "id" };
		String sql = "INSERT INTO rentalInfo (bikeId, rentDockId, rentStartTime, depositAmount) VALUES (?,?,?,?)";
		try (PreparedStatement preparedStm = ConnectDatabase.connect().prepareStatement(sql, generatedColumns)){
			preparedStm.setInt(1, rentalInfo.getBikeId());
			preparedStm.setInt(2, rentalInfo.getRentDockId());
			preparedStm.setObject(3, rentalInfo.getRentStartTime());
			preparedStm.setLong(4, rentalInfo.getDepositAmount());

			preparedStm.executeUpdate();
			ResultSet rs = preparedStm.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public void updateRentalInfo(RentalInfo rentalInfo) {
		String sql = "UPDATE rentalInfo SET returnDockId = ?, rentEndTime = ?, rentalFee = ? WHERE id = " + rentalInfo.getId();
		try (PreparedStatement preparedStm = ConnectDatabase.connect().prepareStatement(sql)){
			preparedStm.setInt(1, rentalInfo.getReturnDockId());
			preparedStm.setObject(2, rentalInfo.getRentEndTime());
			preparedStm.setLong(3, rentalInfo.getRentalFee());
			
			preparedStm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}