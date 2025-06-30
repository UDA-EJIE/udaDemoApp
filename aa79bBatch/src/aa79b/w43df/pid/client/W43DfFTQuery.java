
package aa79b.w43df.pid.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for w43DfFTQuery complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="w43DfFTQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="locale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeSubtypes" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="returnKeys" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="simpleAttrSearchConditions" type="{com/ejie/documents/xml}w43DfFTSimpleAttrCondition" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="valuelistAttrSearchConditions" type="{com/ejie/documents/xml}w43DfFTValuelistAttrCondition" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="rangeAttrSearchConditions" type="{com/ejie/documents/xml}w43DfFTRangeAttrCondition" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contentSearchCondition" type="{com/ejie/documents/xml}w43DfFTContentCondition" minOccurs="0"/>
 *         &lt;element name="facetDefinitions" type="{com/ejie/documents/xml}w43DfFTFacetDefinition" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="orderByClauses" type="{com/ejie/documents/xml}w43DfFTOrderByClause" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="lowerBound" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="upperBound" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="hitsEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "w43DfFTQuery", propOrder = {
    "locale",
    "type",
    "includeSubtypes",
    "returnKeys",
    "simpleAttrSearchConditions",
    "valuelistAttrSearchConditions",
    "rangeAttrSearchConditions",
    "contentSearchCondition",
    "facetDefinitions",
    "orderByClauses",
    "lowerBound",
    "upperBound",
    "hitsEnabled"
})
public class W43DfFTQuery {

    protected String locale;
    protected String type;
    protected boolean includeSubtypes;
    protected List<String> returnKeys;
    protected List<W43DfFTSimpleAttrCondition> simpleAttrSearchConditions;
    protected List<W43DfFTValuelistAttrCondition> valuelistAttrSearchConditions;
    protected List<W43DfFTRangeAttrCondition> rangeAttrSearchConditions;
    protected W43DfFTContentCondition contentSearchCondition;
    protected List<W43DfFTFacetDefinition> facetDefinitions;
    protected List<W43DfFTOrderByClause> orderByClauses;
    protected int lowerBound;
    protected int upperBound;
    protected boolean hitsEnabled;

    /**
     * Gets the value of the locale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Sets the value of the locale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocale(String value) {
        this.locale = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the includeSubtypes property.
     * 
     */
    public boolean isIncludeSubtypes() {
        return includeSubtypes;
    }

    /**
     * Sets the value of the includeSubtypes property.
     * 
     */
    public void setIncludeSubtypes(boolean value) {
        this.includeSubtypes = value;
    }

    /**
     * Gets the value of the returnKeys property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the returnKeys property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReturnKeys().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getReturnKeys() {
        if (returnKeys == null) {
            returnKeys = new ArrayList<String>();
        }
        return this.returnKeys;
    }

    /**
     * Gets the value of the simpleAttrSearchConditions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the simpleAttrSearchConditions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSimpleAttrSearchConditions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link W43DfFTSimpleAttrCondition }
     * 
     * 
     */
    public List<W43DfFTSimpleAttrCondition> getSimpleAttrSearchConditions() {
        if (simpleAttrSearchConditions == null) {
            simpleAttrSearchConditions = new ArrayList<W43DfFTSimpleAttrCondition>();
        }
        return this.simpleAttrSearchConditions;
    }

    /**
     * Gets the value of the valuelistAttrSearchConditions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valuelistAttrSearchConditions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValuelistAttrSearchConditions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link W43DfFTValuelistAttrCondition }
     * 
     * 
     */
    public List<W43DfFTValuelistAttrCondition> getValuelistAttrSearchConditions() {
        if (valuelistAttrSearchConditions == null) {
            valuelistAttrSearchConditions = new ArrayList<W43DfFTValuelistAttrCondition>();
        }
        return this.valuelistAttrSearchConditions;
    }

    /**
     * Gets the value of the rangeAttrSearchConditions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rangeAttrSearchConditions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRangeAttrSearchConditions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link W43DfFTRangeAttrCondition }
     * 
     * 
     */
    public List<W43DfFTRangeAttrCondition> getRangeAttrSearchConditions() {
        if (rangeAttrSearchConditions == null) {
            rangeAttrSearchConditions = new ArrayList<W43DfFTRangeAttrCondition>();
        }
        return this.rangeAttrSearchConditions;
    }

    /**
     * Gets the value of the contentSearchCondition property.
     * 
     * @return
     *     possible object is
     *     {@link W43DfFTContentCondition }
     *     
     */
    public W43DfFTContentCondition getContentSearchCondition() {
        return contentSearchCondition;
    }

    /**
     * Sets the value of the contentSearchCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link W43DfFTContentCondition }
     *     
     */
    public void setContentSearchCondition(W43DfFTContentCondition value) {
        this.contentSearchCondition = value;
    }

    /**
     * Gets the value of the facetDefinitions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the facetDefinitions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFacetDefinitions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link W43DfFTFacetDefinition }
     * 
     * 
     */
    public List<W43DfFTFacetDefinition> getFacetDefinitions() {
        if (facetDefinitions == null) {
            facetDefinitions = new ArrayList<W43DfFTFacetDefinition>();
        }
        return this.facetDefinitions;
    }

    /**
     * Gets the value of the orderByClauses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderByClauses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderByClauses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link W43DfFTOrderByClause }
     * 
     * 
     */
    public List<W43DfFTOrderByClause> getOrderByClauses() {
        if (orderByClauses == null) {
            orderByClauses = new ArrayList<W43DfFTOrderByClause>();
        }
        return this.orderByClauses;
    }

    /**
     * Gets the value of the lowerBound property.
     * 
     */
    public int getLowerBound() {
        return lowerBound;
    }

    /**
     * Sets the value of the lowerBound property.
     * 
     */
    public void setLowerBound(int value) {
        this.lowerBound = value;
    }

    /**
     * Gets the value of the upperBound property.
     * 
     */
    public int getUpperBound() {
        return upperBound;
    }

    /**
     * Sets the value of the upperBound property.
     * 
     */
    public void setUpperBound(int value) {
        this.upperBound = value;
    }

    /**
     * Gets the value of the hitsEnabled property.
     * 
     */
    public boolean isHitsEnabled() {
        return hitsEnabled;
    }

    /**
     * Sets the value of the hitsEnabled property.
     * 
     */
    public void setHitsEnabled(boolean value) {
        this.hitsEnabled = value;
    }

}
