
package aa79b.w43df.pid.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for w43DfAttributedQuery complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="w43DfAttributedQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="returnKeys" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="searchConditions" type="{com/ejie/documents/xml}w43DfCondition" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="maxRows" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="maxTypes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "w43DfAttributedQuery", propOrder = {
    "returnKeys",
    "searchConditions",
    "maxRows",
    "maxTypes"
})
public class W43DfAttributedQuery {

    protected List<String> returnKeys;
    protected List<W43DfCondition> searchConditions;
    protected int maxRows;
    protected int maxTypes;

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

    /**
     * Gets the value of the maxTypes property.
     * 
     */
    public int getMaxTypes() {
        return maxTypes;
    }

    /**
     * Sets the value of the maxTypes property.
     * 
     */
    public void setMaxTypes(int value) {
        this.maxTypes = value;
    }

}
