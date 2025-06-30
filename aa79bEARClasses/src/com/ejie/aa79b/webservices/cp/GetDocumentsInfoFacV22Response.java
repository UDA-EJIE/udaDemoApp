
package com.ejie.aa79b.webservices.cp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getDocumentsInfoFacV22Result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getDocumentsInfoFacV22Result"
})
@XmlRootElement(name = "getDocumentsInfoFacV22Response")
public class GetDocumentsInfoFacV22Response {

    protected String getDocumentsInfoFacV22Result;

    /**
     * Gets the value of the getDocumentsInfoFacV22Result property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetDocumentsInfoFacV22Result() {
        return getDocumentsInfoFacV22Result;
    }

    /**
     * Sets the value of the getDocumentsInfoFacV22Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetDocumentsInfoFacV22Result(String value) {
        this.getDocumentsInfoFacV22Result = value;
    }

}
