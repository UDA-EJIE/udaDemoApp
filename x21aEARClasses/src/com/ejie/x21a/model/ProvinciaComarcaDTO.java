package com.ejie.x21a.model;

import java.math.BigDecimal;

import org.hdiv.services.SecureIdContainer;
import org.hdiv.services.TrustAssertion;

public class ProvinciaComarcaDTO implements java.io.Serializable, SecureIdContainer {
	
	private static final long serialVersionUID = 1L;
	
	@TrustAssertion(idFor = Provincia.class)
	private BigDecimal codeProvincia;
	@TrustAssertion(idFor = Comarca.class)
	private BigDecimal codeComarca;
	@TrustAssertion(idFor = Comarca.class)
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
