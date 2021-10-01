package com.my.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.PatientDao;
import com.my.db.DbManager;
import com.my.entity.Patient;
import com.my.exception.DBException;

@WebServlet("/edit")
public class EditPatient extends HttpServlet {
	private static Logger log = Logger.getLogger(InsertNewPatient.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		PatientDao dao = new PatientDao();
		try {
			int pId = Integer.parseInt(request.getParameter("pId"));
			Patient patient = dao.getPatient(con, pId);
			if (patient != null) {
				request.setAttribute("patient", patient);
				getServletContext().getRequestDispatcher("/editPatient.jsp").forward(request, response);
			} else {
				getServletContext().getRequestDispatcher("/notFound.jsp").forward(request, response);
			}
		} catch (DBException ex) {
			log.log(Level.SEVERE, "insertPatient(con, p) failed : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		PatientDao dao = new PatientDao();
		try {
			int id = Integer.parseInt(request.getParameter("pId"));
			String fname = request.getParameter("firstname");
			String lname = request.getParameter("lastname");
			String uname = request.getParameter("username");
			String bd = request.getParameter("birthday");
			LocalDate localDate = LocalDate.parse(bd);
			Date date = java.sql.Date.valueOf(localDate);
			String pwd = request.getParameter("password");
			int rid = Integer.parseInt(request.getParameter("rId"));
			int hcid = Integer.parseInt(request.getParameter("hcId"));

			Patient patient = new Patient(id, fname, lname, uname, date, pwd, rid, hcid);
			dao.updatePatient(con, patient);
			response.sendRedirect("welcome.jsp");
		} catch (DBException ex) {
			log.log(Level.SEVERE, "updatePatient(con, p) failed : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
		}
	}
}
