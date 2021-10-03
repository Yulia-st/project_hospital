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

import com.my.dao.DoctorDao;
import com.my.db.DbManager;
import com.my.entity.Doctor;

/**
 * Servlet displays list of some part of doctors by pages.
 * 
 */

@WebServlet("/listDoc")
public class ListOfDoctors extends HttpServlet {
	private static final Logger log = Logger.getLogger(ListOfDoctors.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "getConnection() failed : ", ex);
		}
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int page = 1;
		int recordsPerPage = 5;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));

		DoctorDao dao = new DoctorDao();
		int noOfRecords;
		int noOfPages;
		try {
			noOfRecords = dao.getNoOfRecords(con);
			noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			int offset = (page - 1) * recordsPerPage;
			int offset2 = (recordsPerPage * page) - recordsPerPage;

			List<Doctor> list = dao.findAllDoctors(con, recordsPerPage, offset2);

			request.setAttribute("doctors", list);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			RequestDispatcher rd = request.getRequestDispatcher("listDoc.jsp");
			rd.forward(request, response);

//		HttpSession session=request.getSession();
//		session.setAttribute("patient", p1);
//		response.sendRedirect("showPatient.jsp");
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "findAllDoctors(con, recordsPerPage, offset2) failed : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("views/error.jsp").forward(request, response);
		}
	}
}
