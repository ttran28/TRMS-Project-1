package com.revature.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
<<<<<<< HEAD
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
=======
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
>>>>>>> 7b70f8a4828261d33ad79825f492008e610c1147

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employee;
import com.revature.beans.Form;
import com.revature.daoimpl.EmployeeDAOImpl;
import com.revature.daoimpl.FormDAOImpl;

<<<<<<< HEAD
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In formServlet doGet");
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In formServlet doPost");

		try {
//			HttpSession ses = req.getSession(false);
//			int userid = (int) ses.getAttribute("userid");

			String[] info = getServletContext().getInitParameter("dbInfo").split(",");

			EmployeeDAOImpl edi = new EmployeeDAOImpl(info);
			FormDAOImpl fdi = new FormDAOImpl(info);

			//getEmployee(empid)
			Employee emp = edi.getEmployee(202);
			Employee sup = edi.getEmployee(emp.getSupervisorId());
			edi.initForm(emp.getId());

			String address = req.getParameter("eventaddress1") + ", " + req.getParameter("eventcity") + ", "
					+ req.getParameter("eventstate") + ", " + req.getParameter("eventzip");


			Form form = convertForm2(req.getParameter("eventgroup"), req.getParameter("currentDate"),
					req.getParameter("eventdate"), address, req.getParameter("eventdescription"),
					req.getAttribute("attachedfile"), req.getParameter("estReimbAmt"), sup, emp);

			fdi.createForm(form);
			PrintWriter pw = resp.getWriter();
			resp.setContentType("text/html");  
			pw.println("<script type=\"text/javascript\">");  
			pw.println("alert('Form has been submitted!');");  
			pw.println("</script>");
			pw.close();
			req.getRequestDispatcher("/home.html").forward(req, resp);
		} catch (SQLException e) {
			PrintWriter pw = resp.getWriter();
			pw.print("System is down! Please try again later!");
			req.getRequestDispatcher("/home.html").include(req, resp);
			pw.close();
		}
	}

	// EventID, EventDate, address, EventDescription, File(?), GradeFormat,
	// ReimbursementAmount, sup, emp)
	private Form convertForm2(String id, String submitDate, String eventDate, String location, String desc, Object wrj,
			double amount, Employee sup, Employee emp) {
		System.out.println("Converting the form.");
		
		
		Form form = new Form();
		form.setSubmissionDate(submitDate);
		form.setEventId(Integer.parseInt(id));
		form.setEventDate(eventDate);
		form.setEventLocation(location);
		form.setEventDesc(desc);
		if (wrj != null) {
			form.setWrj((File) wrj);
		}
		form.setPresGrade("Not available.");
		form.setStatus(1);
		form.setReimbursementAmount(amount);
		form.setAmountStatus(0);

		// Determines if the request is urgent, the event being under a week away
		// if (submitDate.until(eventDate).minusDays(7).isZero() ||
		// submitDate.until(eventDate).minusDays(7).isNegative())
		// form.setUrgent(true);
		// else
		// form.setUrgent(false);
		form.setUrgent(true);
		// Determines which approvals are needed
		if (sup.isHead() || sup.isBenCo()) {
			form.setSupervisorApproval(true);
		} else {
			form.setSupervisorApproval(false);
		}
		if (emp.isHead()) {
			form.setHeadApproval(true);
		} else {
			form.setHeadApproval(false);
		}

		form.setBenCoApproval(false);

		return form;
	}

}
=======
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
>>>>>>> 7b70f8a4828261d33ad79825f492008e610c1147
