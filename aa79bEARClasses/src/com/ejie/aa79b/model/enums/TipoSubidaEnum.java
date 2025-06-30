package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author javarona
 *
 */
public enum TipoSubidaEnum implements Serializable {
	DOC_EXPEDIENTE(1, "ID_DOC_056", "OID_FICHERO_056", "CONTENT_TYPE_056", "NOMBRE_FICHERO_056", "TAMANO_DOC_056",
			"AA79B56S01"), DOC_TAREAS(2, "ID_FICHERO_088", "OID_FICHERO_088", "CONTENT_TYPE_FICHERO_088",
					"NOMBRE_FICHERO_088", "TAMANO_FICHERO_088", "AA79B88S01");

	private int value;
	private String idFichero;
	private String oidCampo;
	private String contentTypeCampo;
	private String nombreCampo;
	private String tamanoCampo;
	private String tabla;

	private TipoSubidaEnum(int value, String idFichero, String oidCampo, String contentTypeCampo, String nombreCampo,
			String tamanoCampo, String tabla) {
		this.value = value;
		this.idFichero = idFichero;
		this.oidCampo = oidCampo;
		this.contentTypeCampo = contentTypeCampo;
		this.nombreCampo = nombreCampo;
		this.tamanoCampo = tamanoCampo;
		this.tabla = tabla;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return the oidCampo
	 */
	public String getOidCampo() {
		return oidCampo;
	}

	/**
	 * @return the contentTypeCampo
	 */
	public String getContentTypeCampo() {
		return contentTypeCampo;
	}

	/**
	 * @return the nombreCampo
	 */
	public String getNombreCampo() {
		return nombreCampo;
	}

	/**
	 * @return the tabla
	 */
	public String getTabla() {
		return tabla;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @param oidCampo
	 *            the oidCampo to set
	 */
	public void setOidCampo(String oidCampo) {
		this.oidCampo = oidCampo;
	}

	/**
	 * @param contentTypeCampo
	 *            the contentTypeCampo to set
	 */
	public void setContentTypeCampo(String contentTypeCampo) {
		this.contentTypeCampo = contentTypeCampo;
	}

	/**
	 * @param nombreCampo
	 *            the nombreCampo to set
	 */
	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}

	/**
	 * @param tabla
	 *            the tabla to set
	 */
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	/**
	 * Obtiene un TipoSubidaEnum por la clave
	 * 
	 * @param key
	 *            la clave
	 * @return TipoSubidaEnum
	 * 
	 * @author aresua
	 */
	public static TipoSubidaEnum getById(int id) {
		for (TipoSubidaEnum item : TipoSubidaEnum.values()) {
			if (id == item.getValue()) {
				return item;
			}
		}
		return null;
	}

	/**
	 * @return the idFichero
	 */
	public String getIdFichero() {
		return idFichero;
	}

	/**
	 * @param idFichero
	 *            the idFichero to set
	 */
	public void setIdFichero(String idFichero) {
		this.idFichero = idFichero;
	}

	/**
	 * @return the tamanoCampo
	 */
	public String getTamanoCampo() {
		return tamanoCampo;
	}

	/**
	 * @param tamanoCampo
	 *            the tamanoCampo to set
	 */
	public void setTamanoCampo(String tamanoCampo) {
		this.tamanoCampo = tamanoCampo;
	}
}