package aa79b.util.beans;

/**
 * MotivosAnulacion
 * 
 * @author mrodriguez
 */
public class MotivosAnulacion {
	
	private Long id012;
	private String descEs012;
	private String descEu012;
	private String estado012;
	
	/**
	 * Method 'MotivosAnulacion'.
	 */
	public MotivosAnulacion() {
	    /*constructor*/
	}

	/**
	 * Method 'getId012'.
	 *
	 * @return Long
	 */

	public Long getId012() {
		return this.id012;
	}

	/**
	 * Method 'setId012'.
	 *
	 * @param id012
	 *            Long
	 * @return
	 */

	public void setId012(Long id012) {
		this.id012 = id012;
	}

	/**
	 * Method 'getDescEs012'.
	 *
	 * @return String
	 */

	public String getDescEs012() {
		return this.descEs012;
	}

	/**
	 * Method 'setDescEs012'.
	 *
	 * @param descEs012
	 *            String
	 * @return
	 */

	public void setDescEs012(String descEs012) {
		this.descEs012 = descEs012;
	}

	/**
	 * Method 'getDescEu012'.
	 *
	 * @return String
	 */

	public String getDescEu012() {
		return this.descEu012;
	}

	/**
	 * Method 'setDescEu012'.
	 *
	 * @param descEu012
	 *            String
	 * @return
	 */

	public void setDescEu012(String descEu012) {
		this.descEu012 = descEu012;
	}

	/**
	 * Method 'getEstado012'.
	 *
	 * @return String
	 */

	public String getEstado012() {
		return this.estado012;
	}

	/**
	 * Method 'setEstado012'.
	 *
	 * @param estado012
	 *            String
	 * @return
	 */

	public void setEstado012(String estado012) {
		this.estado012 = estado012;
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
		result.append(" [ id012: ").append(this.id012).append(" ]");
		result.append(", [ descEs012: ").append(this.descEs012).append(" ]");
		result.append(", [ descEu012: ").append(this.descEu012).append(" ]");
		result.append(", [ estado012: ").append(this.estado012).append(" ]");
		result.append("}");
		return result.toString();
	}

}
