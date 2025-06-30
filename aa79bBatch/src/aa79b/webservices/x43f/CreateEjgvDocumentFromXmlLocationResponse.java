
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
 *         &lt;element name="createEjgvDocumentFromXmlLocationResult" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "createEjgvDocumentFromXmlLocationResult"
})
@XmlRootElement(name = "createEjgvDocumentFromXmlLocationResponse")
public class CreateEjgvDocumentFromXmlLocationResponse {

    @XmlElement(required = true)
    protected String createEjgvDocumentFromXmlLocationResult;

    /**
     * Obtiene el valor de la propiedad createEjgvDocumentFromXmlLocationResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateEjgvDocumentFromXmlLocationResult() {
        return createEjgvDocumentFromXmlLocationResult;
    }

    /**
     * Define el valor de la propiedad createEjgvDocumentFromXmlLocationResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateEjgvDocumentFromXmlLocationResult(String value) {
        this.createEjgvDocumentFromXmlLocationResult = value;
    }

}
