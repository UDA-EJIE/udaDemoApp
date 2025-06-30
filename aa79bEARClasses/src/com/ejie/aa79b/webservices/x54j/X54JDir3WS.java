
package com.ejie.aa79b.webservices.x54j;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for X54jDir3WS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="X54jDir3WS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="og" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ut" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X54jDir3WS", propOrder = {
    "og",
    "ut",
    "oc"
})
public class X54JDir3WS {

    @XmlElement(required = true, nillable = true)
    protected String og;
    @XmlElement(required = true, nillable = true)
    protected String ut;
    @XmlElement(required = true, nillable = true)
    protected String oc;

    /**
     * Gets the value of the og property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOg() {
        return og;
    }

    /**
     * Sets the value of the og property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOg(String value) {
        this.og = value;
    }

    /**
     * Gets the value of the ut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUt() {
        return ut;
    }

    /**
     * Sets the value of the ut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUt(String value) {
        this.ut = value;
    }

    /**
     * Gets the value of the oc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOc() {
        return oc;
    }

    /**
     * Sets the value of the oc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOc(String value) {
        this.oc = value;
    }

}
