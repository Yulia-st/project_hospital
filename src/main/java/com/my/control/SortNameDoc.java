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

import com.my.dao.DoctorDao;
import com.my.db.DbManager;
import com.my.entity.Doctor;
import com.my.exception.DBException;
/**
 * Servlet displays a page after sorting the doctors by their lastname.
 *
 */
@WebServlet("/sortNameDoc")
public class SortNameDoc extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SortNameDoc.class);
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	DoctorDao dao = new DoctorDao();
	Connection con = null;
	try {
		con = DbManager.getInstance().getConnection();
	} catch (SQLException ex) {
		log.log(Level.DEBUG, "getConnection() failed : ", ex);
	}
		
		try {
		List<Doctor> list = dao.sortDocByNm(con);
		log.log(Level.INFO, "sortDocByNm(con) has done ");
		request.setAttribute("doctors", list);
		RequestDispatcher rd = request.getRequestDispatcher("listDoc.jsp");
		rd.forward(request, response);
		}catch(Exception ex) {
			log.log(Level.DEBUG, "sortDocByNm(con) failed : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("views/error.jsp").forward(request, response);
		}
//		HttpSession session=request.getSession();
//		session.setAttribute("doctors", list);
//		response.sendRedirect("listDoc.jsp");
	}
}
