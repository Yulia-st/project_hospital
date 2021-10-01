package com.my.entity;

import java.io.Serializable;

public class HospitalCard implements Serializable{
	private int hcId;
	private String diagnosis;
	private String medPrescription;
	private int dId;
	
public HospitalCard() {
				
	}
	
	public HospitalCard(int hcId, String diagnosis, String medPrescription, int dId) {
		
		this.hcId = hcId;
		this.diagnosis = diagnosis;
		this.medPrescription = medPrescription;
		this.dId = dId;
	}
	
	
	public int getHcId() {
		return hcId;
	}

	public void setHcId(int hcId) {
		this.hcId = hcId;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getMedPrescription() {
		return medPrescription;
	}

	public void setMedPrescription(String medPrescription) {
		this.medPrescription = medPrescription;
	}

	public int getdId() {
		return dId;
	}

	public void setdId(int dId) {
		this.dId = dId;
	}

	@Override
	public String toString() {
		return "HostipalCard [hc_id=" + hcId + ", diagnosis=" + diagnosis + ", medic prescription=" + medPrescription
				+ "number of doctor="  + dId + "]";
	}
	
}
