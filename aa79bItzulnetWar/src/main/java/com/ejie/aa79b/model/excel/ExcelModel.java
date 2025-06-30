package com.ejie.aa79b.model.excel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dlopez2
 * 
 */
public final class ExcelModel {
	/**
	 * Lista de hojas del excel
	 */
	private List<ExcelHoja> listaHojas = new ArrayList<ExcelHoja>();
	/**
	 * Nombre del excel
	 */
	private String nombre = "";

	/**
	 * @return String
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * @param nombre
	 *            String
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return List
	 */
	public List<ExcelHoja> getListaHojas() {
		return this.listaHojas;
	}

	/**
	 * @param listaHojas
	 *            List
	 */
	public void setListaHojas(List<ExcelHoja> listaHojas) {
		this.listaHojas = listaHojas;
	}

}
