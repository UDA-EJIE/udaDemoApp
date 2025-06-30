
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
 *         &lt;element name="paramPruebaCompuesto" type="{http://www.ejie.es/webServiceClase/p43jWebServicesWar}P43JPruebaParamType"/>
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
    "paramPruebaCompuesto"
})
@XmlRootElement(name = "testCompuesto")
public class TestCompuesto {

    @XmlElement(required = true)
    protected P43JPruebaParamType paramPruebaCompuesto;

    /**
     * Gets the value of the paramPruebaCompuesto property.
     * 
     * @return
     *     possible object is
     *     {@link P43JPruebaParamType }
     *     
     */
    public P43JPruebaParamType getParamPruebaCompuesto() {
        return paramPruebaCompuesto;
    }

    /**
     * Sets the value of the paramPruebaCompuesto property.
     * 
     * @param value
     *     allowed object is
     *     {@link P43JPruebaParamType }
     *     
     */
    public void setParamPruebaCompuesto(P43JPruebaParamType value) {
        this.paramPruebaCompuesto = value;
    }

}
