package com.my.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.PatientDao;
import com.my.db.DbManager;
import com.my.entity.Patient;

@WebServlet("/sortName")
public class SortByName extends HttpServlet {
	private static Logger log = Logger.getLogger(InsertNewPatient.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PatientDao dao = new PatientDao();
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			List<Patient> list = dao.sortPtnByNm(con);

			request.setAttribute("patients", list);

			RequestDispatcher rd = request.getRequestDispatcher("listOfPatients.jsp");
			rd.forward(request, response);
		} catch (Exception ex) {
			log.log(Level.SEVERE, "sortPtnByNm(con) failed : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
		}
//		HttpSession session=request.getSession();
//		session.setAttribute("patient", p1);
//		response.sendRedirect("showPatient.jsp");
	}

}
