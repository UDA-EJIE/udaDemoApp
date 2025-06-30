
package aa79b.w43df.pid.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for searchFulltext complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="searchFulltext">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fulltextQuery" type="{com/ejie/documents/xml}w43DfFTQuery" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchFulltext", propOrder = {
    "fulltextQuery"
})
public class SearchFulltext {

    @XmlElement(namespace = "com/ejie/documents/xml")
    protected W43DfFTQuery fulltextQuery;

    /**
     * Gets the value of the fulltextQuery property.
     * 
     * @return
     *     possible object is
     *     {@link W43DfFTQuery }
     *     
     */
    public W43DfFTQuery getFulltextQuery() {
        return fulltextQuery;
    }

    /**
     * Sets the value of the fulltextQuery property.
     * 
     * @param value
     *     allowed object is
     *     {@link W43DfFTQuery }
     *     
     */
    public void setFulltextQuery(W43DfFTQuery value) {
        this.fulltextQuery = value;
    }

}
