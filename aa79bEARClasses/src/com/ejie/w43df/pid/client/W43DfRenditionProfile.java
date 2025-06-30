
package com.ejie.w43df.pid.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for w43DfRenditionProfile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="w43DfRenditionProfile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceFormat" type="{com/ejie/documents/xml}w43DfFormat" minOccurs="0"/>
 *         &lt;element name="targetFormats" type="{com/ejie/documents/xml}w43DfFormat" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="parameterNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "w43DfRenditionProfile", propOrder = {
    "name",
    "description",
    "sourceFormat",
    "targetFormats",
    "parameterNames"
})
public class W43DfRenditionProfile {

    protected String name;
    protected String description;
    protected W43DfFormat sourceFormat;
    protected List<W43DfFormat> targetFormats;
    protected List<String> parameterNames;

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
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the sourceFormat property.
     * 
     * @return
     *     possible object is
     *     {@link W43DfFormat }
     *     
     */
    public W43DfFormat getSourceFormat() {
        return sourceFormat;
    }

    /**
     * Sets the value of the sourceFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link W43DfFormat }
     *     
     */
    public void setSourceFormat(W43DfFormat value) {
        this.sourceFormat = value;
    }

    /**
     * Gets the value of the targetFormats property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the targetFormats property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTargetFormats().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link W43DfFormat }
     * 
     * 
     */
    public List<W43DfFormat> getTargetFormats() {
        if (targetFormats == null) {
            targetFormats = new ArrayList<W43DfFormat>();
        }
        return this.targetFormats;
    }

    /**
     * Gets the value of the parameterNames property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameterNames property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameterNames().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getParameterNames() {
        if (parameterNames == null) {
            parameterNames = new ArrayList<String>();
        }
        return this.parameterNames;
    }

}
