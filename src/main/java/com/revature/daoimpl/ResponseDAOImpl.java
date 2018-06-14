package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.ConnFactory;
import com.revature.beans.Response;
import com.revature.dao.ResponseDAO;

public class ResponseDAOImpl implements ResponseDAO{
	private String[] info;
	
	public ResponseDAOImpl(String[] info) {
		this.info = info;
	}
	
	@Override
	public void createResponse(Response resp) throws SQLException {
		// Retrieve the ConnFactory instance and create a database connection
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection(info);
		
		// Create a callable statement to insert a new response into the database
		String sql = "call CREATE_RESPONSE{?, ?, ?, ?}";
		CallableStatement stmt = conn.prepareCall(sql);
		stmt.setInt(1, resp.getSender());
		stmt.setInt(2, resp.getReceiver());
		stmt.setString(3, resp.getResponseDate());
		stmt.setString(4, resp.getResponse());
		
		// Executes the statement and closes the database connection
		stmt.execute();
		conn.close();
	}

	@Override
	public void updateResponse(Response resp) throws SQLException {
		// Retrieve the ConnFactory instance and create a database connection
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection(info);
				
		// Create a callable statement to update a response in the database
		String sql = "call UPDATE_REQUEST{?, ?, ?, ?, ?}";
		CallableStatement stmt = conn.prepareCall(sql);
		stmt.setInt(1, resp.getId());
		stmt.setInt(2, resp.getSender());
		stmt.setInt(3, resp.getReceiver());
		stmt.setString(4, resp.getResponseDate());
		stmt.setString(5, resp.getResponse());
		
		// Executes the statement and closes the database connection
		stmt.execute();
		conn.close();
	}

	@Override
	public Response retrieveResponse(int id) throws SQLException {
		// Retrieves the ConnFactory instance to create a database connection and creates an empty response
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection(info);
		Response resp = new Response();
		
		// Prepares the SQL statement
		String sql = "SELECT * FROM RESPONSEID WHERE RESPONSEID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		// Puts the response information into resp
		while(rs.next()) {
			resp.setId(rs.getInt(1));
			resp.setSender(rs.getInt(2));
			resp.setReceiver(rs.getInt(3));
			resp.setResponseDate(rs.getString(4));
			resp.setResponse(rs.getString(5));
		}
		
		// Closes the database connection and returns resp
		conn.close();
		return resp;
	}

	@Override
	public List<Response> retrieveSentResponses(int id) throws SQLException {
		// Retrieves the ConnFactory instance to create a database connection and creates a list to store responses
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection(info);
		List<Response> responses = new ArrayList<>();
						
		// Prepares the SQL resources
		String sql = "SELECT * FROM RESPONSEID WHERE SENDER = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
						
		// Retrieves the responses in the database that an employee sent and puts them into responses
		while(rs.next()) {
			Response resp = new Response();
			
			resp.setId(rs.getInt(1));
			resp.setSender(rs.getInt(2));
			resp.setReceiver(rs.getInt(3));
			resp.setResponseDate(rs.getString(4));
			resp.setResponse(rs.getString(5));
			
			responses.add(resp);
		}
						
		// Closes the database connection and returns responses
		conn.close();
		return responses;
	}
	
	@Override
	public List<Response> retrieveReceivedResponses(int id) throws SQLException {
		// Retrieves the ConnFactory instance to create a database connection and creates a list to store responses
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection(info);
		List<Response> responses = new ArrayList<>();
						
		// Prepares the SQL resources
		String sql = "SELECT * FROM RESPONSEID WHERE RECEIVER = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
						
		// Retrieves the responses in the database that an employee received and puts them into responses
		while(rs.next()) {
			Response resp = new Response();
			
			resp.setId(rs.getInt(1));
			resp.setSender(rs.getInt(2));
			resp.setReceiver(rs.getInt(3));
			resp.setResponseDate(rs.getString(4));
			resp.setResponse(rs.getString(5));
			
			responses.add(resp);
		}
						
		// Closes the database connection and returns responses
		conn.close();
		return responses;
	}
	
	@Override
	public List<Response> retrieveAllResponses(int id) throws SQLException {
		// Retrieves the ConnFactory instance to create a database connection and creates a list to store responses
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection(info);
		List<Response> responses = new ArrayList<>();
						
		// Prepares the SQL resources
		String sql = "SELECT * FROM RESPONSEID WHERE RECEIVER = ? OR SENDER = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.setInt(2, id);
<<<<<<< HEAD
		ResultSet rs = stmt.executeQuery(sql);
=======
		ResultSet rs = stmt.executeQuery();
>>>>>>> 7b70f8a4828261d33ad79825f492008e610c1147
						
		// Retrieves the responses in the database that an employee received and puts them into responses
		while(rs.next()) {
			Response resp = new Response();
			
			resp.setId(rs.getInt(1));
			resp.setSender(rs.getInt(2));
			resp.setReceiver(rs.getInt(3));
			resp.setResponseDate(rs.getString(4));
			resp.setResponse(rs.getString(5));
			
			responses.add(resp);
		}
						
		// Closes the database connection and returns responses
		conn.close();
		return responses;
	}
}
