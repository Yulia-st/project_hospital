package com.my.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.my.dao.PatientDao;
import com.my.db.DbManager;
import com.my.entity.Patient;
/**
 * Servlet displays a page after sorting the patients by their lastname.
 *
 */
@WebServlet("/sortName")
public class SortByName extends HttpServlet {
	private static final Logger log = Logger.getLogger(SortByName.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PatientDao dao = new PatientDao();
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "getConnection() failed : ", ex);
		}
		try {
			List<Patient> list = dao.sortPtnByNm(con);

			request.setAttribute("patients", list);

			RequestDispatcher rd = request.getRequestDispatcher("listOfPatients.jsp");
			rd.forward(request, response);
		} catch (Exception ex) {
			log.log(Level.DEBUG, "sortPtnByNm(con) failed : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("views/error.jsp").forward(request, response);
		}
//		HttpSession session=request.getSession();
//		session.setAttribute("patient", p1);
//		response.sendRedirect("showPatient.jsp");
	}

}
