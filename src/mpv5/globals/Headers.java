package mpv5.globals;

import mpv5.i18n.LanguageManager;
import mpv5.logging.Log;

public enum Headers {

    SEARCH_DEFAULT(new String[]{"Internal ID", "", ""}),
    CONTACT_DEFAULT(new String[]{"Internal ID", "ID", "Name", "City"}),
    CONTACT_DETAILS(new String[]{"Internal ID", "ID", "Title", "Prename", "Name", "Street", "Zip", "City", "Mainphone", "Fax", "Mobilephone", "Workphone", "Mailaddress", "Company", "Website", "Notes", "Taxnumber"}),
    USER_DEFAULT(new String[]{"Internal ID", "ID", "User Name", "Mail", "Last logged in"}),
    USER_DETAILS(new String[]{"Internal ID", "User Name", "Fullname", "Mail", "Enabled", "Logged in"}),
    BABELFISH(new String[]{"Component", "Language Value", "New Value"}),
    ITEM_DEFAULT(new String[]{"Internal ID", "ID", "Date", "Net Value"}),
    ITEM_DETAIL(new String[]{"Internal ID", "ID", "Date", "Value"}),
    FILE_REFERENCES(new String[]{"Internal ID", "Filename", "Date", "Description", "Size", "Type"}),
    HISTORY(new String[]{"Internal ID", "Description", "User", "Group", "Date"}),
    IMPORT(new String[]{"Internal ID", "Import", "Type", "Name", "Data"}),
    JOURNAL(new String[]{"Internal ID", "Date", "Group", "Account", "Number", "Type", "Status", "Volume", "Revenue"}),
    TRASHBIN(new String[]{"Type", "Internal ID", "Description"}),
    PRODUCT_DEFAULT(new String[]{"Internal ID", "Name", "Number", "Description"}),
    SUBITEMS(new String[]{"Internal ID", "", "Count", "Measure", "Description", "Netto Price", "Tax Rate", "Total Price", "Tax value", "Net 2", "Product ID"}),
    TEMPLATES(new String[]{"Name", "Type", "Group"}),
    MAILTEMPLATES(new String[]{"Name", "Description", "Group"}),
    SCHEDULE_LIST(new String[]{"Item ID", "Interval (Months)", "End date", "Added by"}),
    EXPENSE(new String[]{"Number", "Description", "Account", "Value", "Tax Rate"});

    private Headers(String[] header) {
        this.header = header;
    }
    private String[] header;

    public String[] getValue() {
      
           if (LanguageManager.isReady()) {
            for (int i = 0; i < header.length; i++) {
                try {
                    header[i] = LanguageManager.getBundle().getString(this.name() + "." + i);
                } catch (Exception e) {
                    Log.Debug(this, e.getMessage());
                }
            }
        }
        
        return header;
    }

    public void setValue(String[] header) {
        this.header = header;
    }

    /**
     * Print out the values and their keys
     */
    public void printValues(){
         for (int k = 0; k < this.values().length; k++) {
            Headers data = this.values()[k];
            for (int i = 0; i < data.getValue().length; i++) {
                System.err.println(data.name() + "." + i + "=" + data.getValue()[i]);
            }
        }
    }
}
