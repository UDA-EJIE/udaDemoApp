
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
 *         &lt;element name="WSUpdateInputRegisterResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "wsUpdateInputRegisterResult"
})
@XmlRootElement(name = "WSUpdateInputRegisterResponse")
public class WSUpdateInputRegisterResponse {

    @XmlElement(name = "WSUpdateInputRegisterResult")
    protected boolean wsUpdateInputRegisterResult;

    /**
     * Gets the value of the wsUpdateInputRegisterResult property.
     * 
     */
    public boolean isWSUpdateInputRegisterResult() {
        return wsUpdateInputRegisterResult;
    }

    /**
     * Sets the value of the wsUpdateInputRegisterResult property.
     * 
     */
    public void setWSUpdateInputRegisterResult(boolean value) {
        this.wsUpdateInputRegisterResult = value;
    }

}
