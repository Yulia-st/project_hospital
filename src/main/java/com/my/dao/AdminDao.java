package com.my.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.security.auth.login.LoginException;

import com.my.db.DbManager;
import com.my.entity.Admin;
import com.my.entity.Doctor;
import com.my.entity.Patient;
import com.my.exception.DBException;
import com.my.exception.UsernameExistsException;

public class AdminDao {
	private static final String SQL_SELECT_ADMIN = "SELECT * FROM admin WHERE username=?";
	private static final String SQL_INSERT_ADMIN = "INSERT INTO admin VALUES (DEFAULT,?,?,?,?,?)";

	private static Logger log = Logger.getLogger(AdminDao.class.getName());

	public Admin logIn(String login, String password) throws LoginException {
		// return DBManager.getInstance().logIn(login, hash(password));
		return logIn(login, hash(password));
	}

	public String hash(String pwd) {
		String md5Hashed = null;
		if (null == pwd) {
			return null;
		}

		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(pwd.getBytes(), 0, pwd.length());
			md5Hashed = new BigInteger(1, digest.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5Hashed;
	}

	public void register(String firstname, String lastname, String username, String password, int roleId)
			throws DBException {

		Connection con = null;

		try {
			con = DbManager.getInstance().getConnection();
			System.out.println(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Admin user = getAdmin(con, username);
		System.out.println(user);
		// if (!(user.getUsername().equals(username))) {
		if (user.getUsername() == null) {
			// if (user== null) {
			user = new Admin();
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setUsername(username);
			// user.setPassword(passwordEncoder.encode(password));
			user.setPassword(password);
			user.setrId(roleId);
			insertAdmin(con, user);
			System.out.println(user);
		} else {
			System.out.println("Username already Exists");
			throw new UsernameExistsException(username);
		}

	}

	public Admin getAdmin(Connection con, String username) throws DBException, UsernameExistsException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Admin admin = new Admin();

		try {
			ps = con.prepareStatement(SQL_SELECT_ADMIN);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				admin.setaId(rs.getInt("a_id"));
				admin.setFirstname(rs.getString("firstname"));
				admin.setLastname(rs.getString("lastname"));
				admin.setUsername(rs.getString("username"));
				admin.setPassword(rs.getString("password"));
				// String hashpass=hash(rs.getString("password"));
				// System.out.println(hashpass);

				// admin.setPassword(hashpass);
				// hash(password)
				admin.setrId(rs.getInt("r_id"));
			} else {
				System.out.println("No admin with username " + username);
				//throw new UsernameExistsException("Username already Exists");
			}
		} catch (SQLException ex) {
			System.out.println(ex);
			log.log(Level.SEVERE, "Cannot get admin: ", ex);
			throw new DBException("Cannot get admin", ex);

		} finally {
			DbManager.close(rs);
			DbManager.close(ps);
		}
		return admin;

	}

	public void insertAdmin(Connection con, Admin admin) throws DBException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement(SQL_INSERT_ADMIN);

			ps.setString(1, admin.getFirstname());
			ps.setString(2, admin.getLastname());
			ps.setString(3, admin.getUsername());
			ps.setString(4, admin.getPassword());
			ps.setInt(5, admin.getrId());
			ps.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			log.log(Level.SEVERE, "Cannot insert admin: ", ex);
			DbManager.rollback(con);
			// throw yr own exception
			throw new DBException("Cannot insert admin", ex);
		} finally {
			DbManager.close(rs);
			DbManager.close(ps);
		}
	}

}
