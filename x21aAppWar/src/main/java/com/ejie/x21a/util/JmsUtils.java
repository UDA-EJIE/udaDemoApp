/**
 * 
 */
package com.ejie.x21a.util;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que encapsula la logica de escritura en colas JMS
 * 
 * @author Jorge Hidalgo, GFI-NORTE (2013)
 * 
 */
public class JmsUtils {
	private static final Logger logger = LoggerFactory
			.getLogger(JmsUtils.class);

	/**
	 * Tipos de mensajes para encolar
	 */
	public static enum MESSAGE_TYPE {
		TEXT_MESSAGE, OBJECT_MESSAGE, MAP_MESSAGE
	};

	/**
	 * Constructor privado
	 */
	private JmsUtils() {
	}

	/**
	 * 
	 * Funcion generica que puede enviar un objeto Message a una cola de
	 * Weblogic.
	 * 
	 * @param parametros
	 *            Datos que se enviaran en el mensaje. Puede ser de tipo
	 *            {@link java.lang.String}, o cualquier objeto que implemente la
	 *            interfaz {@link java.util.Map} o {@link java.io.Serializable}
	 * @param factoriaJndi
	 *            factoria de la cola jms que se quiere utilizar
	 * @param colaJndi
	 *            cola jms a la que se quiere enviar el mensaje
	 * @param entorno
	 *            datos de inicializacion del contexto (opcional)
	 * @return String id del mensaje enviado
	 * 
	 */
	public static String enviarJMS(Object parametros, String factoriaJndi,
			String colaJndi, Hashtable<String, String> entorno// NOPMD
	) {
		return JmsUtils.enviarJMS(parametros, factoriaJndi, colaJndi, entorno,
				false, TopicSession.AUTO_ACKNOWLEDGE);
	}

	/**
	 * 
	 * Funcion generica que puede enviar un objeto Message a una cola de
	 * Weblogic.
	 * 
	 * @param parametros
	 *            Datos que se enviaran en el mensaje. Puede ser de tipo
	 *            {@link java.lang.String}, o cualquier objeto que implemente la
	 *            interfaz {@link java.util.Map} o {@link java.io.Serializable}
	 * @param factoriaJndi
	 *            factoria de la cola jms que se quiere utilizar
	 * @param colaJndi
	 *            cola jms a la que se quiere enviar el mensaje
	 * @param entorno
	 *            datos de inicializacion del contexto (opcional)
	 * @param session
	 *            Indica si el objeto queueSession se crea con sesion o no
	 * @param acknowledge
	 *            Modo en el que se notifica que se ha recibido el mensaje
	 * 
	 * @return String id del mensaje enviado
	 * 
	 */
	public static String enviarJMS(Object parametros, String factoriaJndi,
			String colaJndi, Hashtable<String, String> entorno,// NOPMD
			boolean session, int acknowledge) {
		JmsUtils.logger.trace("enviarJMS INI");
		Context jndiContext = null;
		TopicConnectionFactory topicConnectionFactory = null;
		TopicConnection topicConnection = null;
		TopicSession topicSession = null;
		Topic topic = null;
		TopicPublisher topicSender = null;
		Message oMessage = null;
		{
			try {
				// Al crear un nuevo contexto recordar que debe cerrarse al
				// finalizar la operacion.
				if (entorno != null) {
					// Si se ha informado los datos del entorno
					// los utilizamos para crear el contexto:
					JmsUtils.logger
							.debug("Se inicializa el contexto con la informacion que se pasa por parametro: "
									+ entorno);
					jndiContext = new InitialContext(entorno);
				} else {
					// Entorno por defecto
					JmsUtils.logger.debug("Entorno por defecto");
					jndiContext = new InitialContext();
				}
			} catch (NamingException e) {
				JmsUtils.logger.error("Error al crear el contexto de la cola",
						e);
				return null;
			}
			JmsUtils.logger.trace("Contexto creado correctamente.");
			try {
				// Preparamos la llamada a la cola jms
				topicConnectionFactory = (TopicConnectionFactory) jndiContext
						.lookup(factoriaJndi);
				JmsUtils.logger
						.trace("QueueConnectionFactory creado correctamente.");
				topic = (Topic) jndiContext.lookup(colaJndi);
				JmsUtils.logger.trace("Queue creado correctamente.");
				topicConnection = topicConnectionFactory
						.createTopicConnection();
				JmsUtils.logger.trace("QueueConnection creado correctamente.");
				topicSession = topicConnection.createTopicSession(session,
						acknowledge);
				JmsUtils.logger.trace("QueueSession creado correctamente.");
				topicSender = topicSession.createPublisher(topic);
				JmsUtils.logger.trace("topicSender creado correctamente.");

				// creamos el mensaje que queremos encolar con la informacion
				// recibida por parametro.
				if (parametros instanceof String) {
					/*
					 * ********************************************** *
					 * EL PARAMETRO QUE SE QUIERE ENVIAR ES UN STRING *
					 * ********************************************** *
					 */
					oMessage = topicSession.createTextMessage();// Se utiliza el
																// tipo
																// TextMessage
																// para encolar
					// Como solo hay un valor que introducir en el mensaje, lo
					// metemos en el texto de este.
					((TextMessage) oMessage).setText((String) parametros);
					JmsUtils.logger.trace("Mensaje JMS de texto creado: "
							+ (String) parametros);
				} else if (parametros instanceof Map<?, ?>) {
					/*
					 * ******************************************** *
					 * EL PARAMETRO QUE SE QUIERE ENVIAR ES UN MAPA *
					 * ******************************************** *
					 */
					StringBuilder sbTraza = new StringBuilder();
					oMessage = topicSession.createMapMessage();// Se utiliza el
																// tipo
																// MapMessage
																// para encolar
					// Recorremos todas las propiedades del mapa y las metemos
					// en el mensaje
					@SuppressWarnings(value = "rawtypes")
					Iterator it = ((Map<?, ?>) parametros).entrySet()
							.iterator();
					while (it.hasNext()) {
						@SuppressWarnings(value = "rawtypes")
						Map.Entry e = (Map.Entry) it.next();
						// Los parametros los introducimos como
						// propiedades en el mensaje
						((MapMessage) oMessage).setObjectProperty(
								String.valueOf(e.getKey()), e.getValue());
						sbTraza.append("\r\nParametro-> ").append(e.getKey())
								.append(" : ").append(e.getValue());
					}
					JmsUtils.logger.trace("Mensaje JMS de tipo mapa creado:"
							+ sbTraza.toString());
				} else if (parametros instanceof Serializable) {
					/*
					 * *************************************************** *
					 * EL PARAMETRO QUE SE QUIERE ENVIAR ES UN OBJETO JAVA
					 * SERIALIZABLE
					 * *************************************************** *
					 */
					oMessage = topicSession.createObjectMessage();// Se utiliza
																	// el tipo
																	// ObjectMessage
																	// para
																	// encolar
					((ObjectMessage) oMessage)
							.setObject((Serializable) parametros);
					JmsUtils.logger.trace("Mensaje JMS de tipo Object creado:"
							+ (ObjectMessage) oMessage);
				}

				// ENVIO DEL MENSAJE A LA COLA DESTINO
				topicSender.publish(oMessage);
			
				JmsUtils.logger.trace("enviarJMS FIN, messageID: "
						+ oMessage.getJMSMessageID());
				return oMessage.getJMSMessageID();
			} catch (NamingException e) {
				JmsUtils.logger.error(
						"Error al buscar la factoria o la cola JMS", e);
				return null;
			} catch (JMSException e) {
				JmsUtils.logger.error(
						"Error jms al crear/enviar el mensaje JMS", e);
				return null;
			} catch (Exception e) {
				JmsUtils.logger.error(
						"Error generico al enviar el mensaje JMS", e);
				return null;
			} finally {
				if (topicConnection != null) {
					try {
						topicConnection.close();
					} catch (JMSException e) {
						JmsUtils.logger.error("Error al cerrar la cola JMS", e);
					}
				}
				// Cerrar el contexto de envio a colas. Muy importante!!
				// Si no, cogera estos datos de contexto para la ejecucion
				// posterior...
				if (jndiContext != null) {
					try {
						jndiContext.close();
					} catch (NamingException e) {

						StringWriter sw = new StringWriter();
						PrintWriter pw = new PrintWriter(sw);
						e.printStackTrace(pw);
						String trace = sw.toString();
						JmsUtils.logger
								.error("Error al cerrar el Contexto de envio JMS:\r\n "
										+ trace);
					}
				}
			}
		}
	}

	/**
	 * Devuelve un mapa con los datos de un contexto para poder encolar mensajes
	 * JMS.
	 * 
	 * @param contextFactory
	 *            String
	 * @param urlProvider
	 *            String
	 * @param user
	 *            String
	 * @param password
	 *            String
	 * @return Hashtable<String, String>
	 */
	public static Hashtable<String, String> // NOPMD
	getEnviromentJMS(String contextFactory, String urlProvider, String user,
			String password) {
		JmsUtils.logger.trace("getEnviromentJMSPropagador INI");
		Hashtable<String, String> environment = new Hashtable<String, String>();// NOPMD
		environment.put(Context.INITIAL_CONTEXT_FACTORY, contextFactory);
		environment.put(Context.PROVIDER_URL, urlProvider);
		environment.put(Context.SECURITY_PRINCIPAL, user);
		environment.put(Context.SECURITY_CREDENTIALS, password);
		JmsUtils.logger.trace("getEnviromentJMSPropagador FIN: " + environment);
		return environment;

	}

}
