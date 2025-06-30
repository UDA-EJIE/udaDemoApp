package com.ejie.aa79b.model.pdf.content;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.ejie.aa79b.model.pdf.PdfContent;

/**
 * @author dlopez2
 *
 */
public class PdfCriterios extends PdfContent {
	private Map<String, String> criterios = new LinkedHashMap<String, String>();

	private Map<String, String> criteriosEu = new LinkedHashMap<String, String>();

	private Map<String, String> criteriosEs = new LinkedHashMap<String, String>();

	private Map<String, String> valores = new LinkedHashMap<String, String>();

	/**
	 * @param key
	 *            String
	 * @param value
	 *            String
	 */
	public void addCriterio(String key, String value) {
		this.criterios.put(key, (value != null) ? value : "");
	}

	/**
	 * @param key
	 *            String
	 * @param value
	 *            String
	 */
	public void addCriterioEs(String key, String value) {
		this.criteriosEs.put(key, (value != null) ? value : "");
	}

	/**
	 * @param key
	 *            String
	 * @param value
	 *            String
	 */
	public void addCriterioEu(String key, String value) {
		this.criteriosEu.put(key, (value != null) ? value : "");
	}

	/**
	 * @param key
	 *            String
	 */
	public void deleteCriterioEu(String key) {
		this.criteriosEu.remove(key);
	}

	/**
	 * @param key
	 *            String
	 * @param value
	 *            String
	 */
	public void addValor(String key, String value) {
		this.valores.put(key, (value != null) ? value : "");
	}

	/**
	 *
	 * The method anadirCriterio.
	 *
	 * @param propertie
	 *            el valor de la propertie
	 * @param euskera
	 *            el valor en euskera
	 * @param castellano
	 *            el valor en castellano
	 * @param valor
	 *            el valor
	 */
	public void anadirCriterio(String propertie, String euskera, String castellano, String valor) {
		this.criteriosEu.put(propertie, (euskera != null) ? euskera : "");
		this.criteriosEs.put(propertie, (castellano != null) ? castellano : "");
		this.valores.put(propertie, (valor != null) ? valor : "");
	}

	/**
	 *
	 * The method borrarCriterio.
	 *
	 * @param property
	 *            el valor de la property
	 */
	public void borrarCriterio(String property) {
		this.criteriosEu.remove(property);
		this.criteriosEs.remove(property);
		this.valores.remove(property);
	}

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
		final Element criteriosElement = document.createElement("criterios");
		element.appendChild(criteriosElement);

		int index = 0;
		Element filaCriteriosElement = null;

		for (String key : this.criterios.keySet()) {
			final String value = this.criterios.get(key);
			final String valor = this.valores.get(key);

			if (index % 2 == 0) {
				filaCriteriosElement = document.createElement("filaCriterios");
				criteriosElement.appendChild(filaCriteriosElement);

				// Texto_EU
				final Element keyIzqElement = document.createElement("keyIzq");
				filaCriteriosElement.appendChild(keyIzqElement);
				final Text valueTituloIzqText = document.createTextNode(value);
				keyIzqElement.appendChild(valueTituloIzqText);

				// Valor
				final Element valueIzqElement = document.createElement("valueIzq");
				filaCriteriosElement.appendChild(valueIzqElement);
				final Text valueIzqText = document.createTextNode(valor);
				valueIzqElement.appendChild(valueIzqText);
			} else {
				// Texto_EU
				final Element keyDerElement = document.createElement("keyDer");
				filaCriteriosElement.appendChild(keyDerElement);
				final Text valueTituloDerText = document.createTextNode(value);
				keyDerElement.appendChild(valueTituloDerText);

				// Valor
				final Element valueDerElement = document.createElement("valueDer");
				filaCriteriosElement.appendChild(valueDerElement);
				final Text valueDerText = document.createTextNode(valor);
				valueDerElement.appendChild(valueDerText);
			}

			index++;
		}
	}

}
