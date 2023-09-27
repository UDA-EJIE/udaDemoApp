package com.ejie.x21a.model;

import java.math.BigDecimal;

public class ProvinciaComarcaLocalidadDTO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private BigDecimal codeProvincia;
	
	private BigDecimal codeComarca;
	
    private BigDecimal codeLocalidad;
	
	public BigDecimal getCodeProvincia() {
		return codeProvincia;
	}
	public void setCodeProvincia(BigDecimal codeProvincia) {
		this.codeProvincia = codeProvincia;
	}
	public BigDecimal getCodeComarca() {
		return codeComarca;
	}
	public void setCodeComarca(BigDecimal codeComarca) {
		this.codeComarca = codeComarca;
	}
	public BigDecimal getCodeLocalidad() {
		return codeLocalidad;
	}
	public void setCodeLocalidad(BigDecimal codeLocalidad) {
		this.codeLocalidad = codeLocalidad;
	}
}
