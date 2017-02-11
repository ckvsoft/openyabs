/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpv5.ui.beans;

import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;

/**
 *
 * @author dev
 */
public class BigDropDownButton extends JLayeredPane {

    private static final long serialVersionUID = 4828760647031992592L;

    private String _label;
    
    /**
     * Creates new form DropDownButton
     */
    public BigDropDownButton() {
        initComponents();
    }
    
    /**
     * This method is called from within the constructor to
     * initialize the form. WARNING: Do NOT modify this
     * code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setToolTipText("");
        setMaximumSize(new java.awt.Dimension(104, 50));
        setMinimumSize(new java.awt.Dimension(104, 50));
        setSize(new java.awt.Dimension(104, 50));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/22/player_playlist.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setIconTextGap(0);
        jButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton2.setMaximumSize(new java.awt.Dimension(32, 60));
        jButton2.setMinimumSize(new java.awt.Dimension(32, 60));
        jButton2.setOpaque(false);
        jButton2.setPreferredSize(new java.awt.Dimension(32, 60));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        setLayer(jButton2, javax.swing.JLayeredPane.PALETTE_LAYER);
        add(jButton2);
        jButton2.setBounds(80, 3, 30, 60);

        jButton1.setBorder(null);
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setIconTextGap(0);
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.setMaximumSize(new java.awt.Dimension(70, 60));
        jButton1.setMinimumSize(new java.awt.Dimension(70, 60));
        jButton1.setPreferredSize(new java.awt.Dimension(80, 60));
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add(jButton1);
        jButton1.setBounds(0, 0, 110, 60);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jPopupMenu1.show(this,this.jButton2.getX()+ this.jButton2.getSize().width, (int) (this.jButton2.getY() + Math.round(this.jButton2.getSize().height * 0.5)));
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPopupMenu jPopupMenu1;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the _label
     */
    public String get_Label() {
        return jButton1.getText();
    }

    /**
     * @param label the _label to set
     */
    public void setLabel(String label) {
        this._label = label;
        jButton1.setText(_label);
        this.setToolTipText(label);
        jButton1.setToolTipText(label);
    }
    
    public void setIcon(Icon icon) {
        jButton1.setIcon(icon);
    }

    public void addActionListener(ActionListener act) {
        jButton1.addActionListener(act);
    }
    
    @Override
    public void setEnabled(boolean enabled) {
        jButton2.setEnabled(enabled);
        jButton1.setEnabled(enabled);
    }

    public void add(Icon icon, String label, ActionListener l) {
        JMenuItem item;
        jPopupMenu1.add(item = new JMenuItem(label, icon));
        item.setHorizontalTextPosition(JMenuItem.RIGHT);
        item.addActionListener(l);
    }
}