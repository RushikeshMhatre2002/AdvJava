package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.pojos.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(req, resp);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String email= req.getParameter("email");
		String passwd = req.getParameter("passwd");
		
		try(UserDao userdao = new UserDaoImpl()) {
			User dbUser = userdao.findByEmail(email);
			if(dbUser != null && dbUser.getPasswd().equals(passwd)) {
				System.out.println("Login Successful");
				String userName = dbUser.getFirstName() + dbUser.getLastName();
				Cookie c = new Cookie("uname", userName);
				c.setMaxAge(3600);
				resp.addCookie(c);
				HttpSession session = req.getSession();
				session.setAttribute("user", dbUser);
				
				if(dbUser.getRole().equals("voter")) {
					resp.sendRedirect("candlist");
				}
				else if(dbUser.getRole().equals("admin")) {
					resp.sendRedirect("result");
				}
			}
			else {
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title> Login </title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h2>Login Failed</h2>");
				out.println("<a href ='index.html'>Login Again</a>");
				out.println("</body>");
				out.println("</html>");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
