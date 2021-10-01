package com.my.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.HospitalCardDao;
import com.my.dao.PatientDoctorDao;
import com.my.db.DbManager;
import com.my.entity.HospitalCard;
import com.my.entity.PatientDoctor;

@WebServlet("/listSetUpDoc")
public class ListSettingDoc extends HttpServlet {
	private static Logger log = Logger.getLogger(InsertNewPatient.class.getName());
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PatientDoctorDao dao = new PatientDoctorDao();
			Connection con = null;
			try {
				con = DbManager.getInstance().getConnection();	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
			List<PatientDoctor> listPatDoc = dao.findAllSet(con);
			
			request.setAttribute("patsDocs", listPatDoc);
			
			RequestDispatcher rd = request.getRequestDispatcher("listPatDoc.jsp");
			rd.forward(request, response);
			}catch (Exception ex) {
				log.log(Level.SEVERE, "findAll(con) failed : ", ex);
				request.setAttribute("errorMessage", ex.getMessage());
				request.getRequestDispatcher("/views/error.jsp").forward(request, response);
			}
//			HttpSession session=request.getSession();
//			session.setAttribute("patient", p1);
//			response.sendRedirect("showPatient.jsp");
		}
}
