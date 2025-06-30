
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
 *         &lt;element name="x54JPeticionAnadirRolWS" type="{java:x54j.x54jbeans}X54JPeticionAnadirRolWS"/>
 *         &lt;element name="x54JUsuarioWS" type="{java:x54j.x54jbeans}X54JUsuarioWS"/>
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
    "x54JPeticionAnadirRolWS",
    "x54JUsuarioWS"
})
@XmlRootElement(name = "anadirRol", namespace = "http://www.ejie.es/webServiceEJB/x54jPermisosWSWar")
public class AnadirRol {

    @XmlElement(namespace = "http://www.ejie.es/webServiceEJB/x54jPermisosWSWar", required = true)
    protected X54JPeticionAnadirRolWS x54JPeticionAnadirRolWS;
    @XmlElement(namespace = "http://www.ejie.es/webServiceEJB/x54jPermisosWSWar", required = true)
    protected X54JUsuarioWS x54JUsuarioWS;

    /**
     * Gets the value of the x54JPeticionAnadirRolWS property.
     * 
     * @return
     *     possible object is
     *     {@link X54JPeticionAnadirRolWS }
     *     
     */
    public X54JPeticionAnadirRolWS getX54JPeticionAnadirRolWS() {
        return x54JPeticionAnadirRolWS;
    }

    /**
     * Sets the value of the x54JPeticionAnadirRolWS property.
     * 
     * @param value
     *     allowed object is
     *     {@link X54JPeticionAnadirRolWS }
     *     
     */
    public void setX54JPeticionAnadirRolWS(X54JPeticionAnadirRolWS value) {
        this.x54JPeticionAnadirRolWS = value;
    }

    /**
     * Gets the value of the x54JUsuarioWS property.
     * 
     * @return
     *     possible object is
     *     {@link X54JUsuarioWS }
     *     
     */
    public X54JUsuarioWS getX54JUsuarioWS() {
        return x54JUsuarioWS;
    }

    /**
     * Sets the value of the x54JUsuarioWS property.
     * 
     * @param value
     *     allowed object is
     *     {@link X54JUsuarioWS }
     *     
     */
    public void setX54JUsuarioWS(X54JUsuarioWS value) {
        this.x54JUsuarioWS = value;
    }

}
