package com.revature.daoimpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.revature.beans.ConnFactory;
import com.revature.beans.Form;
import com.revature.dao.FormDAO;

public class FormDAOImpl implements FormDAO{
	private String[] info;
	
	public FormDAOImpl(String[] info) {
		this.info = info;
	}

	@Override
	public void createForm(Form form) throws SQLException {
		// Retrieve the ConnFactory instance and create a database connection
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection(info);
		
		// Create a callable statement to insert a new form into the database
<<<<<<< HEAD
		String sql = "call CREATE_REQUEST{?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?}";
		CallableStatement stmt = conn.prepareCall(sql);
		
		stmt.setInt(1, form.getEventId());
		stmt.setString(2, form.getSubmissionDate());
=======
		String sql = "call CREATE_REQUEST{?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?}";
		CallableStatement stmt = conn.prepareCall(sql);
		
		stmt.setInt(1, form.getEventId());
>>>>>>> 7b70f8a4828261d33ad79825f492008e610c1147
		stmt.setString(3, form.getEventDate());
		stmt.setString(4, form.getEventLocation());
		stmt.setString(5, form.getEventDesc());
		stmt.setBlob(6, (Blob) form.getWrj());
		stmt.setString(7, form.getPresGrade());
		stmt.setInt(8, form.getGradeFormat());
		stmt.setInt(9, form.getStatus());
		stmt.setDouble(10, form.getReimbursementAmount());
		stmt.setInt(11, form.getAmountStatus());
		stmt.setBoolean(12, form.isUrgent());
		stmt.setBoolean(13, form.isHeadApproval());
		stmt.setBoolean(14, form.isSupervisorApproval());
		stmt.setBoolean(15, form.isBenCoApproval());
		
		// Executes the statement and closes the database connection
		stmt.execute();
		conn.close();
	}

	@Override
	public void updateForm(Form form) throws SQLException {
		// Retrieve the ConnFactory instance and create a database connection
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection(info);
				
		// Create a callable statement to update a form in the database
		String sql = "call UPDATE_REQUEST{?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?}";
		CallableStatement stmt = conn.prepareCall(sql);
		
		stmt.setInt(1, form.getId());
		stmt.setInt(2, form.getEventId());
		stmt.setString(3, form.getSubmissionDate());
		stmt.setString(4, form.getEventDate());
		stmt.setString(5, form.getEventLocation());
		stmt.setString(6, form.getEventDesc());
		stmt.setBlob(7, (Blob) form.getWrj());
		stmt.setString(8, form.getPresGrade());
		stmt.setInt(9, form.getGradeFormat());
		stmt.setInt(10, form.getStatus());
		stmt.setDouble(11, form.getReimbursementAmount());
		stmt.setInt(12, form.getAmountStatus());
		stmt.setBoolean(13, form.isUrgent());
		stmt.setBoolean(14, form.isHeadApproval());
		stmt.setBoolean(15, form.isSupervisorApproval());
		stmt.setBoolean(16, form.isBenCoApproval());
		
		// Executes the statement and closes the database connection
		stmt.execute();
		conn.close();
	}

	@Override
	public Form retrieveForm(int id) throws SQLException {
		// Retrieves the ConnFactory instance to create a database connection and creates an empty form
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection(info);
		Form form = new Form();
		
		// Prepares the SQL statement
		String sql = "SELECT * FROM FORMID WHERE FORMID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		// Puts the request information into form
		while(rs.next()) {
			form.setId(rs.getInt(1));
			form.setEventId(rs.getInt(2));
			form.setSubmissionDate(rs.getString(3));
			form.setEventDate(rs.getString(4));
			form.setEventLocation(rs.getString(5));
			form.setEventDesc(rs.getString(6));
			
			// Converts the Blob into a file
<<<<<<< HEAD
//			try {
//			InputStream is = rs.getBinaryStream(7);
//			File temp = new File("temp.txt");
//			temp.deleteOnExit();
//			FileUtils.copyInputStreamToFile(is, temp);
//			form.setWrj(temp);
//			}catch(IOException e) {
//				e.printStackTrace();
//				form.setWrj(null);
//			}
=======
			try {
			InputStream is = rs.getBinaryStream(7);
			File temp = new File("temp.txt");
			temp.deleteOnExit();
			FileUtils.copyInputStreamToFile(is, temp);
			form.setWrj(temp);
			}catch(IOException e) {
				e.printStackTrace();
				form.setWrj(null);
			}
>>>>>>> 7b70f8a4828261d33ad79825f492008e610c1147
			
			form.setPresGrade(rs.getString(8));
			form.setGradeFormat(rs.getInt(9));
			form.setStatus(rs.getInt(10));
			form.setReimbursementAmount(rs.getDouble(11));
			form.setAmountStatus(rs.getInt(12));
			form.setUrgent(rs.getBoolean(13));
			form.setHeadApproval(rs.getBoolean(14));
			form.setSupervisorApproval(rs.getBoolean(15));
			form.setBenCoApproval(rs.getBoolean(16));
		}
		
		// Closes the database connection and returns form
		conn.close();
		return form;
	}

	@Override
	public List<Form> retrieveForms(int id) throws SQLException {
		// Retrieves the ConnFactory instance to create a database connection and creates a list to store forms
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection(info);
		List<Form> forms = new ArrayList<>();
						
		// Prepares the SQL resources
		String sql = "SELECT FORMID FROM EMPID_FORMID WHERE EMPID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
						
		// Retrieves the forms in the database that match an employee's id and puts them in the forms list
		while(rs.next()) {
			Form reim = retrieveForm(rs.getInt(1));
			
			forms.add(reim);
		}
						
		// Closes the database connection and returns forms
		conn.close();
		return forms;
	}
	
	@Override
	public List<String> retrieveGradeFormat(int id) throws SQLException{
		// Retrieves the ConnFactory instance to create a database connection and creates a list to store Strings
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection(info);
		List<String> format = new ArrayList<>();
		
		// Prepares the SQL resources
		String sql = "SELECT FORMGRDFORMAT FROM FORMGRDFORMATID WHERE FORMGRDFORMATID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
<<<<<<< HEAD
		ResultSet rs = stmt.executeQuery(sql);
=======
		ResultSet rs = stmt.executeQuery();
>>>>>>> 7b70f8a4828261d33ad79825f492008e610c1147
		
		// Retrieves the requested information from the database and stores them in format
		while(rs.next()) {
			format.add(rs.getString(1));
			format.add(rs.getString(2));
		}
		
		// Returns format
		return format;
	}
	
	@Override
	public String retrieveFormStatus(int id) throws SQLException{
		// Retrieves the ConnFactory instance to create a database connection and creates an empty String to store the information
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection(info);
		String status = null;
				
		// Prepares the SQL resources
		String sql = "SELECT FORMSTATUS FROM FORMSTATUSCODE WHERE FORMSTATUSCODE = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
<<<<<<< HEAD
		ResultSet rs = stmt.executeQuery(sql);
=======
		ResultSet rs = stmt.executeQuery();
>>>>>>> 7b70f8a4828261d33ad79825f492008e610c1147
				
		// Retrieves the requested information from the database and stores it in status
		while(rs.next()) {
			status = rs.getString(1);
		}
				
		// Returns status
		return status;
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 7b70f8a4828261d33ad79825f492008e610c1147
