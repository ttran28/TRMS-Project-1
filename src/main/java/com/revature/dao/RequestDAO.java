package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Request;

public interface RequestDAO {
	public abstract void createRequest(Request req) throws SQLException;
	public abstract void updateRequest(Request req) throws SQLException;
	public abstract Request retrieveRequest(int id) throws SQLException;
	public abstract List<Request> retrieveRequests(int id) throws SQLException;
}
