/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReceiptLookup.java
 *
 * Created on 07.11.2016, 08:56:18
 */
package mpv5.ui.panels;

import ag.ion.bion.officelayer.internal.application.Messages;
import com.sun.org.apache.bcel.internal.generic.Select;
import freemarker.template.utility.Collections12;
import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.SwingUtilities;
import mpv5.db.common.Context;
import mpv5.db.common.QueryCriteria2;
import mpv5.db.common.QueryParameter;
import mpv5.db.objects.Item;
import mpv5.handler.MPEnum;
import mpv5.logging.Log;
import mpv5.utils.models.MPComboBoxModelItem;

/**
 *
 * @author andreas
 */
public class ReceiptLookup extends javax.swing.JPanel {

    /** Creates new form ReceiptLookup */
    public ReceiptLookup() {
        initComponents();
        final Object[][] typeModel = new Object[][]{
            new Object[]{Context.getInvoice(), Item.getTypeString(Item.TYPE_INVOICE)},
            new Object[]{Context.getOffer(), Item.getTypeString(Item.TYPE_OFFER)},
            new Object[]{Context.getOrder(), Item.getTypeString(Item.TYPE_ORDER)},
            new Object[]{Context.getDelivery(), Item.getTypeString(Item.TYPE_DELIVERY_NOTE)},
            new Object[]{Context.getConfirmation(), Item.getTypeString(Item.TYPE_ORDER_CONFIRMATION)},};
        type.setModel(typeModel);
        type.setSearchEnabled(false);
        type.setSearchOnEnterEnabled(false);

        type.getComboBox().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                search.setText("");
                int index = type.getComboBox().getSelectedIndex();
                if (index < 0) {
                    index = 0;//type must be selected
                }
                generalListPanel1.setData((Context) typeModel[index][0], getStatusQuery());
            }
        });

        search.getTextField().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                generalListPanel1.setData((Context) typeModel[type.getComboBox().getSelectedIndex()][0], search.getText());
            }
        });
        MPEnum[] stati = new MPEnum[Item.getStatusStrings().length + 1];
        for (int i = 0; i < stati.length; i++) {
            if (i == 0) {
                stati[i] = new MPEnum() {

                    @Override
                    public Integer getId() {
                        return -2;
                    }

                    @Override
                    public String getName() {
                        return mpv5.globals.Messages.ALL.toString();
                    }
                };
            } else {
                stati[i] = Item.getStatusStrings()[i - 1];
            }
        }

        status.setModel(stati, MPComboBoxModelItem.COMPARE_BY_ID, Collections.EMPTY_LIST);
        status.setSearchEnabled(false);
        status.setSearchOnEnterEnabled(false);
        status.getComboBox().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                search.setText("");
                generalListPanel1.setData((Context) type.getSelectedItem().getIdObject(), getStatusQuery());
            }
        });
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                generalListPanel1.setData((Context) typeModel[0][0], getStatusQuery());
            }
        });
    }

    private QueryCriteria2 getStatusQuery() {
        MPComboBoxModelItem s = status.getSelectedItem();
        if (s != null) {
            int id = (int) s.getIdObject();
            if (id >= 0) {
                QueryCriteria2 q = new QueryCriteria2();
                q.and(new QueryParameter(Context.getInvoice(), "intstatus", id, QueryParameter.EQUALS));
                return q;
            }
        }
        return null;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        generalListPanel1 = new mpv5.ui.panels.GeneralListPanel();
        search = new mpv5.ui.beans.LabeledTextField();
        type = new mpv5.ui.beans.LabeledCombobox();
        status = new mpv5.ui.beans.LabeledCombobox();
        jLabel1 = new javax.swing.JLabel();

        setName("Form"); // NOI18N

        generalListPanel1.setName("generalListPanel1"); // NOI18N

        java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle();
        search.set_Label(bundle.getString("search._Label")); // NOI18N
        search.setName("search"); // NOI18N

        type.set_Label(bundle.getString("type._Label")); // NOI18N
        type.setName("type"); // NOI18N

        status.set_Label(bundle.getString("status._Label")); // NOI18N
        status.setName("status"); // NOI18N

        jLabel1.setText(bundle.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(search, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(type, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
                .addGap(448, 448, 448))
            .addComponent(generalListPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(generalListPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private mpv5.ui.panels.GeneralListPanel generalListPanel1;
    private javax.swing.JLabel jLabel1;
    private mpv5.ui.beans.LabeledTextField search;
    private mpv5.ui.beans.LabeledCombobox status;
    private mpv5.ui.beans.LabeledCombobox type;
    // End of variables declaration//GEN-END:variables
}
