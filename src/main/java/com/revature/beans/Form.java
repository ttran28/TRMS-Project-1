package com.revature.beans;

import java.io.File;
<<<<<<< HEAD
import java.sql.Date;
=======
>>>>>>> 7b70f8a4828261d33ad79825f492008e610c1147

public class Form {
	// The values stored in a Form object
	private int id;
	private int eventId;
	private String submissionDate;
	private String eventDate;
	private String eventLocation;
	private String eventDesc;
	private File wrj;
	private String presGrade;
	private int gradeFormat;
	private int status;
	private double reimbursementAmount;
	private int amountStatus;
	private boolean urgent;
	private boolean supervisorApproval;
	private boolean headApproval;
	private boolean benCoApproval;
	
	// An empty constructor
	public Form() {
		
	}

	// Getter and setter methods for the id field
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	// Getter and setter methods for the eventId field
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	// Getter and setter methods for the submissionDate field
	public String getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	// Getter and setter methods for the eventDate field
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	// Getter and setter methods for the eventLocation field
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	// Getter and setter methods for the eventDesc field
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}

	// Getter and setter methods for the wrj field
	public File getWrj() {
		return wrj;
	}
	public void setWrj(File wrj) {
		this.wrj = wrj;
	}

	// Getter and setter methods for the presGrade field
	public String getPresGrade() {
		return presGrade;
	}
	public void setPresGrade(String presGrade) {
		this.presGrade = presGrade;
	}

	// Getter and setter methods for the gradeFormat field
	public int getGradeFormat() {
		return gradeFormat;
	}
	public void setGradeFormat(int gradeFormat) {
		this.gradeFormat = gradeFormat;
	}

	// Getter and setter methods for the status field
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	// Getter and setter methods for the reimbursementAmount field
	public double getReimbursementAmount() {
		return reimbursementAmount;
	}
	public void setReimbursementAmount(double reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

	// Getter and setter methods for the amountStatus field
	public int getAmountStatus() {
		return amountStatus;
	}
	public void setAmountStatus(int amountStatus) {
		this.amountStatus = amountStatus;
	}

	// Getter and setter methods for the urgent field
	public boolean isUrgent() {
		return urgent;
	}
	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	// Getter and setter methods for the supervisorApproval field
	public boolean isSupervisorApproval() {
		return supervisorApproval;
	}
	public void setSupervisorApproval(boolean supervisorApproval) {
		this.supervisorApproval = supervisorApproval;
	}

	// Getter and setter methods for the headApproval field
	public boolean isHeadApproval() {
		return headApproval;
	}
	public void setHeadApproval(boolean headApproval) {
		this.headApproval = headApproval;
	}

	// Getter and setter methods for the benCoApproval field
	public boolean isBenCoApproval() {
		return benCoApproval;
	}
	public void setBenCoApproval(boolean benCoApproval) {
		this.benCoApproval = benCoApproval;
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 7b70f8a4828261d33ad79825f492008e610c1147
