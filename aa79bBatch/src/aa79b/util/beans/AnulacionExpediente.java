package aa79b.util.beans;

import java.io.Serializable;

public class AnulacionExpediente implements Serializable {
	
	private static final long serialVersionUID = 453212829327418126L;
	
	private Long id;
	private Long idMotivoAnulacion;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the idMotivoAnulacion
	 */
	public Long getIdMotivoAnulacion() {
		return this.idMotivoAnulacion;
	}
	/**
	 * @param idMotivoAnulacion the idMotivoAnulacion to set
	 */
	public void setIdMotivoAnulacion(Long idMotivoAnulacion) {
		this.idMotivoAnulacion = idMotivoAnulacion;
	}

}
