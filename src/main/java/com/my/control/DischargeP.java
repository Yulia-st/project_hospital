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

import com.my.dao.HospitalCardDao;
import com.my.dao.PatientDao;
import com.my.db.DbManager;
import com.my.entity.Patient;
import com.my.exception.DBException;
/**
 * Servlet displays a page after forming discharge of a patient.
 * 
 * * Method getDischarge(con, hcId, p) provides getting of  a discharge of patients: 
	 * 1) check if id's patient exist 
	 * 2) check if file of discharge exist on disk, remove it
	 * 3) delete set up doctor to patient
 * 
 */
@WebServlet("/dischargeP")
public class DischargeP extends HttpServlet{
	private static final Logger log = Logger.getLogger(DischargeP.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		HospitalCardDao dao = new HospitalCardDao();
		PatientDao pDao = new PatientDao();
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
			
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "getConnection() failed : ", ex);
		}
	        try {
	            int hcId = Integer.parseInt(request.getParameter("hcId")); 
	            //int pId = Integer.parseInt(request.getParameter("p_id"));
	            //Patient p = pDao.getPatient(pId);
	            Patient p = pDao.getPatient(con, hcId);
	            dao.getDischarge(con, hcId, p);
	            response.sendRedirect("welcome.jsp");
	        }
	        catch(DBException ex) {
	        	log.log(Level.DEBUG, "getDischarge(con, hcId, p) failed : ", ex);
				request.setAttribute("errorMessage", ex.getMessage());
				request.getRequestDispatcher("views/error.jsp").forward(request, response);
	        }
	    }
}
