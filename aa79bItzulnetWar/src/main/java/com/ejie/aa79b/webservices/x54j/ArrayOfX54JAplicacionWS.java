
package com.ejie.aa79b.webservices.x54j;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfX54JAplicacionWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfX54JAplicacionWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="X54JAplicacionWS" type="{java:x54j.x54jbeans}X54JAplicacionWS" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfX54JAplicacionWS", propOrder = {
    "x54JAplicacionWS"
})
public class ArrayOfX54JAplicacionWS {

    @XmlElement(name = "X54JAplicacionWS", nillable = true)
    protected List<X54JAplicacionWS> x54JAplicacionWS;

    /**
     * Gets the value of the x54JAplicacionWS property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the x54JAplicacionWS property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getX54JAplicacionWS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link X54JAplicacionWS }
     * 
     * 
     */
    public List<X54JAplicacionWS> getX54JAplicacionWS() {
        if (x54JAplicacionWS == null) {
            x54JAplicacionWS = new ArrayList<X54JAplicacionWS>();
        }
        return this.x54JAplicacionWS;
    }

}
