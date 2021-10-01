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
import javax.servlet.http.HttpSession;

import com.my.dao.LoginDao;
import com.my.db.DbManager;
import com.my.exception.DBException;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static Logger log = Logger.getLogger(Login.class.getName());

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		String uname = request.getParameter("username");
		String pass = request.getParameter("pass");
		LoginDao dao = new LoginDao();
		try {
			con = DbManager.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (dao.check(con, uname, pass)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", uname);
				response.sendRedirect("welcome.jsp");
			} else {
				response.sendRedirect("login.jsp");
				HttpSession session2 = request.getSession();
				session2.setAttribute("error", "Invalid Username or Password");
			}
		} catch (DBException ex) {
			log.log(Level.SEVERE, "Cannot check password and login admin : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("/WEB-INF/view/error/error.jsp").forward(request, response);
		}
	}
}
