package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.DetalleExpedienteVisionCliente;
import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.ExpedienteInterpretacion;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.GestorExpediente;
import com.ejie.aa79b.model.ObservacionesExpediente;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.SubsanacionExpediente;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;

public class DatosDetalleExpDesdeClienteConsultaRowMapper implements RowMapper<DetalleExpedienteVisionCliente> {

	@Override
	public DetalleExpedienteVisionCliente mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DetalleExpedienteVisionCliente expediente = new DetalleExpedienteVisionCliente();
		expediente.setAnyo(resultSet.getLong(DBConstants.ANYO));
		expediente.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		expediente.setIdTipoExpediente(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
		expediente.setTipoExpedienteDescEs(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCES));
		expediente.setTipoExpedienteDescEu(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCEU));
		expediente.setTitulo(resultSet.getString(DBConstants.TITULO));

		SubsanacionExpediente subsanacion = new SubsanacionExpediente();
		subsanacion.setId(resultSet.getLong(DBConstants.IDREQUERIMIENTO));
		subsanacion.setTipoRequerimiento(resultSet.getLong(DBConstants.TIPOREQUERIMIENTO));
		subsanacion.setEstado(resultSet.getInt(DBConstants.ESTADOSUBSANACION));
		subsanacion.setIndSubsanado(resultSet.getString(DBConstants.INDSUBSANADO));

		BitacoraExpediente bitacora = new BitacoraExpediente();
		EstadosExpediente estado = new EstadosExpediente();
		estado.setId(resultSet.getLong(DBConstants.IDESTADOEXP));
		FasesExpediente fase = new FasesExpediente();
		fase.setId(resultSet.getLong(DBConstants.IDFASEEXPEDIENTE));
		fase.setDescEs(resultSet.getString(DBConstants.ESTADOFASEAA06ADESCES));
		fase.setDescEu(resultSet.getString(DBConstants.ESTADOFASEAA06ADESCEU));
		bitacora.setEstadoExp(estado);
		bitacora.setFaseExp(fase);
		expediente.setBitacoraExpediente(bitacora);
		expediente.setOrigen(resultSet.getString(DBConstants.ORIGEN));
		expediente.setFechaAlta(resultSet.getDate(DBConstants.FECHAALTA));
		expediente.setHoraAlta(resultSet.getString(DBConstants.HORAALTA));
		ObservacionesExpediente observaciones = new ObservacionesExpediente();
		observaciones.setObservaciones(resultSet.getString(DBConstants.OBSERVACIONES55));

		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(expediente.getIdTipoExpediente())) {
			ExpedienteInterpretacion interExp = new ExpedienteInterpretacion();
			interExp.setModoInterpretacion(resultSet.getLong(DBConstants.MODOINTERPRETACION));
			interExp.setModoInterpretacionDescEs(resultSet.getString(DBConstants.MODOINTERPRETACIONDESCES));
			interExp.setModoInterpretacionDescEu(resultSet.getString(DBConstants.MODOINTERPRETACIONDESCEU));
			interExp.setTipoActo(resultSet.getLong(DBConstants.TIPOACTO));
			interExp.setTipoActoDescEs(resultSet.getString(DBConstants.TIPOACTODESCES));
			interExp.setTipoActoDescEu(resultSet.getString(DBConstants.TIPOACTODESCEU));
			interExp.setTipoPeticionario(resultSet.getString(DBConstants.TIPOPETICIONARIO));
			interExp.setTipoPeticionarioDescEs(resultSet.getString(DBConstants.TIPOPETICIONARIODESCES));
			interExp.setTipoPeticionarioDescEu(resultSet.getString(DBConstants.TIPOPETICIONARIODESCEU));
			interExp.setIndProgramada(resultSet.getString(DBConstants.INDPROGRAMADA));
			interExp.setProgramadaDescEs(resultSet.getString(DBConstants.PROGRAMADADESCES));
			interExp.setProgramadaDescEu(resultSet.getString(DBConstants.PROGRAMADADESCEU));
			interExp.setFechaIni(resultSet.getDate(DBConstants.FECHAINI));
			interExp.setHoraIni(resultSet.getString(DBConstants.HORAINI));
			interExp.setFechaFin(resultSet.getDate(DBConstants.FECHAFIN));
			interExp.setHoraFin(resultSet.getString(DBConstants.HORAFIN));
			interExp.setIndPresupuesto(resultSet.getString(DBConstants.INDPRESUPUESTOINTER));
			interExp.setPresupuestoDescEs(resultSet.getString(DBConstants.DESCPRESUPUESTOINTERES));
			interExp.setPresupuestoDescEu(resultSet.getString(DBConstants.DESCPRESUPUESTOINTEREU));
			interExp.setIndObservaciones(resultSet.getString(DBConstants.INDOBSERVACIONESINTER));
			interExp.setObservaciones(observaciones);
			DireccionNora dirNora = new DireccionNora();
			dirNora.setDirNora(resultSet.getInt(DBConstants.DIRNORA));
			interExp.setDirNora(dirNora);
			expediente.setExpedienteInterpretacion(interExp);
		} else {
			ExpedienteTradRev tradRevExp = new ExpedienteTradRev();
			tradRevExp.setNumTotalPalSolic(resultSet.getInt(DBConstants.NUMTOTALPALSOLIC));
			tradRevExp.setIndFacturable(resultSet.getString(DBConstants.INDFACTURABLE));
			tradRevExp.setIdRelevancia(resultSet.getLong(DBConstants.IDRELEVANCIA));
			tradRevExp.setIndUrgente(resultSet.getString(DBConstants.INDURGENTE));
			tradRevExp.setHoraFinalIzo(resultSet.getString(DBConstants.HORAFINALIZO));
			tradRevExp.setFechaFinalProp(resultSet.getDate(DBConstants.FECHAFINALPROP));
			tradRevExp.setFechaFinalSolic(resultSet.getDate(DBConstants.FECHAFINALSOLIC));
			tradRevExp.setHoraFinalSolic(resultSet.getString(DBConstants.HORAFINALSOLIC));
			tradRevExp.setIndPublicarBopv(resultSet.getString(DBConstants.INDPUBLICARBOPV));
			tradRevExp.setPublicarBopvDescEs(resultSet.getString(DBConstants.PUBLICARBOPVDESCES));
			tradRevExp.setPublicarBopvDescEu(resultSet.getString(DBConstants.PUBLICARBOPVDESCEU));
			tradRevExp.setIndPresupuesto(resultSet.getString(DBConstants.INDPRESUPUESTOTRADREV));
			tradRevExp.setReqPresupuestoDescEs(resultSet.getString(DBConstants.DESCPRESUPUESTOTRADREVES));
			tradRevExp.setReqPresupuestoDescEu(resultSet.getString(DBConstants.DESCPRESUPUESTOTRADREVEU));
			tradRevExp.setRefTramitagune(resultSet.getString(DBConstants.REFTRAMITAGUNE));
			tradRevExp.setIndCorredaccion(resultSet.getString(DBConstants.INDCORREDACCION));
			tradRevExp.setCorredaccionDescEs(resultSet.getString(DBConstants.DESCCORREDACCIONES));
			tradRevExp.setCorredaccionDescEu(resultSet.getString(DBConstants.DESCCORREDACCIONEU));
			tradRevExp.setIdIdioma(resultSet.getLong(DBConstants.IDIDIOMA));
			tradRevExp.setIdIdiomaDescEs(resultSet.getString(DBConstants.IDIOMADESCES));
			tradRevExp.setIdIdiomaDescEu(resultSet.getString(DBConstants.IDIOMADESCEU));
			tradRevExp.setIndObservaciones(resultSet.getString(DBConstants.INDOBSERVACIONESTRADREV));
			tradRevExp.setIndConfidencial(resultSet.getString(DBConstants.INDCONFIDENCIAL));
			expediente.setIndConfidencialDescEs(resultSet.getString(DBConstants.INDCONFIDENCIALDESCES));
			expediente.setIndConfidencialDescEu(resultSet.getString(DBConstants.INDCONFIDENCIALDESCEU));
			tradRevExp.setFechaLimitePresupuesto(resultSet.getDate(DBConstants.FECHALIMITE));
			tradRevExp.setNumTotalPalIzo(resultSet.getInt(DBConstants.NUMTOTALPALIZO));
			tradRevExp.setFechaFinalIzo(resultSet.getDate(DBConstants.FECHAFINALIZO));
			expediente.setFechaEntrega(resultSet.getDate(DBConstants.FECHAENTREGA));
			expediente.setHoraEntrega(resultSet.getString(DBConstants.HORAENTREGA));
			tradRevExp.setTexto(resultSet.getString(DBConstants.TEXTO53));
			tradRevExp.setTipoRedaccion(resultSet.getString(DBConstants.TIPOREDACCION53));
			tradRevExp.setParticipantes(resultSet.getString(DBConstants.PARTICIPANTES53));

			observaciones.setContentType(resultSet.getString(DBConstants.CONTENTTYPE55));
			observaciones.setExtension(resultSet.getString(DBConstants.EXTENSIONDOC55));
			observaciones.setNombre(resultSet.getString(DBConstants.NOMBREFICHERO55));
			observaciones.setOidFichero(resultSet.getString(DBConstants.OIDFICHERO55));
			tradRevExp.setObservaciones(observaciones);
			expediente.setExpedienteTradRev(tradRevExp);
		}

		GestorExpediente gestor = new GestorExpediente();
		Solicitante solicitante = new Solicitante();
		solicitante.setPreDni(resultSet.getString(DBConstants.PREDNISOLICITANTE));
		solicitante.setDni(resultSet.getString(DBConstants.DNISOLICITANTE));
		solicitante.setSufDni(resultSet.getString(DBConstants.SUFDNISOLICITANTE));
		solicitante.setNombre(resultSet.getString(DBConstants.NOMBRESOLICITANTE));
		solicitante.setApellido1(resultSet.getString(DBConstants.APEL1SOLICITANTE));
		solicitante.setApellido2(resultSet.getString(DBConstants.APEL2SOLICITANTE));
		solicitante.setGestorExpedientesVIP(resultSet.getString(DBConstants.INDVIPSOLICITANTE));
		Entidad entidad = new Entidad();

		entidad.setTipo(resultSet.getString(DBConstants.TIPOENTIDAD));
		entidad.setCodigo(resultSet.getInt(DBConstants.IDENTIDAD));
		entidad.setDescAmpEu(resultSet.getString(DBConstants.DESCAMPENTIDADEU));
		entidad.setDescAmpEs(resultSet.getString(DBConstants.DESCAMPENTIDADES));
		entidad.setDescEu(resultSet.getString(DBConstants.DESCENTIDADEU));
		entidad.setDescEs(resultSet.getString(DBConstants.DESCENTIDADES));
		entidad.setCif(resultSet.getString(DBConstants.CIFENTIDAD));
		gestor.setSolicitante(solicitante);
		gestor.setEntidad(entidad);
		expediente.setGestorExpediente(gestor);

		Solicitante representante = new Solicitante();
		representante.setPreDni(resultSet.getString(DBConstants.PREDNIREPRESENTANTE));
		representante.setDni(resultSet.getString(DBConstants.DNIREPRESENTANTE));
		representante.setSufDni(resultSet.getString(DBConstants.SUFDNIREPRESENTANTE));
		representante.setNombre(resultSet.getString(DBConstants.NOMBREREPRESENTANTE));
		representante.setApellido1(resultSet.getString(DBConstants.APEL1REPRESENTANTE));
		representante.setApellido2(resultSet.getString(DBConstants.APEL2REPRESENTANTE));
		expediente.setRepresentante(representante);

		return expediente;
	}

}
