
package aa79b.webservices.x43f;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para header complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="header">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="placement" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="format" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isConservable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isIdazkiDesktop" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="documentIsRequired" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="flags" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tsaSerialNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "header", propOrder = {
    "type",
    "placement",
    "format",
    "isConservable",
    "isIdazkiDesktop",
    "documentIsRequired",
    "version",
    "flags",
    "tsaSerialNumber"
})
public class Header {

    @XmlElement(required = true)
    protected String type;
    @XmlElement(required = true)
    protected String placement;
    protected String format;
    protected String isConservable;
    protected String isIdazkiDesktop;
    protected String documentIsRequired;
    @XmlElement(required = true)
    protected String version;
    protected String flags;
    protected String tsaSerialNumber;

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
     * Obtiene el valor de la propiedad placement.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlacement() {
        return placement;
    }

    /**
     * Define el valor de la propiedad placement.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlacement(String value) {
        this.placement = value;
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
     * Obtiene el valor de la propiedad isConservable.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsConservable() {
        return isConservable;
    }

    /**
     * Define el valor de la propiedad isConservable.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsConservable(String value) {
        this.isConservable = value;
    }

    /**
     * Obtiene el valor de la propiedad isIdazkiDesktop.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsIdazkiDesktop() {
        return isIdazkiDesktop;
    }

    /**
     * Define el valor de la propiedad isIdazkiDesktop.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsIdazkiDesktop(String value) {
        this.isIdazkiDesktop = value;
    }

    /**
     * Obtiene el valor de la propiedad documentIsRequired.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentIsRequired() {
        return documentIsRequired;
    }

    /**
     * Define el valor de la propiedad documentIsRequired.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentIsRequired(String value) {
        this.documentIsRequired = value;
    }

    /**
     * Obtiene el valor de la propiedad version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Define el valor de la propiedad version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Obtiene el valor de la propiedad flags.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlags() {
        return flags;
    }

    /**
     * Define el valor de la propiedad flags.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlags(String value) {
        this.flags = value;
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

}
