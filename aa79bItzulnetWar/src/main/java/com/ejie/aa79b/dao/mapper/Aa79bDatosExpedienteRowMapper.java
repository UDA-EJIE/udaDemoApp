package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.webservices.Aa79bDatosInterpretacion;
import com.ejie.aa79b.model.webservices.Aa79bDatosTradRev;
import com.ejie.aa79b.model.webservices.Aa79bDescripcionIdioma;
import com.ejie.aa79b.model.webservices.Aa79bDireccionNora;
import com.ejie.aa79b.model.webservices.Aa79bEstadoFaseExpediente;
import com.ejie.aa79b.model.webservices.Aa79bExpediente;
import com.ejie.aa79b.model.webservices.Aa79bFicheroObservaciones;
import com.ejie.aa79b.model.webservices.Aa79bGestorExpediente;

public class Aa79bDatosExpedienteRowMapper implements RowMapper<Aa79bExpediente> {

	@Override
	public Aa79bExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Aa79bExpediente aa79bExpediente = new Aa79bExpediente();
		aa79bExpediente.setAnyo(resultSet.getLong(DBConstants.ANYO));
		aa79bExpediente.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		Aa79bDescripcionIdioma tipoExpediente = new Aa79bDescripcionIdioma();
		tipoExpediente.setCodigo(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
		tipoExpediente.setDescEs(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCES));
		tipoExpediente.setDescEu(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCEU));
		aa79bExpediente.setTipoExpediente(tipoExpediente);
		aa79bExpediente.setTitulo(resultSet.getString(DBConstants.TITULO));
		aa79bExpediente.setIdRequerimiento(resultSet.getInt("IDREQUERIMIENTO"));
		aa79bExpediente.setIdTipoRequerimiento(resultSet.getInt("TIPOREQUERIMIENTO"));
		aa79bExpediente.setEstadoSubsanacion(resultSet.getInt("ESTADOSUBSANACION"));
		aa79bExpediente.setIndSubsanado(resultSet.getString("INDSUBSANADO"));

		Aa79bEstadoFaseExpediente aa79bEstadoFaseExpediente = new Aa79bEstadoFaseExpediente();
		aa79bEstadoFaseExpediente.setIdEstado(resultSet.getLong(DBConstants.IDESTADOEXP));
		aa79bEstadoFaseExpediente.setIdFase(resultSet.getLong(DBConstants.IDFASEEXPEDIENTE));
		aa79bExpediente.setEstadoFaseExpediente(aa79bEstadoFaseExpediente);
		aa79bExpediente.setOrigen(resultSet.getString("ORIGEN"));
		final Date fechaAlta = resultSet.getTimestamp(DBConstants.FECHAALTA);
		if (fechaAlta != null) {
			aa79bExpediente.setFechaSolicitud(fechaAlta.getTime());
		}

		Aa79bDescripcionIdioma estadoFase = new Aa79bDescripcionIdioma();
		estadoFase.setId(resultSet.getLong("ESTADOFASEAA06A"));
		estadoFase.setDescEs(resultSet.getString("ESTADOFASEAA06ADESCES"));
		estadoFase.setDescEu(resultSet.getString("ESTADOFASEAA06ADESCEU"));
		aa79bExpediente.setEstadoFase(estadoFase);

		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(aa79bExpediente.getTipoExpediente().getCodigo())) {
			Aa79bDatosInterpretacion aa79bDatosInterpretacion = new Aa79bDatosInterpretacion();
			Aa79bDescripcionIdioma modoInterpretacion = new Aa79bDescripcionIdioma();
			modoInterpretacion.setId(resultSet.getLong("MODOINTERPRETACION"));
			modoInterpretacion.setDescEu(resultSet.getString("MODOINTERPRETACIONDESCEU"));
			modoInterpretacion.setDescEs(resultSet.getString("MODOINTERPRETACIONDESCES"));
			aa79bDatosInterpretacion.setModoInterpretacion(modoInterpretacion);

			Aa79bDescripcionIdioma tipoActo = new Aa79bDescripcionIdioma();
			tipoActo.setId(resultSet.getLong("TIPOACTO"));
			tipoActo.setDescEu(resultSet.getString("TIPOACTODESCEU"));
			tipoActo.setDescEs(resultSet.getString("TIPOACTODESCES"));
			aa79bDatosInterpretacion.setTipoActo(tipoActo);

			Aa79bDescripcionIdioma tipoPeticionario = new Aa79bDescripcionIdioma();
			tipoPeticionario.setCodigo(resultSet.getString("TIPOPETICIONARIO"));
			tipoPeticionario.setDescEu(resultSet.getString("TIPOPETICIONARIODESCEU"));
			tipoPeticionario.setDescEs(resultSet.getString("TIPOPETICIONARIODESCES"));
			aa79bDatosInterpretacion.setTipoPeticionario(tipoPeticionario);

			aa79bDatosInterpretacion.setIndProgramada(resultSet.getString("INDPROGRAMADA"));
			final Date fechaIni = resultSet.getTimestamp("FECHAINI");
			final Date fechaFin = resultSet.getTimestamp("FECHAFIN");
			if (fechaIni != null) {
				aa79bDatosInterpretacion.setFechaIni(fechaIni.getTime());
			}
			if (fechaFin != null) {
				aa79bDatosInterpretacion.setFechaFin(fechaFin.getTime());
			}

			aa79bDatosInterpretacion.setIndPresupuesto(resultSet.getString("INDPRESUPUESTOINTER"));
			aa79bDatosInterpretacion.setIndObservaciones(resultSet.getString("INDOBSERVACIONESINTER"));
			aa79bDatosInterpretacion.setIndFacturable(resultSet.getString(DBConstants.INDFACTURABLE));

			Aa79bDireccionNora aa79bDireccionNora = new Aa79bDireccionNora();
			DireccionNora direccionNora = new DireccionNora();
			direccionNora.setDirNora(resultSet.getInt("DIRNORA"));
			aa79bDireccionNora.setDireccion(direccionNora);
			aa79bDatosInterpretacion.setDirNora(aa79bDireccionNora);

			aa79bExpediente.setDatosInterpretacion(aa79bDatosInterpretacion);
		} else {
			Aa79bDatosTradRev aa79bDatosTradRev = new Aa79bDatosTradRev();

			aa79bDatosTradRev.setNumTotalPalSolic(resultSet.getInt("NUMTOTALPALSOLIC"));
			aa79bDatosTradRev.setIndFacturable(resultSet.getString(DBConstants.INDFACTURABLE));
			aa79bDatosTradRev.setIdRelevancia(resultSet.getLong("IDRELEVANCIA"));
			aa79bDatosTradRev.setIndUrgente(resultSet.getString("INDURGENTE"));
			aa79bDatosTradRev.setHoraFinalIzo(resultSet.getString("HORAFINALIZO"));

			final Date fechaFinalProp = resultSet.getTimestamp("FECHAFINALPROP");
			if (fechaFinalProp != null) {
				aa79bDatosTradRev.setFechaFinalProp(fechaFinalProp.getTime());
			}

			final Date fechaFinalSolic = resultSet.getTimestamp("FECHAFINALSOLIC");
			if (fechaFinalSolic != null) {
				aa79bDatosTradRev.setFechaFinalSolic(fechaFinalSolic.getTime());
				aa79bDatosTradRev.setHoraFinalSolic(resultSet.getString("HORAFINALSOLIC"));
			}

			aa79bDatosTradRev.setIndPublicarBopv(resultSet.getString("INDPUBLICARBOPV"));
			aa79bDatosTradRev.setIndPresupuesto(resultSet.getString("INDPRESUPUESTOTRADREV"));
			aa79bDatosTradRev.setRefTramitagune(resultSet.getString("REFTRAMITAGUNE"));
			aa79bDatosTradRev.setIndCorredaccion(resultSet.getString("INDCORREDACCION"));

			Aa79bDescripcionIdioma idioma = new Aa79bDescripcionIdioma();
			idioma.setId(resultSet.getLong("IDIDIOMA"));
			idioma.setDescEu(resultSet.getString("IDIOMADESCEU"));
			idioma.setDescEs(resultSet.getString("IDIOMADESCES"));
			aa79bDatosTradRev.setIdioma(idioma);

			aa79bDatosTradRev.setIndObservaciones(resultSet.getString("INDOBSERVACIONESTRADREV"));
			aa79bDatosTradRev.setIndConfidencial(resultSet.getString("INDCONFIDENCIAL"));

			final Date fechaLimite = resultSet.getTimestamp("FECHALIMITE");
			if (fechaLimite != null) {
				aa79bDatosTradRev.setFechaLimite(fechaLimite.getTime());
			}

			aa79bDatosTradRev.setNumTotalPalIzo(resultSet.getInt("NUMTOTALPALIZO"));

			final Date fechaFinalIzo = resultSet.getTimestamp("FECHAFINALIZO");
			if (fechaFinalIzo != null) {
				aa79bDatosTradRev.setFechaFinalIzo(fechaFinalIzo.getTime());
			}

			final Date fechaEntrega = resultSet.getTimestamp("FECHAENTREGA");
			if (fechaEntrega != null) {
				aa79bDatosTradRev.setFechaEntrega(fechaEntrega.getTime());
			}

			aa79bDatosTradRev.setObservaciones(resultSet.getString("OBSERVACIONES55"));
			aa79bDatosTradRev.setTexto(resultSet.getString("TEXTO53"));
			aa79bDatosTradRev.setTipoRedaccion(resultSet.getString("TIPOREDACCION53"));
			aa79bDatosTradRev.setParticipantes(resultSet.getString("PARTICIPANTES53"));

			Aa79bFicheroObservaciones ficheroObservaciones = new Aa79bFicheroObservaciones();
			ficheroObservaciones.setContentType(resultSet.getString("CONTENTTYPE55"));
			ficheroObservaciones.setExtension(resultSet.getString("EXTENSIONDOC55"));
			ficheroObservaciones.setNombre(resultSet.getString("NOMBREFICHERO55"));
			ficheroObservaciones.setOidFichero(resultSet.getString("OIDFICHERO55"));

			aa79bDatosTradRev.setFicheroObservaciones(ficheroObservaciones);

			aa79bExpediente.setDatosTradRev(aa79bDatosTradRev);
		}

		Aa79bGestorExpediente gestor = new Aa79bGestorExpediente();
		gestor.setPreDniGestor(resultSet.getString("PREDNISOLICITANTE"));
		gestor.setDniGestor(resultSet.getString("DNISOLICITANTE"));
		gestor.setSufDniGestor(resultSet.getString("SUFDNISOLICITANTE"));
		gestor.setNombreGestor(resultSet.getString("NOMBRESOLICITANTE"));
		gestor.setApellido1Gestor(resultSet.getString("APEL1SOLICITANTE"));
		gestor.setApellido2Gestor(resultSet.getString("APEL2SOLICITANTE"));
		gestor.setIndVIPGestor(resultSet.getString("INDVIPSOLICITANTE"));
		gestor.setTipoEntidadGestor(resultSet.getString("TIPOENTIDAD"));
		gestor.setCodigoEntidadGestor(resultSet.getInt("IDENTIDAD"));
		gestor.setDescAmpEntidadEuGestor(resultSet.getString("DESCAMPENTIDADEU"));
		gestor.setDescAmpEntidadEsGestor(resultSet.getString("DESCAMPENTIDADES"));
		gestor.setDescEntidadEuGestor(resultSet.getString("DESCENTIDADEU"));
		gestor.setDescEntidadEsGestor(resultSet.getString("DESCENTIDADES"));
		gestor.setCifGestor(resultSet.getString("CIFENTIDAD"));
		gestor.setPreDniRepresentante(resultSet.getString("PREDNIREPRESENTANTE"));
		gestor.setDniRepresentante(resultSet.getString("DNIREPRESENTANTE"));
		gestor.setSufDniRepresentante(resultSet.getString("SUFDNIREPRESENTANTE"));
		gestor.setNombreRepresentante(resultSet.getString("NOMBREREPRESENTANTE"));
		gestor.setApellido1Representante(resultSet.getString("APEL1REPRESENTANTE"));
		gestor.setApellido2Representante(resultSet.getString("APEL2REPRESENTANTE"));
		aa79bExpediente.setGestor(gestor);

		return aa79bExpediente;
	}

}
