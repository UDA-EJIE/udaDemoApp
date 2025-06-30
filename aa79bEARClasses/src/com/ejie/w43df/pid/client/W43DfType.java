
package com.ejie.w43df.pid.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for w43DfType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="w43DfType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descriptionCA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descriptionEU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="metadatas" type="{com/ejie/documents/xml}w43DfMetadata" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="childType" type="{com/ejie/documents/xml}w43DfType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "w43DfType", propOrder = {
    "name",
    "descriptionCA",
    "descriptionEU",
    "metadatas",
    "childType"
})
public class W43DfType {

    protected String name;
    protected String descriptionCA;
    protected String descriptionEU;
    protected List<W43DfMetadata> metadatas;
    protected W43DfType childType;

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
     * Gets the value of the metadatas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metadatas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetadatas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link W43DfMetadata }
     * 
     * 
     */
    public List<W43DfMetadata> getMetadatas() {
        if (metadatas == null) {
            metadatas = new ArrayList<W43DfMetadata>();
        }
        return this.metadatas;
    }

    /**
     * Gets the value of the childType property.
     * 
     * @return
     *     possible object is
     *     {@link W43DfType }
     *     
     */
    public W43DfType getChildType() {
        return childType;
    }

    /**
     * Sets the value of the childType property.
     * 
     * @param value
     *     allowed object is
     *     {@link W43DfType }
     *     
     */
    public void setChildType(W43DfType value) {
        this.childType = value;
    }

}
