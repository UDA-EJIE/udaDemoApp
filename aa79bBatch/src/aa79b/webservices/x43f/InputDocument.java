
package aa79b.webservices.x43f;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para inputDocument complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="inputDocument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="documentContent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="documentLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="documentHash" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="documentHashAlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inputRefUri" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inputDocument", propOrder = {
    "documentContent",
    "documentLocation",
    "documentHash",
    "documentHashAlg",
    "inputRefUri"
})
public class InputDocument {

    protected byte[] documentContent;
    protected String documentLocation;
    protected String documentHash;
    protected String documentHashAlg;
    protected String inputRefUri;

    /**
     * Obtiene el valor de la propiedad documentContent.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getDocumentContent() {
        return documentContent;
    }

    /**
     * Define el valor de la propiedad documentContent.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setDocumentContent(byte[] value) {
        this.documentContent = value;
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

    /**
     * Obtiene el valor de la propiedad documentHash.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentHash() {
        return documentHash;
    }

    /**
     * Define el valor de la propiedad documentHash.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentHash(String value) {
        this.documentHash = value;
    }

    /**
     * Obtiene el valor de la propiedad documentHashAlg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentHashAlg() {
        return documentHashAlg;
    }

    /**
     * Define el valor de la propiedad documentHashAlg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentHashAlg(String value) {
        this.documentHashAlg = value;
    }

    /**
     * Obtiene el valor de la propiedad inputRefUri.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputRefUri() {
        return inputRefUri;
    }

    /**
     * Define el valor de la propiedad inputRefUri.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputRefUri(String value) {
        this.inputRefUri = value;
    }

}
