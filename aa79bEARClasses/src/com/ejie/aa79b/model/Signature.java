/**
 * 
 */
package com.ejie.aa79b.model;

/**
 * @author aresua
 *
 */
public class Signature implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String type;
	private String placement;
	private String format;
	private String isConservable;
	private String documentIsRequired;
	private String version;
	private String flags;
	private String tsaSerialNumber;
	private String sign;
	private String auditUser;
	private String integrationToken;
	private String documentID;
	private String formatExtension;
	private String fecha;

	/**
	 * 
	 */
	public Signature() {
		// Constructor
		super();
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the placement
	 */
	public String getPlacement() {
		return placement;
	}

	/**
	 * @param placement
	 *            the placement to set
	 */
	public void setPlacement(String placement) {
		this.placement = placement;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return the isConservable
	 */
	public String getIsConservable() {
		return isConservable;
	}

	/**
	 * @param isConservable
	 *            the isConservable to set
	 */
	public void setIsConservable(String isConservable) {
		this.isConservable = isConservable;
	}

	/**
	 * @return the documentIsRequired
	 */
	public String getDocumentIsRequired() {
		return documentIsRequired;
	}

	/**
	 * @param documentIsRequired
	 *            the documentIsRequired to set
	 */
	public void setDocumentIsRequired(String documentIsRequired) {
		this.documentIsRequired = documentIsRequired;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the flags
	 */
	public String getFlags() {
		return flags;
	}

	/**
	 * @param flags
	 *            the flags to set
	 */
	public void setFlags(String flags) {
		this.flags = flags;
	}

	/**
	 * @return the tsaSerialNumber
	 */
	public String getTsaSerialNumber() {
		return tsaSerialNumber;
	}

	/**
	 * @param tsaSerialNumber
	 *            the tsaSerialNumber to set
	 */
	public void setTsaSerialNumber(String tsaSerialNumber) {
		this.tsaSerialNumber = tsaSerialNumber;
	}

	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * @param sign
	 *            the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	/**
	 * @return the auditUser
	 */
	public String getAuditUser() {
		return auditUser;
	}

	/**
	 * @param auditUser
	 *            the auditUser to set
	 */
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	/**
	 * @return the integrationToken
	 */
	public String getIntegrationToken() {
		return integrationToken;
	}

	/**
	 * @param integrationToken
	 *            the integrationToken to set
	 */
	public void setIntegrationToken(String integrationToken) {
		this.integrationToken = integrationToken;
	}

	/**
	 * @return the documentID
	 */
	public String getDocumentID() {
		return documentID;
	}

	/**
	 * @param documentID
	 *            the documentID to set
	 */
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}

	/**
	 * @return the formatExtension
	 */
	public String getFormatExtension() {
		return formatExtension;
	}

	/**
	 * @param formatExtension
	 *            the formatExtension to set
	 */
	public void setFormatExtension(String formatExtension) {
		this.formatExtension = formatExtension;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
