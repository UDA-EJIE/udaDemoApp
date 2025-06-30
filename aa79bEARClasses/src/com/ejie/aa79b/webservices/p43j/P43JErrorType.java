
package com.ejie.aa79b.webservices.p43j;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for P43JErrorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="P43JErrorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="errorTipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="errorTexto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "P43JErrorType", propOrder = {
    "errorTipo",
    "errorTexto",
    "errorData"
})
public class P43JErrorType {

    @XmlElement(required = true)
    protected String errorTipo;
    protected String errorTexto;
    protected String errorData;

    /**
     * Gets the value of the errorTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorTipo() {
        return errorTipo;
    }

    /**
     * Sets the value of the errorTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorTipo(String value) {
        this.errorTipo = value;
    }

    /**
     * Gets the value of the errorTexto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorTexto() {
        return errorTexto;
    }

    /**
     * Sets the value of the errorTexto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorTexto(String value) {
        this.errorTexto = value;
    }

    /**
     * Gets the value of the errorData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorData() {
        return errorData;
    }

    /**
     * Sets the value of the errorData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorData(String value) {
        this.errorData = value;
    }

}
