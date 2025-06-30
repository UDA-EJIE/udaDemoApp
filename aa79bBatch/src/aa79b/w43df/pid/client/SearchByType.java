
package aa79b.w43df.pid.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for searchByType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="searchByType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="typedQuery" type="{com/ejie/documents/xml}w43DfTypedQuery" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchByType", propOrder = {
    "typedQuery"
})
public class SearchByType {

    @XmlElement(namespace = "com/ejie/documents/xml")
    protected W43DfTypedQuery typedQuery;

    /**
     * Gets the value of the typedQuery property.
     * 
     * @return
     *     possible object is
     *     {@link W43DfTypedQuery }
     *     
     */
    public W43DfTypedQuery getTypedQuery() {
        return typedQuery;
    }

    /**
     * Sets the value of the typedQuery property.
     * 
     * @param value
     *     allowed object is
     *     {@link W43DfTypedQuery }
     *     
     */
    public void setTypedQuery(W43DfTypedQuery value) {
        this.typedQuery = value;
    }

}
