
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
 *         &lt;element name="sSessionToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lProcesW93BIds" type="{http://www.openuri.org/}ArrayOfString" minOccurs="0"/>
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
    "sSessionToken",
    "lProcesW93BIds"
})
@XmlRootElement(name = "getProceduresW93B")
public class GetProceduresW93B {

    protected String sSessionToken;
    protected ArrayOfString lProcesW93BIds;

    /**
     * Gets the value of the sSessionToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSSessionToken() {
        return sSessionToken;
    }

    /**
     * Sets the value of the sSessionToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSSessionToken(String value) {
        this.sSessionToken = value;
    }

    /**
     * Gets the value of the lProcesW93BIds property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getLProcesW93BIds() {
        return lProcesW93BIds;
    }

    /**
     * Sets the value of the lProcesW93BIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setLProcesW93BIds(ArrayOfString value) {
        this.lProcesW93BIds = value;
    }

}
