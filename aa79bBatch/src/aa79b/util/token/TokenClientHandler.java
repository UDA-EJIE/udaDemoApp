package aa79b.util.token;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import aa79b.util.common.Logger;

/**
 * @author dlopez2
 *
 */
public class TokenClientHandler implements SOAPHandler<SOAPMessageContext> {

	private final TransformerFactory transformerFactory;
	private final String rutaWsToken;

	/**
	 * Constructor para Aa06aTokenClientHandler.
	 */
	public TokenClientHandler(String rutaWsToken) {
		this.transformerFactory = TransformerFactory.newInstance();
		this.rutaWsToken = rutaWsToken;
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
		final Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		Logger.batchlog(Logger.INFO, "outbound " + outbound);

		// solo para los mensajes SALIENTES
		if (outbound != null && outbound.booleanValue()) {
			valid = false;

			//Logger.batchlog(Logger.INFO, "CONTEXT: " + soap2string(context));

			try {
				Logger.batchlog(Logger.INFO, "Se recoge el token ");
				final Document token = this.recogerToken();
				//Logger.batchlog(Logger.INFO, "Se recoge el SoapHeader ");
				// recuperar cabecera SOAP
				final SOAPMessage soapMsg = context.getMessage();
				final SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
				SOAPHeader soapHeader = soapEnv.getHeader();

				//if no header, add one
				if (soapHeader == null){
					soapHeader = soapEnv.addHeader();
				}

				//Logger.batchlog(Logger.INFO, "Finalizado el soapHeader ");

				// añadimos bloque pidns a la cabecera
				final SOAPHeaderElement subBloqueHeader = soapHeader
						.addHeaderElement(new QName("com/ejie/documents/xml", "W43sIdentity", "pidns"));

				// incluir token en la cabecera creada
				final Source source = new DOMSource(token);
				final Result result = new DOMResult(subBloqueHeader);
				//Logger.batchlog(Logger.INFO, "Transformer ");
				this.transformerFactory.newTransformer().transform(source, result);
				// se puede continuar el procesamiento
				valid = true;
				// Registra salientes
				//Logger.batchlog(Logger.INFO, "registerOutbound ");
				this.registerOutbound(context);
			} catch (final Exception e) {
				Logger.batchlog(Logger.INFO, "Error recuperando el token de la cabecera " + e);
			}
		} else {
			// Registra entrantes
			this.registerInbound(context);
		}

		return valid;
	}

	/**
	 * Metodo para recoger el token de xlnets
	 *
	 * @author vdorantes
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws TransformerException  TransformerException
	 * @return el token
	 */
	private Document recogerToken() throws Exception{
		try {
			final URL url = new URL(this.rutaWsToken);
			final URLConnection con= url.openConnection();
			final InputStream in = con.getInputStream();
			String encoding = con.getContentEncoding();
			encoding = encoding == null ? "UTF-8" : encoding;
			String token = IOUtils.toString(in, encoding);
			token = token.replace("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>", "");
			//Logger.batchlog(Logger.INFO, "TokenClientHandler. recogerToken() token: ", token);
			final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = docFactory.newDocumentBuilder();
			return builder.parse(new InputSource(new StringReader(token)));
		} catch (final Exception e) {
			Logger.batchlog(Logger.INFO, "TokenClientHandler. recogerToken() MalformedURLException ", e);
			throw e;
		}
	}

	/**
	 * Este metodo sirve para realizar el registro del mensaje saliente
	 *
	 * @param context
	 *            El contexto del mensaje
	 */
	private void registerOutbound(SOAPMessageContext context) {
		// Obtenemos los valores a registrar
		final String accion = (String) context.get("WL_OP_NAME");
		final String peticion = this.soap2string(context);

		//Logger.batchlog(Logger.INFO, "REQUEST WL_OP_NAME " + accion);
		//Logger.batchlog(Logger.INFO, peticion);
	}

	/**
	 * Este metodo sirve para realizar el registro del mensaje entrante
	 *
	 * @param context
	 *            El contexto del mensaje
	 */
	private void registerInbound(SOAPMessageContext context) {
		// Obtenemos los valores a registrar
		final String accion = (String) context.get("WL_OP_NAME");
		final String respuesta = this.soap2string(context);

		//Logger.batchlog(Logger.INFO, "RESPONSE WL_OP_NAME " + accion);
		//Logger.batchlog(Logger.INFO, respuesta);
	}

	/**
	 * Este metodo sirve para realizar el registro del fallos
	 *
	 * @param context
	 *            El contexto del mensaje
	 */
	private void registerFault(SOAPMessageContext context) {
		// Obtenemos los valores a registrar
		final String accion = (String) context.get("WL_OP_NAME");
		final String respuesta = this.soap2string(context);

		// Fijar valores a registrar
		//Logger.batchlog(Logger.INFO, "FAULT RESPONSE WL_OP_NAME " + accion);
		//Logger.batchlog(Logger.INFO, respuesta);
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
		} catch (final Exception e) {
			Logger.batchlog(Logger.INFO, "[ERROR] parseando el mensaje SOAP: " + e);
			return "Error parseando el mensaje SOAP: " + e.toString();
		}
	}
}
