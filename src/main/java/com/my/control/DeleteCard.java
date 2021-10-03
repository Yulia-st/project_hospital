package com.my.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.my.dao.HospitalCardDao;
import com.my.db.DbManager;
import com.my.exception.DBException;
/**
 * Servlet displays a page after deleting a hospital card.
 * 
 */
@WebServlet("/deleteC")
public class DeleteCard extends HttpServlet {
	private static final Logger log = Logger.getLogger(DeleteCard.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HospitalCardDao dao = new HospitalCardDao();
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "getConnection() failed : ", ex);
		}
		try {
			int hcId = Integer.parseInt(request.getParameter("hcId"));
			dao.deleteCard(con, hcId);
			response.sendRedirect("listCard.jsp");
		} catch (DBException ex) {
			log.log(Level.DEBUG, "deleteCard(con, hcId) failed : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("views/error.jsp").forward(request, response);
		}
	}
}
