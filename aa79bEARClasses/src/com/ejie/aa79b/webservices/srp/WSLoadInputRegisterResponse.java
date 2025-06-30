
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
 *         &lt;element name="WSLoadInputRegisterResult" type="{http://tempuri.org/}WSInputRegister" minOccurs="0"/>
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
    "wsLoadInputRegisterResult"
})
@XmlRootElement(name = "WSLoadInputRegisterResponse")
public class WSLoadInputRegisterResponse {

    @XmlElement(name = "WSLoadInputRegisterResult")
    protected WSInputRegister wsLoadInputRegisterResult;

    /**
     * Gets the value of the wsLoadInputRegisterResult property.
     * 
     * @return
     *     possible object is
     *     {@link WSInputRegister }
     *     
     */
    public WSInputRegister getWSLoadInputRegisterResult() {
        return wsLoadInputRegisterResult;
    }

    /**
     * Sets the value of the wsLoadInputRegisterResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSInputRegister }
     *     
     */
    public void setWSLoadInputRegisterResult(WSInputRegister value) {
        this.wsLoadInputRegisterResult = value;
    }

}
