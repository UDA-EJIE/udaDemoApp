
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
 *         &lt;element name="getNotifiedActForProcedureFACResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getNotifiedActForProcedureFACResult"
})
@XmlRootElement(name = "getNotifiedActForProcedureFACResponse")
public class GetNotifiedActForProcedureFACResponse {

    protected String getNotifiedActForProcedureFACResult;

    /**
     * Gets the value of the getNotifiedActForProcedureFACResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetNotifiedActForProcedureFACResult() {
        return getNotifiedActForProcedureFACResult;
    }

    /**
     * Sets the value of the getNotifiedActForProcedureFACResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetNotifiedActForProcedureFACResult(String value) {
        this.getNotifiedActForProcedureFACResult = value;
    }

}
