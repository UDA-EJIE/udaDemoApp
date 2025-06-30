
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
 *         &lt;element name="createHashLocationResult" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "createHashLocationResult"
})
@XmlRootElement(name = "createHashLocationResponse")
public class CreateHashLocationResponse {

    @XmlElement(required = true)
    protected String createHashLocationResult;

    /**
     * Obtiene el valor de la propiedad createHashLocationResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateHashLocationResult() {
        return createHashLocationResult;
    }

    /**
     * Define el valor de la propiedad createHashLocationResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateHashLocationResult(String value) {
        this.createHashLocationResult = value;
    }

}
