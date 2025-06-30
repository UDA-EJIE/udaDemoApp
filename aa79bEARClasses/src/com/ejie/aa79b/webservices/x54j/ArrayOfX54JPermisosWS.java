
package com.ejie.aa79b.webservices.x54j;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfX54JPermisosWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfX54JPermisosWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="X54JPermisosWS" type="{java:x54j.x54jbeans}X54JPermisosWS" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfX54JPermisosWS", propOrder = {
    "x54JPermisosWS"
})
public class ArrayOfX54JPermisosWS {

    @XmlElement(name = "X54JPermisosWS", nillable = true)
    protected List<X54JPermisosWS> x54JPermisosWS;

    /**
     * Gets the value of the x54JPermisosWS property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the x54JPermisosWS property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getX54JPermisosWS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link X54JPermisosWS }
     * 
     * 
     */
    public List<X54JPermisosWS> getX54JPermisosWS() {
        if (x54JPermisosWS == null) {
            x54JPermisosWS = new ArrayList<X54JPermisosWS>();
        }
        return this.x54JPermisosWS;
    }

}
