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
			e.printStackTrace();
			pw.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
