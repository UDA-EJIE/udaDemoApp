
package com.ejie.w43df.pid.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for w43DfFTSimpleAttrCondition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="w43DfFTSimpleAttrCondition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attributeKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attributeValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="searchOperator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exactMatch" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fuzzySearch" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fuzzySimilarity" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "w43DfFTSimpleAttrCondition", propOrder = {
    "attributeKey",
    "attributeValue",
    "searchOperator",
    "exactMatch",
    "fuzzySearch",
    "fuzzySimilarity"
})
public class W43DfFTSimpleAttrCondition {

    protected String attributeKey;
    protected String attributeValue;
    protected String searchOperator;
    protected boolean exactMatch;
    protected boolean fuzzySearch;
    protected float fuzzySimilarity;

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
     * Gets the value of the attributeValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeValue() {
        return attributeValue;
    }

    /**
     * Sets the value of the attributeValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeValue(String value) {
        this.attributeValue = value;
    }

    /**
     * Gets the value of the searchOperator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchOperator() {
        return searchOperator;
    }

    /**
     * Sets the value of the searchOperator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchOperator(String value) {
        this.searchOperator = value;
    }

    /**
     * Gets the value of the exactMatch property.
     * 
     */
    public boolean isExactMatch() {
        return exactMatch;
    }

    /**
     * Sets the value of the exactMatch property.
     * 
     */
    public void setExactMatch(boolean value) {
        this.exactMatch = value;
    }

    /**
     * Gets the value of the fuzzySearch property.
     * 
     */
    public boolean isFuzzySearch() {
        return fuzzySearch;
    }

    /**
     * Sets the value of the fuzzySearch property.
     * 
     */
    public void setFuzzySearch(boolean value) {
        this.fuzzySearch = value;
    }

    /**
     * Gets the value of the fuzzySimilarity property.
     * 
     */
    public float getFuzzySimilarity() {
        return fuzzySimilarity;
    }

    /**
     * Sets the value of the fuzzySimilarity property.
     * 
     */
    public void setFuzzySimilarity(float value) {
        this.fuzzySimilarity = value;
    }

}
