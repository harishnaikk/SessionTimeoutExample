package com.jcg.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/timeoutServlet")
public class TimeoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/***** This Method Is Called By The Servlet Container To Process A 'POST' Request *****/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
		
		
		
		System.out.println("harish");
	}

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/***** Set Response Content Type *****/
		response.setContentType("text/html");


		/***** Post Parameters From The Request *****/
		String param1 = request.getParameter("username");
		if (param1 != null && !param1.equals("")) {

			int timeout = 20;
			HttpSession sessionObj = request.getSession(true);


			/***** Setting The Updated Session Time Out *****/
			sessionObj.setMaxInactiveInterval(timeout);

			/***** Once The Time Out Is Reached. This Line Will Automatically Refresh The Page *****/
			response.setHeader("Refresh", timeout + "; URL=timeout.jsp");
		} else {
			RequestDispatcher rdObj = request.getRequestDispatcher("/index.jsp");
			rdObj.include(request, response);
		}

	}
}