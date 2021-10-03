package com.my.control;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.my.dao.DoctorDao;
import com.my.dao.PatientDoctorDao;
import com.my.db.DbManager;
import com.my.entity.Doctor;
/**
 * Servlet displays a page after sorting the doctors by quantity of their patients.
 *
 */
@WebServlet("/sortQuantity")
public class SortQuant extends HttpServlet {
	private static final Logger log = Logger.getLogger(SortQuant.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DoctorDao dao = new DoctorDao();
		PatientDoctorDao dao1 = new PatientDoctorDao();
		Connection con = null;

		try {
			con = DbManager.getInstance().getConnection();
			log.info("Connection well done ");
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "getConnection() failed : ", ex);
		}
		try {
			List<Doctor> listDoc = dao.findAllDocs(con);
			Map<Object, Long> mapDoc = dao1.sortQuantityOfPat(con);

			request.setAttribute("mapDoctors", mapDoc);
			request.setAttribute("listdoctors", listDoc);
			log.log(Level.INFO, "sortQuantityOfPat(con) has done ");
			RequestDispatcher rd = request.getRequestDispatcher("sortQuantityP.jsp");
			rd.forward(request, response);
		} catch (Exception ex) {
			log.log(Level.DEBUG, "sortDocByNm(con) failed : ", ex);
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("views/error.jsp").forward(request, response);
		}
//		HttpSession session=request.getSession();
//		session.setAttribute("doctors", list);
//		response.sendRedirect("listDoc.jsp");
	}
}
