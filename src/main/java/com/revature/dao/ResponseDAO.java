package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Response;

public interface ResponseDAO {
	public abstract void createResponse(Response resp) throws SQLException;
	public abstract void updateResponse(Response resp) throws SQLException;
	public abstract Response retrieveResponse(int id) throws SQLException;
	public abstract List<Response> retrieveSentResponses(int id) throws SQLException;
	public abstract List<Response> retrieveReceivedResponses(int id) throws SQLException;
	public abstract List<Response> retrieveAllResponses(int id) throws SQLException;
}
