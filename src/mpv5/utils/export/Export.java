/*
 *  This file is part of YaBS.
 *
 *      YaBS is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *
 *      YaBS is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with YaBS.  If not, see <http://www.gnu.org/licenses/>.
 */
package mpv5.utils.export;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.SwingUtilities;
import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.db.common.Formattable;
import mpv5.db.common.NodataFoundException;
import mpv5.db.common.QueryCriteria;
import mpv5.db.objects.Contact;
import mpv5.db.objects.MailMessage;
import mpv5.db.objects.Template;
import mpv5.globals.Messages;
import mpv5.handler.FormFieldsHandler;
import mpv5.handler.VariablesHandler;
import mpv5.logging.Log;
import mpv5.mail.SimpleMail;
import mpv5.ui.dialogs.Popup;
import mpv5.ui.frames.MPView;
import mpv5.utils.files.FileDirectoryHandler;
import mpv5.utils.jobs.Job;
import mpv5.utils.jobs.Waitable;
import mpv5.utils.jobs.Waiter;
import mpv5.utils.print.PrintJob2;

/**
 * The Export class is the primary handler in Yabs to do exporting tasks, such as printing, generating files etc.
 *  
 */
public final class Export extends HashMap<String, Object> implements Waitable {

    private static final long serialVersionUID = 1L;

    /**
     * Mail a template
     * @param preloadedTemplate
     * @param dataOwner
     * @param to
     */
    public static void mail(Template preloadedTemplate, DatabaseObject dataOwner, Contact to) {
        try {
            mpv5.YabsViewProxy.instance().setWaiting(true);
            QueryCriteria c = new QueryCriteria("usersids", mpv5.db.objects.User.getCurrentUser().__getIDS());
            MailMessage m = null;
            boolean send = true;
            try {
                m = (MailMessage) Popup.SelectValue(DatabaseObject.getObjects(Context.getMessage(), c), Messages.SELECT_A_TEMPLATE);
            } catch (Exception ex) {
                Log.Debug(Export.class, ex.getMessage());
                send = Popup.Y_N_dialog(Messages.NO_MAIL_TEMPLATE_DEFINED);
            }

            if (send) {
                HashMap<String, Object> hm1 = new FormFieldsHandler(dataOwner).getFormattedFormFields(null);
                File f2 = FileDirectoryHandler.getTempFile(((Formattable) dataOwner).getFormatHandler().toUserString(), "pdf");
                Export ex = new Export(preloadedTemplate);
                ex.putAll(hm1);

                ex.setTemplate(preloadedTemplate.getExFile());
                ex.setTargetFile(f2);

                try {
                    Contact cont = to;
                    if (mpv5.db.objects.User.getCurrentUser().__getMail().contains("@") && mpv5.db.objects.User.getCurrentUser().__getMail().contains(".") && cont.__getMailaddress().contains("@") && cont.__getMailaddress().contains(".")) {
                        SimpleMail pr = new SimpleMail();
                        pr.setMailConfiguration(mpv5.db.objects.User.getCurrentUser().getMailConfiguration());
                        pr.setRecipientsAddress(cont.__getMailaddress());
                        if (m != null && m.__getCName() != null) {
                            pr.setSubject(VariablesHandler.parse(m.__getCName(), dataOwner));
                            pr.setText(VariablesHandler.parse(m.__getDescription(), dataOwner));
                        }
                        try {
                            new Job(ex, (Waiter) pr).execute();
                        } catch (Exception e) {
                            Popup.error(e);
                        }

                    } else {
                        Popup.notice(Messages.NO_MAIL_DEFINED);
                    }
                } catch (UnsupportedOperationException unsupportedOperationException) {
                    Log.Debug(unsupportedOperationException);
                    Popup.notice(Messages.NOT_POSSIBLE + "\n" + Messages.NO_MAIL_CONFIG);
                }
            }
        } catch (Exception e) {
            Log.Debug(e);
        } finally {
            mpv5.YabsViewProxy.instance().setWaiting(false);
        }
    }

    /**
     * Print a template
     * @param preloadedTemplate
     * @param dataOwner
     */
    public static void print(final Template preloadedTemplate, final DatabaseObject dataOwner) {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                HashMap<String, Object> hm1 = new FormFieldsHandler(dataOwner).getFormattedFormFields(null);
                File f2 = FileDirectoryHandler.getTempFile("pdf");
                final Export ex = new Export(preloadedTemplate);
                ex.putAll(hm1);
                ex.setTemplate(preloadedTemplate.getExFile());
                ex.setTargetFile(f2);
//        new Job(ex, (Waiter) new PrintJob()).execute();

                ex.waitFor();
                try {
                    PrintJob2.print(ex.getTargetFile(), preloadedTemplate.__getPrinter());
                } catch (Exception ex1) {
                    Log.Debug(ex1);
                }
            }
        };
        new Thread(runnable).start();
    }

    /**
     * Print multiple templates
     * @param preloadedTemplate
     * @param dataOwner
     */
    public static void print(final Template[] preloadedTemplate, final DatabaseObject dataOwner) {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                List<File> files = new Vector<File>();
                if (preloadedTemplate != null && preloadedTemplate.length > 0) {
                    for (int i = 0; i < preloadedTemplate.length; i++) {
                        Template template = preloadedTemplate[i];
                        HashMap<String, Object> hm1 = new FormFieldsHandler(dataOwner).getFormattedFormFields(null);
                        File f2 = FileDirectoryHandler.getTempFile("pdf");
                        Export ex = new Export(template);
                        ex.putAll(hm1);
                        try {
                            ex.processData(f2);
                            files.add(f2);
                        } catch (NodataFoundException ex1) {
                            Log.Debug(ex1);
                        } catch (FileNotFoundException ex1) {
                            Log.Debug(ex1);
                        }
                    }

                    try {
                        PrintJob2.print(mergeFiles(files), preloadedTemplate[0].__getPrinter());
                    } catch (Exception fileNotFoundException) {
                        Popup.error(fileNotFoundException);
                        Log.Debug(fileNotFoundException);
                    }
                }
            }
        };
        new Thread(runnable).start();
    }

    private static File mergeFiles(List<File> p) {

        Document document = new Document();
        try {
            List<InputStream> pdfs = new Vector<InputStream>();
            for (int i = 0; i < p.size(); i++) {
                File inputStream = p.get(i);
                pdfs.add(new FileInputStream(inputStream));
            }
            List<PdfReader> readers = new ArrayList<PdfReader>();
            int totalPages = 0;
            Iterator<InputStream> iteratorPDFs = pdfs.iterator();


            while (iteratorPDFs.hasNext()) {
                InputStream pdf = iteratorPDFs.next();
                PdfReader pdfReader = new PdfReader(pdf);
                readers.add(pdfReader);
                totalPages += pdfReader.getNumberOfPages();
            }

            File f = FileDirectoryHandler.getTempFile("pdf");
            FileOutputStream outputstream = new FileOutputStream(f);
            PdfWriter writer = PdfWriter.getInstance(document, outputstream);

            document.open();
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            PdfContentByte cb = writer.getDirectContent();

            PdfImportedPage page;
            int currentPageNumber = 0;
            int pageOfCurrentReaderPDF = 0;
            Iterator<PdfReader> iteratorPDFReader = readers.iterator();

            // Loop through the PDF files and add to the output.
            while (iteratorPDFReader.hasNext()) {
                PdfReader pdfReader = iteratorPDFReader.next();

                // Create a new page in the target for each source page.
                while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
                    document.newPage();
                    pageOfCurrentReaderPDF++;
                    currentPageNumber++;
                    page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
                    cb.addTemplate(page, 0, 0);

                }
                pageOfCurrentReaderPDF = 0;
            }
            outputstream.flush();
            document.close();
            outputstream.close();

            return f;
        } catch (Exception e) {
            Log.Debug(e);
        } finally {
            if (document.isOpen()) {
                document.close();
            }
        }

        return null;
    }

    /**
     * Create a {@link Waitable} which is able to create a file PDF
     * @param preloadedTemplate
     * @param dataOwner
     * @return
     */
    public static Waitable createFile(Template preloadedTemplate, DatabaseObject dataOwner) {
        return createFile(null, preloadedTemplate, dataOwner);
    }

    /**
     * Create a {@link Waitable} which is able to create a file ODT
     * @param preloadedTemplate
     * @param dataOwner
     * @return
     */
    public static Waitable sourceFile(Template preloadedTemplate, DatabaseObject dataOwner) {
        return sourceFile(null, preloadedTemplate, dataOwner);
    }

    /**
     * Create a {@link Waitable} which is able to create a file PDF
     * @param aname
     * @param preloadedTemplate
     * @param dataOwner
     * @return
     */
    public static Waitable createFile(String aname, Template preloadedTemplate, DatabaseObject dataOwner) {
        HashMap<String, Object> hm1 = new FormFieldsHandler(dataOwner).getFormattedFormFields(null);
        File f2;
        if (aname == null) {
            f2 = FileDirectoryHandler.getTempFile("pdf");
        } else {
            f2 = FileDirectoryHandler.getTempFile(aname, "pdf");
        }
        Export ex = new Export(preloadedTemplate);
        ex.putAll(hm1);
        ex.setTemplate(preloadedTemplate.getExFile());
        ex.setTargetFile(f2);
        return ex;
    }

    /**
     * Create a {@link Waitable} which is able to create a file ODT
     * @param aname
     * @param preloadedTemplate
     * @param dataOwner
     * @return
     */
    public static Waitable sourceFile(String aname, Template preloadedTemplate, DatabaseObject dataOwner) {
        HashMap<String, Object> hm1 = new FormFieldsHandler(dataOwner).getFormattedFormFields(null);
        File f2;
        if (aname == null) {
            f2 = FileDirectoryHandler.getTempFile("odt");
        } else {
            f2 = FileDirectoryHandler.getTempFile(aname, "odt");
        }

        Export ex = new Export(preloadedTemplate);
        ex.putAll(hm1);
        ex.setTemplate(preloadedTemplate.getExFile());
        ex.setTargetFile(f2);
        return ex;
    }

    /**
     * Create a {@link Waitable} which is able to create a file PDF
     * @param aname
     * @param preloadedTemplate
     * @param dataOwner
     * @param adddata
     * @return
     */
    public static Waitable createFile(String aname, Template preloadedTemplate, DatabaseObject dataOwner, HashMap<String, Object> adddata) {
        HashMap<String, Object> hm1 = new FormFieldsHandler(dataOwner).getFormattedFormFields(null);
        File f2;
        if (aname == null) {
            f2 = FileDirectoryHandler.getTempFile("pdf");
        } else {
            f2 = FileDirectoryHandler.getTempFile(aname, "pdf");
        }
        Export ex = new Export(preloadedTemplate);
        ex.putAll(hm1);
        ex.putAll(adddata);
        ex.setTemplate(preloadedTemplate.getExFile());
        ex.setTargetFile(f2);
        return ex;
    }

    /**
     * Create a {@link Waitable} which is able to create a file ODT
     * @param aname
     * @param preloadedTemplate
     * @param dataOwner
     * @param adddata
     * @return
     */
    public static Waitable sourceFile(String aname, Template preloadedTemplate, DatabaseObject dataOwner, HashMap<String, Object> adddata) {
        HashMap<String, Object> hm1 = new FormFieldsHandler(dataOwner).getFormattedFormFields(null);
        File f2;
        if (aname == null) {
            f2 = FileDirectoryHandler.getTempFile("odt");
        } else {
            f2 = FileDirectoryHandler.getTempFile(aname, "odt");
        }

        Export ex = new Export(preloadedTemplate);
        ex.putAll(hm1);
        ex.putAll(adddata);
        ex.setTemplate(preloadedTemplate.getExFile());
        ex.setTargetFile(f2);
        return ex;
    }

    /**
     * (Pre)load a template. Do not run this from the EDT, as the fetching of the templatefile from the database might take a while.
     * @param dataOwner
     * @return
     * @deprecated DO NOT use this anymore, will always return NULL
     */
    public static Template loadTemplate(final DatabaseObject dataOwner) {
//        Template preloadedTemplate;
//        preloadedTemplate = TemplateHandler.loadTemplate(dataOwner);
//        Exportable preloadedExportFile;
//        if (preloadedTemplate != null) {
//            try {
//                preloadedExportFile = preloadedTemplate.getExFile();
//            } catch (Exception e) {
//                Log.Debug(e);
//            }
//        }
        return null;
    }

    /*
     * Prints a screen capture of the given component
     */
    public static void print(final Component c) {
        new PrintJob2(c);
    }
    private Exportable fromFile;
    private File toFile;
    private final Template template;

    /**
     *
     * @param t
     */
    public Export(Template t) {
        super();
        this.template = t;
        setTemplate(t.getExFile());
    }

    /**
     * Add the data, must be key - value pairs
     * @param data 
     */
    public void addData(String[][] data) {
        for (int i = 0; i < data.length; i++) {
            String[] strings = data[i];
            super.put(strings[0], strings[1]);
        }
    }

    /**
     *  Set the file to be filled.. not required to be called explicitely
     * @param <T>
     * @param templateFile 
     */
    public <T extends Exportable> void setTemplate(T templateFile) {
        this.fromFile = templateFile;
    }

    /**
     * Exports the given data to the target file using the given template file.
     * <br/>
     * @param toFile The target file. If the file exists, will be overwritten if possible.
     * @throws NodataFoundException If no data has been previously added
     * @throws FileNotFoundException If no file was given as template
     */
    public void processData(File toFile) throws NodataFoundException, FileNotFoundException {
        if (this.isEmpty()) {
//            throw new NodataFoundException();
//            return;
        }

        if (fromFile == null) {
            throw new FileNotFoundException("You must call setFile(Exportable file) first!");
        } else if (!this.fromFile.exists()) {
            throw new FileNotFoundException(fromFile.getPath());
        }

        if (toFile != null) {
            if (toFile.exists()) {
                toFile.delete();
                Log.Debug(this, "File exists, will be replaced: " + toFile);
            }

            toFile.getParentFile().mkdirs();
            fromFile.setTarget(toFile);
        }
        fromFile.setData(this);
        fromFile.setTemplate(template);

        try {
            SwingUtilities.invokeAndWait(fromFile);// we need to wait for OO to perform the task
        } catch (Exception e) {
            Log.Debug(e);
        }
    }

    public Exception waitFor() {
        try {
            processData(getTargetFile());
        } catch (Exception ex) {
            return ex;
        }
        return null;
    }

    /**
     * The target file. If the file exists, will be overwritten if possible.
     * @param toFile the toFile to set
     */
    public void setTargetFile(File toFile) {
        this.toFile = toFile;
    }

    /**
     * @return the toFile
     */
    public File getTargetFile() {
        return toFile;
    }
}
