package com.ejie.x21a.model;

import java.math.BigDecimal;

import org.hdiv.services.SecureIdContainer;
import org.hdiv.services.TrustAssertion;

public class ComarcaLocalidadDTO implements java.io.Serializable, SecureIdContainer {
	
	private static final long serialVersionUID = 1L;

	@TrustAssertion(idFor = ComarcaLocalidadDTO.class)
	private BigDecimal codeComarcaLocalidad;
	@TrustAssertion(idFor = Provincia.class)
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
