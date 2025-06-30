package aa79b.util.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mrodriguez
 *
 */
public class SubsanacionExpediente implements Serializable {
	
	private static final long serialVersionUID = -456175490042599074L;
	
	private Long id;
	private Date fechaLimite;
	private String horaLimite;
	private String indSubsanado;
	private Integer estado;
	private String estadoDescEu;
	private String estadoDescEs;
	private Long tipoRequerimiento;
	private String tipoRequerimientoDescEu;
	private String tipoRequerimientoDescEs;
	private Double difPlazoEntrega;
	
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
	 * @return the fechaLimite
	 */
	public Date getFechaLimite() {
		return this.fechaLimite;
	}
	/**
	 * @param fechaLimite the fechaLimite to set
	 */
	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	/**
	 * @return the horaLimite
	 */
	public String getHoraLimite() {
		return this.horaLimite;
	}
	/**
	 * @param horaLimite the horaLimite to set
	 */
	public void setHoraLimite(String horaLimite) {
		this.horaLimite = horaLimite;
	}
	/**
	 * @return the indSubsanado
	 */
	public String getIndSubsanado() {
		return this.indSubsanado;
	}
	/**
	 * @param indSubsanado the indSubsanado to set
	 */
	public void setIndSubsanado(String indSubsanado) {
		this.indSubsanado = indSubsanado;
	}
	/**
	 * @return the estado
	 */
	public Integer getEstado() {
		return this.estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	/**
	 * @return the estadoDescEu
	 */
	public String getEstadoDescEu() {
		return this.estadoDescEu;
	}
	/**
	 * @param estadoDescEu the estadoDescEu to set
	 */
	public void setEstadoDescEu(String estadoDescEu) {
		this.estadoDescEu = estadoDescEu;
	}
	/**
	 * @return the estadoDescEs
	 */
	public String getEstadoDescEs() {
		return this.estadoDescEs;
	}
	/**
	 * @param estadoDescEs the estadoDescEs to set
	 */
	public void setEstadoDescEs(String estadoDescEs) {
		this.estadoDescEs = estadoDescEs;
	}
	/**
	 * @return the tipoRequerimiento
	 */
	public Long getTipoRequerimiento() {
		return this.tipoRequerimiento;
	}
	/**
	 * @param tipoRequerimiento the tipoRequerimiento to set
	 */
	public void setTipoRequerimiento(Long tipoRequerimiento) {
		this.tipoRequerimiento = tipoRequerimiento;
	}
	/**
	 * @return the tipoRequerimientoDescEu
	 */
	public String getTipoRequerimientoDescEu() {
		return this.tipoRequerimientoDescEu;
	}
	/**
	 * @param tipoRequerimientoDescEu the tipoRequerimientoDescEu to set
	 */
	public void setTipoRequerimientoDescEu(String tipoRequerimientoDescEu) {
		this.tipoRequerimientoDescEu = tipoRequerimientoDescEu;
	}
	/**
	 * @return the tipoRequerimientoDescEs
	 */
	public String getTipoRequerimientoDescEs() {
		return this.tipoRequerimientoDescEs;
	}
	/**
	 * @param tipoRequerimientoDescEs the tipoRequerimientoDescEs to set
	 */
	public void setTipoRequerimientoDescEs(String tipoRequerimientoDescEs) {
		this.tipoRequerimientoDescEs = tipoRequerimientoDescEs;
	}
	/**
	 * @return the difPlazoEntrega
	 */
	public Double getDifPlazoEntrega() {
		return this.difPlazoEntrega;
	}
	/**
	 * @param difPlazoEntrega the difPlazoEntrega to set
	 */
	public void setDifPlazoEntrega(Double difPlazoEntrega) {
		this.difPlazoEntrega = difPlazoEntrega;
	}

}
