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
import com.revature.beans.Form;
import com.revature.daoimpl.EmployeeDAOImpl;
import com.revature.daoimpl.FormDAOImpl;

public class DenyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int formId = Integer.parseInt(request.getParameter("id"));
		HttpSession ses = request.getSession(false);
		int empId = (int) ses.getAttribute("userid");
		
		PrintWriter pw = response.getWriter();
		try {
			String[] info = getServletContext().getInitParameter("dbInfo").split(",");
			FormDAOImpl fdi = new FormDAOImpl(info);
			EmployeeDAOImpl edi = new EmployeeDAOImpl(info);
			
			Employee emp = edi.getEmployee(empId);
			Form f = fdi.retrieveForm(formId);
			
			if(emp.isBenCo())
				f.setBenCoApproval(false);
			if(emp.isHead())
				f.setHeadApproval(false);
			if(emp.isSupervisor())
				f.setSupervisorApproval(false);
			
			f.setStatus(0);
			
			fdi.updateForm(f);
			
			request.getRequestDispatcher("/home.html").include(request, response);
		}catch(SQLException e) {
			pw.print("System is down! Please try again later!");
			// request.getRequestDispatcher("/home").include(request, response);
			e.printStackTrace();
			pw.close();
		}
	}

}