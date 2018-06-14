package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employee;
import com.revature.beans.Form;
import com.revature.beans.Response;
import com.revature.daoimpl.EmployeeDAOImpl;
import com.revature.daoimpl.FormDAOImpl;
import com.revature.daoimpl.ResponseDAOImpl;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Form> formList = new ArrayList<>();
		List<Response> responseList = new ArrayList<>();
		// response.getWriter().append("this");
		PrintWriter pw = response.getWriter();
		try {
			System.out.println("I'm inside the HomeServlet");
			String[] info = getServletContext().getInitParameter("dbInfo").split(",");
			EmployeeDAOImpl edi = new EmployeeDAOImpl(info);
			FormDAOImpl fdi = new FormDAOImpl(info);
			ResponseDAOImpl rdi = new ResponseDAOImpl(info);

			HttpSession ses = request.getSession(false);
			int id = (int) ses.getAttribute("userid");
			
			System.out.println("sess id inside home servlet: " + id);
			
			Employee emp = edi.getEmployee(id);// (int)ses.getAttribute("userid"));
			if (emp.getId() == 0) {
				System.out.println("I'm inside if in HomeServlet");
				PrintWriter pw2 = response.getWriter();
				response.setContentType("text/html");
				pw2.println("<script type=\"text/javascript\">");
				pw2.println("alert('Username and password do not match!');");
				pw2.println("</script>");

				request.getRequestDispatcher("/login.html").include(request, response);
				pw2.close();
			}
			request.getRequestDispatcher("/WEB-INF/home.html").include(request, response);
			formList = fdi.retrieveForms(emp.getId());
			responseList = rdi.retrieveReceivedResponses(emp.getId());

			printFormHTML(pw);
			generateFormValue(formList, pw);
			printRequestHTML(pw);
			generateRequestValue(responseList, pw, edi);

			response.setContentType("text/html");
		} catch (SQLException e) {
			pw.print("System is down! Please try again later!");
			// request.getRequestDispatcher("/home").include(request, response);
			e.printStackTrace();
			pw.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		request.getRequestDispatcher("/home.html").include(request, response);
	}

	public void generateFormValue(List<Form> formList, PrintWriter pw){
		for(Form form : formList){
			String date = form.getSubmissionDate();
			int status = form.getStatus();
			String statusStr = getstatus(status);
			String amount = form.getReimbursementAmount();
			Boolean urgency = form.isUrgent();
			Boolean DHA = form.isHeadApproval();
			Boolean DSA = form.isSupervisorApproval();
			Boolean BCA = form.isBenCoApproval();
			pw.println("<tr>");
			pw.println("<td>" + date + "</td>");
			pw.println("<td>" + statusStr + "</td>");
			pw.println("<td>" + amount + "</td>");
			pw.println("<td>" + urgency + "</td>");
			pw.println("<td>" + DHA + "</td>");
			pw.println("<td>" + DSA + "</td>");
			pw.println("<td>" + BCA + "</td>");
			pw.println("</tr>");
		}
		pw.println("</table>");
		pw.println("</div>");
		pw.println("<a href=\"form.html\"><button class=\"btn btn-success btn-sm\">Create New Reimbursement Request</button></a>");
		pw.println("<button type=\"button\" class=\"btn btn-primary btn-sm\" data-toggle=\"modal\" data-target=\"#myModal\">View More Info</button>");
	}
	
	public void generateRequestValue(List<Response> responseList, PrintWriter pw, EmployeeDAOImpl edi) throws SQLException{
		for(Response response : responseList){
			int SID = response.getSender();
			int RID = response.getReceiver();
			String date = response.getResponseDate().toString();
			String res = response.getResponse();
			Employee emp = edi.getEmployee(SID);
			String name = emp.getName();
			Employee emp2 = edi.getEmployee(RID);
			String name2 = emp2.getName();
			pw.println("<tr>");
			pw.println("<td>" + name + "</td>");
			pw.println("<td>" + name2 + "</td>");
			pw.println("<td>" + date + "</td>");
			pw.println("<td>" + res + "</td>");
			pw.println("</tr>");
		}
		pw.println("</table>");
		pw.println("</div>");
		pw.println("<button type=\"button\" class=\"btn btn-primary btn-sm\" data-toggle=\"modal\" data-target=\"#myModal2\">View More Info</button>");
	}

	public static String getstatus(int status) {
		if(status == 0)
			return "Denied";
		else if(status == 1)
			return "In Progress";
		else if(status == 2)
			return "Currently Pending";
		else
			return "Approved";
	}
	
	public void printFormHTML(PrintWriter pw){
		pw.println("<div class=\"container\">");
		pw.println("<h1>Submitted Form</h1>");
		pw.println("<div class=\"split2 right\" data-spy=\"scroll\">");
		pw.println("<table id=\"formTable\" class=\"table table-striped table-hover\"  >");
		pw.println("<thead>");
		pw.println("<tr>");
		pw.println("<th>Submision Date</th>");
		pw.println("<th>Status</th>");
		pw.println("<th>Amount</th>");
		pw.println("<th>Urgency</th>");
		pw.println("<th>Department Head Approval</th>");
		pw.println("<th>Direct Supervisor Approval</th>");
		pw.println("<th>Benefits Cordinator Approval</th>");
		pw.println("</tr>");
		pw.println("</thead>");
	}
	
	public void printRequestHTML(PrintWriter pw){
		pw.println("<h1>Requests</h1>");
		pw.println("<div class=\"split2 right\" data-spy=\"scroll\">");
		pw.println("<table id=\"formTable\" class=\"table table-striped table-hover\"  >");
		pw.println("<thead>");
		pw.println("<tr>");
		pw.println("<th>Sender</th>");
		pw.println("<th>Receiver</th>");
		pw.println("<th>Response Date</th>");
		pw.println("<th>Comment</th>");
		pw.println("</tr>");
		pw.println("</thead>");
	}
}


