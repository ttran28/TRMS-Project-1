package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employee;
import com.revature.daoimpl.EmployeeDAOImpl;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Forwards get requests
		req.getRequestDispatcher("/home.html").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In login servlet...");
		try {
			String[] info = getServletContext().getInitParameter("dbInfo").split(",");
			// Retrieves any employees who match the username/password
			EmployeeDAOImpl edi = new EmployeeDAOImpl(info);
			Employee emp = edi.employeeLogin(req.getParameter("email"), req.getParameter("password"));
			resp.setContentType("text/html");

			// Sends the user to the home screen if the username and password match an
			// employee
			
			System.out.println("emp.getId(): " + emp.getId());
			if (emp.getId() != 0) {
				HttpSession ses = req.getSession(true);
				ses.setAttribute("userid", emp.getId());
				resp.sendRedirect("/ReimbursementSystem/home");
				System.out.println("I'm inside the if statement...");
				int id = (int) ses.getAttribute("userid");
				System.out.println("sess id: " + id);
			}
			// Lets the user know the username and password don't match any employee records
			else {
				System.out.println("I'm inside the else statement...");
				PrintWriter pw = resp.getWriter();
				resp.setContentType("text/html");
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('Username and password do not match!');");
				pw.println("</script>");
				req.getRequestDispatcher("/login.html").include(req, resp);
				pw.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}