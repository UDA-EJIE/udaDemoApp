
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
 *         &lt;element name="WSGetBooksOfficeResult" type="{http://tempuri.org/}BooksOffice" minOccurs="0"/>
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
    "wsGetBooksOfficeResult"
})
@XmlRootElement(name = "WSGetBooksOfficeResponse")
public class WSGetBooksOfficeResponse {

    @XmlElement(name = "WSGetBooksOfficeResult")
    protected BooksOffice wsGetBooksOfficeResult;

    /**
     * Gets the value of the wsGetBooksOfficeResult property.
     * 
     * @return
     *     possible object is
     *     {@link BooksOffice }
     *     
     */
    public BooksOffice getWSGetBooksOfficeResult() {
        return wsGetBooksOfficeResult;
    }

    /**
     * Sets the value of the wsGetBooksOfficeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooksOffice }
     *     
     */
    public void setWSGetBooksOfficeResult(BooksOffice value) {
        this.wsGetBooksOfficeResult = value;
    }

}
