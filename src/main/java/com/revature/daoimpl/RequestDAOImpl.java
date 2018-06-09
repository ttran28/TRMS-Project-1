package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.ConnFactory;
import com.revature.beans.Request;
import com.revature.dao.RequestDAO;

public class RequestDAOImpl implements RequestDAO{
	
	@Override
	public void createRequest(Request req) throws SQLException {
		// Retrieve the ConnFactory instance and create a database connection
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection();
		
		// Create a callable statement to insert a new request into the database
		String sql = "call CREATE_REQUEST{?, ?, ?, ?, ?}";
		CallableStatement stmt = conn.prepareCall(sql);
		
		
		// Executes the statement and closes the database connection
		stmt.execute();
		conn.close();
	}

	@Override
	public void updateRequest(Request req) throws SQLException {
		// Retrieve the ConnFactory instance and create a database connection
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection();
				
		// Create a callable statement to update a request in the database
		String sql = "call UPDATE_REQUEST{?, ?, ?, ?, ?}";
		CallableStatement stmt = conn.prepareCall(sql);
		
		// Executes the statement and closes the database connection
		stmt.execute();
		conn.close();
	}

	@Override
	public Request retrieveRequest(int id) throws SQLException {
		// Retrieves the ConnFactory instance to create a database connection and creates an empty request
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection();
		Request req = new Request();
		
		// Prepares the SQL statement
		String sql = "SELECT * FROM REQUEST WHERE reqId = ?";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		// Puts the request information into req
		while(rs.next()) {
			
		}
		
		// Closes the database connection and returns req
		conn.close();
		return req;
	}

	@Override
	public List<Request> retrieveRequests(int id) throws SQLException {
		// Retrieves the ConnFactory instance to create a database connection and creates a list to store requests
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection();
		List<Request> requests = new ArrayList<>();
						
		// Prepares the SQL resources
		String sql = "SELECT * FROM REQUEST WHERE empId = ?";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
						
		// Retrieves the requests in the database that match an employee's id and puts them in the requests list
		while(rs.next()) {
			Request req = new Request();
			
			
			
			requests.add(req);
		}
						
		// Closes the database connection and returns requests
		conn.close();
		return requests;
	}
}
