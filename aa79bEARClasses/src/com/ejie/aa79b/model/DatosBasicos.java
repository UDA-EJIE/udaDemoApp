package com.ejie.aa79b.model;

import java.util.List;

public class DatosBasicos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private ConfigPlazoMinimo usuarioNormal;
	private ConfigPlazoMinimo usuarioVIP;
	private LimitePalConcor configHorasPrevistasLimPalConcor;
	private LimitePalConcor configHorasPrevistasProvExtLimPalConcor;
	private ConfigCalculoPresupuesto configCalculoPresupuesto;
	private ConfigPerfilSolicitante configPerfilSolicitante;
	private ConfigDireccionEmail configDireccionEmail;
	private ConfigDireccionEmail configDireccionEmailInterpretacion;
	private List<ConfigTextoEmails> textoEmails;
	private ConfigLibroRegistro libroRegistro;

	public LimitePalConcor getConfigHorasPrevistasLimPalConcor() {
		return this.configHorasPrevistasLimPalConcor;
	}

	public void setConfigHorasPrevistasLimPalConcor(LimitePalConcor configHorasPrevistasLimPalConcor) {
		this.configHorasPrevistasLimPalConcor = configHorasPrevistasLimPalConcor;
	}

	public LimitePalConcor getConfigHorasPrevistasProvExtLimPalConcor() {
		return this.configHorasPrevistasProvExtLimPalConcor;
	}

	public void setConfigHorasPrevistasProvExtLimPalConcor(LimitePalConcor configHorasPrevistasProvExtLimPalConcor) {
		this.configHorasPrevistasProvExtLimPalConcor = configHorasPrevistasProvExtLimPalConcor;
	}

	public ConfigCalculoPresupuesto getConfigCalculoPresupuesto() {
		return this.configCalculoPresupuesto;
	}

	public void setConfigCalculoPresupuesto(ConfigCalculoPresupuesto configCalculoPresupuesto) {
		this.configCalculoPresupuesto = configCalculoPresupuesto;
	}

	public ConfigPerfilSolicitante getConfigPerfilSolicitante() {
		return this.configPerfilSolicitante;
	}

	public void setConfigPerfilSolicitante(ConfigPerfilSolicitante configPerfilSolicitante) {
		this.configPerfilSolicitante = configPerfilSolicitante;
	}

	public ConfigDireccionEmail getConfigDireccionEmail() {
		return this.configDireccionEmail;
	}

	public void setConfigDireccionEmail(ConfigDireccionEmail configDireccionEmail) {
		this.configDireccionEmail = configDireccionEmail;
	}

	public ConfigPlazoMinimo getUsuarioNormal() {
		return this.usuarioNormal;
	}

	public void setUsuarioNormal(ConfigPlazoMinimo usuarioNormal) {
		this.usuarioNormal = usuarioNormal;
	}

	public ConfigPlazoMinimo getUsuarioVIP() {
		return this.usuarioVIP;
	}

	public void setUsuarioVIP(ConfigPlazoMinimo usuarioVIP) {
		this.usuarioVIP = usuarioVIP;
	}

	public List<ConfigTextoEmails> getTextoEmails() {
		return this.textoEmails;
	}

	public void setTextoEmails(List<ConfigTextoEmails> textoEmails) {
		this.textoEmails = textoEmails;
	}

	public ConfigLibroRegistro getLibroRegistro() {
		return this.libroRegistro;
	}

	public void setLibroRegistro(ConfigLibroRegistro libroRegistro) {
		this.libroRegistro = libroRegistro;
	}

	public ConfigDireccionEmail getConfigDireccionEmailInterpretacion() {
		return this.configDireccionEmailInterpretacion;
	}

	public void setConfigDireccionEmailInterpretacion(ConfigDireccionEmail configDireccionEmailInterpretacion) {
		this.configDireccionEmailInterpretacion = configDireccionEmailInterpretacion;
	}

}
