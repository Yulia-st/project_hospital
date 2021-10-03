package com.my.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
import com.my.exception.DBException;
/**
 * Servlet displays a form for edit an information about a hospitl card by it id on a page.
 * 
 */
@WebServlet("/editCard")
public class EditCard extends HttpServlet{
	private static final Logger log = Logger.getLogger(EditCard.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		response.setContentType("text/html");
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		HospitalCardDao dao = new HospitalCardDao();
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();	
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "getConnection() failed : ", ex);
		}
	        try {
	            int hcId = Integer.parseInt(request.getParameter("hcId"));
	            HospitalCard card = dao.getHospitalCard(con, hcId);
	            if(card!=null) {
	                request.setAttribute("card", card);
	                getServletContext().getRequestDispatcher("/editCard.jsp").forward(request, response);
	            }
	            else {
	                getServletContext().getRequestDispatcher("/notFound.jsp").forward(request, response);
	            }
	        }catch (DBException ex) {
				log.log(Level.DEBUG, "Cannot get a hospital card : ", ex);
				request.setAttribute("errorMessage", ex.getMessage());
				request.getRequestDispatcher("views/error.jsp").forward(request, response);
			}
	    }
	     
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    	response.setContentType("text/html");
		    request.setCharacterEncoding("utf-8");
		    response.setCharacterEncoding("utf-8");
	    	HospitalCardDao dao = new HospitalCardDao();
	    	Connection con = null;
			try {
				con = DbManager.getInstance().getConnection();	
			} catch (SQLException ex) {
				log.log(Level.DEBUG, "getConnection() failed : ", ex);
			}
	        try {
	        	int hcId = Integer.parseInt(request.getParameter("hcId"));
	        	String dia = request.getParameter("diagnosis");
	    		String medPr = request.getParameter("medPrescription");
	    		int did = Integer.parseInt(request.getParameter("dId"));
	            
	    		HospitalCard hospitalCard = new HospitalCard(hcId,dia, medPr, did);
	            dao.updateCard(con, hospitalCard);
	            response.sendRedirect("welcome.jsp");
	        }catch (DBException ex) {
				log.log(Level.DEBUG, "updateCard(con, hospitalCard) failed : ", ex);
				request.setAttribute("errorMessage", ex.getMessage());
				request.getRequestDispatcher("views/error.jsp").forward(request, response);
			}
	    }
}
