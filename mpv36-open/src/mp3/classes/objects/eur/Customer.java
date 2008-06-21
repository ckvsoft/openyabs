/*
 *  This file is part of MP by anti43 /GPL.
 *  
 *      MP is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *  
 *      MP is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *  
 *      You should have received a copy of the GNU General Public License
 *      along with MP.  If not, see <http://www.gnu.org/licenses/>.
 */

package mp3.classes.objects.eur;

import java.util.ArrayList;
import mp3.database.util.Query;
import mp3.classes.interfaces.Structure;
import mp3.classes.layer.Popup;
import mp3.classes.layer.QueryClass;
import mp3.classes.utils.Log;

/**
 *
 * @author anti
 */
public class Customer extends mp3.classes.layer.People implements mp3.classes.interfaces.Structure {

   
    private String Kundennummer = "";
    private String Firma = "";
    private String Anrede = "";
    private String Vorname = "";
    private String Name = "";
    private String Str = "";
    private String PLZ = "";
    private String Ort = "";
    private String Tel = "";
    private String Fax = "";
    private String Mobil = "";
    private String Mail = "";
    private String Webseite = "";
    private String Notizen = "";
    private Query query;
    private boolean deleted=false;

 

    public Customer(Query query) {
        super(query.clone(TABLE_CUSTOMERS));
        this.query=query;

    }

    public Customer(Query query, String id) {
        super(query.clone(TABLE_CUSTOMERS));
        this.id=Integer.valueOf(id);
        this.explode(this.selectLast("*", "id", id, true , true, false));
        this.query=query;
    }

    public Customer(QueryClass query, String kundennummer, boolean like) {
        super(query.clone(TABLE_CUSTOMERS));
//        this.id=Integer.valueOf(id);
        this.explode(
                this.selectLast("*","kundennummer",kundennummer, false, false, false));
        this.query=query;
    }

//    public String[][] getAll() {
//       return getAll(true);
//    }


    @SuppressWarnings("unchecked")
    public ArrayList getAllCustomers() {
        ArrayList arr = new ArrayList();
        
        Query q = query.clone(TABLE_CUSTOMERS);

        String[][] str = q.select("id", null);
   
     
       
        for (int i = 0; i < str.length; i++) {
            
            arr.add(new Customer(QueryClass.instanceOf(), str[i][0]));

        }

        
        
        return arr;
    }





    public String getid() {
        return id.toString();
    }
//    public void setId(String id) {
//        this.id = id;
//    }
    public String getKundennummer() {
        return Kundennummer;
    }

    public boolean isValid() {
        if(this.id > 0) {
            return true;
        }
        else {
            return false;
        }
    }



    public void setKundennummer(String Kundennummer) {
        this.Kundennummer = Kundennummer;
        this.isSaved = false;
    }

    public String getFirma() {
        return Firma;
    }

    public void setFirma(String Firma) {
        this.Firma = Firma;
        this.isSaved = false;
    }

    public String getAnrede() {
        return Anrede;
    }

    public void setAnrede(String Anrede) {
        this.Anrede = Anrede;
        this.isSaved = false;
    }

    public String getVorname() {
        return Vorname;
    }

    public void setVorname(String Vorname) {
        this.Vorname = Vorname;
        this.isSaved = false;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
        this.isSaved = false;
    }

    public String getStr() {
        return Str;
    }

    public void setStr(String Str) {
        this.Str = Str;
        this.isSaved = false;
    }

    public String getPLZ() {
        return PLZ;
    }

    public void setPLZ(String PLZ) {
        this.PLZ = PLZ;
        this.isSaved = false;
    }

    public String getOrt() {
        return Ort;
    }

    public void setOrt(String Ort) {
        this.Ort = Ort;
        this.isSaved = false;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
        this.isSaved = false;
    }

    public String getMobil() {
        return Mobil;
    }

    public void setMobil(String Mobil) {
        this.Mobil = Mobil;
        this.isSaved = false;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String Mail) {
        this.Mail = Mail;
        this.isSaved = false;
    }

    public String getWebseite() {
        return Webseite;
    }

    public void setWebseite(String Webseite) {
        this.Webseite = Webseite;
        this.isSaved = false;
    }

    public String getNotizen() {
        return Notizen;
    }

    public void setNotizen(String Notizen) {
        this.Notizen = Notizen;
        this.isSaved = false;
    }

    private String collect() {
        String str = "";
//            "Kundennummer" + "," + "Firma" + "," + "Anrede" + "," + "Vorname" + 
//            "," + "Name" + "," + "Str" + "," + "PLZ" + "," + "Ort" + "," +
//            "Tel" + "," + "Mobil" + "," + "Mail" + "," + "Webseite" + "," + "Notizen"
        str = str + "(;;2#4#1#1#8#0#;;)" + this.getKundennummer() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str +  "(;;2#4#1#1#8#0#;;)" + this.getFirma() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str +  "(;;2#4#1#1#8#0#;;)" + this.getAnrede() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str +  "(;;2#4#1#1#8#0#;;)" + this.getVorname() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str +  "(;;2#4#1#1#8#0#;;)" + this.getName() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str +  "(;;2#4#1#1#8#0#;;)" + this.getStr() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str +  "(;;2#4#1#1#8#0#;;)" + this.getPLZ() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str +  "(;;2#4#1#1#8#0#;;)" + this.getOrt() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str +  "(;;2#4#1#1#8#0#;;)" + this.getTel() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str +  "(;;2#4#1#1#8#0#;;)" + this.getFax() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str +  "(;;2#4#1#1#8#0#;;)" + this.getMobil() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str +  "(;;2#4#1#1#8#0#;;)" + this.getMail() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str +  "(;;2#4#1#1#8#0#;;)" + this.getWebseite() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str +  "(;;2#4#1#1#8#0#;;)" + this.getNotizen() + "(;;2#4#1#1#8#0#;;)";



        return str;
    }
    private void expose(){
    
     System.out.println(collect());
    
    }

    private void explode(String[] str) {
        try {

            this.id = Integer.valueOf(str[0]);
            this.setKundennummer(str[1]);
            this.setFirma(str[2]);
            this.setAnrede(str[3]);
            this.setVorname(str[4]);
            this.setName(str[5]);
            this.setStr(str[6]);
            this.setPLZ(str[7]);
            this.setOrt(str[8]);
            this.setTel(str[9]);
            this.setFax(str[10]);
            this.setMobil(str[11]);
            this.setMail(str[12]);
            this.setWebseite(str[12 + 1]);
            this.setNotizen(str[12 + 2]);
            if(str[12+3].equals("1")) {
                this.setDeleted(true);
            }

        } catch (Exception exception) {
            Log.Debug(exception);
        }

    }
    public void save() {

        if (id > 0) {
            this.update(TABLE_CUSTOMER_FIELDS, this.collect(), id.toString());
            isSaved = true;
        } else if (id == 0) {
            this.insert(TABLE_CUSTOMER_FIELDS, this.collect());
            
            

            
        } else {

            mp3.classes.layer.Popup.warn(java.util.ResourceBundle.getBundle("languages/Bundle").getString("no_data_to_save"), Popup.WARN);

        }
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String Fax) {
        this.Fax = Fax;
        this.isSaved = false;
    }

    public String[][] getBills(){
           
        Query q = query.clone(TABLE_BILLS);

        String[] wher = {"kundenid", this.getId(), ""};

        String[][] str = q.select("id,rechnungnummer,datum", wher);
   
        return str;

    }
    
    
    public String[][] getPrintModel(){
    
        Query q = query.clone(TABLE_CUSTOMERS);

        String[][] str = q.select(Structure.TABLE_CUSTOMER_PRINT_FIELDS, null);
   
        return str;  
    }
    
    public String[][] getAll(boolean withDeleted){
    
        Query q = query.clone(TABLE_CUSTOMERS);

        
        String[][] str = q.select(ALL, null, withDeleted);
   
        return str;  
    }
    
    public void export(){
        Query q = query.clone(TABLE_CUSTOMERS);
        
        q.export("Kunden.txt");
    
    
    }

    private void setDeleted(boolean b) {
        this.deleted = b;
    }
    /**
     * 
     * @return the satus of this customer
     */
    public boolean isDeleted() {
        return this.deleted;
    }
    
    public Object[][] countBills(){
    
         Query q = query.clone(TABLE_CUSTOMERS);

         
         
         String[][] str  = q.select("firma,", null, TABLE_CUSTOMERS, "kundenid","datum");
   
        
         return str;  
    
    }
}