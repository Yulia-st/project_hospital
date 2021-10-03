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
import com.my.dao.HospitalCardDao;
import com.my.db.DbManager;
import com.my.entity.Doctor;
import com.my.entity.HospitalCard;

/**
 * Servlet displays list of hospiatl cards of patients on page.
 * 
 */
@WebServlet("/listCard")
public class ListOfCards extends HttpServlet {
	private static final Logger log = Logger.getLogger(ListOfCards.class);
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HospitalCardDao dao = new HospitalCardDao();
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();	
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "getConnection() failed : ", ex);
		}
		try {
		List<HospitalCard> listHC = dao.findAll(con);
		
		request.setAttribute("cards", listHC);
		
		RequestDispatcher rd = request.getRequestDispatcher("listCard.jsp");
		rd.forward(request, response);
		}catch (Exception ex) {
			log.log(Level.DEBUG, "findAll(con) failed : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("views/error.jsp").forward(request, response);
		}
//		HttpSession session=request.getSession();
//		session.setAttribute("patient", p1);
//		response.sendRedirect("showPatient.jsp");
	}
}
