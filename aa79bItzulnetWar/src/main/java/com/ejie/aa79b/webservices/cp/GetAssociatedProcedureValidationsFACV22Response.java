
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
 *         &lt;element name="getAssociatedProcedureValidationsFACV22Result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getAssociatedProcedureValidationsFACV22Result"
})
@XmlRootElement(name = "getAssociatedProcedureValidationsFACV22Response")
public class GetAssociatedProcedureValidationsFACV22Response {

    protected String getAssociatedProcedureValidationsFACV22Result;

    /**
     * Gets the value of the getAssociatedProcedureValidationsFACV22Result property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetAssociatedProcedureValidationsFACV22Result() {
        return getAssociatedProcedureValidationsFACV22Result;
    }

    /**
     * Sets the value of the getAssociatedProcedureValidationsFACV22Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetAssociatedProcedureValidationsFACV22Result(String value) {
        this.getAssociatedProcedureValidationsFACV22Result = value;
    }

}
