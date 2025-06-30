package com.ejie.aa79b.model.pdf.content;

import java.util.Locale;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ejie.aa79b.model.pdf.PdfContent;

/**
 * @author omartinez
 * 
 */
public class PdfRuptura extends PdfContent {

	/**
	 * Este es un metodo sobreescrito.
	 * 
	 * @see com.ejie.PdfContent.model.pdf.PdfContent#procesarContenido(org.w3c.dom.Document,
	 *      org.w3c.dom.Element)
	 * @param document
	 *            Document
	 * @param element
	 *            Element
	 */
	@Override()
	public void procesarContenido(Document document, Element element, Locale locale) {
		final Element criteriosElement = document.createElement("ruptura");
		element.appendChild(criteriosElement);
	}

}
