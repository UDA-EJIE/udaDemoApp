package com.ejie.aa79b.control;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.model.ConfigHorasPrevistas;
import com.ejie.aa79b.model.ConfigHorasPrevistasProveedorExt;
import com.ejie.aa79b.model.DatosBasicos;
import com.ejie.aa79b.model.LimitePalConcor;
import com.ejie.aa79b.model.TipoRelevancia;
import com.ejie.aa79b.model.TiposAviso;
import com.ejie.aa79b.service.ConfigCalculoPresupuestoService;
import com.ejie.aa79b.service.ConfigHorasPrevistasService;
import com.ejie.aa79b.service.ConfigLibroRegistroService;
import com.ejie.aa79b.service.ConfigPerfilSolicitanteService;
import com.ejie.aa79b.service.ConfigPlazoMinimoService;
import com.ejie.aa79b.service.ConfigTextoEmailsService;
import com.ejie.aa79b.service.DatosBasicosService;
import com.ejie.aa79b.service.TipoRelevanciaService;
import com.ejie.aa79b.service.TiposAvisoService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;

@Controller
@RequestMapping(value = "/administracion/datosBasicos")

public class DatosBasicosController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatosBasicosController.class);

	@Autowired()
	private DatosBasicosService datosBasicosService;
	@Autowired()
	private ConfigPlazoMinimoService configPlazoMinimoService;
	@Autowired()
	private ConfigHorasPrevistasService configHorasPrevistasService;
	@Autowired()
	private ConfigCalculoPresupuestoService configCalculoPresupuestoService;
	@Autowired()
	private ConfigPerfilSolicitanteService configPerfilSolicitanteService;
	@Autowired()
	private ConfigTextoEmailsService configTextoEmailsService;
	@Autowired()
	private TiposAvisoService tiposAvisoService;
	@Autowired()
	private TipoRelevanciaService tiposRelevanciaService;
	@Autowired()
	private ConfigLibroRegistroService libroRegistroService;
	
	

	/**
	 * Metodo de presentacion del RUP_TABLE.
	 * 
	 * @param model
	 *            Model
	 * @return String
	 */
	@RequestMapping(value = "/maint", method = RequestMethod.GET)
	public String getFormEdit(Model model) {
		DatosBasicosController.LOGGER.info("[GET - View] : datosbasicos");
		DatosBasicos datosBasicos = this.datosBasicosService.find();		
		model.addAttribute("datosBasicos", datosBasicos);
		List<TiposAviso> tiposAviso = this.tiposAvisoService.findAll(new TiposAviso(), new JQGridRequestDto(null, null, "ID_041", "ASC"));
		model.addAttribute("tiposAviso", tiposAviso);
		TipoRelevancia tipoRelevanciaFilter = new TipoRelevancia();
		tipoRelevanciaFilter.setEstado(Constants.ALTA);
		Long tiposRelevancia = this.tiposRelevanciaService.findAllCount(tipoRelevanciaFilter);
		model.addAttribute("tiposRelevanciaCount", tiposRelevancia);
		return "datosbasicos";
	}
	
	@RequestMapping(value = "/editPlazoMinimo", method = RequestMethod.POST)
	public @ResponseBody() DatosBasicos editPlazoMinimo(@RequestBody() DatosBasicos datosBasicos) {
		return this.configPlazoMinimoService.update(datosBasicos);
	}
	
	@RequestMapping(value = "/editHorasPrevistas", method = RequestMethod.POST)
	public @ResponseBody() Integer editHorasPrevistas(
			@RequestJsonBody(required = true, param = "limitePalConcor") LimitePalConcor limitePalConcor,
			@RequestJsonBody(required = true, param = "listaConfigHoras") String listaConfigHorasStr) {
		DatosBasicosController.LOGGER.info("[POST - View] : editHorasPrevistas");
		ObjectMapper mapper = new ObjectMapper();
		Integer resul = 0;
		List<ConfigHorasPrevistas> listaConfigHoras = new ArrayList<ConfigHorasPrevistas>();
		try {
			listaConfigHoras = mapper.readValue(listaConfigHorasStr,  new TypeReference<List<ConfigHorasPrevistas>>() {	});
			for (ConfigHorasPrevistas configHorasPrevistas: listaConfigHoras) {
				configHorasPrevistas.setId(Constants.ID_DATOS_BASICOS);
			}
			resul = this.configHorasPrevistasService.editHorasPrevistas(limitePalConcor,listaConfigHoras);
		} catch (Exception e) {
			DatosBasicosController.LOGGER.info("[POST - View] : editHorasPrevistas: "+ e);
		}
		return resul;
	}
	
	@RequestMapping(value = "/editHorasPrevistasProveedExt", method = RequestMethod.POST)
	public @ResponseBody() Integer editHorasPrevistasProveedExt(
			@RequestJsonBody(required = true, param = "limitePalConcor") LimitePalConcor limitePalConcor,
			@RequestJsonBody(required = true, param = "listaConfigHoras") String listaConfigHorasStr) {
		DatosBasicosController.LOGGER.info("[POST - View] : editHorasPrevistas");
		ObjectMapper mapper = new ObjectMapper();
		Integer resul = 0;
		List<ConfigHorasPrevistasProveedorExt> listaConfigHoras = new ArrayList<ConfigHorasPrevistasProveedorExt>();
		try {
			listaConfigHoras = mapper.readValue(listaConfigHorasStr,  new TypeReference<List<ConfigHorasPrevistasProveedorExt>>() {	});
			for (ConfigHorasPrevistasProveedorExt configHorasPrevistas: listaConfigHoras) {
				configHorasPrevistas.setId(Constants.ID_DATOS_BASICOS);
			}
			resul = this.configHorasPrevistasService.editHorasPrevistasProveedorExterno(limitePalConcor,listaConfigHoras);
		} catch (Exception e) {
			DatosBasicosController.LOGGER.info("[POST - View] : editHorasPrevistas");
		}
		return resul;
	}
	
	@RequestMapping(value = "/editCalculoPresupuesto", method = RequestMethod.POST)
	public @ResponseBody() DatosBasicos editCalculoPresupuesto(@RequestBody() DatosBasicos datosBasicos) {
		this.configCalculoPresupuestoService.update(datosBasicos.getConfigCalculoPresupuesto());
		return datosBasicos;
	}
	
	@RequestMapping(value = "/editPerfilSolicitante", method = RequestMethod.POST)
	public @ResponseBody() DatosBasicos editPerfilSolicitante(@RequestBody() DatosBasicos datosBasicos) {
		this.configPerfilSolicitanteService.update(datosBasicos.getConfigPerfilSolicitante());
		return datosBasicos;
	}
	@RequestMapping(value = "/editEmailsAviso", method = RequestMethod.POST)
	public @ResponseBody() DatosBasicos editEmailsAviso(@RequestBody() DatosBasicos datosBasicos) {
		this.configTextoEmailsService.update(datosBasicos);
		return datosBasicos;
	}
	@RequestMapping(value = "/editLibroRegistro", method = RequestMethod.POST)
	public @ResponseBody() DatosBasicos editLibroRegistro(@RequestBody() DatosBasicos datosBasicos) {
		this.libroRegistroService.update(datosBasicos.getLibroRegistro());
		return datosBasicos;
	}
}
