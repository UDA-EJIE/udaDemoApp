package com.ejie.x21a.model;

import java.math.BigDecimal;

import org.hdiv.services.SecureIdContainer;
import org.hdiv.services.TrustAssertion;

public class ProvinciaComarcaLocalidadDTO implements java.io.Serializable, SecureIdContainer {
	
	private static final long serialVersionUID = 1L;
	
	@TrustAssertion(idFor = Provincia.class)
	private BigDecimal codeProvincia;
	@TrustAssertion(idFor = Comarca.class)
	private BigDecimal codeComarca;
	@TrustAssertion(idFor = Localidad.class)
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
