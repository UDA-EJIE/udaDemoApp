
package aa79b.w43df.pid.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for modifyDocument complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="modifyDocument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="putDocument" type="{com/ejie/documents/xml}w43DfDocument" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyDocument", propOrder = {
    "putDocument"
})
public class ModifyDocument {

    @XmlElement(namespace = "com/ejie/documents/xml")
    protected W43DfDocument putDocument;

    /**
     * Gets the value of the putDocument property.
     * 
     * @return
     *     possible object is
     *     {@link W43DfDocument }
     *     
     */
    public W43DfDocument getPutDocument() {
        return putDocument;
    }

    /**
     * Sets the value of the putDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link W43DfDocument }
     *     
     */
    public void setPutDocument(W43DfDocument value) {
        this.putDocument = value;
    }

}
