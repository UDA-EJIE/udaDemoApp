
package aa79b.w43df.pid.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for w43DfGETSignature complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="w43DfGETSignature">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="signedFormats" type="{com/ejie/documents/xml}w43DfFormat" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="getAll" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "w43DfGETSignature", propOrder = {
    "signedFormats",
    "getAll"
})
public class W43DfGETSignature {

    protected List<W43DfFormat> signedFormats;
    protected boolean getAll;

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

    /**
     * Gets the value of the getAll property.
     * 
     */
    public boolean isGetAll() {
        return getAll;
    }

    /**
     * Sets the value of the getAll property.
     * 
     */
    public void setGetAll(boolean value) {
        this.getAll = value;
    }

}
