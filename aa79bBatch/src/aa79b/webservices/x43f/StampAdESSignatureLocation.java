
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
 *         &lt;element name="signature" type="{http://com.ejie.x43f/X43FNSHF/}ejgvDocumentType"/>
 *         &lt;element name="documentLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "signature",
    "documentLocation"
})
@XmlRootElement(name = "stampAdESSignatureLocation")
public class StampAdESSignatureLocation {

    @XmlElement(required = true)
    protected EjgvDocumentType signature;
    @XmlElement(required = true)
    protected String documentLocation;

    /**
     * Obtiene el valor de la propiedad signature.
     * 
     * @return
     *     possible object is
     *     {@link EjgvDocumentType }
     *     
     */
    public EjgvDocumentType getSignature() {
        return signature;
    }

    /**
     * Define el valor de la propiedad signature.
     * 
     * @param value
     *     allowed object is
     *     {@link EjgvDocumentType }
     *     
     */
    public void setSignature(EjgvDocumentType value) {
        this.signature = value;
    }

    /**
     * Obtiene el valor de la propiedad documentLocation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentLocation() {
        return documentLocation;
    }

    /**
     * Define el valor de la propiedad documentLocation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentLocation(String value) {
        this.documentLocation = value;
    }

}
