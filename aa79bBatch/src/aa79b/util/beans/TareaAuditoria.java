package aa79b.util.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class TareaAuditoria extends Tarea implements Serializable {

	private static final long serialVersionUID = 1L;
	private Persona auditor;
	private BigDecimal idTareaAuditar;
	private Lotes lote;
	private BigDecimal idAuditoria;
	private Integer estadoAuditoria;
	private String tituloExp;

	/**
	 * @return the auditor
	 */
	public Persona getAuditor() {
		return this.auditor;
	}
	/**
	 * @param auditor the auditor to set
	 */
	public void setAuditor(Persona auditor) {
		this.auditor = auditor;
	}
	/**
	 * @return the idTareaAuditar
	 */
	public BigDecimal getIdTareaAuditar() {
		return this.idTareaAuditar;
	}
	/**
	 * @param idTareaAuditar the idTareaAuditar to set
	 */
	public void setIdTareaAuditar(BigDecimal idTareaAuditar) {
		this.idTareaAuditar = idTareaAuditar;
	}
	/**
	 * @return the lote
	 */
	public Lotes getLote() {
		return this.lote;
	}
	/**
	 * @param lote the lote to set
	 */
	public void setLote(Lotes lote) {
		this.lote = lote;
	}
	/**
	 * @return the idAuditoria
	 */
	public BigDecimal getIdAuditoria() {
		return this.idAuditoria;
	}
	/**
	 * @param idAuditoria the idAuditoria to set
	 */
	public void setIdAuditoria(BigDecimal idAuditoria) {
		this.idAuditoria = idAuditoria;
	}
	/**
	 * @return the estadoAuditoria
	 */
	public Integer getEstadoAuditoria() {
		return this.estadoAuditoria;
	}
	/**
	 * @param estadoAuditoria the estadoAuditoria to set
	 */
	public void setEstadoAuditoria(Integer estadoAuditoria) {
		this.estadoAuditoria = estadoAuditoria;
	}
	/**
	 * @return the tituloExp
	 */
	public String getTituloExp() {
		return this.tituloExp;
	}
	/**
	 * @param tituloExp the tituloExp to set
	 */
	public void setTituloExp(String tituloExp) {
		this.tituloExp = tituloExp;
	}

}
