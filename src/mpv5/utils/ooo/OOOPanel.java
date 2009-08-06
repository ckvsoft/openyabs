package mpv5.utils.ooo;

import ag.ion.bion.officelayer.desktop.DesktopException;
import ag.ion.bion.officelayer.document.DocumentException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import ag.ion.bion.officelayer.application.IOfficeApplication;
import ag.ion.bion.officelayer.application.OfficeApplicationException;
import ag.ion.bion.officelayer.desktop.GlobalCommands;
import ag.ion.bion.officelayer.desktop.IFrame;
import ag.ion.bion.officelayer.document.DocumentDescriptor;
import ag.ion.bion.officelayer.document.IDocument;
import ag.ion.bion.officelayer.form.IFormComponent;

import ag.ion.bion.officelayer.text.ITextDocument;
import ag.ion.noa.frame.IDispatchDelegate;
import com.sun.star.awt.XTextComponent;
import com.sun.star.beans.XPropertySet;
import com.sun.star.form.XFormComponent;
import com.sun.star.uno.UnoRuntime;
import enoa.connection.NoaConnection;
import java.io.File;
import java.util.Hashtable;


import mpv5.db.common.Context;
import mpv5.db.common.QueryHandler;
import mpv5.db.common.SaveString;
import mpv5.globals.LocalSettings;
import mpv5.globals.Messages;
import mpv5.logging.Log;
import mpv5.ui.dialogs.Popup;
import mpv5.ui.frames.MPView;

public class OOOPanel extends JPanel {

    public OOOPanel() {
        super(new BorderLayout());
    }

    /**
     * Loads the given template into this panel
     * @param odtFile
     */
    public void constructOOOPanel(final File odtFile) {
        if (!LocalSettings.getBooleanProperty(LocalSettings.OFFICE_REMOTE)) {
            MPView.setWaiting(true);

            ITextDocument textDocument;
            setVisible(true);

            try {
                IFrame officeFrame = NoaConnection.getConnection().getDesktopService().constructNewOfficeFrame(this);
                officeFrame.setFocus();
                if (odtFile != null) {
                    IDocument document = NoaConnection.getConnection().getDocumentService().loadDocument(officeFrame, odtFile.getPath());
                    textDocument = (ITextDocument) document;
                } else {
                    NoaConnection.getConnection().getDocumentService().constructNewDocument(officeFrame, IDocument.WRITER, DocumentDescriptor.DEFAULT);
                }

                configureFrame(officeFrame);
            } catch (Exception ex) {
                Log.Debug(ex);
            } finally {
                getParent().validate();
                MPView.setWaiting(false);
            }
            MPView.addMessage(Messages.DONE_LOADING_OOO);
        } else {
            Popup.notice(Messages.OONOTLOCAL);
        }
    }

    /**
     * Configures the given IFrame to print the closed document's path to System.out on close<br/>
     * and removes the Close and Quit capabilites of the frame
     * @param officeFrame
     */
    public void configureFrame(IFrame officeFrame) {
//
//        officeFrame.addDispatchDelegate(GlobalCommands.SAVE, new IDispatchDelegate() {
//
//            @Override
//            public void dispatch(Object[] arg0) {
//                try {
//                    QueryHandler.instanceOf().clone(Context.getFiles()).insertFile(new File(NoaConnection.getConnection().getDocumentService().getCurrentDocuments()[0].getPersistenceService().getLocation().getPath()),
//                            MPView.getUser(), new SaveString("Template", true));
//                } catch (DocumentException ex) {
//                    Log.Debug(ex);
//                }
//            }
//        });
//
//        officeFrame.addDispatchDelegate(GlobalCommands.OPEN_DOCUMENT, new IDispatchDelegate() {
//
//            @Override
//            public void dispatch(Object[] arg0) {
////                try {
////
//////                    officeApplication.getDocumentService().loadDocument(officeFrame, officeFrame.);
////                } catch (OfficeApplicationException ex) {
////                    Log.Debug(ex);
////                }
//            }
//        });

        officeFrame.updateDispatches();
        officeFrame.disableDispatch(GlobalCommands.CLOSE_DOCUMENT);
        officeFrame.disableDispatch(GlobalCommands.QUIT_APPLICATION);

    }

    /**
     * Fill the form fields of the template with values
     * @param textDocument
     * @param template
     * @param data
     * @throws java.lang.Exception
     */
    public void fillFields(ITextDocument textDocument, File template, Hashtable<String, String> data) throws Exception {

        IFormComponent[] formComponents = textDocument.getFormService().getFormComponents();
        // iterate over hashtable and insert values into field masters
        java.util.Enumeration keys = data.keys();
        String key = null;
        while (keys.hasMoreElements()) {

            try {
                // get column name
                key = (String) keys.nextElement();

                for (int i = 0; i < formComponents.length; i++) {

                    XFormComponent xFormComponent = formComponents[i].getXFormComponent();
                    XTextComponent xTextComponent = formComponents[i].getXTextComponent();
                    XPropertySet propertySet = (XPropertySet) UnoRuntime.queryInterface(XPropertySet.class,
                            xFormComponent);

                    if (propertySet != null && propertySet.getPropertySetInfo().hasPropertyByName("Name")) {
                        String name = propertySet.getPropertyValue("Name").toString();
                        if (name.equalsIgnoreCase(key)) {
                            xTextComponent.setText(data.get(key));
                        }
                    }
                }
            } catch (Exception noSuchElementException) {
                System.err.println(noSuchElementException.getMessage());
            }
        }
    }
}