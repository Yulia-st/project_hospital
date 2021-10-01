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

@WebServlet("/add")
public class InsertNewPatient extends HttpServlet {
	private static Logger log = Logger.getLogger(InsertNewPatient.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		try {
			String fname = request.getParameter("firstname");
			String lname = request.getParameter("lastname");
			String uname = request.getParameter("username");
			String bd = request.getParameter("birthday");
			LocalDate localDate = LocalDate.parse(bd);
			Date date = java.sql.Date.valueOf(localDate);
			String pwd = request.getParameter("password");
			int rid = Integer.parseInt(request.getParameter("rId"));
			int hcid = Integer.parseInt(request.getParameter("hcId"));
			

			Patient p = new Patient(fname, lname, uname, date, pwd, rid, hcid);
			PatientDao dao = new PatientDao();
			
			dao.insertPatient(con, p);
		} catch (SQLException | DBException ex) {
			log.log(Level.SEVERE, "insertPatient(con, p) failed : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
		}
		response.sendRedirect("welcome.jsp");
	}
}
