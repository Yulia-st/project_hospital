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
import javax.servlet.http.HttpSession;

import com.my.dao.DoctorDao;
import com.my.db.DbManager;
import com.my.entity.Doctor;
import com.my.exception.DBException;

@WebServlet("/sortNameDoc")
public class SortNameDoc extends HttpServlet{
	private static Logger log = Logger.getLogger(InsertNewPatient.class.getName());
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	DoctorDao dao = new DoctorDao();
	Connection con = null;
	try {
		con = DbManager.getInstance().getConnection();
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
		try {
		List<Doctor> list = dao.sortDocByNm(con);
		request.setAttribute("doctors", list);
		RequestDispatcher rd = request.getRequestDispatcher("listDoc.jsp");
		rd.forward(request, response);
		}catch(Exception ex) {
			log.log(Level.SEVERE, "sortDocByNm(con) failed : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
		}
//		HttpSession session=request.getSession();
//		session.setAttribute("doctors", list);
//		response.sendRedirect("listDoc.jsp");
	}
}
