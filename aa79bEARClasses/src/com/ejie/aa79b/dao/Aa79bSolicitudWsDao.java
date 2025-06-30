package com.ejie.aa79b.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.BitacoraSolicitante;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.webservices.Aa79bDocumentoExpediente;
import com.ejie.aa79b.model.webservices.Aa79bRegistroExpediente;
import com.ejie.aa79b.model.webservices.Aa79bSolicitud;

public interface Aa79bSolicitudWsDao extends GenericoDao<Aa79bSolicitud> {

	/**
	 *
	 * @param solicitante
	 * @param solicitud
	 * @return
	 */
	Aa79bRegistroExpediente altaSolicitud(Solicitante solicitante, Aa79bSolicitud solicitud);

	/**
	 *
	 * @param solicitud
	 * @return
	 */
	int addSolicitud69(Aa79bSolicitud solicitud);

	/**
	 *
	 * @param bitacoraExpediente
	 */
	long addBitacoraExpediente(BitacoraExpediente bitacoraExpediente);

	/**
	 *
	 * @param solicitud
	 * @return
	 */
	int updateIdEstadoBitacora(Aa79bSolicitud solicitud);

	/**
	 *
	 * @param solicitud
	 * @param parametros
	 * @return
	 */
	int addGestorExpediente(Aa79bSolicitud solicitud, Map<String, String> parametros);

	/**
	 *
	 * @param solicitud
	 */
	void addExpedienteInterpretacion(Aa79bSolicitud solicitud);

	/**
	 *
	 * @param solicitud
	 */
	void addExpedienteTradRev(Aa79bSolicitud solicitud);

	/**
	 *
	 * @param solicitud
	 */
	void addDocumentosExpediente(Aa79bSolicitud solicitud);

	/**
	 *
	 * @param solicitud
	 * @param object
	 * @param accionAlta
	 * @param parametros
	 * @param string
	 */
	void registrarAcciones(Aa79bSolicitud solicitud, Aa79bSolicitud original, Long accionAlta,
			Map<String, String> parametros, String mensaje);

	/**
	 *
	 * @param solicitud
	 * @return
	 */
	int addExpedienteTradRev71(Aa79bSolicitud solicitud);

	/**
	 *
	 * @param solicitud
	 */
	int addExpedienteInterpretacion70(Aa79bSolicitud solicitud);

	/**
	 *
	 * @param solicitud
	 * @param tabla
	 * @return
	 */
	int relacionarExpedientes(Aa79bSolicitud solicitud, String tabla);

	/**
	 *
	 * @param solicitud
	 * @return
	 */
	int addBitacoraSolicitud(Aa79bSolicitud solicitud, int accionBitacora);

	int addBitacoraSolicitante(BitacoraSolicitante bitacoraSolicitante);

	/**
	 *
	 * @param solicitud
	 * @return
	 */
	int addDocumentosExpediente73(Aa79bSolicitud solicitud);

	/**
	 *
	 * @param solicitud
	 * @param object
	 * @param accionAlta
	 * @param parametros
	 * @param string
	 * @return
	 */
	void addRegistroAcciones(Aa79bSolicitud solicitud, Aa79bSolicitud original, Long accionAlta,
			Map<String, String> parametros, String string);

	/**
	 *
	 * @param solicitud
	 * @param subsanar
	 */
	int updateSolicitud69(Aa79bSolicitud solicitud, boolean subsanar);

	/**
	 *
	 * @param solicitud
	 * @param subsanar
	 */
	int updateExpedienteTradRev(Aa79bSolicitud solicitud, boolean subsanar);

	/**
	 *
	 * @param solicitud
	 * @param subsanar
	 */
	int updateExpedienteTradRev71(Aa79bSolicitud solicitud, boolean subsanar);

	/**
	 *
	 * @param solicitud
	 * @param subsanar
	 */
	int updateExpedienteInterpretacion(Aa79bSolicitud solicitud, boolean subsanar);

	/**
	 *
	 * @param solicitud
	 * @param subsanar
	 */
	int updateExpedienteInterpretacion70(Aa79bSolicitud solicitud, boolean subsanar);

	/**
	 *
	 * @param solicitud
	 * @param tabla057
	 * @param subsanar
	 */
	int updateExpedientesRelacionados(Aa79bSolicitud solicitud, String tabla057, boolean subsanar);

	/**
	 *
	 * @param solicitud
	 * @param subsanar
	 */
	List<BigDecimal> updateDocumentosExpediente(Aa79bSolicitud solicitud, boolean subsanar);

	/**
	 *
	 * @param solicitud
	 */
	int updateDocumentosExpediente73(Aa79bSolicitud solicitud, Aa79bDocumentoExpediente documento, int x);

	/**
	 *
	 * @param solicitanteAInsertar
	 *            Solicitante
	 * @return int
	 */
	int guardarTecnico(Solicitante solicitanteAInsertar);

	/**
	 *
	 * @param solicitud
	 * @return
	 */
	int updateExpedienteTradRevNumTotalPalSolic(Aa79bSolicitud solicitud);

	/**
	 *
	 * @param documento
	 */
	void removeDocYRelacionado(Aa79bDocumentoExpediente documento);

	/**
	 *
	 * @param elId
	 * @return
	 */
	List<String> getOidsABorrar(BigDecimal elId);

	/**
	 * busca el oid de un documento en base de datos por su idDoc
	 *
	 * @param elId
	 * @return
	 */
	String getOidBBDD(BigDecimal elId);

	List<String> getOidFichRelacionado(BigDecimal elId);

	void removeDocRelacionado(Aa79bDocumentoExpediente documento);

	List<Expediente> getExpedientesRefAplic(Expediente expediente);
}
