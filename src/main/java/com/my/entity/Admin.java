package com.my.entity;

import java.io.Serializable;

public class Admin implements Serializable{
	private int aId;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private int rId;
	
	public Admin() {
		
	}
	
	public Admin(String firstname, String lastname, String username, String password, int rId) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.rId = rId;
	}
	
	public Admin(int aId, String firstname, String lastname, String username, String password, int rId) {
		this.aId = aId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.rId = rId;
	}
	public int getaId() {
		return aId;
	}
	public void setaId(int aId) {
		this.aId = aId;
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
		return "Admin [aId=" + aId + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", rId=" + rId + "]";
	}
	
	
}
