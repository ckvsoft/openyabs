/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpv5.db.common;

import java.sql.Driver;
import mpv5.logging.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import mpv5.globals.LocalSettings;
import mpv5.ui.dialogs.Popup;

/**
 *
 *  
 */
public class DatabaseConnection {

    private static java.sql.Connection conn;
    private static DatabaseConnection connector;
    private ConnectionTypeHandler ctype;
    private static String user;
    private static String password;

    /**
     * 
     * @return Database connector
     * @throws Exception 
     */
    public static synchronized DatabaseConnection instanceOf() throws Exception {
        if (connector == null) {
            connector = new DatabaseConnection();
            connector.connect(false);
        }
        return connector;
    }
    private Statement statement;

    public java.sql.Connection getConnection() {
        return conn;
    }

    /**
     * Test-Verbindung zur Datenbank herstellen.
     * @param predefinedDriver
     * @param user
     * @param password
     * @param location
     * @param dbname
     * @param create
     * @return Connection
     * @throws Exception
     */
    public boolean connect(String predefinedDriver, String user, String password, String location, String dbname, boolean create) throws Exception {

        ctype = new ConnectionTypeHandler();
        ctype.setDRIVER(predefinedDriver);
        ctype.setURL(location);
        ctype.setDBName(dbname);

        try {

            DriverManager.registerDriver((Driver) Class.forName(ctype.getDriver()).newInstance());
            Log.Debug(this, "Datenbanktreiber: " + ctype.getDriver());
        } catch (Exception ex) {
            Log.Debug(this, ex.getMessage());
        }

        try {
            Log.Debug(this, "Datenbankverbindung: " + ctype.getConnectionString(create));
            conn = DriverManager.getConnection(ctype.getConnectionString(create), user, password);
            if (conn != null //                    && conn.isValid(10)//does not work with MySQL 5.0
                    ) {
                connector = this;
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            System.out.println("Database Error: " + ex.getMessage());
            Log.Debug(this, ex);
            Log.Debug(this, ex.getNextException());
            Log.Debug(this, ex.getNextException().getNextException());
            Log.Debug(this, ex.getNextException().getNextException().getNextException());
//            Popup.warn(ex.getMessage(), Popup.ERROR);
            DatabaseConnection.shutdown();
            return false;
        }

    }

    /**
     * Verbindung zur Datenbank herstellen.
     * @return Connection
     */
    private Connection connect(boolean create) throws Exception {

        ctype = new ConnectionTypeHandler();
        try {
            Log.Debug(this, "Datenbanktreiber: " + ctype.getDriver());
            try {
                Class.forName(ctype.getDriver()).newInstance();
            } catch (ClassNotFoundException ex) {
//                Popup.warn(Messages.DB_DRIVER_INVALID + ex.getMessage(), Popup.ERROR);
                DatabaseConnection.shutdown();

            }
            user = LocalSettings.getProperty("dbuser");
            password = LocalSettings.getProperty("dbpassword");

        } catch (Exception ex) {
            ex.printStackTrace();
            Popup.warn(ex.getMessage());
            DatabaseConnection.shutdown();
        }

        try {
            Log.Debug(this, "Datenbankverbindung: " + ctype.getConnectionString(create));
            conn = DriverManager.getConnection(ctype.getConnectionString(create), user, password);

        } catch (SQLException ex) {
            System.out.println("Database Error: " + ex.getMessage());
            Log.Debug(this, ex.getMessage());
            try {
                Log.Debug(this, ex.getNextException());
                Log.Debug(this, ex.getNextException().getNextException());
                Log.Debug(this, ex.getNextException().getNextException().getNextException());
                Popup.warn(ex.getMessage());
                DatabaseConnection.shutdown();
            } catch (Exception e) {
                Log.Debug(this, ex.getMessage());
            }

            throw new Exception("Datenbank konnte nicht gestartet werden.");
        }
        return conn;
    }

    /**
     * Verbindung trennen
     */
    public static void shutdown() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                conn = null;
            }

        } catch (SQLException ex) {

            ex.printStackTrace();
            System.exit(1);
        }
    }

    public boolean runQueries(String[] queries) throws SQLException {
        Statement stm = this.getConnection().createStatement();
//        for (int i = 0; i < queries.length; i++) {
//            stm.addBatch(queries[i]);
//        }
//        Log.PrintArray(queries);
        try {
//            stm.executeBatch();
            for (int i = 0; i < queries.length; i++) {
                String string = queries[i];
                Log.Print(string);
                stm.execute(string);
                Thread.sleep(100);
            }
            return true;
        } catch (Exception sQLException) {
            Log.Debug(sQLException);
            sQLException.printStackTrace();
            return false;
        }
    }
}