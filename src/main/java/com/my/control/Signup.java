package com.my.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.my.dao.AdminDao;
import com.my.dao.PatientDao;
import com.my.db.DbManager;
import com.my.entity.Admin;
import com.my.entity.Patient;
import com.my.exception.DBException;
import com.my.exception.UsernameExistsException;
import com.my.utils.PasswordHash;
/**
 * Servlet displays a login page after signed up an admin or error page if not.
 *
 */
@WebServlet("/signup")
public class Signup extends HttpServlet {
	private static final Logger log = Logger.getLogger(Signup.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// * Redirects from registration page to doc's or admin's page if user is logged
		// in
		Admin admin = (Admin) request.getSession().getAttribute("username");
		if (admin != null) {
			// if (administer.getAccessLevel().equals(AccessLevel.ADMIN)) {
			response.sendRedirect("welcome.jsp");
			// } else {
			// response.sendRedirect(doc_JSP);
			// }
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
			rd.forward(request, response);
		}

//		RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
//		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request.setAttribute("error", "Username already Exists");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
			System.out.println(con);
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "getConnection() failed : ", ex);
		}

		AdminDao dao = new AdminDao();

		try {
			String fname = request.getParameter("firstname");
			String lname = request.getParameter("lastname");
			String uname = request.getParameter("username");
			String pwd = request.getParameter("password");
			// int rid = Integer.parseInt(request.getParameter("rId"));
			int nr = 2;

			String hashingPwd=PasswordHash.md5Apache(pwd);
			System.out.println("PasswordHash.md5Apache(pwd)"+hashingPwd);
			
			

			Admin newAdm = new Admin(fname, lname, uname, hashingPwd, nr);
			Admin user = null;
			try {
				user = dao.getAdmin(con, uname);

				if (user.getUsername() == null) {
					user = new Admin();
					user.setFirstname(fname);
					user.setLastname(lname);
					user.setUsername(uname);
					// user.setPassword(passwordEncoder.encode(password));
					user.setPassword(hashingPwd);
					user.setrId(nr);
					dao.insertAdmin(con, user);
					System.out.println(user);
					log.log(Level.INFO, "insertAdmin(con, user) has done ");
					response.sendRedirect("login.jsp");// index.jsp
				} else {
					log.log(Level.INFO, "Username already Exists ");
					request.setAttribute("errorExistUser", "Username already Exists, change it to sign up!");
					request.getRequestDispatcher("signup.jsp").forward(request, response);

					// throw new UsernameExistsException(uname);
				}

			} catch (UsernameExistsException e) {
				log.log(Level.DEBUG, "registeration new admin was failed : ", e);
				request.setAttribute("errorMessage", e.getMessage());
				request.getRequestDispatcher("views/error.jsp").forward(request, response);
			}

		} catch (DBException ex) {
			log.log(Level.DEBUG, "registeration new admin was failed : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("views/error.jsp").forward(request, response);
		}
		
	}
}
