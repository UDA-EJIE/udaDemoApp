
package aa79b.w43df.pid.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for testInteroperability complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="testInteroperability">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="newDocuments" type="{com/ejie/documents/xml}w43DfDocument" maxOccurs="unbounded" minOccurs="0" form="qualified"/>
 *         &lt;element name="doParse" type="{http://www.w3.org/2001/XMLSchema}boolean" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "testInteroperability", propOrder = {
    "newDocuments",
    "doParse"
})
public class TestInteroperability {

    @XmlElement(namespace = "com/ejie/documents/xml")
    protected List<W43DfDocument> newDocuments;
    @XmlElement(namespace = "com/ejie/documents/xml")
    protected boolean doParse;

    /**
     * Gets the value of the newDocuments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the newDocuments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNewDocuments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link W43DfDocument }
     * 
     * 
     */
    public List<W43DfDocument> getNewDocuments() {
        if (newDocuments == null) {
            newDocuments = new ArrayList<W43DfDocument>();
        }
        return this.newDocuments;
    }

    /**
     * Gets the value of the doParse property.
     * 
     */
    public boolean isDoParse() {
        return doParse;
    }

    /**
     * Sets the value of the doParse property.
     * 
     */
    public void setDoParse(boolean value) {
        this.doParse = value;
    }

}
