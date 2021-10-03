package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.my.control.SortQuant;
import com.my.db.DbManager;
import com.my.exception.DBException;
import com.my.utils.PasswordHash;

public class LoginDao {
	private static final Logger log = Logger.getLogger(LoginDao.class);
	
	private static final String sql1 = "SELECT username, password FROM doctor WHERE username=? AND password=?";
	private static final String SQL_SELECT_PWD_LOGIN_ADMIN = "SELECT username, password FROM admin WHERE username=? AND password=?";

	public boolean check(Connection con, String uname, String pass) throws DBException {
		PreparedStatement prSt = null;
		ResultSet rs = null;
		
		String hashPWD=PasswordHash.md5Apache(pass);
		
		try {
			prSt = con.prepareStatement(SQL_SELECT_PWD_LOGIN_ADMIN);
			prSt.setString(1, uname);
			prSt.setString(2, hashPWD);
			rs = prSt.executeQuery();
			if (rs.next()) {
				return true;
			}
			con.commit();
		} catch (SQLException ex) {
			log.log(Level.DEBUG, "Cannot insert admin: ", ex);
			DbManager.rollback(con);
			throw new DBException("Cannot insert admin", ex);
		} finally {
			DbManager.close(rs);
			DbManager.close(prSt);
		}
		return false;
	}
}
