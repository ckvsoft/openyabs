/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpv5.db.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import mpv5.globals.Messages;
import mpv5.logging.Log;
import mpv5.ui.panels.DataPanel;
import mpv5.ui.dialogs.Popup;
import mpv5.utils.date.DateConverter;

/**
 *
 * @author Administrator
 */
public abstract class DatabaseObject {

    public Context context = new Context(this);
    public Integer ids = 0;
    public boolean isSaved = false;
    public boolean readonly = false;
    public boolean active = true;

    public abstract String __getCName();

    public abstract void setCName(String name);

    public Integer __getIDS() {
        return ids;
    }

    public void setIDS(int ids) {
        this.ids = ids;
    }

    public ArrayList<Method> setVars() {
        ArrayList<Method> list = new ArrayList<Method>();
        for (int i = 0; i < this.getClass().getMethods().length; i++) {
            if (this.getClass().getMethods()[i].getName().startsWith("set") &&
                    !this.getClass().getMethods()[i].getName().startsWith("setVars") &&
                    !this.getClass().getMethods()[i].getName().startsWith("setPanelData") &&
                    !this.getClass().getMethods()[i].getName().startsWith("getDbID") &&
                    !this.getClass().getMethods()[i].getName().startsWith("getObject")) {
                list.add(this.getClass().getMethods()[i]);
            }
        }
        return list;
    }

    public ArrayList<Method> getVars() {
        ArrayList<Method> list = new ArrayList<Method>();
        for (int i = 0; i < this.getClass().getMethods().length; i++) {
            if (this.getClass().getMethods()[i].getName().startsWith("__get")){
                list.add(this.getClass().getMethods()[i]);
            }
        }
        return list;
    }

    public boolean save() {

        if (__getCName()!=null && __getCName().length()>0) {
            try {
                if (ids <= 0) {
                    Log.Debug(this, "Inserting new dataset:");
                    ids = QueryHandler.instanceOf().clone(context).insert(collect(), this.__getCName() + Messages.ROW_INSERTED);
                } else {
                    Log.Debug(this, "Updating dataset: " + ids + " within context '" + context +"'");
                    QueryHandler.instanceOf().clone(context).update(collect(), new String[]{"ids", String.valueOf(ids), ""}, this.__getCName() + Messages.ROW_UPDATED);
                }
                return true;
            } catch (Exception e) {
                Log.Debug(this, e);
                e.printStackTrace();
                return false;
            }
        } else {
            Popup.notice(Messages.CNAME_CANNOT_BE_NULL);
            return false;
        }
    }

    public boolean reset() {
        return false;
    }

    public boolean delete() {
        return false;
    }

    public String getDbID() {
        return context.getDbIdentity();
    }

    private String[] collect() {

        String left = "";
        String right = "";
        Object tempval;
//        int intval = 0;
        String stringval = "";

        for (int i = 0; i < this.getClass().getMethods().length; i++) {
            if (this.getClass().getMethods()[i].getName().startsWith("__get") && !this.getClass().getMethods()[i].getName().endsWith("IDS")) {
                try {
                    left += this.getClass().getMethods()[i].getName().toLowerCase().substring(5, this.getClass().getMethods()[i].getName().length()) + ",";
                    tempval = this.getClass().getMethods()[i].invoke(this, (Object[]) null);
                    System.out.println(tempval.getClass().getName() + " : " + this.getClass().getMethods()[i].getName() + " ? " +  tempval);
                    if (tempval.getClass().isInstance(new String())) {
                        stringval = "(;;2#4#1#1#8#0#;;)" + tempval + "(;;2#4#1#1#8#0#;;)";
                    } else if (tempval.getClass().isInstance(true)) {
                        boolean c = (Boolean) tempval;
                        if (c) {
                            stringval = "1";
                        } else {
                            stringval = "0";
                        }
                    } else  if (tempval.getClass().isInstance(new Date())) {
                          stringval = "(;;2#4#1#1#8#0#;;)" + DateConverter.getSQLDateString((Date)tempval) + "(;;2#4#1#1#8#0#;;)";
                    } else  if (tempval.getClass().isInstance(new Integer(0))) {
                          stringval = String.valueOf(tempval);
                    } else  if (tempval.getClass().isInstance(new Float(0f))) {
                          stringval = String.valueOf(tempval);
                    } else  if (tempval.getClass().isInstance(new Double(0d))) {
                          stringval = String.valueOf(tempval);
                    }
                    right += stringval + "(;;,;;)";
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            else if (this.getClass().getMethods()[i].getName().startsWith("is")) {
//                try {
//                    left += this.getClass().getMethods()[i].getName().substring(2, this.getClass().getMethods()[i].getName().length()) + ",";
//                    tempval = this.getClass().getMethods()[i].invoke(this, (Object[]) null);
//                    if (tempval.getClass().isInstance(boolean.class)) {
//                        if (((Boolean) tempval)) {
//                            intval = 1;
//                        }
//                    }
//                    right += "(;;2#4#1#1#8#0#;;)" + intval + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
//                } catch (IllegalAccessException ex) {
//                    Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IllegalArgumentException ex) {
//                    Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (InvocationTargetException ex) {
//                    Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }

        return new String[]{left.substring(0, left.length() - 1), right.substring(0, right.length() - 7), ""};
    }

    public void getPanelData(DataPanel source) {
        source.collectData();
        ArrayList<Method> vars = setVars();
        for (int i = 0; i < vars.size(); i++) {
            try {
//                System.out.println(vars.get(i).getName().toLowerCase().substring(3, vars.get(i).getName().length()) + "_ : " + data.getClass().getField(vars.get(i).getName().toLowerCase().substring(3, vars.get(i).getName().length()) + "_").getType().getName());
                vars.get(i).invoke(this, source.getClass().getField(vars.get(i).getName().toLowerCase().substring(3, vars.get(i).getName().length()) + "_").get(source));
            } catch (Exception n) {
                System.out.println(n.getCause());
                n.printStackTrace();

            }
        }
    }

    public void setPanelData(DataPanel target) {

        ArrayList<Method> vars = getVars();

        for (int i = 0; i < vars.size(); i++) {
            try {
//                System.out.println(vars.get(i).getName());
                target.getClass().getField(vars.get(i).getName().toLowerCase().substring(5, vars.get(i).getName().length()) + "_").set(target, vars.get(i).invoke(this,new Object[0]));
            } catch (Exception n) {
                System.out.println(n.getCause());
                n.printStackTrace();
            }
        }
    }
    
    
    /**
     *
     * @return A list containing pairs of VARNAME and their VALUE of this Databaseobject,
     * those which return in getVars(), as two-fields String-Array
     * Example: new String[]{CName, Michael}
     */
    public ArrayList<String[]> getValues() {
        ArrayList<Method> vars = getVars();
        ArrayList<String[]> vals = new ArrayList<String[]>();

        for (int i = 0; i < vars.size(); i++) {
            try {
                vals.add(new String[]{vars.get(i).getName().substring(5, vars.get(i).getName().length()),
                String.valueOf(vars.get(i).invoke(this,new Object[0]))});
               
            } catch (Exception n) {
                System.out.println(n.getCause());
                n.printStackTrace();
            }
        }

        return vals;
    }

    public static DatabaseObject getObject(Context context, int id) {
     
        try {
            Object obj = context.getIdentityClass().newInstance();
            ((DatabaseObject) obj).fetchDataOf(id);
            return (DatabaseObject) obj;
        } catch (InstantiationException ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }

    public void fetchDataOf(int id) {
        explode(QueryHandler.instanceOf().clone(context).select(id));
    }

    private void explode(ReturnValue select) {
        ArrayList<Method> vars = setVars();

        for (int i = 0; i < select.getData().length; i++) {
            for (int j = 0; j < select.getData()[i].length; j++) {
                String name = select.getColumnnames()[j].toLowerCase();

                for (int k = 0; k < vars.size(); k++) {
                    if (vars.get(k).getName().toLowerCase().substring(3).equals(name)) {
                        Log.Debug(this, name + " ?? : " + vars.get(k).getName() +" = " + select.getData()[i][j]);
                        try {
                            if (name.startsWith("is")) {
                                if (String.valueOf(select.getData()[i][j]).equals("1")) {
                                    vars.get(k).invoke(this, new Object[]{true});
                                } else {
                                    vars.get(k).invoke(this, new Object[]{false});
                                }
                            } else if (name.endsWith("uid") || name.equals("ids")) {
                                vars.get(k).invoke(this, new Object[]{Integer.valueOf(String.valueOf(select.getData()[i][j]))});
                            } else if (name.startsWith("date")) {
                                vars.get(k).invoke(this, new Object[]{DateConverter.getDate(String.valueOf(select.getData()[i][j]))});
                            } else {
                                vars.get(k).invoke(this, new Object[]{String.valueOf(select.getData()[i][j])});
                            }
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalArgumentException ex) {
                            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InvocationTargetException ex) {
                            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }
}
