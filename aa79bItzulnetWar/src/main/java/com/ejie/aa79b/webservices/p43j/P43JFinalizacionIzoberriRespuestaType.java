
package com.ejie.aa79b.webservices.p43j;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for P43JFinalizacionIzoberriRespuestaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="P43JFinalizacionIzoberriRespuestaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="expeIzo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="expeApli" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valorResultado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="listaErrores" type="{http://www.ejie.es/webServiceClase/p43jWebServicesWar}P43JListaErroresType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "P43JFinalizacionIzoberriRespuestaType", propOrder = {
    "expeIzo",
    "expeApli",
    "valorResultado",
    "listaErrores"
})
public class P43JFinalizacionIzoberriRespuestaType {

    protected String expeIzo;
    protected String expeApli;
    @XmlElement(required = true)
    protected String valorResultado;
    protected P43JListaErroresType listaErrores;

    /**
     * Gets the value of the expeIzo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpeIzo() {
        return expeIzo;
    }

    /**
     * Sets the value of the expeIzo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpeIzo(String value) {
        this.expeIzo = value;
    }

    /**
     * Gets the value of the expeApli property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpeApli() {
        return expeApli;
    }

    /**
     * Sets the value of the expeApli property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpeApli(String value) {
        this.expeApli = value;
    }

    /**
     * Gets the value of the valorResultado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValorResultado() {
        return valorResultado;
    }

    /**
     * Sets the value of the valorResultado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValorResultado(String value) {
        this.valorResultado = value;
    }

    /**
     * Gets the value of the listaErrores property.
     * 
     * @return
     *     possible object is
     *     {@link P43JListaErroresType }
     *     
     */
    public P43JListaErroresType getListaErrores() {
        return listaErrores;
    }

    /**
     * Sets the value of the listaErrores property.
     * 
     * @param value
     *     allowed object is
     *     {@link P43JListaErroresType }
     *     
     */
    public void setListaErrores(P43JListaErroresType value) {
        this.listaErrores = value;
    }

}
