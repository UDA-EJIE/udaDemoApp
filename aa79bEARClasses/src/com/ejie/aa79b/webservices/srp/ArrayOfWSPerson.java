
package com.ejie.aa79b.webservices.srp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfWSPerson complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWSPerson">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WSPerson" type="{http://tempuri.org/}WSPerson" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWSPerson", propOrder = {
    "wsPerson"
})
public class ArrayOfWSPerson {

    @XmlElement(name = "WSPerson", nillable = true)
    protected List<WSPerson> wsPerson;

    /**
     * Gets the value of the wsPerson property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsPerson property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSPerson().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSPerson }
     * 
     * 
     */
    public List<WSPerson> getWSPerson() {
        if (wsPerson == null) {
            wsPerson = new ArrayList<WSPerson>();
        }
        return this.wsPerson;
    }

}
