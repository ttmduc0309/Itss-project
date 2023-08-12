package model.transaction;

import java.sql.*;

import db.DBConnection;

public class TransactionDAO {
	public int saveTransaction(Transaction transaction) {
		String[] returnId = {"id"};
		int transactionId = 0;
		String sql = "INSERT INTO transactions (content, amount, timeCreated) VALUES (?,?,?)";
		try (PreparedStatement preparedStm = DBConnection.connect().prepareStatement(sql, returnId)){
			preparedStm.setString(1, transaction.getContent());
			preparedStm.setLong(2, transaction.getAmount());
			preparedStm.setObject(3, transaction.getTimeCreated());
			
			preparedStm.executeUpdate();
			
			try (ResultSet rs = preparedStm.getGeneratedKeys()){
				if (rs.next()) {
					transactionId = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return transactionId;
	}
}
