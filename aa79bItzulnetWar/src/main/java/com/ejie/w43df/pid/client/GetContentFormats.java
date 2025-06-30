
package com.ejie.w43df.pid.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getContentFormats complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getContentFormats">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getFormat" type="{com/ejie/documents/xml}w43DfFormat" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getContentFormats", propOrder = {
    "getFormat"
})
public class GetContentFormats {

    @XmlElement(namespace = "com/ejie/documents/xml")
    protected W43DfFormat getFormat;

    /**
     * Gets the value of the getFormat property.
     * 
     * @return
     *     possible object is
     *     {@link W43DfFormat }
     *     
     */
    public W43DfFormat getGetFormat() {
        return getFormat;
    }

    /**
     * Sets the value of the getFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link W43DfFormat }
     *     
     */
    public void setGetFormat(W43DfFormat value) {
        this.getFormat = value;
    }

}
