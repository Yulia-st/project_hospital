//package com.my.entity;
//
//import java.io.Serializable;
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DoctorNew implements Serializable{
//	private int dId;
//	private String firstname;
//	private String lastname;
//	private String username;
//	private String category;
//	private String password;
//	//private int rId;
//	   private List<String> roles;
//	//private int hc_id;
//	
//	public DoctorNew() {
//		
//	}
//	
//	
//	public DoctorNew(String firstname, String lastname, String username, String category, String password,
//			String... roles) {
//		
//		this.firstname = firstname;
//		this.lastname = lastname;
//		this.username = username;
//		this.category = category;
//		this.password = password;
//		
//		this.roles = new ArrayList<String>();
//	      if (roles != null) {
//	         for (String r : roles) {
//	            this.roles.add(r);
//	         }
//	      }
//	}
//	
//	public DoctorNew(int dId, String firstname, String lastname, String username, String category, String password,
//			String... roles) {
//		
//		this.dId = dId;
//		this.firstname = firstname;
//		this.lastname = lastname;
//		this.username = username;
//		this.category = category;
//		this.password = password;
//		this.roles = new ArrayList<String>();
//	      if (roles != null) {
//	         for (String r : roles) {
//	            this.roles.add(r);
//	         }
//	      }
//	}
//
//
//	public int getdId() {
//		return dId;
//	}
//
//
//	public void setdId(int dId) {
//		this.dId = dId;
//	}
//
//
//	public String getFirstname() {
//		return firstname;
//	}
//
//
//	public void setFirstname(String firstname) {
//		this.firstname = firstname;
//	}
//
//
//	public String getLastname() {
//		return lastname;
//	}
//
//
//	public void setLastname(String lastname) {
//		this.lastname = lastname;
//	}
//
//
//	public String getUsername() {
//		return username;
//	}
//
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//
//	public String getCategory() {
//		return category;
//	}
//
//
//	public void setCategory(String category) {
//		this.category = category;
//	}
//
//
//	public String getPassword() {
//		return password;
//	}
//
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//
////	public int getrId() {
////		return rId;
////	}
////
////
////	public void setrId(int rId) {
////		this.rId = rId;
////	}
//
//	public List<String> getRoles() {
//	      return roles;
//	   }
//
//	   public void setRoles(List<String> roles) {
//	      this.roles = roles;
//	   }
//
//
//	@Override
//	public String toString() {
//		return "Doctor [dId=" + dId + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
//				+ ", category=" + category + ", roles=" + roles + "]";
//	}
//	   
////	@Override
////	public String toString() {
////		return "Doctor [dId=" + dId + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
////				+ ", category=" + category + "]";
////	}
//	
//	
//	
//	
//}
