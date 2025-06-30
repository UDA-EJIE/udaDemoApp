
package com.ejie.w43df.pid.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for w43DfFTRangeAttrCondition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="w43DfFTRangeAttrCondition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attributeKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lowerValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upperValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "w43DfFTRangeAttrCondition", propOrder = {
    "attributeKey",
    "lowerValue",
    "upperValue"
})
public class W43DfFTRangeAttrCondition {

    protected String attributeKey;
    protected String lowerValue;
    protected String upperValue;

    /**
     * Gets the value of the attributeKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeKey() {
        return attributeKey;
    }

    /**
     * Sets the value of the attributeKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeKey(String value) {
        this.attributeKey = value;
    }

    /**
     * Gets the value of the lowerValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLowerValue() {
        return lowerValue;
    }

    /**
     * Sets the value of the lowerValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLowerValue(String value) {
        this.lowerValue = value;
    }

    /**
     * Gets the value of the upperValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpperValue() {
        return upperValue;
    }

    /**
     * Sets the value of the upperValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpperValue(String value) {
        this.upperValue = value;
    }

}
