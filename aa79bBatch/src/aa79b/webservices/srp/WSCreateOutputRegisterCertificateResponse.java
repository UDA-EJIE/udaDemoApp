
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
 *         &lt;element name="WSCreateOutputRegisterCertificateResult" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
    "wsCreateOutputRegisterCertificateResult"
})
@XmlRootElement(name = "WSCreateOutputRegisterCertificateResponse")
public class WSCreateOutputRegisterCertificateResponse {

    @XmlElement(name = "WSCreateOutputRegisterCertificateResult")
    protected byte[] wsCreateOutputRegisterCertificateResult;

    /**
     * Gets the value of the wsCreateOutputRegisterCertificateResult property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getWSCreateOutputRegisterCertificateResult() {
        return wsCreateOutputRegisterCertificateResult;
    }

    /**
     * Sets the value of the wsCreateOutputRegisterCertificateResult property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setWSCreateOutputRegisterCertificateResult(byte[] value) {
        this.wsCreateOutputRegisterCertificateResult = ((byte[]) value);
    }

}
