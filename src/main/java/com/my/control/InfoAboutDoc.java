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
import com.my.dao.DoctorDao;
import com.my.db.DbManager;
import com.my.entity.Doctor;
import com.my.exception.DBException;

@WebServlet("/infoDoc")
public class InfoAboutDoc extends HttpServlet{
	private static Logger log = Logger.getLogger(InsertNewPatient.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DoctorDao dao = new DoctorDao();
		Doctor doc1 = null;
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			doc1 = dao.getDoctor(con, id);
		} catch (DBException ex) {
			log.log(Level.SEVERE, "getDoctor(con, id) failed : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
		}
		

		HttpSession session = request.getSession();
		session.setAttribute("doctor", doc1);
		response.sendRedirect("showDoc.jsp");
	}
}
