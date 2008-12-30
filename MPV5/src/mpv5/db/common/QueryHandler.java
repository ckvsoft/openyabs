/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpv5.db.common;

import java.awt.Cursor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import mpv5.globals.Messages;
import mpv5.logging.Log;
import mpv5.ui.frames.MPV5View;
import mpv5.ui.dialogs.Popup;
import mpv5.utils.arrays.ArrayUtils;
import mpv5.utils.date.DateConverter;
import mpv5.utils.date.vTimeframe;

/**
 *
 * @author Andreas
 */
public class QueryHandler implements Cloneable {

    private static QueryHandler instance;
    private DatabaseConnection conn = null;
    private Connection sqlConn = null;
    private Statement stm = null;
    public String[] resultArray = null;
    private ResultSet resultSet = null;
    private String table = null;
    public String resultString = null;
    private static JFrame comp = new JFrame();
    private Context context;

    /**
     *
     * @return
     */
    public static QueryHandler instanceOf() {
        if (instance == null) {
            instance = new QueryHandler();
        }
        return instance;
    }

    private QueryHandler() {
        try {
            conn = DatabaseConnection.instanceOf();
            sqlConn = conn.getConnection();
        } catch (Exception ex) {
            Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param context
     * @return
     */
    public QueryHandler setContext(Context context) {
        table = context.getDbIdentity();
        return this;
    }

    /**
     *
     * @param id
     * @return
     */
    public ReturnValue select(int id) {
        return freeReturnQuery("SELECT * FROM " + table + " WHERE ids = " + id, mpv5.usermanagement.SecurityManager.VIEW, null);
    }

    public Object[] getValuesFor(String needle, String value) {
        if (context != null) {
            if (value == null) {
                return ArrayUtils.ObjectToSingleColumnArray(freeReturnQuery("SELECT " + needle + " FROM " + table + " " + context.getConditions(), mpv5.usermanagement.SecurityManager.VIEW, null).getData());
            } else {
                return ArrayUtils.ObjectToSingleColumnArray(freeReturnQuery("SELECT " + needle + " FROM " + table + " WHERE " + needle + " LIKE %" + value + "% AND " + context.getConditions(), mpv5.usermanagement.SecurityManager.VIEW, null).getData());
            }
        } else if (value == null) {
            return ArrayUtils.ObjectToSingleColumnArray(freeReturnQuery("SELECT " + needle + " FROM " + table, mpv5.usermanagement.SecurityManager.VIEW, null).getData());
        } else {
            return ArrayUtils.ObjectToSingleColumnArray(freeReturnQuery("SELECT " + needle + " FROM " + table + "  WHERE " + needle + " LIKE %" + value + "%", mpv5.usermanagement.SecurityManager.VIEW, null).getData());
        }
    }

    /**
     * 
     * @param what
     * @param where
     * @param zeitraum
     * @return
     */
    public Object[][] selectBetween(String what, String[] where, vTimeframe zeitraum) {
        String str = "AND datum BETWEEN '" + DateConverter.getSQLDateString(zeitraum.getStart()) + "' AND '" + DateConverter.getSQLDateString(zeitraum.getEnd()) + "'";
        String query;
        if (where != null) {
            query = "SELECT " + what + " FROM " + table + " WHERE " + where[0] + " = " + where[2] + where[1] + where[2] + " " + "  " + str;
        } else {
            query = "SELECT " + what + " FROM " + table + "  " + str;
        }
        String message = "Database Error (select) :";
        return freeReturnQuery(query, mpv5.usermanagement.SecurityManager.VIEW, null).getData();
    }

    /**
     *
     * @param what
     * @param where
     * @param zeitraum
     * @param additionalCondition
     * @return
     */
    public ArrayList<Double> selectYearlySums(String what, String[] where, vTimeframe zeitraum, String additionalCondition) {

        Date temdate = zeitraum.getStart();
        ArrayList<Double> values = new ArrayList<java.lang.Double>();
        String query;
        do {
            String str = "AND datum BETWEEN '" + DateConverter.getSQLDateString(temdate) + "' AND '" + DateConverter.getSQLDateString(DateConverter.addYear(temdate)) + "'";

            if (where != null) {
                query = "SELECT SUM(" + what + ") FROM " + table + " WHERE " + where[0] + " = " + where[2] + where[1] + where[2] + " " + "  " + str + " " + additionalCondition;
            } else {
                query = "SELECT SUM(" + what + ") FROM " + table + "  " + str + " " + additionalCondition;
            }

            Object[][] o = freeReturnQuery(query, mpv5.usermanagement.SecurityManager.VIEW, null).getData();
            if (o != null && o[0][0] != null && !o[0][0].equals("null")) {
                values.add(Double.valueOf(String.valueOf(o[0][0])));
            } else {
                values.add(0d);
            }
            temdate = DateConverter.addMonth(temdate);
        } while (temdate.before(zeitraum.getEnd()));

        return values;

    }

    public ArrayList<Double> selectMonthlySums(String what, String[] where, vTimeframe zeitraum, String additionalCondition) {

        Date temdate = zeitraum.getStart();
        ArrayList<Double> values = new ArrayList<java.lang.Double>();
        String query;
        do {
            String str = "AND datum BETWEEN '" + DateConverter.getSQLDateString(temdate) + "' AND '" + DateConverter.getSQLDateString(DateConverter.addMonth(temdate)) + "'";

            if (where != null) {
                query = "SELECT SUM(" + what + ") FROM " + table + " WHERE " + where[0] + " = " + where[2] + where[1] + where[2] + " " + "  " + str + " " + additionalCondition;
            } else {
                query = "SELECT SUM(" + what + ") FROM " + table + "  " + str + " " + additionalCondition;
            }

            Object[][] o = freeReturnQuery(query, mpv5.usermanagement.SecurityManager.VIEW, null).getData();
            if (o != null && o[0][0] != null && !o[0][0].equals("null")) {
                Log.Debug(this, "Summe: " + o[0][0]);
                values.add(Double.valueOf(String.valueOf(o[0][0])));
            } else {
                Log.Debug(this, "Summe: " + 0);
                values.add(0d);
            }
            temdate = DateConverter.addMonth(temdate);
        } while (temdate.before(zeitraum.getEnd()));

        return values;

    }

    public Object[][] select(String what, String[] where, String leftJoinTable, String leftJoinKey, String order, Boolean like) {
        start();
        String query;
        String l1 = "";
        String l2 = "";
        String k = " = ";
        String j = "";

        String wher = "";
        java.util.Date date;

        if (like != null) {
            if (like) {
                if (where != null && where[0].endsWith("datum")) {
                    k = " BETWEEN ";
                    date = DateConverter.getDate(where[1]);
                    where[1] = "'" + DateConverter.getSQLDateString(date) + "'" + " AND " + "'" + DateConverter.getSQLDateString(DateConverter.addMonth(date)) + "'";
                    where[2] = " ";
                } else {
                    l1 = "%";
                    l2 = "%";
                    k = " LIKE ";
                }
            }
        }

        if (where != null) {

            query = "SELECT " + what + " FROM " + table +
                    " LEFT OUTER JOIN " + leftJoinTable + " ON " + table + "." + leftJoinKey + " = " + leftJoinTable + ".id" +
                    " WHERE " + table + "." + where[0] + " " + k + " " + where[2] + l1 + where[1] + l2 + where[2] + " ORDER BY " + order;
        } else {
            query = "SELECT " + what + " FROM " + table +
                    " LEFT OUTER JOIN  " + leftJoinTable + " ON " +
                    table + "." + leftJoinKey + " = " + leftJoinTable + ".id " +
                    "  ORDER BY " + order;
        }

        return freeReturnQuery(query, mpv5.usermanagement.SecurityManager.VIEW, null).getData();
    }

    public int selectCountBetween(java.util.Date date1, java.util.Date date2) {
        return selectCount("datum", "BETWEEN '" + DateConverter.getSQLDateString(date1) + "' AND '" + DateConverter.getSQLDateString(date2) + "'");
    }

    public void setTable(String newTable) {
        this.table = newTable;
    }

    public String getTable() {
        return table;
    }

    /**
     * For the wait-cursor
     * @param main 
     */
    public static void setWaitCursorFor(JFrame main) {
        comp = main;
    }

    private void stop() {
        comp.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    private void start() {
        comp.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    }

//    public String getNextStringNumber(String colName) {
//        Integer s = getNextIndexOfStringCol(colName, null);
//        return originalvalue.substring(0, substringcount) + s;
//    }
    public Integer getCount() {
        start();
        int i = selectCount(null, null);
        stop();

        i = (i < 0) ? -i : i;
        return i;
    }

    /**
     *
     * @param what
     * @param uniquecols
     * @return id (unique) of the inserted row
     */
    public int insert(String[] what, int[] uniquecols, String jobmessage) {
        what[1] = what[1].replaceAll("'", "`");
        what[1] = what[1].replaceAll("\\(;;2#4#1#1#8#0#;;\\)", "'");
        what[1] = what[1].replaceAll("\\(;;\\,;;\\)", ",");
        what[2] = what[2].replaceAll("\\(;;\\,;;\\)", ",");
        String query;
        start();


        if (uniquecols != null) {
            for (int i = 0; i < uniquecols.length; i++) {
                int j = uniquecols[i];
                Object[][] val = select(what[0].split(",")[j], new String[]{what[0].split(",")[j], what[1].split(",")[j], what[2]});
                if (val != null && val.length > 0) {
                    Popup.error("Wert für `" + what[0].split(",")[j] + "´ existiert bereits: " + val[0][0] +
                            ",\nund kann nicht mehrfach eingetragen werden.",
                            "Fehler beim Überprüfen der Datenkonsistenz");

                    return -1;
                }
            }
        }

        query = "INSERT INTO " + table + " (" + what[0] + " ) VALUES (" + what[1] + ") ";

        return freeQuery(query, mpv5.usermanagement.SecurityManager.CREATE, jobmessage).getId();
    }

    /**
     * 
     * @param what  : {set, value, "'"}
     *   this.insert("name,wert", "'Sprache (Waehrung, z.B. Schweiz:  de_CH' ,'de_DE'");
     * @return id of inserted row
     */
    public int insert(String[] what, String jobmessage) {
        return insert(what,null,jobmessage);
    }

    /**
     * 
     * @param what  : {set, values}
     * @param where : {value, comparison, "'"}
     */
    public void update(String[] what, String[] where, String jobmessage) {

        start();
        what[1] = what[1].replaceAll("'", "`");
        what[1] = what[1].replaceAll("\\(;;2#4#1#1#8#0#;;\\)", "'");
        String query;
        Log.Debug(this, what);

        String[] a = what[0].split(",");
        String[] b = what[1].split("\\(;;\\,;;\\)");
        String c = "";

        for (int i = 0; i < a.length; i++) {
            c = c + a[i] + " = " + b[i] + ", ";
        }

        c = c.substring(0, c.length() - 2);

        query = "UPDATE " + table + " SET " + c + " WHERE " + where[0] + " = " + where[2] + where[1] + where[2];
        freeQuery(query, mpv5.usermanagement.SecurityManager.EDIT, jobmessage);
        stop();
    }

    /**
     * 
     * @param what
     * @param where : {value, comparison, "'"}
     * @return last matching result as string array
     */
    @SuppressWarnings("unchecked")
    public Object[] selectLast(String what, String[] where) {

        String l = "";
        String k = " = ";

        String query;
        start();
        query = "SELECT " + what + " FROM " + table + " WHERE " + where[0] + k + where[2] + l + where[1] + l + where[2];

        return freeReturnQuery(query, mpv5.usermanagement.SecurityManager.VIEW, null).getData()[0];

    }

    /**
     * if "where" is "null", everything is selected (without "where" -clause)
     * 
     * @param what
     * @param where : {value, comparison, "'"}
     * @return first matching result as string array
     */
    @SuppressWarnings("unchecked")
    public Object[] selectFirst(String what, String[] where) {

        start();
        String query = "SELECT " + what + " FROM " + table + " WHERE " + where[0] + " = " + where[2] + where[1] + where[2] + " ";
        Object[][] data = freeReturnQuery(query, mpv5.usermanagement.SecurityManager.VIEW, null).getData();
        return data[data.length - 1];
    }

    /**
     * 
     * @param what
     * @param where
     * @param leftJoinTable
     * @param leftJoinKey 
     * @param order 
     * @return results as multidimensional string array
     */
    @SuppressWarnings("unchecked")
    public Object[][] select(String what, String[] where, String leftJoinTable, String leftJoinKey, String order) {
        return select(what, where, leftJoinTable, leftJoinKey, order, null);
    }

    /**
     * 
     * @param what
     * @param where
     * @param leftJoinTable
     * @param leftJoinKey 
     * @return results as multidimensional string array
     */
    @SuppressWarnings({"unchecked", "unchecked"})
    public Object[][] select(String what, String[] where, String leftJoinTable, String leftJoinKey) {
        return select(what, where, leftJoinTable, leftJoinKey, "id", null);
    }

    /**
     * 
     * @param what
     * @param where : {value, comparison, "'"}
     * @return results as multidimensional string array
     */
    @SuppressWarnings("unchecked")
    public Object[][] select(String what, String[] where) {
        start();
        String query;
        if (where != null) {
            query = "SELECT " + what + " FROM " + table + " WHERE " + where[0] + " = " + where[2] + where[1] + where[2];
        } else {
            query = "SELECT " + what + " FROM " + table;
        }
        return freeReturnQuery(query, mpv5.usermanagement.SecurityManager.VIEW, null).getData();
    }

    /**
     * 
     * @param what
     * @param where : {value, comparison, "'"}
     * @param order 
     * @param like - datum will be returned between given and given + 1 month
     * @return results as multidimensional string array
     */
    @SuppressWarnings("unchecked")
    public Object[][] select(String what, String[] where, String order, boolean like) {
        start();
        String l1 = "";
        String l2 = "";
        String k = " = ";
        String j = "";

        if (order == null) {
            order = "ids DESC ";
        }

        String ord = " ORDER BY " + order;
        String wher = "";
        java.util.Date date;


        if (like) {
            if (where != null && where[0].endsWith("datum")) {
                k = " BETWEEN ";
                date = DateConverter.getDate(where[1]);
                where[1] = "'" + DateConverter.getSQLDateString(date) + "'" + " AND " + "'" + DateConverter.getSQLDateString(DateConverter.addMonth(date)) + "'";
                where[2] = " ";
            } else {
                l1 = "%";
                l2 = "%";
                k = " LIKE ";
            }
        }

        if (where == null) {
            wher = "  ";
        } else {
            wher = " WHERE " + where[0] + " " + k + " " + where[2] + l1 + where[1] + l2 + where[2] + " ";
        }
        String query = "SELECT " + what + " FROM " + table + wher + ord;

        return freeReturnQuery(query, mpv5.usermanagement.SecurityManager.VIEW, null).getData();
    }

    /**
     * 
     * @param where : {value, comparison, "'"}
     * @throws Exception 
     */
    public void delete(Object[][] where) throws Exception {

        start();
        String str = "";
        String query = null;

        if (where != null) {
            for (int i = 0; i < where.length; i++) {
                str = str + where[i][0] + " = " + where[i][2] + where[i][1] + where[i][2];
            }
            query = "DELETE FROM " + table + " WHERE " + str;
            freeQuery(query, mpv5.usermanagement.SecurityManager.VIEW, null);
        }
    }

    /**
     * 
     * @param newTable
     * @return a clone of this ConnectionHandler (with database connection)
     */
    public QueryHandler clone(String newTable) {
        QueryHandler theClone = null;
        try {
            theClone = (QueryHandler) this.clone();
            theClone.setTable(newTable);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return theClone;
    }

    public QueryHandler clone(Context context) {
        QueryHandler theClone = null;
        this.context = context;
        try {
            theClone = (QueryHandler) this.clone();
            theClone.setTable(context.getDbIdentity());
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return theClone;
    }

    /**
     * 
     * @param what
     * @param where
     * @param order
     * @param like
     * @param integer
     * @return
     */
    @SuppressWarnings({"unchecked", "unchecked"})
    public Object[][] select(String what, String[] where, String order, boolean like, boolean integer) {
        start();
        String l = "";
        String k = " = ";
        String j = "";
        String ord = " ORDER BY " + order;
        String wher = "";
        wher = "";

        if (integer) {
            if (where[1].equals("")) {
                where[1] = "0";
            }
            where[2] = "";
//            l = "%";
            k = " = ";
//            j = " OR WHERE " + where[0] + " " + k + " " + where[2] + l + where[1] + l + where[2];
        }
        java.util.Date date;
        if (like) {
            if (where != null && where[0].contains("datum")) {
                k = " BETWEEN ";
                date = DateConverter.getDate(where[1]);
                where[1] = "'" + DateConverter.getSQLDateString(date) + "'" + " AND " + "'" + DateConverter.getSQLDateString(DateConverter.addMonth(date)) + "'";
                where[2] = " ";
            } else {
                l = "%";
                k = " LIKE ";
            }
        }

        if (where == null) {
        } else {
            wher = where[0] + " " + k + " " + where[2] + l + where[1] + l + where[2] + " AND " + wher;
            if (where.length > 3) {
                wher = wher + " AND " + where[3] + " " + k + " " + where[5] + l + where[4] + l + where[5] + " ";
            }
        }
        String query = "SELECT " + what + " FROM " + table + " WHERE " + wher + ord;

        return freeReturnQuery(query, mpv5.usermanagement.SecurityManager.VIEW, null).getData();
    }

    /**
     * 
     * @param from
     * @param where
     * @return
     */
    public int selectCount(String from, String where) {
        String wher = "";

        if (where != null) {
            wher = " WHERE " + from + " " + where;
        }
        String query = "SELECT COUNT(*) AS rowcount FROM " + table + " " + wher;
        String message = "Database Error (SelectCount:COUNT):";
        stm = null;
        resultSet = null;

        Log.Debug(this, query, true);
        try {
            // Select-Anweisung ausführen
            stm = sqlConn.createStatement(resultSet.TYPE_SCROLL_INSENSITIVE, resultSet.CONCUR_READ_ONLY);
            resultSet = stm.executeQuery(query);

            if (resultSet.first()) {
                Log.Debug(this, "Count " + resultSet.getInt("rowcount"));
                return resultSet.getInt("rowcount");
            } else {
                stop();
                return 0;
            }
        } catch (SQLException ex) {
            Log.Debug(this, message + ex.getMessage());
            Popup.error(message + ex.getMessage(), "Datenbankfehler");
            stop();
            return 0;
        } finally {
            // Alle Ressourcen wieder freigeben
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Log.Debug(this, message + ex.getMessage());
                    Popup.error(message + ex.getMessage(), "Datenbankfehler");
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Log.Debug(this, message + ex.getMessage());
                    Popup.error(message + ex.getMessage(), "Datenbankfehler");
                }
            }
        }
    }

    /**
     * Free SQL Statement!
     * @param string
     * @return
     */
    @SuppressWarnings("unchecked")
    public ReturnValue freeQuery(String string, int action, String jobmessage) {
        return freeQuery(string, null, action, jobmessage);
    }

    @SuppressWarnings("unchecked")
    public ReturnValue freeQuery(String string, JTextArea log, int action, String jobmessage) {

        if (!mpv5.usermanagement.SecurityManager.check(context, action)) {
            Log.Debug(this, Messages.SECURITYMANAGER_DENIED +
                    mpv5.usermanagement.SecurityManager.getActionName(action) + Messages.CONTEXT + context.getDbIdentity());
            Popup.warn(Messages.SECURITYMANAGER_DENIED +
                    mpv5.usermanagement.SecurityManager.getActionName(action) + Messages.CONTEXT + context.getDbIdentity(),
                    Messages.ACCESS_DENIED);
            return new ReturnValue(-1, new Object[0][0], new String[0]);
        }

        start();
        String query = string;
        ReturnValue retval = new ReturnValue(-1, new Object[0][0], new String[0]);
        String message = "Database Error (freeQuery) :";
        stm = null;
        resultSet = null;
        boolean bool;
        ResultSetMetaData rsmd;
        Object[][] data;
        ArrayList z;
        int id = -1;
        String[] columnnames;
        try {
            // Select-Anweisung ausführen
            stm = sqlConn.createStatement();
            Log.Debug(this, query, true);
            if (log != null) {
                log.append("\n " + query);
            }
            bool = stm.execute(query);
            if (log != null) {
                log.append("\n " + stm.getUpdateCount() + " Reihen betroffen.");
            }
            stop();

            if (bool) {

                resultSet = stm.getResultSet();
                ArrayList spalten = new ArrayList();
                ArrayList zeilen = new ArrayList();
                rsmd = resultSet.getMetaData();
                if (stm.getGeneratedKeys() != null && stm.getGeneratedKeys().first()) {
                    id = stm.getGeneratedKeys().getInt(1);
                }

                columnnames = new String[rsmd.getColumnCount()];
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    columnnames[i - 1] = rsmd.getColumnName(i);
                }

                while (resultSet.next()) {
                    spalten = new ArrayList();
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        spalten.add(resultSet.getObject(i));
                    }
                    zeilen.add(spalten);
                }
                data = new Object[zeilen.size()][spalten.size()];

                for (int h = 0; h < zeilen.size(); h++) {
                    z = (ArrayList) zeilen.get(h);
                    for (int i = 0; i < spalten.size(); i++) {
                        data[h][i] = z.get(i);
                    }
                }
                retval = new ReturnValue(id, data, columnnames);
            }

        } catch (SQLException ex) {
            stop();
            Log.Debug(this, message + ex.getMessage());
            if (log != null) {
                log.append("\n " + ex.getMessage());
            }
        } finally {
            // Alle Ressourcen wieder freigeben
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    stop();
                    Log.Debug(this, message + ex.getMessage());
                    if (log != null) {
                        log.append(" \n" + ex.getMessage());
                    }
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    stop();
                    Log.Debug(this, message + ex.getMessage());
                    if (log != null) {
                        log.append(" \n" + ex.getMessage());
                    }
                }
            }
        }
        stop();
        if(jobmessage!=null) {
            MPV5View.addMessage(jobmessage);
        }
        return retval;

    }

    /**
     * Free SQL Select Statement!
     * @param query
     * @return Your Data
     */
    @SuppressWarnings({"unchecked"})
    public ReturnValue freeReturnQuery(String query, int action, String jobmessage) {

        if (!mpv5.usermanagement.SecurityManager.check(context, action)) {

            Log.Debug(this, Messages.SECURITYMANAGER_DENIED +
                    mpv5.usermanagement.SecurityManager.getActionName(action) + Messages.CONTEXT + context.getDbIdentity());
            Popup.warn(Messages.SECURITYMANAGER_DENIED +
                    mpv5.usermanagement.SecurityManager.getActionName(action) + Messages.CONTEXT + context.getDbIdentity(),
                    Messages.ACCESS_DENIED);
            return new ReturnValue(-1, new Object[0][0], new String[0]);
        }

        start();
        String message = "Database Error (selectFreeQuery) :";

        stm = null;
        resultSet = null;
        ResultSetMetaData rsmd;
        Object[][] data = null;
        ArrayList z;
        int id = -1;
        String[] columnnames = null;

        try {
            // Select-Anweisung ausführen
            stm = sqlConn.createStatement();
            Log.Debug(this, query, true);
            resultSet = stm.executeQuery(query);
            ArrayList spalten = new ArrayList();
            ArrayList zeilen = new ArrayList();
            rsmd = resultSet.getMetaData();
            if (stm.getGeneratedKeys() != null && stm.getGeneratedKeys().first()) {
                id = stm.getGeneratedKeys().getInt(1);
            }

            columnnames = new String[rsmd.getColumnCount()];
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                columnnames[i - 1] = rsmd.getColumnName(i);
            }

            while (resultSet.next()) {
                spalten = new ArrayList();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    spalten.add(resultSet.getObject(i));
                }
                zeilen.add(spalten);
            }
            data = new Object[zeilen.size()][spalten.size()];

            for (int h = 0; h < zeilen.size(); h++) {
                z = (ArrayList) zeilen.get(h);
                for (int i = 0; i < spalten.size(); i++) {
                    data[h][i] = z.get(i);
                }
            }
        } catch (SQLException ex) {
            Log.Debug(this, "Datenbankfehler: " + query, true);
            Log.Debug(this, message + ex.getMessage());
            Popup.error(message + ex.getMessage(), "Datenbankfehler");
        } finally {
            // Alle Ressourcen wieder freigeben
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Log.Debug(this, message + ex.getMessage());
                    Popup.error(message + ex.getMessage(), "Datenbankfehler");
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Log.Debug(this, message + ex.getMessage());
                    Popup.error(message + ex.getMessage(), "Datenbankfehler");
                }
            }
        }
        stop();
        if(jobmessage!=null)MPV5View.addMessage(jobmessage);
        return new ReturnValue(id, data, columnnames);
    }
}