
package com.ejie.w43df.pid.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getDocumentsInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getDocumentsInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getDocumentInfos" type="{com/ejie/documents/xml}w43DfGETDocumentInfo" maxOccurs="unbounded" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDocumentsInfo", propOrder = {
    "getDocumentInfos"
})
public class GetDocumentsInfo {

    @XmlElement(namespace = "com/ejie/documents/xml")
    protected List<W43DfGETDocumentInfo> getDocumentInfos;

    /**
     * Gets the value of the getDocumentInfos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the getDocumentInfos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGetDocumentInfos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link W43DfGETDocumentInfo }
     * 
     * 
     */
    public List<W43DfGETDocumentInfo> getGetDocumentInfos() {
        if (getDocumentInfos == null) {
            getDocumentInfos = new ArrayList<W43DfGETDocumentInfo>();
        }
        return this.getDocumentInfos;
    }

}
