package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.pojos.Candidate;
import com.sunbeam.pojos.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(req, resp);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// TODO Auto-generated method stub
		List<Candidate> list = new ArrayList<>();
		try(CandidateDao candDao = new CandidateDaoImpl()) {
			list = candDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();	// print exception on console
			throw new ServletException(e); 	// send exception to web container -- display on resp page
		}
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Result</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>Result</h2>");
		String userName="";
		Cookie[] arr = req.getCookies();
		if(arr != null)
		{
			for(Cookie c : arr) {
				if(c.getName().equals("uname"))
					userName = c.getValue();			}
		}
		out.println("hello," + userName + "<hr/>");
		out.println("<table border='1'>");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th>Id</th>");
		out.println("<th>Name</th>");
		out.println("<th>Party</th>");
		out.println("<th>Votes</th>");
		out.println("<th>Action</th>");
		out.println("</tr>");
		out.println("</thead>");
		for (Candidate cand : list) {
			out.println("<tr>");
			out.printf("<td>%d</td>\n", cand.getId());
			out.printf("<td>%s</td>\n", cand.getName());
			out.printf("<td>%s</td>\n", cand.getParty());
			out.printf("<td>%d</td>\n", cand.getVotes());
			out.println("<td>");
			out.printf("<a href='delcand?candid=%d'><img src='images/Delete.png' alt='Delete' height='25px'></a>\n", cand.getId());
			out.println("</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("<br/>");
		String msg = (String) req.getAttribute("msg");
		if (msg != null)
			out.println("<p>" + msg + "</p>");
		out.println("<a href='announce.html'>Announcement</a>");
		//out.println("<a href='addcandidate.html'>New Candidate</a>");
		out.println("<a href='logout'>Sign Out</a>");
		out.println("<form method='post' action='addcandidate.html'>");
		out.println("<input type='submit' value='New Candidate'>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
}
