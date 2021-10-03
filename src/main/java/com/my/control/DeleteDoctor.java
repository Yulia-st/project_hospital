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
import com.my.exception.DBException;
/**
 * Servlet displays a page after removing a doctor.
 * 
 */
@WebServlet("/deleteDoc")
public class DeleteDoctor extends HttpServlet{
	//private static Logger log = Logger.getLogger(InsertNewPatient.class.getName());
	private static final Logger log = Logger.getLogger(DeleteDoctor.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		DoctorDao dao = new DoctorDao();
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "getConnection() failed : ", ex);
		}
	        try {
	            int dId = Integer.parseInt(request.getParameter("dId"));          
	            dao.deleteDoctor(con, dId);
	            response.sendRedirect("welcome.jsp");
	        }catch (DBException ex) {
				log.log(Level.DEBUG, "deleteDoctor(con, dId) failed : ", ex);
				request.setAttribute("errorMessage", ex.getMessage());
				request.getRequestDispatcher("views/error.jsp").forward(request, response);
			}
	    }
}
