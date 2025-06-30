
package com.ejie.w43df.pid.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for w43DfMetadata complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="w43DfMetadata">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descriptionCA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descriptionEU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isRepeating" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isReadOnly" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isMandatory" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isFTRetrievable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isFTIndexable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isFTOrderable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="valueAssistances" type="{com/ejie/documents/xml}w43DfValueAssistance" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "w43DfMetadata", propOrder = {
    "name",
    "type",
    "descriptionCA",
    "descriptionEU",
    "isRepeating",
    "isReadOnly",
    "isMandatory",
    "isFTRetrievable",
    "isFTIndexable",
    "isFTOrderable",
    "valueAssistances"
})
public class W43DfMetadata {

    protected String name;
    protected String type;
    protected String descriptionCA;
    protected String descriptionEU;
    protected boolean isRepeating;
    protected boolean isReadOnly;
    protected boolean isMandatory;
    protected boolean isFTRetrievable;
    protected boolean isFTIndexable;
    protected boolean isFTOrderable;
    protected List<W43DfValueAssistance> valueAssistances;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the descriptionCA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescriptionCA() {
        return descriptionCA;
    }

    /**
     * Sets the value of the descriptionCA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescriptionCA(String value) {
        this.descriptionCA = value;
    }

    /**
     * Gets the value of the descriptionEU property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescriptionEU() {
        return descriptionEU;
    }

    /**
     * Sets the value of the descriptionEU property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescriptionEU(String value) {
        this.descriptionEU = value;
    }

    /**
     * Gets the value of the isRepeating property.
     * 
     */
    public boolean isIsRepeating() {
        return isRepeating;
    }

    /**
     * Sets the value of the isRepeating property.
     * 
     */
    public void setIsRepeating(boolean value) {
        this.isRepeating = value;
    }

    /**
     * Gets the value of the isReadOnly property.
     * 
     */
    public boolean isIsReadOnly() {
        return isReadOnly;
    }

    /**
     * Sets the value of the isReadOnly property.
     * 
     */
    public void setIsReadOnly(boolean value) {
        this.isReadOnly = value;
    }

    /**
     * Gets the value of the isMandatory property.
     * 
     */
    public boolean isIsMandatory() {
        return isMandatory;
    }

    /**
     * Sets the value of the isMandatory property.
     * 
     */
    public void setIsMandatory(boolean value) {
        this.isMandatory = value;
    }

    /**
     * Gets the value of the isFTRetrievable property.
     * 
     */
    public boolean isIsFTRetrievable() {
        return isFTRetrievable;
    }

    /**
     * Sets the value of the isFTRetrievable property.
     * 
     */
    public void setIsFTRetrievable(boolean value) {
        this.isFTRetrievable = value;
    }

    /**
     * Gets the value of the isFTIndexable property.
     * 
     */
    public boolean isIsFTIndexable() {
        return isFTIndexable;
    }

    /**
     * Sets the value of the isFTIndexable property.
     * 
     */
    public void setIsFTIndexable(boolean value) {
        this.isFTIndexable = value;
    }

    /**
     * Gets the value of the isFTOrderable property.
     * 
     */
    public boolean isIsFTOrderable() {
        return isFTOrderable;
    }

    /**
     * Sets the value of the isFTOrderable property.
     * 
     */
    public void setIsFTOrderable(boolean value) {
        this.isFTOrderable = value;
    }

    /**
     * Gets the value of the valueAssistances property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valueAssistances property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValueAssistances().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link W43DfValueAssistance }
     * 
     * 
     */
    public List<W43DfValueAssistance> getValueAssistances() {
        if (valueAssistances == null) {
            valueAssistances = new ArrayList<W43DfValueAssistance>();
        }
        return this.valueAssistances;
    }

}
