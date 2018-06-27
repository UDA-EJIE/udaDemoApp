/*
* Copyright 2012 E.J.I.E., S.A.
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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.x21a.model.NoraCalle;
import com.ejie.x21a.model.NoraMunicipio;
import com.ejie.x21a.model.NoraProvincia;
import com.ejie.x21a.service.NoraCalleService;
import com.ejie.x21a.service.NoraMunicipioService;
import com.ejie.x21a.service.NoraProvinciaService;

/**
 * ExperimentalController
 * 
 * @author UDA
 */
@Controller
@RequestMapping(value = "/integracion")
public class IntegracionController {

	//private static final Logger logger = LoggerFactory.getLogger(IntegracionController.class);

	//z-index
	@RequestMapping(value = "z-index", method = RequestMethod.GET)
	public String getZIndex(Model model) {
		return "z-index";
	}
	
	//Nora
	@RequestMapping(value = "nora", method = RequestMethod.GET)
	public String getNora(Model model) {
		return "nora";
	}
	//Tiny
	@RequestMapping(value = "tiny", method = RequestMethod.GET)
	public String getTiny(Model model) {
		return "tiny";
	}
	
	//Webdav
	@RequestMapping(value = "webdav", method = RequestMethod.GET)
	public String getWebdav(Model model) {
		return "webdav";
	}
	
	//PIF
	@RequestMapping(value = "pif", method = RequestMethod.GET)
	public String getPIf(Model model) {
		return "pif";
	}
	
	/**
	 * NORA
	 */
		
	@Autowired 
	private NoraProvinciaService provinciaService;
	
	@Autowired 
	private NoraMunicipioService municipioService;
	
	@Autowired 
	private NoraCalleService calleService;
	
	
	@RequestMapping(value = "comboEnlazado/remoteEnlazadoProvincia", method=RequestMethod.GET)
	public @ResponseBody List<NoraProvincia> getEnlazadoProvincia() {
		try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
		List<NoraProvincia> findAll = provinciaService.findAll(null, null);
		return findAll;
	}
	
	@RequestMapping(value = "comboEnlazado/remoteEnlazadoMunicipio", method=RequestMethod.GET)
	public @ResponseBody List<NoraMunicipio> getEnlazadoMunicipio(
			@RequestParam(value = "provincia", required = false) String provincia_code) {
		//Convertir parámetros en entidad para búsqueda
		NoraMunicipio municipio = new NoraMunicipio();
		municipio.setProvinciaId(provincia_code);
		try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
		return municipioService.findAll(municipio, null);
	}	
	@RequestMapping(value = "autocomplete/calleRemote", method = RequestMethod.GET)
	public @ResponseBody List<NoraCalle> getCalleRemoteAutocomplete(
		@RequestParam(value = "q", required = true) String q,
		@RequestParam(value = "c", required = true) Boolean c,
		@RequestParam(value = "provinciaId", required = false) String provinciaId,
		@RequestParam(value = "municipioId", required = false) String municipioId) {
		// Filtro
		NoraCalle calle = new NoraCalle();
		calle.setDsO(q);
		calle.setMunicipioId(municipioId);
		calle.setProvinciaId(provinciaId);
		List<NoraCalle> findAllLike = calleService.findAllLike(calle, null, !c);
		return findAllLike;
	}
}
