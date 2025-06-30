
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
 *         &lt;element name="tipoFirma" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="signatureLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "tipoFirma",
    "signatureLocation",
    "appDest"
})
@XmlRootElement(name = "createSignatureEjgvDocument")
public class CreateSignatureEjgvDocument {

    @XmlElement(required = true)
    protected String tipoFirma;
    @XmlElement(required = true)
    protected String signatureLocation;
    @XmlElement(required = true)
    protected String appDest;

    /**
     * Obtiene el valor de la propiedad tipoFirma.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoFirma() {
        return tipoFirma;
    }

    /**
     * Define el valor de la propiedad tipoFirma.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoFirma(String value) {
        this.tipoFirma = value;
    }

    /**
     * Obtiene el valor de la propiedad signatureLocation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignatureLocation() {
        return signatureLocation;
    }

    /**
     * Define el valor de la propiedad signatureLocation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignatureLocation(String value) {
        this.signatureLocation = value;
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
