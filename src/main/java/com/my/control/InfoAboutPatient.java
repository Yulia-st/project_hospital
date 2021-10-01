package com.my.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.dao.PatientDao;
import com.my.db.DbManager;
import com.my.entity.Patient;
import com.my.exception.DBException;

@WebServlet("/info")
public class InfoAboutPatient extends HttpServlet {
	private static Logger log = Logger.getLogger(InsertNewPatient.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PatientDao dao = new PatientDao();
		Patient p1 = null;
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			p1 = dao.getPatient(con, id);
		} catch (DBException ex) {
			log.log(Level.SEVERE, "insertPatient(con, p) failed : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
		}
		

		HttpSession session = request.getSession();
		session.setAttribute("patient", p1);
		response.sendRedirect("showPatient.jsp");
	}
}
