package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.Employee;
import com.revature.daoimpl.EmployeeDAOImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.html").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			EmployeeDAOImpl edi = new EmployeeDAOImpl();
			Employee emp = edi.employeeLogin(req.getParameter("username"), req.getParameter("password"));
			resp.setContentType("text/html");
			
			if(emp.getId()!=0) {
				req.getRequestDispatcher("/home").forward(req, resp);
			}
			else {
				PrintWriter pw = resp.getWriter();
				pw.print("Username and password don't match!");
				req.getRequestDispatcher("/login").include(req,  resp);
				pw.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
