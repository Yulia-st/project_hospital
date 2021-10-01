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
import javax.servlet.http.HttpSession;

import com.my.dao.AdminDao;
import com.my.dao.PatientDao;
import com.my.db.DbManager;
import com.my.entity.Admin;
import com.my.entity.Patient;
import com.my.exception.DBException;
import com.my.exception.UsernameExistsException;

@WebServlet("/signup")
public class Signup extends HttpServlet {
	private static Logger log = Logger.getLogger(Signup.class.getName());
	// private static final Logger LOG = Logger.getLogger(Signup.class);

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
		//request.setAttribute("error", "Username already Exists");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html; charset=UTF-8");
		Connection con = null;
		try {
			con = DbManager.getInstance().getConnection();
			System.out.println(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		AdminDao dao = new AdminDao();

		try {
			String fname = request.getParameter("firstname");
			String lname = request.getParameter("lastname");
			String uname = request.getParameter("username");
			String pwd = request.getParameter("password");
			 //int rid = Integer.parseInt(request.getParameter("rId"));
			//int rid = Integer.parseInt(request.getParameter("rId"));
			// String rid = request.getParameter("rId");
			int nr = 2;

			String hashpass = dao.hash(pwd);
			System.out.println(hashpass);

			Admin newAdm = new Admin(fname, lname, uname, pwd, nr);
			Admin user = null; 
			try {
			 user = dao.getAdmin(con, uname);
			
			if (user.getUsername() == null) {
					user = new Admin();
					user.setFirstname(fname);
					user.setLastname(lname);
					user.setUsername(uname);
					// user.setPassword(passwordEncoder.encode(password));
					user.setPassword(pwd);
					user.setrId(nr);
					dao.insertAdmin(con, user);
					System.out.println(user);
				} 
			else {
					System.out.println("Username already Exists");
					
					request.setAttribute("errorExistUser", "Username already Exists, change it to sign up!");
					request.getRequestDispatcher("signup.jsp").forward(request, response);
					
					//throw new UsernameExistsException(uname);
				}
			
			}catch(UsernameExistsException e) {
				log.log(Level.SEVERE, "registeration new admin was failed : ", e);
				request.setAttribute("errorMessage", e.getMessage());;
				request.getRequestDispatcher("/views/error.jsp").forward(request, response);
			}
			
		}catch (DBException ex) {
				log.log(Level.SEVERE, "registeration new admin was failed : ", ex);
				request.setAttribute("errorMessage", ex.getMessage());;
				request.getRequestDispatcher("/views/error.jsp").forward(request, response);
				}
		response.sendRedirect("login.jsp");// index.jsp
		}
	}

