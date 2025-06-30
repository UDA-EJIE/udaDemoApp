package com.ejie.aa79b.control;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.model.CategExp;
import com.ejie.aa79b.model.ListaCategExp;
import com.ejie.aa79b.model.MetadatosBusqueda;
import com.ejie.aa79b.model.MetadatosExpedientes;
import com.ejie.aa79b.service.CategExpService;
import com.ejie.aa79b.service.MetadatosBusquedaService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

@Controller
@RequestMapping(value = "/categorizacionexpediente")
public class CategorizacionExpedienteController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategorizacionExpedienteController.class);

	@Autowired
	CategExpService categExpService;

	@Autowired
	MetadatosBusquedaService metadatosService;

	/**
	 * Devuelve una lista de beans correspondientes a los valores de filtrados
	 * indicados en el objeto pasado como parametro.
	 *
	 * @param filterCategExp
	 *            CategExp Objeto que contiene los parametros de filtrado
	 *            utilizados en la busqueda.
	 * @return List<ReceptorAutorizado> Lista de objetos correspondientes a la
	 *         busqueda realizada.
	 */
	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<CategExp> getAll(@RequestJsonBody(param = "filter") CategExp filterCategExp,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		CategorizacionExpedienteController.LOGGER.info("[GET - find_ALL] : Obtener ReceptorAutorizado por filtro");
		return this.categExpService.filter(filterCategExp, jqGridRequestDto, false);
	}

	/**
	 * Devuelve una lista de beans correspondientes a los valores de filtrados
	 * indicados en el objeto pasado como parametro.
	 *
	 * @param filterCategExp
	 *            CategExp Objeto que contiene los parametros de filtrado
	 *            utilizados en la busqueda.
	 * @return List<ReceptorAutorizado> Lista de objetos correspondientes a la
	 *         busqueda realizada.
	 */
	@RequestMapping(value = "/getAllMetadatos", method = RequestMethod.POST)
	public @ResponseBody List<MetadatosBusqueda> cargarCategExp() {
		CategorizacionExpedienteController.LOGGER.info("[GET - find_ALL] : Obtener CategExp");
		MetadatosBusqueda filterMetadatosBusqueda = new MetadatosBusqueda();
		return this.metadatosService.findAll(filterMetadatosBusqueda, null);
	}

	/**
	 *
	 * @param anyo
	 *            Long
	 * @param numExp
	 *            Integer
	 * @return ListaCategExp
	 */
	@RequestMapping(value = "getMetadatosIds/{anyo}/{numExp}", method = RequestMethod.GET)
	public @ResponseBody ListaCategExp get(@PathVariable Long anyo, @PathVariable Integer numExp) {
		CategExp categExpFilter = new CategExp();
		categExpFilter.setAnyo(anyo);
		categExpFilter.setNumExp(numExp);
		List<CategExp> lista = new ArrayList<CategExp>();

		JQGridRequestDto jqGridRequestDto = new JQGridRequestDto(null, null, "DESCMETADATOEU",
				DaoConstants.ASC);

		lista = this.categExpService.findAll(categExpFilter, jqGridRequestDto);
		ListaCategExp listaCategExp = new ListaCategExp();
		if (lista != null && !lista.isEmpty()) {
			listaCategExp.setListaCategExp(lista);
		}
		return listaCategExp;
	}

	/**
	 *
	 * @param listaCategExp
	 *            ListaCategExp
	 * @return ListaCategExp
	 */
	@RequestMapping(value = "/comprobarYGuardarOEliminar", method = RequestMethod.POST)
	public @ResponseBody ListaCategExp comprobarYGuardarOEliminar(@RequestJsonBody ListaCategExp listaCategExp) {
		CategorizacionExpedienteController.LOGGER.info("[POST - comprobarYGuardarOEliminar ");
		return this.categExpService.comprobarYGuardarOEliminar(listaCategExp);
	}

	/**
	 *
	 * @param listaCategExp
	 *            ListaCategExp
	 * @return ListaCategExp Objeto correspondiente al identificador indicado.
	 */
	@RequestMapping(value = "/eliminarAllCategExp", method = RequestMethod.POST)
	public @ResponseBody ListaCategExp eliminarAllCategExp(@RequestJsonBody ListaCategExp listaCategExp) {
		CategorizacionExpedienteController.LOGGER.info("[POST - eliminarAllCategExp ");
		return this.categExpService.eliminarAllCategExp(listaCategExp);
	}

	/**
	 *
	 * @param metadatosExpedientes
	 *            MetadatosExpedientes
	 * @return Integer
	 */
	@RequestMapping(value = "/guardarYOEliminarMetadatos", method = RequestMethod.POST)
	public @ResponseBody Integer guardarYOEliminarMetadatos(
			@RequestJsonBody MetadatosExpedientes metadatosExpedientes) {
		CategorizacionExpedienteController.LOGGER.info("[POST - guardarYOEliminarMetadatos ");
		// eliminar relacion de expedientes con metadatos de BBDD
		if (metadatosExpedientes.getListaExpedientes().getListaExpediente().size()> 1) {
			//eliminamos los seleccionados que ya pueda tener, para insertarlos luego
			this.categExpService.eliminarMetadatosSeleccionadosDeExpedientes(metadatosExpedientes);
		}else {
			//Eliminamos todos los que tiene
			this.categExpService.eliminarMetadatosDeExpedientes(metadatosExpedientes.getListaExpedientes());
		}
		// guardar nueva relacion de expedientes con metadatos
		return this.categExpService.guardarYOEliminarMetadatos(metadatosExpedientes);
	}

	/**
	 *
	 * @param metadatosExpedientes
	 *            MetadatosExpedientes
	 * @return Integer
	 */
	@RequestMapping(value = "/eliminarMetadatos", method = RequestMethod.POST)
	public @ResponseBody Integer eliminarMetadatos(@RequestJsonBody MetadatosExpedientes metadatosExpedientes) {
		CategorizacionExpedienteController.LOGGER.info("[POST - eliminarMetadatos ");
		// eliminar relacion de expedientes con metadatos de BBDD
		return this.categExpService.eliminarMetadatosDeExpedientes(metadatosExpedientes.getListaExpedientes());
	}

}
