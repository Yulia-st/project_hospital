package com.my.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.my.control.DischargeP;

import com.my.db.DbManager;
import com.my.entity.Doctor;
import com.my.entity.HospitalCard;
import com.my.entity.Patient;
import com.my.exception.DBException;
import com.mysql.cj.Session;

public class HospitalCardDao {
	private static final Logger log = Logger.getLogger(HospitalCardDao.class);
	// private static Logger logger =
	// Logger.getLogger(HospitalCardDao.class.getName());

	private static final String SQL_FIND_ALL_CARD = "SELECT * FROM hospital_card";
	private static final String SQL_INSERT_CARD = "INSERT INTO hospital_card VALUES (DEFAULT,?,?,?)";
	private static final String SQL_DELETE_CARD = "DELETE FROM hospital_card WHERE hc_id = ?";
	private static final String SQL_UPDATE_CARD = "UPDATE hospital_card SET diagnosis = ?, med_prescription = ?, d_id = ? WHERE hc_id = ? ";
	private static final String SQL_DISCHARGE_PATIENT = "SELECT patient.firstname, patient.lastname, patient.birthday, hospital_card.diagnosis, hospital_card.med_prescription  FROM patient, hospital_card WHERE hospital_card.hc_id=? AND patient.firstname= ? AND patient.lastname=? AND patient.birthday=? INTO OUTFILE \"d:/EPAM/discharge.txt\" ";

	public List<HospitalCard> findAll(Connection con) {
		List<HospitalCard> listHc = new ArrayList<>();
		Statement stat = null;
		ResultSet rs = null;
		try {
			stat = con.createStatement();
			rs = stat.executeQuery(SQL_FIND_ALL_CARD);
			while (rs.next()) {
				HospitalCard hc = new HospitalCard();
				listHc.add(hc);
				hc.setHcId(rs.getInt(1));
				hc.setDiagnosis(rs.getString(2));
				hc.setMedPrescription(rs.getString(3));
				hc.setdId(rs.getInt(4));

			}
		} catch (Exception ex) {
			log.log(Level.DEBUG, "find all doctors exception : ", ex);
			// logger.error("findAll(Connection con) failed", e);
			// ((org.apache.logging.log4j.Logger) logger).error("find all doctors
			// exception:", e);
			return Collections.emptyList();
		} finally {
			DbManager.close(rs);
			DbManager.close(stat);
		}
		return listHc;
	}

	public void insertHospCard(Connection con, HospitalCard hc) throws SQLException, DBException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(SQL_INSERT_CARD);
			ps.setString(1, hc.getDiagnosis());
			ps.setString(2, hc.getMedPrescription());
			ps.setInt(3, hc.getdId());

			ps.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "insertHospCard(Connection con, HospitalCard hc) failed: ", ex);
			DbManager.rollback(con);
			throw new DBException("Cannot insert Hospital card for a patient", ex);
		} finally {
			DbManager.close(rs);
			DbManager.close(ps);
		}
	}

	public boolean deleteCard(Connection con, int hcid) throws DBException {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SQL_DELETE_CARD);
			ps.setInt(1, hcid);
			ps.executeUpdate();
			con.commit();
			System.out.println("Card was deleted with id: " + hcid);

		} catch (SQLException ex) {
			log.log(Level.DEBUG, "deleteCard(int hcid) failed : ", ex);
			DbManager.rollback(con);
			throw new DBException("Cannot delete hospital card of the patient", ex);
		} finally {
			DbManager.close(ps);
		}
		return true;
	}

	public boolean updateCard(Connection con, HospitalCard hc) throws DBException {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SQL_UPDATE_CARD);
			ps.setString(1, hc.getDiagnosis());
			ps.setString(2, hc.getMedPrescription());
			ps.setInt(3, hc.getdId());
			ps.setInt(4, hc.getHcId());

			if (ps.executeUpdate() != 1) {
				return false;
			}
			con.commit();
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "updateCard(HospitalCard hc) failed : ", ex);
			DbManager.rollback(con);
			throw new DBException("Can't update a card", ex);
		} finally {
			DbManager.close(ps);
		}
		return true;
	}

	public HospitalCard getHospitalCard(Connection conn, int hcid) throws DBException {
		HospitalCard hc = new HospitalCard();
		Statement stat = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM hospital_card WHERE hc_id=" + hcid;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				hc.setHcId(rs.getInt("hc_Id"));
				hc.setDiagnosis(rs.getString("diagnosis"));
				hc.setMedPrescription(rs.getString("med_prescription"));
				hc.setdId(rs.getInt("d_Id"));
				// hc.setHc_id(rs.getInt("hc_id"));

			} else {
				throw new NoSuchElementException("No card with id " + hcid);
			}
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "Cannot get a hospital card: ", ex);
			throw new DBException("Cannot get a hospital card", ex);
		} finally {
			DbManager.close(rs);
			DbManager.close(stat);
		}
		return hc;
	}

	/**
	 * Method provides to get a discharge of patients: 
	 * 1) check if id's patient exist 
	 * 2) check if file of discharge exist on disk, remove it
	 * 3) delete set up doctor to patient
	 * 
	 */
	public boolean getDischarge(Connection con, int hcid, Patient patient) throws DBException, FileNotFoundException {

		String dischargeP = "d:/EPAM/discharge.txt";

		PreparedStatement ps = null;

		File f = new File(dischargeP);

		if (f.exists() && !f.isDirectory()) {
			log.log(Level.INFO, "File discharge.txt exists ");
		} else {
			log.log(Level.INFO, "File discharge.txt doesnt exist");
		}

		File newFile = new File("d:/OldDischargeTxt/dischargeOld.txt");
		if (f.renameTo(newFile)) {
			log.log(Level.INFO, "File discharge.txt removed ");
		} else {
			log.log(Level.INFO, "File discharge.txt didnt remove ");
		}

		if (f.delete()) {
			System.out.println(f.getName() + " deleted");
		} else {
			System.out.println(f.getName() + " not deleted");
		}

		try {
			ps = con.prepareStatement(SQL_DISCHARGE_PATIENT);
			ps.setInt(1, hcid);
			ps.setString(2, patient.getFirstname());
			ps.setString(3, patient.getLastname());
			ps.setDate(4, patient.getBirthday());
			ps.executeQuery();
			log.log(Level.INFO, "Patient was discharged with id of hospital card : " + hcid);
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "getDischarge(int hcid) failed : ", ex);
			throw new DBException("Cannot get the patient", ex);
		} finally {
			DbManager.close(ps);
		}

		String str = "";
		Scanner sc = new Scanner(f);
		StringBuilder stb = new StringBuilder();
		str = sc.nextLine();

		String[] words = str.split("\t");
		for (int i = 0; i < words.length; i++) {
			System.out.println(words[i]);
		}
		stb.append("The discharge of the patients\n").append("Firstname:\t\t" + words[0]).append("\n")
				.append("Lastname:\t\t" + words[1]).append("\n").append("Birthday:\t\t\t" + words[2]).append("\n")
				.append("Diagnosis:\t\t" + words[3]).append("\n").append("Med_prescription:\t" + words[4]);

		str = stb.toString();

		try {
			FileWriter fw = new FileWriter(f);
			fw.append(str);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
