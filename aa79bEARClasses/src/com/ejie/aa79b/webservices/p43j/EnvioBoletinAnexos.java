
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
 *         &lt;element name="paramEnvioBoletinAnexos" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "paramEnvioBoletinAnexos"
})
@XmlRootElement(name = "envioBoletinAnexos")
public class EnvioBoletinAnexos {

    @XmlElement(required = true)
    protected String paramEnvioBoletinAnexos;

    /**
     * Gets the value of the paramEnvioBoletinAnexos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParamEnvioBoletinAnexos() {
        return paramEnvioBoletinAnexos;
    }

    /**
     * Sets the value of the paramEnvioBoletinAnexos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParamEnvioBoletinAnexos(String value) {
        this.paramEnvioBoletinAnexos = value;
    }

}
