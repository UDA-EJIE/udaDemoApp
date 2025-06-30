package com.ejie.aa79b.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.security.Usuario;
import com.ejie.aa79b.service.EntidadService;

@Controller()
@RequestMapping(value = "/entidad")
public class EntidadController {

	@Autowired()
	private EntidadService entidadService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EntidadController.class);

	@RequestMapping(value = "/suggest", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidades(@RequestParam() String q,
			@RequestParam(required = false) String tipo, Locale locale) {
		EntidadController.LOGGER.info("[suggest] : Obtener Entidad por filtro");

		final Entidad entidad = new Entidad();
		if (Constants.LANG_CASTELLANO.equals(locale.getLanguage())) {
			entidad.setDescAmpEs(q);
		} else {
			entidad.setDescAmpEu(q);
		}
		entidad.setTipo(tipo);

		return this.entidadService.findAllLikeGrupoTrabajo(entidad, null, false);
	}

	@RequestMapping(value = "/suggestAlta", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidadesAlta(@RequestParam() String q,
			@RequestParam(required = false) String tipo, Locale locale) {
		EntidadController.LOGGER.info("[suggestAlta]");

		final Entidad entidad = new Entidad();
		if (Constants.LANG_CASTELLANO.equals(locale.getLanguage())) {
			entidad.setDescAmpEs(q);
		} else {
			entidad.setDescAmpEu(q);
		}
		entidad.setTipo(tipo);
		entidad.setEstado(Constants.ALTA);

		return this.entidadService.findAllLike(entidad, null, false);
	}

	@RequestMapping(value = "/expEstudio/{tipoEntidad}", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidadesConExpEnEstudio(@PathVariable() String tipoEntidad) {
		EntidadController.LOGGER.info("getEntidadesConExpEnEstudio");
		final Entidad entidad = new Entidad();
		if (tipoEntidad != null && !"".equals(tipoEntidad)) {
			entidad.setTipo(tipoEntidad);
			return this.entidadService.getEntidadesGestorasConExpEnEstado(entidad,
					Integer.valueOf(EstadoExpedienteEnum.EN_ESTUDIO.getValue()));
		} else {
			return new ArrayList<Entidad>();
		}

	}

	@RequestMapping(value = "/expEstudio", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidadesConExpEnEstudio() {
		EntidadController.LOGGER.info("getEntidadesConExpEnEstudio");
		return this.entidadService.getEntidadesGestorasConExpEnEstado(new Entidad(),
				Integer.valueOf(EstadoExpedienteEnum.EN_ESTUDIO.getValue()));
	}

	@RequestMapping(value = "/expCurso/{tipoEntidad}", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidadesConExpEnCurso(@PathVariable() String tipoEntidad) {
		EntidadController.LOGGER.info("getEntidadesConExpEnCurso");
		final Entidad entidad = new Entidad();
		if (tipoEntidad != null && !"".equals(tipoEntidad)) {
			entidad.setTipo(tipoEntidad);
			return this.entidadService.getEntidadesGestorasConExpEnEstado(entidad,
					Integer.valueOf(EstadoExpedienteEnum.EN_CURSO.getValue()));
		} else {
			return new ArrayList<Entidad>();
		}
	}

	@RequestMapping(value = "/expCurso", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidadesConExpEnCurso() {
		EntidadController.LOGGER.info("[GET - find_ALL] : getEntidadesConExpEnCurso Obtener Entidad por filtro");
		return this.entidadService.getEntidadesGestorasConExpEnEstado(new Entidad(),
				Integer.valueOf(EstadoExpedienteEnum.EN_CURSO.getValue()));
	}

	@RequestMapping(value = "/entAFacturarExpFasePdteRevisarFact/{tipoEntidad}", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidadesConExpFasePdteRevisarFact(@PathVariable() String tipoEntidad) {
		EntidadController.LOGGER
				.info("[GET - find_ALL] : entAFacturarExpFasePdteRevisarFact Obtener Entidad por filtro");
		final Entidad entidad = new Entidad();
		if (tipoEntidad != null && !"".equals(tipoEntidad) && !Constants.MINUS_UNO.toString().equals(tipoEntidad)) {
			entidad.setTipo(tipoEntidad);
		}
		return this.entidadService.getEntidadesGestorasConExpEnEstado(entidad,
				Integer.valueOf(EstadoExpedienteEnum.CERRADO.getValue()),
				Integer.valueOf(FaseExpedienteEnum.PDTE_REV_FACTURACION.getValue()), Constants.FACTURACION);
	}

	@RequestMapping(value = "/entAFacturarExpFasePdteRevisarFact", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidadesConExpFasePdteRevisarFact() {
		EntidadController.LOGGER
				.info("[GET - find_ALL] : entAFacturarExpFasePdteRevisarFact Obtener Entidad por filtro");
		return this.entidadService.getEntidadesGestorasConExpEnEstado(new Entidad(),
				Integer.valueOf(EstadoExpedienteEnum.CERRADO.getValue()),
				Integer.valueOf(FaseExpedienteEnum.PDTE_REV_FACTURACION.getValue()), Constants.FACTURACION);
	}

	@RequestMapping(value = "/entGestorasExpFasePdteRevisarFact/{tipoEntidad}", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> entGestorasExpFasePdteRevisarFact(@PathVariable() String tipoEntidad) {
		EntidadController.LOGGER
				.info("[GET - find_ALL] : entGestorasExpFasePdteRevisarFact Obtener Entidad por filtro");
		final Entidad entidad = new Entidad();
		if (tipoEntidad != null && !"".equals(tipoEntidad) && !Constants.MINUS_UNO.toString().equals(tipoEntidad)) {
			entidad.setTipo(tipoEntidad);
			return this.entidadService.getEntidadesGestorasConExpEnEstado(entidad,
					Integer.valueOf(EstadoExpedienteEnum.CERRADO.getValue()),
					Integer.valueOf(FaseExpedienteEnum.PDTE_REV_FACTURACION.getValue()), null);
		} else {
			return new ArrayList<Entidad>();
		}
	}

	@RequestMapping(value = "/entGestorasExpFasePdteRevisarFact", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> entGestorasExpFasePdteRevisarFact() {
		EntidadController.LOGGER
				.info("[GET - find_ALL] : entGestorasExpFasePdteRevisarFact Obtener Entidad por filtro");
		return this.entidadService.getEntidadesGestorasConExpEnEstado(new Entidad(),
				Integer.valueOf(EstadoExpedienteEnum.CERRADO.getValue()),
				Integer.valueOf(FaseExpedienteEnum.PDTE_REV_FACTURACION.getValue()), null);
	}

	@RequestMapping(value = "/{tipoEntidad}", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidadesConExp(@PathVariable() String tipoEntidad) {
		EntidadController.LOGGER.info("[GET - find_ALL] : getEntidadesConExp Obtener Entidad por filtro");
		final Entidad entidad = new Entidad();
		if (tipoEntidad != null && !"".equals(tipoEntidad)) {
			entidad.setTipo(tipoEntidad);
			return this.entidadService.getEntidadesGestorasConExpEnEstado(entidad, null);
		} else {
			return new ArrayList<Entidad>();
		}

	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidades() {
		EntidadController.LOGGER.info("[GET - find_ALL] : getEntidades Obtener Entidad por filtro");
		return this.entidadService.getEntidadesGestorasConExpEnEstado(new Entidad(), null);
	}

	@RequestMapping(value = "/exprel/{tipoEntidad}", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidadesConExpARelacionar(@PathVariable() String tipoEntidad) {
		EntidadController.LOGGER.info("[GET - find_ALL] : getEntidadesConExpARelacionar Obtener Entidad por filtro");

		final Entidad entidad = new Entidad();
		if (tipoEntidad != null && !"".equals(tipoEntidad)) {
			entidad.setTipo(tipoEntidad);
			return this.entidadService.getEntidadesConExpARelacionar(entidad);
		} else {
			return new ArrayList<Entidad>();
		}

	}

	@RequestMapping(value = "/exprel", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidadesConExpARelacionar() {
		EntidadController.LOGGER.info("[GET - find_ALL] : getEntidadesConExpARelacionar Obtener Entidad por filtro");
		return this.entidadService.getEntidadesConExpARelacionar(new Entidad());
	}

	@RequestMapping(value = "/entidadReceptor", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidadesReceptores(@RequestParam() String q) {
		EntidadController.LOGGER.info("[GET - find_ALL] : getEntidadesReceptores Obtener Entidad por filtro");
		final Entidad entidad = new Entidad();
		entidad.setDescAmpEu(q);
		return this.entidadService.getEntidadesReceptores(entidad);
	}

	@RequestMapping(value = "/entidadSolicitante", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidadesSolicitantes(@RequestParam() String q,
			@RequestParam(required = false) String tipo) {
		EntidadController.LOGGER.info("[GET - find_ALL] : entidadSolicitante Obtener Entidad por filtro");
		final Entidad entidad = new Entidad();
		entidad.setDescAmpEu(q);
		if (tipo != null && !"".equals(tipo)) {
			entidad.setTipo(tipo);
		}
		return this.entidadService.getEntidadesSolicitantes(entidad);
	}

	@RequestMapping(value = "/suggestEntidadesConContactosSolicitantes", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidadesConSolicitantes(@RequestParam() String q,
			@RequestParam(required = false) String tipo, Locale locale) {
		EntidadController.LOGGER.info("[GET - find_ALL] : getEntidadesConSolicitantes Obtener Entidad por filtro");

		final Entidad entidad = new Entidad();
		if (Constants.LANG_CASTELLANO.equals(locale.getLanguage())) {
			entidad.setDescAmpEs(q);
		} else {
			entidad.setDescAmpEu(q);
		}
		entidad.setTipo(tipo);

		return this.entidadService.getEntidadesConContactosSolicitantes(entidad);
	}

	@RequestMapping(value = "/suggestEntidadesConContactosSolicitantesConBajas", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> suggestEntidadesConContactosSolicitantesConBajas(@RequestParam() String q,
			@RequestParam(required = false) String tipo, Locale locale) {
		EntidadController.LOGGER
				.info("[GET - find_ALL] : suggestEntidadesConContactosSolicitantesConBajas Obtener Entidad por filtro");

		final Entidad entidad = new Entidad();
		if (Constants.LANG_CASTELLANO.equals(locale.getLanguage())) {
			entidad.setDescAmpEs(q);
		} else {
			entidad.setDescAmpEu(q);
		}
		entidad.setTipo(tipo);

		return this.entidadService.getEntidadesConContactosSolicitantesConBajas(entidad);
	}

	@RequestMapping(value = "/entidadesGestAnulacion/{tipoEntidad}", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidadesGestAnulacion(@PathVariable() String tipoEntidad) {
		EntidadController.LOGGER.info("[GET - find_ALL] : getEntidadesGestAnulacion Obtener Entidad por filtro");
		final Entidad entidad = new Entidad();
		if (StringUtils.isNotBlank(tipoEntidad) && !Constants.MINUS_UNO.toString().equals(tipoEntidad)) {
			entidad.setTipo(tipoEntidad);
		}
		return this.entidadService.getEntidadesGestAnulacion(entidad);
	}
	
	@RequestMapping(value = "/expConfidenciales/{tipoEntidad}", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidadesConExpConfidenciales(@PathVariable() String tipoEntidad) {
		EntidadController.LOGGER.info("getEntidadesConExpConfidenciales");
		final Entidad entidad = new Entidad();
		if (tipoEntidad != null && !"".equals(tipoEntidad)) {
			Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			
			entidad.setTipo(tipoEntidad);
			return this.entidadService.getEntidadesGestorasConExpConfidenciales(entidad, credentials.getNif());
		} else {
			return new ArrayList<Entidad>();
		}

	}

	@RequestMapping(value = "/expConfidenciales", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidadesConExpConfidenciales() {
		EntidadController.LOGGER.info("getEntidadesConExpConfidenciales");
		
		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		
		return this.entidadService.getEntidadesGestorasConExpConfidenciales(new Entidad(), credentials.getNif());
	}

	@RequestMapping(value = "/actDatosFacturacion/{tipoEntidad}", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getActDatosFacturacion(@PathVariable() String tipoEntidad) {
		EntidadController.LOGGER.info("[GET - find_ALL] : getActDatosFacturacion Obtener Entidad por filtro");
		final Entidad entidad = new Entidad();
		if (tipoEntidad != null && !"".equals(tipoEntidad) && !Constants.MINUS_UNO.toString().equals(tipoEntidad)) {
			entidad.setTipo(tipoEntidad);
			return this.entidadService.getEntidadesGestorasActDatosFacturacion(entidad, null);
		} else {
			return new ArrayList<Entidad>();
		}
	}

	@RequestMapping(value = "/actDatosFacturacion", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getActDatosFacturacion() {
		EntidadController.LOGGER.info("[GET - find_ALL] : getActDatosFacturacion Obtener Entidad por filtro");
		return this.entidadService.getEntidadesGestorasActDatosFacturacion(new Entidad(), null);
	}

	@RequestMapping(value = "/entAFacturarActDatosFacturacion/{tipoEntidad}", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntAFacturarActDatosFacturacion(@PathVariable() String tipoEntidad) {
		EntidadController.LOGGER
				.info("[GET - find_ALL] : getEntAFacturarActDatosFacturacion Obtener Entidad por filtro");
		final Entidad entidad = new Entidad();
		if (tipoEntidad != null && !"".equals(tipoEntidad) && !Constants.MINUS_UNO.toString().equals(tipoEntidad)) {
			entidad.setTipo(tipoEntidad);
		}
		return this.entidadService.getEntidadesGestorasActDatosFacturacion(entidad, Constants.FACTURACION);
	}

	@RequestMapping(value = "/entAFacturarActDatosFacturacion", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntAFacturarActDatosFacturacion() {
		EntidadController.LOGGER
				.info("[GET - find_ALL] : getEntAFacturarActDatosFacturacion Obtener Entidad por filtro");
		return this.entidadService.getEntidadesGestorasActDatosFacturacion(new Entidad(), Constants.FACTURACION);
	}
	
	@RequestMapping(value = "/entidadesConGestorActivo/{tipoEntidad}", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidadesConGestorActivo(@PathVariable() String tipoEntidad) {
		EntidadController.LOGGER.info("[GET - find_ALL] : getEntidadesConGestorActivo Obtener Entidad por filtro");

		final Entidad entidad = new Entidad();
		if (tipoEntidad != null && !"".equals(tipoEntidad)) {
			entidad.setTipo(tipoEntidad);
			return this.entidadService.getEntidadesConGestorActivo(entidad);
		} else {
			return this.entidadService.getEntidadesConGestorActivo(new Entidad());
		}

	}
	
	@RequestMapping(value = "/entidadesConGestorActivo", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidadesConGestorActivo() {
		EntidadController.LOGGER.info("[GET - find_ALL] : getEntidadesConGestorActivo Obtener Entidad por filtro");
		return this.entidadService.getEntidadesConGestorActivo(new Entidad());
	}

}
