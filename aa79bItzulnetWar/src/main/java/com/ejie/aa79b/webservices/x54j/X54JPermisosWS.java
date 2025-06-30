
package com.ejie.aa79b.webservices.x54j;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for X54JPermisosWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="X54JPermisosWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="des_Cas" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="des_Eus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X54JPermisosWS", propOrder = {
    "codigo",
    "desCas",
    "desEus"
})
public class X54JPermisosWS {

    @XmlElement(required = true, nillable = true)
    protected String codigo;
    @XmlElement(name = "des_Cas", required = true, nillable = true)
    protected String desCas;
    @XmlElement(name = "des_Eus", required = true, nillable = true)
    protected String desEus;

    /**
     * Gets the value of the codigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets the value of the codigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigo(String value) {
        this.codigo = value;
    }

    /**
     * Gets the value of the desCas property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesCas() {
        return desCas;
    }

    /**
     * Sets the value of the desCas property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesCas(String value) {
        this.desCas = value;
    }

    /**
     * Gets the value of the desEus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesEus() {
        return desEus;
    }

    /**
     * Sets the value of the desEus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesEus(String value) {
        this.desEus = value;
    }

}
