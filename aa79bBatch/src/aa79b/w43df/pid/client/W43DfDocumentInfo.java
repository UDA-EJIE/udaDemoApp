
package aa79b.w43df.pid.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for w43DfDocumentInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="w43DfDocumentInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="core" type="{com/ejie/documents/xml}w43DfCore" minOccurs="0"/>
 *         &lt;element name="contentFormats" type="{com/ejie/documents/xml}w43DfFormat" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="indexedFormats" type="{com/ejie/documents/xml}w43DfFormat" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="signedFormats" type="{com/ejie/documents/xml}w43DfFormat" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "w43DfDocumentInfo", propOrder = {
    "id",
    "type",
    "core",
    "contentFormats",
    "indexedFormats",
    "signedFormats"
})
public class W43DfDocumentInfo {

    protected String id;
    protected String type;
    protected W43DfCore core;
    protected List<W43DfFormat> contentFormats;
    protected List<W43DfFormat> indexedFormats;
    protected List<W43DfFormat> signedFormats;

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
     * Gets the value of the type property.
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
     * Sets the value of the type property.
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
     * Gets the value of the core property.
     * 
     * @return
     *     possible object is
     *     {@link W43DfCore }
     *     
     */
    public W43DfCore getCore() {
        return core;
    }

    /**
     * Sets the value of the core property.
     * 
     * @param value
     *     allowed object is
     *     {@link W43DfCore }
     *     
     */
    public void setCore(W43DfCore value) {
        this.core = value;
    }

    /**
     * Gets the value of the contentFormats property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contentFormats property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContentFormats().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link W43DfFormat }
     * 
     * 
     */
    public List<W43DfFormat> getContentFormats() {
        if (contentFormats == null) {
            contentFormats = new ArrayList<W43DfFormat>();
        }
        return this.contentFormats;
    }

    /**
     * Gets the value of the indexedFormats property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the indexedFormats property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIndexedFormats().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link W43DfFormat }
     * 
     * 
     */
    public List<W43DfFormat> getIndexedFormats() {
        if (indexedFormats == null) {
            indexedFormats = new ArrayList<W43DfFormat>();
        }
        return this.indexedFormats;
    }

    /**
     * Gets the value of the signedFormats property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the signedFormats property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSignedFormats().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link W43DfFormat }
     * 
     * 
     */
    public List<W43DfFormat> getSignedFormats() {
        if (signedFormats == null) {
            signedFormats = new ArrayList<W43DfFormat>();
        }
        return this.signedFormats;
    }

}
