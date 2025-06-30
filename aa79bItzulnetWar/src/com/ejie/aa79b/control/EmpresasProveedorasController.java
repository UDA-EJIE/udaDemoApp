package com.ejie.aa79b.control;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.aa79b.model.EmpresasProveedoras;
import com.ejie.aa79b.model.enums.TipoEntidadEnum;
import com.ejie.aa79b.service.EmpresasProveedorasService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

@Controller()
@RequestMapping(value = "/administracion/empresasproveedoras")
public class EmpresasProveedorasController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmpresasProveedorasController.class);

	@Autowired()
	private EmpresasProveedorasService empresasProveedorasService;

	/**
	 * Devuelve una lista de beans correspondientes a los valores de filtrados
	 * indicados en el objeto pasado como parametro.
	 *
	 * @param filterEmpresasProveedoras
	 *            EmpresasProveedoras Objeto que contiene los parametros de
	 *            filtrado utilizados en la busqueda.
	 * @return List<EmpresasProveedoras> Lista de objetos correspondientes a la
	 *         busqueda realizada.
	 */
	@RequestMapping(value = "/suggest", method = RequestMethod.GET)
	public @ResponseBody() List<EmpresasProveedoras> getAll(
			@ModelAttribute EmpresasProveedoras filterEmpresasProveedoras) {
		EmpresasProveedorasController.LOGGER.info("[GET - find_ALL] : Obtener EmpresasProveedoras por filtro");
		return this.empresasProveedorasService.findAll(filterEmpresasProveedoras, null);
	}

	/**
	 * Operacion CRUD Read. Devuelve el bean correspondiente al identificador
	 * indicado.
	 * 
	 * @param empresasProveedoras
	 *            EmpresasProveedoras Objeto que contiene los parametros de
	 *            filtrado utilizados en la busqueda.
	 * @return EmpresasProveedoras Objeto correspondiente al identificador
	 *         indicado.
	 */
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public @ResponseBody EmpresasProveedoras get(@RequestJsonBody EmpresasProveedoras empresasProveedoras) {
		EmpresasProveedorasController.LOGGER.info("[GET - findBy_PK] : Obtener EmpresasProveedoras por PK");
		return this.empresasProveedorasService.find(empresasProveedoras);
	}

	/*
	 * METODOS COMPONENTE RUP_TABLE
	 * 
	 */

	/**
	 * Metodo de presentacion del RUP_TABLE.
	 * 
	 * @param model
	 *            Model
	 * @return String
	 */
	@RequestMapping(value = "/maint", method = RequestMethod.GET)
	public String getFormEdit() {
		EmpresasProveedorasController.LOGGER.info("[GET - View] : Empresas proveedoras");
		return "lotes";
	}

	/**
	 * Operacion de filtrado del componente RUP_TABLE.
	 * 
	 * @param filterEmpresasProveedoras
	 *            EmpresasProveedoras Bean que contiene los parametros de
	 *            filtrado a emplear.
	 * @param jqGridRequestDto
	 *            Dto que contiene los parametros de configuracion propios del
	 *            RUP_TABLE a aplicar en el filtrado.
	 * @return JQGridResponseDto<EmpresasProveedoras> Dto que contiene el
	 *         resultado del filtrado realizado por el componente RUP_TABLE.
	 */
	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody() JQGridResponseDto<EmpresasProveedoras> filter(
			@RequestJsonBody(param = "filter") EmpresasProveedoras filterEmpresasProveedoras,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		EmpresasProveedorasController.LOGGER.info("[POST - filter] : Obtener EmpresasProveedoras");
		return this.empresasProveedorasService.filter(filterEmpresasProveedoras, jqGridRequestDto, false);
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/empresasProveedorasConTareasNoPagadas", method = RequestMethod.GET)
	public @ResponseBody List<EmpresasProveedoras> getEmpresasProvConTareasNoPagadas() {
		return empresasProveedorasService.getEmpresasProveedorasConTareasNoPagadas(true);
	}

	@RequestMapping(value = "/empresasProveedorasConTareasNoPagadasYPagadas", method = RequestMethod.GET)
	public @ResponseBody List<EmpresasProveedoras> getEmpresasProvConTareasNoPagadasYPagadas() {
		return empresasProveedorasService.getEmpresasProveedorasConTareasNoPagadas(false);
	}

	@RequestMapping(value = "/findEmpresasProveedoras", method = RequestMethod.GET)
	public @ResponseBody() List<EmpresasProveedoras> getEmpresasProveedoras(@RequestParam() String q) {
		EmpresasProveedorasController.LOGGER
				.info("[GET - find_ALL] : findEmpresasProveedoras Obtener empresas proveedoras por filtro");
		final EmpresasProveedoras empresasProveedoras = new EmpresasProveedoras();
		empresasProveedoras.setDescEu(q);
		empresasProveedoras.setTipo(TipoEntidadEnum.EMPRESA.getValue());
		return this.empresasProveedorasService.getEmpresasProveedoras(empresasProveedoras);
	}

}
