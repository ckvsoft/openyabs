/*
 * 
 *  *  This file is part of MP by anti43 /GPL.
 *  *  
 *  *      MP is free software: you can redistribute it and/or modify
 *  *      it under the terms of the GNU General Public License as published by
 *  *      the Free Software Foundation, either version 3 of the License, or
 *  *      (at your option) any later version.
 *  *  
 *  *      MP is distributed in the hope that it will be useful,
 *  *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  *      GNU General Public License for more details.
 *  *  
 *  *      You should have received a copy of the GNU General Public License
 *  *      along with MP.  If not, see <http://www.gnu.org/licenses/>.
 *  
 */
package mp4.panels.misc;

import mp4.utils.ui.Position;
import java.awt.Component;

/*
 * mpSplashScreen.java
 *
 * Created on 24. Oktober 2007, 06:53
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import mp4.globals.Constants;
/**
 *
 * @author  anti43
 */
public class SplashScreen extends javax.swing.JFrame implements Runnable {
    private Component comp = null;

    /** Creates new form mpSplashScreen
     * @param comp 
     */
    public SplashScreen(Component comp) {
        this.comp =comp;
        
        Thread t=new Thread(this);
        
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        initComponents();
        
        this.setTitle("Starte MP...");
        
        new Position(this);
        this.setVisible(true);

        jProgressBar1.setIndeterminate(true);
        
        t.start();

    }

    public SplashScreen(String string) {
        
        Thread t=new Thread(this);
        this.setUndecorated(true);

        initComponents();
        this.setTitle("Starte MP...");
        new Position(this);
        this.setVisible(true);
        
        jProgressBar1.setIndeterminate(true);
        message.setText(string);
        t.start();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        message = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        setAlwaysOnTop(false);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 248, 220));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12));
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Lade MP...");

        message.setForeground(new java.awt.Color(0, 0, 0));

//        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bilder/babytux.png"))); // NOI18N

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText(Constants.TITLE);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jPanel1Layout.
                createSequentialGroup().add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).
                add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.
                createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(message, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE).add(jProgressBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE).add(jPanel1Layout.createSequentialGroup().add(jLabel1).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 244, Short.MAX_VALUE).add(jLabel3)))).add(jPanel1Layout.createSequentialGroup().add(54, 54, 54).add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 180, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))).addContainerGap()));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).
                add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.
                createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(jLabel1).add(jLabel3)).
                addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 62, Short.MAX_VALUE).add(jLabel2, 
                org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).
                add(35, 35, 35).add(message, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, 
                org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(18, 18, 18).add(jProgressBar1, 
                org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 
                org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addContainerGap()));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>                        
    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel message;

    public String getMessage() {
        return message.getText();
    }

    public void setMessage(String message) {
          this.message.setText(message);
    }

    public void run() {
        while(true){
            try {
                if (getComp() != null && getComp().isVisible()) {                                                            
                    this.dispose();
                }
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }

    public Component getComp() {
        return comp;
    }

    public void setComp(Component comp) {
        this.comp = comp;
    }
                   
}
