package com.my.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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


@WebServlet("/editD")
public class EditDoctor extends HttpServlet{
	private static Logger log = Logger.getLogger(InsertNewPatient.class.getName());
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		response.setContentType("text/html");
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		DoctorDao dao = new DoctorDao();
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
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
				log.log(Level.SEVERE, "getDoctor(con, dId) failed : ", ex);
				request.setAttribute("errorMessage", ex.getMessage());
				request.getRequestDispatcher("/views/error.jsp").forward(request, response);
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
			} catch (SQLException e) {
				e.printStackTrace();
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
				log.log(Level.SEVERE, "updateDoctor(con, p) failed : ", ex);
				request.setAttribute("errorMessage", ex.getMessage());
				request.getRequestDispatcher("/views/error.jsp").forward(request, response);
			}
	    }
}
