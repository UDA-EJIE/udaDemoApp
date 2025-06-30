
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
 *         &lt;element name="verifyCertificateLocationResult" type="{http://com.ejie.x43f/X43FNSHF/}verificationResultType"/>
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
    "verifyCertificateLocationResult"
})
@XmlRootElement(name = "verifyCertificateLocationResponse")
public class VerifyCertificateLocationResponse {

    @XmlElement(required = true)
    protected VerificationResultType verifyCertificateLocationResult;

    /**
     * Obtiene el valor de la propiedad verifyCertificateLocationResult.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getVerifyCertificateLocationResult() {
        return verifyCertificateLocationResult;
    }

    /**
     * Define el valor de la propiedad verifyCertificateLocationResult.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setVerifyCertificateLocationResult(VerificationResultType value) {
        this.verifyCertificateLocationResult = value;
    }

}
