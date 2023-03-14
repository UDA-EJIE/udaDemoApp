/*
* Copyright 2023 E.J.I.E., S.A.
*
* Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
* Solo podrá usarse esta obra si se respeta la Licencia.
* Puede obtenerse una copia de la Licencia en
*
* http://ec.europa.eu/idabc/eupl.html
*
* Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
* el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
* SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
* Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
* que establece la Licencia.
*/
package com.ejie.x21a.control;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.x38.hdiv.error.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MvcController {

	private static final Logger logger = LoggerFactory.getLogger(MvcController.class);
	private ObjectMapper objectMapper = new ObjectMapper();

	@RequestMapping(value = "/error")
	public String getErrorView(Model model, HttpServletRequest request) {
		logger.error("Ha ocurrido un error...");
		if(isJsonRequest(request.getContentType())) {
			//otro error// TODO
			return "redirect:/jsonError";
		}
		return "error";
	}
	
	private boolean isJsonRequest(String contentType) {
		MediaType requestContentType = contentType == null ? null : MediaType.valueOf(contentType);
		return MediaType.APPLICATION_JSON.isCompatibleWith(requestContentType);
	}
	
	@RequestMapping(value = "/jsonError")
	public void getJsonErrorView(HttpServletRequest request, HttpServletResponse response) {
		logger.error("Ha ocurrido un error...");
		
		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.setContentType(MediaType.APPLICATION_JSON.toString());
		response.setCharacterEncoding("UTF-8");
		
		ErrorResponse errorResponse = (ErrorResponse) request.getAttribute("VALIDATION_ERROR");
		
		try {
			if(errorResponse == null) {
				response.getWriter().write("Error desconocido");
			}else {
				response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
			}
		} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}