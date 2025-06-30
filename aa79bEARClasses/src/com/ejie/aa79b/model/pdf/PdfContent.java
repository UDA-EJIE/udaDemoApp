package com.ejie.aa79b.model.pdf;

import java.util.Locale;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author dlopez2
 * 
 */
public abstract class PdfContent {
	/**
	 * @param document
	 *            Document
	 * @param element
	 *            Element
	 */
	public abstract void procesarContenido(Document document, Element element, Locale locale);
}
