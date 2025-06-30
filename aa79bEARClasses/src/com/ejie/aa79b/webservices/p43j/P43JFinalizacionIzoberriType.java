
package com.ejie.aa79b.webservices.p43j;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for P43JFinalizacionIzoberriType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="P43JFinalizacionIzoberriType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="expeIzo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="expeApli" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rutaPif" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "P43JFinalizacionIzoberriType", propOrder = {
    "expeIzo",
    "expeApli",
    "rutaPif"
})
public class P43JFinalizacionIzoberriType {

    @XmlElement(required = true)
    protected String expeIzo;
    protected String expeApli;
    @XmlElement(required = true)
    protected String rutaPif;

    /**
     * Gets the value of the expeIzo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpeIzo() {
        return expeIzo;
    }

    /**
     * Sets the value of the expeIzo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpeIzo(String value) {
        this.expeIzo = value;
    }

    /**
     * Gets the value of the expeApli property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpeApli() {
        return expeApli;
    }

    /**
     * Sets the value of the expeApli property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpeApli(String value) {
        this.expeApli = value;
    }

    /**
     * Gets the value of the rutaPif property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutaPif() {
        return rutaPif;
    }

    /**
     * Sets the value of the rutaPif property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutaPif(String value) {
        this.rutaPif = value;
    }

}
