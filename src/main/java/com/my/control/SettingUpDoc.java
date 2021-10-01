package com.my.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.PatientDao;
import com.my.dao.PatientDoctorDao;
import com.my.db.DbManager;
import com.my.entity.Patient;
import com.my.entity.PatientDoctor;
import com.my.exception.DBException;

@WebServlet("/setUpDoc")
public class SettingUpDoc extends HttpServlet{
	private static Logger log = Logger.getLogger(InsertNewPatient.class.getName());
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("setDocForP.jsp");
	    rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PatientDoctorDao dao = new PatientDoctorDao();
        try {
        	int idPat = Integer.parseInt(request.getParameter("pId"));
        	int idDoc = Integer.parseInt(request.getParameter("dId"));
      
            PatientDoctor patDoc = new PatientDoctor(idPat, idDoc);
            dao.insertPatDoc(con, patDoc);
            
            response.sendRedirect("welcome.jsp");
        }
        catch (DBException ex) {
			log.log(Level.SEVERE, "insertPatient(con, p) failed : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
		}
	}
}
