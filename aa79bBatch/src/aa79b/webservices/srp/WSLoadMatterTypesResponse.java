
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
 *         &lt;element name="WSLoadMatterTypesResult" type="{http://tempuri.org/}WSMatterTypesResponse" minOccurs="0"/>
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
    "wsLoadMatterTypesResult"
})
@XmlRootElement(name = "WSLoadMatterTypesResponse")
public class WSLoadMatterTypesResponse {

    @XmlElement(name = "WSLoadMatterTypesResult")
    protected WSMatterTypesResponse wsLoadMatterTypesResult;

    /**
     * Gets the value of the wsLoadMatterTypesResult property.
     * 
     * @return
     *     possible object is
     *     {@link WSMatterTypesResponse }
     *     
     */
    public WSMatterTypesResponse getWSLoadMatterTypesResult() {
        return wsLoadMatterTypesResult;
    }

    /**
     * Sets the value of the wsLoadMatterTypesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSMatterTypesResponse }
     *     
     */
    public void setWSLoadMatterTypesResult(WSMatterTypesResponse value) {
        this.wsLoadMatterTypesResult = value;
    }

}
