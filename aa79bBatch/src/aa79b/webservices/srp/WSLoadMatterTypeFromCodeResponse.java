
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
 *         &lt;element name="WSLoadMatterTypeFromCodeResult" type="{http://tempuri.org/}WSMatterType" minOccurs="0"/>
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
    "wsLoadMatterTypeFromCodeResult"
})
@XmlRootElement(name = "WSLoadMatterTypeFromCodeResponse")
public class WSLoadMatterTypeFromCodeResponse {

    @XmlElement(name = "WSLoadMatterTypeFromCodeResult")
    protected WSMatterType wsLoadMatterTypeFromCodeResult;

    /**
     * Gets the value of the wsLoadMatterTypeFromCodeResult property.
     * 
     * @return
     *     possible object is
     *     {@link WSMatterType }
     *     
     */
    public WSMatterType getWSLoadMatterTypeFromCodeResult() {
        return wsLoadMatterTypeFromCodeResult;
    }

    /**
     * Sets the value of the wsLoadMatterTypeFromCodeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSMatterType }
     *     
     */
    public void setWSLoadMatterTypeFromCodeResult(WSMatterType value) {
        this.wsLoadMatterTypeFromCodeResult = value;
    }

}
