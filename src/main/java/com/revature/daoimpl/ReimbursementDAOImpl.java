package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.ConnFactory;
import com.revature.beans.Reimbursement;
import com.revature.dao.ReimbursementDAO;

public class ReimbursementDAOImpl implements ReimbursementDAO{

	@Override
	public void createReimbursement(Reimbursement reim) throws SQLException {
		// Retrieve the ConnFactory instance and create a database connection
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection();
		
		// Create a callable statement to insert a new reimbursement into the database
		String sql = "call CREATE_REQUEST{?, ?, ?, ?, ?}";
		CallableStatement stmt = conn.prepareCall(sql);
		
		
		// Executes the statement and closes the database connection
		stmt.execute();
		conn.close();
	}

	@Override
	public void updateReimbursement(Reimbursement reim) throws SQLException {
		// Retrieve the ConnFactory instance and create a database connection
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection();
				
		// Create a callable statement to update a reimbursement in the database
		String sql = "call UPDATE_REQUEST{?, ?, ?, ?, ?}";
		CallableStatement stmt = conn.prepareCall(sql);
		
		// Executes the statement and closes the database connection
		stmt.execute();
		conn.close();
	}

	@Override
	public Reimbursement retrieveReimbursement(int id) throws SQLException {
		// Retrieves the ConnFactory instance to create a database connection and creates an empty reimbursement
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection();
		Reimbursement reim = new Reimbursement();
		
		// Prepares the SQL statement
		String sql = "SELECT * FROM REQUEST WHERE reqId = ?";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		// Puts the request information into reim
		while(rs.next()) {
			
		}
		
		// Closes the database connection and returns reim
		conn.close();
		return reim;
	}

	@Override
	public List<Reimbursement> retrieveReimbursements(int id) throws SQLException {
		// Retrieves the ConnFactory instance to create a database connection and creates a list to store reimbursements
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection();
		List<Reimbursement> reimbursements = new ArrayList<>();
						
		// Prepares the SQL resources
		String sql = "SELECT * FROM REIMBURSEMENT WHERE empId = ?";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
						
		// Retrieves the reimbursements in the database that match an employee's id and puts them in the reimbursements list
		while(rs.next()) {
			Reimbursement req = new Reimbursement();
			
			
			
			reimbursements.add(req);
		}
						
		// Closes the database connection and returns reimbursements
		conn.close();
		return reimbursements;
	}
}
