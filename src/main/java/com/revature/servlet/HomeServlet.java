package com.revature.servlet;

import java.io.File;
import java.io.FileWriter;
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

import com.google.gson.Gson;
import com.revature.beans.Employee;
import com.revature.beans.Form;
import com.revature.beans.Response;
import com.revature.daoimpl.EmployeeDAOImpl;
import com.revature.daoimpl.FormDAOImpl;
import com.revature.daoimpl.ResponseDAOImpl;

import com.revature.beans.Employee;
import com.revature.beans.Form;
import com.revature.daoimpl.EmployeeDAOImpl;
import com.revature.daoimpl.FormDAOImpl;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Form> formList = new ArrayList<>();
		List<Response> responseList = new ArrayList<>();
		//response.getWriter().append("this");
		PrintWriter pw = response.getWriter();
		try {
			String[] info = getServletContext().getInitParameter("dbInfo").split(",");
			EmployeeDAOImpl edi = new EmployeeDAOImpl(info);
			FormDAOImpl fdi = new FormDAOImpl(info);
			ResponseDAOImpl rdi = new ResponseDAOImpl(info);
			
//			HttpSession ses = request.getSession(false);
//			System.out.println((int)ses.getAttribute("userid"));
			
			Employee emp = edi.getEmployee(201);//(Integer)request.getAttribute("employeeID"));
			if(emp.getId() != 0){
				request.getRequestDispatcher("/home.html").include(request,  response);
			}		
			formList = fdi.retrieveForms(emp.getId());
			responseList = rdi.retrieveReceivedResponses(emp.getId());
			System.out.println(emp);
			
			printFormHTML(pw);
			generateFormValue(formList, pw);
			printRequestHTML(pw);
			generateRequestValue(responseList, pw);
			
			response.setContentType("text/html");
		}catch(SQLException e) {
			pw.print("System is down! Please try again later!");
			//request.getRequestDispatcher("/home").include(request,  response);
=======
		response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Form> formList = new ArrayList<>();
		//response.getWriter().append("this");
		try {
			EmployeeDAOImpl edi = new EmployeeDAOImpl();
			FormDAOImpl fdi = new FormDAOImpl();
			Employee emp = edi.getEmployee(200);//(Integer) request.getAttribute("employeeid"));
			System.out.println(emp);
			formList = fdi.retrieveForms(emp.getId());
		}catch(SQLException e) {
			PrintWriter pw = response.getWriter();
			pw.print("System is down! Please try again later!");
			request.getRequestDispatcher("/home").include(request,  response);
>>>>>>> ed4c19b395be48303ea5d2b50fae57261aaac459
			e.printStackTrace();
			pw.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.getRequestDispatcher("/home.html").include(request,  response);
	}

	public <T> String generateFormJSONData(List<T> formList) {
		Gson gson = new Gson();
		if(formList == null){
			Form newForm = new Form();
			formList.add((T) newForm);
		}
		String resultJSON = gson.toJson(formList);
		return resultJSON;
	}
	
	public void generateFormValue(List<Form> formList, PrintWriter pw){
		for(Form form : formList){
			String date = form.getSubmissionDate();
			int status = form.getStatus();
			String statusStr = getstatus(status);
			Double amount = form.getReimbursementAmount();
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
		pw.println("<button type=\"button\" class=\"btn btn-primary btn-sm\" data-toggle=\"modal\" data-target=\"#myModal\">View More Info</button>");
	}
	
	public void generateRequestValue(List<Response> responseList, PrintWriter pw){
		for(Response response : responseList){
			int SID = response.getSender();
			int RID = response.getReceiver();
			String date = response.getResponseDate().toString();
			String res = response.getResponse();
			pw.println("<tr>");
			pw.println("<td>" + SID + "</td>");
			pw.println("<td>" + RID + "</td>");
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
		pw.println("<th>Sender ID</th>");
		pw.println("<th>Receiver ID</th>");
		pw.println("<th>Response Date</th>");
		pw.println("<th>Comment</th>");
		pw.println("</tr>");
		pw.println("</thead>");
	}
}
