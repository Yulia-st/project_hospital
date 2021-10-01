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

import com.my.dao.DoctorDao;
import com.my.db.DbManager;
import com.my.entity.Doctor;
import com.my.exception.DBException;

@WebServlet("/addDoc")
public class InsertDoc extends HttpServlet {
	private static Logger log = Logger.getLogger(InsertNewPatient.class.getName());

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		DoctorDao dDao = new DoctorDao();
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
			String cath = request.getParameter("cathegory");
			System.out.println(cath);

			String pwd = request.getParameter("password");
			int rid = Integer.parseInt(request.getParameter("rId"));
			
			Doctor d = new Doctor(fname, lname, uname, cath, pwd, rid);

			dDao.insertDoc(con, d);
		} catch (DBException ex) {
			log.log(Level.SEVERE, "insertDoc(con, d) failed : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
		}

		// response.sendRedirect("listOfPatients.jsp");
		response.sendRedirect("welcome.jsp");
	}
}
