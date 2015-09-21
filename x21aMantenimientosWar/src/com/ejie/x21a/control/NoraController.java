package com.ejie.x21a.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.x21a.model.NoraAutonomia;
import com.ejie.x21a.model.NoraCalle;
import com.ejie.x21a.model.NoraMunicipio;
import com.ejie.x21a.model.NoraPais;
import com.ejie.x21a.model.NoraProvincia;
import com.ejie.x21a.service.NoraAutonomiaService;
import com.ejie.x21a.service.NoraCalleService;
import com.ejie.x21a.service.NoraMunicipioService;
import com.ejie.x21a.service.NoraPaisService;
import com.ejie.x21a.service.NoraProvinciaService;

@Controller
@RequestMapping(value = "/nora")
public class NoraController {
	
	
	@Autowired 
	private NoraPaisService paisService;
	
	@Autowired 
	private NoraProvinciaService provinciaService;
	
	@Autowired 
	private NoraAutonomiaService autonomiaService;
	
	@Autowired 
	private NoraMunicipioService municipioService;
	
	@Autowired 
	private NoraCalleService calleService;
	
	
	@RequestMapping(value = "pais", method=RequestMethod.GET)
	public @ResponseBody List<NoraPais> getPaises() {
		List<NoraPais> findAll = paisService.findAll(null, null);
		return findAll;
	}
	
	@RequestMapping(value = "autonomia", method=RequestMethod.GET)
	public @ResponseBody List<NoraAutonomia> getAutonomias() {
		List<NoraAutonomia> findAll = autonomiaService.findAll(null, null);
		return findAll;
	}
	
	@RequestMapping(value = "provincia", method=RequestMethod.GET)
	public @ResponseBody List<NoraProvincia> getProvincias(
			@RequestParam(value="autonomia.id", required=false) String autonomiaId) {
		
		NoraProvincia filtroProvincia = new NoraProvincia();
		NoraAutonomia filtroAutonomia = new NoraAutonomia();
		filtroAutonomia.setId(autonomiaId);
		filtroProvincia.setAutonomia(filtroAutonomia);
		
		List<NoraProvincia> findAll = provinciaService.findAll(filtroProvincia, null);
		return findAll;
	}
	
	@RequestMapping(value = "municipio", method=RequestMethod.GET)
	public @ResponseBody List<NoraMunicipio> getMunicipios(
			@RequestParam(value="provincia.id", required=false) String provinciaId) {
		
		NoraMunicipio filterMunicipio = new NoraMunicipio();
		filterMunicipio.setProvinciaId(provinciaId);
		List<NoraMunicipio> findAll = municipioService.findAll(filterMunicipio, null);
		return findAll;
	}
	
//	@RequestMapping(value = "municipio", method=RequestMethod.GET)
//	public @ResponseBody List<NoraMunicipio> getMunicipios(
//			@RequestParam(value = "q", required = true) String q,
//			@RequestParam(value = "c", required = true) Boolean c) {
//		
//		NoraMunicipio filterMunicipio = new NoraMunicipio();
//		filterMunicipio.setDsO(q);
//		List<NoraMunicipio> findAll = municipioService.findAllLike(filterMunicipio, null, !c);
//		return findAll;
//	}
	
	@RequestMapping(value = "calle", method=RequestMethod.GET)
	public @ResponseBody List<NoraCalle> getCalles(
			@RequestParam(value = "q", required = true) String q,
			@RequestParam(value = "c", required = true) Boolean c) {
		
		NoraCalle filterCalle = new NoraCalle();
		filterCalle.setDsO(q);
		List<NoraCalle> findAll = calleService.findAllLike(filterCalle, null, !c);
		return findAll;
	}

}
