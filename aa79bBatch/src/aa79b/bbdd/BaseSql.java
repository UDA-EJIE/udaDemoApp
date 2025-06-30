/*
 * Created on 01/10/2007
 *
 */
package aa79b.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import aa79b.util.common.Constants;
import aa79b.util.common.Logger;

/**
 * Titulo: BaseSql Empresa: Eurohelp Consulting Copyright: Copyright (c)
 * 2011.
 *
 * @author omartinez
 * @version 1.0
 *
 */
public final class BaseSql {
    /**
     * Este metodo cierra la conexion, el statement y el resulset.
     *
     * @param conn
     *            La conexion
     * @param pstmt
     *            El estatement
     * @param rs
     *            El resultset
     */
    public static void closeConection(Connection conn, PreparedStatement pstmt,
            ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (final Exception e) {
            Logger.batchlog(Logger.ERROR,
                    "rs.close [" + e.getClass()
                    + "] en ResultSet", e);
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (final Exception e) {
            Logger.batchlog(Logger.ERROR,
                    "pstmt.close [" + e.getClass()
                    + "] en PreparedStatement", e);
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (final Exception e) {
            Logger.batchlog(Logger.ERROR,
                    "conn.close [" + e.getClass()
                    + "] en Connection", e);
        }
    }

    /**
     * Este metodo sirve para crear una instancia de W22BaseSql.
     *
     * @param driver
     *            El dirver
     * @param server
     *            El server
     * @param login
     *            El login
     * @param password
     *            El password
     * @return Una instancia
     */
    public static BaseSql createInstance(String driver, String server,
            String login, String password) {
        final BaseSql baseSql = new BaseSql();
        baseSql.setDbDriver(driver);
        baseSql.setDbServer(server);
        baseSql.setDbLogin(login);
        baseSql.setDbPassword(password);

        return baseSql;
    }

    /**
     * dbDriver: String.
     */
    private String dbDriver = "";
    /**
     * dbServer: String.
     */
    private String dbServer = "";
    /**
     * dbLogin: String.
     */
    private String dbLogin = "";
    /**
     * dbPassword: String.
     */
    private String dbPassword = "";

    /**
     * @return the dbDriver
     */
    public String getDbDriver() {
        return this.dbDriver;
    }

    /**
     * @param dbDriver
     *            the dbDriver to set
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
     * @param dbServer
     *            the dbServer to set
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
     * @param dbLogin
     *            the dbLogin to set
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
     * @param dbPassword
     *            the dbPassword to set
     */
    private void setDbPassword(String dbPassword) {
        if (dbPassword != null) {
            this.dbPassword = dbPassword;
        } else {
            this.dbPassword = "";
        }
    }

    /**
     * Este metodo sirve para obtener la conexion del base sql.
     *
     * @return La conexion
     * @throws SQLException
     *             Cualquier excepcion
     */
    public Connection getConnection() throws SQLException {
        try {
            Class.forName(this.getDbDriver());
        } catch (final ClassNotFoundException cnfe) {
            throw new java.sql.SQLException(cnfe.getMessage() + "..."
                    + cnfe.getClass().getName());
        }
        if(this.getDbServer().contains("jdbc:oracle:thin:@")){
            return DriverManager.getConnection(
                    this.getDbServer()
                    , this.getDbLogin()
                    , this.getDbPassword());
        } else {
            return DriverManager.getConnection(
                    Constants.JDBC_ORACLE+this.getDbServer()
                    , this.getDbLogin()
                    , this.getDbPassword());
        }
    }

    /**
     * El constructor.
     */
    private BaseSql() {
    }

}
