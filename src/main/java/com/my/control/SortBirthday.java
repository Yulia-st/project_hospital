package com.my.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.PatientDao;
import com.my.db.DbManager;
import com.my.entity.Patient;

@WebServlet("/sortBirthday")
public class SortBirthday extends HttpServlet {
	private static Logger log = Logger.getLogger(InsertNewPatient.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
			System.out.println(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PatientDao dao = new PatientDao();
		List<Patient> list = dao.sortBirthday(con);

		request.setAttribute("patients", list);

		RequestDispatcher rd = request.getRequestDispatcher("listOfPatients.jsp");
		rd.forward(request, response);

//		HttpSession session=request.getSession();
//		session.setAttribute("patient", p1);
//		response.sendRedirect("showPatient.jsp");
	}

}
