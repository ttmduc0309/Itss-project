package data;
import java.sql.*;
import java.util.Properties;
import java.io.*;

public class ConnectDatabase {
	public static Connection connect() {
		String url = "";
		String username = "";
		String password = "";
		
		try (InputStream input = new FileInputStream("src/resources/config.properties")){
			Properties prop = new Properties();
			prop.load(input);
			url = prop.getProperty("db.url");
			username = prop.getProperty("db.username");
			password = prop.getProperty("db.password");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connect successfully");
		}catch(SQLException e){
			System.out.println(e);
		}
		return conn;
	}
}
