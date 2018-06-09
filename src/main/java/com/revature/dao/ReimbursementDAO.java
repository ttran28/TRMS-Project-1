package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Reimbursement;

public interface ReimbursementDAO {
	public abstract void createReimbursement(Reimbursement reim) throws SQLException;
	public abstract void updateReimbursement(Reimbursement reim) throws SQLException;
	public abstract Reimbursement retrieveReimbursement(int id) throws SQLException;
	public abstract List<Reimbursement> retrieveReimbursements(int id) throws SQLException;
}
