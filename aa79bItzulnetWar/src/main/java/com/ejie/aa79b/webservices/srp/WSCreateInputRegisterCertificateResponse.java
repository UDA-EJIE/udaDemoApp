
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
 *         &lt;element name="WSCreateInputRegisterCertificateResult" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
    "wsCreateInputRegisterCertificateResult"
})
@XmlRootElement(name = "WSCreateInputRegisterCertificateResponse")
public class WSCreateInputRegisterCertificateResponse {

    @XmlElement(name = "WSCreateInputRegisterCertificateResult")
    protected byte[] wsCreateInputRegisterCertificateResult;

    /**
     * Gets the value of the wsCreateInputRegisterCertificateResult property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getWSCreateInputRegisterCertificateResult() {
        return wsCreateInputRegisterCertificateResult;
    }

    /**
     * Sets the value of the wsCreateInputRegisterCertificateResult property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setWSCreateInputRegisterCertificateResult(byte[] value) {
        this.wsCreateInputRegisterCertificateResult = ((byte[]) value);
    }

}
