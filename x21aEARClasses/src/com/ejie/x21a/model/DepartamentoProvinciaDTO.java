package com.ejie.x21a.model;

import java.math.BigDecimal;

import org.hdiv.services.SecureIdContainer;
import org.hdiv.services.TrustAssertion;

public class DepartamentoProvinciaDTO implements java.io.Serializable, SecureIdContainer {
	
	private static final long serialVersionUID = 1L;
	
	@TrustAssertion(idFor = DepartamentoProvinciaDTO.class)
	private BigDecimal codeDepartamentoProvincia;
	@TrustAssertion(idFor = Provincia.class)
	private BigDecimal codeProvincia;
	@TrustAssertion(idFor = Departamento.class)
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
