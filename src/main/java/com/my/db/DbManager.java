package com.my.db;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DbManager {
	private static DbManager instance;
	private DataSource ds;
	
	
	private static Logger log = Logger.getLogger(DbManager.class.getName());

	private DbManager() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/hospital");
		} catch (NamingException ex) {
			throw new IllegalStateException("Cannot init DbMabager", ex);

		}
	}

	public static synchronized DbManager getInstance() {
		if (instance == null) {
			instance = new DbManager();
		}
		return instance;

	}

	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	public static void close(AutoCloseable ac) {
		if (ac != null) {
			try {
				ac.close();
			} catch (Exception ex) {
				log.log(Level.SEVERE, "close(AutoCloseable ac) failed: ", ex);
			}
		}
	}

	public static void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				log.log(Level.SEVERE, "rollback() failed: ", ex);
			}
		}
	}

}

