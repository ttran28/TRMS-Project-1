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
	private ResponseServlet respServ;
	private SubmitServlet submitServ;
	
	public Controller() {
		formServ = new FormServlet();
		homeServ = new HomeServlet();
		loginServ = new LoginServlet();
		respServ = new ResponseServlet();
		submitServ = new SubmitServlet();
	}
	
	private void track(String req) {
		System.out.println("Requesting page: " + req);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		dispatch(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		dispatch(req, resp);
	}
	
	private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		if(req.getRequestDispatcher("/home") != null) {
			req.getRequestDispatcher("/home").forward(req, resp);
		}
		if(req.getRequestDispatcher("/login") != null) {
			req.getRequestDispatcher("/login").forward(req, resp);
		}
		if(req.getRequestDispatcher("/form") != null) {
			req.getRequestDispatcher("/form").forward(req, resp);
		}
		if(req.getRequestDispatcher("/submit") != null) {
			req.getRequestDispatcher("/submit").forward(req, resp);
		}
		if(req.getRequestDispatcher("/response") != null) {
			req.getRequestDispatcher("/response").forward(req, resp);
		}
	}
	
	public void respond(HttpServletResponse resp) throws ServletException, IOException{
		
	}
}
