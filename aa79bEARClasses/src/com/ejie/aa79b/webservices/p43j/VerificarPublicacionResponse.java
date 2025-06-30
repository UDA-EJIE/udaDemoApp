
package com.ejie.aa79b.webservices.p43j;

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
 *         &lt;element name="verificarPublicacionResult" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "verificarPublicacionResult"
})
@XmlRootElement(name = "verificarPublicacionResponse")
public class VerificarPublicacionResponse {

    @XmlElement(required = true)
    protected String verificarPublicacionResult;

    /**
     * Gets the value of the verificarPublicacionResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerificarPublicacionResult() {
        return verificarPublicacionResult;
    }

    /**
     * Sets the value of the verificarPublicacionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerificarPublicacionResult(String value) {
        this.verificarPublicacionResult = value;
    }

}
