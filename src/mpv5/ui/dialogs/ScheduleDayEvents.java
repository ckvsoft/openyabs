/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SplashScreen.java
 *
 * Created on 30.03.2009, 21:55:52
 */
package mpv5.ui.dialogs;

import java.util.ArrayList;

import java.util.Date;
import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.db.common.NodataFoundException;
import mpv5.db.objects.Schedule;
import mpv5.globals.Headers;
import mpv5.logging.Log;
import mpv5.ui.frames.MPView;
import mpv5.ui.misc.Position;
import mpv5.utils.date.DateConverter;
import mpv5.utils.models.MPTableModel;
import mpv5.utils.tables.TableFormat;

/**
 *
 *  
 */
public class ScheduleDayEvents extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    private final Date day;

    /**
     *
     * @param day
     */
    public ScheduleDayEvents(Date day) {
        initComponents();
        this.day = day;
        refresh(Schedule.getEvents(day));
        setTitle(getTitle() + " " + DateConverter.getDefDateString(day));
        new Position(this);
        setVisible(rootPaneCheckingEnabled);
    }

    /** Creates new form SplashScreen
     * @param list
     */
    public ScheduleDayEvents(ArrayList<Schedule> list) {
        initComponents();
        refresh(list);
        day = new Date();
        new Position(this);
        setVisible(rootPaneCheckingEnabled);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle();
        setTitle(bundle.getString("ScheduleDayEvents.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("ScheduleDayEvents.jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jButton2.setText(bundle.getString("ScheduleDayEvents.jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setName("jTable1"); // NOI18N
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText(bundle.getString("ScheduleDayEvents.jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() > 1) {
            DatabaseObject d = (DatabaseObject) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
            if (d != null) {
                mpv5.YabsViewProxy.instance().getIdentifierView().addTab(d);
                this.dispose();
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ScheduleDayEvent.instanceOf().setDate(day);
        ScheduleDayEvent.instanceOf().setVisible(rootPaneCheckingEnabled);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void refresh(ArrayList<Schedule> list) {
        Object[][] data = new Object[list.size()][4];

        for (int i = 0; i < list.size(); i++) {
            try {
                Schedule schedule = list.get(i);
                data[i][0] = schedule.getItem();
                data[i][1] = schedule.__getIntervalmonth();
                data[i][2] = DateConverter.getDefDateString(schedule.__getStopdate());
                try {
                    data[i][3] = DatabaseObject.getObject(Context.getUser(), schedule.__getIntaddedby());
                } catch (NodataFoundException nodataFoundException) {
                }
            } catch (NodataFoundException ex) {
                Log.Debug(this, ex);
            }
        }
        jTable1.setModel(new MPTableModel(data, Headers.SCHEDULE_LIST));
//        TableFormat.stripFirstColumn(jTable1);
        TableFormat.format(jTable1, 1, 100);
    }
}
