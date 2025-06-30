
package com.ejie.aa79b.webservices.x54j;

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
 *         &lt;element name="result" type="{java:x54j.x54jbeans}X54JRespuestaWS"/>
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
    "result"
})
@XmlRootElement(name = "guardarTrabajadorResponse", namespace = "http://www.ejie.es/webServiceEJB/x54jPermisosWSWar")
public class GuardarTrabajadorResponse {

    @XmlElement(namespace = "http://www.ejie.es/webServiceEJB/x54jPermisosWSWar", required = true)
    protected X54JRespuestaWS result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link X54JRespuestaWS }
     *     
     */
    public X54JRespuestaWS getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link X54JRespuestaWS }
     *     
     */
    public void setResult(X54JRespuestaWS value) {
        this.result = value;
    }

}
