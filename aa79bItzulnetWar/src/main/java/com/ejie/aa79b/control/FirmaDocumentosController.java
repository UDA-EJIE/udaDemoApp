package com.ejie.aa79b.control;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.aa79b.model.ListaDocumentoTareaAdjunto;
import com.ejie.aa79b.service.FirmaDocumentosService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;

/**
 * 
 * @author javarona
 */

@Controller
@RequestMapping(value = "/firmadocumentos")

public class FirmaDocumentosController {

	@Autowired()
	private FirmaDocumentosService firmaDocumentosService;

	private static final Logger LOGGER = LoggerFactory.getLogger(FirmaDocumentosController.class);

	@RequestMapping(value = "/firmar", method = RequestMethod.POST)
	public @ResponseBody ListaDocumentoTareaAdjunto firmar(
		@RequestJsonBody ListaDocumentoTareaAdjunto listaDocumentoTareaAdjunto, HttpServletRequest request) {
		
		FirmaDocumentosController.LOGGER.info("[POST ] : firmar");
		this.firmaDocumentosService.firmarDocumento(listaDocumentoTareaAdjunto, request);
		
		return listaDocumentoTareaAdjunto;
	}
	
}
