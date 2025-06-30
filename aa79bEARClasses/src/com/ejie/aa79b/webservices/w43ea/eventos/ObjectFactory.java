package com.ejie.aa79b.webservices.w43ea.eventos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.ejie.aa79b.webservices.w43ea.eventos
 * package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _NotificarEventoResponse_QNAME = new QName("http://www.ejie.net/",
			"notificarEventoResponse");
	private final static QName _NotificarEvento_QNAME = new QName("http://www.ejie.net/", "notificarEvento");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package:
	 * com.ejie.aa79b.webservices.w43ea.eventos
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link NotificarEvento }
	 * 
	 */
	public NotificarEvento createNotificarEvento() {
		return new NotificarEvento();
	}

	/**
	 * Create an instance of {@link NotificarEventoResponse }
	 * 
	 */
	public NotificarEventoResponse createNotificarEventoResponse() {
		return new NotificarEventoResponse();
	}

	/**
	 * Create an instance of {@link ReglaSuscripcion }
	 * 
	 */
	public ReglaSuscripcion createReglaSuscripcion() {
		return new ReglaSuscripcion();
	}

	/**
	 * Create an instance of {@link JAXBElement
	 * }{@code <}{@link NotificarEventoResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.ejie.net/", name = "notificarEventoResponse")
	public JAXBElement<NotificarEventoResponse> createNotificarEventoResponse(NotificarEventoResponse value) {
		return new JAXBElement<NotificarEventoResponse>(_NotificarEventoResponse_QNAME, NotificarEventoResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link NotificarEvento
	 * }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.ejie.net/", name = "notificarEvento")
	public JAXBElement<NotificarEvento> createNotificarEvento(NotificarEvento value) {
		return new JAXBElement<NotificarEvento>(_NotificarEvento_QNAME, NotificarEvento.class, null, value);
	}

}
