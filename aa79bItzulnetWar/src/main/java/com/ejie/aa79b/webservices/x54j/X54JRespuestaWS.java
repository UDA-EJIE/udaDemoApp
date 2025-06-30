
package com.ejie.aa79b.webservices.x54j;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for X54JRespuestaWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="X54JRespuestaWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aplicaciones" type="{java:x54j.x54jbeans}ArrayOfX54JAplicacionWS"/>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mensaje_Es" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mensaje_Eu" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dni" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="apellido1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="apellido2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desCas" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desEus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="funcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cif" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telefonoMovil" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telefonoFijo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="loginAutoformacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sexo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X54JRespuestaWS", propOrder = {
    "aplicaciones",
    "codigo",
    "mensajeEs",
    "mensajeEu",
    "dni",
    "nombre",
    "apellido1",
    "apellido2",
    "desCas",
    "desEus",
    "funcion",
    "cif",
    "telefonoMovil",
    "telefonoFijo",
    "email",
    "loginAutoformacion",
    "sexo"
})
public class X54JRespuestaWS {

    @XmlElement(required = true, nillable = true)
    protected ArrayOfX54JAplicacionWS aplicaciones;
    @XmlElement(required = true, nillable = true)
    protected String codigo;
    @XmlElement(name = "mensaje_Es", required = true, nillable = true)
    protected String mensajeEs;
    @XmlElement(name = "mensaje_Eu", required = true, nillable = true)
    protected String mensajeEu;
    @XmlElement(required = true, nillable = true)
    protected String dni;
    @XmlElement(required = true, nillable = true)
    protected String nombre;
    @XmlElement(required = true, nillable = true)
    protected String apellido1;
    @XmlElement(required = true, nillable = true)
    protected String apellido2;
    @XmlElement(required = true, nillable = true)
    protected String desCas;
    @XmlElement(required = true, nillable = true)
    protected String desEus;
    @XmlElement(required = true, nillable = true)
    protected String funcion;
    @XmlElement(required = true, nillable = true)
    protected String cif;
    @XmlElement(required = true, nillable = true)
    protected String telefonoMovil;
    @XmlElement(required = true, nillable = true)
    protected String telefonoFijo;
    @XmlElement(required = true, nillable = true)
    protected String email;
    @XmlElement(required = true, nillable = true)
    protected String loginAutoformacion;
    @XmlElementRef(name = "sexo", namespace = "java:x54j.x54jbeans", type = JAXBElement.class)
    protected JAXBElement<String> sexo;

    /**
     * Gets the value of the aplicaciones property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfX54JAplicacionWS }
     *     
     */
    public ArrayOfX54JAplicacionWS getAplicaciones() {
        return aplicaciones;
    }

    /**
     * Sets the value of the aplicaciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfX54JAplicacionWS }
     *     
     */
    public void setAplicaciones(ArrayOfX54JAplicacionWS value) {
        this.aplicaciones = value;
    }

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
     * Gets the value of the mensajeEs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensajeEs() {
        return mensajeEs;
    }

    /**
     * Sets the value of the mensajeEs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensajeEs(String value) {
        this.mensajeEs = value;
    }

    /**
     * Gets the value of the mensajeEu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensajeEu() {
        return mensajeEu;
    }

    /**
     * Sets the value of the mensajeEu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensajeEu(String value) {
        this.mensajeEu = value;
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
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the apellido1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Sets the value of the apellido1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido1(String value) {
        this.apellido1 = value;
    }

    /**
     * Gets the value of the apellido2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Sets the value of the apellido2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido2(String value) {
        this.apellido2 = value;
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

    /**
     * Gets the value of the funcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFuncion() {
        return funcion;
    }

    /**
     * Sets the value of the funcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFuncion(String value) {
        this.funcion = value;
    }

    /**
     * Gets the value of the cif property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCif() {
        return cif;
    }

    /**
     * Sets the value of the cif property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCif(String value) {
        this.cif = value;
    }

    /**
     * Gets the value of the telefonoMovil property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    /**
     * Sets the value of the telefonoMovil property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonoMovil(String value) {
        this.telefonoMovil = value;
    }

    /**
     * Gets the value of the telefonoFijo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    /**
     * Sets the value of the telefonoFijo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonoFijo(String value) {
        this.telefonoFijo = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the loginAutoformacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginAutoformacion() {
        return loginAutoformacion;
    }

    /**
     * Sets the value of the loginAutoformacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginAutoformacion(String value) {
        this.loginAutoformacion = value;
    }

    /**
     * Gets the value of the sexo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSexo() {
        return sexo;
    }

    /**
     * Sets the value of the sexo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSexo(JAXBElement<String> value) {
        this.sexo = ((JAXBElement<String> ) value);
    }

}
