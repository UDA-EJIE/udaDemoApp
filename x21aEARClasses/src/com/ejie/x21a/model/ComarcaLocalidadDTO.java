package com.ejie.x21a.model;

import java.math.BigDecimal;

public class ComarcaLocalidadDTO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;


	private BigDecimal codeComarcaLocalidad;

	private BigDecimal codeProvincia;

	public BigDecimal getCodeComarcaLocalidad() {
		return codeComarcaLocalidad;
	}
	public void setCodeComarcaLocalidad(BigDecimal codeComarcaLocalidad) {
		this.codeComarcaLocalidad = codeComarcaLocalidad;
	}
	public BigDecimal getCodeProvincia() {
		return codeProvincia;
	}
	public void setCodeProvincia(BigDecimal codeProvincia) {
		this.codeProvincia = codeProvincia;
	}
}
