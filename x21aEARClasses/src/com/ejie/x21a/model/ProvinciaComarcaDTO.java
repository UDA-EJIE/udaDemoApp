package com.ejie.x21a.model;

import java.math.BigDecimal;

public class ProvinciaComarcaDTO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private BigDecimal codeProvincia;

	private BigDecimal codeComarca;

	private BigDecimal codeAuxiliar;
	
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
	public BigDecimal getCodeAuxiliar() {
		return codeAuxiliar;
	}
	public void setCodeAuxiliar(BigDecimal codeAuxiliar) {
		this.codeAuxiliar = codeAuxiliar;
	}
}
