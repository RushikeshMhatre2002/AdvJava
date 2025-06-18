package com.sunbeam.servlets;

import java.io.IOException;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delcand")
public class DeleteCandidateServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(req, resp);
	}
	
	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String CandId = req.getParameter("candid");
		int id = Integer.parseInt(CandId);
		try(CandidateDao candidatedao = new CandidateDaoImpl()){
			int count = candidatedao.deleteById(id);
			System.out.println("Candidate deleted: " + count);
			String msg = "Candidates deleted: " + count;
			req.setAttribute("msg", msg);
			RequestDispatcher rd = req.getRequestDispatcher("result");
			rd.forward(req, resp);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(req, resp);
	}
}
