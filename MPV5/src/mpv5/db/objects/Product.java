/*
 *  This file is part of MP.
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
package mpv5.db.objects;

import java.util.Date;
import javax.swing.JComponent;
import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.globals.Messages;

/**
 *
 *  anti
 */
public class Product extends DatabaseObject {


    /**
     * Returns a localized string represenation of the given product type
     * @param type
     * @return
     */
    public static String getTypeString(int type) {
        switch (type) {
            case (TYPE_PRODUCT):
                return Messages.TYPE_PRODUCT.toString();
            case (TYPE_SERVICE):
                return Messages.TYPE_SERVICE.toString();
        }
        return null;
    }

    private int inttaxids;
    private int inttype;
    private String manufacturer;
    private String supplier;
    
    private double externalnetvalue;
    private double internalnetvalue;
    private String description = "";
    private String cnumber = "";
    private String measure = "";
    private String url = "";
    private String ean = "";
    private String reference = "";//herstellernummer

  
    public static final int TYPE_PRODUCT = 0;
    public static final int TYPE_SERVICE = 1;

    public Product() {
        context.setDbIdentity(Context.IDENTITY_PRODUCTS);
        context.setIdentityClass(Product.class);
    }


     @Override
    public mpv5.utils.images.MPIcon getIcon() {
        return null;
    }

    @Override
    public JComponent getView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the inttaxids
     */
    public int __getInttaxids() {
        return inttaxids;
    }

    /**
     * @param inttaxids the inttaxids to set
     */
    public void setInttaxids(int inttaxids) {
        this.inttaxids = inttaxids;
    }

    /**
     * @return the inttype
     */
    public int __getInttype() {
        return inttype;
    }

    /**
     * @param inttype the inttype to set
     */
    public void setInttype(int inttype) {
        this.inttype = inttype;
    }

    /**
     * @return the manufacturer
     */
    public String __getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return the supplier
     */
    public String __getSupplier() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    /**
     * @return the externalnetvalue
     */
    public double __getExternalnetvalue() {
        return externalnetvalue;
    }

    /**
     * @param externalnetvalue the externalnetvalue to set
     */
    public void setExternalnetvalue(double externalnetvalue) {
        this.externalnetvalue = externalnetvalue;
    }

    /**
     * @return the internalnetvalue
     */
    public double __getInternalnetvalue() {
        return internalnetvalue;
    }

    /**
     * @param internalnetvalue the internalnetvalue to set
     */
    public void setInternalnetvalue(double internalnetvalue) {
        this.internalnetvalue = internalnetvalue;
    }

    /**
     * @return the description
     */
    public String __getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the cnumber
     */
    public String __getCnumber() {
        return cnumber;
    }

    /**
     * @param cnumber the cnumber to set
     */
    public void setCnumber(String cnumber) {
        this.cnumber = cnumber;
    }

    /**
     * @return the measure
     */
    public String __getMeasure() {
        return measure;
    }

    /**
     * @param measure the measure to set
     */
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    /**
     * @return the url
     */
    public String __getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the ean
     */
    public String __getEan() {
        return ean;
    }

    /**
     * @param ean the ean to set
     */
    public void setEan(String ean) {
        this.ean = ean;
    }

    /**
     * @return the reference
     */
    public String __getReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }
}