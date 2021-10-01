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

import com.my.dao.HospitalCardDao;
import com.my.db.DbManager;
import com.my.exception.DBException;

@WebServlet("/deleteC")
public class DeleteCard extends HttpServlet {
	private static Logger log = Logger.getLogger(InsertNewPatient.class.getName());

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HospitalCardDao dao = new HospitalCardDao();
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			int hcId = Integer.parseInt(request.getParameter("hcId"));
			dao.deleteCard(con, hcId);
			response.sendRedirect("listCard.jsp");
		} catch (DBException ex) {
			log.log(Level.SEVERE, "deleteCard(con, hcId) failed : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
		}
	}
}
