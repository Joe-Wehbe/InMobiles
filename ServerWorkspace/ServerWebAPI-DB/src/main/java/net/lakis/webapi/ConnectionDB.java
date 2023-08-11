package net.lakis.webapi;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionDB {

	static String dbName = "";
	static String url = "jdbc:mysql://localhost:3306" + dbName;
	static String username = "root";
	static String password = "root";

	public ArrayList<Name> listAll()
			throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {

		Connection connection = connect();
		PreparedStatement ps;
		ResultSet result;

		ps = connection.prepareStatement("SELECT * FROM `webapisdb`.`names`;");
		result = ps.executeQuery();

		String fname = "";
		String lname = "";
		String id = "";
		ArrayList<Name> names = new ArrayList<Name>();

		while (result.next()) {
			id = result.getString(1);
			fname = result.getString(2);
			lname = result.getString(3);
			names.add(new Name(id, fname, lname));
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if (result != null)
				result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return names;
	}

	public Name searchNameById(String id) throws SQLException, InstantiationException, 
	IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, 
	SecurityException, ClassNotFoundException {
		
		Connection connection = connect();
		PreparedStatement ps;
		ResultSet result;
		
		ps = connection.prepareStatement("SELECT * FROM `webapisdb`.`names` WHERE id = ?");
		ps.setInt(1, Integer.parseInt(id));

		result = ps.executeQuery();

		String fname = "";
		String lname = "";

		while (result.next()) {
			fname = result.getString(2);
			lname = result.getString(3);
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if (result != null)
				result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return new Name(id, fname, lname);
	}

	public void addName(String fname, String lname) throws SQLException, InstantiationException, IllegalAccessException, 
	IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {	
		
		Connection connection = connect();
		PreparedStatement ps;

		ps = connection.prepareStatement("INSERT INTO `webapisdb`.`names` (`fname`, `lname`) VALUES (?, ?);");
		ps.setString(1, fname);
		ps.setString(2, lname);

		int status = ps.executeUpdate();

		if (status != 0) {
			System.out.println("Name Added");
		} else {
			System.out.println("Failed to add name");
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (Exception e) {
		}
		
		try {
			if (ps != null)
				ps.close();
		} catch (Exception e) {
		}
	}

	public void updateName(String id, String fname, String lname) throws SQLException, InstantiationException, IllegalAccessException, 
	IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		
		Connection connection = connect();
		PreparedStatement ps;
		
		ps = connection.prepareStatement("UPDATE `webapisdb`.`names` SET `fname`=?, `lname`=? WHERE `id`=?");
		ps.setString(1, fname);
		ps.setString(2, lname);
		ps.setInt(3, Integer.parseInt(id));

		int status = ps.executeUpdate();

		if (status != 0) {
			System.out.println("Name updated");
		} else {
			System.out.println("Failed to update name");
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (Exception e) {
		}
		
		try {
			if (ps != null)
				ps.close();
		} catch (Exception e) {
		}
		
	}

	public void deleteName(String id) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, 
	InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		
		Connection connection = connect();
		PreparedStatement ps;
		
		ps = connection.prepareStatement("DELETE FROM `webapisdb`.`names` WHERE `id`=?");
		ps.setInt(1, Integer.parseInt(id));

		int status = ps.executeUpdate();

		if (status != 0) {
			System.out.println("Name deleted");
		} else {
			System.out.println("Failed to delete name");
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (Exception e) {
		}
		
		try {
			if (ps != null)
				ps.close();
		} catch (Exception e) {
		}

	}

	private Connection connect() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		return DriverManager.getConnection(url, username, password);
	}

}
