
package aa79b.webservices.srp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WSLoadMatterTypeFromIdResult" type="{http://tempuri.org/}WSMatterType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "wsLoadMatterTypeFromIdResult"
})
@XmlRootElement(name = "WSLoadMatterTypeFromIdResponse")
public class WSLoadMatterTypeFromIdResponse {

    @XmlElement(name = "WSLoadMatterTypeFromIdResult")
    protected WSMatterType wsLoadMatterTypeFromIdResult;

    /**
     * Gets the value of the wsLoadMatterTypeFromIdResult property.
     * 
     * @return
     *     possible object is
     *     {@link WSMatterType }
     *     
     */
    public WSMatterType getWSLoadMatterTypeFromIdResult() {
        return wsLoadMatterTypeFromIdResult;
    }

    /**
     * Sets the value of the wsLoadMatterTypeFromIdResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSMatterType }
     *     
     */
    public void setWSLoadMatterTypeFromIdResult(WSMatterType value) {
        this.wsLoadMatterTypeFromIdResult = value;
    }

}
