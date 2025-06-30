
package aa79b.w43df.pid.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for w43DfTypedQuery complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="w43DfTypedQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeSubtypes" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="returnKeys" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="searchConditions" type="{com/ejie/documents/xml}w43DfCondition" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="orderByKeys" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="orderByDescend" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="maxRows" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "w43DfTypedQuery", propOrder = {
    "type",
    "includeSubtypes",
    "returnKeys",
    "searchConditions",
    "orderByKeys",
    "orderByDescend",
    "maxRows"
})
public class W43DfTypedQuery {

    protected String type;
    protected boolean includeSubtypes;
    protected List<String> returnKeys;
    protected List<W43DfCondition> searchConditions;
    protected List<String> orderByKeys;
    protected boolean orderByDescend;
    protected int maxRows;

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
     * Gets the value of the searchConditions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchConditions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchConditions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link W43DfCondition }
     * 
     * 
     */
    public List<W43DfCondition> getSearchConditions() {
        if (searchConditions == null) {
            searchConditions = new ArrayList<W43DfCondition>();
        }
        return this.searchConditions;
    }

    /**
     * Gets the value of the orderByKeys property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderByKeys property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderByKeys().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getOrderByKeys() {
        if (orderByKeys == null) {
            orderByKeys = new ArrayList<String>();
        }
        return this.orderByKeys;
    }

    /**
     * Gets the value of the orderByDescend property.
     * 
     */
    public boolean isOrderByDescend() {
        return orderByDescend;
    }

    /**
     * Sets the value of the orderByDescend property.
     * 
     */
    public void setOrderByDescend(boolean value) {
        this.orderByDescend = value;
    }

    /**
     * Gets the value of the maxRows property.
     * 
     */
    public int getMaxRows() {
        return maxRows;
    }

    /**
     * Sets the value of the maxRows property.
     * 
     */
    public void setMaxRows(int value) {
        this.maxRows = value;
    }

}
