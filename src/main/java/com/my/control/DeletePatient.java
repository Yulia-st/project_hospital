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

import com.my.dao.PatientDao;
import com.my.db.DbManager;
import com.my.exception.DBException;
/**
 * Servlet displays a page after deleting a patient.
 * 
 */
@WebServlet("/deleteP")
public class DeletePatient extends HttpServlet{
	private static final Logger log = Logger.getLogger(DeletePatient.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
			System.out.println(con);
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "getConnection() failed : ", ex);
		}
		PatientDao dao = new PatientDao();
	        try {
	            int pId = Integer.parseInt(request.getParameter("pId"));          
	            dao.deletePatient(con, pId);
	            //response.sendRedirect(request.getContextPath() + "welcome.jsp");
	            response.sendRedirect("welcome.jsp");
	        }
	        catch(DBException ex) {
	        	log.log(Level.DEBUG, "deletePatient(con, p) failed : ", ex);
				request.setAttribute("errorMessage", ex.getMessage());
				request.getRequestDispatcher("views/error.jsp").forward(request, response);
	        }
	    }
}
