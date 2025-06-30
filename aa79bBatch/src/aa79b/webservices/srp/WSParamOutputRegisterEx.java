
package aa79b.webservices.srp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSParamOutputRegisterEx complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSParamOutputRegisterEx">
 *   &lt;complexContent>
 *     &lt;extension base="{http://tempuri.org/}WSParamOutputRegister">
 *       &lt;sequence>
 *         &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Documents" type="{http://tempuri.org/}ArrayOfWSParamDocument" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSParamOutputRegisterEx", propOrder = {
    "language",
    "documents"
})
public class WSParamOutputRegisterEx
    extends WSParamOutputRegister
{

    @XmlElement(name = "Language")
    protected String language;
    @XmlElement(name = "Documents")
    protected ArrayOfWSParamDocument documents;

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the documents property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSParamDocument }
     *     
     */
    public ArrayOfWSParamDocument getDocuments() {
        return documents;
    }

    /**
     * Sets the value of the documents property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSParamDocument }
     *     
     */
    public void setDocuments(ArrayOfWSParamDocument value) {
        this.documents = value;
    }

}
