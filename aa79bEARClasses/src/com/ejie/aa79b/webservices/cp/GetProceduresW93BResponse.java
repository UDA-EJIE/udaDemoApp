
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
 *         &lt;element name="getProceduresW93BResult" type="{http://www.openuri.org/}Map"/>
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
    "getProceduresW93BResult"
})
@XmlRootElement(name = "getProceduresW93BResponse")
public class GetProceduresW93BResponse {

    @XmlElement(required = true)
    protected Map getProceduresW93BResult;

    /**
     * Gets the value of the getProceduresW93BResult property.
     * 
     * @return
     *     possible object is
     *     {@link Map }
     *     
     */
    public Map getGetProceduresW93BResult() {
        return getProceduresW93BResult;
    }

    /**
     * Sets the value of the getProceduresW93BResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Map }
     *     
     */
    public void setGetProceduresW93BResult(Map value) {
        this.getProceduresW93BResult = value;
    }

}
