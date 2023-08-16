package net.lakis.webapi;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionDB {

	static String dbName = "";
	static String url = "jdbc:mysql://localhost:3306" + dbName;
	static String username = "root";
	static String password = "root";

	public ArrayList<Name> listAll()
			throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
	
		ArrayList<Name> names = new ArrayList<Name>();
		String fname = "";
		String lname = "";
		String id = "";
		
		try(Connection connection = connect();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM `webapisdb`.`names`;");
				ResultSet result = ps.executeQuery()){

			while (result.next()) {
				id = result.getString(1);
				fname = result.getString(2);
				lname = result.getString(3);
				names.add(new Name(id, fname, lname));
			}
		}	

		return names;
	}
	
	public Name searchNameById(String id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, 
	IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
	    String fname = "";
	    String lname = "";

	    try (Connection connection = connect();
	         PreparedStatement ps = connection.prepareStatement("SELECT * FROM `webapisdb`.`names` WHERE id = ?")) {
	        
	        ps.setInt(1, Integer.parseInt(id));
	        
	        try (ResultSet result = ps.executeQuery()) {
	        	
	            if (result.next()) {
					fname = result.getString(2);
					lname = result.getString(3);
	            }
	        }
	    }
	    
	    return new Name(id, fname, lname);
	}

	public int addName(String fname, String lname) throws SQLException, InstantiationException, IllegalAccessException, 
	IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {	
		
		int id = -1;
		
		try(Connection connection = connect();
				PreparedStatement ps = connection.prepareStatement("INSERT INTO `webapisdb`.`names` (`fname`, `lname`) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS)){
			
			ps.setString(1, fname);
			ps.setString(2, lname);

			int status = ps.executeUpdate();
			
			try(ResultSet result = ps.getGeneratedKeys()){
				if(result.next()) {
					id = result.getInt(1);
				}
			}
			
			if (status != 0) {
				System.out.println("Name Added");
			} else {
				System.out.println("Failed to add name");
			}
		}
		
		return id;
	}

	public void updateName(String id, String fname, String lname) throws SQLException, InstantiationException, IllegalAccessException, 
	IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		
		try(Connection connection = connect();
				PreparedStatement ps = connection.prepareStatement("UPDATE `webapisdb`.`names` SET `fname`=?, `lname`=? WHERE `id`=?")){
			
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setInt(3, Integer.parseInt(id));

			int status = ps.executeUpdate();

			if (status != 0) {
				System.out.println("Name updated");
			} else {
				System.out.println("Failed to update name");
			}
		}
	}

	public void deleteName(String id) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, 
	InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		
		try(Connection connection = connect();
				PreparedStatement ps = connection.prepareStatement("DELETE FROM `webapisdb`.`names` WHERE `id`=?")){
			
			ps.setInt(1, Integer.parseInt(id));
			int status = ps.executeUpdate();

			if (status != 0) {
				System.out.println("Name deleted");
			} else {
				System.out.println("Failed to delete name");
			}
		}	
	}

	private Connection connect() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		return DriverManager.getConnection(url, username, password);
	}

}
