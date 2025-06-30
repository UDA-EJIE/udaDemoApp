
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
 *         &lt;element name="paramFinalizacionIzoberri" type="{http://www.ejie.es/webServiceClase/p43jWebServicesWar}P43JFinalizacionIzoberriType"/>
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
    "paramFinalizacionIzoberri"
})
@XmlRootElement(name = "finalizacionIzoberri")
public class FinalizacionIzoberri {

    @XmlElement(required = true)
    protected P43JFinalizacionIzoberriType paramFinalizacionIzoberri;

    /**
     * Gets the value of the paramFinalizacionIzoberri property.
     * 
     * @return
     *     possible object is
     *     {@link P43JFinalizacionIzoberriType }
     *     
     */
    public P43JFinalizacionIzoberriType getParamFinalizacionIzoberri() {
        return paramFinalizacionIzoberri;
    }

    /**
     * Sets the value of the paramFinalizacionIzoberri property.
     * 
     * @param value
     *     allowed object is
     *     {@link P43JFinalizacionIzoberriType }
     *     
     */
    public void setParamFinalizacionIzoberri(P43JFinalizacionIzoberriType value) {
        this.paramFinalizacionIzoberri = value;
    }

}
