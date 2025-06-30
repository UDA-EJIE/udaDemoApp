package com.ejie.aa79b.control;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.Proveedor;
import com.ejie.aa79b.service.ProveedorService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

@Controller()
@RequestMapping (value = "/proveedores")
public class ProveedorController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProveedorController.class);
	
	@Autowired()
	private ProveedorService proveedorService;
	
	/**
	 * Devuelve una lista de beans correspondientes a los valores de filtrados
	 * indicados como parametro.
	 * 
	 * @param codigoEntidad
	 *            codigo de entidad utilizado en la busqueda.
	 * @param tipoEntidad
	 *            tipo de entidad utilizado en la busqueda.
	 * @return List<Proveedor> Lista de objetos correspondientes a la
	 *         busqueda realizada.
	 */
	@RequestMapping(value = "/{codigoEntidad}/{tipoEntidad}", method = RequestMethod.GET)
	public @ResponseBody() List<Proveedor> get(@PathVariable() Integer codigoEntidad, @PathVariable() String tipoEntidad) {
		Proveedor proveedor = new Proveedor();
		Entidad entidad = new Entidad();
		entidad.setCodigo(codigoEntidad);
		entidad.setTipo(tipoEntidad);
		proveedor.setEntidad(entidad);
		List<Proveedor> proveedores = this.proveedorService.findAll(proveedor, null);
		ProveedorController.LOGGER.info("[GET - find_ALL] : Obtener proveedores por codigo y tipo de entidad");
		return proveedores;
	}
	
	/**
	 * Operacion de filtrado del componente RUP_TABLE.
	 * 
	 * @param filterProveedor
	 *            Proveedor Bean que contiene los parametros de filtrado a emplear.
	 * @param jqGridRequestDto
	 *            Dto que contiene los parametros de configuracion propios del
	 *            RUP_TABLE a aplicar en el filtrado.
	 * @return JQGridResponseDto<Proveedor> Dto que contiene el resultado del
	 *         filtrado realizado por el componente RUP_TABLE.
	 */
	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody() JQGridResponseDto<Proveedor> filter(@RequestJsonBody(param = "filter") Proveedor filterProveedor,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		ProveedorController.LOGGER.info("[POST - filter] : Obtener Proveedores");
		return this.proveedorService.filter(filterProveedor, jqGridRequestDto, false);
	}

}
