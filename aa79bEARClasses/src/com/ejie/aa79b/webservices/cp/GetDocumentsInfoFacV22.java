
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
 *         &lt;element name="sProcedureId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sInnerDocument" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sInstanceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stelematicActionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sProcedureId",
    "sInnerDocument",
    "sInstanceId",
    "stelematicActionId"
})
@XmlRootElement(name = "getDocumentsInfoFacV22")
public class GetDocumentsInfoFacV22 {

    protected String sSessionToken;
    protected String sProcedureId;
    protected String sInnerDocument;
    protected String sInstanceId;
    protected String stelematicActionId;

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
     * Gets the value of the sProcedureId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSProcedureId() {
        return sProcedureId;
    }

    /**
     * Sets the value of the sProcedureId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSProcedureId(String value) {
        this.sProcedureId = value;
    }

    /**
     * Gets the value of the sInnerDocument property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSInnerDocument() {
        return sInnerDocument;
    }

    /**
     * Sets the value of the sInnerDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSInnerDocument(String value) {
        this.sInnerDocument = value;
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

    /**
     * Gets the value of the stelematicActionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStelematicActionId() {
        return stelematicActionId;
    }

    /**
     * Sets the value of the stelematicActionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStelematicActionId(String value) {
        this.stelematicActionId = value;
    }

}
