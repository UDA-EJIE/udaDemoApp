
package aa79b.webservices.srp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfWSParamField complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWSParamField">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WSParamField" type="{http://tempuri.org/}WSParamField" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWSParamField", propOrder = {
    "wsParamField"
})
public class ArrayOfWSParamField {

    @XmlElement(name = "WSParamField", nillable = true)
    protected List<WSParamField> wsParamField;

    /**
     * Gets the value of the wsParamField property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsParamField property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSParamField().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSParamField }
     * 
     * 
     */
    public List<WSParamField> getWSParamField() {
        if (wsParamField == null) {
            wsParamField = new ArrayList<WSParamField>();
        }
        return this.wsParamField;
    }

}
