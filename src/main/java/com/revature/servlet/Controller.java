package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet{
	private static final long serialVersionUID = -2572291301119125965L;
	
	private FormServlet formServ;
	private HomeServlet homeServ;
	private LoginServlet loginServ;
	
	public Controller() {
		formServ = new FormServlet();
		homeServ = new HomeServlet();
		loginServ = new LoginServlet();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		dispatch(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		dispatch(req, resp);
	}
	
	private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		if(req.getRequestDispatcher("/home") != null) {
			homeServ.init();
			homeServ.service(req, resp);
			homeServ.destroy();
		}
		if(req.getRequestDispatcher("/login") != null) {
			loginServ.init();
			loginServ.service(req, resp);
			loginServ.destroy();
		}
		if(req.getRequestDispatcher("/form") != null) {
			formServ.init();
			formServ.service(req, resp);
			formServ.destroy();
		}
	}
	
	// Sends the response back to the html page
	public static void respond(HttpServletResponse resp, String response) throws ServletException, IOException{
		resp.getWriter().write(response);
	}
}
