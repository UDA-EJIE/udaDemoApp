package com.ejie.aa79b.service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.FormattingResults;
import org.apache.fop.apps.MimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.ejie.aa79b.common.exceptions.AppRuntimeException;
import com.ejie.aa79b.model.pdf.PdfContent;
import com.ejie.aa79b.model.pdf.PdfModel;
import com.ejie.aa79b.model.pdf.content.PdfCriterios;
import com.ejie.aa79b.model.pdf.content.PdfRuptura;
import com.ejie.aa79b.model.pdf.content.PdfTable;
import com.ejie.aa79b.model.pdf.content.PdfTableJson;
import com.ejie.aa79b.utils.date.DateFormaterFactory;
import com.ejie.x38.json.JSONArray;

/**
 * @author dlopez2
 *
 */
@Service(value = "PdfReportService")
public class PdfReportServiceImpl implements PdfReportService {

	@Autowired()
	private ReloadableResourceBundleMessageSource appMessageSource;

	/**
	 * LOGGER: Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PdfReportServiceImpl.class);

	/**
	 * Este metodo sirve para generar el PDF
	 *
	 * @param templateName
	 *            La plantilla
	 * @param pdfModel
	 *            El modelo del PDF
	 * @param outputStream
	 *            El stream de salida del pdf
	 */
	@Override()
	public void generarPdf(String templateName, PdfModel pdfModel, OutputStream outputStream, Locale locale) {
		try {
			// Step 1: Construct a FopFactory
			final FopFactory fopFactory = FopFactory.newInstance();

			// Step 2: Set up output stream.
			// Note: Parameter from the caller

			// Step 3: Construct fop with desired output format
			final Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, outputStream);

			// Step 4: Setup JAXP using identity transformer
			final TransformerFactory factory = TransformerFactory.newInstance();
			final Transformer transformer = factory.newTransformer();

			// Step 5: Setup input and output for XSLT transformation
			// Setup input stream
			final Reader reader = this.createFopReader(templateName, pdfModel, locale);
			final Source src = new StreamSource(reader);
			// Resulting SAX events (the generated FO) must be piped through to
			// FOP
			final Result res = new SAXResult(fop.getDefaultHandler());

			// Step 6: Start XSLT transformation and FOP processing
			transformer.transform(src, res);

			FormattingResults foResults = fop.getResults();
			PdfReportServiceImpl.LOGGER.info("Generated " + foResults.getPageCount() + " pages in total.");
			PdfReportServiceImpl.LOGGER.info("");
		} catch (Throwable e) {// NOSONAR Necesario cachear throwable //NOPMD
			PdfReportServiceImpl.LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * Este metodo sirve para generar el PDF
	 *
	 * @param templateName
	 *            La plantilla
	 * @param pdfModel
	 *            El modelo del PDF
	 * @return byte[] los bytes del fichero
	 */
	@Override()
	public byte[] generarPdf(String templateName, PdfModel pdfModel, Locale locale) {
		try {
			// Step 1: Construct a FopFactory
			final FopFactory fopFactory = FopFactory.newInstance();

			// Step 2: Set up output stream.
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			// Step 3: Construct fop with desired output format
			final Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

			// Step 4: Setup JAXP using identity transformer
			final TransformerFactory factory = TransformerFactory.newInstance();
			final Transformer transformer = factory.newTransformer();

			// Step 5: Setup input and output for XSLT transformation
			// Setup input stream
			final Reader reader = this.createFopReader(templateName, pdfModel, locale);
			final Source src = new StreamSource(reader);
			// Resulting SAX events (the generated FO) must be piped through to
			// FOP
			final Result res = new SAXResult(fop.getDefaultHandler());

			// Step 6: Start XSLT transformation and FOP processing
			transformer.transform(src, res);

			FormattingResults foResults = fop.getResults();
			PdfReportServiceImpl.LOGGER.info("Generated " + foResults.getPageCount() + " pages in total.");
			PdfReportServiceImpl.LOGGER.info("");

			byte[] content = out.toByteArray();

			out.close();
			System.gc(); // NOSONAR Codigo de uda //NOPMD

			return content;

		} catch (Throwable e) {// NOSONAR Necesario cachear throwable //NOPMD
			PdfReportServiceImpl.LOGGER.error(e.getMessage(), e);
			return new byte[0];
		}
	}

	/**
	 * @param templateName
	 *            String
	 * @param pdfModel
	 *            PdfModel
	 * @return Reader
	 * @throws Exception
	 *             Exception
	 */
	private Reader createFopReader(String templateName, PdfModel pdfModel, Locale locale) throws Exception {
		final TransformerFactory factory = TransformerFactory.newInstance();
		final Source xslStream = new StreamSource(
				PdfReportServiceImpl.class.getResourceAsStream("fop/" + templateName + ".xsl"));
		final Transformer transformer = factory.newTransformer(xslStream);

		final Document document = this.createPdfDocument(pdfModel, locale);
		final Source source = new DOMSource(document);

		final StringWriter writer = new StringWriter();
		final Result result = new StreamResult(writer);

		transformer.transform(source, result);

		final String foString = writer.toString();

		return new StringReader(foString);
	}

	/**
	 * @param pdfModel
	 *            PdfModel
	 * @return Document
	 * @throws Exception
	 *             Exception
	 */
	private Document createPdfDocument(PdfModel pdfModel, Locale locale) throws Exception {
		final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		final Document document = documentBuilder.newDocument();

		// Crear nodo informe
		final Element informeElement = document.createElement("informe");
		document.appendChild(informeElement);

		this.procesarParametros(document, informeElement, pdfModel.getParametros());

		// Crear nodo contenido
		this.procesarContenido(document, informeElement, pdfModel.getContenido(), locale);

		return document;
	}

	/**
	 * era
	 * 
	 * @param document
	 *            Document
	 * @param element
	 *            Element
	 * @param parametros
	 *            List
	 */
	public void procesarParametros(Document document, Element element, Map<String, String> parametros) {
		// Crear nodo parametros
		final Element parametrosElement = document.createElement("parametros");
		element.appendChild(parametrosElement);

		for (String key : parametros.keySet()) {
			final Element keyElement = document.createElement(key);
			parametrosElement.appendChild(keyElement);

			final Text valueText = document.createTextNode(parametros.get(key));
			keyElement.appendChild(valueText);
		}
	}

	/**
	 * @param document
	 *            Document
	 * @param element
	 *            Element
	 * @param contenidos
	 *            List
	 */
	public void procesarContenido(Document document, Element element, List<PdfContent> contenidos, Locale locale) {
		final Element contenidoElement = document.createElement("contenido");
		element.appendChild(contenidoElement);

		for (PdfContent pdfContent : contenidos) {
			pdfContent.procesarContenido(document, contenidoElement, locale);
		}
	}

	/**
	 *
	 * The method getCabeceras.
	 *
	 * @param columns
	 *            columns
	 * @return PdfTable
	 */
	@Override
	public PdfTable obtenerNombreColumnas(String columns, Locale locale) {

		PdfTable pdfTable = new PdfTable();
		JSONArray jsonArr = new JSONArray(columns);

		for (int i = 0; i < jsonArr.length(); ++i) {
			JSONArray column = (JSONArray) jsonArr.get(i);
			String label = (String) column.get(1);
			String[] cabeceras = label.split("#");
			String value = this.appMessageSource.getMessage(cabeceras[0], null, locale);
			pdfTable.addlabel(label, value);
		}

		return pdfTable;
	}

	public PdfTable obtenerNombreColumnasJson(String columns, Locale locale) {

		PdfTable pdfTable = new PdfTableJson();
		JSONArray jsonArr = new JSONArray(columns);

		for (int i = 0; i < jsonArr.length(); ++i) {
			JSONArray column = (JSONArray) jsonArr.get(i);
			String label = (String) column.get(1);
			String[] cabeceras = label.split("#");
			String value = this.appMessageSource.getMessage(cabeceras[0], null, locale);
			pdfTable.addlabel(label, value);
		}

		return pdfTable;
	}

	/**
	 *
	 * The method getCabeceras.
	 *
	 * @param criterios
	 *            los criterios
	 * @return PdfTable
	 */
	@Override
	public PdfCriterios obtenerNombreCriterios(String criterios, Locale locale) {

		PdfCriterios pdfCriterios = new PdfCriterios();
		JSONArray jsonArr = new JSONArray(criterios);

		for (int i = 0; i < jsonArr.length(); ++i) {
			JSONArray criterio = (JSONArray) jsonArr.get(i);
			String label = (String) criterio.get(0);
			String valor = (String) criterio.get(1);

			pdfCriterios.addCriterio(label, this.appMessageSource.getMessage(label, null, locale));
			pdfCriterios.addValor(label, valor);
		}

		return pdfCriterios;
	}

	@Override()
	public PdfModel getPdfModel(String criterios, String columnas, List<?> resultadoBusqueda, Locale locale,
			String titulo) {
		return this.getPdfModel(criterios, columnas, resultadoBusqueda, titulo, locale, false);
	}

	@Override()
	public PdfModel getPdfModelJson(String criterios, String columnas, List<?> resultadoBusqueda, Locale locale,
			String titulo) {
		return this.getPdfModel(criterios, columnas, resultadoBusqueda, titulo, locale, true);
	}

	private PdfModel getPdfModel(String criterios, String columnas, List<?> resultadoBusqueda, String titulo,
			Locale locale, Boolean json) {
		try {
			final PdfModel pdfModel = new PdfModel();

			// Parametros
			pdfModel.addParametro("imagen",
					PdfReportServiceImpl.class.getResource("fop/logo-IVAP_nuevo.jpg").toString());
			pdfModel.addParametro("titulo", this.appMessageSource.getMessage(titulo, new Object[] {}, locale));
			pdfModel.addParametro("fecha",
					DateFormaterFactory.getInstance().getFechaCortaDateFormater(locale).parse(new Date()));
			pdfModel.addParametro("fechaLabel",
					this.appMessageSource.getMessage("label.fecha", new Object[] {}, locale));
			pdfModel.addParametro("pagLabel",
					this.appMessageSource.getMessage("label.pagina", new Object[] {}, locale));

			// Contenido
			PdfCriterios pdfCriterios = new PdfCriterios();
			if (StringUtils.isNotBlank(criterios)) {
				pdfCriterios = this.obtenerNombreCriterios(criterios, locale);
			}

			// Tabla
			final PdfTable pdfTable = json ? this.obtenerNombreColumnasJson(columnas, locale)
					: this.obtenerNombreColumnas(columnas, locale);

			pdfTable.addWidth(columnas);

			pdfTable.addContenidoTabla(columnas, resultadoBusqueda);

			pdfModel.addContenido(pdfTable);
			pdfModel.addContenido(new PdfRuptura());
			if (StringUtils.isNotBlank(criterios)) {
				pdfModel.addContenido(pdfCriterios);
			}

			return pdfModel;
		} catch (Exception e) {
			throw new AppRuntimeException(e);
		}
	}

	@Override
	public void generarPdf(String template, HttpServletResponse response, String criterios, String columnas,
			List<?> resultadoBusqueda, String titulo, Locale locale, String fichero) {
		PdfModel pdfModel = this.getPdfModel(criterios, columnas, resultadoBusqueda, titulo, locale, false);
		this.generarPdf(template, pdfModel, response, fichero, locale);
	}

	@Override
	public void generarPdfJson(String template, HttpServletResponse response, String criterios, String columnas,
			List<?> resultadoBusqueda, String titulo, Locale locale, String fichero) {
		PdfModel pdfModel = this.getPdfModel(criterios, columnas, resultadoBusqueda, titulo, locale, true);
		this.generarPdf(template, pdfModel, response, fichero, locale);
	}

	private void generarPdf(String templateName, PdfModel pdfModel, HttpServletResponse response, String fichero,
			Locale locale) {
		try {
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "attachment;filename=" + fichero + ".pdf");

			Cookie cookie = new Cookie("fileDownload", "true");
			cookie.setPath("/");
			response.addCookie(cookie);

			this.generarPdf("defaultPDF", pdfModel, response.getOutputStream(), locale);
		} catch (Exception e) {
			throw new AppRuntimeException(e);
		}
	}

}
