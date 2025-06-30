
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
 *         &lt;element name="WSValidateUnitCodeResult" type="{http://tempuri.org/}WSUnit" minOccurs="0"/>
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
    "wsValidateUnitCodeResult"
})
@XmlRootElement(name = "WSValidateUnitCodeResponse")
public class WSValidateUnitCodeResponse {

    @XmlElement(name = "WSValidateUnitCodeResult")
    protected WSUnit wsValidateUnitCodeResult;

    /**
     * Gets the value of the wsValidateUnitCodeResult property.
     * 
     * @return
     *     possible object is
     *     {@link WSUnit }
     *     
     */
    public WSUnit getWSValidateUnitCodeResult() {
        return wsValidateUnitCodeResult;
    }

    /**
     * Sets the value of the wsValidateUnitCodeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSUnit }
     *     
     */
    public void setWSValidateUnitCodeResult(WSUnit value) {
        this.wsValidateUnitCodeResult = value;
    }

}
