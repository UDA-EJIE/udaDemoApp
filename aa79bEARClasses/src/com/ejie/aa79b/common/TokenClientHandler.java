package com.ejie.aa79b.common;

import java.io.ByteArrayOutputStream;
import java.util.HashSet;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import com.ejie.x38.util.StaticsContainer;

import n38a.exe.N38APISesion;

/**
 * @author dlopez2
 * 
 */
public class TokenClientHandler implements SOAPHandler<SOAPMessageContext> {

	private static final Logger LOGGER = LoggerFactory.getLogger(TokenClientHandler.class);

	private final TransformerFactory transformerFactory;
	private static final String WL_OP_NAME = "WL_OP_NAME";

	/**
	 * Constructor para Aa06aTokenClientHandler.
	 */
	public TokenClientHandler() {
		this.transformerFactory = TransformerFactory.newInstance();
	}

	/**
	 * Este es un metodo sobreescrito.
	 * 
	 * @see javax.xml.ws.handler.soap.SOAPHandler#getHeaders()
	 * @return Set<QName>
	 */
	@Override
	public Set<QName> getHeaders() {
		return new HashSet<QName>();
	}

	/**
	 * Este es un metodo sobreescrito.
	 * 
	 * @see javax.xml.ws.handler.Handler#close(javax.xml.ws.handler.MessageContext)
	 * @param context
	 *            MessageContext
	 */
	@Override
	public void close(MessageContext context) {
		// Nada que hacer
	}

	/**
	 * Este es un metodo sobreescrito.
	 * 
	 * @see javax.xml.ws.handler.Handler#handleFault(javax.xml.ws.handler.MessageContext)
	 * @param context
	 *            SOAPMessageContext
	 * @return boolean
	 */
	@Override
	public boolean handleFault(SOAPMessageContext context) {
		this.registerFault(context);
		return true;
	}

	/**
	 * Este es un metodo sobreescrito.
	 * 
	 * @see javax.xml.ws.handler.Handler#handleMessage(javax.xml.ws.handler.MessageContext)
	 * @param context
	 *            SOAPMessageContext
	 * @return boolean
	 */
	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		boolean valid = true;
		Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		// solo para los mensajes SALIENTES
		if (outbound != null && outbound.booleanValue()) {
			valid = false;

			String codApp = StaticsContainer.webAppName;

			try {
				// recuperar codigo de aplicacion de configuracion
				if (context.containsKey("codApp")) {
					codApp = (String) context.get("codApp");
				}

				String codigoAplicacion = codApp;

				// crear token n38 de aplicacion
				N38APISesion n38Sesion = new N38APISesion();

				Document token = n38Sesion.n38APISesionCrearApp(codigoAplicacion);
				// recuperar cabecera SOAP
				SOAPHeader soapHeader = context.getMessage().getSOAPHeader();

				// añadimos bloque pidns a la cabecera
				SOAPHeaderElement subBloqueHeader = soapHeader
						.addHeaderElement(new QName("com/ejie/documents/xml", "W43sIdentity", "pidns"));

				// incluir token en la cabecera creada
				Source source = new DOMSource(token);
				Result result = new DOMResult(subBloqueHeader);

				this.transformerFactory.newTransformer().transform(source, result);
				// se puede continuar el procesamiento
				valid = true;
				// Registra salientes
				this.registerOutbound(context);
			} catch (Exception e) {
				TokenClientHandler.LOGGER.info("Error recuperando el token de la cabecera " + codApp + e);
			}
		} else {
			// Registra entrantes
			this.registerInbound(context);
		}

		return valid;
	}

	/**
	 * Este metodo sirve para realizar el registro del mensaje saliente
	 * 
	 * @param context
	 *            El contexto del mensaje
	 */
	private void registerOutbound(SOAPMessageContext context) {
		// Obtenemos los valores a registrar
		final String accion = (String) context.get(WL_OP_NAME);
		final String peticion = this.soap2string(context);

		TokenClientHandler.LOGGER.info("REQUEST WL_OP_NAME " + accion);
		TokenClientHandler.LOGGER.info(peticion);
	}

	/**
	 * Este metodo sirve para realizar el registro del mensaje entrante
	 * 
	 * @param context
	 *            El contexto del mensaje
	 */
	private void registerInbound(SOAPMessageContext context) {
		// Obtenemos los valores a registrar
		final String accion = (String) context.get(WL_OP_NAME);
		final String respuesta = this.soap2string(context);

		TokenClientHandler.LOGGER.info("RESPONSE WL_OP_NAME " + accion);
		TokenClientHandler.LOGGER.info(respuesta);
	}

	/**
	 * Este metodo sirve para realizar el registro del fallos
	 * 
	 * @param context
	 *            El contexto del mensaje
	 */
	private void registerFault(SOAPMessageContext context) {
		// Obtenemos los valores a registrar
		final String accion = (String) context.get(WL_OP_NAME);
		final String respuesta = this.soap2string(context);

		// Fijar valores a registrar
		TokenClientHandler.LOGGER.info("FAULT RESPONSE WL_OP_NAME " + accion);
		TokenClientHandler.LOGGER.info(respuesta);
	}

	/**
	 * The method soap2string.
	 * 
	 * @param context
	 *            SOAPMessageContext
	 * @return String
	 */
	private String soap2string(SOAPMessageContext context) {
		try {
			final SOAPMessage message = context.getMessage();
			final ByteArrayOutputStream out = new ByteArrayOutputStream();

			message.writeTo(out);

			return new String(out.toByteArray());
		} catch (Exception e) {
			TokenClientHandler.LOGGER.info("[ERROR] parseando el mensaje SOAP: " + e);
			return "Error parseando el mensaje SOAP: " + e.toString();
		}
	}
}
