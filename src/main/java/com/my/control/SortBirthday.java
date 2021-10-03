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
 * Servlet displays a page after sorting the patients by their birthday.
 *
 */
@WebServlet("/sortBirthday")
public class SortBirthday extends HttpServlet {
	private static final Logger log = Logger.getLogger(SortBirthday.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
			System.out.println(con);
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "getConnection() failed : ", ex);
		}
		PatientDao dao = new PatientDao();
		List<Patient> list = dao.sortBirthday(con);
		log.log(Level.INFO, "sortBirthday(con) has done ");
		
		request.setAttribute("patients", list);
		RequestDispatcher rd = request.getRequestDispatcher("listOfPatients.jsp");
		rd.forward(request, response);

//		HttpSession session=request.getSession();
//		session.setAttribute("patients", list);
//		response.sendRedirect("listOfPatients.jsp");
	}

}
