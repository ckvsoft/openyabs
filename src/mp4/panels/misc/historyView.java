/*
 * customers.java
 *
 * Created on 28. Dezember 2007, 19:17
 */
package mp4.panels.misc;

import java.util.Formattable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import mp4.items.People;
import mp4.items.Product;
import mp4.items.Steuersatz;
import mp4.items.handler.HistoryHandler;
import javax.swing.table.DefaultTableModel;
import mp4.interfaces.panelInterface;
import mp4.frames.mainframe;
import mp4.utils.tabellen.TableFormat;

/**
 *
 * @author  anti43
 */
public class historyView extends mp4.panels.misc.commonPanel implements Runnable, panelInterface {
    private static final long serialVersionUID = 7605611553087473038L;

    private String[][] liste;
    private String[] header;
    private mainframe mainframe;
    private Thread t;

    /** 
     * Creates new form customers
     * @param frame 
     */
    public historyView(mainframe frame) {
        initComponents();
        mainframe = frame;
        liste = HistoryHandler.getHistory();
        header = new String[]{"Aktion", "Beschreibung", "Datum", "Benutzer"};
        historytable.setModel(new DefaultTableModel(liste, header));
        resizeTable();
        t = new Thread(this);
        t.setPriority(Thread.MIN_PRIORITY);
        t.start();
    }

    private JTable getHistoryTable() {
        return historytable;
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
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        historytable = new javax.swing.JTable();

        setBackground(new java.awt.Color(204, 204, 255));

        jPanel1.setBackground(new java.awt.Color(227, 219, 202));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 14));
        jLabel1.setText("Verlauf");

        jButton6.setText("Aktualisieren");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addComponent(jButton6))
        );

        historytable.setAutoCreateRowSorter(true);
        historytable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        historytable.setDoubleBuffered(true);
        jScrollPane1.setViewportView(historytable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     * @param current
     */
    private void jButton6ActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        HistoryHandler.validate();
        liste = HistoryHandler.getHistory();
        historytable.setModel(new DefaultTableModel(liste, header));
        resizeTable();

    }//GEN-LAST:event_jButton6ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable historytable;
    public javax.swing.JButton jButton6;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public void run() {
        while (true) {
            try {
                HistoryHandler.validate();
                liste = HistoryHandler.getHistory();
                historytable.setModel(new DefaultTableModel(liste, header));
                resizeTable();
                Thread.sleep(50000);
            } catch (InterruptedException ex) {
              
            }
        }
    }

    private void resizeTable() {
        
       TableFormat.resizeCols(getHistoryTable(), new Integer[]{130,null,100,100}, true);
        
    }

    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void setContact(People contact) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public People getContact() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void switchTab(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTax(Steuersatz steuersatz) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}