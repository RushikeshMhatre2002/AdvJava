package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.pojos.Candidate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("candid"));
		Candidate candidate = null;
		try (CandidateDao candDao = new CandidateDaoImpl()) {
			candidate = candDao.findById(id);
		} catch (Exception e) {
			throw new ServletException(e);
		}

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head><title>Edit Candidate</title></head>");
		out.println("<body>");
		out.println("<h2>Edit Candidate</h2>");
		String userName = "";
		Cookie[] arr = req.getCookies();
		if (arr != null) {
			for (Cookie c : arr) {
				if (c.getName().equals("uname"))
					userName = c.getValue();
			}
		}
		out.println("Hello, " + userName + "<hr/>");
		out.println("<form method='post' action='edit'>");
		out.println("<table border='1'>");
		out.println("<tr><td>Id:</td><td>");
		out.printf("<input type='number' name='id' value='%d' readonly><br><br>", candidate.getId());
		out.println("</td></tr>");
		out.println("<tr><td>Name:</td><td>");
		out.printf("<input type='text' name='name' value='%s'><br><br>", candidate.getName());
		out.println("</td></tr>");
		out.println("<tr><td>Party:</td><td>");
		out.printf("<input type='text' name='party' value='%s'><br><br>", candidate.getParty());
		out.println("</td></tr>");
		out.println("<tr><td>Party:</td><td>");
		out.printf("<input type='number' name='votes' value='%s' readonly><br><br>", candidate.getVotes());
		out.println("</td></tr>");
		out.println("</table>");
		out.println("<br/><input type='submit' value='Update'>");
		out.println("</form>");
		out.println("<br/><a href='announce.html'>Announcement</a>");
		out.println("<a href='logout'>Sign Out</a>");
		out.println("</body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String party = req.getParameter("party");
		int vote = Integer.parseInt(req.getParameter("votes"));

		try (CandidateDao candDao = new CandidateDaoImpl()) {
			candDao.update(new Candidate(id, name, party, vote));
		} catch (Exception e) {
			throw new ServletException(e);
		}
		// Redirect to result page after update
		resp.sendRedirect("result");
	}
}