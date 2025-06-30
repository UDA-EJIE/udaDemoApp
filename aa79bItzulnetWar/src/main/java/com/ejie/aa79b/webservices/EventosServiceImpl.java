package com.ejie.aa79b.webservices;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.exceptions.AppRuntimeException;
import com.ejie.aa79b.dao.ExpedienteTradRevDao;
import com.ejie.aa79b.model.Comunicaciones;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.cambiofecha.CrearCambioFecha;
import com.ejie.aa79b.model.enums.TipoCierreEnum;
import com.ejie.aa79b.model.factura.CrearFactura;
import com.ejie.aa79b.model.finexpediente.CrearFinExpediente;
import com.ejie.aa79b.model.finexpediente.RutasPif;
import com.ejie.aa79b.model.messageevent.CrearMessageEvent;
import com.ejie.aa79b.webservices.w43ea.eventos.Notificacion;
import com.ejie.aa79b.webservices.w43ea.eventos.NotificacionPortBindingQSService1;
import com.ejie.aa79b.webservices.w43ea.eventos.NotificarEvento;
import com.ejie.aa79b.webservices.w43ea.eventos.ReglaSuscripcion;

@Service(value = "aa79bEventosService")
public class EventosServiceImpl implements EventosService {

	private static final String COD_APLICACION_W05U = "W05U";
	private static final String WSDL = "webservice.evento.wsdl";
	private static final String NAME_SPACE_URI = "qname.evento.namespaceURI";
	private static final String LOCAL_PART = "qname.evento.localpart";

	@Autowired()
	private Properties appConfiguration;
	@Autowired()
	private ExpedienteTradRevDao expedienteTradRevDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(EventosServiceImpl.class);

	@Override
	public void createInvoice(String idFactura, CrearFactura factura) {
		EventosServiceImpl.LOGGER.info("EventosServiceImpl.createInvoice");
		try {
			NotificarEvento evento = new NotificarEvento();
			evento.setEventWho(Constants.CONSTANTE_APLICACION);
			evento.setEventEntity("IVAP");
			evento.setEventWhat("CREATE_INVOICE");
			evento.setEventTipology("INVOICE");
			evento.setEventCorrelationId(Constants.CONSTANTE_APLICACION + "_" + idFactura);

			Calendar calendar = Calendar.getInstance();
			evento.setEventTimeStamp(calendar.getTime());

			ReglaSuscripcion regla = new ReglaSuscripcion();
			regla.setNombre("targetApp");
			regla.setValor(COD_APLICACION_W05U);
			evento.getReglasSuscripcion().add(regla);

			String stringXml = EventosServiceImpl.crearXml(factura);

			EventosServiceImpl.LOGGER.info("Xml crear factura: " + stringXml);

			// INSTANCIAR EL CLIENTE DEL SERVICIO
			NotificacionPortBindingQSService1 service = new NotificacionPortBindingQSService1(
					new URL(this.appConfiguration.getProperty(WSDL)),
					new QName(this.appConfiguration.getProperty(NAME_SPACE_URI),
							this.appConfiguration.getProperty(LOCAL_PART)));
			Notificacion notificacionManager = service.getNotificacionPortBindingQSPort1();
			((BindingProvider) notificacionManager).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
					this.appConfiguration.getProperty(WSDL));

			// INVOCAR AL WS
			String msgId = notificacionManager.notificarEvento(evento.getEventWho(), evento.getEventEntity(),
					evento.getEventWhat(), evento.getEventTipology(), calendar.getTime(),
					evento.getEventCorrelationId(), evento.getReglasSuscripcion(), stringXml);

			// IDENTIFICADOR DEL EVENTO EN EL ENRUTADOR
			EventosServiceImpl.LOGGER.info("Acuse de recibo: " + msgId);
		} catch (Exception e) {
			throw new AppRuntimeException("Error enviar factura", e);
		}
	}

	@Override
	public void createInvoiceCambioFecha(ExpedienteTradRev expedienteTradRev, Expediente exp) {
		try {
			NotificarEvento evento = new NotificarEvento();
			evento.setEventWho(Constants.CONSTANTE_APLICACION);
			evento.setEventWhat(Constants.EVENT_WHAT);
			evento.setEventTipology(Constants.EVENT_TIPOLOGY);
			evento.setEventCorrelationId(Constants.CONSTANTE_APLICACION);

			Calendar calendar = Calendar.getInstance();
			evento.setEventTimeStamp(calendar.getTime());

			ReglaSuscripcion regla = new ReglaSuscripcion();
			regla.setNombre("targetApp");
			regla.setValor(exp.getAplicacionOrigen());
			evento.getReglasSuscripcion().add(regla);
			ReglaSuscripcion regla2 = new ReglaSuscripcion();
			regla2.setNombre("changeType");
			regla2.setValor(Constants.CHANGE_DATE);
			evento.getReglasSuscripcion().add(regla2);

			CrearCambioFecha crearCambioFecha = new CrearCambioFecha();
			crearCambioFecha.setCodigoExpediente(expedienteTradRev.getAnyoNumExpConcatenado());
			crearCambioFecha.setCodigoExpedienteExterno(expedienteTradRev.getRefTramitagune());
			crearCambioFecha.setFechaIzo(expedienteTradRev.getFechaHoraFinalIZOEs());

			String stringXml = EventosServiceImpl.crearXmlCambioFecha(crearCambioFecha);

			EventosServiceImpl.LOGGER.info("Xml crear cambio fecha: " + stringXml);

			// INSTANCIAR EL CLIENTE DEL SERVICIO
			NotificacionPortBindingQSService1 service = new NotificacionPortBindingQSService1(
					new URL(this.appConfiguration.getProperty(WSDL)),
					new QName(this.appConfiguration.getProperty(NAME_SPACE_URI),
							this.appConfiguration.getProperty(LOCAL_PART)));
			Notificacion notificacionManager = service.getNotificacionPortBindingQSPort1();
			((BindingProvider) notificacionManager).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
					this.appConfiguration.getProperty(WSDL));

			// INVOCAR AL WS
			String msgId = notificacionManager.notificarEvento(evento.getEventWho(), evento.getEventEntity(),
					evento.getEventWhat(), evento.getEventTipology(), calendar.getTime(),
					evento.getEventCorrelationId(), evento.getReglasSuscripcion(), stringXml);

			// IDENTIFICADOR DEL EVENTO EN EL ENRUTADOR
			EventosServiceImpl.LOGGER.info("Acuse de recibo: " + msgId);
		} catch (Exception e) {
			throw new AppRuntimeException("Error cambio fecha ", e);
		}
	}

	@Override
	public void createInvoiceCambioEstado(Expediente expediente, List<String> rutasPif, int tipoCierre) {
		try {
			NotificarEvento evento = new NotificarEvento();
			evento.setEventWho(Constants.CONSTANTE_APLICACION);
			evento.setEventWhat(Constants.EVENT_WHAT);
			evento.setEventTipology(Constants.EVENT_TIPOLOGY);
			evento.setEventCorrelationId(Constants.CONSTANTE_APLICACION);

			Calendar calendar = Calendar.getInstance();
			evento.setEventTimeStamp(calendar.getTime());

			ReglaSuscripcion regla = new ReglaSuscripcion();
			regla.setNombre("targetApp");
			regla.setValor(expediente.getAplicacionOrigen());
			evento.getReglasSuscripcion().add(regla);
			ReglaSuscripcion regla2 = new ReglaSuscripcion();
			regla2.setNombre("changeType");
			regla2.setValor(Constants.CHANGE_RESULT_DELIVERED);
			evento.getReglasSuscripcion().add(regla2);

			CrearFinExpediente crearFinExpediente = new CrearFinExpediente();
			crearFinExpediente.setCodigoExpediente(expediente.getAnyoNumExpConcatenado());
			ExpedienteTradRev expTradRev = new ExpedienteTradRev();
			expTradRev.setAnyo(expediente.getAnyo());
			expTradRev.setNumExp(expediente.getNumExp());
			expTradRev = this.expedienteTradRevDao.find(expTradRev);
			expediente.setExpedienteTradRev(expTradRev);
			if(expTradRev != null) {
				crearFinExpediente.setCodigoExpedienteExterno(expediente.getExpedienteTradRev().getRefTramitagune());
			}else {
				crearFinExpediente.setCodigoExpedienteExterno("");
			}

			crearFinExpediente.setTipoCierre(tipoCierre);
			RutasPif rP = new RutasPif();
			rP.setRutaPif(rutasPif);
			if (rutasPif.size() > 0) {
				crearFinExpediente.setRutasPIF(rP);
			}

			if(TipoCierreEnum.RECHAZADO.getValue() == tipoCierre && expediente.getAnulacionRechazo() != null) {
				StringBuilder str = new StringBuilder();
				str.append(expediente.getAnulacionRechazo().getMotivoDescEu());
				str.append(" / ");
				str.append(expediente.getAnulacionRechazo().getMotivoDescEs());
				if (expediente.getAnulacionRechazo().getObservaciones() != null) {
					str.append(" // ");
					str.append(expediente.getAnulacionRechazo().getObservaciones());
				}
				crearFinExpediente.setMotivo(str.toString());
			}else if(TipoCierreEnum.ANULADO.getValue() == tipoCierre && expediente.getMotivosAnulacion() != null) {
				StringBuilder str = new StringBuilder();
				str.append(expediente.getMotivosAnulacion().getDescEu012());
				str.append(" / ");
				str.append(expediente.getMotivosAnulacion().getDescEs012());
				crearFinExpediente.setMotivo(str.toString());
			}

			String stringXml = EventosServiceImpl.crearXmlFinExpediente(crearFinExpediente);

			EventosServiceImpl.LOGGER.info("Xml crear cambio estado: " + stringXml);

			// INSTANCIAR EL CLIENTE DEL SERVICIO
			NotificacionPortBindingQSService1 service = new NotificacionPortBindingQSService1(
					new URL(this.appConfiguration.getProperty(WSDL)),
					new QName(this.appConfiguration.getProperty(NAME_SPACE_URI),
							this.appConfiguration.getProperty(LOCAL_PART)));
			Notificacion notificacionManager = service.getNotificacionPortBindingQSPort1();
			((BindingProvider) notificacionManager).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
					this.appConfiguration.getProperty(WSDL));

			// INVOCAR AL WS
			String msgId = notificacionManager.notificarEvento(evento.getEventWho(), evento.getEventEntity(),
					evento.getEventWhat(), evento.getEventTipology(), calendar.getTime(),
					evento.getEventCorrelationId(), evento.getReglasSuscripcion(), stringXml);

			// IDENTIFICADOR DEL EVENTO EN EL ENRUTADOR
			EventosServiceImpl.LOGGER.info("Acuse de recibo: " + msgId);
		} catch (Exception e) {
			throw new AppRuntimeException("Error cambio estado", e);
		}
	}

	/**
	 * @param w43dImportDocuments
	 *            W43dImportDocuments
	 * @return String
	 * @throws JAXBException
	 * @throws IOException
	 * @throws Exception
	 * @throws Exception
	 */
	private static String crearXml(CrearFactura factura) throws JAXBException, IOException {
		final JAXBContext jaxbContext = JAXBContext.newInstance(CrearFactura.class);
		final Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		marshaller.marshal(factura, byteArrayOutputStream);

		byteArrayOutputStream.flush();
		final String stringXml = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
		byteArrayOutputStream.close();

		return stringXml;
	}

	/**
	 * @param w43dImportDocuments
	 *            W43dImportDocuments
	 * @return String
	 * @throws JAXBException
	 * @throws IOException
	 * @throws Exception
	 * @throws Exception
	 */
	private static String crearXmlCambioFecha(CrearCambioFecha crearCambioFecha) throws JAXBException, IOException{
		final JAXBContext jaxbContext = JAXBContext.newInstance(CrearCambioFecha.class);
		final Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		marshaller.marshal(crearCambioFecha, byteArrayOutputStream);

		byteArrayOutputStream.flush();
		final String stringXml = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
		byteArrayOutputStream.close();

		return stringXml;
	}

	/**
	 * @param w43dImportDocuments
	 *            W43dImportDocuments
	 * @return String
	 * @throws JAXBException
	 * @throws IOException
	 * @throws Exception
	 * @throws Exception
	 */
	private static String crearXmlFinExpediente(CrearFinExpediente crearFinExpediente) throws JAXBException, IOException{
		final JAXBContext jaxbContext = JAXBContext.newInstance(CrearFinExpediente.class);
		final Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		marshaller.marshal(crearFinExpediente, byteArrayOutputStream);

		byteArrayOutputStream.flush();
		final String stringXml = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
		byteArrayOutputStream.close();

		return stringXml;
	}



	@Override
	public void createComunicacion( Comunicaciones comunicacion, Expediente exp) {
		try {
			NotificarEvento evento = new NotificarEvento();
			evento.setEventWho(Constants.CONSTANTE_APLICACION);
			evento.setEventWhat(Constants.EVENT_WHAT_AA79B_MESSAGE);
			evento.setEventTipology(Constants.EVENT_TIPOLOGY_AA79B_MESSAGE);
			evento.setEventCorrelationId(Constants.CONSTANTE_APLICACION);

			Calendar calendar = Calendar.getInstance();
			evento.setEventTimeStamp(calendar.getTime());

			ReglaSuscripcion regla = new ReglaSuscripcion();
			regla.setNombre("targetApp");
			regla.setValor(exp.getAplicacionOrigen());
			evento.getReglasSuscripcion().add(regla);
			ReglaSuscripcion regla2 = new ReglaSuscripcion();
			regla2.setNombre("changeType");
			regla2.setValor(Constants.CHANGE_TYPE_AA79B_MESSAGE);
			evento.getReglasSuscripcion().add(regla2);

			CrearMessageEvent crearMessageEvent = new CrearMessageEvent();

			crearMessageEvent.setCodigoExpediente( exp.getAnyoNumExpConcatenado());
			crearMessageEvent.setCodigoExpedienteExterno( comunicacion.getRefTramitagune());
			crearMessageEvent.setAsunto( comunicacion.getAsunto() );
			crearMessageEvent.setMensaje( comunicacion.getMensaje() );
			if ( StringUtils.isNotEmpty( comunicacion.getRutaPif() )) {
				crearMessageEvent.setFichero( comunicacion.getRutaPif() );
			}
			String stringXml = EventosServiceImpl.crearXmlMessageAa79b( crearMessageEvent );

			EventosServiceImpl.LOGGER.info("Xml crear mensaje comunicacion: {}", stringXml);

			// INSTANCIAR EL CLIENTE DEL SERVICIO
			NotificacionPortBindingQSService1 service = new NotificacionPortBindingQSService1(
					new URL(this.appConfiguration.getProperty(WSDL)),
					new QName(this.appConfiguration.getProperty(NAME_SPACE_URI),
					this.appConfiguration.getProperty(LOCAL_PART)));

			Notificacion notificacionManager = service.getNotificacionPortBindingQSPort1();
			((BindingProvider) notificacionManager).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,	this.appConfiguration.getProperty(WSDL));

			// INVOCAR AL WS
			String msgId = notificacionManager.notificarEvento(evento.getEventWho(), evento.getEventEntity(),
					evento.getEventWhat(), evento.getEventTipology(), calendar.getTime(),
					evento.getEventCorrelationId(), evento.getReglasSuscripcion(), stringXml);

			// IDENTIFICADOR DEL EVENTO EN EL ENRUTADOR
			EventosServiceImpl.LOGGER.info("Acuse de recibo del envio del evento de comunicacion AA79_IZO_MESSAGE msgId: {} "+ "timestamp: " + calendar.getTime(), msgId);
		} catch (Exception e) {
			throw new AppRuntimeException("Error createComunicacion ", e);
		}
	}


	private static String crearXmlMessageAa79b(CrearMessageEvent crearMessageEvent) throws JAXBException, IOException{
		final JAXBContext jaxbContext = JAXBContext.newInstance(CrearMessageEvent.class);
		final Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		marshaller.marshal(crearMessageEvent, byteArrayOutputStream);

		byteArrayOutputStream.flush();
		final String stringXml = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
		byteArrayOutputStream.close();

		return stringXml;
	}

}
