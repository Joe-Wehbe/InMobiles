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
	
	public void listAll() throws SQLException {
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM `webapisdb`.`names`;");
		
		int status = ps.executeUpdate();
		
		if(status != 0) {
			System.out.println("Success");
		}
		else {
			System.out.println("Failure");
		}
	}
	
	public void searchNameById(String id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM `webapisdb`.`names` WHERE id = ?");
		ps.setInt(1, Integer.parseInt(id));
		
		int status = ps.executeUpdate();
		
		if(status != 0) {
			System.out.println("Success");
		}
		else {
			System.out.println("Failure");
		}
	}
	
	public void addName(String fname, String lname) throws SQLException {
		
		PreparedStatement ps = connection.prepareStatement("INSERT INTO `webapisdb`.`names` (`fname`, `lname`) VALUES (?, ?);");
		ps.setString(1, fname);
		ps.setString(2, lname);
		ps.executeUpdate();
		
		int status = ps.executeUpdate();
		
		if(status != 0) {
			System.out.println("Name Added");
		}
		else {
			System.out.println("Failed to add name");
		}
	}
	
	public void updateName(String id, String fname, String lname) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE `webapisdb`.`names` SET `fname`=?, `lname`=? WHERE `id`=?");
		ps.setString(1, fname);
		ps.setString(2, lname);
		ps.setInt(3, Integer.parseInt(id));
		
		int status = ps.executeUpdate();
		
		if(status != 0) {
			System.out.println("Name updated");
		}
		else {
			System.out.println("Failed to update name");
		}

	}
	
	public void deleteName(String id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("DELETE FROM `webapisdb`.`names` WHERE `id`=?");
		ps.setInt(1, Integer.parseInt(id));
		
		int status = ps.executeUpdate();
		
		if(status != 0) {
			System.out.println("Name deleted");
		}
		else {
			System.out.println("Failed to delete name");
		}

	}
	
	public void connect() throws InstantiationException, IllegalAccessException, IllegalArgumentException, 
	InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		connection = DriverManager.getConnection(url, username, password);
	}
	
}
