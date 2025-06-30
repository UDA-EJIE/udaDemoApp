
package aa79b.w43df.pid.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for searchByAttributes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="searchByAttributes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attributedQuery" type="{com/ejie/documents/xml}w43DfAttributedQuery" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchByAttributes", propOrder = {
    "attributedQuery"
})
public class SearchByAttributes {

    @XmlElement(namespace = "com/ejie/documents/xml")
    protected W43DfAttributedQuery attributedQuery;

    /**
     * Gets the value of the attributedQuery property.
     * 
     * @return
     *     possible object is
     *     {@link W43DfAttributedQuery }
     *     
     */
    public W43DfAttributedQuery getAttributedQuery() {
        return attributedQuery;
    }

    /**
     * Sets the value of the attributedQuery property.
     * 
     * @param value
     *     allowed object is
     *     {@link W43DfAttributedQuery }
     *     
     */
    public void setAttributedQuery(W43DfAttributedQuery value) {
        this.attributedQuery = value;
    }

}
