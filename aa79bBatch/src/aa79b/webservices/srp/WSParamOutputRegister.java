
package aa79b.webservices.srp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSParamOutputRegister complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSParamOutputRegister">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UnitCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Destination" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransportType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransportNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MatterType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Matter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Persons" type="{http://tempuri.org/}ArrayOfWSParamPerson" minOccurs="0"/>
 *         &lt;element name="AddFields" type="{http://tempuri.org/}ArrayOfWSAddField" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSParamOutputRegister", propOrder = {
    "unitCode",
    "sender",
    "destination",
    "transportType",
    "transportNumber",
    "matterType",
    "matter",
    "persons",
    "addFields"
})
@XmlSeeAlso({
    WSParamOutputRegisterEx.class,
    WSParamInputRegister.class
})
public class WSParamOutputRegister {

    @XmlElement(name = "UnitCode")
    protected String unitCode;
    @XmlElement(name = "Sender")
    protected String sender;
    @XmlElement(name = "Destination")
    protected String destination;
    @XmlElement(name = "TransportType")
    protected String transportType;
    @XmlElement(name = "TransportNumber")
    protected String transportNumber;
    @XmlElement(name = "MatterType")
    protected String matterType;
    @XmlElement(name = "Matter")
    protected String matter;
    @XmlElement(name = "Persons")
    protected ArrayOfWSParamPerson persons;
    @XmlElement(name = "AddFields")
    protected ArrayOfWSAddField addFields;

    /**
     * Gets the value of the unitCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitCode() {
        return unitCode;
    }

    /**
     * Sets the value of the unitCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitCode(String value) {
        this.unitCode = value;
    }

    /**
     * Gets the value of the sender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSender() {
        return sender;
    }

    /**
     * Sets the value of the sender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSender(String value) {
        this.sender = value;
    }

    /**
     * Gets the value of the destination property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the value of the destination property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestination(String value) {
        this.destination = value;
    }

    /**
     * Gets the value of the transportType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransportType() {
        return transportType;
    }

    /**
     * Sets the value of the transportType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransportType(String value) {
        this.transportType = value;
    }

    /**
     * Gets the value of the transportNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransportNumber() {
        return transportNumber;
    }

    /**
     * Sets the value of the transportNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransportNumber(String value) {
        this.transportNumber = value;
    }

    /**
     * Gets the value of the matterType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatterType() {
        return matterType;
    }

    /**
     * Sets the value of the matterType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatterType(String value) {
        this.matterType = value;
    }

    /**
     * Gets the value of the matter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatter() {
        return matter;
    }

    /**
     * Sets the value of the matter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatter(String value) {
        this.matter = value;
    }

    /**
     * Gets the value of the persons property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSParamPerson }
     *     
     */
    public ArrayOfWSParamPerson getPersons() {
        return persons;
    }

    /**
     * Sets the value of the persons property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSParamPerson }
     *     
     */
    public void setPersons(ArrayOfWSParamPerson value) {
        this.persons = value;
    }

    /**
     * Gets the value of the addFields property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWSAddField }
     *     
     */
    public ArrayOfWSAddField getAddFields() {
        return addFields;
    }

    /**
     * Sets the value of the addFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWSAddField }
     *     
     */
    public void setAddFields(ArrayOfWSAddField value) {
        this.addFields = value;
    }

}
