
package com.ejie.aa79b.webservices.x54j;

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
 *         &lt;element name="x54JPeticionWS" type="{java:x54j.x54jbeans}X54JPeticionWS"/>
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
    "x54JPeticionWS"
})
@XmlRootElement(name = "actualizarDatosContacto", namespace = "http://www.ejie.es/webServiceEJB/x54jPermisosWSWar")
public class ActualizarDatosContacto {

    @XmlElement(namespace = "http://www.ejie.es/webServiceEJB/x54jPermisosWSWar", required = true)
    protected X54JPeticionWS x54JPeticionWS;

    /**
     * Gets the value of the x54JPeticionWS property.
     * 
     * @return
     *     possible object is
     *     {@link X54JPeticionWS }
     *     
     */
    public X54JPeticionWS getX54JPeticionWS() {
        return x54JPeticionWS;
    }

    /**
     * Sets the value of the x54JPeticionWS property.
     * 
     * @param value
     *     allowed object is
     *     {@link X54JPeticionWS }
     *     
     */
    public void setX54JPeticionWS(X54JPeticionWS value) {
        this.x54JPeticionWS = value;
    }

}
