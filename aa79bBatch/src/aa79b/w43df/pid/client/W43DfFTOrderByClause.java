
package aa79b.w43df.pid.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for w43DfFTOrderByClause complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="w43DfFTOrderByClause">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderByKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderByDescend" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "w43DfFTOrderByClause", propOrder = {
    "orderByKey",
    "orderByDescend"
})
public class W43DfFTOrderByClause {

    protected String orderByKey;
    protected boolean orderByDescend;

    /**
     * Gets the value of the orderByKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderByKey() {
        return orderByKey;
    }

    /**
     * Sets the value of the orderByKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderByKey(String value) {
        this.orderByKey = value;
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

}
