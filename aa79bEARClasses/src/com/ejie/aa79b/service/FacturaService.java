package com.ejie.aa79b.service;

import java.util.List;

import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.Factura;
import com.ejie.aa79b.model.FacturasExpediente;
import com.ejie.aa79b.model.factura.CrearFactura;
import com.ejie.aa79b.model.factura.Tercero;

public interface FacturaService extends GenericoService<Factura> {

	/**
	 * Obtiene el id de la siguiente factura
	 * 
	 * @return Long
	 */
	Long getIdFacturaSgt();

	/**
	 * Comprueba si existe partida presupuestaria para el concepto indicado
	 * 
	 * @param concepto
	 * @return Long
	 */
	Long comprobarPartidaPresupuestaria(String concepto, String tipoPartida);

	/**
	 * Registra los datos asociados a la generación de la factura en las tablas A4 y
	 * A5
	 * 
	 * @param factura         Factura
	 * @param listFacturasExp List<FacturasExpediente>
	 */
	void generarFactura(Factura factura, List<FacturasExpediente> listFacturasExp);

	/**
	 * Obtiene los datos de la entidad/contacto al que se va a facturar
	 * 
	 * @param idEntidad   Integer
	 * @param tipoEntidad String
	 * @param dniContacto String
	 * @return Tercero
	 */
	Tercero generarDatosTercero(Entidad entidad, String dniContacto);

	/**
	 * Rellena la plantilla de la factura
	 * 
	 * @param tipoExpFactura  String
	 * @param listFacturasExp List<FacturasExpediente>
	 * @param crearFactura    CrearFactura
	 * @param factura         Factura
	 * @return CrearFactura
	 */
	CrearFactura rellenarPlantilla(String tipoExpFactura, List<FacturasExpediente> listFacturasExp,
			CrearFactura crearFactura, Factura factura) throws Exception;

}
