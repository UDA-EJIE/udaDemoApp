
package com.ejie.w43df.pid.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for w43DfDocument complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="w43DfDocument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="core" type="{com/ejie/documents/xml}w43DfCore" minOccurs="0"/>
 *         &lt;element name="content" type="{com/ejie/documents/xml}w43DfContent" minOccurs="0"/>
 *         &lt;element name="attributes" type="{com/ejie/documents/xml}w43DfAttribute" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="signatures" type="{com/ejie/documents/xml}w43DfSignature" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "w43DfDocument", propOrder = {
    "id",
    "type",
    "core",
    "content",
    "attributes",
    "signatures"
})
public class W43DfDocument {

    protected String id;
    protected String type;
    protected W43DfCore core;
    protected W43DfContent content;
    protected List<W43DfAttribute> attributes;
    protected List<W43DfSignature> signatures;

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
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link W43DfContent }
     *     
     */
    public W43DfContent getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link W43DfContent }
     *     
     */
    public void setContent(W43DfContent value) {
        this.content = value;
    }

    /**
     * Gets the value of the attributes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attributes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link W43DfAttribute }
     * 
     * 
     */
    public List<W43DfAttribute> getAttributes() {
        if (attributes == null) {
            attributes = new ArrayList<W43DfAttribute>();
        }
        return this.attributes;
    }

    /**
     * Gets the value of the signatures property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the signatures property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSignatures().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link W43DfSignature }
     * 
     * 
     */
    public List<W43DfSignature> getSignatures() {
        if (signatures == null) {
            signatures = new ArrayList<W43DfSignature>();
        }
        return this.signatures;
    }

}
