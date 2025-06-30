
package aa79b.webservices.x43f;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para body complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="body">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sign" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="simpleSignature" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signatureTimestamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signerCertificateRevocationStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "body", propOrder = {
    "sign",
    "simpleSignature",
    "signatureTimestamp",
    "signerCertificateRevocationStatus"
})
public class Body {

    protected String sign;
    protected String simpleSignature;
    protected String signatureTimestamp;
    protected String signerCertificateRevocationStatus;

    /**
     * Obtiene el valor de la propiedad sign.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSign() {
        return sign;
    }

    /**
     * Define el valor de la propiedad sign.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSign(String value) {
        this.sign = value;
    }

    /**
     * Obtiene el valor de la propiedad simpleSignature.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimpleSignature() {
        return simpleSignature;
    }

    /**
     * Define el valor de la propiedad simpleSignature.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimpleSignature(String value) {
        this.simpleSignature = value;
    }

    /**
     * Obtiene el valor de la propiedad signatureTimestamp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignatureTimestamp() {
        return signatureTimestamp;
    }

    /**
     * Define el valor de la propiedad signatureTimestamp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignatureTimestamp(String value) {
        this.signatureTimestamp = value;
    }

    /**
     * Obtiene el valor de la propiedad signerCertificateRevocationStatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignerCertificateRevocationStatus() {
        return signerCertificateRevocationStatus;
    }

    /**
     * Define el valor de la propiedad signerCertificateRevocationStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignerCertificateRevocationStatus(String value) {
        this.signerCertificateRevocationStatus = value;
    }

}
