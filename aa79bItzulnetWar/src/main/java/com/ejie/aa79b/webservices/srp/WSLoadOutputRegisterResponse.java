
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
 *         &lt;element name="WSLoadOutputRegisterResult" type="{http://tempuri.org/}WSOutputRegister" minOccurs="0"/>
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
    "wsLoadOutputRegisterResult"
})
@XmlRootElement(name = "WSLoadOutputRegisterResponse")
public class WSLoadOutputRegisterResponse {

    @XmlElement(name = "WSLoadOutputRegisterResult")
    protected WSOutputRegister wsLoadOutputRegisterResult;

    /**
     * Gets the value of the wsLoadOutputRegisterResult property.
     * 
     * @return
     *     possible object is
     *     {@link WSOutputRegister }
     *     
     */
    public WSOutputRegister getWSLoadOutputRegisterResult() {
        return wsLoadOutputRegisterResult;
    }

    /**
     * Sets the value of the wsLoadOutputRegisterResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSOutputRegister }
     *     
     */
    public void setWSLoadOutputRegisterResult(WSOutputRegister value) {
        this.wsLoadOutputRegisterResult = value;
    }

}
