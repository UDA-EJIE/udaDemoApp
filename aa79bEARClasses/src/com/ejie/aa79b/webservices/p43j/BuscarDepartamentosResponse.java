
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
 *         &lt;element name="buscarDepartamentosResult" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "buscarDepartamentosResult"
})
@XmlRootElement(name = "buscarDepartamentosResponse")
public class BuscarDepartamentosResponse {

    @XmlElement(required = true)
    protected String buscarDepartamentosResult;

    /**
     * Gets the value of the buscarDepartamentosResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuscarDepartamentosResult() {
        return buscarDepartamentosResult;
    }

    /**
     * Sets the value of the buscarDepartamentosResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuscarDepartamentosResult(String value) {
        this.buscarDepartamentosResult = value;
    }

}
