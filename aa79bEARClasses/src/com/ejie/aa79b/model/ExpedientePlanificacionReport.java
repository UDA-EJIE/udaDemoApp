package com.ejie.aa79b.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExpedientePlanificacionReport implements Serializable {

	private static final long serialVersionUID = 1L;
	private ExpedientePlanificacion expedientePlanificacion;
	private List<ExpTareasResumen> listaTareas = new ArrayList<ExpTareasResumen>();

	/**
	 * @return the expedientePlanificacion
	 */
	public ExpedientePlanificacion getExpedientePlanificacion() {
		return expedientePlanificacion;
	}

	/**
	 * @param expedientePlanificacion
	 *            the expedientePlanificacion to set
	 */
	public void setExpedientePlanificacion(ExpedientePlanificacion expedientePlanificacion) {
		this.expedientePlanificacion = expedientePlanificacion;
	}

	/**
	 * @return the listaTareas
	 */
	public List<ExpTareasResumen> getListaTareas() {
		return listaTareas;
	}

	/**
	 * @param listaTareas
	 *            the listaTareas to set
	 */
	public void setListaTareas(List<ExpTareasResumen> listaTareas) {
		this.listaTareas = listaTareas;
	}

}
