package com.ejie.aa79b.control;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ejie.aa79b.model.Auditoria;
import com.ejie.aa79b.model.AuditoriaCampoSeccionExpediente;
import com.ejie.aa79b.model.AuditoriaDocumentoSeccionExpediente;
import com.ejie.aa79b.model.AuditoriaSeccionExpediente;
import com.ejie.aa79b.security.Usuario;
import com.ejie.aa79b.service.AuditoriaService;
import com.ejie.aa79b.service.DocumentosGeneralService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 *
 * @author eaguirresarobe
 *
 */
@Controller
@RequestMapping(value = "/auditoria")
public class AuditoriaController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultasController.class);

	@Autowired()
	private AuditoriaService auditoriaService;
	@Autowired
	private DocumentosGeneralService documentosGeneralService;

	@RequestMapping(value = "/maint", method = RequestMethod.GET)
	public String getAuditoriasView(Model model) {
		AuditoriaController.LOGGER.info("[GET - View] : getAuditoriasView");
		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		model.addAttribute("usuarioConectado", credentials.getNif());
		return "auditoria";
	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<Auditoria> getAuditorias(
			@RequestJsonBody(param = "filter") Auditoria auditoriaFilter,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		AuditoriaController.LOGGER.info("[POST - filter] : getAuditorias");
		return this.auditoriaService.filterAuditoria(auditoriaFilter, jqGridRequestDto, false);
	}

	@RequestMapping(value = "/detalle/{anyo}/{numExp}/{idTarea}/{idTareaAuditar}", method = RequestMethod.GET)
	public String getDetalleAuditoriaView(Model model, @PathVariable Long anyo,@PathVariable Integer numExp,@PathVariable BigDecimal idTarea,@PathVariable BigDecimal idTareaAuditar) {
		AuditoriaController.LOGGER.info("[GET - View] : getDetalleAuditoriaView");
		this.auditoriaService.getDatosDetalleAuditoria(model, anyo, numExp, idTarea, idTareaAuditar);
		return "auditoriadetalle";
	}

	@RequestMapping(value = "/detalle/camposSeccion/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<AuditoriaCampoSeccionExpediente> getCamposSeccion(
			@RequestJsonBody(param = "filter") AuditoriaSeccionExpediente seccionFilter,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		AuditoriaController.LOGGER.info("[POST - filter] : getCamposSeccion");
		return this.auditoriaService.filterCamposSeccion(seccionFilter, jqGridRequestDto, false);
	}

	@RequestMapping(value = "/detalle/documentosSeccion/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<AuditoriaDocumentoSeccionExpediente> getDocumentosSeccion(
			@RequestJsonBody(param = "filter") AuditoriaDocumentoSeccionExpediente seccionFilter,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		AuditoriaController.LOGGER.info("[POST - filter] : getDocumentosSeccion");
		return this.auditoriaService.filterDocumentosSeccion(seccionFilter, jqGridRequestDto, false);
	}

	@RequestMapping(value = "/detalle/eliminarDocumento", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Integer eliminarDocumento(@RequestJsonBody  AuditoriaDocumentoSeccionExpediente docAuditoria) {
		AuditoriaController.LOGGER.info(
				"[DELETE - eliminarDocumento] : Eliminar documento asociado a auditoria");
		return this.auditoriaService.eliminarDocAuditoria(docAuditoria);
	}

	@RequestMapping(value = "/detalle/anyadirDocumento", method = RequestMethod.POST)
	public @ResponseBody() AuditoriaDocumentoSeccionExpediente anyadirDocumento(@RequestBody AuditoriaDocumentoSeccionExpediente documentoAuditoria) {
		AuditoriaController.LOGGER.info("[POST] : subirDocumento");
		return this.auditoriaService.anyadirDocAuditoria(documentoAuditoria);
	}

    @RequestMapping(value = "/detalle/guardarDatosAuditoria", method = RequestMethod.POST)
    public @ResponseBody Auditoria guardarDatosAuditoria(@RequestJsonBody Auditoria auditoria) {
    	AuditoriaController.LOGGER.info("[POST] : guardarDatosAuditoria");
    	 this.auditoriaService.guardarDatosAuditoria(auditoria);
    	 return auditoria ;
    }
}
