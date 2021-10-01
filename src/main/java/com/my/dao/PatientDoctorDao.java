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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.*;
import static java.util.stream.Collectors.counting;

import com.my.db.DbManager;
import com.my.entity.Doctor;
import com.my.entity.Patient;
import com.my.entity.PatientDoctor;
import com.my.exception.DBException;

public class PatientDoctorDao<R> {
	private static Logger logger = Logger.getLogger(PatientDoctorDao.class.getName());

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
			logger.log(Level.SEVERE, "Cannot set a doctor for the patient: ", ex);
			DbManager.rollback(con);
			throw new DBException("Cannot set a doctor for the patient", ex);
		}finally {
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
			logger.log(Level.SEVERE, "findAllSet(Connection con) failed: ", e);
			return Collections.emptyList();
		}finally {
			DbManager.close(rs);
			DbManager.close(ps);
		}
		
		return patDocs;
	}
	
	public Map<Object, Long> sortQuantityOfPat(Connection con) {
		// Map<Doctor, Integer> = new ArrayList<>();
		List<PatientDoctor> patDoctors = new ArrayList<>();
		Statement stat = null;
		ResultSet rs = null;
		try {
			stat = con.createStatement();
			//rs = stat.executeQuery(SQL_FIND_ALL_DOCTORS_BY_QUANTITY_OF_PATIENTS);
			rs = stat.executeQuery(FIND_ALL_PAT_DOC);
			while (rs.next()) {
				PatientDoctor pd = new PatientDoctor();
				patDoctors.add(pd);
				//pd.setpId(rs.getInt(1));
				pd.setdId(rs.getInt(1));
				pd.setpId(rs.getInt(2));
				
//				pd.sesetFirstname(rs.getString(2));
//				pd.setLastname(rs.getString(3));
//				pd.setUsername(rs.getString(4));
//				pd.setCategory(rs.getString(5));
//				pd.setPassword(rs.getString(6));
//				pd.setrId(rs.getInt(7));
			}
//			Map<List, Long> wordsFreq = Arrays.stream(text)
//			        .collect(Collectors.groupingBy((s ->s), counting()));
//			    System.out.println("Получена карта, "
//			            + "\nключ - слово, значение - количество вхожденией в строку: ");
//			    
//			    for(Map.Entry<String, Long> d: wordsFreq.entrySet()){
//			                System.out.println(d.getKey() + " - " + d.getValue());

		} catch (Exception e) {
			logger.log(Level.SEVERE, "sortQuantityOfPat() failed : ", e);
			 //return Collections.emptyList();
			return Collections.emptyMap();
		} finally {
			DbManager.close(rs);
			DbManager.close(stat);
		}
		
       //List<PatientDoctor> givenList3 = new ArrayList<>(Arrays.asList(ph1, ph2, ph3, ph4, ph5, ph6));
        Map<Object, Long> patFreq = patDoctors.stream()
                .collect(Collectors.groupingBy((d->d.getpId()), counting() ));
                       // Collectors.toList() ) );d->d.getdId()
        System.out.println("Карта doctors, где ключ-id's doctor, значение - список patients: " );
        for(Entry<Object, Long> el: patFreq.entrySet()){
            System.out.println(el.getKey() + " - " + el.getValue());
        }   


		
//		Map<List<PatientDoctor>, Integer> patFreq =  patDoctors.stream()
//				.collect(Collectors.groupingBy((s -> s), counting()), Collectors.toMap());
//		patFreq.entrySet().stream().sorted()
//		.map(e->e.getValue().sorted()).flatMap(v->v).collect(Collectors.toList());
//        System.out.println("Карта телефонов, где ключ-диагональ, значение - список телефонов: " );
//        for(Map.Entry<List<PatientDoctor>, Integer> d: patFreq.entrySet()){
//            System.out.println(d.getKey() + " - " + d.getValue());
//        }   
//
//			List<PatientDoctor> givenList3 = new ArrayList<>((patDoctors));

//			Map<List<Doctor>, Integer> phonesByDiag = givenList3.stream()
//					.collect(Collectors.groupingBy((p -> p.getDiag()), Collectors.toList()));
//			System.out.println("Карта телефонов, где ключ-диагональ, значение - список телефонов: ");
//			for (Map.Entry<List<Doctor>, Integer> fr : phonesByDiag.entrySet()) {
//				System.out.println(fr.getKey() + " - " + fr.getValue());
//			}
			
//			Map<Integer, Integer> freqP = patDoctors.stream()
//					.collect(Collectors.groupingBy((s -> s), counting()))
//							.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
//							.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1, e2)-> e1, LinkedHashMap::new));
			// return doctors;

			return  patFreq;
//		int res = 0;
//
//		try (PreparedStatement pstmt = con.prepareStatement(SQL_DOCTORS_NUMBER_OF_RECORDS,
//				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
//
//			try (ResultSet resultSet = pstmt.executeQuery()) {
//				if (resultSet.first()) {
//					res = resultSet.getInt("number_doctor");
//				}
//			}
//		}
//		
	}
}
