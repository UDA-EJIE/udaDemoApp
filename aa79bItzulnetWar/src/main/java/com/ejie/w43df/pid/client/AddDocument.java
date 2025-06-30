
package com.ejie.w43df.pid.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addDocument complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addDocument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="newDocument" type="{com/ejie/documents/xml}w43DfDocument" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addDocument", propOrder = {
    "newDocument"
})
public class AddDocument {

    @XmlElement(namespace = "com/ejie/documents/xml")
    protected W43DfDocument newDocument;

    /**
     * Gets the value of the newDocument property.
     * 
     * @return
     *     possible object is
     *     {@link W43DfDocument }
     *     
     */
    public W43DfDocument getNewDocument() {
        return newDocument;
    }

    /**
     * Sets the value of the newDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link W43DfDocument }
     *     
     */
    public void setNewDocument(W43DfDocument value) {
        this.newDocument = value;
    }

}
