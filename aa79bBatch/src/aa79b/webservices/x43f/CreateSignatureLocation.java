
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
 *         &lt;element name="inputOptions" type="{http://com.ejie.x43f/X43FNSHF/}inputOptions"/>
 *         &lt;element name="documentLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="appDest" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "inputOptions",
    "documentLocation",
    "appDest"
})
@XmlRootElement(name = "createSignatureLocation")
public class CreateSignatureLocation {

    @XmlElement(required = true)
    protected InputOptions inputOptions;
    @XmlElement(required = true)
    protected String documentLocation;
    @XmlElement(required = true)
    protected String appDest;

    /**
     * Obtiene el valor de la propiedad inputOptions.
     * 
     * @return
     *     possible object is
     *     {@link InputOptions }
     *     
     */
    public InputOptions getInputOptions() {
        return inputOptions;
    }

    /**
     * Define el valor de la propiedad inputOptions.
     * 
     * @param value
     *     allowed object is
     *     {@link InputOptions }
     *     
     */
    public void setInputOptions(InputOptions value) {
        this.inputOptions = value;
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
     * Obtiene el valor de la propiedad appDest.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppDest() {
        return appDest;
    }

    /**
     * Define el valor de la propiedad appDest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppDest(String value) {
        this.appDest = value;
    }

}
