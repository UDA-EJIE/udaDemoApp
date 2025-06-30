
package com.ejie.aa79b.webservices.x54j;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for X54JPeticionAnadirRolWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="X54JPeticionAnadirRolWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dni" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="aplicacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rol" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="permisos" type="{java:x54j.x54jbeans}ArrayOfX54JPermisosWS" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X54JPeticionAnadirRolWS", propOrder = {
    "dni",
    "aplicacion",
    "rol",
    "permisos"
})
public class X54JPeticionAnadirRolWS {

    @XmlElement(required = true, nillable = true)
    protected String dni;
    @XmlElement(required = true, nillable = true)
    protected String aplicacion;
    @XmlElement(required = true, nillable = true)
    protected String rol;
    @XmlElementRef(name = "permisos", namespace = "java:x54j.x54jbeans", type = JAXBElement.class)
    protected JAXBElement<ArrayOfX54JPermisosWS> permisos;

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
     * Gets the value of the aplicacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAplicacion() {
        return aplicacion;
    }

    /**
     * Sets the value of the aplicacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAplicacion(String value) {
        this.aplicacion = value;
    }

    /**
     * Gets the value of the rol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRol() {
        return rol;
    }

    /**
     * Sets the value of the rol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRol(String value) {
        this.rol = value;
    }

    /**
     * Gets the value of the permisos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfX54JPermisosWS }{@code >}
     *     
     */
    public JAXBElement<ArrayOfX54JPermisosWS> getPermisos() {
        return permisos;
    }

    /**
     * Sets the value of the permisos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfX54JPermisosWS }{@code >}
     *     
     */
    public void setPermisos(JAXBElement<ArrayOfX54JPermisosWS> value) {
        this.permisos = ((JAXBElement<ArrayOfX54JPermisosWS> ) value);
    }

}
