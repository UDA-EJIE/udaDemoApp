
package com.ejie.aa79b.webservices.cp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="getProcedureNamesByIdsXmlResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getProcedureNamesByIdsXmlResult"
})
@XmlRootElement(name = "getProcedureNamesByIdsXmlResponse")
public class GetProcedureNamesByIdsXmlResponse {

    protected String getProcedureNamesByIdsXmlResult;

    /**
     * Gets the value of the getProcedureNamesByIdsXmlResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetProcedureNamesByIdsXmlResult() {
        return getProcedureNamesByIdsXmlResult;
    }

    /**
     * Sets the value of the getProcedureNamesByIdsXmlResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetProcedureNamesByIdsXmlResult(String value) {
        this.getProcedureNamesByIdsXmlResult = value;
    }

}
