
package com.ejie.aa79b.webservices.x54j;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for X54jDireccionWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="X54jDireccionWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codPais" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pais" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codProvincia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="provincia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codMunicipio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="municipio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codLocalidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="localidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codCalle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="calle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codPortal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="portal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codPostal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="escalera" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="piso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mano" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="puerta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="aproximacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X54jDireccionWS", propOrder = {
    "codigo",
    "tipo",
    "codPais",
    "pais",
    "codProvincia",
    "provincia",
    "codMunicipio",
    "municipio",
    "codLocalidad",
    "localidad",
    "codCalle",
    "calle",
    "codPortal",
    "portal",
    "codPostal",
    "escalera",
    "piso",
    "mano",
    "puerta",
    "aproximacion"
})
public class X54JDireccionWS {

    @XmlElement(required = true, nillable = true)
    protected String codigo;
    @XmlElement(required = true, nillable = true)
    protected String tipo;
    @XmlElement(required = true, nillable = true)
    protected String codPais;
    @XmlElement(required = true, nillable = true)
    protected String pais;
    @XmlElement(required = true, nillable = true)
    protected String codProvincia;
    @XmlElement(required = true, nillable = true)
    protected String provincia;
    @XmlElement(required = true, nillable = true)
    protected String codMunicipio;
    @XmlElement(required = true, nillable = true)
    protected String municipio;
    @XmlElement(required = true, nillable = true)
    protected String codLocalidad;
    @XmlElement(required = true, nillable = true)
    protected String localidad;
    @XmlElement(required = true, nillable = true)
    protected String codCalle;
    @XmlElement(required = true, nillable = true)
    protected String calle;
    @XmlElement(required = true, nillable = true)
    protected String codPortal;
    @XmlElement(required = true, nillable = true)
    protected String portal;
    @XmlElement(required = true, nillable = true)
    protected String codPostal;
    @XmlElement(required = true, nillable = true)
    protected String escalera;
    @XmlElement(required = true, nillable = true)
    protected String piso;
    @XmlElement(required = true, nillable = true)
    protected String mano;
    @XmlElement(required = true, nillable = true)
    protected String puerta;
    @XmlElement(required = true, nillable = true)
    protected String aproximacion;

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
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Gets the value of the codPais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodPais() {
        return codPais;
    }

    /**
     * Sets the value of the codPais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodPais(String value) {
        this.codPais = value;
    }

    /**
     * Gets the value of the pais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPais() {
        return pais;
    }

    /**
     * Sets the value of the pais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPais(String value) {
        this.pais = value;
    }

    /**
     * Gets the value of the codProvincia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodProvincia() {
        return codProvincia;
    }

    /**
     * Sets the value of the codProvincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodProvincia(String value) {
        this.codProvincia = value;
    }

    /**
     * Gets the value of the provincia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Sets the value of the provincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvincia(String value) {
        this.provincia = value;
    }

    /**
     * Gets the value of the codMunicipio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodMunicipio() {
        return codMunicipio;
    }

    /**
     * Sets the value of the codMunicipio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodMunicipio(String value) {
        this.codMunicipio = value;
    }

    /**
     * Gets the value of the municipio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * Sets the value of the municipio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipio(String value) {
        this.municipio = value;
    }

    /**
     * Gets the value of the codLocalidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodLocalidad() {
        return codLocalidad;
    }

    /**
     * Sets the value of the codLocalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodLocalidad(String value) {
        this.codLocalidad = value;
    }

    /**
     * Gets the value of the localidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Sets the value of the localidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalidad(String value) {
        this.localidad = value;
    }

    /**
     * Gets the value of the codCalle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodCalle() {
        return codCalle;
    }

    /**
     * Sets the value of the codCalle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodCalle(String value) {
        this.codCalle = value;
    }

    /**
     * Gets the value of the calle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Sets the value of the calle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalle(String value) {
        this.calle = value;
    }

    /**
     * Gets the value of the codPortal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodPortal() {
        return codPortal;
    }

    /**
     * Sets the value of the codPortal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodPortal(String value) {
        this.codPortal = value;
    }

    /**
     * Gets the value of the portal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortal() {
        return portal;
    }

    /**
     * Sets the value of the portal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortal(String value) {
        this.portal = value;
    }

    /**
     * Gets the value of the codPostal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodPostal() {
        return codPostal;
    }

    /**
     * Sets the value of the codPostal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodPostal(String value) {
        this.codPostal = value;
    }

    /**
     * Gets the value of the escalera property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEscalera() {
        return escalera;
    }

    /**
     * Sets the value of the escalera property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEscalera(String value) {
        this.escalera = value;
    }

    /**
     * Gets the value of the piso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPiso() {
        return piso;
    }

    /**
     * Sets the value of the piso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPiso(String value) {
        this.piso = value;
    }

    /**
     * Gets the value of the mano property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMano() {
        return mano;
    }

    /**
     * Sets the value of the mano property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMano(String value) {
        this.mano = value;
    }

    /**
     * Gets the value of the puerta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuerta() {
        return puerta;
    }

    /**
     * Sets the value of the puerta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuerta(String value) {
        this.puerta = value;
    }

    /**
     * Gets the value of the aproximacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAproximacion() {
        return aproximacion;
    }

    /**
     * Sets the value of the aproximacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAproximacion(String value) {
        this.aproximacion = value;
    }

}
