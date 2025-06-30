
package aa79b.webservices.x43f;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ejgvDocumentType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ejgvDocumentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ejgvDocument" type="{http://com.ejie.x43f/X43FNSHF/}ejgvDocument"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ejgvDocumentType", propOrder = {
    "ejgvDocument"
})
public class EjgvDocumentType {

    @XmlElement(required = true)
    protected EjgvDocument ejgvDocument;

    /**
     * Obtiene el valor de la propiedad ejgvDocument.
     * 
     * @return
     *     possible object is
     *     {@link EjgvDocument }
     *     
     */
    public EjgvDocument getEjgvDocument() {
        return ejgvDocument;
    }

    /**
     * Define el valor de la propiedad ejgvDocument.
     * 
     * @param value
     *     allowed object is
     *     {@link EjgvDocument }
     *     
     */
    public void setEjgvDocument(EjgvDocument value) {
        this.ejgvDocument = value;
    }

}
