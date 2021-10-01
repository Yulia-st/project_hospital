package com.my.entity;


import java.io.Serializable;
import java.sql.Date;

public class Doctor implements Serializable{
	private int dId;
	private String firstname;
	private String lastname;
	private String username;
	private String category;
	private String password;
	private int rId;
	//private int hc_id;
	
	public Doctor() {
		
	}
	
	
	public Doctor(String firstname, String lastname, String username, String category, String password,
			int rId) {
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.category = category;
		this.password = password;
		this.rId = rId;
	}
	
	public Doctor(int dId, String firstname, String lastname, String username, String category, String password,
			int rId) {
		
		this.dId = dId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.category = category;
		this.password = password;
		this.rId = rId;
	}


	public int getdId() {
		return dId;
	}


	public void setdId(int dId) {
		this.dId = dId;
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


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
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


	@Override
	public String toString() {
		return "Doctor [dId=" + dId + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", category=" + category + ", rId=" + rId + "]";
	}
	
	
	
	
}
