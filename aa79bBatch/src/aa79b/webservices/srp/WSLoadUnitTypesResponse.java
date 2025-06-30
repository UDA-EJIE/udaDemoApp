
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
 *         &lt;element name="WSLoadUnitTypesResult" type="{http://tempuri.org/}WSUnitTypesResponse" minOccurs="0"/>
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
    "wsLoadUnitTypesResult"
})
@XmlRootElement(name = "WSLoadUnitTypesResponse")
public class WSLoadUnitTypesResponse {

    @XmlElement(name = "WSLoadUnitTypesResult")
    protected WSUnitTypesResponse wsLoadUnitTypesResult;

    /**
     * Gets the value of the wsLoadUnitTypesResult property.
     * 
     * @return
     *     possible object is
     *     {@link WSUnitTypesResponse }
     *     
     */
    public WSUnitTypesResponse getWSLoadUnitTypesResult() {
        return wsLoadUnitTypesResult;
    }

    /**
     * Sets the value of the wsLoadUnitTypesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSUnitTypesResponse }
     *     
     */
    public void setWSLoadUnitTypesResult(WSUnitTypesResponse value) {
        this.wsLoadUnitTypesResult = value;
    }

}
