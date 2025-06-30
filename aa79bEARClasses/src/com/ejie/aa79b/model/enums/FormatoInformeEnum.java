package com.ejie.aa79b.model.enums;

import java.io.Serializable;

import com.ejie.aa79b.common.Constants;

public enum FormatoInformeEnum implements Serializable {
	EXCEL(Constants.UNOLONG, Constants.EXTENSION_EXCEL, Constants.CONTENT_TYPE_EXCEL, Constants.TEMPLATE_EXCEL), PDF(
			Constants.DOSLONG, Constants.EXTENSION_PDF, Constants.CONTENT_TYPE_PDF, Constants.TEMPLATE_PDF);

	private Long value;
	private String extension;
	private String contentType;
	private String template;

	private FormatoInformeEnum(Long value, String extension, String contentType, String template) {
		this.value = value;
		this.extension = extension;
		this.contentType = contentType;
		this.template = template;
	}

	/**
	 * 
	 * @return value Long
	 */
	public Long getValue() {
		return this.value;
	}

	/**
	 * @return extension String
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @return contentType String
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @return template String
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * Obtiene un FormatoInformeEnum por la clave
	 * 
	 * @param key
	 *            la clave
	 * @return FormatoInformeEnum
	 * 
	 * @author aresua
	 */
	public static FormatoInformeEnum getById(Long type) {
		for (FormatoInformeEnum item : FormatoInformeEnum.values()) {
			if (type == item.value) {
				return item;
			}
		}
		return null;
	}

}
