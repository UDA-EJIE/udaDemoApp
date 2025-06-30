
package com.ejie.aa79b.webservices.x54j;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for X54JAplicacionWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="X54JAplicacionWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="roles" type="{java:x54j.x54jbeans}ArrayOfX54JRolesWS"/>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigo_Ejie" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="des_Cas" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="des_Eus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipo_Trabajador" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="des_Cas_Ent_Depto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="des_Eus_Ent_Depto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="direccionPostal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dir3" type="{java:x54j.x54jbeans}X54jDir3WS"/>
 *         &lt;element name="cif" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X54JAplicacionWS", propOrder = {
    "roles",
    "codigo",
    "codigoEjie",
    "desCas",
    "desEus",
    "tipoTrabajador",
    "desCasEntDepto",
    "desEusEntDepto",
    "direccionPostal",
    "dir3",
    "cif"
})
public class X54JAplicacionWS {

    @XmlElement(required = true, nillable = true)
    protected ArrayOfX54JRolesWS roles;
    @XmlElement(required = true, nillable = true)
    protected String codigo;
    @XmlElement(name = "codigo_Ejie", required = true, nillable = true)
    protected String codigoEjie;
    @XmlElement(name = "des_Cas", required = true, nillable = true)
    protected String desCas;
    @XmlElement(name = "des_Eus", required = true, nillable = true)
    protected String desEus;
    @XmlElement(name = "tipo_Trabajador", required = true, nillable = true)
    protected String tipoTrabajador;
    @XmlElement(name = "des_Cas_Ent_Depto", required = true, nillable = true)
    protected String desCasEntDepto;
    @XmlElement(name = "des_Eus_Ent_Depto", required = true, nillable = true)
    protected String desEusEntDepto;
    @XmlElement(required = true, nillable = true)
    protected String direccionPostal;
    @XmlElement(required = true, nillable = true)
    protected X54JDir3WS dir3;
    @XmlElement(required = true, nillable = true)
    protected String cif;

    /**
     * Gets the value of the roles property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfX54JRolesWS }
     *     
     */
    public ArrayOfX54JRolesWS getRoles() {
        return roles;
    }

    /**
     * Sets the value of the roles property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfX54JRolesWS }
     *     
     */
    public void setRoles(ArrayOfX54JRolesWS value) {
        this.roles = value;
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
     * Gets the value of the codigoEjie property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoEjie() {
        return codigoEjie;
    }

    /**
     * Sets the value of the codigoEjie property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoEjie(String value) {
        this.codigoEjie = value;
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
     * Gets the value of the tipoTrabajador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoTrabajador() {
        return tipoTrabajador;
    }

    /**
     * Sets the value of the tipoTrabajador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoTrabajador(String value) {
        this.tipoTrabajador = value;
    }

    /**
     * Gets the value of the desCasEntDepto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesCasEntDepto() {
        return desCasEntDepto;
    }

    /**
     * Sets the value of the desCasEntDepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesCasEntDepto(String value) {
        this.desCasEntDepto = value;
    }

    /**
     * Gets the value of the desEusEntDepto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesEusEntDepto() {
        return desEusEntDepto;
    }

    /**
     * Sets the value of the desEusEntDepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesEusEntDepto(String value) {
        this.desEusEntDepto = value;
    }

    /**
     * Gets the value of the direccionPostal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccionPostal() {
        return direccionPostal;
    }

    /**
     * Sets the value of the direccionPostal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccionPostal(String value) {
        this.direccionPostal = value;
    }

    /**
     * Gets the value of the dir3 property.
     * 
     * @return
     *     possible object is
     *     {@link X54JDir3WS }
     *     
     */
    public X54JDir3WS getDir3() {
        return dir3;
    }

    /**
     * Sets the value of the dir3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link X54JDir3WS }
     *     
     */
    public void setDir3(X54JDir3WS value) {
        this.dir3 = value;
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

}
