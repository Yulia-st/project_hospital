package com.my.dao;

import static java.util.stream.Collectors.counting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.stream.Collectors;
import java.util.*;
import static java.util.stream.Collectors.counting;

import com.my.control.SortQuant;
import com.my.db.DbManager;
import com.my.entity.Doctor;
import com.my.entity.Patient;
import com.my.entity.PatientDoctor;
import com.my.exception.DBException;

public class PatientDoctorDao<R> {
	private static final Logger log = Logger.getLogger(PatientDoctorDao.class);

//	private static final String FIND_ALL_PAT_DOC = "SELECT doctor.lastnam, pd.* FROM doctor, patient_doctor as pd WHERE doctor.d_id=patient_doctor.d_id";
	private static final String FIND_ALL_PAT_DOC = "SELECT * FROM patient_doctor ";
	private static final String SQL_INSERT_PAT_DOC = "INSERT INTO patient_doctor VALUES (?,?)";
	private static final String SQL_FIND_ALL_SET = "SELECT * FROM patient_doctor";

	public void insertPatDoc(Connection con, PatientDoctor pd) throws DBException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement(SQL_INSERT_PAT_DOC);
			ps.setInt(1, pd.getpId());
			ps.setInt(2, pd.getdId());
			ps.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "Cannot set a doctor for the patient: ", ex);
			DbManager.rollback(con);
			throw new DBException("Cannot set a doctor for the patient", ex);
		} finally {
			DbManager.close(rs);
			DbManager.close(ps);
		}
	}

	public List<PatientDoctor> findAllSet(Connection con) {
		List<PatientDoctor> patDocs = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement(SQL_FIND_ALL_SET);
			rs = ps.executeQuery();
			while (rs.next()) {
				PatientDoctor pd = new PatientDoctor();
				pd.setpId(rs.getInt(1));
				pd.setdId(rs.getInt(2));
				patDocs.add(pd);
			}
		} catch (Exception e) {
			log.log(Level.DEBUG, "findAllSet(Connection con) failed: ", e);
			return Collections.emptyList();
		} finally {
			DbManager.close(rs);
			DbManager.close(ps);
		}

		return patDocs;
	}

	public Map<Object, Long> sortQuantityOfPat(Connection con) {
		List<PatientDoctor> patDoctors = new ArrayList<>();
		Statement stat = null;
		ResultSet rs = null;
		try {
			stat = con.createStatement();
			rs = stat.executeQuery(FIND_ALL_PAT_DOC);
			while (rs.next()) {
				PatientDoctor pd = new PatientDoctor();
				patDoctors.add(pd);
				pd.setdId(rs.getInt(1));
				pd.setpId(rs.getInt(2));
			}

		} catch (Exception e) {
			log.log(Level.DEBUG, "sortQuantityOfPat() failed : ", e);
			return Collections.emptyMap();
		} finally {
			DbManager.close(rs);
			DbManager.close(stat);
		}
		Map<Object, Long> patFreq = patDoctors.stream().collect(Collectors.groupingBy((d -> d.getpId()), counting()));

		System.out.println("Карта doctors, где ключ-id's doctor, значение - список patients: ");
		for (Entry<Object, Long> el : patFreq.entrySet()) {
			System.out.println(el.getKey() + " - " + el.getValue());
		}
		return patFreq;

	}
}
