package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Form;

public interface FormDAO {
	public abstract void createForm(Form form) throws SQLException;
	public abstract void updateForm(Form form) throws SQLException;
	public abstract Form retrieveForm(int id) throws SQLException;
	public abstract List<Form> retrieveForms(int id) throws SQLException;
	public abstract List<String> retrieveGradeFormat(int id) throws SQLException;
	public abstract String retrieveFormStatus(int id) throws SQLException;
}
