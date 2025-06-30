
package com.ejie.aa79b.webservices.srp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="WSLoadUnitsFromUnitResult" type="{http://tempuri.org/}WSUnitsResponse" minOccurs="0"/>
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
    "wsLoadUnitsFromUnitResult"
})
@XmlRootElement(name = "WSLoadUnitsFromUnitResponse")
public class WSLoadUnitsFromUnitResponse {

    @XmlElement(name = "WSLoadUnitsFromUnitResult")
    protected WSUnitsResponse wsLoadUnitsFromUnitResult;

    /**
     * Gets the value of the wsLoadUnitsFromUnitResult property.
     * 
     * @return
     *     possible object is
     *     {@link WSUnitsResponse }
     *     
     */
    public WSUnitsResponse getWSLoadUnitsFromUnitResult() {
        return wsLoadUnitsFromUnitResult;
    }

    /**
     * Sets the value of the wsLoadUnitsFromUnitResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSUnitsResponse }
     *     
     */
    public void setWSLoadUnitsFromUnitResult(WSUnitsResponse value) {
        this.wsLoadUnitsFromUnitResult = value;
    }

}
