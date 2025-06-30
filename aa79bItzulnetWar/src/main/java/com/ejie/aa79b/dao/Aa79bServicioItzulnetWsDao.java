package com.ejie.aa79b.dao;

import java.util.List;
import java.util.Map;

import com.ejie.aa79b.model.Comunicaciones;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.enums.AccionBitacoraEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.model.webservices.servicios.AA79bDatosSolicitante;
import com.ejie.aa79b.model.webservices.servicios.AA79bDatosSolicitanteSolicitud;

public interface Aa79bServicioItzulnetWsDao {

	Integer addExpediente(Expediente expediente);
	Integer addExpedienteTradRev(Expediente expediente);
	Integer addObservacionesExpediente(Expediente expediente);
	Integer addGestorExpediente(Expediente expediente);
	Integer addContactoFacturacionExpediente(Expediente expediente);
	Integer addDocumentosExpediente(Expediente expediente);
	Integer addBitacoraExpediente(Expediente expediente, EstadoExpedienteEnum estado, FaseExpedienteEnum fase);

	Integer generarSolicitud(Expediente expediente);
	int addBitacoraSolicitud(Expediente expediente, AccionBitacoraEnum accionBitacora);

	void guardarTecnico(String dni);

	void addRegistroAcciones(Expediente expediente, Expediente original, Long accionAlta,
			Map<String, String> parametros, String string);
	void guardarTecnico(List<String> dnis);

	List<Expediente> getExpedientesRefAplic(Expediente expediente);

	String validarSolicitud(Expediente expediente);

	Integer validarEliminacion(Expediente expediente);

	void eliminarSolicitud(Expediente expediente);

	Integer addComunicacion(Comunicaciones comunicaciones);

	Integer comprobacionPersona(AA79bDatosSolicitanteSolicitud datosSolicitante);

	boolean comprobacionSolicitante(AA79bDatosSolicitanteSolicitud datosSolicitante);

	AA79bDatosSolicitante getDatosEntidad(AA79bDatosSolicitanteSolicitud datosSolicitante);

}
