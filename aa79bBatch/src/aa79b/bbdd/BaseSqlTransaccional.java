package aa79b.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import aa79b.util.common.Constants;
import aa79b.util.common.Logger;


/**
 * Titulo: DBConnection 
 * Empresa: Eurohelp Consulting 
 * Copyright: Copyright (c) 2012
 * 
 * @author EuroHelp
 * @version 1.0
 * 
 */
public class BaseSqlTransaccional {
	
	private String dbDriver = "";
	private String dbServer = "";
	private String dbLogin = "";
	private String dbPassword = "";
	private String dbCharSet = "UTF-8";
	private Connection dbConnection = null;
	
	/**
	 * Este metodo cierra la conexion
	 */
	public void closeConection() {
		try {
			if (this.dbConnection != null) {
				this.dbConnection.close();
			}
		} catch (Exception e) {
			Logger.batchlog(Logger.INFO, "Cerrando conexion transaccional...");
		}
	}

	/**
	 * Este metodo sirve para crear una instancia de W22BaseSql
	 * 
	 * @param driver El dirver
	 * @param server El server
	 * @param login El login
	 * @param password El password
	 * @return Una instancia
	 */
	public BaseSqlTransaccional(
			 String driver
			,String server
			,String login
			,String password
			,String charSet) {
		this.setDbDriver(driver);
		this.setDbServer(server);
		this.setDbLogin(login);
		this.setDbPassword(password);
		this.setDbCharSet(charSet);
		
		try {
			this.setDbConnection(this.getConnection());
		} catch (Throwable e) {
			Logger.batchlog(Logger.INFO, "creando conexion transaccional...");
			throw new RuntimeException(e);
		}
	}
	

	/**
	 * @return the dbDriver
	 */
	public String getDbDriver() {
		return this.dbDriver;
	}

	/**
	 * @param dbDriver the dbDriver to set
	 */
	private void setDbDriver(String dbDriver) {
		if (dbDriver != null) {
			this.dbDriver = dbDriver;
		} else {
			this.dbDriver = "";
		}
	}

	/**
	 * @return the dbServer
	 */
	public String getDbServer() {
		return this.dbServer;
	}

	/**
	 * @param dbServer the dbServer to set
	 */
	private void setDbServer(String dbServer) {
		if (dbServer != null) {
			this.dbServer = dbServer;
		} else {
			this.dbServer = "";
		}
	}

	/**
	 * @return the dbLogin
	 */
	public String getDbLogin() {
		return this.dbLogin;
	}

	/**
	 * @param dbLogin the dbLogin to set
	 */
	private void setDbLogin(String dbLogin) {
		if (dbLogin != null) {
			this.dbLogin = dbLogin;
		} else {
			this.dbLogin = "";
		}
	}

	/**
	 * @return the dbPassword
	 */
	public String getDbPassword() {
		return this.dbPassword;
	}

	/**
	 * @param dbPassword the dbPassword to set
	 */
	private void setDbPassword(String dbPassword) {
		if (dbPassword != null) {
			this.dbPassword = dbPassword;
		} else {
			this.dbPassword = "";
		}
	}
	
	/**
	 * @return the dbCharSet
	 */
	public String getDbCharSet() {
		return this.dbCharSet;
	}
	
	/**
	 * @param dbCharSet the dbCharSet to set
	 */
	private void setDbCharSet(String dbCharSet) {
		if (dbCharSet != null) {
			this.dbCharSet = dbCharSet;
		} else {
			this.dbCharSet = "";
		}
	}

	/**
	 * Method to get the dbConnection.
	 * @return the dbConnection
	 */
	public final Connection getDbConnection() {
		return this.dbConnection;
	}

	/**
	 * Method to set the dbConnection.
	 * @param dbConnection the dbConnection to set
	 */
	private final void setDbConnection(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	/**
	 * Este metodo sirve para obtener la conexion del base sql
	 * 
	 * @return La conexion
	 * @throws SQLException  Cualquier excepcion
	 */
	private Connection getConnection() throws SQLException {
		try {
			Class.forName(this.getDbDriver());
		} catch (ClassNotFoundException cnfe) {
			throw new java.sql.SQLException(cnfe.getMessage() 
					+ "..."
					+ cnfe.getClass().getName());
		}
		
		Properties props = new Properties();
		props.put ("user", this.getDbLogin());
		props.put ("password", this.getDbPassword());
		props.put ("charSet", this.getDbCharSet());
		
		Connection conn = DriverManager.getConnection(Constants.JDBC_ORACLE+this.getDbServer(), props);
		conn.setAutoCommit(false);
		
		return conn;
	}
}
