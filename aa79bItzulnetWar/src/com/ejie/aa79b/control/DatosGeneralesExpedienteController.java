package com.ejie.aa79b.control;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.Fichero;
import com.ejie.aa79b.model.ObservacionesExpediente;
import com.ejie.aa79b.model.Persona;
import com.ejie.aa79b.model.enums.AccesoExpedienteEnum;
import com.ejie.aa79b.model.enums.OrigenExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.security.Usuario;
import com.ejie.aa79b.service.DatosGeneralesExpedienteService;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.aa79b.webservices.PIDService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;

/**
 * DatosGeneralesExpedienteController
 * 
 * Controller utilizado para gestionar la pestaña de datos generales del
 * expediente
 * 
 * @author mrodriguez
 */

@Controller
@RequestMapping(value = "/datosgeneralesexpediente")
public class DatosGeneralesExpedienteController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatosGeneralesExpedienteController.class);

	private static final String FICHERO = "fichero";

	@Autowired()
	private PIDService pidService;

	@Autowired()
	private DatosGeneralesExpedienteService datosGeneralesExpedienteService;

	/**
	 * Metodo de presentacion del RUP_TABLE.
	 * 
	 * @return Expediente
	 */
	@RequestMapping(value = "/datosexpedienteview", method = RequestMethod.GET)
	public String getModelAndView() {
		return "datosgeneralesexpediente";
	}

	/**
	 * Metodo de presentacion del RUP_TABLE.
	 * 
	 * @return Expediente
	 */
	@RequestMapping(value = "/planificacion/datosexpedienteview", method = RequestMethod.GET)
	public ModelAndView getModelAndViewPlanificacion() {
		return new ModelAndView("planificaciondatosgeneralesexpediente");
	}

	/**
	 * Metodo de presentacion del RUP_TABLE.
	 * 
	 * @return Expediente
	 */
	@RequestMapping(value = "/datosexpediente", method = RequestMethod.GET)
	public @ResponseBody Expediente getDatosGeneralesExpediente() {
		DatosGeneralesExpedienteController.LOGGER.info("[GET - View] : datosgeneralesexpediente");

		return this.obtenerDatosGeneralesExpediente(null, null, AccesoExpedienteEnum.ALTA.getValue());
	}

	/**
	 * Metodo de presentacion del RUP_TABLE.
	 * 
	 * @param idExpediente
	 *            Integer
	 * @param anyoExpediente
	 *            Integer
	 * @param origen
	 *            String
	 * @return Expediente
	 */
	@RequestMapping(value = "/datosexpediente/{idExpediente}/{anyoExpediente}/{origen}", method = RequestMethod.GET)
	public @ResponseBody Expediente getDatosGeneralesExpediente(@PathVariable Integer idExpediente,
			@PathVariable Integer anyoExpediente, @PathVariable String origen) {
		DatosGeneralesExpedienteController.LOGGER.info("[GET - View] : datosgeneralesexpediente");

		return this.obtenerDatosGeneralesExpediente(idExpediente, anyoExpediente, origen);
	}
	
	@RequestMapping(value = "/cambiarTipoExpediente", method = RequestMethod.POST)
	public @ResponseBody() void procCambioTipoExpedientePL(@RequestJsonBody(required = false, param = "expediente") Expediente expediente) {
		DatosGeneralesExpedienteController.LOGGER.info("[POST] : Cambiar el tipo del expediente");
		this.datosGeneralesExpedienteService.procCambioTipoExpedientePL(expediente.getAnyo(), expediente.getNumExp(), expediente.getIdTipoExpediente());
	}
	

	/**
	 * @param numExpediente
	 *            Integer
	 * @param anyoExpediente
	 *            Integer
	 * @param origen
	 *            String
	 * @return Expediente
	 */
	private Expediente obtenerDatosGeneralesExpediente(Integer numExpediente, Integer anyoExpediente, String origen) {

		Expediente expediente = new Expediente();

		if (numExpediente == null) {

			Date fechaAlta = new Date();
			// Obtenemos la fecha del sistema
			expediente.setFechaAlta(fechaAlta);
			// Obtenemos la hora del sistema
			expediente.setHoraAlta(DateUtils.obtHoraFormateada(fechaAlta));

			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			expediente.setAnyo((long) year);

			Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			Persona tecnico = new Persona();
			tecnico.setDni(credentials.getNif());
			tecnico.setNombre(credentials.getName());
			tecnico.setApellido1(credentials.getSurname());
			expediente.setTecnico(tecnico);

		} else {

			expediente.setNumExp(numExpediente);
			expediente.setAnyo((long) anyoExpediente);

			expediente = this.datosGeneralesExpedienteService.find(expediente, origen);

			// Tratamiento para poner disabled cuando el técnico no
			// coincide con el técnico asignado al expediente
			final Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication()
					.getCredentials();
			final String dni = expediente.getTecnico().getDni();

			if (!credentials.getNif().equals(dni)) {
				expediente.getTecnico().setEstado(Constants.CONSULTA);
			}
		}

		return expediente;
	}

	/**
	 * Metodo de presentacion del RUP_TABLE.
	 * 
	 * @param idExpediente
	 *            Integer
	 * @param anyoExpediente
	 *            Integer
	 * @param origen
	 *            String
	 * @return ObservacionesExpediente
	 */
	@RequestMapping(value = "/observacionesexpediente/{idExpediente}/{anyoExpediente}", method = RequestMethod.GET)
	public @ResponseBody ObservacionesExpediente getObservacionesExpediente(@PathVariable Integer idExpediente,
			@PathVariable Integer anyoExpediente) {
		DatosGeneralesExpedienteController.LOGGER.info("[GET] : getObservacionesExpediente");

		Expediente expediente = new Expediente();
		expediente.setNumExp(idExpediente);
		expediente.setAnyo((long) anyoExpediente);

		return this.datosGeneralesExpedienteService.observacionesFind(expediente);
	}

	/**
	 * Operacion CRUD Create. Creacion de un nuevo registro a partir del bean
	 * indicado.
	 *
	 * @param expediente
	 *            Expediente Bean que contiene la informacion con la que se va a
	 *            crear el nuevo registro.
	 * @param origen
	 *            String
	 * @return DatosGeneralesExpediente Bean resultante del proceso de creacion.
	 */
	@RequestMapping(value = "/addExpediente/{origen}", method = RequestMethod.POST)
	public @ResponseBody Expediente add(@RequestBody Expediente expediente, @PathVariable String origen,
			HttpServletRequest request) {
		Expediente expedienteAux = null;

		if (expediente != null) {
			expediente.setOrigen(OrigenExpedienteEnum.OFICIO.getValue());
			if (expediente.getNumExp() == null) {
				Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
				Persona tecnico = new Persona();
				tecnico.setDni(credentials.getNif());
				expediente.setTecnico(tecnico);
			}

			if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(expediente.getIdTipoExpediente())) {
				expediente.setExpedienteTradRev(null);
				expediente.setDocumentosExpediente(null);
			} else {
				expediente.setExpedienteInterpretacion(null);
			}

			if (expediente.getNumExp() != null) {
				expedienteAux = this.datosGeneralesExpedienteService.update(expediente, origen);
				DatosGeneralesExpedienteController.LOGGER.info("[POST] : Expediente actualizado correctamente");
			} else {
				expedienteAux = this.datosGeneralesExpedienteService.add(expediente, request);
				DatosGeneralesExpedienteController.LOGGER.info("[POST] : Expediente insertado correctamente");
			}
		}

		return expedienteAux;
	}

	/**
	 * @param fichero
	 *            Fichero
	 * @param request
	 *            HttpServletRequest
	 * @return Fichero
	 */
	@RequestMapping(value = "/cargarSolicitudes", method = RequestMethod.POST)
	public @ResponseBody() Fichero cargarSolicitudes(@RequestJsonBody() Fichero fichero, HttpServletRequest request) {
		DatosGeneralesExpedienteController.LOGGER.info("cargarSolicitudes");
		Fichero aux = (Fichero) request.getSession().getAttribute(DatosGeneralesExpedienteController.FICHERO);
		String oid;
		try {
			oid = this.pidService.addDocument(aux.getNombre(), aux.getRutaPif());
			aux.setOid(oid);
			DatosGeneralesExpedienteController.LOGGER.info("oid " + oid);
		} catch (Exception e) {
			DatosGeneralesExpedienteController.LOGGER.info("cargarSolicitudes Error: " + e);
		}
		request.getSession().removeAttribute(DatosGeneralesExpedienteController.FICHERO);
		return aux;
	}

}
