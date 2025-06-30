
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
 *         &lt;element name="WSNewOutputRegisterResult" type="{http://tempuri.org/}WSOutputRegister" minOccurs="0"/>
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
    "wsNewOutputRegisterResult"
})
@XmlRootElement(name = "WSNewOutputRegisterResponse")
public class WSNewOutputRegisterResponse {

    @XmlElement(name = "WSNewOutputRegisterResult")
    protected WSOutputRegister wsNewOutputRegisterResult;

    /**
     * Gets the value of the wsNewOutputRegisterResult property.
     * 
     * @return
     *     possible object is
     *     {@link WSOutputRegister }
     *     
     */
    public WSOutputRegister getWSNewOutputRegisterResult() {
        return wsNewOutputRegisterResult;
    }

    /**
     * Sets the value of the wsNewOutputRegisterResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSOutputRegister }
     *     
     */
    public void setWSNewOutputRegisterResult(WSOutputRegister value) {
        this.wsNewOutputRegisterResult = value;
    }

}
