
package aa79b.w43df.pid.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getDocumentInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getDocumentInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getDocumentInfo" type="{com/ejie/documents/xml}w43DfGETDocumentInfo" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDocumentInfo", propOrder = {
    "getDocumentInfo"
})
public class GetDocumentInfo {

    @XmlElement(namespace = "com/ejie/documents/xml")
    protected W43DfGETDocumentInfo getDocumentInfo;

    /**
     * Gets the value of the getDocumentInfo property.
     * 
     * @return
     *     possible object is
     *     {@link W43DfGETDocumentInfo }
     *     
     */
    public W43DfGETDocumentInfo getGetDocumentInfo() {
        return getDocumentInfo;
    }

    /**
     * Sets the value of the getDocumentInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link W43DfGETDocumentInfo }
     *     
     */
    public void setGetDocumentInfo(W43DfGETDocumentInfo value) {
        this.getDocumentInfo = value;
    }

}
