
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
 *         &lt;element name="getNotifiedActFACResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getNotifiedActFACResult"
})
@XmlRootElement(name = "getNotifiedActFACResponse")
public class GetNotifiedActFACResponse {

    protected String getNotifiedActFACResult;

    /**
     * Gets the value of the getNotifiedActFACResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetNotifiedActFACResult() {
        return getNotifiedActFACResult;
    }

    /**
     * Sets the value of the getNotifiedActFACResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetNotifiedActFACResult(String value) {
        this.getNotifiedActFACResult = value;
    }

}
