package com.my.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.my.dao.DoctorDao;
import com.my.db.DbManager;
import com.my.entity.Doctor;
import com.my.exception.DBException;
/**
 * Servlet displays a page after inserting a doctor.
 *
 */
@WebServlet("/addDoc")
public class InsertDoc extends HttpServlet {
	private static final Logger log = Logger.getLogger(InsertDoc.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		DoctorDao dDao = new DoctorDao();
		Connection con = null;

		try {
			con = DbManager.getInstance().getConnection();
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "getConnection() failed : ", ex);
		}
		try {
			String fname = request.getParameter("firstname");
			String lname = request.getParameter("lastname");
			String uname = request.getParameter("username");
			String cath = request.getParameter("cathegory");
			System.out.println(cath);

			String pwd = request.getParameter("password");
			int rid = Integer.parseInt(request.getParameter("rId"));
			
			Doctor d = new Doctor(fname, lname, uname, cath, pwd, rid);

			dDao.insertDoc(con, d);
		} catch (DBException ex) {
			log.log(Level.DEBUG, "insertDoc(con, d) failed : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("views/error.jsp").forward(request, response);
		}

		// response.sendRedirect("listOfPatients.jsp");
		response.sendRedirect("welcome.jsp");
	}
}
