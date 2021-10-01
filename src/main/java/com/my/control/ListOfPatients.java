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

import com.my.dao.PatientDao;
import com.my.db.DbManager;
import com.my.entity.Patient;

@WebServlet("/list")
public class ListOfPatients extends HttpServlet {
	private static Logger log = Logger.getLogger(InsertNewPatient.class.getName());

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Connection con = null;
	try {
		con = DbManager.getInstance().getConnection();	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	//	response.setContentType("text/html");
//    request.setCharacterEncoding("utf-8");
//    response.setCharacterEncoding("utf-8");
	
   // HttpSession session=request.getSession();
	//session.setAttribute("patient", p1);
    
//    if (null != request.getSession().getAttribute("patient")) {
//        request.getSession().removeAttribute("patient");
//    }
//    doPost(request, response);
    //Patient p = (Patient)request.getSession().getAttribute("patient");
	
	
	int page = 1;
    int recordsPerPage = 2;
    if(request.getParameter("page") != null)
        page = Integer.parseInt(request.getParameter("page"));
   
    PatientDao dao = new PatientDao();
    //List<Patient> listPt = dao.viewAllEmployees((page-1)*recordsPerPage,
    //                         recordsPerPage);
//    List<Patient> listPt = dao.findAllPatients((page-1)*recordsPerPage,
//            recordsPerPage);
    
    int noOfRecords;
    int noOfPages;
	try {
		noOfRecords = dao.getNoOfRecords(con);
	
    noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
	
   	
		//int id = Integer.parseInt(request.getParameter("id"));
		//PatientDao dao = new PatientDao();
		//List<Patient> list = dao.findAllPatients();
    int offset = (page - 1) * recordsPerPage;
    int offset2 = (recordsPerPage*page)-recordsPerPage;
		 List<Patient> listPt = dao.findAllPatients(con, recordsPerPage, offset2);//(offset, recordsPerPage);
		
		request.setAttribute("patients", listPt);
		 request.setAttribute("noOfPages", noOfPages);
		    request.setAttribute("currentPage", page);
		
		RequestDispatcher rd = request.getRequestDispatcher("listOfPatients.jsp");
		rd.forward(request, response);
	} catch (SQLException ex) {
		log.log(Level.SEVERE, "findAllPatients(con, recordsPerPage, offset2) failed : ", ex);

		request.setAttribute("errorMessage", ex.getMessage());

		request.getRequestDispatcher("/views/error.jsp").forward(request, response);
	}
//		HttpSession session=request.getSession();
//		session.setAttribute("patient", p1);
//		response.sendRedirect("showPatient.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
		
	}

}
