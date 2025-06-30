
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
 *         &lt;element name="numeroOrganismoHistorico" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroOrganismoAntiguo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroOrganismoNuevo" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "numeroOrganismoHistorico",
    "numeroOrganismoAntiguo",
    "numeroOrganismoNuevo"
})
@XmlRootElement(name = "modificarOrganismo")
public class ModificarOrganismo {

    @XmlElement(required = true)
    protected String numeroOrganismoHistorico;
    @XmlElement(required = true)
    protected String numeroOrganismoAntiguo;
    @XmlElement(required = true)
    protected String numeroOrganismoNuevo;

    /**
     * Gets the value of the numeroOrganismoHistorico property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroOrganismoHistorico() {
        return numeroOrganismoHistorico;
    }

    /**
     * Sets the value of the numeroOrganismoHistorico property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroOrganismoHistorico(String value) {
        this.numeroOrganismoHistorico = value;
    }

    /**
     * Gets the value of the numeroOrganismoAntiguo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroOrganismoAntiguo() {
        return numeroOrganismoAntiguo;
    }

    /**
     * Sets the value of the numeroOrganismoAntiguo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroOrganismoAntiguo(String value) {
        this.numeroOrganismoAntiguo = value;
    }

    /**
     * Gets the value of the numeroOrganismoNuevo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroOrganismoNuevo() {
        return numeroOrganismoNuevo;
    }

    /**
     * Sets the value of the numeroOrganismoNuevo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroOrganismoNuevo(String value) {
        this.numeroOrganismoNuevo = value;
    }

}
