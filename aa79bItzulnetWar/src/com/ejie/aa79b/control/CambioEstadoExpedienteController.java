package com.ejie.aa79b.control;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.aa79b.model.AnulacionExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.Leyenda;
import com.ejie.aa79b.model.RechazoExpediente;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.service.AnulacionesService;
import com.ejie.aa79b.service.EstadosExpedienteService;
import com.ejie.aa79b.service.ExpedienteService;
import com.ejie.aa79b.service.ExpedientesCambioEstadoService;
import com.ejie.aa79b.service.FasesExpedienteService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 * CambioEstadoExpedienteController
 * 
 * @author mrodriguez
 */

@Controller()
@RequestMapping(value = "/servicios/cambioEstadoExpediente")
public class CambioEstadoExpedienteController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CambioEstadoExpedienteController.class);

	@Autowired()
	private ExpedientesCambioEstadoService expedientesCambioEstadoService;
	@Autowired()
	private AnulacionesService anulacionesService;
	@Autowired()
	private ExpedienteService expedienteService;
	@Autowired()
	private EstadosExpedienteService estadosExpedienteService;
	@Autowired()
	private FasesExpedienteService fasesExpedienteService;

	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/maint", method = RequestMethod.GET)
	public String getCambioEstadoExpediente() {
		CambioEstadoExpedienteController.LOGGER.info("[GET - View]: getCambioEstadoExpediente");
		return "cambioestadoexpediente";
	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<Expediente> getFilter(@RequestJsonBody(param = "filter") Expediente filter,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		CambioEstadoExpedienteController.LOGGER.info("[POST - filter] : Consulta expedientes");

		return this.expedientesCambioEstadoService.findExpedientesCambioEstado(filter, jqGridRequestDto);

	}

	@RequestMapping(value = "/detalleCambioEstadoExp", method = RequestMethod.GET)
	public String getDetalleCambioEstadoExp(Model model) {
		CambioEstadoExpedienteController.LOGGER.info("[GET - View] : getDetalleCambioEstadoExp");

		model.addAttribute("estadosExpediente", this.estadosExpedienteService.findAllSinAltaExp());
		model.addAttribute("fasesExpediente",
				this.fasesExpedienteService.findAllCambioEstadoExp(new FasesExpediente(), StringUtils.EMPTY));

		return "detallecambioestadoexp";
	}

	/**
	 * Comprueba si hay facturas asociadas al expediente y que no estén anuladas
	 * 
	 * @param anyo
	 *            Long
	 * @param numExp
	 *            Integer
	 * @return long, Devuelve (0/n): 0 - Si el expediente no tiene facturas
	 *         asociadas; n - Si el expediente tiene facturas asociadas y que no
	 *         estén anuladas
	 */
	@RequestMapping(value = "/comprobarExpFacturado/{anyoExpediente}/{idExpediente}", method = RequestMethod.GET)
	public @ResponseBody() long comprobarExpFacturado(@PathVariable Long anyoExpediente,
			@PathVariable Integer idExpediente) {
		CambioEstadoExpedienteController.LOGGER.info("[POST] : Comprobar expediente facturado");
		return this.expedientesCambioEstadoService.comprobarExpFacturado(anyoExpediente, idExpediente);
	}

	/**
	 * Se comprueba si el orden establecido para las tareas es correcto.
	 * 
	 * @param anulacionExpediente
	 *            AnulacionExpediente
	 * @param listExpedientesStr
	 *            String
	 */
	@RequestMapping(value = "/cambiarEstadoExp", method = RequestMethod.POST)
	public @ResponseBody() void cambiarEstadoExp(
			@RequestJsonBody(required = false, param = "anulacionExpediente") AnulacionExpediente anulacionExpediente,
			@RequestJsonBody(required = false, param = "rechazoExp") RechazoExpediente rechazoExp,
			@RequestJsonBody(required = false, param = "expediente") Expediente expediente) {
		CambioEstadoExpedienteController.LOGGER.info("[POST] : Cambiar estado expediente");

		if (EstadoExpedienteEnum.ANULADO.getValue() == expediente.getBitacoraExpediente().getEstadoExp().getId()
				&& FaseExpedienteEnum.ANULADO.getValue() == expediente.getBitacoraExpediente().getFaseExp().getId()) {
			this.anulacionesService.anularExpediente(anulacionExpediente, expediente);
		} else if (EstadoExpedienteEnum.RECHAZADO.getValue() == expediente.getBitacoraExpediente().getEstadoExp()
				.getId()
				&& FaseExpedienteEnum.RECHAZADO.getValue() == expediente.getBitacoraExpediente().getFaseExp().getId()) {
			this.expedienteService.rechazarExpCambioEstadoExp(expediente, rechazoExp);
		} else {
			this.expedientesCambioEstadoService.cambiarEstadoExp(expediente);
		}

	}

	/**
	 * Obtiene los datos de la leyenda para el estado/fase del expediente
	 * seleccionado
	 * 
	 * @param idEstadosExp
	 *            Long
	 * @param idFaseExp
	 *            Long
	 * @return List<Leyenda>
	 */
	@RequestMapping(value = "/findConfLeyenda/{idEstadosExp}/{idFaseExp}", method = RequestMethod.GET)
	public @ResponseBody() List<Leyenda> findConfLeyenda(@PathVariable Long idEstadosExp,
			@PathVariable Long idFaseExp) {
		Leyenda leyenda = new Leyenda();
		leyenda.setIdEstadosExp(idEstadosExp);
		leyenda.setIdFaseExp(idFaseExp);

		return this.expedientesCambioEstadoService.findConfLeyenda(leyenda);
	}

}
