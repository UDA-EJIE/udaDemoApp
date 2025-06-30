
package com.ejie.aa79b.webservices.srp;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSParamInputRegister complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSParamInputRegister">
 *   &lt;complexContent>
 *     &lt;extension base="{http://tempuri.org/}WSParamOutputRegister">
 *       &lt;sequence>
 *         &lt;element name="OriginalNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OriginalDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OriginalType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="OriginalEntity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSParamInputRegister", propOrder = {
    "originalNumber",
    "originalDate",
    "originalType",
    "originalEntity"
})
@XmlSeeAlso({
    WSParamInputRegisterEx.class
})
public class WSParamInputRegister
    extends WSParamOutputRegister
{

    @XmlElement(name = "OriginalNumber")
    protected String originalNumber;
    @XmlElement(name = "OriginalDate")
    protected Date originalDate;
    @XmlElement(name = "OriginalType")
    protected Integer originalType;
    @XmlElement(name = "OriginalEntity")
    protected String originalEntity;

    /**
     * Gets the value of the originalNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginalNumber() {
        return originalNumber;
    }

    /**
     * Sets the value of the originalNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalNumber(String value) {
        this.originalNumber = value;
    }

    /**
     * Gets the value of the originalDate property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getOriginalDate() {
        return originalDate;
    }

    /**
     * Sets the value of the originalDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setOriginalDate(Date value) {
        this.originalDate = value;
    }

    /**
     * Gets the value of the originalType property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOriginalType() {
        return originalType;
    }

    /**
     * Sets the value of the originalType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOriginalType(Integer value) {
        this.originalType = value;
    }

    /**
     * Gets the value of the originalEntity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginalEntity() {
        return originalEntity;
    }

    /**
     * Sets the value of the originalEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalEntity(String value) {
        this.originalEntity = value;
    }

}
