/*
 *  This file is part of MP by anti43 /GPL.
 *  
 *      MP is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *  
 *      MP is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *  
 *      You should have received a copy of the GNU General Public License
 *      along with MP.  If not, see <http://www.gnu.org/licenses/>.
 */
package mp3.classes.layer;

import javax.swing.JTable;

import mp3.classes.interfaces.Queries;
import handling.db.Query;


/**
 *
 * @author anti
 */
public abstract class People implements Queries, mp4.datenbank.struktur.Tabellen{

    public Integer id = 0;
    public boolean isSaved = false;
    private Query q;

    public People(Query query) {

        q = query;
    }

    public int delete(String id) {
        String[] where = {"id", id, ""};
        return q.delete(where);
    }

    /**
     * 
     * @param id
     * @return
     */
    public int deactivate(String id) {

        String[] where = {"id", id, ""};
        String[] what = {"deleted", "1", ""};
        return q.update(what, where);
    }

    @Override
    public void finalize() {

//        if (!isSaved) {
//            this.save();
//        }

    }

    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param set
     * @param value
     * @param id
     * @return rowcount
     */
    public int update(String set, String value, String id) {
        String[] where = {"id", id, ""};
        String[] what = {set, value, "'"};
        return q.update(what, where);
    }

    /**
     * 
     * example: "*", name , anti43 , false 
     * 
     * @param what
     * @param from
     * @param where
     * @param id
     * @return
     */
    public String[][] select(String what, String from, String where, boolean id) {
        String hk = "'";
        if (id) {
            hk = "";
        }

        String[] wher = {from, where, hk};
        if (from == null) {
            wher = null;
        }

        return q.select(what, wher);
    }

    /**
     * 
     * example: "*", name , anti43 , false 
     * 
     * @param what
     * @param from
     * @param where
     * @param id
     * @return
     */
    public String[] selectFirst(String what, String from, String where, boolean id) {
        String hk = "'";
        if (id) {
            hk = "";
        }

        String[] wher = {from, where, hk};

        return q.selectFirst(what, wher);
    }

    /**
     * Example: "*", "Name", "anti43", "Name", true
     * will return everyone who`s name is like "anti43" sortet by name.
     * eg. anti43, anti43web
     * 
     * @param what
     * @param from
     * @param where
     * @param order
     * @param like
     * @return A multidimensional string-array containing the data found
     */
    public String[][] select(String what, String from, String where, String order, boolean like) {
        String hk = "'";

        String[] wher = {from, where, hk};
        if(from == null) {
            wher = null;
        }

        return q.select(what, wher, order, like);
    }

    /**
     * 
     * example: "*", name , anti43 , false 
     * 
     * @param what
     * @param from
     * @param where
     * @param id
     * @param ghosts 
     * @param like 
     * @return
     */
    public String[] selectLast(String what, String from, String where, boolean id, boolean ghosts, boolean like) {
        String hk = "'";
        if (id) {
            hk = "";
        }

        String[] wher = {from, where, hk};

        return q.selectLast(what, wher, ghosts, like);
    }

    public int insert(String set, String value) {
        String[] str = {set, value, ""};
        return q.insert(str);
    }

    public void destroy() {
        this.delete(this.id.toString());
        this.id = 0;
    }

    public Integer getNextNumber(String what) {
        return q.getNextIndex(what);
    }

    /**
     * Hides he first column of a table (usually "id")
     * @param table
     */
    public void stripFirst(JTable table) {

        table.getColumn(table.getColumnName(0)).setWidth(0);
        table.getColumn(table.getColumnName(0)).setPreferredWidth(0);
        table.getColumn(table.getColumnName(0)).setMinWidth(0);
        table.getColumn(table.getColumnName(0)).setMaxWidth(0);

        table.doLayout();


    }

    /**
     * to be overwritten..
     */
    public abstract void save();
}
