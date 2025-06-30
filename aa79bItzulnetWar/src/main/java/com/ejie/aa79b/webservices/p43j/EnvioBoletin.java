
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
 *         &lt;element name="paramEnvioBoletin" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "paramEnvioBoletin"
})
@XmlRootElement(name = "envioBoletin")
public class EnvioBoletin {

    @XmlElement(required = true)
    protected String paramEnvioBoletin;

    /**
     * Gets the value of the paramEnvioBoletin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParamEnvioBoletin() {
        return paramEnvioBoletin;
    }

    /**
     * Sets the value of the paramEnvioBoletin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParamEnvioBoletin(String value) {
        this.paramEnvioBoletin = value;
    }

}
