package com.my.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.my.db.DbManager;
import com.my.entity.HospitalCard;
import com.my.entity.Patient;

import com.my.exception.DBException;

public class PatientDao {

	private static Logger log = Logger.getLogger(PatientDao.class.getName());

	private static final String SQL_PATIENTS_NUMBER_OF_RECORDS = "SELECT COUNT(*) AS number_patient FROM  patient";
	private static final String SQL_INSERT_HCARD = "INSERT INTO hospital_card VALUES ()";
	private static final String SQL_INSERT_PATIENT = "INSERT INTO patient VALUES (DEFAULT,?,?,?,?,?,?,?)";
	private static final String SQL_FIND_ALL_PATIENTS_BY_LASTNAME = "SELECT * FROM patient ORDER BY lastname";
	private static final String SQL_FIND_ALL_PATIENTS_BY_BIRTHDAY = "SELECT * FROM patient ORDER BY birthday";
	private static final String SQL_DELETE_PATIENT_BY_ID = "DELETE FROM patient WHERE p_id = ?";
	private static final String SQL_FIND_ALL_PATIENTS_BY_LIMIT = "SELECT * FROM patient ORDER BY p_id LIMIT ? OFFSET ?";
	private static final String SQL_UPDATE_PATIENT = "UPDATE patient SET firstname=?, lastname = ?,"
			+ "username =?, birthday=?, password=?, r_id=?, hc_id=? WHERE p_id=?";
	
	public int getNoOfRecords(Connection con) throws SQLException {
		int res = 0;

		try (PreparedStatement pstmt = con.prepareStatement(SQL_PATIENTS_NUMBER_OF_RECORDS,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			// pstmt.setInt(1, patientId);
			try (ResultSet resultSet = pstmt.executeQuery()) {
				if (resultSet.first()) {
					res = resultSet.getInt("number_patient");
				}
			}
		}
		return res;
	}

	public Patient getPatient(Connection con, int pId) throws DBException {
		Patient p = new Patient();
		Statement stat = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM patient WHERE p_id=" + pId;
		
		try {
			stat = con.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				p.setpId(rs.getInt("p_id"));
				p.setFirstname(rs.getString("firstname"));
				p.setLastname(rs.getString("lastname"));
				p.setUsername(rs.getString("username"));
				p.setBirthday(rs.getDate("birthday"));
				p.setrId(rs.getInt("r_id"));
				p.setHcId(rs.getInt("hc_id"));

			} else {
				throw new NoSuchElementException("No patient with id " + pId);
			}

		} catch (SQLException ex) {
			log.log(Level.SEVERE, "Cannot get the patient: ", ex);
			throw new DBException("Cannot get the patient", ex);
		}finally {
			DbManager.close(rs);
			DbManager.close(stat);
		}
		return p;
	}

	public List<Patient> findAllPatients(Connection con, int limit, int offset) {
		List<Patient> patients = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement(SQL_FIND_ALL_PATIENTS_BY_LIMIT);
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			rs = ps.executeQuery();
			while (rs.next()) {
				Patient p = new Patient();
				p.setpId(rs.getInt(1));
				p.setFirstname(rs.getString(2));
				p.setLastname(rs.getString(3));
				p.setUsername(rs.getString(4));
				p.setBirthday(rs.getDate(5));
				p.setPassword(rs.getString(6));
				p.setrId(rs.getInt(7));
				p.setHcId(rs.getInt(8));
				patients.add(p);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "findAllPatients() failed: ", e);
			return Collections.emptyList();
		}finally {
			DbManager.close(rs);
			DbManager.close(ps);
		}
		
		return patients;
	}

//	public List<Patient> findAllPatients() {
//		List<Patient> patients = new ArrayList<>();
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection con = DriverManager.getConnection(url, username, password);
//
//			ResultSet rs = null;
//
//			Statement ps = con.createStatement();
//
//			String FIND_ALL_USERS = "SELECT * FROM patient";
//			rs = ps.executeQuery(FIND_ALL_USERS);
//			while (rs.next()) {
//				Patient p = new Patient();
//				// patients.add(p);
//				p.setpId(rs.getInt(1));
//				p.setFirstname(rs.getString(2));
//				p.setLastname(rs.getString(3));
//				p.setUsername(rs.getString(4));
//				p.setBirthday(rs.getDate(5));
//				p.setPassword(rs.getString(6));
//				p.setrId(rs.getInt(7));
//				p.setHcId(rs.getInt(8));
//				patients.add(p);
//			}
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "find all patients exception : ", e);
//
//			return Collections.emptyList();
//		}
//		return patients;
//	}

	public void insertPatient(Connection con, Patient patient) throws SQLException, DBException {
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement(SQL_INSERT_HCARD);

			ps2 = con.prepareStatement(SQL_INSERT_PATIENT);

			ps2.setString(1, patient.getFirstname());
			ps2.setString(2, patient.getLastname());
			ps2.setString(3, patient.getUsername());
			ps2.setDate(4, patient.getBirthday());
			ps2.setString(5, patient.getPassword());
			ps2.setInt(6, patient.getrId());
			ps2.setInt(7, patient.getHcId());
			

			con.setAutoCommit(false);
			boolean correct = true;
			try {

				ps.executeUpdate();
				ps2.executeUpdate();
			} catch (SQLException ex) {
				correct = false;
				log.log(Level.SEVERE, "Cannot insert hospital card or the patient: ", ex);
				throw new DBException("Cannot insert hospital card or the patient", ex);
			}
			if (correct) {
				con.commit();
			} else {
//				con.rollback();
				DbManager.rollback(con);
			}

		} finally {
			DbManager.close(rs);
			DbManager.close(ps2);
			DbManager.close(ps);
		}

	}

	public List<Patient> sortPtnByNm(Connection con) {
		List<Patient> patients = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_PATIENTS_BY_LASTNAME);
			while (rs.next()) {
				Patient p = new Patient();
				patients.add(p);
				p.setpId(rs.getInt(1));
				p.setFirstname(rs.getString(2));
				p.setLastname(rs.getString(3));
				p.setUsername(rs.getString(4));
				p.setBirthday(rs.getDate(5));
				p.setPassword(rs.getString(6));
				p.setrId(rs.getInt(7));
				p.setHcId(rs.getInt(8));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "find all patients exception : ", e);
			return Collections.emptyList();
		} finally {
			DbManager.close(rs);
			DbManager.close(stmt);
		}
		return patients;
	}

	public List<Patient> sortBirthday(Connection con) {
		List<Patient> patients = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(SQL_FIND_ALL_PATIENTS_BY_BIRTHDAY);
			while (rs.next()) {
				Patient p = new Patient();
				patients.add(p);
				p.setpId(rs.getInt(1));
				p.setFirstname(rs.getString(2));
				p.setLastname(rs.getString(3));
				p.setUsername(rs.getString(4));
				p.setBirthday(rs.getDate(5));
				p.setPassword(rs.getString(6));
				p.setrId(rs.getInt(7));
				p.setHcId(rs.getInt(8));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "find all patients exception : ", e);
			return Collections.emptyList();
		} finally {
			DbManager.close(rs);
			DbManager.close(stmt);
		}
		return patients;
	}

	public boolean deletePatient(Connection con, int id) throws DBException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(SQL_DELETE_PATIENT_BY_ID);
			ps.setInt(1, id);
			ps.executeUpdate();
			//System.out.println("Patient was deleted with id: " + id);
			con.commit();
		} catch (SQLException ex) {
			log.log(Level.SEVERE, "deletePatient(int id) failed : ", ex);
			DbManager.rollback(con);
			System.out.println("Can't delete patient:" + ex.getMessage());
			throw new DBException("Can't delete patient by id" + id, ex);
			// return false;
		} finally {
			DbManager.close(rs);
			DbManager.close(ps);
		}
		return true;
	}

	public boolean updatePatient(Connection con, Patient patient) throws DBException {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SQL_UPDATE_PATIENT);
			ps.setString(1, patient.getFirstname());
			ps.setString(2, patient.getLastname());
			ps.setString(3, patient.getUsername());
			ps.setDate(4, patient.getBirthday());
			ps.setString(5, patient.getPassword());
			ps.setInt(6, patient.getrId());
			ps.setInt(7, patient.getHcId());
			ps.setInt(8, patient.getpId());

			if (ps.executeUpdate() != 1) {
				return false;
			}
			con.commit();
		} catch (SQLException ex) {
			log.log(Level.SEVERE, "updatePatient(int id) failed : ", ex);
			DbManager.rollback(con);
			throw new DBException("Can't update patient", ex);
			//return false;
		} finally {
			DbManager.close(ps);
		}
		return true;
	}
}
