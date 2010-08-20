/*
 *  This file is part of YaBS.
 *
 *      YaBS is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *
 *      YaBS is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with YaBS.  If not, see <http://www.gnu.org/licenses/>.
 */
package mpv5.db.objects;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;

import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.db.common.NodataFoundException;
import mpv5.db.common.QueryCriteria;
import mpv5.logging.Log;

/**
 *
 *  
 */
public final class ValueProperty extends DatabaseObject {

    public ValueProperty() {
        context = Context.getValueProperties();
    }

    /**
     * Create a new property
     * @param key
     * @param value
     * @param owner
     */
    public ValueProperty(final String key, final Serializable value, final DatabaseObject owner) {
        this();
        defineValueObj(owner);
        setContextids(owner.getContext().getId());
        setObjectids(owner.__getIDS());
        setGroupsids(owner.__getGroupsids());
        setCName(key);
    }

    /**
     * Create a new property or update an existing one
     * @param key
     * @param value
     * @param owner
     */
    public static synchronized void updateOrAddProperty(final String key, final Serializable value, final DatabaseObject owner) {
        if (key != null && owner !=null && key.length() > 0) {
            ValueProperty val;
            try {
                val = getProperty(owner, key);
            } catch (NodataFoundException ex) {
                val = new ValueProperty(key, value, owner);
            }
            val.defineValueObj(value);
            val.save(true);
        }
    }

    /**
     * Search for a specific property
     * @param owner
     * @param key
     * @return
     * @throws NodataFoundException
     */
    public static synchronized ValueProperty getProperty(final DatabaseObject owner, final String key) throws NodataFoundException {
        QueryCriteria c = new QueryCriteria("contextids", owner.getContext().getId());
        c.addAndCondition("cname", key);
        c.addAndCondition("objectids", owner.__getIDS());
        ArrayList<DatabaseObject> objects = DatabaseObject.getObjects(Context.getValueProperties(), c);
        return (ValueProperty) objects.get(0);
    }

    /**
     * Search for a specific property
     * @param owner
     * @param key
     */
    public static synchronized void deleteProperty(final DatabaseObject owner, final String key) {
        try {
            QueryCriteria c = new QueryCriteria("contextids", owner.getContext().getId());
            c.addAndCondition("cname", key);
            c.addAndCondition("objectids", owner.__getIDS());
            ArrayList<DatabaseObject> objects = DatabaseObject.getObjects(Context.getValueProperties(), c);
            for (int i = 0; i < objects.size(); i++) {
                DatabaseObject databaseObject = objects.get(i);
                databaseObject.delete();
            }
        } catch (NodataFoundException ex) {
        }
    }

    /**
     * Find all properties for the given owner
     * @param owner
     * @return A list of properties (possibly empty)
     */
    public static synchronized List<ValueProperty> getProperties(final DatabaseObject owner) {
        try {
            QueryCriteria c = new QueryCriteria("contextids", owner.getContext().getId());
            c.addAndCondition("objectids", owner.__getIDS());
            ArrayList<DatabaseObject> objects = DatabaseObject.getObjects(Context.getValueProperties(), c);
            return DatabaseObject.toObjectList(objects, new ValueProperty());
        } catch (NodataFoundException ex) {
            Log.Debug(ValueProperty.class, ex.getMessage());
            return new ArrayList<ValueProperty>();
        }
    }

    /**
     * Add or update multiple properties
     * @param values
     * @param owner
     */
    public synchronized static void addOrUpdateProperties(final Map<String, Serializable> values, final DatabaseObject owner) {
        for (Map.Entry<String, Serializable> element : values.entrySet()) {
            updateOrAddProperty(element.getKey(), element.getValue(), owner);
        }
    }
    private String classname = "";
    private Serializable valueObj;
    private int contextids;
    private int objectids;

    @Override
    public JComponent getView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Generates a xml string representation of the value object
     * @return the value
     */
    public synchronized String __getValue() {
        try {
            ByteArrayOutputStream io = new ByteArrayOutputStream();
            new XMLEncoder(io).writeObject(getValueObj());
            String x = io.toString("UTF-8");
            Log.Debug(io, x);
            return x;
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            //shall not happen on utf-8
            return "";
        }
    }

    /**
     * 
     * @param <T>
     * @param target
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public synchronized <T extends Object> T getValue(T target) throws Exception {

        if (target.getClass().isAssignableFrom(getClazz())) {
            return (T) valueObj;
        } else {
            throw new UnsupportedOperationException(target + " is not  assignable to " + getClazz());
        }
    }

    /**
     * Return the Class of the value object
     * @return
     * @throws ClassNotFoundException
     */
    public Class<?> getClazz() throws ClassNotFoundException {
        return Class.forName(__getClassname());
    }

    /**
     * You should not need to programmatically call this method, use {@link #defineValueObj(Serializable valueObj)}
     * @param value the value to set
     */
    public void setValue(final String value) {
        try {
            ByteArrayInputStream io = new ByteArrayInputStream(value.getBytes("UTF-8"));
            XMLDecoder d = new XMLDecoder(io);
            this.valueObj = (Serializable) d.readObject();
        } catch (Exception unsupportedEncodingException) {
            Log.Debug(unsupportedEncodingException);
        }
    }

    @Override
    public mpv5.utils.images.MPIcon getIcon() {
        return null;
    }

    /**
     * @return the classname
     */
    public String __getClassname() {
        return classname;
    }

    /**
     * You should not need to programmatically call this method, use {@link #defineValueObj(Serializable valueObj)}
     * @param classname the classname to set
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }

    /**
     * @return the contextids
     */
    public int __getContextids() {
        return contextids;
    }

    /**
     * @param contextids the contextids to set
     */
    public void setContextids(int contextids) {
        this.contextids = contextids;
    }

    /**
     * @return the objectids
     */
    public int __getObjectids() {
        return objectids;
    }

    /**
     * @param objectids the objectids to set
     */
    public void setObjectids(int objectids) {
        this.objectids = objectids;
    }

    /**
     * @return the valueObj
     */
    public Object getValueObj() {
        return valueObj;
    }

    /**
     * @param valueObj the valueObj to set
     */
    public void defineValueObj(Serializable valueObj) {
        this.valueObj = valueObj;
        setClassname(valueObj.getClass().getCanonicalName());
        __getValue();//generate xml

    }
}
