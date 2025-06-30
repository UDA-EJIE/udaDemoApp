package com.ejie.aa79b.webservices;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPHeader;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.xpath.XPathAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.ejie.aa79b.common.exceptions.AppRuntimeException;

import n38c.exe.N38API;

/**
 * 
 * The type .
 * 
 * @author omartinez
 *
 */
public class N38TokenServerHandler implements SOAPHandler<SOAPMessageContext> {
	private String tokenN38XQuery = "//n38";
	private XPathExpression expression;
	private TransformerFactory transformerFactory;

	private static String FUNCION_WS_EVALUADORES = "AA79B-FN-0002";
	protected String getFuncion() {
		return FUNCION_WS_EVALUADORES;
	}
	
	
	public static final String PATH_CHECK_CORRECTO = "/n38/elementos/elemento/parametro[@id=\"cn\"]/valor/text()";

	private static final Logger LOGGER = LoggerFactory.getLogger(N38TokenServerHandler.class);

	/**
	 * Constructor por defecto.
	 * 
	 * @throws XPathExpressionException
	 *             exception
	 */
	public N38TokenServerHandler() throws XPathExpressionException {
		// inicializacion de la expresión XPATH
		XPathFactory xpFactory = XPathFactory.newInstance();
		XPath xpath = xpFactory.newXPath();
		this.expression = xpath.compile(this.tokenN38XQuery);
		this.transformerFactory = TransformerFactory.newInstance();
	}

	/**
	 * @return Set<Qname>
	 */
	@Override
	public Set<QName> getHeaders() {
		return new HashSet<QName>();
	}

	/**
	 * @param context
	 *            el contexto
	 */
	@Override
	public void close(MessageContext context) {
		// Nada que hacer
	}

	/**
	 * @see javax.xml.ws.handler.Handler#handleFault(javax.xml.ws.handler.MessageContext)
	 * @return boolean
	 * @param context
	 *            el contexto
	 */
	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return false;
	}

	/**
	 * @see javax.xml.ws.handler.Handler#handleMessage(javax.xml.ws.handler.MessageContext)
	 * @return boolean
	 * @param context
	 *            el contexto
	 */
	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		boolean valid = true;
		Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		// solo para los mensajes entrantes
		if (outbound != null && !outbound.booleanValue()) {
			valid = false;
			try {
				String tokenN38 = this.doExtractToken(context.getMessage().getSOAPHeader());
				// Leer header mensaje SOAP
				SOAPHeader soapHeader = context.getMessage().getSOAPHeader();
				final String xmlSOAPHeader = soapHeader.toString();

				if (soapHeader == null || xmlSOAPHeader == null) {
					throw new AppRuntimeException("La petición XML al WebService no contiene cabecera");
				} else if ("".equals(xmlSOAPHeader.trim())) {
					throw new AppRuntimeException("La petición XML al WebService tiene la cabecera vacía");
				} else {
					valid = comprobarToken(valid, tokenN38);
				}
			} catch (Exception e) {
				N38TokenServerHandler.LOGGER.info("Excepción en el método handleMessage: " + e);
				throw new AppRuntimeException(e.getMessage());
			}
		}
		return valid;
	}

	/**
	 * @param valid
	 * @param tokenN38
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException
	 */
	private boolean comprobarToken(boolean valid, String tokenN38)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {
		boolean esValido = valid;
		if ((tokenN38 != null) && (!"".equals(tokenN38))) {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document documentoN38 = builder.parse(new InputSource(new StringReader(tokenN38)));

			final N38API n38api = new N38API(documentoN38);

			String funcion = this.getFuncion();
			Document documentoAutorizacion = n38api.n38ItemObtenerAutorizacion(funcion);

			// Comprobamos que el resultado es correcto
			NodeList nodeLi = XPathAPI.selectNodeList(documentoAutorizacion, PATH_CHECK_CORRECTO);

			for (int i = 0; i < nodeLi.getLength(); i++) {
				if (funcion.equals(nodeLi.item(i).getNodeValue())) {
					esValido = true;
				}
			}
		} else {
			throw new AppRuntimeException("La petición XML al WebService tiene la cabecera vacía");
		}
		return esValido;
	}

	/**
	 * Extrae el xml correspondiente a la sesión de aplicación.
	 * 
	 * @param node
	 *            node
	 * @return String
	 * @throws XPathExpressionException
	 *             exception
	 * @throws TransformerException
	 *             exception
	 */
	private String doExtractToken(Node node) throws XPathExpressionException, TransformerException {
		String tokenN38 = "";
		// busqueda XPath del nodo n38
		Node tokenN38Node = (Node) this.expression.evaluate(node, XPathConstants.NODE);
		if (tokenN38Node != null) {
			// convertimos a String el nodo del token
			Source tokenN38NodeSource = new DOMSource(tokenN38Node);
			StringWriter writer = new StringWriter();
			Result result = new StreamResult(writer);
			this.transformerFactory.newTransformer().transform(tokenN38NodeSource, result);
			tokenN38 = writer.toString();
		}
		return tokenN38;
	}
}