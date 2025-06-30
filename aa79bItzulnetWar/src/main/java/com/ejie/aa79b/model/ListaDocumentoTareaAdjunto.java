package com.ejie.aa79b.model;

import java.util.List;

public class ListaDocumentoTareaAdjunto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private List<DocumentoTareaAdjunto> listaDocumentoTareaAdjunto;

	public ListaDocumentoTareaAdjunto() {
		// Constructor vacio
	}

	/**
	 * @return the listaDocumentoTareaAdjunto
	 */
	public List<DocumentoTareaAdjunto> getListaDocumentoTareaAdjunto() {
		return listaDocumentoTareaAdjunto;
	}

	/**
	 * @param listaDocumentoTareaAdjunto
	 *            the listaDocumentoTareaAdjunto to set
	 */
	public void setListaDocumentoTareaAdjunto(List<DocumentoTareaAdjunto> listaDocumentoTareaAdjunto) {
		this.listaDocumentoTareaAdjunto = listaDocumentoTareaAdjunto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ListaDocumentoTareaAdjunto [listaDocumentoTareaAdjunto=" + listaDocumentoTareaAdjunto + "]";
	}

}
