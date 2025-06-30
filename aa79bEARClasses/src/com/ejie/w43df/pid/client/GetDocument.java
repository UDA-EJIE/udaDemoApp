
package com.ejie.w43df.pid.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getDocument complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getDocument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getDocument" type="{com/ejie/documents/xml}w43DfGETDocument" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDocument", propOrder = {
    "getDocument"
})
public class GetDocument {

    @XmlElement(namespace = "com/ejie/documents/xml")
    protected W43DfGETDocument getDocument;

    /**
     * Gets the value of the getDocument property.
     * 
     * @return
     *     possible object is
     *     {@link W43DfGETDocument }
     *     
     */
    public W43DfGETDocument getGetDocument() {
        return getDocument;
    }

    /**
     * Sets the value of the getDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link W43DfGETDocument }
     *     
     */
    public void setGetDocument(W43DfGETDocument value) {
        this.getDocument = value;
    }

}
