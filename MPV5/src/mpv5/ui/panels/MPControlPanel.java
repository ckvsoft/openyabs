package mpv5.ui.panels;

import de.muntjak.tinylookandfeel.TinyLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import mpv5.Main;
import mpv5.db.common.Context;
import mpv5.globals.LocalSettings;
import mpv5.globals.Messages;
import mpv5.logging.Log;
import mpv5.i18n.LanguageManager;
import mpv5.ui.dialogs.ControlApplet;
import mpv5.ui.dialogs.Popup;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Accounts;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Fonts;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Groups;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Local;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Locale;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Plugins;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Users;
import mpv5.ui.frames.MPBabelFish;
import mpv5.ui.frames.MPV5View;
import mpv5.utils.files.FileDirectoryHandler;

/**
 *
 * @author anti43
 */
public class MPControlPanel extends javax.swing.JPanel {

    private static MPControlPanel cpanel;

    /**
     * 
     * @return
     */
    public static synchronized JComponent instanceOf() {
        if (cpanel != null) {
            return cpanel;
        } else {
            cpanel = new MPControlPanel();
            return cpanel;
        }
    }
    private Context context;

    /** Creates new form ListPanel */
    private MPControlPanel() {
        initComponents();
    }

    /** This me4thod is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttons = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        details = new javax.swing.JPanel();
        scroller = new javax.swing.JScrollPane();
        actions = new javax.swing.JPanel();

        setName("Form"); // NOI18N
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        buttons.setBackground(new java.awt.Color(255, 255, 255));
        java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle(); // NOI18N
        buttons.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("MPControlPanel.buttons.border.title"))); // NOI18N
        buttons.setAutoscrolls(true);
        buttons.setMinimumSize(new java.awt.Dimension(300, 10));
        buttons.setName("buttons"); // NOI18N
        buttons.setPreferredSize(new java.awt.Dimension(340, 200));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/internet.png"))); // NOI18N
        jButton1.setText(bundle.getString("MPControlPanel.jButton1.text")); // NOI18N
        jButton1.setToolTipText(bundle.getString("MPControlPanel.jButton1.toolTipText")); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.setContentAreaFilled(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton1.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(90, 80));
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        buttons.add(jButton1);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/locale.png"))); // NOI18N
        jButton2.setText(bundle.getString("MPControlPanel.jButton2.text")); // NOI18N
        jButton2.setToolTipText(bundle.getString("MPControlPanel.jButton2.toolTipText")); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.setContentAreaFilled(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton2.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(90, 80));
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        buttons.add(jButton2);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/looknfeel.png"))); // NOI18N
        jButton3.setText(bundle.getString("MPControlPanel.jButton3.text")); // NOI18N
        jButton3.setToolTipText(bundle.getString("MPControlPanel.jButton3.toolTipText")); // NOI18N
        jButton3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton3.setContentAreaFilled(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton3.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(90, 80));
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        buttons.add(jButton3);

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/babelfish.png"))); // NOI18N
        jButton5.setText(bundle.getString("MPControlPanel.jButton5.text")); // NOI18N
        jButton5.setToolTipText(bundle.getString("MPControlPanel.jButton5.toolTipText")); // NOI18N
        jButton5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton5.setContentAreaFilled(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton5.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton5.setName("jButton5"); // NOI18N
        jButton5.setPreferredSize(new java.awt.Dimension(90, 80));
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        buttons.add(jButton5);

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/evolution-contacts.png"))); // NOI18N
        jButton6.setText(bundle.getString("MPControlPanel.jButton6.text")); // NOI18N
        jButton6.setToolTipText(bundle.getString("MPControlPanel.jButton6.toolTipText")); // NOI18N
        jButton6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton6.setContentAreaFilled(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton6.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton6.setName("jButton6"); // NOI18N
        jButton6.setPreferredSize(new java.awt.Dimension(90, 80));
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        buttons.add(jButton6);

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/openofficeorg-20.png"))); // NOI18N
        jButton7.setText(bundle.getString("MPControlPanel.jButton7.text")); // NOI18N
        jButton7.setToolTipText(bundle.getString("MPControlPanel.jButton7.toolTipText")); // NOI18N
        jButton7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton7.setContentAreaFilled(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton7.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton7.setName("jButton7"); // NOI18N
        jButton7.setPreferredSize(new java.awt.Dimension(90, 80));
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttons.add(jButton7);

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/draw-eraser.png"))); // NOI18N
        jButton8.setText(bundle.getString("MPControlPanel.jButton8.text")); // NOI18N
        jButton8.setToolTipText(bundle.getString("MPControlPanel.jButton8.toolTipText")); // NOI18N
        jButton8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton8.setContentAreaFilled(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton8.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton8.setName("jButton8"); // NOI18N
        jButton8.setPreferredSize(new java.awt.Dimension(90, 80));
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        buttons.add(jButton8);

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/fonts.png"))); // NOI18N
        jButton9.setText(bundle.getString("MPControlPanel.jButton9.text")); // NOI18N
        jButton9.setToolTipText(bundle.getString("MPControlPanel.jButton9.toolTipText")); // NOI18N
        jButton9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton9.setContentAreaFilled(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton9.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton9.setName("jButton9"); // NOI18N
        jButton9.setPreferredSize(new java.awt.Dimension(90, 80));
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        buttons.add(jButton9);

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/flag.png"))); // NOI18N
        jButton10.setText(bundle.getString("MPControlPanel.jButton10.text")); // NOI18N
        jButton10.setToolTipText(bundle.getString("MPControlPanel.jButton10.toolTipText")); // NOI18N
        jButton10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton10.setContentAreaFilled(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton10.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton10.setName("jButton10"); // NOI18N
        jButton10.setPreferredSize(new java.awt.Dimension(90, 80));
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        buttons.add(jButton10);

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/usb.png"))); // NOI18N
        jButton11.setText(bundle.getString("MPControlPanel.jButton11.text")); // NOI18N
        jButton11.setToolTipText(bundle.getString("MPControlPanel.jButton11.toolTipText")); // NOI18N
        jButton11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton11.setContentAreaFilled(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton11.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton11.setName("jButton11"); // NOI18N
        jButton11.setPreferredSize(new java.awt.Dimension(90, 80));
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        buttons.add(jButton11);

        jButton12.setFont(new java.awt.Font("Tahoma", 0, 10));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/48/gkrellm2.png"))); // NOI18N
        jButton12.setText(bundle.getString("MPControlPanel.jButton12.text")); // NOI18N
        jButton12.setToolTipText(bundle.getString("MPControlPanel.jButton12.toolTipText")); // NOI18N
        jButton12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton12.setContentAreaFilled(false);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton12.setMaximumSize(new java.awt.Dimension(90, 90));
        jButton12.setMinimumSize(new java.awt.Dimension(90, 80));
        jButton12.setName("jButton12"); // NOI18N
        jButton12.setPreferredSize(new java.awt.Dimension(90, 80));
        jButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        buttons.add(jButton12);

        add(buttons);

        details.setBackground(new java.awt.Color(255, 255, 255));
        details.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("MPControlPanel.details.border.title"))); // NOI18N
        details.setMinimumSize(new java.awt.Dimension(300, 200));
        details.setName("details"); // NOI18N
        details.setLayout(new java.awt.BorderLayout());

        scroller.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setName("scroller"); // NOI18N
        details.add(scroller, java.awt.BorderLayout.CENTER);

        add(details);

        actions.setBackground(new java.awt.Color(255, 255, 255));
        actions.setName("actions"); // NOI18N
        actions.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        add(actions);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        openDetails(new ControlPanel_Local());

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        MPV5View.setWaiting(true);
        TinyLookAndFeel.controlPanelInstantiated = true;
        new de.muntjak.tinylookandfeel.controlpanel.ControlPanel(Main.getApplication());
        MPV5View.setWaiting(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        if (Popup.Y_N_dialog(Messages.REALLY_WIPE)) {
            FileDirectoryHandler.deleteFile(LocalSettings.getLocalFile(), false);
            Popup.notice(Messages.WIPED_LOCALSETTINGS);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        openDetails(new ControlPanel_Locale());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        MPV5View.setWaiting(true);
        new MPBabelFish();
        MPV5View.setWaiting(false);

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        openDetails(new ControlPanel_Fonts());
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        openDetails(new ControlPanel_Users());
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        openDetails(new ControlPanel_Groups());

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        openDetails(new ControlPanel_Plugins());
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        openDetails(new ControlPanel_Accounts());
    }//GEN-LAST:event_jButton12ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actions;
    private javax.swing.JPanel buttons;
    private javax.swing.JPanel details;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JScrollPane scroller;
    // End of variables declaration//GEN-END:variables

    /**
     * This method adds a button to the Properties panel, with given
     * Icon and Text. A click on the generated button creates a new instance of the given class.
     * @param icon
     * @param text
     * @param clazz
     */
    public void addShortcut(Icon icon, String text, final Class clazz) {
        JButton button = new JButton(text, icon);
        button.setToolTipText(text); // NOI18N
        button.setFont(new java.awt.Font(LocalSettings.getProperty("defaultfont"), 0, 10)); // NOI18N
        button.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        button.setContentAreaFilled(false);
        button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button.setMaximumSize(new java.awt.Dimension(90, 90));
        button.setMinimumSize(new java.awt.Dimension(90, 80));
        button.setPreferredSize(new java.awt.Dimension(90, 80));
        button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        button.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    clazz.newInstance();
                } catch (InstantiationException ex) {
                    Log.Debug(this, ex);
                } catch (IllegalAccessException ex) {
                    Log.Debug(this, ex);
                }
            }
        });

        buttons.add(button);
    }

    /**
     * This method adds a button to the Properties panel, with given
     * Icon and Text. A click on the generated button places the JPanel on the details pane.
//     * <b>Bring your own scrollpane!<b>
     * @param icon
     * @param text
     * @param panel 
     */
    public void addShortcut(Icon icon, String text, final JPanel panel) {
        JButton button = new JButton(text, icon);
        button.setToolTipText(text); // NOI18N
        button.setFont(new java.awt.Font(LocalSettings.getProperty("defaultfont"), 0, 10)); // NOI18N
        button.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        button.setContentAreaFilled(false);
        button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button.setMaximumSize(new java.awt.Dimension(90, 90));
        button.setMinimumSize(new java.awt.Dimension(90, 80));
        button.setPreferredSize(new java.awt.Dimension(90, 80));
        button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        button.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    details.removeAll();
                    details.add(panel, BorderLayout.CENTER);
                } catch (Exception ex) {
                    Log.Debug(this, ex);
                }
            }
        });

        buttons.add(button);
    }

    /**
     *  Show a Control Panel Applet
     * @param panel
     */
    public void openDetails(ControlApplet panel) {
        if (panel != null) {
            MPV5View.setWaiting(true);
                scroller.setViewportView((Component) panel);

            try {
                actions.removeAll();
                actions.add(panel.getActionPanel());
                actions.validate();
            } catch (Exception e) {
                Log.Debug(this, e);
            }

      
            ((Component) panel).validate();
            MPV5View.setWaiting(false);
            MPV5View.identifierFrame.validate();

        }
    }
}
