package com.my.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.my.dao.PatientDao;
import com.my.db.DbManager;
import com.my.entity.Patient;
import com.my.exception.DBException;
/**
 * Servlet displays an information of a patient on a page.
 * 
 */
@WebServlet("/info")
public class InfoAboutPatient extends HttpServlet {
	private static final Logger log = Logger.getLogger(InfoAboutPatient.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "getConnection() failed : ", ex);
		}
		PatientDao dao = new PatientDao();
		Patient p1 = null;
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			p1 = dao.getPatient(con, id);
		} catch (DBException ex) {
			log.log(Level.DEBUG, "getPatient(con, id) failed : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("views/error.jsp").forward(request, response);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("patient", p1);
		response.sendRedirect("showPatient.jsp");
	}
}
