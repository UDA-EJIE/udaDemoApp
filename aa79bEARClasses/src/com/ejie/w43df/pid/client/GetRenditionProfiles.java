
package com.ejie.w43df.pid.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getRenditionProfiles complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getRenditionProfiles">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="renditionProfile" type="{com/ejie/documents/xml}w43DfRenditionProfile" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRenditionProfiles", propOrder = {
    "renditionProfile"
})
public class GetRenditionProfiles {

    @XmlElement(namespace = "com/ejie/documents/xml")
    protected W43DfRenditionProfile renditionProfile;

    /**
     * Gets the value of the renditionProfile property.
     * 
     * @return
     *     possible object is
     *     {@link W43DfRenditionProfile }
     *     
     */
    public W43DfRenditionProfile getRenditionProfile() {
        return renditionProfile;
    }

    /**
     * Sets the value of the renditionProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link W43DfRenditionProfile }
     *     
     */
    public void setRenditionProfile(W43DfRenditionProfile value) {
        this.renditionProfile = value;
    }

}
