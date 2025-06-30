package com.ejie.aa79b.model.pdf;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dlopez2
 * 
 */
public final class PdfModel {
	private Map<String, String> parametros = new LinkedHashMap<String, String>();
	private List<PdfContent> contenido = new ArrayList<PdfContent>();

	/**
	 * @return String
	 */
	public Map<String, String> getParametros() {
		return this.parametros;
	}

	/**
	 * @param key
	 *            String
	 * @param value
	 *            String
	 */
	public void addParametro(String key, String value) {
		this.parametros.put(key, (value != null) ? value : "");
	}

	/**
	 * @param key
	 *            String
	 */
	public void removeParametro(String key) {
		this.parametros.remove(key);
	}

	/**
	 * @return String
	 */
	public List<PdfContent> getContenido() {
		return this.contenido;
	}

	/**
	 * @param pdfContent
	 *            String
	 */
	public void addContenido(PdfContent pdfContent) {
		this.contenido.add(pdfContent);
	}

	/**
	 * @param index
	 *            String
	 */
	public void removeContenido(int index) {
		this.contenido.remove(index);
	}
}
