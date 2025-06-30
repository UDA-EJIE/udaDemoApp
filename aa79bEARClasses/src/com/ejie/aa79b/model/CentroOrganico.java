package com.ejie.aa79b.model;

import java.io.Serializable;

public class CentroOrganico implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private String descAmpEs;
	private String descAmpEu;
	private String descAmp;

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescAmpEs() {
		return this.descAmpEs;
	}

	public void setDescAmpEs(String descEsAmp) {
		this.descAmpEs = descEsAmp;
	}

	public String getDescAmpEu() {
		return this.descAmpEu;
	}

	public void setDescAmpEu(String descEuAmp) {
		this.descAmpEu = descEuAmp;
	}

	public String getDescAmp() {
		return this.descAmp;
	}

	public void setDescAmp(String descAmp) {
		this.descAmp = descAmp;
	}

}
