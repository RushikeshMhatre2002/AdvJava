package com.sunbeam.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.daos.CandidateDao;
import com.sunbeam.pojos.Candidate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addcandidate")
public class AddCandidateServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(req, resp);
	}
	
	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// TODO Auto-generated method stub
		
		String Name = req.getParameter("name");
		String Party = req.getParameter("party");
		
		Candidate newcand = new Candidate(0, Name, Party, 0);
		try(CandidateDao canddao = new CandidateDaoImpl()) {
			 int count= canddao.save(newcand);
			 if(count == 1) {
				 
				 System.out.println("candidate Registered Succesfully");
				 resp.sendRedirect("result");
			 }else {
				 System.out.println("Candidate Not Registered");
			 }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

