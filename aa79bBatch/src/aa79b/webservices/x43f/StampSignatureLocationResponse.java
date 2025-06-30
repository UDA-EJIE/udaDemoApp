
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
 *         &lt;element name="stampSignatureLocationResult" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "stampSignatureLocationResult"
})
@XmlRootElement(name = "stampSignatureLocationResponse")
public class StampSignatureLocationResponse {

    @XmlElement(required = true)
    protected String stampSignatureLocationResult;

    /**
     * Obtiene el valor de la propiedad stampSignatureLocationResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStampSignatureLocationResult() {
        return stampSignatureLocationResult;
    }

    /**
     * Define el valor de la propiedad stampSignatureLocationResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStampSignatureLocationResult(String value) {
        this.stampSignatureLocationResult = value;
    }

}
