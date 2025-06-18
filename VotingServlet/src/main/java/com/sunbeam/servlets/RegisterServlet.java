package com.sunbeam.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.daos.UserDao;
import com.sunbeam.pojos.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(req, resp);
	}
	
	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// TODO Auto-generated method stub
		
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String birth = req.getParameter("birth");
		Date sqlDate = java.sql.Date.valueOf(birth);
		String passwd = req.getParameter("passwd");
		
		User newuser = new User(0, firstName, lastName, email, passwd, sqlDate, false, "voter");
		try(UserDao userdao = new UserDaoImpl()) {
			 int count= userdao.save(newuser);
			 if(count == 1) {
				 
				 System.out.println("user Registered Succesfully");
				 resp.sendRedirect("index.html");
			 }else {
				 System.out.println("User Not Registered");
			 }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
