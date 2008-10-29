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
package mp4.items;

import mp4.datenbank.verbindung.Query;
import mp4.logs.Log;

/**
 * @author anti43         
 */
public class ProductGroupGroup extends mp4.items.Things implements mp4.datenbank.installation.Tabellen {

    private String gruppennummer = "0";
    private Integer familienid = 0;
    private String name = "";
    private String[][] data;
    private Query query;


    public void destroy() {
        this.deactivate(this.id.toString());
        this.id = 0;
    }

    public ProductGroupGroup(Query query) {
        super(query.clone(TABLE_PRODUCTS_GROUPS_GROUPS));

    }

    public ProductGroupGroup(Query query, Integer id) {
        super(query.clone(TABLE_PRODUCTS_GROUPS_GROUP_FIELDS));

        this.id = id;
        try {
            this.explode(this.selectLast("*", "id", id.toString(), true));
        } catch (Exception ex) {
             Log.Debug(this,ex);
        }
        this.query = query;
    }

    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return this.name;

    }

    private void explode(String[] data) {
        this.id = Integer.valueOf(data[0]);
        this.setGruppennummer(data[1]);
        this.setFamilienid(Integer.valueOf(data[2]));
        this.setName(data[3]);

    }

    private String collect() {
        String str = "";
        str = str + "(;;2#4#1#1#8#0#;;)" + getGruppennummer() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str + getFamilienid() + "(;;,;;)";
        str = str + "(;;2#4#1#1#8#0#;;)" + getName() + "(;;2#4#1#1#8#0#;;)";

        return str;
    }

    public void save() {

        if (id > 0) {
            this.update(TABLE_PRODUCTS_GROUPS_GROUP_FIELDS, this.collect(), id);
            isSaved = true;
        } else if (id == 0) {
            if (this.getGruppennummer().equals("0")) {
                this.setGruppennummer(this.getNextIndex("gruppenummer").toString());
            }
            id = this.insert(TABLE_PRODUCTS_GROUPS_GROUP_FIELDS, this.collect(),null);
        } else {
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public String getGruppennummer() {
        return gruppennummer;
    }

    public void setGruppennummer(String gruppennummer) {
        this.gruppennummer = gruppennummer;
    }

    public Integer getFamilienid() {
        return familienid;
    }

    public void setFamilienid(Integer familienid) {
        this.familienid = familienid;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFamilyID() {
        return familienid;
    }
}
