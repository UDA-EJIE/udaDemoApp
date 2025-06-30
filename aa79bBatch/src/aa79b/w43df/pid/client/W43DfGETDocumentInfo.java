
package aa79b.w43df.pid.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for w43DfGETDocumentInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="w43DfGETDocumentInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="getContentFormatsInfo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="getSignaturesFormatsInfo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="getIndexInfo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "w43DfGETDocumentInfo", propOrder = {
    "id",
    "getContentFormatsInfo",
    "getSignaturesFormatsInfo",
    "getIndexInfo"
})
public class W43DfGETDocumentInfo {

    protected String id;
    protected boolean getContentFormatsInfo;
    protected boolean getSignaturesFormatsInfo;
    protected boolean getIndexInfo;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the getContentFormatsInfo property.
     * 
     */
    public boolean isGetContentFormatsInfo() {
        return getContentFormatsInfo;
    }

    /**
     * Sets the value of the getContentFormatsInfo property.
     * 
     */
    public void setGetContentFormatsInfo(boolean value) {
        this.getContentFormatsInfo = value;
    }

    /**
     * Gets the value of the getSignaturesFormatsInfo property.
     * 
     */
    public boolean isGetSignaturesFormatsInfo() {
        return getSignaturesFormatsInfo;
    }

    /**
     * Sets the value of the getSignaturesFormatsInfo property.
     * 
     */
    public void setGetSignaturesFormatsInfo(boolean value) {
        this.getSignaturesFormatsInfo = value;
    }

    /**
     * Gets the value of the getIndexInfo property.
     * 
     */
    public boolean isGetIndexInfo() {
        return getIndexInfo;
    }

    /**
     * Sets the value of the getIndexInfo property.
     * 
     */
    public void setGetIndexInfo(boolean value) {
        this.getIndexInfo = value;
    }

}
