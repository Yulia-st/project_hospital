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

import com.my.db.DbManager;

import com.my.exception.DBException;

@WebServlet("/test")
public class ConnectionTest extends HttpServlet {
	//private static Logger log = Logger.getLogger(ConnectionTest.class.getName());
	private static final Logger log = Logger.getLogger(ConnectionTest.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ConnectionTest#doGet");
		response.getWriter().println(" ok ");
		
		try {
			Connection con = DbManager.getInstance().getConnection();
			System.out.println(con);
		}catch(SQLException ex){
			log.log(Level.DEBUG, "getConnection() failed : ", ex);
		}
}
		
//		try {
//			Patient p = new Patient();
//			p.setFirstname("ivan");
//			p.setLastname("ivashina");
//			p.setPassword("ivash");
//			DbManager dbMan = DbManager.getInstance();
//			dbMan.insertPatient0(p);
//		}catch(DBException ex) {
//			log.log(Level.SEVERE, "insertPatient(Patient patient) failed: ", ex);
//			//log
//			//2 save error msg in appropriate container
//			
//			request.setAttribute("errorMessage", ex.getMessage());;
//			//go to error page
//			request.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(request, response); 
//		}
			
		////////////////////
		/*
		HospitalCard hc = new HospitalCard();
		try {
			
			hc.setDiagnosis("some");
			hc.setMedPrescription("aaa");
			DbManager dbMan = DbManager.getInstance();
			dbMan.insertHospitalCard(hc);
			
			
		} catch (DBException ex) {
			
			log.log(Level.SEVERE, "insertPatient(Patient patient) failed: ", ex);
			//log
			//2 save error msg in appropriate container
			
			request.setAttribute("errorMessage", ex.getMessage());;
			//go to error page
			request.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(request, response); 
		}
		//DbManager.instance.insertUser(Patient.createUser("petrov"));
	}*/

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doGet(request, response);
	}

}
