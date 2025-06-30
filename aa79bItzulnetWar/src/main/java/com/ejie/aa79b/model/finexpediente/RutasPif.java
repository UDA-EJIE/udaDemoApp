package com.ejie.aa79b.model.finexpediente;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "rutaPif" })
@XmlRootElement(name = "")
public class RutasPif {

	protected List<String> rutaPif;

	/**
	 * Gets the value of the rutaPif property.
	 *
	 */
	public List<String> getrutaPif() {
		return this.rutaPif;
	}

	/**
	 * Sets the value of the rutaPif property.
	 *
	 */
	public void setRutaPif(List<String> value) {
		this.rutaPif = value;
	}

}
