
package com.ejie.aa79b.webservices.cp;

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
 *         &lt;element name="getProceduresPlateaResult" type="{http://www.openuri.org/}Map"/>
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
    "getProceduresPlateaResult"
})
@XmlRootElement(name = "getProceduresPlateaResponse")
public class GetProceduresPlateaResponse {

    @XmlElement(required = true)
    protected Map getProceduresPlateaResult;

    /**
     * Gets the value of the getProceduresPlateaResult property.
     * 
     * @return
     *     possible object is
     *     {@link Map }
     *     
     */
    public Map getGetProceduresPlateaResult() {
        return getProceduresPlateaResult;
    }

    /**
     * Sets the value of the getProceduresPlateaResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Map }
     *     
     */
    public void setGetProceduresPlateaResult(Map value) {
        this.getProceduresPlateaResult = value;
    }

}
