package com.my.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.my.dao.PatientDao;
import com.my.db.DbManager;
import com.my.entity.Admin;
import com.my.entity.Patient;
import com.my.exception.DBException;
import com.my.exception.UsernameExistsException;

/**
 * Servlet displays a page after checking a patient 
 * and inserting a hospital card and a patient.
 *
 */
@WebServlet("/add")
public class InsertNewPatient extends HttpServlet {
	private static final Logger log = Logger.getLogger(InsertNewPatient.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "getConnection() failed : ", ex);
		}
		try {
			String fname = request.getParameter("firstname");
			String lname = request.getParameter("lastname");
			String uname = request.getParameter("username");
			String bd = request.getParameter("birthday");
			LocalDate localDate = LocalDate.parse(bd);
			Date date = java.sql.Date.valueOf(localDate);
			String pwd = request.getParameter("password");
			int rid = Integer.parseInt(request.getParameter("rId"));
			int hcid = Integer.parseInt(request.getParameter("hcId"));
			

			Patient p = new Patient(fname, lname, uname, date, pwd, rid, hcid);
			PatientDao dao = new PatientDao();
			
			
			Patient patient = null;
		//	try {
				patient = dao.getPatientByUsername(con, uname);

				if (patient.getUsername() == null) {
//					patient = new Patient();
//					patient.setFirstname(fname);
//					patient.setLastname(lname);
//					patient.setUsername(uname);
//					// user.setPassword(passwordEncoder.encode(password));
//					patient.setPassword(pwd);
//					patient.setrId(rid);
					dao.insertPatient(con, p);
					//dao.insertPatient(con, patient);//insertPatient(con, p)
					System.out.println(patient);
					log.log(Level.INFO, "insertPatient(con, user) has done " + patient);
					response.sendRedirect("welcome.jsp");
				} else {
					log.log(Level.INFO, "Username already Exists ");
					request.setAttribute("errorExistUser", "Username already Exists, change it to insert!");
					request.getRequestDispatcher("createP.jsp").forward(request, response);

					// throw new UsernameExistsException(uname);
				}

//			} 
//			catch (UsernameExistsException e) {
//				log.log(Level.DEBUG, "registeration new admin was failed : ", e);
//				request.setAttribute("errorMessage", e.getMessage());
//				request.getRequestDispatcher("/views/error.jsp").forward(request, response);
//			}
			
			
			
			
		} catch (SQLException | DBException ex) {
			log.log(Level.DEBUG, "insertPatient(con, p) failed : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("views/error.jsp").forward(request, response);
		}

	}
}
