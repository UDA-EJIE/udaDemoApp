package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteInterpretacion;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.ObservacionesExpediente;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.utils.Utils;

public class JustiSolicitudRowMapper implements RowMapper<Expediente> {

	@Override
	public Expediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Expediente expediente = new Expediente();
		expediente.setAnyo(resultSet.getLong("ANYO_069"));
		expediente.setNumExp(resultSet.getInt("NUM_EXP_069"));
		expediente.setIdTipoExpediente(resultSet.getString("ID_TIPO_EXPEDIENTE_069"));
		expediente.setTipoExpedienteDescEs(resultSet.getString("TIPO_EXPEDIENTE_ES"));
		expediente.setTipoExpedienteDescEu(resultSet.getString("TIPO_EXPEDIENTE_EU"));
		expediente.setTipoExpedienteDescEsPDF(resultSet.getString("TIPOEXPEDIENTEDESCES"));
		expediente.setTipoExpedienteDescEuPDF(resultSet.getString("TIPOEXPEDIENTEDESCEU"));
		expediente.setTitulo(resultSet.getString("TITULO_069"));
		expediente.setFechaAlta(resultSet.getDate("FECHAALTA069"));
		expediente.setHoraAlta(resultSet.getString("HORAALTA069"));

		// ExpedienteInterpretacion
		ExpedienteInterpretacion expedienteInterpretacion = new ExpedienteInterpretacion();
		expedienteInterpretacion.setModoInterpretacion(resultSet.getLong("MODO_INTERPRETACION_070"));
		expedienteInterpretacion.setModoInterpretacionDescEs(resultSet.getString("DESC_ES_014"));
		expedienteInterpretacion.setModoInterpretacionDescEu(resultSet.getString("DESC_EU_014"));
		expedienteInterpretacion.setTipoActo(resultSet.getLong("TIPO_ACTO_070"));
		expedienteInterpretacion.setTipoActoDescEs(resultSet.getString("DESC_ES_008"));
		expedienteInterpretacion.setTipoActoDescEu(resultSet.getString("DESC_EU_008"));
		expedienteInterpretacion.setTipoPeticionario(resultSet.getString("TIPO_PETICIONARIO_070"));
		expedienteInterpretacion.setTipoPeticionarioDescEs(resultSet.getString("TIPOPETICIONARIODESCES"));
		expedienteInterpretacion.setTipoPeticionarioDescEu(resultSet.getString("TIPOPETICIONARIODESCEU"));
		expedienteInterpretacion.setIndProgramada(resultSet.getString("IND_PROGRAMADA_070"));
		expedienteInterpretacion.setProgramadaDescEs(resultSet.getString("INDPROGRAMADADESCES"));
		expedienteInterpretacion.setProgramadaDescEu(resultSet.getString("INDPROGRAMADADESCEU"));
		expedienteInterpretacion.setFechaIni(resultSet.getDate("FECHA_INI_070"));
		expedienteInterpretacion.setHoraIni(resultSet.getString("HORA_INI_070"));
		expedienteInterpretacion.setFechaFin(resultSet.getDate("FECHA_FIN_070"));
		expedienteInterpretacion.setHoraFin(resultSet.getString("HORA_FIN_070"));

		DireccionNora direccionNora = new DireccionNora();
		direccionNora.setDirNora(resultSet.getInt("CDIRNORA_049"));
		expedienteInterpretacion.setDirNora(direccionNora);

		expedienteInterpretacion.setIndPresupuesto(resultSet.getString("IND_PRESUPUESTO_070"));
		expedienteInterpretacion.setPresupuestoDescEs(resultSet.getString("PRESUPUESTODESCES"));
		expedienteInterpretacion.setPresupuestoDescEu(resultSet.getString("PRESUPUESTODESCEU"));
		expedienteInterpretacion.setPersonaContacto(resultSet.getString("PERSONA_CONTACTO_070"));
		expedienteInterpretacion.setEmailContacto(resultSet.getString("EMAIL_CONTACTO_070"));
		expedienteInterpretacion.setTelefonoContacto(resultSet.getString("TELEFONO_CONTACTO_070"));
		expedienteInterpretacion.setIndObservaciones(resultSet.getString("IND_OBSERVACIONES_070"));

		ObservacionesExpediente observacionesExpedienteInter = new ObservacionesExpediente();
		observacionesExpedienteInter.setNombre(resultSet.getString("IND_OBSERVACIONES_071"));
		observacionesExpedienteInter.setObservaciones(resultSet.getString("OBSERVACIONES_072"));

		expedienteInterpretacion.setObservaciones(observacionesExpedienteInter);

		expediente.setExpedienteInterpretacion(expedienteInterpretacion);

		// ExpedienteTradRev
		ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev();
		expedienteTradRev.setAnyo(expediente.getAnyo());
		expedienteTradRev.setNumExp(expediente.getNumExp());
		expedienteTradRev.setIndPublicarBopv(resultSet.getString("IND_PUBLICAR_BOPV_071"));
		expedienteTradRev.setPublicarBopvDescEs(resultSet.getString("PUBLICARBOPVDESCES"));
		expedienteTradRev.setPublicarBopvDescEu(resultSet.getString("PUBLICARBOPVDESCEU"));
		expedienteTradRev.setIdIdioma(resultSet.getLong("ID_IDIOMA_071"));
		expedienteTradRev.setIdIdiomaDescEs(resultSet.getString("DESC_IDIOMA_ES_009"));
		expedienteTradRev.setIdIdiomaDescEu(resultSet.getString("DESC_IDIOMA_EU_009"));
		expedienteTradRev.setIndConfidencial(resultSet.getString("IND_CONFIDENCIAL_071"));
		expedienteTradRev.setIndCorredaccion(resultSet.getString("IND_CORREDACCION_071"));
		expedienteTradRev.setTexto(resultSet.getString("TEXTO_071"));
		expedienteTradRev.setTipoRedaccion(resultSet.getString("TIPO_REDACCION_071"));
		expedienteTradRev.setParticipantes(resultSet.getString("PARTICIPANTES_071"));
		expedienteTradRev.setRefTramitagune(resultSet.getString("REF_TRAMITAGUNE_071"));
		expedienteTradRev.setNumTotalPalSolic(resultSet.getInt("NUM_TOTAL_PAL_SOLIC_071"));
		expedienteTradRev.setIndFacturable(resultSet.getString("IND_FACTURABLE_071"));
		expedienteTradRev.setIdRelevancia(resultSet.getLong("ID_RELEVANCIA_071"));
		expedienteTradRev.setIndUrgente(resultSet.getString("IND_URGENTE_071"));
		expedienteTradRev.setFechaFinalSolic(resultSet.getDate("FECHA_FINAL_SOLIC_071"));
		expedienteTradRev.setHoraFinalSolic(resultSet.getString("HORAFINALSOLIC"));
		expedienteTradRev.setIndObservaciones(resultSet.getString("IND_OBSERVACIONES_071"));
		expedienteTradRev.setIndPresupuesto(resultSet.getString("IND_PRESUPUESTO_071"));

		ObservacionesExpediente observacionesExpedienteTradRev = new ObservacionesExpediente();
		observacionesExpedienteTradRev.setNombre(resultSet.getString("NOMBRE_FICHERO_072"));
		observacionesExpedienteTradRev.setObservaciones(resultSet.getString("OBSERVACIONES_072"));

		expedienteTradRev.setObservaciones(observacionesExpedienteTradRev);
		expediente.setExpedienteTradRev(expedienteTradRev);

		// GestorExpediente
		Gestor gestorExpediente = new Gestor();
		Solicitante solicitanteAux = new Solicitante();
		solicitanteAux.setDni(resultSet.getString("DNI_SOLICITANTE_054"));
		solicitanteAux.setPreDni(resultSet.getString("PREDNI_077"));
		solicitanteAux.setSufDni(resultSet.getString("SUFDNI_077"));
		solicitanteAux.setDniCompleto(resultSet.getString("DNICOMPLETO"));
		solicitanteAux.setGestorExpedientesVIP(resultSet.getString("IND_VIP_054"));
		solicitanteAux.setGestorExpedientesVIPDescEs(resultSet.getString("GESTOREXPEDIENTESVIPDESCES"));
		solicitanteAux.setGestorExpedientesVIPDescEu(resultSet.getString("GESTOREXPEDIENTESVIPDESCEU"));
		solicitanteAux.setNombre(resultSet.getString("NOMBRE_077"));
		solicitanteAux.setApellido1(resultSet.getString("APEL1_077"));
		solicitanteAux.setApellido2(resultSet.getString("APEL2_077"));
		gestorExpediente.setSolicitante(solicitanteAux);
		Entidad entidadAux = new Entidad();
		entidadAux.setTipo(resultSet.getString("TIPO_ENTIDAD_054"));
		entidadAux.setTipoDesc(Utils.getTipoEntidadDescLabel(entidadAux.getTipo()));
		entidadAux.setCodigo(resultSet.getInt("ID_ENTIDAD_054"));
		entidadAux.setCif(resultSet.getString("ENTIDAD_CIF"));
		entidadAux.setEstado(resultSet.getString("ENTIDAD_ESTADO"));
		entidadAux.setDescEs(resultSet.getString("ENTIDAD_DESC_ES"));
		entidadAux.setDescEu(resultSet.getString("ENTIDAD_DESC_EU"));
		entidadAux.setDescAmpEs(resultSet.getString("ENTIDAD_DESC_AMP_ES"));
		entidadAux.setDescAmpEu(resultSet.getString("ENTIDAD_DESC_AMP_EU"));
		gestorExpediente.setEntidad(entidadAux);
		expediente.setGestorExpediente(gestorExpediente);

		return expediente;
	}

}
