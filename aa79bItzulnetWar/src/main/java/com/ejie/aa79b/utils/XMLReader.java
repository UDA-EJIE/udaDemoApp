package com.ejie.aa79b.utils;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.ejie.aa79b.common.exceptions.AppRuntimeException;
import com.ejie.x38.util.StackTraceManager;

/**
 * @author dlopez2
 * 
 */
public final class XMLReader {
	/**
	 * LOGGER: Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(XMLReader.class);

	/**
	 * XPATH_FACTORY: XPathFactory.
	 */
	private static XPathFactory XPATH_FACTORY = XPathFactory.newInstance();
	/**
	 * XPATH: XPath.
	 */
	private static XPath XPATH = XMLReader.XPATH_FACTORY.newXPath();

	/**
	 * Este metodo devuelve una instancia a partir del string de un XML.
	 * 
	 * @param xmlString
	 *            El XML en string
	 * @return Una instancia
	 */
	public static XMLReader getInstance(String xmlString) {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			final InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(xmlString));

			Document document = documentBuilder.parse(inputSource);

			return new XMLReader(document);
		} catch (Exception e) {
			XMLReader.LOGGER.error(StackTraceManager.getStackTrace(e));
			throw new AppRuntimeException(e);
		}
	}

	/**
	 * document: Document.
	 */
	private Document document = null;

	/**
	 * Constructor para Aa06aXMLReader.
	 * 
	 * @param document
	 *            El DOM del XML a ser leido
	 */
	public XMLReader(Document document) {
		this.document = document;
	}

	/**
	 * Este metodo sirve para evaluar una expresion de xpath sobre el documento.
	 * 
	 * @param expression
	 *            La expression de xpath
	 * @return El resultado
	 */
	public String evaluate(String expression) {
		try {
			final XPathExpression xPathExpression = XMLReader.XPATH.compile(expression);
			return xPathExpression.evaluate(this.document);
		} catch (Exception e) {
			LOGGER.info("Error al parsear XML", e);
			throw new AppRuntimeException(e);
		}
	}

}
