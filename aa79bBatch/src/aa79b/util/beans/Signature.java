package aa79b.util.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Signature implements Serializable {
	
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
		return this.type;
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
		return this.placement;
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
		return this.format;
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
		return this.isConservable;
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
		return this.documentIsRequired;
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
		return this.version;
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
		return this.flags;
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
		return this.tsaSerialNumber;
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
		return this.sign;
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
		return this.auditUser;
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
		return this.integrationToken;
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
		return this.documentID;
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
		return this.formatExtension;
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
		return this.fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
