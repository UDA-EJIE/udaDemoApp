
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
 *         &lt;element name="searchProceduresResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "searchProceduresResult"
})
@XmlRootElement(name = "searchProceduresResponse")
public class SearchProceduresResponse {

    protected String searchProceduresResult;

    /**
     * Gets the value of the searchProceduresResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchProceduresResult() {
        return searchProceduresResult;
    }

    /**
     * Sets the value of the searchProceduresResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchProceduresResult(String value) {
        this.searchProceduresResult = value;
    }

}
