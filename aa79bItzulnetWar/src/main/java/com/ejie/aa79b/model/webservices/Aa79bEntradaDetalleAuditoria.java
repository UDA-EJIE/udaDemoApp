package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ejie.aa79b.model.IdiomaWS;

/**
 * Aa79bEntradaDetalleAuditoria
 * 
 * @author xtotorika
 */
public class Aa79bEntradaDetalleAuditoria extends IdiomaWS implements Serializable {

	private static final long serialVersionUID = -3695012946036974390L;
	private Long anyo;
	private Integer numExp;
	private BigDecimal idTarea;
	private BigDecimal idTareaAuditar;

	public Long getAnyo() {
		return anyo;
	}

	public void setAnyo(Long anyo) {
		this.anyo = anyo;
	}

	public Integer getNumExp() {
		return numExp;
	}

	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}

	public BigDecimal getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(BigDecimal idTarea) {
		this.idTarea = idTarea;
	}

	public BigDecimal getIdTareaAuditar() {
		return idTareaAuditar;
	}

	public void setIdTareaAuditar(BigDecimal idTareaAuditar) {
		this.idTareaAuditar = idTareaAuditar;
	}

}
