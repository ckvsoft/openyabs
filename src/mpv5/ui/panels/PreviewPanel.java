package mpv5.ui.panels;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;
import enoa.connection.NoaConnection;
import enoa.handler.DocumentHandler;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.db.common.QueryCriteria;
import mpv5.db.common.QueryHandler;
import mpv5.globals.Messages;
import mpv5.logging.Log;
import mpv5.ui.dialogs.BigPopup;
import mpv5.ui.dialogs.DialogForFile;
import mpv5.ui.dialogs.Popup;
import mpv5.ui.frames.MPView;
import mpv5.utils.export.Export;
import mpv5.utils.files.FileDirectoryHandler;
import mpv5.utils.files.FileReaderWriter;
import mpv5.utils.jobs.Waiter;
import mpv5.utils.ooo.OOOPanel;
import mpv5.utils.print.PrintJob;

/**
 *
 * 
 */
public class PreviewPanel extends javax.swing.JPanel implements Waiter {

    private static final long serialVersionUID = 1L;
    private File file;
    private DatabaseObject dataOwner;
    private PDFFile pdffile;
    private PagePanel panel;
    private DataPanel parent;

    /** Creates new form
     */
    public PreviewPanel() {
        initComponents();
    }

    /** Creates new preview for the given file. Currently supported file types:
     * <li>PDF
     * <li>ODT
     * <li>TXT
     * @param file
     */
    public PreviewPanel(File file) {
        initComponents();
        openl(file);
    }

    /**
     * Shows the given pdf file in the preview panel
     * @param pdf
     */
    public void openPdf(File pdf) {
        this.file = pdf;
        if (pdf.isFile() && pdf.exists()) {
            try {

                panel = new PagePanel();
                ppanel.removeAll();
                JScrollPane sp = new JScrollPane(panel);
                ppanel.add(sp, BorderLayout.CENTER);
                ppanel.validate();

                if (getParent() instanceof JFrame) {
                    JFrame p = (JFrame) getParent();
                    p.pack();
                    p.setVisible(true);
                }

                RandomAccessFile raf = new RandomAccessFile(pdf, "r");
                FileChannel channel = raf.getChannel();
                ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                pdffile = new PDFFile(buf);
                buf.clear();
                channel.close();
                raf.close();
                panel.useZoomTool(true);
                // show the first page
                PDFPage page = pdffile.getPage(pagen);
                panel.showPage(page);
            } catch (IOException ex) {
                Log.Debug(ex);
            }
        } else {
            throw new IllegalArgumentException("File is not existing or a directory: " + pdf);
        }
    }

    /** This me4thod is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator5 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        ppanel = new javax.swing.JPanel();
        toolbar = new javax.swing.JToolBar();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jSeparator5.setName("jSeparator5"); // NOI18N

        setName("Form"); // NOI18N

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("mpv5/resources/languages/Panels"); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("PreviewPanel.jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        ppanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ppanel.setName("ppanel"); // NOI18N
        ppanel.setLayout(new java.awt.BorderLayout());

        toolbar.setRollover(true);
        toolbar.setName("toolbar"); // NOI18N

        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/printer.png"))); // NOI18N
        jButton27.setText(bundle.getString("PreviewPanel.jButton27.text")); // NOI18N
        jButton27.setToolTipText(bundle.getString("PreviewPanel.jButton27.toolTipText")); // NOI18N
        jButton27.setContentAreaFilled(false);
        jButton27.setFocusable(false);
        jButton27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton27.setName("jButton27"); // NOI18N
        jButton27.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        toolbar.add(jButton27);

        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/mail_reply.png"))); // NOI18N
        jButton28.setText(bundle.getString("PreviewPanel.jButton28.text")); // NOI18N
        jButton28.setToolTipText(bundle.getString("PreviewPanel.jButton28.toolTipText")); // NOI18N
        jButton28.setContentAreaFilled(false);
        jButton28.setFocusable(false);
        jButton28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton28.setName("jButton28"); // NOI18N
        jButton28.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        toolbar.add(jButton28);

        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/acroread.png"))); // NOI18N
        jButton30.setText(bundle.getString("PreviewPanel.jButton30.text")); // NOI18N
        jButton30.setToolTipText(bundle.getString("PreviewPanel.jButton30.toolTipText")); // NOI18N
        jButton30.setContentAreaFilled(false);
        jButton30.setFocusable(false);
        jButton30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton30.setName("jButton30"); // NOI18N
        jButton30.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        toolbar.add(jButton30);

        jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/ark.png"))); // NOI18N
        jButton31.setText(bundle.getString("PreviewPanel.jButton31.text")); // NOI18N
        jButton31.setToolTipText(bundle.getString("PreviewPanel.jButton31.toolTipText")); // NOI18N
        jButton31.setContentAreaFilled(false);
        jButton31.setFocusable(false);
        jButton31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton31.setName("jButton31"); // NOI18N
        jButton31.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        toolbar.add(jButton31);

        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/fileexport.png"))); // NOI18N
        jButton32.setText(bundle.getString("PreviewPanel.jButton32.text")); // NOI18N
        jButton32.setToolTipText(bundle.getString("PreviewPanel.jButton32.toolTipText")); // NOI18N
        jButton32.setContentAreaFilled(false);
        jButton32.setFocusable(false);
        jButton32.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton32.setName("jButton32"); // NOI18N
        jButton32.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        toolbar.add(jButton32);

        jSeparator1.setName("jSeparator1"); // NOI18N
        toolbar.add(jSeparator1);

        jButton1.setText(bundle.getString("PreviewPanel.jButton1.text")); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        toolbar.add(jButton1);

        jButton2.setText(bundle.getString("PreviewPanel.jButton2.text")); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        toolbar.add(jButton2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
            .addComponent(ppanel, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ppanel, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        if (file != null) {
            try {
                new PrintJob().print(file);
            } catch (Exception ex) {
                Log.Debug(ex);
            }
        }
}//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed

        if (dataOwner != null) {
            try {
                file = FileDirectoryHandler.copyFile2(file, new File(FileDirectoryHandler.getTempDir() + dataOwner.__getCName() + ".pdf"));
            } catch (Exception ex) {
                Log.Debug(ex);
            }
        }

        if (dataOwner != null && dataOwner.isExisting()) {
            if (QueryHandler.instanceOf().clone(Context.getFiles()).insertFile(file, dataOwner, QueryCriteria.getSaveStringFor(dataOwner.__getCName()))) {
                Popup.notice(Messages.FILE_SAVED.toString() + file.getName());
                if (parent != null) {
                    parent.refresh();
                }
            }
        } else {
            Popup.notice(Messages.NOT_POSSIBLE);
        }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        DialogForFile d = new DialogForFile(DialogForFile.FILES_ONLY);
        if (dataOwner == null) {
            d.setSelectedFile(new File(file.getName()));
        } else {
            d.setSelectedFile(new File(dataOwner.__getCName() + ".pdf"));
        }
        if (d.chooseFile()) {
            d.getFile().delete();
            if (file.renameTo(d.getFile())) {
                try {
                    MPView.addMessage(Messages.FILE_SAVED + file.getCanonicalPath());
                } catch (IOException ex) {
                    Log.Debug(ex);
                }
            }
        }
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed

        FileDirectoryHandler.open(file);

}//GEN-LAST:event_jButton30ActionPerformed
    int pagen = 1;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            pagen++;
            PDFPage p = pdffile.getPage(pagen);
            panel.showPage(p);
        } catch (Exception e) {
            //No pdf loaded
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (pagen > 0) {
                pagen--;
            }
            PDFPage p = pdffile.getPage(pagen);
            panel.showPage(p);
        } catch (Exception e) {
            //No pdf loaded
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JPanel ppanel;
    private javax.swing.JToolBar toolbar;
    // End of variables declaration//GEN-END:variables

//    public void openOdt(File file) {
//        this.file = file;
//        Log.Debug(this, "Preparing preview for: " + file);
//        OOOPanel op = new OOOPanel();
//        ppanel.removeAll();
//        ppanel.setLayout(new BorderLayout());
//        ppanel.add(op, BorderLayout.CENTER);
//
//        op.constructOOOPanel(file);
//        this.validate();
//    }
    public void open(File file) {
        this.file = file;
        FileReaderWriter f = new FileReaderWriter(file);
        String t = f.read();
        JEditorPane p = new JEditorPane();
        p.setText(t);
        ppanel.removeAll();
        ppanel.add(p, BorderLayout.CENTER);
        ppanel.validate();
    }

    /**
     * Show this panel in a new frame
     * @param title
     */
    public void showInNewFrame(String title) {
        BigPopup.showPopup(this, jPanel1, title, JFrame.MAXIMIZED_VERT, 800);
    }

    /**
     * @return the dataOwner
     */
    public DatabaseObject getDataOwner() {
        return dataOwner;
    }

    /**
     * @param dataOwner the dataOwner to set
     */
    public void setDataOwner(DatabaseObject dataOwner) {
        this.dataOwner = dataOwner;
    }

    public void set(Object object, Exception exception) throws Exception {
        if (exception == null) {
            try {
                setCursor(new Cursor(Cursor.WAIT_CURSOR));
                if (object instanceof Export) {
                    showInNewFrame("Export");
                    openl(((Export) object).getTargetFile());
                } else {
                    showInNewFrame(((File) object).getName());
                    openl((File) object);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        } else {
            throw exception;
        }
    }

    private void openl(File file) {
        if (file.getName().split("\\.").length < 2) {
            throw new UnsupportedOperationException("The file must have an extension: " + file);
        }

        String extension = file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());

        Log.Debug(this, "Found extension: " + extension);

        if (extension.equalsIgnoreCase(".pdf")) {
            try {
                openPdf(file);
            } catch (Exception ex) {
                Log.Debug(ex);
            }
        } else if (extension.equalsIgnoreCase(".odt")) {
            try {
                FileDirectoryHandler.open(file);
                makeToolBar();
            } catch (Exception ex) {
                Log.Debug(ex);
            }
        } else if (extension.equalsIgnoreCase(".txt")) {
            try {
                open(file);
            } catch (Exception ex) {
                Log.Debug(ex);
            }
        } else {
            FileDirectoryHandler.open(file);
            makeToolBar();
        }
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(DataPanel parent) {
        this.parent = parent;
    }

    private void makeToolBar() {
        jPanel1.remove(ppanel);
        validate();
        BigPopup.pack(this);
        BigPopup.setOnTop(this);
    }
}
