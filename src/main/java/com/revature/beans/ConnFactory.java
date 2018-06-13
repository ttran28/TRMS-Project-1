package com.revature.beans;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnFactory {
	// Holds the single instance of ConnFactory allowed
	private static ConnFactory cf = null;
		
	// A private constructor that makes the ConnFactory class a singleton
	private ConnFactory() {
		super();
	}
		
	// Retrieves the instance of ConnFactory
	public static synchronized ConnFactory getInstance() {
		// Creates a new instance if one doesn't already exist
		if(cf == null)
			cf = new ConnFactory();
		// Returns the instance of Connection factory
		return cf;
	}
		
	// Creates and returns a Connection to the database
	public Connection getConnection(String url, String username, String password) {
		// Creates a Connection object
		Connection conn = null;
		
		// Attempts to establish a connection with the database
		try {
			//Retrieves the necessary database driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Establishes the connection using the properties file
			conn = DriverManager.getConnection(url, username, password);
		// Executes if a connection couldn't be established with the database
	   	}catch(SQLException | ClassNotFoundException e) {
	   		System.out.println("Couldn't connect to database!");
	   		e.printStackTrace();
	   	}
		
		// Returns the created Connection object
		return conn;
	}
}
