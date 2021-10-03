package com.my.dao;

import java.sql.Connection;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;


import com.my.control.ListOfPatients;

import com.my.db.DbManager;
import com.my.entity.Doctor;
import com.my.exception.DBException;

public class DoctorDao {
	
	//private static Logger logger = Logger.getLogger(DoctorDao.class.getName());
	private static final Logger log = Logger.getLogger(DoctorDao.class);

	private static final String FIND_ALL_DOCTORS = "SELECT * FROM doctor";
	private static final String SQL_INSERT_DOCTOR = "INSERT INTO doctor VALUES (DEFAULT,?,?,?,?,?,?)";
	private static final String SQL_DELETE_DOCTOR = "DELETE FROM doctor WHERE d_id = ?";
	private static final String SQL_UPDATE_DOCTOR = "UPDATE doctor SET firstname=?, lastname = ?,"
			+ "username =?, category=?, password=?, r_id=? WHERE d_id=?";
	private static final String SQL_FIND_ALL_DOCTORS_BY_LASTNAME = "SELECT * FROM doctor ORDER BY lastname";
	private static final String SQL_FIND_ALL_DOCTORS_BY_CATEGORY = "SELECT * FROM doctor ORDER BY category";
	private static final String SQL_DOCTORS_NUMBER_OF_RECORDS = "SELECT COUNT(*) AS number_doctor FROM  doctor";
	private static final String SQL_FIND_ALL_DOCTORS_BY_LIMIT = "SELECT * FROM doctor ORDER BY d_id LIMIT ? OFFSET ?";
	private static final String SQL_FIND_ALL_DOCTORS_BY_QUANTITY_OF_PATIENTS = "SELECT doctor.lastname, COUNT(patient.p_id) as quantity FROM doctor, patient, patient_doctor WHERE doctor.d_id=patient_doctor.d_id AND patient.p_id=patient_doctor.p_id GROUP BY(doctor.d_id)";

	
	
//	private static final Map<String, Doctor> mapUsers = new HashMap<String, Doctor>();
//
//	   static {
//	      initUsers();
//	   }
//
//	   private static void initUsers() {
//
//	      // This user has a role as EMPLOYEE.
//		   Doctor emp = new Doctor("employee1", "123", Doctor.GENDER_MALE, //
//	            SecurityConfig.ROLE_EMPLOYEE);
//
//	      // This user has 2 roles EMPLOYEE and MANAGER.
//	      UserAccount mng = new UserAccount("manager1", "123", UserAccount.GENDER_MALE, //
//	            SecurityConfig.ROLE_EMPLOYEE, SecurityConfig.ROLE_MANAGER);
//
//	      mapUsers.put(emp.getUserName(), emp);
//	      mapUsers.put(mng.getUserName(), mng);
//	   }
	
	
	
	public List<Doctor> findAllDocs(Connection con) {
		String FIND_ALL_USERS = "SELECT * FROM doctor";
		List<Doctor> doctors = new ArrayList<>();
		Statement stat = null;
		ResultSet rs = null;
		try {
			stat = con.createStatement();
			rs = stat.executeQuery(FIND_ALL_USERS);
			while (rs.next()) {
				Doctor d = new Doctor();
				doctors.add(d);
				d.setdId(rs.getInt(1));
				d.setFirstname(rs.getString(2));
				d.setLastname(rs.getString(3));
				d.setUsername(rs.getString(4));
				d.setCategory(rs.getString(5));
				d.setPassword(rs.getString(6));
				d.setrId(rs.getInt(7));
			}
		} catch (Exception e){
			log.log(Level.DEBUG, "find all doctors exception : ", e);

			return Collections.emptyList();
		} finally {
			DbManager.close(rs);
			DbManager.close(stat);
		}
		return doctors;
	}

	public int getNoOfRecords(Connection con) throws SQLException {
		int res = 0;

		try (PreparedStatement pstmt = con.prepareStatement(SQL_DOCTORS_NUMBER_OF_RECORDS,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

			try (ResultSet resultSet = pstmt.executeQuery()) {
				if (resultSet.first()) {
					res = resultSet.getInt("number_doctor");
				}
			}
		}
		return res;
	}

	public List<Doctor> findAllDoctors(Connection con, int limit, int offset) {
		List<Doctor> doctors = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement(SQL_FIND_ALL_DOCTORS_BY_LIMIT);
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			rs = ps.executeQuery();
			while (rs.next()) {
				Doctor doc = new Doctor();
				doc.setdId(rs.getInt(1));
				doc.setFirstname(rs.getString(2));
				doc.setLastname(rs.getString(3));
				doc.setUsername(rs.getString(4));
				doc.setCategory(rs.getString(5));
				doc.setPassword(rs.getString(6));
				doc.setrId(rs.getInt(7));
				doctors.add(doc);
			}
		} catch (Exception e) {
			log.log(Level.DEBUG, "findAllDoctors(Connection con, int limit, int offset): ", e);
			return Collections.emptyList();
		} finally {
			DbManager.close(rs);
			DbManager.close(ps);
		}

		return doctors;
	}

	public void insertDoc(Connection con, Doctor doctor) throws DBException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement(SQL_INSERT_DOCTOR);
			ps.setString(1, doctor.getFirstname());
			ps.setString(2, doctor.getLastname());
			ps.setString(3, doctor.getUsername());
			ps.setString(4, doctor.getCategory());
			ps.setString(5, doctor.getPassword());
			ps.setInt(6, doctor.getrId());
			ps.executeUpdate();
			con.commit();

		} catch (SQLException ex) {
			log.log(Level.DEBUG, "Cannot insert a doctor: ", ex);
			DbManager.rollback(con);
			throw new DBException("Cannot insert a doctor", ex);
		} finally {
			DbManager.close(rs);
			DbManager.close(ps);
		}

	}

	public boolean deleteDoctor(Connection con, int did) throws DBException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(SQL_DELETE_DOCTOR);

			ps.setInt(1, did);
			ps.executeUpdate();

			System.out.println("Doctor was deleted with id: " + did);
			con.commit();
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "deleteDoctor(int did) failed : ", ex);
			DbManager.rollback(con);
			throw new DBException("Cannot delete doctor", ex);

		} finally {
			DbManager.close(rs);
			DbManager.close(ps);
		}
		return true;
	}

	public boolean updateDoctor(Connection con, Doctor doctor) throws DBException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(SQL_UPDATE_DOCTOR);

			ps.setString(1, doctor.getFirstname());
			ps.setString(2, doctor.getLastname());
			ps.setString(3, doctor.getUsername());
			ps.setString(4, doctor.getCategory());
			ps.setString(5, doctor.getPassword());
			ps.setInt(6, doctor.getrId());
			ps.setInt(7, doctor.getdId());

			if (ps.executeUpdate() != 1) {
				return false;
			}
			con.commit();
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "updateDoctor(Connection con, Doctor doctor) failed : ", ex);
			DbManager.rollback(con);
			throw new DBException("Cannot update a doctor", ex);
		} finally {
			DbManager.close(rs);
			DbManager.close(ps);
		}
		return true;
	}

	public Doctor getDoctor(Connection conn, int did) throws DBException {
		Doctor d = new Doctor();
		Statement stat = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM doctor WHERE d_id=" + did;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				d.setdId(rs.getInt("d_id"));
				d.setFirstname(rs.getString("firstname"));
				d.setLastname(rs.getString("lastname"));
				d.setUsername(rs.getString("username"));
				d.setCategory(rs.getString("category"));
				d.setrId(rs.getInt("r_id"));
			} else {
				throw new NoSuchElementException("No doctor with id " + did);
			}

		} catch (SQLException ex) {
			log.log(Level.DEBUG, "getDoctor(Connection conn, int did) failed : ", ex);
			throw new DBException("Cannot get a doctor", ex);
		} finally {
			DbManager.close(rs);
			DbManager.close(stat);
		}
		return d;
	}

	public List<Doctor> sortDocByNm(Connection con) {
		List<Doctor> doctors = new ArrayList<>();
		Statement stat = null;
		ResultSet rs = null;

		try {
			stat = con.createStatement();
			rs = stat.executeQuery(SQL_FIND_ALL_DOCTORS_BY_LASTNAME);
			while (rs.next()) {
				Doctor d = new Doctor();
				doctors.add(d);
				d.setdId(rs.getInt(1));
				d.setFirstname(rs.getString(2));
				d.setLastname(rs.getString(3));
				d.setUsername(rs.getString(4));
				d.setCategory(rs.getString(5));
				d.setPassword(rs.getString(6));
				d.setrId(rs.getInt(7));
			}
		} catch (Exception e) {
			log.log(Level.DEBUG, "sortDocByNm() failed : ", e);
			return Collections.emptyList();
		} finally {
			DbManager.close(rs);
			DbManager.close(stat);
		}
		return doctors;
	}

	public List<Doctor> sortCategoryDoc(Connection con) {
		List<Doctor> doctors = new ArrayList<>();
		Statement stat = null;
		ResultSet rs = null;
		try {
			stat = con.createStatement();
			rs = stat.executeQuery(SQL_FIND_ALL_DOCTORS_BY_CATEGORY);
			while (rs.next()) {
				Doctor d = new Doctor();
				doctors.add(d);
				d.setdId(rs.getInt(1));
				d.setFirstname(rs.getString(2));
				d.setLastname(rs.getString(3));
				d.setUsername(rs.getString(4));
				d.setCategory(rs.getString(5));
				d.setPassword(rs.getString(6));
				d.setrId(rs.getInt(7));
			}
		} catch (Exception e) {
			log.log(Level.DEBUG, "sortCathegoryDoc() failed : ", e);
			return Collections.emptyList();
		} finally {
			DbManager.close(rs);
			DbManager.close(stat);
		}
		return doctors;
	}

}
