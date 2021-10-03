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
import javax.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.my.dao.PatientDao;
import com.my.db.DbManager;
import com.my.entity.Patient;

/**
 * Servlet displays list of some part of patients by pages.
 * 
 */

@WebServlet("/list")
public class ListOfPatients extends HttpServlet {
	private static final Logger log = Logger.getLogger(ListOfPatients.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
			log.info("Connection well done ");
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "getConnection() failed : ", ex);
		}

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// HttpSession session=request.getSession();
		// session.setAttribute("patient", p1);

//    if (null != request.getSession().getAttribute("patient")) {
//        request.getSession().removeAttribute("patient");
//    }
//    doPost(request, response);
		// Patient p = (Patient)request.getSession().getAttribute("patient");

		int page = 1;
		int recordsPerPage = 2;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));

		PatientDao dao = new PatientDao();
		// List<Patient> listPt = dao.viewAllEmployees((page-1)*recordsPerPage,
		// recordsPerPage);
//    List<Patient> listPt = dao.findAllPatients((page-1)*recordsPerPage,
//            recordsPerPage);

		int noOfRecords;
		int noOfPages;
		try {
			noOfRecords = dao.getNoOfRecords(con);

			noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

			// int id = Integer.parseInt(request.getParameter("id"));
			// PatientDao dao = new PatientDao();
			// List<Patient> list = dao.findAllPatients();
			int offset = (page - 1) * recordsPerPage;
			int offset2 = (recordsPerPage * page) - recordsPerPage;
			List<Patient> listPt = dao.findAllPatients(con, recordsPerPage, offset2);// (offset, recordsPerPage);

			request.setAttribute("patients", listPt);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			log.info("findAllPatients(con, recordsPerPage, offset2) well done ");
			RequestDispatcher rd = request.getRequestDispatcher("listOfPatients.jsp");
			rd.forward(request, response);
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "findAllPatients(con, recordsPerPage, offset2) failed : ", ex);

			request.setAttribute("errorMessage", ex.getMessage());

			request.getRequestDispatcher("views/error.jsp").forward(request, response);
		}
//		HttpSession session=request.getSession();
//		session.setAttribute("patient", p1);
//		response.sendRedirect("showPatient.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}

}
