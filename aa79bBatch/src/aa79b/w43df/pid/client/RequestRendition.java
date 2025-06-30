
package aa79b.w43df.pid.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for requestRendition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="requestRendition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="renditionRequest" type="{com/ejie/documents/xml}w43DfRenditionRequest" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestRendition", propOrder = {
    "renditionRequest"
})
public class RequestRendition {

    @XmlElement(namespace = "com/ejie/documents/xml")
    protected W43DfRenditionRequest renditionRequest;

    /**
     * Gets the value of the renditionRequest property.
     * 
     * @return
     *     possible object is
     *     {@link W43DfRenditionRequest }
     *     
     */
    public W43DfRenditionRequest getRenditionRequest() {
        return renditionRequest;
    }

    /**
     * Sets the value of the renditionRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link W43DfRenditionRequest }
     *     
     */
    public void setRenditionRequest(W43DfRenditionRequest value) {
        this.renditionRequest = value;
    }

}
