package com.my.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.my.dao.LoginDao;
import com.my.db.DbManager;
import com.my.exception.DBException;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final Logger log = Logger.getLogger(Login.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		String uname = request.getParameter("username");
		String pass = request.getParameter("pass");
		LoginDao dao = new LoginDao();
		try {
			con = DbManager.getInstance().getConnection();
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "getConnection() failed : ", ex);
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
			log.log(Level.DEBUG, "Cannot check password and login admin : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("view/error.jsp").forward(request, response);
		}
	}
}
