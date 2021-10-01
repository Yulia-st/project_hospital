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

//import org.apache.logging.log4j.Level;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import com.my.dao.HospitalCardDao;
import com.my.dao.PatientDao;
import com.my.db.DbManager;
import com.my.entity.Patient;
import com.my.exception.DBException;

@WebServlet("/dischargeP")
public class DischargeP extends HttpServlet{
	private static Logger logger = Logger.getLogger(InsertNewPatient.class.getName());
	 //private static final Log logger = LogManager.getLogger();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		HospitalCardDao dao = new HospitalCardDao();
		PatientDao pDao = new PatientDao();
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
			
		} catch (SQLException e) {
			e.printStackTrace();
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
	        	logger.log(Level.SEVERE, "getDischarge(con, hcId, p) failed : ", ex);
				request.setAttribute("errorMessage", ex.getMessage());
				request.getRequestDispatcher("/views/error.jsp").forward(request, response);
	        }
	    }
}
