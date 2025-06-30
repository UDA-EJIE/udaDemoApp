
package com.ejie.aa79b.webservices.srt;

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
 *         &lt;element name="sXmlExitRegistryCreationInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sXmlExitRegistryCreationInfo"
})
@XmlRootElement(name = "createExitRegistry")
public class CreateExitRegistry {

    protected String sSessionToken;
    protected String sXmlExitRegistryCreationInfo;

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
     * Gets the value of the sXmlExitRegistryCreationInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSXmlExitRegistryCreationInfo() {
        return sXmlExitRegistryCreationInfo;
    }

    /**
     * Sets the value of the sXmlExitRegistryCreationInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSXmlExitRegistryCreationInfo(String value) {
        this.sXmlExitRegistryCreationInfo = value;
    }

}
