
package com.ejie.w43df.pid.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for copyDocument complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="copyDocument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="copyDocument" type="{com/ejie/documents/xml}w43DfDocument" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "copyDocument", propOrder = {
    "copyDocument"
})
public class CopyDocument {

    @XmlElement(namespace = "com/ejie/documents/xml")
    protected W43DfDocument copyDocument;

    /**
     * Gets the value of the copyDocument property.
     * 
     * @return
     *     possible object is
     *     {@link W43DfDocument }
     *     
     */
    public W43DfDocument getCopyDocument() {
        return copyDocument;
    }

    /**
     * Sets the value of the copyDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link W43DfDocument }
     *     
     */
    public void setCopyDocument(W43DfDocument value) {
        this.copyDocument = value;
    }

}
