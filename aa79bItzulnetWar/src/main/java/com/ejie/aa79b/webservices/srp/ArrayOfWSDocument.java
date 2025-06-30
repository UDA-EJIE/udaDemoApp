
package com.ejie.aa79b.webservices.srp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfWSDocument complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWSDocument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WSDocument" type="{http://tempuri.org/}WSDocument" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWSDocument", propOrder = {
    "wsDocument"
})
public class ArrayOfWSDocument {

    @XmlElement(name = "WSDocument", nillable = true)
    protected List<WSDocument> wsDocument;

    /**
     * Gets the value of the wsDocument property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsDocument property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSDocument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSDocument }
     * 
     * 
     */
    public List<WSDocument> getWSDocument() {
        if (wsDocument == null) {
            wsDocument = new ArrayList<WSDocument>();
        }
        return this.wsDocument;
    }

}
