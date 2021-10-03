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
import com.my.db.DbManager;
import com.my.entity.Doctor;
import com.my.exception.DBException;

/**
 * Servlet displays a form for edit an information about a doctor on a page.
 * 
 */
@WebServlet("/editD")
public class EditDoctor extends HttpServlet{
	private static final Logger log = Logger.getLogger(EditDoctor.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		response.setContentType("text/html");
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		DoctorDao dao = new DoctorDao();
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "getConnection() failed : ", ex);
		}
	        try {
	            int dId = Integer.parseInt(request.getParameter("dId"));
	            Doctor doctor = dao.getDoctor(con, dId);
	            if(doctor!=null) {
	                request.setAttribute("doctor", doctor);
	                getServletContext().getRequestDispatcher("/editDoc.jsp").forward(request, response);
	            }
	            else {
	                getServletContext().getRequestDispatcher("/notFound.jsp").forward(request, response);
	            }
	        }catch (DBException ex) {
				log.log(Level.DEBUG, "getDoctor(con, dId) failed : ", ex);
				request.setAttribute("errorMessage", ex.getMessage());
				request.getRequestDispatcher("views/error.jsp").forward(request, response);
			}
	    }
	     
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    	response.setContentType("text/html");
		    request.setCharacterEncoding("utf-8");
		    response.setCharacterEncoding("utf-8");
	    	DoctorDao dao = new DoctorDao();
	    	Connection con = null;
			try {
				con = DbManager.getInstance().getConnection();
			} catch (SQLException ex) {
				log.log(Level.DEBUG, "getConnection() failed : ", ex);
			}
	        try {
	        	int did = Integer.parseInt(request.getParameter("dId"));
	        	String fname = request.getParameter("firstname");
	    		String lname = request.getParameter("lastname");
	    		String uname = request.getParameter("username");
	    		String cat = request.getParameter("category");
	    		String pwd = request.getParameter("password");
	            int rid = Integer.parseInt(request.getParameter("rId"));
	         
	            Doctor doc = new Doctor(did, fname,lname,uname,cat,pwd,rid);
	            dao.updateDoctor(con, doc);
	            response.sendRedirect("welcome.jsp");
	        }catch (DBException ex) {
				log.log(Level.DEBUG, "updateDoctor(con, p) failed : ", ex);
				request.setAttribute("errorMessage", ex.getMessage());
				request.getRequestDispatcher("views/error.jsp").forward(request, response);
			}
	    }
}
