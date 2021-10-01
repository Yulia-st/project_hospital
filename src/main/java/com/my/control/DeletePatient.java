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

import com.my.dao.PatientDao;
import com.my.db.DbManager;
import com.my.exception.DBException;

@WebServlet("/deleteP")
public class DeletePatient extends HttpServlet{
	private static Logger log = Logger.getLogger(InsertNewPatient.class.getName());
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
			System.out.println(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PatientDao dao = new PatientDao();
	        try {
	            int pId = Integer.parseInt(request.getParameter("pId"));          
	            dao.deletePatient(con, pId);
	            //response.sendRedirect(request.getContextPath() + "welcome.jsp");
	            response.sendRedirect("welcome.jsp");
	        }
	        catch(DBException ex) {
	        	log.log(Level.SEVERE, "deletePatient(con, p) failed : ", ex);
				request.setAttribute("errorMessage", ex.getMessage());
				request.getRequestDispatcher("/views/error.jsp").forward(request, response);
	        }
	    }
}
