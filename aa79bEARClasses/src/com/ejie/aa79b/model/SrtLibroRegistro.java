package com.ejie.aa79b.model;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ejie.aa79b.utils.DateUtils;

/**
 * @author dlopez2
 * 
 */
public class SrtLibroRegistro implements Serializable {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(SrtLibroRegistro.class);
	
	/**
	 * serialVersionUID: long.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * registryNumber: String.
	 */
	private String registryNumber = "";
	/**
	 * registryTimestamp: String.
	 */
	private Date registryTimestamp = null;
	/**
	 * registrySubmissionTimestamp: String.
	 */
	private Date registrySubmissionTimestamp = null;

	/**
	 * El getter de registryNumber.
	 * 
	 * @return String
	 */
	public String getRegistryNumber() {
		return this.registryNumber;
	}

	/**
	 * El setter de registryNumber
	 * 
	 * @param registryNumber
	 *            String
	 */
	public void setRegistryNumber(String registryNumber) {
		this.registryNumber = registryNumber;
	}

	/**
	 * El getter de registryTimestamp. Formato DD-MM-YYYY HH:MM:SS.
	 * 
	 * @return Date
	 */
	public Date getRegistryTimestamp() {
		return this.registryTimestamp;
	}

	/**
	 * El setter de registryTimestamp. Formato DD-MM-YYYY HH:MM:SS.
	 * 
	 * @param registryTimestamp
	 *            String
	 */
	public void setRegistryTimestamp(String registryTimestamp) {
		try {
			this.registryTimestamp = DateUtils.formatearFechaSrt(registryTimestamp);
		} catch (Exception e) {
			LOGGER.debug("SrtLibroRegistroBean.setRegistryTimestamp", e);
		}
	}

	/**
	 * El setter de registryTimestamp. Formato DD-MM-YYYY HH:MM:SS.
	 * 
	 * @param registryTimestamp
	 *            Date
	 */
	public void setRegistryTimestamp(Date registryTimestamp) {
		this.registryTimestamp = registryTimestamp;
	}

	/**
	 * El getter de registrySubmissionTimestamp. Formato DD-MM-YYYY HH:MM:SS.
	 * 
	 * @return Date
	 */
	public Date getRegistrySubmissionTimestamp() {
		return this.registrySubmissionTimestamp;
	}

	/**
	 * El setter de registrySubmissionTimestamp. Formato DD-MM-YYYY HH:MM:SS.
	 * 
	 * @param registrySubmissionTimestamp
	 *            String
	 */
	public void setRegistrySubmissionTimestamp(
			String registrySubmissionTimestamp) {
		try {
			this.registrySubmissionTimestamp = DateUtils.formatearFechaSrt(registrySubmissionTimestamp);
		} catch (Exception e) {
			LOGGER.debug("SrtLibroRegistroBean.setRegistrySubmissionTimestamp", e);
		}
	}

	/**
	 * El setter de registrySubmissionTimestamp. Formato DD-MM-YYYY HH:MM:SS.
	 * 
	 * @param registrySubmissionTimestamp
	 *            Date
	 */
	public void setRegistrySubmissionTimestamp(Date registrySubmissionTimestamp) {
		this.registrySubmissionTimestamp = registrySubmissionTimestamp;
	}

	/**
	 * Intended only for logging and debugging.
	 * 
	 * Here, the contents of every main field are placed into the result.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append(" [ registryNumber: ").append(this.registryNumber)
				.append(" ]");
		result.append(", [ registryTimestamp: ").append(this.registryTimestamp)
				.append(" ]");
		result.append(", [ registrySubmissionTimestamp: ")
				.append(this.registrySubmissionTimestamp).append(" ]");
		result.append("}");
		return result.toString();
	}
}
