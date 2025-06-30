
package com.ejie.aa79b.webservices.x54j;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for X54JUsuarioWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="X54JUsuarioWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codAplic" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipIden" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="preDni" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dni" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sufDni" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userXlnets" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ipPuesto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idioma" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X54JUsuarioWS", propOrder = {
    "codAplic",
    "tipIden",
    "preDni",
    "dni",
    "sufDni",
    "userXlnets",
    "ipPuesto",
    "idioma"
})
public class X54JUsuarioWS {

    @XmlElement(required = true, nillable = true)
    protected String codAplic;
    @XmlElement(required = true, nillable = true)
    protected String tipIden;
    @XmlElement(required = true, nillable = true)
    protected String preDni;
    @XmlElement(required = true, nillable = true)
    protected String dni;
    @XmlElement(required = true, nillable = true)
    protected String sufDni;
    @XmlElement(required = true, nillable = true)
    protected String userXlnets;
    @XmlElement(required = true, nillable = true)
    protected String ipPuesto;
    @XmlElement(required = true, nillable = true)
    protected String idioma;

    /**
     * Gets the value of the codAplic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodAplic() {
        return codAplic;
    }

    /**
     * Sets the value of the codAplic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodAplic(String value) {
        this.codAplic = value;
    }

    /**
     * Gets the value of the tipIden property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipIden() {
        return tipIden;
    }

    /**
     * Sets the value of the tipIden property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipIden(String value) {
        this.tipIden = value;
    }

    /**
     * Gets the value of the preDni property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreDni() {
        return preDni;
    }

    /**
     * Sets the value of the preDni property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreDni(String value) {
        this.preDni = value;
    }

    /**
     * Gets the value of the dni property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDni() {
        return dni;
    }

    /**
     * Sets the value of the dni property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDni(String value) {
        this.dni = value;
    }

    /**
     * Gets the value of the sufDni property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSufDni() {
        return sufDni;
    }

    /**
     * Sets the value of the sufDni property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSufDni(String value) {
        this.sufDni = value;
    }

    /**
     * Gets the value of the userXlnets property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserXlnets() {
        return userXlnets;
    }

    /**
     * Sets the value of the userXlnets property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserXlnets(String value) {
        this.userXlnets = value;
    }

    /**
     * Gets the value of the ipPuesto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpPuesto() {
        return ipPuesto;
    }

    /**
     * Sets the value of the ipPuesto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpPuesto(String value) {
        this.ipPuesto = value;
    }

    /**
     * Gets the value of the idioma property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * Sets the value of the idioma property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdioma(String value) {
        this.idioma = value;
    }

}
