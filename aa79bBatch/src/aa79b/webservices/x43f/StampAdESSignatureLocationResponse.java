
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
 *         &lt;element name="stampAdESSignatureLocationResult" type="{http://com.ejie.x43f/X43FNSHF/}ejgvDocumentType"/>
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
    "stampAdESSignatureLocationResult"
})
@XmlRootElement(name = "stampAdESSignatureLocationResponse")
public class StampAdESSignatureLocationResponse {

    @XmlElement(required = true)
    protected EjgvDocumentType stampAdESSignatureLocationResult;

    /**
     * Obtiene el valor de la propiedad stampAdESSignatureLocationResult.
     * 
     * @return
     *     possible object is
     *     {@link EjgvDocumentType }
     *     
     */
    public EjgvDocumentType getStampAdESSignatureLocationResult() {
        return stampAdESSignatureLocationResult;
    }

    /**
     * Define el valor de la propiedad stampAdESSignatureLocationResult.
     * 
     * @param value
     *     allowed object is
     *     {@link EjgvDocumentType }
     *     
     */
    public void setStampAdESSignatureLocationResult(EjgvDocumentType value) {
        this.stampAdESSignatureLocationResult = value;
    }

}
