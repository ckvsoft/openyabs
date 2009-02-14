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
/*
 * MPBabelFish.java
 *
 * Created on 01.02.2009, 17:33:56
 */
package mpv5.ui.frames;

import javax.swing.DefaultComboBoxModel;
import mpv5.db.common.NodataFoundException;
import mpv5.globals.Headers;
import mpv5.globals.Messages;
import mpv5.resources.languages.LanguageManager;
import mpv5.ui.dialogs.DialogForFile;
import com.google.api.translate.*;
import java.awt.Cursor;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import mpv5.logging.Log;
import mpv5.ui.dialogs.Popup;
import mpv5.ui.parents.Position;
import mpv5.usermanagement.MPSecurityManager;
import mpv5.utils.arrays.ArrayUtils;
import mpv5.utils.models.MPComboBoxModelItem;
import mpv5.utils.models.MPTableModel;
import mpv5.utils.tables.DataModelUtils;
import mpv5.utils.tables.ExcelAdapter;
import mpv5.utils.ui.TextFieldUtils;

/**
 *
 * @author Andreas
 */
public class MPBabelFish extends javax.swing.JFrame {

    /** Creates new form MPBabelFish */
    public MPBabelFish() {
        initComponents();
        new ExcelAdapter(data);
        setLanguageSelection();
        setToolBar();
        new Position(this);
        setVisible(rootPaneCheckingEnabled);
    }

    private void setLanguage() {
//        data.setModel(new DefaultTableModel());
        new Job2(this).execute();
    }

    private void setLanguageSelection() {
        languages.setModel(LanguageManager.getLanguagesAsComboBoxModel());
        languages.setSelectedIndex(MPComboBoxModelItem.getItemID(MPV5View.getUser().__getLanguage(),
                languages.getModel()));

        setLanguage();
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        data = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        languages = new javax.swing.JComboBox();
        langName = new mpv5.ui.beans.LabeledTextField();
        jLabel7 = new javax.swing.JLabel();
        GooogleTranslator = new javax.swing.JToolBar();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jLabel3 = new javax.swing.JLabel();
        from = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        to = new javax.swing.JComboBox();
        translate = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("mpv5/resources/languages/Panels"); // NOI18N
        setTitle(bundle.getString("MPBabelFish.title_1")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("MPBabelFish.jPanel1.border.title_1"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/babelfish.png"))); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        data.setAutoCreateRowSorter(true);
        data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        data.setColumnSelectionAllowed(true);
        data.setDoubleBuffered(true);
        data.setDragEnabled(true);
        data.setName("data"); // NOI18N
        data.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(data);
        data.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel2.setText(bundle.getString("MPBabelFish.jLabel2.text_1")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        languages.setName("languages"); // NOI18N
        languages.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                languagesMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                languagesMouseExited(evt);
            }
        });
        languages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                languagesActionPerformed(evt);
            }
        });

        langName.set_Label(bundle.getString("MPBabelFish.langName._Label")); // NOI18N
        langName.setName("langName"); // NOI18N

        jLabel7.setText(bundle.getString("MPBabelFish.jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        GooogleTranslator.setRollover(true);
        GooogleTranslator.setName("GooogleTranslator"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText(bundle.getString("MPBabelFish.jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        GooogleTranslator.add(jLabel5);

        jSeparator1.setName("jSeparator1"); // NOI18N
        GooogleTranslator.add(jSeparator1);

        jLabel3.setText(bundle.getString("MPBabelFish.jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        GooogleTranslator.add(jLabel3);

        from.setName("from"); // NOI18N
        GooogleTranslator.add(from);

        jLabel4.setText(bundle.getString("MPBabelFish.jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        GooogleTranslator.add(jLabel4);

        to.setAutoscrolls(true);
        to.setName("to"); // NOI18N
        GooogleTranslator.add(to);

        translate.setText(bundle.getString("MPBabelFish.translate.text")); // NOI18N
        translate.setFocusable(false);
        translate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        translate.setName("translate"); // NOI18N
        translate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        translate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                translateActionPerformed(evt);
            }
        });
        GooogleTranslator.add(translate);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(languages, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(langName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
            .addComponent(GooogleTranslator, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(languages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(langName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GooogleTranslator, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuBar1.setName("jMenuBar1"); // NOI18N

        jMenu1.setText(bundle.getString("MPBabelFish.jMenu1.text_1")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N

        jMenuItem2.setText(bundle.getString("MPBabelFish.jMenuItem2.text_1")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setText(bundle.getString("MPBabelFish.jMenuItem1.text_1")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText(bundle.getString("MPBabelFish.jMenuItem3.text_1")); // NOI18N
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        if (MPSecurityManager.checkAdminAccess()) {
            if (langName.hasText()) {
//            MPTableModel mpdel = DataModelUtils.getModelCopy(data);
                DataModelUtils.removeColumn(data, 1);
                LanguageManager.importLanguage(langName.get_Text(), DataModelUtils.tableModelToFile(data, "="));
//            data.setModel(mpdel);
                setLanguageSelection();
            } else {
                TextFieldUtils.blinkerRed(langName.getTextField());
            }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        DialogForFile dialog = new DialogForFile(DialogForFile.FILES_ONLY, "language.mp");
//        MPTableModel mpdel = DataModelUtils.getModelCopy(data);
        DataModelUtils.removeColumn(data, 1);
        dialog.writeFile(DataModelUtils.tableModelToFile(data, "="));
//        data.setModel(mpdel);
        setLanguageSelection();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void translateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_translateActionPerformed
        new Job(this).execute();
    }//GEN-LAST:event_translateActionPerformed

    private void languagesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_languagesMouseExited
    }//GEN-LAST:event_languagesMouseExited

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if (MPSecurityManager.checkAdminAccess()) {
            if (Popup.Y_N_dialog(Messages.REALLY_WIPE + ": " + ((MPComboBoxModelItem) languages.getSelectedItem()).getName())) {
                try {
                    LanguageManager.removeLanguage(((MPComboBoxModelItem) languages.getSelectedItem()).getId());
                } catch (NodataFoundException ex) {
                    Log.Debug(this, ex);
                }
            }
        }

        setLanguageSelection();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void languagesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_languagesMouseClicked
    }//GEN-LAST:event_languagesMouseClicked

    private void languagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_languagesActionPerformed
        setLanguage();
    }//GEN-LAST:event_languagesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar GooogleTranslator;
    private javax.swing.JTable data;
    private javax.swing.JComboBox from;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private mpv5.ui.beans.LabeledTextField langName;
    private javax.swing.JComboBox languages;
    private javax.swing.JComboBox to;
    private javax.swing.JButton translate;
    // End of variables declaration//GEN-END:variables

    private void setToolBar() {
        from.setModel(new DefaultComboBoxModel(Language.validLanguages.toArray()));
        to.setModel(new DefaultComboBoxModel(Language.validLanguages.toArray()));
    }

    class Job extends SwingWorker<Object, Object> {

        private MPBabelFish parent;

        private Job(MPBabelFish aThis) {
            this.parent = aThis;
        }

        @Override
        public Object doInBackground() {
            parent.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            String[] dat = ArrayUtils.SmallObjectToStringArray(DataModelUtils.getColumnAsArray(data, 1));
            String[] translated = new String[dat.length];
            MPV5View.setProgressMaximumValue(dat.length);
            for (int i = 0; i < dat.length; i++) {

                String string = dat[i];
                try {
                    translated[i] = Translate.translate(string, from.getSelectedItem().toString(), to.getSelectedItem().toString());
                    MPV5View.setProgressValue(i + 1);
                } catch (Exception ex) {
                    Log.Debug(this, ex);
                }
            }
            DataModelUtils.replaceColumn(data, 2, translated);
            return null;
        }

        @Override
        public void done() {
            parent.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            MPV5View.setProgressReset();
            Popup.notice(Messages.DONE);
        }
    }

    class Job2 extends SwingWorker<Object, Object> {

        private MPBabelFish parent;

        private Job2(MPBabelFish aThis) {
            this.parent = aThis;
        }

        @Override
        public Object doInBackground() {
            parent.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            
            data.setModel(new MPTableModel(
                    new Class[]{String.class, String.class, String.class},
                    new boolean[]{false, true, true},
                    LanguageManager.getEditorModel(((MPComboBoxModelItem) languages.getSelectedItem()).getId()),
                    Headers.BABELFISH));
            data.validate();
//            langName.set_Text((((MPComboBoxModelItem) languages.getSelectedItem()).getName() + " (" + MPV5View.getUser().getName() + ")"));
            parent.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            return null;
        }

        @Override
        public void done() {
            parent.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
}
