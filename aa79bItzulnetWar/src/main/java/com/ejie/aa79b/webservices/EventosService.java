package com.ejie.aa79b.webservices;

import java.util.List;

import com.ejie.aa79b.model.Comunicaciones;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.factura.CrearFactura;

public interface EventosService {

	/**
	 * Crear el evento de creación de factura
	 *
	 * @param idFactura
	 * @param factura
	 */
	public void createInvoice(String idFactura, CrearFactura factura);

	/**
	 * Crear el evento de cambio de fecha
	 *
	 * @param expedienteTradRev
	 * @param exp
	 */
	public void createInvoiceCambioFecha(ExpedienteTradRev expedienteTradRev, Expediente exp);

	/**
	 * Crear el evento de cambio de fecha
	 *
	 * @param expediente
	 * @param rutasPif
	 */
	public void createInvoiceCambioEstado(Expediente expediente, List<String> rutasPif, int tipoCierre);

	/**
	 * Crear el evento AA79_ MESSAGE _EVENT
	 * @param comunicacion
	 * @param exp
	 */
	void createComunicacion(Comunicaciones comunicacion, Expediente exp);

}
