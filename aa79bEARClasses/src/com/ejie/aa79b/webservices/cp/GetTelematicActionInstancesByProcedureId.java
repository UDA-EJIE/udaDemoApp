
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
 *         &lt;element name="sProcedureID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sProcedureID"
})
@XmlRootElement(name = "getTelematicActionInstancesByProcedureId")
public class GetTelematicActionInstancesByProcedureId {

    protected String sSessionToken;
    protected String sProcedureID;

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
     * Gets the value of the sProcedureID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSProcedureID() {
        return sProcedureID;
    }

    /**
     * Sets the value of the sProcedureID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSProcedureID(String value) {
        this.sProcedureID = value;
    }

}
