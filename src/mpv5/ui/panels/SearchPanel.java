/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SearchPanel.java
 *
 * Created on Nov 30, 2008, 6:16:09 PM
 */
package mpv5.ui.panels;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;
import mpv5.db.common.Context;

import mpv5.db.common.DatabaseObject;
import mpv5.db.common.DatabaseSearch;
import mpv5.db.common.NodataFoundException;
import mpv5.db.common.QueryHandler;
import mpv5.db.common.ReturnValue;
import mpv5.db.objects.User;
import mpv5.db.objects.ValueProperty;
import mpv5.logging.Log;
import mpv5.ui.misc.MPTable;
import mpv5.utils.models.MPTableModel;
import mpv5.utils.tables.Selection;
import mpv5.utils.tables.TableFormat;
import mpv5.ui.misc.TableViewPersistenceHandler;
import mpv5.usermanagement.MPSecurityManager;
import mpv5.utils.arrays.ArrayUtilities;

/**
 *
 * 
 */
public class SearchPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private Context context;
    private DataPanel panel;
    private java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle();
    private String sf;
    private DatabaseSearch d;

    /** Creates new form SearchPanel */
    public SearchPanel() {
        initComponents();
        TableFormat.hideHeader(lookupResultTable);
        ((MPTable) lookupResultTable).setPersistanceHandler(new TableViewPersistenceHandler((MPTable) lookupResultTable, this));
    }

    public SearchPanel(Context context, DataPanel panel) {
        this();
        setName("searchpanel");
        this.validate();
        this.context = context;
        this.panel = panel;
    }

    /**
     * Reload the search result
     */
    public void refresh() {
//        search(lastneedle);
    }
//
//    public void setContextOwner(DatabaseObject object) {
//        context.setOwner(object);
//        refresh();
//    }

    /** This me4thod is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchfields = new javax.swing.JPanel();
        textfield = new javax.swing.JTextField();
        results = new javax.swing.JPanel();
        resultsscrollpane = new javax.swing.JScrollPane();
        lookupResultTable = new  mpv5.ui.misc.MPTable(this) {
            public Component prepareRenderer(TableCellRenderer renderer,
                int rowIndex, int vColIndex) {
                Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
                if (c instanceof JComponent) {
                    JComponent jc = (JComponent)c;
                    jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));
                }
                return c;
            }
        };

        setName("Form"); // NOI18N

        java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle(); // NOI18N
        searchfields.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SearchPanel.searchfields.border.title"))); // NOI18N
        searchfields.setName("searchfields"); // NOI18N
        searchfields.setLayout(new javax.swing.BoxLayout(searchfields, javax.swing.BoxLayout.LINE_AXIS));

        textfield.setText(bundle.getString("SearchPanel.textfield.text")); // NOI18N
        textfield.setName("textfield"); // NOI18N
        textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldActionPerformed(evt);
            }
        });
        textfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textfieldKeyTyped(evt);
            }
        });
        searchfields.add(textfield);

        results.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SearchPanel.results.border.title"))); // NOI18N
        results.setName("results"); // NOI18N

        resultsscrollpane.setName("resultsscrollpane"); // NOI18N

        lookupResultTable.setAutoCreateRowSorter(true);
        lookupResultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        lookupResultTable.setName("lookupResultTable"); // NOI18N
        lookupResultTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lookupResultTable.setShowVerticalLines(false);
        lookupResultTable.setSurrendersFocusOnKeystroke(true);
        lookupResultTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lookupResultTableMouseClicked(evt);
            }
        });
        lookupResultTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lookupResultTableKeyTyped(evt);
            }
        });
        resultsscrollpane.setViewportView(lookupResultTable);

        javax.swing.GroupLayout resultsLayout = new javax.swing.GroupLayout(results);
        results.setLayout(resultsLayout);
        resultsLayout.setHorizontalGroup(
            resultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resultsscrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
        );
        resultsLayout.setVerticalGroup(
            resultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resultsscrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(results, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(searchfields, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(searchfields, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(results, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private synchronized void search(final String value) {
        if (sf == null) {
            sf = "%%tablename%%.ids, %%tablename%%.cname";
        }
        if (d == null) {
            d = new DatabaseSearch(context, 500);
        }

        if (this.isShowing()) {

            Runnable runnable = new Runnable() {

                @Override
                public void run() {

                    Log.Debug(this, "Search parameter: " + value);
                    Set<Integer> data = new TreeSet<Integer>();

                    if (context.equals(Context.getItem())) {
                        for (Integer s : new DatabaseSearch(Context.getItem(), 50).searchObjectIdsFor(value)) {
                            data.add(s);
                        }

                        try {
                            String subitemids = "0";
                            for (Integer s : new DatabaseSearch(Context.getSubItem(), 50).searchObjectIdsFor(value)) {
                                subitemids = s + "," + subitemids;
                            }

                            Object[] sdata = QueryHandler.instanceOf().clone(context).freeQuery("select itemsids from subitems where ids in(" + subitemids + ")", MPSecurityManager.VIEW, null).getFirstColumn();
                            if (sdata != null) {
                                for (int i = 0; i < sdata.length; i++) {
                                    try {
                                        data.add(Integer.valueOf(sdata[i].toString()));
                                    } catch (NumberFormatException numberFormatException) {
                                        Log.Debug(numberFormatException);
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            Log.Debug(this, ex);
                        }

                        try {
                            String contactsids = "0";
                            for (Integer s : new DatabaseSearch(Context.getContact(), 50).searchObjectIdsFor(value)) {
                                contactsids = s + "," + contactsids;
                            }
                            Object[] sdata = QueryHandler.instanceOf().clone(context).freeQuery("select ids from items where contactsids in(" + contactsids + ")", MPSecurityManager.VIEW, null).getFirstColumn();
                            if (sdata != null) {
                                for (int i = 0; i < sdata.length; i++) {
                                    try {
                                        data.add(Integer.valueOf(sdata[i].toString()));
                                    } catch (NumberFormatException numberFormatException) {
                                        Log.Debug(numberFormatException);
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            Log.Debug(this, ex);
                        }
                    } else {
                        for (Integer s : new DatabaseSearch(context, 50).searchObjectIdsFor(value)) {
                            data.add(s);
                        }
                    }
                    String dboids = "0";
                    for (Integer id : data) {
                        dboids = id + "," + dboids;
                    }
                    try {
                        Object[][] res = QueryHandler.instanceOf().clone(context).freeQuery("select " + sf + " from %%tablename%% where ids in (" + dboids + ")", MPSecurityManager.VIEW, null).getData();
                        if (res != null) {
                            lookupResultTable.setModel(new MPTableModel(res));
                        }
                    } catch (Exception ex) {
                        Log.Debug(ex);
                    }

                    TableFormat.hideHeader(lookupResultTable);
                    TableFormat.makeUneditable(lookupResultTable);
                    TableFormat.stripColumns(lookupResultTable, new int[]{0, 4, 5, 6, 7, 8, 9});
                }
            };
            Log.Debug(this, "Starting search..");
            SwingUtilities.invokeLater(runnable);
        }

    }

    private void lookupResultTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lookupResultTableMouseClicked
        Selection sel = new Selection(lookupResultTable);
        if (sel.checkID()) {
            try {
                panel.setDataOwner(DatabaseObject.getObject(context, sel.getId()), true);
            } catch (NodataFoundException ex) {
                Log.Debug(ex);
                User.getCurrentUser().getLayoutProperties().clear();
                try {
                    ValueProperty.deleteProperty(User.getCurrentUser(), "layoutinfo");
                } catch (Exception e) {
                    Log.Debug(ex);
                }

//                mpv5.YabsViewProxy.instance().resetTables();
            }
        }
    }//GEN-LAST:event_lookupResultTableMouseClicked

    private void lookupResultTableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lookupResultTableKeyTyped
    }//GEN-LAST:event_lookupResultTableKeyTyped

    private void textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldActionPerformed
        search(textfield.getText());
    }//GEN-LAST:event_textfieldActionPerformed

    private void textfieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfieldKeyTyped
    }//GEN-LAST:event_textfieldKeyTyped
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable lookupResultTable;
    private javax.swing.JPanel results;
    private javax.swing.JScrollPane resultsscrollpane;
    private javax.swing.JPanel searchfields;
    private javax.swing.JTextField textfield;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the context
     */
    public Context getContext() {
        return context;
    }

    public void search() {
    }

    /**
     * @param context the context to set
     */
    public void setContext(Context context) {
        this.context = context;
        this.d = null;
        this.sf = null;
    }
}
