
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
 *         &lt;element name="computableXMLLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="templateXSLLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "computableXMLLocation",
    "templateXSLLocation",
    "appDest"
})
@XmlRootElement(name = "createEjgvDocumentFromXmlLocation")
public class CreateEjgvDocumentFromXmlLocation {

    @XmlElement(required = true)
    protected String computableXMLLocation;
    @XmlElement(required = true)
    protected String templateXSLLocation;
    @XmlElement(required = true)
    protected String appDest;

    /**
     * Obtiene el valor de la propiedad computableXMLLocation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComputableXMLLocation() {
        return computableXMLLocation;
    }

    /**
     * Define el valor de la propiedad computableXMLLocation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComputableXMLLocation(String value) {
        this.computableXMLLocation = value;
    }

    /**
     * Obtiene el valor de la propiedad templateXSLLocation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplateXSLLocation() {
        return templateXSLLocation;
    }

    /**
     * Define el valor de la propiedad templateXSLLocation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplateXSLLocation(String value) {
        this.templateXSLLocation = value;
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
