package com.revature.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employee;
import com.revature.beans.Response;
import com.revature.daoimpl.EmployeeDAOImpl;
import com.revature.daoimpl.ResponseDAOImpl;

public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In response get servlet...");
		try {
			// Retrieves the database information
			String[] info = getServletContext().getInitParameter("dbInfo").split(",");
			ResponseDAOImpl rdi = new ResponseDAOImpl(info);
			EmployeeDAOImpl edi = new EmployeeDAOImpl(info);
			
			// Retrieves the http session
			HttpSession ses = req.getSession(false);
			
			// Retrieves the users responses
			List<Response> responses = rdi.retrieveAllResponses((int) ses.getAttribute("userid"));
			Map<Integer, String> senders = new HashMap<>();
			Map<Integer, String> receivers = new HashMap<>();
			
			// Retrieves the responses senders and receivers
			for(int i = 0; i < responses.size(); i++) {
				Employee sender = edi.getEmployee(responses.get(i).getSender());
				Employee receiver = edi.getEmployee(responses.get(i).getReceiver());
				senders.put(sender.getId(), sender.getName());
				receivers.put(receiver.getId(), receiver.getName());
			}
			
			// Puts the response information into an html table
			PrintWriter pw = resp.getWriter();
			pw.println("<table BORDER=1 CELLPADDING=0 CELLSPACING=0 WIDTH=100%>"
		              +"<tr><th>SENDER</th><th>RECEIVER</th><th>DATE</th><th>COMMENTS</th><th>FILE</th></tr>");

			for(int i = 0; i < responses.size(); i++){
				pw.println("<tr onClick=\"HighLightTR(this,'#c9cc99','cc3333');\" name=\"" + i + "\"><td><center>" + senders.get(i)+ "</center></td>" +
						   "<td><center>" + receivers.get(i) + "</center>" +
						   "<td><center>" + responses.get(i).getResponseDate() + "</center>" +
						   "<td><center>" + responses.get(i).getResponse() + "</center>" +
						   "<td><center>" + responses.get(i).getAttachment().getAbsolutePath() + "</center></td></tr>");
			}
			pw.println("</table>");
			
			// Returns the information to the home page
			req.getRequestDispatcher("/home.html").forward(req, resp);
		}catch(SQLException e) {
			// Lets the user know the system is down
			PrintWriter pw = resp.getWriter();
			resp.setContentType("text/html");  
			pw.println("<script type=\"text/javascript\">");  
			pw.println("alert('System is down! Please try again later!');");  
			pw.println("</script>");
			req.getRequestDispatcher("login.html").include(req,  resp);
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In response post servlet...");
		try {
			// Retrieves the database information
			String[] info = getServletContext().getInitParameter("dbInfo").split(",");
			ResponseDAOImpl rdi = new ResponseDAOImpl(info);
			// Stores the response information and puts it into the database
			Response r = new Response();
			r.setSender(Integer.parseInt(req.getParameter("sender")));
			r.setReceiver(Integer.parseInt(req.getParameter("receiver")));
			r.setAttachment(new File("na"));
			r.setResponse(req.getParameter("response"));
			rdi.createResponse(r);
			//Returns to the homepage
			req.getRequestDispatcher("/home.html").forward(req,  resp);
		}catch(SQLException | NumberFormatException e) {
			//Lets the user know the system is down
			PrintWriter pw = resp.getWriter();
			pw.print("System is down! Please try again later!");
			req.getRequestDispatcher("/home").include(req,  resp);
			pw.close();
			e.printStackTrace();
		}
	}

}
