package com.ejie.aa79b.model;

import java.util.List;

/**
 * EntradaGestoresRepresentante
 * 
 * @author mrodriguez
 */
public class SalidaObtenerDetalleSub extends DatosSalidaWS implements java.io.Serializable {

	private static final long serialVersionUID = 6914946314137644266L;
	private String detalle;
	private Long fechaLimite;
	private List<CamposSelecSub> camposSelecSub;
	private List<DocusSelecSub> docusSelecSub;
	private String indDocNuevos;

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Long getFechaLimite() {
		return this.fechaLimite;
	}

	public void setFechaLimite(Long fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public List<CamposSelecSub> getCamposSelecSub() {
		return this.camposSelecSub;
	}

	public void setCamposSelecSub(List<CamposSelecSub> camposSelecSub) {
		this.camposSelecSub = camposSelecSub;
	}

	public List<DocusSelecSub> getDocusSelecSub() {
		return this.docusSelecSub;
	}

	public void setDocusSelecSub(List<DocusSelecSub> docusSelecSub) {
		this.docusSelecSub = docusSelecSub;
	}

	@Override
	public String toString() {
		return "SalidaObtenerDetalleSub [detalle=" + this.detalle + ", fechaLimite=" + this.fechaLimite
				+ ", camposSelecSub=" + this.camposSelecSub + ", docusSelecSub=" + this.docusSelecSub + "]";
	}

	/**
	 * @return the indDocNuevos
	 */
	public String getIndDocNuevos() {
		return indDocNuevos;
	}

	/**
	 * @param indDocNuevos
	 *            the indDocNuevos to set
	 */
	public void setIndDocNuevos(String indDocNuevos) {
		this.indDocNuevos = indDocNuevos;
	}

}
