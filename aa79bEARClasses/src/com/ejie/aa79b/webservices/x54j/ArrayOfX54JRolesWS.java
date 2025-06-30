
package com.ejie.aa79b.webservices.x54j;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfX54JRolesWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfX54JRolesWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="X54JRolesWS" type="{java:x54j.x54jbeans}X54JRolesWS" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfX54JRolesWS", propOrder = {
    "x54JRolesWS"
})
public class ArrayOfX54JRolesWS {

    @XmlElement(name = "X54JRolesWS", nillable = true)
    protected List<X54JRolesWS> x54JRolesWS;

    /**
     * Gets the value of the x54JRolesWS property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the x54JRolesWS property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getX54JRolesWS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link X54JRolesWS }
     * 
     * 
     */
    public List<X54JRolesWS> getX54JRolesWS() {
        if (x54JRolesWS == null) {
            x54JRolesWS = new ArrayList<X54JRolesWS>();
        }
        return this.x54JRolesWS;
    }

}
