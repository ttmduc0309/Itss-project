package data;
import java.sql.*;

public class ConnectDatabase {
	private static String url = "jdbc:postgresql://localhost/RentalBike";
	private static String user = "postgres";
	private static String password = "Ducchi0204";
	
	public static Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
//			System.out.println("Connect successfully");
		}catch(SQLException e){
			System.out.println(e); 	
		}
		return conn;
	}
}
