package net.lakis.webapi;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionDB {
	
	static Connection connection = null;
	static String dbName = "";
	static String url = "jdbc:mysql://localhost:3306" + dbName;
	static String username = "root";
	static String password = "root";
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, 
	ClassNotFoundException, SQLException, IllegalArgumentException, InvocationTargetException, 
	NoSuchMethodException, SecurityException {
		
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		connection = DriverManager.getConnection(url, username, password);
		PreparedStatement ps = connection.prepareStatement("INSERT INTO `webapisdb`.`names` (`fname`, `lname`) VALUES (?, ?);");
		ps.setString(1, "joe");
		ps.setString(2, "wehbe");
		ps.executeUpdate();
		
		int status = ps.executeUpdate();
		
		if(status != 0) {
			System.out.println("Success");
		}
		
	}
}
