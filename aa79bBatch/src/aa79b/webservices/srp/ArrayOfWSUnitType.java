
package aa79b.webservices.srp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfWSUnitType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWSUnitType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WSUnitType" type="{http://tempuri.org/}WSUnitType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWSUnitType", propOrder = {
    "wsUnitType"
})
public class ArrayOfWSUnitType {

    @XmlElement(name = "WSUnitType", nillable = true)
    protected List<WSUnitType> wsUnitType;

    /**
     * Gets the value of the wsUnitType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsUnitType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSUnitType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSUnitType }
     * 
     * 
     */
    public List<WSUnitType> getWSUnitType() {
        if (wsUnitType == null) {
            wsUnitType = new ArrayList<WSUnitType>();
        }
        return this.wsUnitType;
    }

}
