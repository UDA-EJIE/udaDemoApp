
package com.ejie.aa79b.webservices.x54j;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for X54JTrabajadorWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="X54JTrabajadorWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipIden" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="preDni" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dni" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sufDni" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="apellido1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="apellido2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipEnt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codEnt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descEnt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="puestoCas" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="puestoEus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="grTitulacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="perfilPuesto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaPreceptividad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoContrato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sexo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telefonoMovil" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telefonoFijo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sms" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="direccion" type="{java:x54j.x54jbeans}X54jDireccionWS"/>
 *         &lt;element name="fechaNacimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X54JTrabajadorWS", propOrder = {
    "tipIden",
    "preDni",
    "dni",
    "sufDni",
    "nombre",
    "apellido1",
    "apellido2",
    "tipEnt",
    "codEnt",
    "descEnt",
    "puestoCas",
    "puestoEus",
    "grTitulacion",
    "perfilPuesto",
    "fechaPreceptividad",
    "tipoContrato",
    "sexo",
    "telefonoMovil",
    "telefonoFijo",
    "email",
    "sms",
    "direccion",
    "fechaNacimiento"
})
public class X54JTrabajadorWS {

    @XmlElement(required = true, nillable = true)
    protected String tipIden;
    @XmlElement(required = true, nillable = true)
    protected String preDni;
    @XmlElement(required = true, nillable = true)
    protected String dni;
    @XmlElement(required = true, nillable = true)
    protected String sufDni;
    @XmlElement(required = true, nillable = true)
    protected String nombre;
    @XmlElement(required = true, nillable = true)
    protected String apellido1;
    @XmlElement(required = true, nillable = true)
    protected String apellido2;
    @XmlElement(required = true, nillable = true)
    protected String tipEnt;
    @XmlElement(required = true, nillable = true)
    protected String codEnt;
    @XmlElement(required = true, nillable = true)
    protected String descEnt;
    @XmlElement(required = true, nillable = true)
    protected String puestoCas;
    @XmlElement(required = true, nillable = true)
    protected String puestoEus;
    @XmlElement(required = true, nillable = true)
    protected String grTitulacion;
    @XmlElement(required = true, nillable = true)
    protected String perfilPuesto;
    @XmlElement(required = true, nillable = true)
    protected String fechaPreceptividad;
    @XmlElement(required = true, nillable = true)
    protected String tipoContrato;
    @XmlElement(required = true, nillable = true)
    protected String sexo;
    @XmlElement(required = true, nillable = true)
    protected String telefonoMovil;
    @XmlElement(required = true, nillable = true)
    protected String telefonoFijo;
    @XmlElement(required = true, nillable = true)
    protected String email;
    @XmlElement(required = true, nillable = true)
    protected String sms;
    @XmlElement(required = true, nillable = true)
    protected X54JDireccionWS direccion;
    @XmlElement(required = true, nillable = true)
    protected String fechaNacimiento;

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
     * Gets the value of the tipEnt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipEnt() {
        return tipEnt;
    }

    /**
     * Sets the value of the tipEnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipEnt(String value) {
        this.tipEnt = value;
    }

    /**
     * Gets the value of the codEnt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodEnt() {
        return codEnt;
    }

    /**
     * Sets the value of the codEnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodEnt(String value) {
        this.codEnt = value;
    }

    /**
     * Gets the value of the descEnt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescEnt() {
        return descEnt;
    }

    /**
     * Sets the value of the descEnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescEnt(String value) {
        this.descEnt = value;
    }

    /**
     * Gets the value of the puestoCas property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuestoCas() {
        return puestoCas;
    }

    /**
     * Sets the value of the puestoCas property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuestoCas(String value) {
        this.puestoCas = value;
    }

    /**
     * Gets the value of the puestoEus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuestoEus() {
        return puestoEus;
    }

    /**
     * Sets the value of the puestoEus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuestoEus(String value) {
        this.puestoEus = value;
    }

    /**
     * Gets the value of the grTitulacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrTitulacion() {
        return grTitulacion;
    }

    /**
     * Sets the value of the grTitulacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrTitulacion(String value) {
        this.grTitulacion = value;
    }

    /**
     * Gets the value of the perfilPuesto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerfilPuesto() {
        return perfilPuesto;
    }

    /**
     * Sets the value of the perfilPuesto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerfilPuesto(String value) {
        this.perfilPuesto = value;
    }

    /**
     * Gets the value of the fechaPreceptividad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaPreceptividad() {
        return fechaPreceptividad;
    }

    /**
     * Sets the value of the fechaPreceptividad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaPreceptividad(String value) {
        this.fechaPreceptividad = value;
    }

    /**
     * Gets the value of the tipoContrato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoContrato() {
        return tipoContrato;
    }

    /**
     * Sets the value of the tipoContrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoContrato(String value) {
        this.tipoContrato = value;
    }

    /**
     * Gets the value of the sexo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Sets the value of the sexo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSexo(String value) {
        this.sexo = value;
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
     * Gets the value of the sms property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSms() {
        return sms;
    }

    /**
     * Sets the value of the sms property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSms(String value) {
        this.sms = value;
    }

    /**
     * Gets the value of the direccion property.
     * 
     * @return
     *     possible object is
     *     {@link X54JDireccionWS }
     *     
     */
    public X54JDireccionWS getDireccion() {
        return direccion;
    }

    /**
     * Sets the value of the direccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link X54JDireccionWS }
     *     
     */
    public void setDireccion(X54JDireccionWS value) {
        this.direccion = value;
    }

    /**
     * Gets the value of the fechaNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Sets the value of the fechaNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaNacimiento(String value) {
        this.fechaNacimiento = value;
    }

}
