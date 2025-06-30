package com.ejie.aa79b.model;

import java.io.Serializable;

public class EstudioEstimado implements Serializable {

	private static final long serialVersionUID = 1L;

	private String recurso;
	private int numRecursos;
	private String costeHoras;
	private String costeHorasRecurso;

	public EstudioEstimado() {
		// Constructor vacio
	}

	/**
	 * @return the recurso
	 */
	public String getRecurso() {
		return recurso;
	}

	/**
	 * @param recurso
	 *            the recurso to set
	 */
	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	/**
	 * @return the numRecursos
	 */
	public int getNumRecursos() {
		return numRecursos;
	}

	/**
	 * @param numRecursos
	 *            the numRecursos to set
	 */
	public void setNumRecursos(int numRecursos) {
		this.numRecursos = numRecursos;
	}

	/**
	 * @return the costeHoras
	 */
	public String getCosteHoras() {
		return costeHoras;
	}

	/**
	 * @param costeHoras
	 *            the costeHoras to set
	 */
	public void setCosteHoras(String costeHoras) {
		this.costeHoras = costeHoras;
	}

	/**
	 * @return the costeHorasRecurso
	 */
	public String getCosteHorasRecurso() {
		return costeHorasRecurso;
	}

	/**
	 * @param costeHorasRecurso
	 *            the costeHorasRecurso to set
	 */
	public void setCosteHorasRecurso(String costeHorasRecurso) {
		this.costeHorasRecurso = costeHorasRecurso;
	}

}
