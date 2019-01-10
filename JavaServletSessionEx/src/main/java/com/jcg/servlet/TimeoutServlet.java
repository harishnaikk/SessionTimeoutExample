package com.jcg.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
	}

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/***** Set Response Content Type *****/
		response.setContentType("text/html");

		/***** Print The Response *****/
		PrintWriter out = response.getWriter();
		String title = "Session Time-Out";		
		String docType = "<!DOCTYPE html>\n";
		out.println(docType 
				+ "<html>\n" + "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>" + title + "</title></head>\n" + "<body>");

		/***** Post Parameters From The Request *****/
		String param1 = request.getParameter("username");
		if (param1 != null && !param1.equals("")) {

			int timeout = 20;
			HttpSession sessionObj = request.getSession(true);

			out.println("<div id='serlvetResponse' style='text-align: left;'>");
			out.println("<h2>Serlvet Session Timeout Example</h2>");
			out.println("<p style='color: green; font-size: large;'>Congratulations! You are an authorised login.</p>");
			out.println("<ul><li><span id=\"usernameId\">Username is?= </span>" + param1 + "</li>");
			out.println("<li><span id=\"defaultTimeOutId\">Default session timeout is?= </span>" + sessionObj.getMaxInactiveInterval() + " seconds.</li>");

			/***** Setting The Updated Session Time Out *****/
			sessionObj.setMaxInactiveInterval(timeout);
			out.println("<li><span id=\"alteredTimeOutId\">Session timeout is altered to?= </span>" + sessionObj.getMaxInactiveInterval() + " seconds.</li></ul>");

			/***** Once The Time Out Is Reached. This Line Will Automatically Refresh The Page *****/
			response.setHeader("Refresh", timeout + "; URL=timeout.jsp");
		} else {
			out.println("<p id='errMsg' style='color: red; font-size: larger; margin-left: 564px'>Please Enter a Correct Name!</p>");
			RequestDispatcher rdObj = request.getRequestDispatcher("/index.jsp");
			rdObj.include(request, response);
		}

		out.println("</body></html>");
		out.close();
	}
}