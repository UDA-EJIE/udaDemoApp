
package aa79b.webservices.x43f;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="verifySignatureLocationResult" type="{http://com.ejie.x43f/X43FNSHF/}verificationResultType"/>
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
    "verifySignatureLocationResult"
})
@XmlRootElement(name = "verifySignatureLocationResponse")
public class VerifySignatureLocationResponse {

    @XmlElement(required = true)
    protected VerificationResultType verifySignatureLocationResult;

    /**
     * Obtiene el valor de la propiedad verifySignatureLocationResult.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getVerifySignatureLocationResult() {
        return verifySignatureLocationResult;
    }

    /**
     * Define el valor de la propiedad verifySignatureLocationResult.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setVerifySignatureLocationResult(VerificationResultType value) {
        this.verifySignatureLocationResult = value;
    }

}
