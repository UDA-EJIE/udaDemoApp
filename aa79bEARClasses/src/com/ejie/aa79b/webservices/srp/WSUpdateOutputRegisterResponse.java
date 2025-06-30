
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
 *         &lt;element name="WSUpdateOutputRegisterResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "wsUpdateOutputRegisterResult"
})
@XmlRootElement(name = "WSUpdateOutputRegisterResponse")
public class WSUpdateOutputRegisterResponse {

    @XmlElement(name = "WSUpdateOutputRegisterResult")
    protected boolean wsUpdateOutputRegisterResult;

    /**
     * Gets the value of the wsUpdateOutputRegisterResult property.
     * 
     */
    public boolean isWSUpdateOutputRegisterResult() {
        return wsUpdateOutputRegisterResult;
    }

    /**
     * Sets the value of the wsUpdateOutputRegisterResult property.
     * 
     */
    public void setWSUpdateOutputRegisterResult(boolean value) {
        this.wsUpdateOutputRegisterResult = value;
    }

}
