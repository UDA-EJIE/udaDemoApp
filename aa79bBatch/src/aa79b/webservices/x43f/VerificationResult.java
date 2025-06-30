
package aa79b.webservices.x43f;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para verificationResult complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="verificationResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="isSuccessResult" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resultMajor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resultMinor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resultMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signingTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="format" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tsaSerialNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signCertificates" type="{http://com.ejie.x43f/X43FNSHF/}signCertificatesType"/>
 *         &lt;element name="signatureFormat" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="signatureType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verificationResult", propOrder = {
    "isSuccessResult",
    "resultMajor",
    "resultMinor",
    "resultMessage",
    "signingTime",
    "format",
    "type",
    "tsaSerialNumber",
    "signCertificates",
    "signatureFormat",
    "signatureType"
})
public class VerificationResult {

    @XmlElement(required = true)
    protected String isSuccessResult;
    @XmlElement(required = true)
    protected String resultMajor;
    @XmlElement(required = true)
    protected String resultMinor;
    protected String resultMessage;
    @XmlElement(required = true)
    protected String signingTime;
    @XmlElement(required = true)
    protected String format;
    @XmlElement(required = true)
    protected String type;
    protected String tsaSerialNumber;
    @XmlElement(required = true)
    protected SignCertificatesType signCertificates;
    @XmlElement(required = true)
    protected String signatureFormat;
    @XmlElement(required = true)
    protected String signatureType;

    /**
     * Obtiene el valor de la propiedad isSuccessResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsSuccessResult() {
        return isSuccessResult;
    }

    /**
     * Define el valor de la propiedad isSuccessResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsSuccessResult(String value) {
        this.isSuccessResult = value;
    }

    /**
     * Obtiene el valor de la propiedad resultMajor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultMajor() {
        return resultMajor;
    }

    /**
     * Define el valor de la propiedad resultMajor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultMajor(String value) {
        this.resultMajor = value;
    }

    /**
     * Obtiene el valor de la propiedad resultMinor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultMinor() {
        return resultMinor;
    }

    /**
     * Define el valor de la propiedad resultMinor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultMinor(String value) {
        this.resultMinor = value;
    }

    /**
     * Obtiene el valor de la propiedad resultMessage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * Define el valor de la propiedad resultMessage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultMessage(String value) {
        this.resultMessage = value;
    }

    /**
     * Obtiene el valor de la propiedad signingTime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSigningTime() {
        return signingTime;
    }

    /**
     * Define el valor de la propiedad signingTime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSigningTime(String value) {
        this.signingTime = value;
    }

    /**
     * Obtiene el valor de la propiedad format.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormat() {
        return format;
    }

    /**
     * Define el valor de la propiedad format.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormat(String value) {
        this.format = value;
    }

    /**
     * Obtiene el valor de la propiedad type.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Define el valor de la propiedad type.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Obtiene el valor de la propiedad tsaSerialNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTsaSerialNumber() {
        return tsaSerialNumber;
    }

    /**
     * Define el valor de la propiedad tsaSerialNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsaSerialNumber(String value) {
        this.tsaSerialNumber = value;
    }

    /**
     * Obtiene el valor de la propiedad signCertificates.
     * 
     * @return
     *     possible object is
     *     {@link SignCertificatesType }
     *     
     */
    public SignCertificatesType getSignCertificates() {
        return signCertificates;
    }

    /**
     * Define el valor de la propiedad signCertificates.
     * 
     * @param value
     *     allowed object is
     *     {@link SignCertificatesType }
     *     
     */
    public void setSignCertificates(SignCertificatesType value) {
        this.signCertificates = value;
    }

    /**
     * Obtiene el valor de la propiedad signatureFormat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignatureFormat() {
        return signatureFormat;
    }

    /**
     * Define el valor de la propiedad signatureFormat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignatureFormat(String value) {
        this.signatureFormat = value;
    }

    /**
     * Obtiene el valor de la propiedad signatureType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignatureType() {
        return signatureType;
    }

    /**
     * Define el valor de la propiedad signatureType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignatureType(String value) {
        this.signatureType = value;
    }

}
