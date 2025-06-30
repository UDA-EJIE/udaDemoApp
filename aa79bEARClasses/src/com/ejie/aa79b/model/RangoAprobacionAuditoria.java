package com.ejie.aa79b.model;

public class RangoAprobacionAuditoria implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private int valMinAprobado;
	private int valMinPeligro;

	public RangoAprobacionAuditoria() {
		super();
	}

	public RangoAprobacionAuditoria(Long id) {
		super();
		this.id = id;
	}

	public RangoAprobacionAuditoria(Long id, int valMinAprobado, int valMinPeligro) {
		super();
		this.id = id;
		this.valMinAprobado = valMinAprobado;
		this.valMinPeligro = valMinPeligro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getValMinAprobado() {
		return valMinAprobado;
	}

	public void setValMinAprobado(int valMinAprobado) {
		this.valMinAprobado = valMinAprobado;
	}

	public int getValMinPeligro() {
		return valMinPeligro;
	}

	public void setValMinPeligro(int valMinPeligro) {
		this.valMinPeligro = valMinPeligro;
	}

	@Override
	public String toString() {
		return "RangoAprobAuditoria [id=" + id + ", valMinAprobado=" + valMinAprobado + ", valMinPeligro="
				+ valMinPeligro + "]";
	}
}
