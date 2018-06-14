package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Employee;

public interface EmployeeDAO {
	//The abstract methods that interface between an Employee object and the SQL database
	public abstract void initForm(int id) throws SQLException;
	public abstract List<Employee> getAllEmployees() throws SQLException;
	public abstract Employee getEmployee(int id) throws SQLException;
	public abstract Employee employeeLogin(String username, String password) throws SQLException;
	public abstract List<Employee> getSubordinates(int id) throws SQLException;
}
