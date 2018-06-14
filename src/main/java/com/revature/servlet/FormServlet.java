package com.revature.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.Employee;
import com.revature.beans.Form;
import com.revature.daoimpl.EmployeeDAOImpl;
import com.revature.daoimpl.FormDAOImpl;

public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String[] info = getServletContext().getInitParameter("dbInfo").split(",");
			EmployeeDAOImpl edi = new EmployeeDAOImpl(info);
			FormDAOImpl fdi = new FormDAOImpl(info);
		
			Employee emp = edi.getEmployee((Integer) req.getAttribute("employeeid"));
			Employee sup = edi.getEmployee(emp.getSupervisorId());
		
			String address = req.getAttribute("eventaddress1") + ", " + req.getAttribute("eventcity") + ", " + req.getAttribute("eventstate") + 
							 ", " + req.getAttribute("eventzip");
		
			Form form = convertForm(Integer.parseInt(req.getParameter("reimbursementtype")),  
									Date.valueOf(req.getParameter("eventdate")).toLocalDate().toString(), address, req.getParameter("eventdescription"), 
									(File) req.getAttribute("attachedfile"), Integer.parseInt(req.getParameter("gradeformat")), 
									Double.parseDouble(req.getParameter("reimbursementamount")), sup, emp);
			
			fdi.createForm(form);
		}catch(SQLException e) {
			PrintWriter pw = resp.getWriter();
			pw.print("System is down! Please try again later!");
			req.getRequestDispatcher("/home").include(req,  resp);
			pw.close();
		}
	}

	private Form convertForm(int id, String eventDate, String location, String desc, File wrj,
							 int gradeFormat, double amount, Employee sup, Employee emp) {
		Form form = new Form();
		
		form.setEventId(id);
		form.setEventDate(eventDate);
		form.setEventLocation(location);
		form.setEventDesc(desc);
		form.setWrj(wrj);
		form.setPresGrade("Not available");
		form.setGradeFormat(gradeFormat);
		form.setStatus(1);
		form.setReimbursementAmount(amount);
		form.setAmountStatus(1);
		// Determines if the request is urgent, the event being under a week away
		if(Instant.now().until(Date.valueOf(eventDate).toLocalDate(), ChronoUnit.DAYS) < 7)
			form.setUrgent(true);
		else
			form.setUrgent(false);
		// Determines which approvals are needed
		if(sup.isHead() || sup.isBenCo()) {
			form.setSupervisorApproval(true);
		}
		else {
			form.setSupervisorApproval(false);
		}
		if(emp.isHead()) {
			form.setHeadApproval(true);
		}
		else {
			form.setHeadApproval(false);
		}
		
		form.setBenCoApproval(false);
		
		return form;
	}
}
