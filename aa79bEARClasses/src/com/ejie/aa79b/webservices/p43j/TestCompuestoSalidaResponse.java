
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
 *         &lt;element name="resultado" type="{http://www.ejie.es/webServiceClase/p43jWebServicesWar}P43JPruebaRespuestaType"/>
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
    "resultado"
})
@XmlRootElement(name = "testCompuestoSalidaResponse")
public class TestCompuestoSalidaResponse {

    @XmlElement(required = true)
    protected P43JPruebaRespuestaType resultado;

    /**
     * Gets the value of the resultado property.
     * 
     * @return
     *     possible object is
     *     {@link P43JPruebaRespuestaType }
     *     
     */
    public P43JPruebaRespuestaType getResultado() {
        return resultado;
    }

    /**
     * Sets the value of the resultado property.
     * 
     * @param value
     *     allowed object is
     *     {@link P43JPruebaRespuestaType }
     *     
     */
    public void setResultado(P43JPruebaRespuestaType value) {
        this.resultado = value;
    }

}
