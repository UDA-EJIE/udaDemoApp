package aa79b.util.beans;

import java.io.Serializable;

/**
 * @author mrodriguez
 *
 */
public class BitacoraExpediente implements Serializable {
	
	private static final long serialVersionUID = 6829987493491222047L;
	
	private Long anyo;
	private Integer numExp;
	private Long idEstadoBitacora;
	private Long idEstadoExp;
	private Long idFaseExp;
	private Long idMotivoAnulacion;
	private SubsanacionExpediente subsanacionExp;
	
	/**
	 * @return the anyo
	 */
	public Long getAnyo() {
		return this.anyo;
	}
	/**
	 * @param anyo the anyo to set
	 */
	public void setAnyo(Long anyo) {
		this.anyo = anyo;
	}
	/**
	 * @return the numExp
	 */
	public Integer getNumExp() {
		return this.numExp;
	}
	/**
	 * @param numExp the numExp to set
	 */
	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}
	/**
	 * @return the idEstadoBitacora
	 */
	public Long getIdEstadoBitacora() {
		return this.idEstadoBitacora;
	}
	/**
	 * @param idEstadoBitacora the idEstadoBitacora to set
	 */
	public void setIdEstadoBitacora(Long idEstadoBitacora) {
		this.idEstadoBitacora = idEstadoBitacora;
	}
	/**
	 * @return the idEstadoExp
	 */
	public Long getIdEstadoExp() {
		return this.idEstadoExp;
	}
	/**
	 * @param idEstadoExp the idEstadoExp to set
	 */
	public void setIdEstadoExp(Long idEstadoExp) {
		this.idEstadoExp = idEstadoExp;
	}
	/**
	 * @return the idFaseExp
	 */
	public Long getIdFaseExp() {
		return this.idFaseExp;
	}
	/**
	 * @param idFaseExp the idFaseExp to set
	 */
	public void setIdFaseExp(Long idFaseExp) {
		this.idFaseExp = idFaseExp;
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
	/**
	 * @return the subsanacionExp
	 */
	public SubsanacionExpediente getSubsanacionExp() {
		return this.subsanacionExp;
	}
	/**
	 * @param subsanacionExp the subsanacionExp to set
	 */
	public void setSubsanacionExp(SubsanacionExpediente subsanacionExp) {
		this.subsanacionExp = subsanacionExp;
	}

}
