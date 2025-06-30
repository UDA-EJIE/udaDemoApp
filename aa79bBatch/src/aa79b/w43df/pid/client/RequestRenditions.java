
package aa79b.w43df.pid.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for requestRenditions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="requestRenditions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="renditionsRequests" type="{com/ejie/documents/xml}w43DfRenditionRequest" maxOccurs="unbounded" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestRenditions", propOrder = {
    "renditionsRequests"
})
public class RequestRenditions {

    @XmlElement(namespace = "com/ejie/documents/xml")
    protected List<W43DfRenditionRequest> renditionsRequests;

    /**
     * Gets the value of the renditionsRequests property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the renditionsRequests property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRenditionsRequests().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link W43DfRenditionRequest }
     * 
     * 
     */
    public List<W43DfRenditionRequest> getRenditionsRequests() {
        if (renditionsRequests == null) {
            renditionsRequests = new ArrayList<W43DfRenditionRequest>();
        }
        return this.renditionsRequests;
    }

}
