package com.ejie.aa79b.model.pdf.content;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.ejie.aa79b.common.Reports;
import com.ejie.aa79b.model.pdf.PdfContent;
import com.ejie.x38.json.JSONArray;

/**
 * @author dlopez2
 *
 */
public class PdfTable extends PdfContent {

	protected LinkedHashMap<String, List<String>> filas = new LinkedHashMap<String, List<String>>();
	protected LinkedHashMap<String, List<String>> aligns = new LinkedHashMap<String, List<String>>();
	protected LinkedHashMap<String, String> labels = new LinkedHashMap<String, String>();
	protected List<String> width = new ArrayList<String>();
	protected List<String> columnasAdicionales = new ArrayList<String>();

	/**
	 * @param key
	 *            String
	 * @param value
	 *            String
	 */
	public void addlabel(String key, String value) {
		this.labels.put(key, (value != null) ? value : "");
	}

	/**
	 * @param columns
	 *            String con las columnas
	 */
	public void addWidth(String columns) {
		JSONArray jsonArr = new JSONArray(columns);
		for (int i = 0; i < jsonArr.length(); ++i) {
			JSONArray column = (JSONArray) jsonArr.get(i);
			this.width.add(String.valueOf(column.get(2)));
		}
	}

	/**
	 * @param posicion
	 *            String con las columnas
	 */
	public void addFila(int posicion) {
		this.filas.put(String.valueOf(posicion), new ArrayList<String>());
		this.aligns.put(String.valueOf(posicion), new ArrayList<String>());
	}

	/**
	 *
	 * The method anadirColumna.
	 *
	 * @param name
	 *            el name de la propiedad
	 * @param width
	 *            el tamaño proporcional de la columna
	 * @param labelEuskera
	 *            el nombre en euskera
	 * @param labelCastellano
	 *            el nombre en castellano
	 */
	public void anadirColumna(String name, String width, String labelEuskera, String labelCastellano) {

		this.width.add(width);
		this.addlabel(labelEuskera, labelCastellano);
		this.columnasAdicionales.add(name);

	}

	/**
	 * @param columns
	 *            String con las columnas
	 * @param resultadoBusqueda
	 *            el resultado de la busqueda
	 * @throws NoSuchMethodException
	 *             e
	 * @throws SecurityException
	 *             e
	 * @throws InvocationTargetException
	 *             e
	 * @throws IllegalAccessException
	 *             e
	 * @throws IllegalArgumentException
	 *             e
	 */
	public void addContenidoTabla(String columns, List<?> resultadoBusqueda) {
		JSONArray jsonArr = new JSONArray(columns);
		String align = "";
		int i = 0;
		for (i = 0; i < jsonArr.length(); ++i) {
			JSONArray column = (JSONArray) jsonArr.get(i);
			String propertie = (String) column.get(0);
			if (column.length() != 3) {
				align = (String) column.get(3);
			} else {
				align = "center";
			}

			int j = 0;
			for (Object object : resultadoBusqueda) {
				if (i == 0) {
					this.addFila(j);
				}
				String contenido = Reports.retrieveObjectValue(object, propertie) == null ? ""
						: Reports.retrieveObjectValue(object, propertie).toString();
				// introducimos el contenido en su posicion, recogiendo antes la
				// lista en cuestion
				this.filas.get(String.valueOf(j)).add(contenido);
				this.aligns.get(String.valueOf(j)).add(align);
				j++;
			}

		}

		// Miramos ahora las columnas adionales

		for (String propertie : this.columnasAdicionales) {

			int j = 0;
			for (Object object : resultadoBusqueda) {
				if (i == 0) {
					this.addFila(j);
				}
				String contenido = Reports.retrieveObjectValue(object, propertie) == null ? ""
						: Reports.retrieveObjectValue(object, propertie).toString();
				// introducimos el contenido en su posicion, recogiendo antes la
				// lista en cuestion
				this.filas.get(String.valueOf(j)).add(contenido);
				j++;
			}
			i++;
		}

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

		final Element datosElement = document.createElement("datos");
		element.appendChild(datosElement);

		// Literales cabeceras tablas
		if (this.width != null && !this.width.isEmpty()) {
			this.anchosTabla(document, datosElement);
		} // Fin if literales

		// Literales cabeceras
		if (this.labels != null && this.labels.size() > 0) {
			this.literalesTabla(document, datosElement, locale.getLanguage());
		} // Fin if literales

		// Datos tabla
		if (!this.filas.isEmpty()) {
			// final Element datos =
			this.procesarContenidoDatosTabla(document, datosElement);

		} else {
			final Element filaPrueba = document.createElement("fila");
			final Element celdaPrueba = document.createElement("celda");
			filaPrueba.appendChild(celdaPrueba);
			datosElement.appendChild(filaPrueba);
		}
	}

	private void procesarContenidoDatosTabla(Document document, Element datosElement) {
		Set<String> keys = this.filas.keySet();
		int i = 0;
		for (String k : keys) {
			final Element filaPrueba = document.createElement("fila");
			filaPrueba.setAttribute("orden", Integer.toString(i + 1));
			final List<String> valores = this.filas.get(k);
			final List<String> alignVals = this.aligns.get(k);
			Element texto = null;
			Element align = null;
			for (int x = 0; x < valores.size(); x++) {
				String string = valores.get(x);
				String alignVal = alignVals.get(x);
				final Element celdaPrueba = document.createElement("celda");
				celdaPrueba.setAttribute("orden", "" + (k + 1));
				texto = document.createElement("texto");
				String strValorCelda = "";
				if (string != null) {
					strValorCelda = string;
				}
				final Text textoCeldaPrueba = document.createTextNode(strValorCelda);
				texto.appendChild(textoCeldaPrueba);
				celdaPrueba.appendChild(texto);
				// metemos la alineación de la columna
				align = document.createElement("align");
				String strAlign = "";
				if (alignVal != null) {
					strAlign = alignVal;
				}
				final Text alignCeldaPrueba = document.createTextNode(strAlign);
				align.appendChild(alignCeldaPrueba);
				celdaPrueba.appendChild(align);

				filaPrueba.appendChild(celdaPrueba);

			}
			datosElement.appendChild(filaPrueba);
		}

	}

	/**
	 * Este metodo sirve para sacar los anchos de la tabla
	 *
	 * @author omartinez
	 * @param document
	 *            el documento del pdf
	 * @param element
	 *            el elemento al que añadir hijos
	 */
	private void anchosTabla(Document document, Element element) {
		final Element zonaAnchos = document.createElement("columnas");
		final Element[] columnas = new Element[this.width.size()];
		final Text[] valoresColumnas = new Text[this.width.size()];
		final Text[] valoresAnchos = new Text[this.width.size()];
		Element texto = null;
		Element ancho = null;
		for (int i = 0; i < this.width.size(); ++i) {
			columnas[i] = document.createElement("columna");
			ancho = document.createElement("ancho");
			texto = document.createElement("texto");
			if (this.width.get(i) != null) {
				valoresColumnas[i] = document.createTextNode(this.width.get(i).toString());
				valoresAnchos[i] = document.createTextNode(this.width.get(i).toString());
			} else {
				valoresColumnas[i] = document.createTextNode("");
				valoresAnchos[i] = document.createTextNode("0");
			}
			texto.appendChild(valoresColumnas[i]);

			columnas[i].appendChild(texto);
			ancho.appendChild(valoresAnchos[i]);
			columnas[i].appendChild(ancho);
			zonaAnchos.appendChild(columnas[i]);
		}

		element.appendChild(zonaAnchos);
	}

	/**
	 * Este metodo sirve para sacar los literales de la tabla segun el idioma
	 *
	 * @author omartinez
	 * @param document
	 *            el documento del pdf
	 * @param idioma
	 *            el idioma que añadir
	 * @param element
	 *            el elemento al que añadir hijos
	 */
	private void literalesTabla(Document document, Element element, String idioma) {
		final Element zonaLiterales = document.createElement("literales");
		final Element[] literales = new Element[this.labels.size()];
		final Text[] valoresLiterales = new Text[this.labels.size()];
		final Text[] valoresAnchos = new Text[this.labels.size()];
		Element texto = null;
		Element ancho = null;
		Set<String> keys = this.labels.keySet();
		int i = 0;
		for (String k : keys) {
			literales[i] = document.createElement("literal");
			ancho = document.createElement("ancho");
			texto = document.createElement("texto");
			if (this.labels.get(k) != null) {
				valoresLiterales[i] = document.createTextNode(this.labels.get(k).toString());
				valoresAnchos[i] = document.createTextNode(this.width.get(i).toString());
			} else {
				valoresLiterales[i] = document.createTextNode("");
				valoresAnchos[i] = document.createTextNode("0");
			}
			texto.appendChild(valoresLiterales[i]);
			literales[i].appendChild(texto);
			ancho.appendChild(valoresAnchos[i]);
			literales[i].appendChild(ancho);
			zonaLiterales.appendChild(literales[i]);
			i++;
		} // Fin for parametros
		element.appendChild(zonaLiterales);
	}

}
