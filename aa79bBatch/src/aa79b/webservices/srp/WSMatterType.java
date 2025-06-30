
package aa79b.webservices.srp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSMatterType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSMatterType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Active" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="AssociatedUnitId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="InputMatterType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OutputMatterType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSMatterType", propOrder = {
    "active",
    "associatedUnitId",
    "code",
    "id",
    "inputMatterType",
    "name",
    "outputMatterType"
})
public class WSMatterType {

    @XmlElement(name = "Active")
    protected int active;
    @XmlElement(name = "AssociatedUnitId")
    protected int associatedUnitId;
    @XmlElement(name = "Code")
    protected String code;
    @XmlElement(name = "Id")
    protected int id;
    @XmlElement(name = "InputMatterType")
    protected int inputMatterType;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "OutputMatterType")
    protected int outputMatterType;

    /**
     * Gets the value of the active property.
     * 
     */
    public int getActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     */
    public void setActive(int value) {
        this.active = value;
    }

    /**
     * Gets the value of the associatedUnitId property.
     * 
     */
    public int getAssociatedUnitId() {
        return associatedUnitId;
    }

    /**
     * Sets the value of the associatedUnitId property.
     * 
     */
    public void setAssociatedUnitId(int value) {
        this.associatedUnitId = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the inputMatterType property.
     * 
     */
    public int getInputMatterType() {
        return inputMatterType;
    }

    /**
     * Sets the value of the inputMatterType property.
     * 
     */
    public void setInputMatterType(int value) {
        this.inputMatterType = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the outputMatterType property.
     * 
     */
    public int getOutputMatterType() {
        return outputMatterType;
    }

    /**
     * Sets the value of the outputMatterType property.
     * 
     */
    public void setOutputMatterType(int value) {
        this.outputMatterType = value;
    }

}
