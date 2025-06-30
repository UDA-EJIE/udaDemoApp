
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
 *         &lt;element name="x54JTrabajadorWS" type="{java:x54j.x54jbeans}X54JTrabajadorWS"/>
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
    "x54JTrabajadorWS",
    "x54JUsuarioWS"
})
@XmlRootElement(name = "guardarTrabajador", namespace = "http://www.ejie.es/webServiceEJB/x54jPermisosWSWar")
public class GuardarTrabajador {

    @XmlElement(namespace = "http://www.ejie.es/webServiceEJB/x54jPermisosWSWar", required = true)
    protected X54JTrabajadorWS x54JTrabajadorWS;
    @XmlElement(namespace = "http://www.ejie.es/webServiceEJB/x54jPermisosWSWar", required = true)
    protected X54JUsuarioWS x54JUsuarioWS;

    /**
     * Gets the value of the x54JTrabajadorWS property.
     * 
     * @return
     *     possible object is
     *     {@link X54JTrabajadorWS }
     *     
     */
    public X54JTrabajadorWS getX54JTrabajadorWS() {
        return x54JTrabajadorWS;
    }

    /**
     * Sets the value of the x54JTrabajadorWS property.
     * 
     * @param value
     *     allowed object is
     *     {@link X54JTrabajadorWS }
     *     
     */
    public void setX54JTrabajadorWS(X54JTrabajadorWS value) {
        this.x54JTrabajadorWS = value;
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
