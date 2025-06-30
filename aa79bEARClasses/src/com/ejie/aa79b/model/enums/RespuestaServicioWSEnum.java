package com.ejie.aa79b.model.enums;

public enum RespuestaServicioWSEnum {
	OK(0, "")
	,ELIMINADO(1, "servicioWS.eliminado")
	,NO_ELIMNADO(2, "servicioWS.noEliminado")
	, NO_ES_GESTOR(101, "servicioWS.noEsGestor")
	, NO_ES_GESTOR_BOPV(102, "servicioWS.noEsGestorBOPV")
	, NO_ES_GESTOR_APLIC_BOPV(102, "servicioWS.noEsGestorAplicBOPV")
	, NO_PERTENECE_ENTIDAD(103, "servicioWS.noPerteneceEntidad")
	, NO_CUMPLE_PLAZO_MINIMO(104, "servicioWS.noCumplePlazoMinimo")
	, APLICACION_NO_PERMITIDA(105, "servicioWS.aplicacionNoPermitida")
	, TIPO_DOCUMENTAL_NO_PERMITIDO(106, "servicioWS.tipoDocumentalNoPermitido")
	, SOLICITANTE_NO_EXISTE(107, "servicioWS.solicitanteNoExiste")
	, ELIMINACION_NO_PERMITIDA(108, "servicioWS.eliminacionNoPermitida")
	, ENTIDAD_NO_ENCONTRADA(109, "servicioWS.entidadNoEncontrada")
	, ERROR_VALIDACION_COMUNICACION(110, "servicioWS.errorValidacionComunicacion")
	, ERROR_FORMATO(501, "servicioWS.errorFormato")
	, ERROR_OBLIGATORIOS(502, "servicioWS.errorObligatorios")
	, ERROR_PIF(503, "servicioWS.errorPIF")
	, ERROR_VALIDACION_DOCUMENTO(504, "")
	, ERROR_ALTA_USUARIO_PERFIL_NUEVO(506, "servicioWS.errorAltaUsuarioPerfilNuevo")
	, ERROR_SISTEMA(901, "servicioWS.errorSistema")
	;

	private Integer codigo;
	private String mensaje;

	private RespuestaServicioWSEnum(Integer codigo, String mensaje) {
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public String getMensaje(){
		return this.mensaje;
	}

}
