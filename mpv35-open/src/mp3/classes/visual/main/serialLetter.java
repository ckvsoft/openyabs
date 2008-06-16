/*
 * serialLetter.java
 *
 * Created on 14. Februar 2008, 18:46
 */
package mp3.classes.visual.main;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import mp3.classes.interfaces.Constants;
import mp3.classes.interfaces.ProtectedStrings;
import mp3.classes.layer.Popup;
import mp3.classes.layer.QueryClass;
import mp3.classes.objects.eur.Customer;
import mp3.classes.objects.pdf.*;
import mp3.classes.utils.CompCenter;
import mp3.classes.utils.Formater;
import mp3.classes.utils.Log;

/**
 *
 * @author  anti43
 */
public class serialLetter extends javax.swing.JFrame implements ProtectedStrings {

    private static ArrayList customers;
    private static JTable jtable1;
    private static serialLetter f;

    /** Creates new form serialLetter */
    public serialLetter() {
        initComponents();


        Formater.stripFirst(jTable1);

        jTextField2.setText(Constants.HOME + ProtectedStrings.SEP + PROG_NAME + SEP + TEMPLATES_DIR + SEP + "serienbrief.pdf");

        new CompCenter(this);
        this.setVisible(rootPaneCheckingEnabled);
    }

    public static serialLetter instanceOf() {

        if (f == null) {
            f = new serialLetter();
        }
        f.setVisible(true);
        return f;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jCheckBox2 = new javax.swing.JCheckBox();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MP Serienbrief");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Serienbrief verfassen"));

        jLabel1.setText("Empf�nger:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "K-Nummer", "Firma", "Senden an"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Tip: Minimieren Sie dieses Fenster und f�gen Kunden aus der Hauptkundenansicht mit der Schaltfl�che \"Serienbrief\" hinzu.");

        jLabel3.setText("Anrede:");

        jCheckBox1.setText("Alle Kunden im Kundenregister");

        jLabel4.setText("Text:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jButton1.setText("Drucken");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Beenden");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Template");

        jButton3.setText("...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("Autom. Anrede und Name einf�gen");

        jProgressBar1.setStringPainted(true);
        jProgressBar1.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox1))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox2))
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jCheckBox2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        flush();
       f.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    public void addCustomer(Customer c) {



        Object[] o = new Object[]{c.getId(), c.getKundennummer(), c.getFirma(), true};
        DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
        m.addRow(o);

    }

    private void flush() {

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
            "id", "K-Nummer", "Firma", "Senden an"
        }) {

            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, true
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

 
        serialLetter.Task task = new Task(this);

        task.execute();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser fc = new JFileChooser(System.getProperty("user.home"));
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int rc = fc.showDialog(null, "Template w�hlen");
        if (rc == JFileChooser.APPROVE_OPTION) {
            jTextField2.setText(fc.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    public javax.swing.JCheckBox jCheckBox1;
    public javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jTable1;
    public javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextField jTextField1;
    public javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
    class Task extends SwingWorker<Void, Void> {

        private serialLetter thisa;

        /*
         * Main task. Executed in background thread.
         */
        public Task(serialLetter thisa) {

            this.thisa = thisa;
        }

        public Void doInBackground() {


            thisa.setCursor(new Cursor(Cursor.WAIT_CURSOR));

            thisa.jProgressBar1.setMinimum(0);

            File file = new File(thisa.jTextField2.getText());
            boolean named = false;
            if (thisa.jCheckBox2.isSelected()) {
                named = true;
            }

            if (!thisa.jCheckBox1.isSelected()) {
                DefaultTableModel m = (DefaultTableModel) thisa.jTable1.getModel();



                if (file.exists() && file.getName().endsWith(".pdf")) {

                    thisa.jProgressBar1.setMaximum(m.getRowCount());
                    thisa.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                    try {

                        for (int i = 0; i < m.getRowCount(); i++) {


                            new SerialPDF(new Customer(QueryClass.instanceOf(), (String) m.getValueAt(i, 0)), file.getAbsoluteFile(), thisa.jTextField1.getText(), thisa.jTextArea1.getText(), named).print();

                            thisa.jProgressBar1.setValue(i);
                        }
                        thisa.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    } catch (Exception exception) {
                        thisa.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        Log.Debug("Drucken fehlgeschlagen. Drucker eingeschaltet?");
                        Popup.notice("Drucken fehlgeschlagen. Drucker eingeschaltet?");
                    }

                } else {

                    Popup.notice("Sie m�ssen ein Template ausw�hlen!");


                }

            } else {

                Customer c = new Customer(QueryClass.instanceOf());

                ArrayList arr = c.getAllCustomers();

                thisa.jProgressBar1.setMaximum(arr.size());


                thisa.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                try {

                    for (int i = 0; i < arr.size(); i++) {


                        new SerialPDF((Customer) arr.get(i), file.getAbsoluteFile(), thisa.jTextField1.getText(), thisa.jTextArea1.getText(), named).print();

                        thisa.jProgressBar1.setValue(i);
                    }
                    thisa.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                } catch (Exception exception) {
                    thisa.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    Log.Debug("Drucken fehlgeschlagen. Drucker eingeschaltet?");
                    Popup.notice("Drucken fehlgeschlagen. Drucker eingeschaltet?");
                }

            }

            thisa.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            thisa.jProgressBar1.setValue(0);
            

            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            Log.Debug("Printing finished.",true);
            thisa.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        }
    }
}


