package com.ejie.aa79b.model.webservices;

import com.ejie.aa79b.model.DatosSalidaWS;

public class Aa79bTablaWebService<T> extends DatosSalidaWS {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Aa79bJaxbList<T> lista;
	protected Long page;
    protected Long recordNum;
    protected Long rows;
    protected String sidx;
    protected String sord;
    
	public Aa79bTablaWebService() {
		//Constructor vacio
	}

	/**
	 * @return the lista
	 */
	public Aa79bJaxbList<T> getLista() {
		return lista;
	}

	/**
	 * @param lista the lista to set
	 */
	public void setLista(Aa79bJaxbList<T> lista) {
		this.lista = lista;
	}

	/**
	 * @return the page
	 */
	public Long getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Long page) {
		this.page = page;
	}

	/**
	 * @return the recordNum
	 */
	public Long getRecordNum() {
		return recordNum;
	}

	/**
	 * @param recordNum the recordNum to set
	 */
	public void setRecordNum(Long recordNum) {
		this.recordNum = recordNum;
	}

	/**
	 * @return the rows
	 */
	public Long getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(Long rows) {
		this.rows = rows;
	}

	/**
	 * @return the sidx
	 */
	public String getSidx() {
		return sidx;
	}

	/**
	 * @param sidx the sidx to set
	 */
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	/**
	 * @return the sord
	 */
	public String getSord() {
		return sord;
	}

	/**
	 * @param sord the sord to set
	 */
	public void setSord(String sord) {
		this.sord = sord;
	}	
}
