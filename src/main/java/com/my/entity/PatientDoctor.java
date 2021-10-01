package com.my.entity;

import java.io.Serializable;


public class PatientDoctor implements Serializable{
	private int pId;
	private int dId;
	
public PatientDoctor() {
	}
	
	public PatientDoctor(int pId, int dId) {
		
		this.pId = pId;
		this.dId = dId;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public int getdId() {
		return dId;
	}
	public void setdId(int dId) {
		this.dId = dId;
	}
	@Override
	public String toString() {
		return "PatientDoctor [number of patient=" + pId + ", number of doctor=" + dId + "]";
	}
	
	
}
