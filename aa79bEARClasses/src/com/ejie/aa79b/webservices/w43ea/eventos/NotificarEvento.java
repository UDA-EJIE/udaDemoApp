package com.ejie.aa79b.webservices.w43ea.eventos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for notificarEvento complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="notificarEvento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="eventWho" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventEntity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventWhat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventTipology" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventTimeStamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="eventCorrelationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reglasSuscripcion" type="{http://www.ejie.net/}reglaSuscripcion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="xmlValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notificarEvento", propOrder = { "eventWho", "eventEntity", "eventWhat", "eventTipology",
		"eventTimeStamp", "eventCorrelationId", "reglasSuscripcion", "xmlValue" })
public class NotificarEvento {

	protected String eventWho;
	protected String eventEntity;
	protected String eventWhat;
	protected String eventTipology;
	@XmlSchemaType(name = "dateTime")
	protected Date eventTimeStamp;
	protected String eventCorrelationId;
	protected List<ReglaSuscripcion> reglasSuscripcion;
	protected String xmlValue;

	/**
	 * Gets the value of the eventWho property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEventWho() {
		return eventWho;
	}

	/**
	 * Sets the value of the eventWho property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEventWho(String value) {
		this.eventWho = value;
	}

	/**
	 * Gets the value of the eventEntity property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEventEntity() {
		return eventEntity;
	}

	/**
	 * Sets the value of the eventEntity property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEventEntity(String value) {
		this.eventEntity = value;
	}

	/**
	 * Gets the value of the eventWhat property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEventWhat() {
		return eventWhat;
	}

	/**
	 * Sets the value of the eventWhat property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEventWhat(String value) {
		this.eventWhat = value;
	}

	/**
	 * Gets the value of the eventTipology property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEventTipology() {
		return eventTipology;
	}

	/**
	 * Sets the value of the eventTipology property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEventTipology(String value) {
		this.eventTipology = value;
	}

	/**
	 * Gets the value of the eventTimeStamp property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public Date getEventTimeStamp() {
		return eventTimeStamp;
	}

	/**
	 * Sets the value of the eventTimeStamp property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setEventTimeStamp(Date value) {
		this.eventTimeStamp = value;
	}

	/**
	 * Gets the value of the eventCorrelationId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEventCorrelationId() {
		return eventCorrelationId;
	}

	/**
	 * Sets the value of the eventCorrelationId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEventCorrelationId(String value) {
		this.eventCorrelationId = value;
	}

	/**
	 * Gets the value of the reglasSuscripcion property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the reglasSuscripcion property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getReglasSuscripcion().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link ReglaSuscripcion }
	 * 
	 * 
	 */
	public List<ReglaSuscripcion> getReglasSuscripcion() {
		if (reglasSuscripcion == null) {
			reglasSuscripcion = new ArrayList<ReglaSuscripcion>();
		}
		return this.reglasSuscripcion;
	}

	/**
	 * Gets the value of the xmlValue property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getXmlValue() {
		return xmlValue;
	}

	/**
	 * Sets the value of the xmlValue property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setXmlValue(String value) {
		this.xmlValue = value;
	}

}
