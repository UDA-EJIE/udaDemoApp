
package com.ejie.aa79b.webservices.x54j;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for X54JRespuestaAnadirRolWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="X54JRespuestaAnadirRolWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mensaje_Es" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mensaje_Eu" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X54JRespuestaAnadirRolWS", propOrder = {
    "codigo",
    "mensajeEs",
    "mensajeEu"
})
public class X54JRespuestaAnadirRolWS {

    @XmlElement(required = true, nillable = true)
    protected String codigo;
    @XmlElement(name = "mensaje_Es", required = true, nillable = true)
    protected String mensajeEs;
    @XmlElement(name = "mensaje_Eu", required = true, nillable = true)
    protected String mensajeEu;

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

}
