
package com.ejie.aa79b.webservices.p43j;

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
 *         &lt;element name="numeroExpediente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="anoBol" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroBol" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="historico" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="crearFichero" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "numeroExpediente",
    "anoBol",
    "numeroBol",
    "historico",
    "crearFichero"
})
@XmlRootElement(name = "subirFicherosCorreccion")
public class SubirFicherosCorreccion {

    @XmlElement(required = true)
    protected String numeroExpediente;
    @XmlElement(required = true)
    protected String anoBol;
    @XmlElement(required = true)
    protected String numeroBol;
    protected boolean historico;
    protected boolean crearFichero;

    /**
     * Gets the value of the numeroExpediente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    /**
     * Sets the value of the numeroExpediente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroExpediente(String value) {
        this.numeroExpediente = value;
    }

    /**
     * Gets the value of the anoBol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnoBol() {
        return anoBol;
    }

    /**
     * Sets the value of the anoBol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnoBol(String value) {
        this.anoBol = value;
    }

    /**
     * Gets the value of the numeroBol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroBol() {
        return numeroBol;
    }

    /**
     * Sets the value of the numeroBol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroBol(String value) {
        this.numeroBol = value;
    }

    /**
     * Gets the value of the historico property.
     * 
     */
    public boolean isHistorico() {
        return historico;
    }

    /**
     * Sets the value of the historico property.
     * 
     */
    public void setHistorico(boolean value) {
        this.historico = value;
    }

    /**
     * Gets the value of the crearFichero property.
     * 
     */
    public boolean isCrearFichero() {
        return crearFichero;
    }

    /**
     * Sets the value of the crearFichero property.
     * 
     */
    public void setCrearFichero(boolean value) {
        this.crearFichero = value;
    }

}
