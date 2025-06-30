
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
 *         &lt;element name="sTelematicActionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sInstanceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sProcedureID",
    "sTelematicActionID",
    "sInstanceId"
})
@XmlRootElement(name = "getManagingUnitInfoForTelematicActionV22")
public class GetManagingUnitInfoForTelematicActionV22 {

    protected String sSessionToken;
    protected String sProcedureID;
    protected String sTelematicActionID;
    protected String sInstanceId;

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

    /**
     * Gets the value of the sTelematicActionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTelematicActionID() {
        return sTelematicActionID;
    }

    /**
     * Sets the value of the sTelematicActionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTelematicActionID(String value) {
        this.sTelematicActionID = value;
    }

    /**
     * Gets the value of the sInstanceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSInstanceId() {
        return sInstanceId;
    }

    /**
     * Sets the value of the sInstanceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSInstanceId(String value) {
        this.sInstanceId = value;
    }

}
