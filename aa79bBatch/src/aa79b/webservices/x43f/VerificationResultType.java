
package aa79b.webservices.x43f;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para verificationResultType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="verificationResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="verificationResult" type="{http://com.ejie.x43f/X43FNSHF/}verificationResult"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verificationResultType", propOrder = {
    "verificationResult"
})
public class VerificationResultType {

    @XmlElement(required = true)
    protected VerificationResult verificationResult;

    /**
     * Obtiene el valor de la propiedad verificationResult.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResult }
     *     
     */
    public VerificationResult getVerificationResult() {
        return verificationResult;
    }

    /**
     * Define el valor de la propiedad verificationResult.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResult }
     *     
     */
    public void setVerificationResult(VerificationResult value) {
        this.verificationResult = value;
    }

}
