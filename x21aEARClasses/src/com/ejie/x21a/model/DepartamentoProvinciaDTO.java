package com.ejie.x21a.model;

import java.math.BigDecimal;

public class DepartamentoProvinciaDTO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private BigDecimal codeDepartamentoProvincia;
	private BigDecimal codeProvincia;
    private BigDecimal codeDepartamento;
	
	public BigDecimal getCodeDepartamentoProvincia() {
		return codeDepartamentoProvincia;
	}
	public void setCodeDepartamentoProvincia(BigDecimal codeDepartamentoProvincia) {
		this.codeDepartamentoProvincia = codeDepartamentoProvincia;
	}
	public BigDecimal getCodeProvincia() {
		return codeProvincia;
	}
	public void setCodeProvincia(BigDecimal codeProvincia) {
		this.codeProvincia = codeProvincia;
	}
	public BigDecimal getCodeDepartamento() {
		return codeDepartamento;
	}
	public void setCodeDepartamento(BigDecimal codeDepartamento) {
		this.codeDepartamento = codeDepartamento;
	}	
}
