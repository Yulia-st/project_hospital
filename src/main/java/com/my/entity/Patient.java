package com.my.entity;

import java.io.Serializable;
//import java.util.Date;
import java.sql.Date;
import java.time.format.*;


public class Patient implements Serializable{
	private int pId;
	private String firstname;
	private String lastname;
	private String username;
	private Date birthday;
	private String password;
	private int rId;
	private int hcId;
	
	public Patient() {
	}
	
	public Patient(String firstname, String lastname, String username, Date birthday, String password,
			int rId, int hcId) {
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.birthday = birthday;
		this.password = password;
		this.rId = rId;
		this.hcId = hcId;
	}
	
	public Patient(int pId, String firstname, String lastname, String username, Date birthday, String password,
			int rId, int hcId) {
		
		this.pId = pId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.birthday = birthday;
		this.password = password;
		this.rId = rId;
		this.hcId = hcId;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public int getHcId() {
		return hcId;
	}

	public void setHcId(int hcId) {
		this.hcId = hcId;
	}

	@Override
	public String toString() {
		return "Patient [pId=" + pId + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", birthday=" + birthday + ", role's number=" + rId + ", number' card=" + hcId + "]";
	}
	
	
	
	
	
}
