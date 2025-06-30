package com.ejie.aa79b.model;

import java.util.List;

/**
 * EntradaGestoresRepresentante
 * @author mrodriguez
 */
public class DatosConsultaExp extends DatosSalidaWS implements java.io.Serializable {
	
	private static final long serialVersionUID = 6914946314137644266L;
	private List<Gestor> gestores; 
	private List<String> titulosExpediente;
	private Long countGestorExpPdteTramCliente;
	
	
	public List<Gestor> getGestores() {
		return this.gestores;
	}
	public void setGestores(List<Gestor> gestores) {
		this.gestores = gestores;
	}
	public List<String> getTitulosExpediente() {
		return this.titulosExpediente;
	}
	public void setTitulosExpediente(List<String> titulosExpediente) {
		this.titulosExpediente = titulosExpediente;
	}
	public Long getCountGestorExpPdteTramCliente() {
		return this.countGestorExpPdteTramCliente;
	}
	public void setCountGestorExpPdteTramCliente(Long countGestorExpPdteTramCliente) {
		this.countGestorExpPdteTramCliente = countGestorExpPdteTramCliente;
	}
	
	
	@Override
	public String toString() {
		return "DatosConsultaExp [gestores=" + this.gestores + ", titulosExpediente=" + this.titulosExpediente
				+ ", countGestorExpPdteTramCliente=" + this.countGestorExpPdteTramCliente + "]";
	}

	
}
