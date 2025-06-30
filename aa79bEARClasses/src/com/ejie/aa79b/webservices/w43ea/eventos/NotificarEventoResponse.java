package com.ejie.aa79b.webservices.w43ea.eventos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for notificarEventoResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="notificarEventoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="messageId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notificarEventoResponse", propOrder = { "messageId" })
public class NotificarEventoResponse {

	@XmlElement(required = true)
	protected String messageId;

	/**
	 * Gets the value of the messageId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * Sets the value of the messageId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMessageId(String value) {
		this.messageId = value;
	}

}
