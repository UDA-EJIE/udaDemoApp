
package com.ejie.w43df.pid.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for w43DfFTFacetDefinition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="w43DfFTFacetDefinition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maxFacets" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="skipEmptyValues" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="caseSensitive" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="nullValueFacet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupingStrategy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderingStrategy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "w43DfFTFacetDefinition", propOrder = {
    "name",
    "key",
    "maxFacets",
    "skipEmptyValues",
    "caseSensitive",
    "nullValueFacet",
    "groupingStrategy",
    "orderingStrategy"
})
public class W43DfFTFacetDefinition {

    protected String name;
    protected String key;
    protected int maxFacets;
    protected boolean skipEmptyValues;
    protected boolean caseSensitive;
    protected String nullValueFacet;
    protected String groupingStrategy;
    protected String orderingStrategy;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * Gets the value of the maxFacets property.
     * 
     */
    public int getMaxFacets() {
        return maxFacets;
    }

    /**
     * Sets the value of the maxFacets property.
     * 
     */
    public void setMaxFacets(int value) {
        this.maxFacets = value;
    }

    /**
     * Gets the value of the skipEmptyValues property.
     * 
     */
    public boolean isSkipEmptyValues() {
        return skipEmptyValues;
    }

    /**
     * Sets the value of the skipEmptyValues property.
     * 
     */
    public void setSkipEmptyValues(boolean value) {
        this.skipEmptyValues = value;
    }

    /**
     * Gets the value of the caseSensitive property.
     * 
     */
    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    /**
     * Sets the value of the caseSensitive property.
     * 
     */
    public void setCaseSensitive(boolean value) {
        this.caseSensitive = value;
    }

    /**
     * Gets the value of the nullValueFacet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNullValueFacet() {
        return nullValueFacet;
    }

    /**
     * Sets the value of the nullValueFacet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNullValueFacet(String value) {
        this.nullValueFacet = value;
    }

    /**
     * Gets the value of the groupingStrategy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupingStrategy() {
        return groupingStrategy;
    }

    /**
     * Sets the value of the groupingStrategy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupingStrategy(String value) {
        this.groupingStrategy = value;
    }

    /**
     * Gets the value of the orderingStrategy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderingStrategy() {
        return orderingStrategy;
    }

    /**
     * Sets the value of the orderingStrategy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderingStrategy(String value) {
        this.orderingStrategy = value;
    }

}
