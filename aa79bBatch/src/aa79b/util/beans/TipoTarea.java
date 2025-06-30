package aa79b.util.beans;

import java.io.Serializable;

/**
 * @author mrodriguez
 *
 */
public class TipoTarea implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String descEs;
	private String descEu;

	/**
	 * Method 'TipoTarea'.
	 */
	public TipoTarea() {
		// Contructor
	}

	/**
	 * Method 'getId'.
	 *
	 * @return Long
	 */

	public Long getId() {
		return this.id;
	}

	/**
	 * Method 'setId'.
	 *
	 * @param id
	 *            Long
	 * @return
	 */

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Method 'getDescEs'.
	 *
	 * @return String
	 */

	public String getDescEs() {
		return this.descEs;
	}

	/**
	 * Method 'setDescEs'.
	 *
	 * @param descEs
	 *            String
	 * @return
	 */

	public void setDescEs(String descEs) {
		this.descEs = descEs;
	}

	/**
	 * Method 'getDescEu'.
	 *
	 * @return String
	 */

	public String getDescEu() {
		return this.descEu;
	}

	/**
	 * Method 'setDescEu'.
	 *
	 * @param descEu
	 *            String
	 * @return
	 */

	public void setDescEu(String descEu) {
		this.descEu = descEu;
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
		result.append(" [ id: ").append(this.id).append(" ]");
		result.append(", [ descEs: ").append(this.descEs).append(" ]");
		result.append(", [ descEu: ").append(this.descEu).append(" ]");
		result.append("}");
		return result.toString();
	}

}

